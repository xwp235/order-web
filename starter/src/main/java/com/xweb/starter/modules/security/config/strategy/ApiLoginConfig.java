package com.xweb.starter.modules.security.config.strategy;

import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class ApiLoginConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http
                .formLogin(form ->
                        form.loginPage("/login")
                )
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                );
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {

    }

}
