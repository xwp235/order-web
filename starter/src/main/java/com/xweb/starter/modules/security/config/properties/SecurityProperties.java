package com.xweb.starter.modules.security.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {

    private String[] csrfIgnoreUrlPatterns;

    private String[] whiteUrlPatterns;

    private FormLoginProperties formLogin;

}
