package com.xweb.starter.modules.security.config.apply;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.modules.security.config.details.LoginExtraDetails;
import com.xweb.starter.modules.security.config.properties.ApiLoginProperties;
import com.xweb.starter.modules.security.config.properties.SecurityProperties;
import com.xweb.starter.utils.JsonUtil;
import com.xweb.starter.utils.MessageUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.context.SecurityContextRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ApiLoginProperties apiLoginProperties;
    private final ObjectMapper objectMapper;

    public ApiAuthenticationFilter(SecurityProperties securityProperties,
                                   ObjectMapper objectMapper,
                                   AuthenticationManager authenticationManager,
                                   SecurityContextRepository securityContextRepository,
                                   SessionRegistry sessionRegistry
    ) {
        this.objectMapper = objectMapper;
        this.apiLoginProperties = securityProperties.getApiLogin();
        setFilterProcessesUrl(apiLoginProperties.getLoginProcessingUrl());
        setAuthenticationManager(authenticationManager);
        setAuthenticationDetailsSource(LoginExtraDetails::new);
        // 使用同一个上下文工厂
        super.setSecurityContextRepository(securityContextRepository);

        if (Objects.nonNull(securityProperties.getMaximumSessions())) {
            var delegateStrategies = getSessionAuthenticationStrategies(sessionRegistry,securityProperties);
            setSessionAuthenticationStrategy(new CompositeSessionAuthenticationStrategy(delegateStrategies));
        }

        setAuthenticationSuccessHandler((req,resp,authentication)-> {
            setResponseDetails(resp);
            var result = JsonResp.ok().setMsg(MessageUtil.getMessage("info_account_authenticate_success"));
            resp.getWriter().write(JsonUtil.obj2Json(result));
        });
        setAuthenticationFailureHandler((req,resp,ex) -> {
            setResponseDetails(resp);
            var result = JsonResp.error(ex.getMessage()).setCode(HttpStatus.UNAUTHORIZED.value());
            resp.getWriter().write(JsonUtil.obj2Json(result));
        });
    }

    private ArrayList<SessionAuthenticationStrategy> getSessionAuthenticationStrategies(SessionRegistry sessionRegistry, SecurityProperties securityProperties) {
        var concurrentSessionControlStrategy = new ConcurrentSessionControlAuthenticationStrategy(
                sessionRegistry);
        concurrentSessionControlStrategy.setMaximumSessions(securityProperties.getMaximumSessions());
        concurrentSessionControlStrategy.setExceptionIfMaximumExceeded(securityProperties.getMaxSessionsPreventsLogin());
        return new ArrayList<>(Arrays.asList(
                concurrentSessionControlStrategy,
                new ChangeSessionIdAuthenticationStrategy(),
                new RegisterSessionAuthenticationStrategy(sessionRegistry)
        ));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (apiLoginProperties.getPostOnly() && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        request.getSession();
        UsernamePasswordAuthenticationToken authRequest;
        try (var is = request.getInputStream()) {
            var jsonNode = objectMapper.readTree(is);
            var username = jsonNode.get(apiLoginProperties.getUsernameParameter()).textValue();
            var password = jsonNode.get(apiLoginProperties.getPasswordParameter()).textValue();
            username = (username != null) ? username.trim() : "";
            password = (password != null) ? password : "";
            authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                    password);
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Authentication required username or password not found.");
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setResponseDetails(HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK.value());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}

