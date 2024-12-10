package com.xweb.starter.modules.security.config.filter;

import com.xweb.starter.modules.security.config.details.LoginExtraDetails;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import com.xweb.starter.modules.security.service.UserCacheService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class WebAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserCacheService userCacheService;

    public WebAuthenticationFilter(AuthenticationManager authenticationManager,
                                   UserCacheService userCacheService) {
        setAuthenticationManager(authenticationManager);
        setAuthenticationDetailsSource(LoginExtraDetails::new);
        this.userCacheService = userCacheService;
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var secureUser = (SecureUser)authResult.getPrincipal();
        if (!secureUser.getUsingMfa()) {
            super.successfulAuthentication(request, response, chain, authResult);
            return;
        }
        // 缓存用户id
        var mfaId = userCacheService.cacheUser(secureUser);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.addHeader("X-Authenticate","mfa");
        response.addHeader("X-Authenticate","realm="+mfaId);
        var handler = new ForwardAuthenticationSuccessHandler("/mfa-login");
        handler.onAuthenticationSuccess(request, response, authResult);
    }

}
