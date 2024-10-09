package com.xweb.starter.modules.security.config.strategy;

import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.utils.JsonUtil;
import com.xweb.starter.utils.MessageUtil;
import com.xweb.starter.utils.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import java.io.IOException;

public final class SimpleCompositeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    private final Log logger = LogFactory.getLog(getClass());

    private final String destinationUrl;

    private final RedirectStrategy redirectStrategy;

    public SimpleCompositeSessionInformationExpiredStrategy(String expiredSessionUrl) {
        this(expiredSessionUrl, new DefaultRedirectStrategy());
    }

    public SimpleCompositeSessionInformationExpiredStrategy(String expiredSessionUrl,
                                                           RedirectStrategy redirectStrategy) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(expiredSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = expiredSessionUrl;
        this.redirectStrategy = redirectStrategy;
    }


    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        var request = event.getRequest();
        var response = event.getResponse();
        if (RequestUtil.isAjaxRequest(event.getRequest())){
               setResponseDetails(response);
               var result = JsonResp.error(MessageUtil.getMessage("info_session_expired")).setCode(HttpStatus.UNAUTHORIZED.value());
               response.getWriter().write(JsonUtil.obj2Json(result));
           } else {
               this.logger.debug("Redirecting to '" + this.destinationUrl + "'");
               // 创建一个新会话，可以避免用户再度触发invalidSessionStrategy
               request.getSession();
               this.redirectStrategy.sendRedirect(request, response, this.destinationUrl);
           }
    }

    private void setResponseDetails(HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK.value());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}
