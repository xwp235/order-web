package com.xweb.starter.modules.security.config.properties;

import lombok.Data;

@Data
public class ApiLoginProperties {

    private String usernameParameter;
    private String passwordParameter;
    private String loginProcessingUrl;
    private Boolean postOnly;

}
