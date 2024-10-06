package com.xweb.starter.modules.security.listener;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.session.SessionCreationEvent;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        System.out.println("Login success");
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        System.out.println("Login fail");
    }

    @EventListener
    public void onSessionCreated(SessionCreationEvent event) {
        System.out.println("Session created");
    }

    @EventListener
    public void onSessionDestroyed(SessionDestroyedEvent event) {
        System.out.println("Session destroyed");
    }

}
