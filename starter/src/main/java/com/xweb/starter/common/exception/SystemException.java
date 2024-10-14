package com.xweb.starter.common.exception;

import com.xweb.starter.utils.MessageUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.xweb.starter.common.constants.Constants.DEFAULT_REQUEST_ERROR_CODE;
import static com.xweb.starter.common.constants.Constants.EXCEPTION_TYPE.ERROR;


@EqualsAndHashCode(callSuper = true)
@Getter
public final class SystemException extends RuntimeException{

    private final String type;
    private final int code;
    private final boolean shouldLog;

    public SystemException(int code, boolean shouldLog, Throwable e) {
        super(e);
        this.type = ERROR;
        this.code = code;
        this.shouldLog = shouldLog;
    }

    public SystemException(int code, boolean shouldLog, String message) {
        super(message);
        this.type = ERROR;
        this.code = code;
        this.shouldLog = shouldLog;
    }

    public SystemException() {
        super(MessageUtil.getMessage("error_server_internal_error"));
        this.type = ERROR;
        this.code = DEFAULT_REQUEST_ERROR_CODE;
        this.shouldLog = true;
    }

}
