package com.xweb.starter.common.exception.enums;

import static com.xweb.starter.common.constants.Constants.ERROR_CODE;
import static com.xweb.starter.common.constants.Constants.EXCEPTION_TYPE.ERROR;
import static com.xweb.starter.common.constants.Constants.EXCEPTION_TYPE.WARN;

public enum BusinessExceptionEnum implements IBusinessException {

    INVALID_REQUEST_PARAMETER(WARN, ERROR_CODE.ERR_10000,ERROR_CODE.ERR_10000_SHOULD_LOG, ERROR_CODE.ERR_10000_MSG),
    REQUEST_TOO_MANY(WARN,ERROR_CODE.ERR_10004,ERROR_CODE.ERR_10004_SHOULD_LOG, ERROR_CODE.ERR_10004_MSG),

    OBJ_SERIALIZE_2_JSON_FAILED(ERROR, ERROR_CODE.ERR_10001,ERROR_CODE.ERR_10001_SHOULD_LOG, ERROR_CODE.ERR_10001_MSG),
    JSON_DESERIALIZE_2_OBJ_FAILED(ERROR , ERROR_CODE.ERR_10002,ERROR_CODE.ERR_10002_SHOULD_LOG, ERROR_CODE.ERR_10002_MSG),
    DEEP_COPY_OBJ_FAILED(ERROR, ERROR_CODE.ERR_10003,ERROR_CODE.ERR_10003_SHOULD_LOG, ERROR_CODE.ERR_10003_MSG),
    ;

    BusinessExceptionEnum(String type,int code,boolean shouldLog,String message) {
        this.type = type;
        this.code = code;
        this.shouldLog = shouldLog;
        this.message = message;
    }

    private final String type;
    private final int code;
    private final boolean shouldLog;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean shouldLog() {
        return shouldLog;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
