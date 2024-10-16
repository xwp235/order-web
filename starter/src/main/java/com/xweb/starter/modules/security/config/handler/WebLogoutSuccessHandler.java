package com.xweb.starter.modules.security.config.handler;

import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.modules.security.HttpSessionUtil;
import com.xweb.starter.modules.security.SecureUserHolder;
import com.xweb.starter.modules.security.dao.HisClientLoginLogDao;
import com.xweb.starter.utils.JsonUtil;
import com.xweb.starter.utils.MessageUtil;
import com.xweb.starter.utils.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class WebLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private final HisClientLoginLogDao clientLoginLogDao;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (Objects.nonNull(authentication)) {
            var invalidSessionId = clientLoginLogDao.updateUserOffline(authentication);
            HttpSessionUtil.invalidateSession(invalidSessionId);
            SecureUserHolder.removeBySessionId(invalidSessionId);
        }
        if (RequestUtil.isAjaxRequest(request)) {
            setResponseDetails(response);
            var result = JsonResp.ok(MessageUtil.getMessage("info_logout_success"));
            response.getWriter().write(JsonUtil.obj2Json(result));
        } else {
            request.getSession();
            super.onLogoutSuccess(request, response, authentication);
        }
    }

    private void setResponseDetails(HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK.value());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}
