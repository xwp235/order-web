package com.xweb.starter.modules.security.config.filter;

import com.xweb.starter.modules.security.config.details.LoginExtraDetails;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class WebAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public WebAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setAuthenticationDetailsSource(LoginExtraDetails::new);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var secureUser = (SecureUser)authResult.getPrincipal();
        if (!secureUser.getUsingMfa()) {
            super.successfulAuthentication(request, response, chain, authResult);
            return;
        }
        request.setAttribute("mfaRealm", secureUser.getAccountId());
        // 缓存用户id
        var handler = new ForwardAuthenticationSuccessHandler("/mfa-login");
        handler.onAuthenticationSuccess(request, response, authResult);
    }

}
