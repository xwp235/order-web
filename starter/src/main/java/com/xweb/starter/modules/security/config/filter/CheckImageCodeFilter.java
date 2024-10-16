package com.xweb.starter.modules.security.config.filter;

import com.xweb.starter.modules.security.config.properties.SecurityProperties;
import com.xweb.starter.utils.CaffeineCacheUtil;
import com.xweb.starter.utils.MessageUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CheckImageCodeFilter extends OncePerRequestFilter {

    private final SimpleUrlAuthenticationFailureHandler failureHandler;

    private final String loginProcessingUrl;
    private final String imageCodeParameter;
    private final String imageCodeTokenParameter;

    public CheckImageCodeFilter(SecurityProperties securityProperties) {
        var formLogin = securityProperties.getFormLogin();
        this.loginProcessingUrl = formLogin.getLoginProcessingUrl();
        this.imageCodeParameter = securityProperties.getImageCodeParameter();
        this.imageCodeTokenParameter = securityProperties.getImageCodeTokenParameter();
        failureHandler = new SimpleUrlAuthenticationFailureHandler(formLogin.getLoginPage()+"?error");
        failureHandler.setAllowSessionCreation(false);
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (
                StringUtils.equalsIgnoreCase(request.getMethod(), "post") &&
                StringUtils.equals(loginProcessingUrl, request.getRequestURI())
        ) {
            try {
                var userInputImageCode = request.getParameter(this.imageCodeParameter);
                if (StringUtils.isBlank(userInputImageCode)) {
                    throw new AuthenticationServiceException(MessageUtil.getMessage("error_image_verification_code_empty"));
                }
                var imageCodeToken = request.getParameter(this.imageCodeTokenParameter);
                var cacheImageCode = (String) CaffeineCacheUtil.get(imageCodeToken);
                if (StringUtils.isBlank(cacheImageCode)) {
                    throw new AuthenticationServiceException(MessageUtil.getMessage("error_image_verification_code_expired"));
                }
                if (!StringUtils.equalsIgnoreCase(cacheImageCode, userInputImageCode)) {
                    throw new AuthenticationServiceException(MessageUtil.getMessage("error_image_verification_code_incorrect"));
                }
            } catch (AuthenticationServiceException e) {
                failureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
