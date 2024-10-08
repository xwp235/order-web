package com.xweb.starter.modules.security.config.strategy;

import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.utils.JsonUtil;
import com.xweb.starter.utils.MessageUtil;
import com.xweb.starter.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import java.io.IOException;

public final class SimpleCompositeInvalidSessionStrategy implements InvalidSessionStrategy {

    private final Log logger = LogFactory.getLog(getClass());
    private final String destinationUrl;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public SimpleCompositeInvalidSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
          if (RequestUtil.isAjaxRequest(request)){
              setResponseDetails(response);
              var result = JsonResp.error(MessageUtil.getMessage("info_invalid_session")).setCode(HttpStatus.UNAUTHORIZED.value());
              response.getWriter().write(JsonUtil.obj2Json(result));
          } else {
              if (this.logger.isDebugEnabled()) {
                  this.logger.debug("Starting new session (if required) and redirecting to '" + this.destinationUrl + "'");
              }
              request.getSession();
              redirectStrategy.sendRedirect(request, response, this.destinationUrl);
          }
    }

    private void setResponseDetails(HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK.value());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}
