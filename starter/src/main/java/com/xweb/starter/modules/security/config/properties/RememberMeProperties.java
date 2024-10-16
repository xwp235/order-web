package com.xweb.starter.modules.security.config.properties;

import lombok.Data;

@Data
public class RememberMeProperties {

    private Boolean enabled;

    private String cookieName;

    private Integer tokenValiditySeconds;

}
