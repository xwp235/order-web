package com.xweb.starter.modules.security.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {

    private String invalidSessionUrl;

    private String expiredSessionUrl;

    private String accessDeniedUrl;

    private String[] csrfIgnoreUrlPatterns;

    private String[] whiteUrlPatterns;

    private Integer maximumSessions;

    private Boolean maxSessionsPreventsLogin;

    private FormLoginProperties formLogin;

    private ApiLoginProperties apiLogin;


}
