package com.xweb.starter.modules.security.domain.bo;

import lombok.Data;

@Data
public class RequestPermission {

    private String requestMethod;
    private String requestUrl;
    private String permissionKey;

}
