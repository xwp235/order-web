package com.xweb.starter.common.filter;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.utils.IdWorkerUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LogbackMDIdFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put(Constants.LOGBACK_LOG_THREAD_ID, IdWorkerUtil.getIdStr());
        filterChain.doFilter(request, response);
    }

}
