package com.xweb.starter.modules.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.Authentication;

@Data
@EqualsAndHashCode(of = {"sessionId"})
public class SecureUserSession {

    private Long accountId;
    private String username;
    private String sessionId;
    private String device;
    private String clientIp;
    private Authentication authentication;

}
