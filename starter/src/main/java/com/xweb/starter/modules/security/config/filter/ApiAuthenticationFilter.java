package com.xweb.starter.modules.security.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.modules.security.config.details.LoginExtraDetails;
import com.xweb.starter.modules.security.config.properties.ApiLoginProperties;
import com.xweb.starter.modules.security.config.properties.SecurityProperties;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import com.xweb.starter.modules.security.service.UserCacheService;
import com.xweb.starter.utils.JsonUtil;
import com.xweb.starter.utils.MessageUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextRepository;

import java.io.IOException;

public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ApiLoginProperties apiLoginProperties;
    private final ObjectMapper objectMapper;
    private final UserCacheService userCacheService;

    public ApiAuthenticationFilter(SecurityProperties securityProperties,
                                   ObjectMapper objectMapper,
                                   AuthenticationManager authenticationManager,
                                   SecurityContextRepository securityContextRepository,
                                   SessionAuthenticationStrategy sessionAuthenticationStrategy,
                                   UserCacheService userCacheService
    ) {
        this.objectMapper = objectMapper;
        this.apiLoginProperties = securityProperties.getApiLogin();
        setFilterProcessesUrl(apiLoginProperties.getLoginProcessingUrl());
        setAuthenticationManager(authenticationManager);
        setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        this.userCacheService = userCacheService;

        setAuthenticationDetailsSource(LoginExtraDetails::new);

        // 使用同一个上下文工厂
        super.setSecurityContextRepository(securityContextRepository);

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

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (apiLoginProperties.getPostOnly() && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
//        request.getSession();
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

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var secureUser = (SecureUser)authResult.getPrincipal();
        if (!secureUser.getUsingMfa()) {
            super.successfulAuthentication(request, response, chain, authResult);
            return;
        }
        var mfaId = userCacheService.cacheUser(secureUser);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.addHeader("X-Authenticate","mfa");
        response.addHeader("X-Authenticate","realm="+mfaId);
        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

    private void setResponseDetails(HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK.value());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}

