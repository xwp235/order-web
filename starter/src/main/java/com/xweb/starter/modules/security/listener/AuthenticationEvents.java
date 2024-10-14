package com.xweb.starter.modules.security.listener;

import com.xweb.starter.modules.security.HttpSessionUtil;
import com.xweb.starter.modules.security.SecureUserHolder;
import com.xweb.starter.modules.security.dao.HisClientLoginLogDao;
import com.xweb.starter.utils.RequestUtil;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class AuthenticationEvents implements HttpSessionListener {

    private final HisClientLoginLogDao hisClientLoginLogDao;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        var auth = event.getAuthentication();
        SecureUserHolder.put(auth);
        var request = RequestUtil.getServletRequest();
        Assert.notNull(request,"request object is not null.");

        var session = request.getSession(false);
        HttpSessionUtil.storeSession(session);

        hisClientLoginLogDao.saveClientLoginLog(auth);
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
    }

}
