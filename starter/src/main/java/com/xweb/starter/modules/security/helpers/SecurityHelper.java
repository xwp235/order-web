package com.xweb.starter.modules.security.helpers;

import com.xweb.starter.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class SecurityHelper {

    public static String generatePermissionMapKey(String requestMethod,String requestUrl) {
        return StringUtils.joinWith(Constants.AUTHORIZE_PERMISSION_METADATA_SOURCE_PERMISSION_KEY_DELIMITER,
                requestMethod.toUpperCase(Locale.ROOT),requestUrl);
    }

}
