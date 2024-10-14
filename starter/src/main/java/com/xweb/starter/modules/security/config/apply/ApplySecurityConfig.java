package com.xweb.starter.modules.security.config.apply;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xweb.starter.modules.security.config.details.LoginExtraDetails;
import com.xweb.starter.modules.security.config.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * form表单登录配置
 */
@RequiredArgsConstructor
public class ApplySecurityConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    private final SecurityProperties securityProperties;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final SessionRegistry sessionRegistry;

    @Override
    public void init(HttpSecurity http) throws Exception {
        var formLogin = securityProperties.getFormLogin();
        var apiAuthenticationFilter = new ApiAuthenticationFilter(securityProperties, objectMapper, authenticationManager,securityContextRepository,sessionRegistry);
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
            // rest api登录配置
            .addFilterAt(apiAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {

    }

}
