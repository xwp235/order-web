package com.xweb.starter.modules.security.config.filter;

import com.xweb.starter.modules.security.config.properties.SecurityProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CheckLoginStateFilter extends OncePerRequestFilter {

    private final SecurityProperties securityProperties;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var formLogin = securityProperties.getFormLogin();
        var loginPageUrl = formLogin.getLoginPage();
        var defaultSuccessUrl = formLogin.getDefaultSuccessUrl();
        // 获取当前认证对象
        var auth = SecurityContextHolder.getContext().getAuthentication();
        // 检查是否已认证且请求的是登录页面
        if (Objects.nonNull(auth) && auth.isAuthenticated() && loginPageUrl.equals(request.getRequestURI())) {
            // 重定向到默认成功页面
            response.sendRedirect(defaultSuccessUrl);
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
