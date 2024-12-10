package com.xweb.starter.modules.security.config.apply;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xweb.starter.modules.security.config.details.LoginExtraDetails;
import com.xweb.starter.modules.security.config.filter.ApiAuthenticationFilter;
import com.xweb.starter.modules.security.config.filter.WebAuthenticationFilter;
import com.xweb.starter.modules.security.config.properties.FormLoginProperties;
import com.xweb.starter.modules.security.config.properties.SecurityProperties;
import com.xweb.starter.modules.security.service.UserCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * form表单登录配置
 */
@RequiredArgsConstructor
public class ApplySecurityConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    private final SecurityProperties securityProperties;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final UserCacheService userCacheService;

    @Override
    public void init(HttpSecurity http) throws Exception {
        var formLogin = securityProperties.getFormLogin();
        var securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
        var sessionAuthenticationStrategy = http.getSharedObject(SessionAuthenticationStrategy.class);
        var apiAuthenticationFilter = new ApiAuthenticationFilter(
                securityProperties, objectMapper,
                authenticationManager,securityContextRepository,
                sessionAuthenticationStrategy,
                userCacheService
        );

        var webAuthenticationFilter = getWebAuthenticationFilter(formLogin, sessionAuthenticationStrategy, securityContextRepository);

        http
            // form表单登录配置
            .formLogin(form ->
               form
                   // 登录页面
                   .loginPage(formLogin.getLoginPage())
                   .usernameParameter(formLogin.getUsernameParameter())
                   .passwordParameter(formLogin.getPasswordParameter())
                   // form的action属性的路径
                   .loginProcessingUrl(formLogin.getLoginProcessingUrl())
                   // 如果你需要登录成功后无论如何都转发到一个固定的页面，并且不希望浏览器地址栏发生变化，请使用 successForwardUrl。
                   // 如果你希望在登录成功后，用户能够返回他们原本想访问的页面（如果有的话），并且你希望页面地址发生变化，请使用 defaultSuccessUrl。
                   .defaultSuccessUrl(formLogin.getDefaultSuccessUrl())
                   .authenticationDetailsSource(LoginExtraDetails::new)
            )
                // 用户名密码登录配置
            .addFilterAt(webAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // rest api登录配置
                .addFilterAt(apiAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private WebAuthenticationFilter getWebAuthenticationFilter(FormLoginProperties formLogin, SessionAuthenticationStrategy sessionAuthenticationStrategy, SecurityContextRepository securityContextRepository) {
        var webAuthenticationFilter = new WebAuthenticationFilter(authenticationManager);
        webAuthenticationFilter.setUsernameParameter(formLogin.getUsernameParameter());
        webAuthenticationFilter.setPasswordParameter(formLogin.getPasswordParameter());
        webAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(formLogin.getLoginProcessingUrl(), "POST"));
        // 配置自定义用户名密码登录认证过滤器
        webAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        webAuthenticationFilter.setSecurityContextRepository(securityContextRepository);
        var handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl(formLogin.getDefaultSuccessUrl());
        handler.setAlwaysUseDefaultTargetUrl(false);
        webAuthenticationFilter.setAuthenticationSuccessHandler(handler);
        return webAuthenticationFilter;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {

    }

}
