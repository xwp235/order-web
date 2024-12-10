package com.xweb.starter.common.constants;

import com.xweb.starter.utils.MessageUtil;

public interface Constants {

    String ROLE_PREFIX = "ROLE_";
    String LOGBACK_LOG_THREAD_ID = "LOG_ID";
    String AUTHORIZE_PERMISSION_METADATA_SOURCE_PERMISSION_KEY_DELIMITER = ">";
    String AUTHORIZE_PERMISSION_KEY_DELIMITER = "|";

    char COLON = ':';

    int DEFAULT_REQUEST_SUCCESS_CODE = 200;
    int DEFAULT_REQUEST_ERROR_CODE = 500;

    interface EXCEPTION_TYPE {
        String INFO ="INFO";
        String WARN = "WARN";
        String ERROR = "ERROR";
    }

    interface ERROR_CODE {
        int ERR_10000 = 10000;
        boolean ERR_10000_SHOULD_LOG = false;
        String ERR_10000_MSG = MessageUtil.getMessage("error_invalid_request_parameters");

        int ERR_10001 = 10001;
        boolean ERR_10001_SHOULD_LOG = true;
        String ERR_10001_MSG = MessageUtil.getMessage("error_obj_serialize2json_failed");

        int ERR_10002 = 10002;
        boolean ERR_10002_SHOULD_LOG = true;
        String ERR_10002_MSG = MessageUtil.getMessage("error_json_deserialize2obj_failed");

        int ERR_10003 = 10003;
        boolean ERR_10003_SHOULD_LOG = true;
        String ERR_10003_MSG = MessageUtil.getMessage("error_deep_copy_obj_failed");

        int ERR_10004 = 10004;
        boolean ERR_10004_SHOULD_LOG = false;
        String ERR_10004_MSG = MessageUtil.getMessage("error_request_too_many_in_short_period");
    }

    String MFA_USER = "mfa-user";

}
