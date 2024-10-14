package com.xweb.starter.common.exception;

import com.xweb.starter.common.exception.enums.IBusinessException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public final class BusinessException extends RuntimeException{

    private final IBusinessException errorInfo;

    public BusinessException(IBusinessException e) {
        super(e.getMessage());
        this.errorInfo = e;
    }

    public BusinessException(String message, int code,String exceptionType,boolean shouldLog) {
        super(message);
        this.errorInfo = new IBusinessException() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getType() {
                return exceptionType;
            }

            @Override
            public boolean shouldLog() {
                return shouldLog;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }

    public String getType() {
        return errorInfo.getType();
    }

    public int getCode() {
        return errorInfo.getCode();
    }

    public boolean shouldLog() {
        return errorInfo.shouldLog();
    }

    /**
     * 不写入堆栈信息，简化异常日志，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
