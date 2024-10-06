package com.xweb.starter.modules.security.helpers;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class SecurityHelper {

    public static String generateBtnPermissionMapKey(String requestMethod,String requestUrl) {
        return StringUtils.joinWith("-",requestMethod.toUpperCase(Locale.ROOT),requestUrl);
    }

}
