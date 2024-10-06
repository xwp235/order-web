package com.xweb.starter.modules.security.config.properties;

import lombok.Data;

@Data
public class FormLoginProperties {

    private String usernameParameter;
    private String passwordParameter;
    private String loginPage;
    private String loginProcessingUrl;
    private String defaultSuccessUrl;

}
