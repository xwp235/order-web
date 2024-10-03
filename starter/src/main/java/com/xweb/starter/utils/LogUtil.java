package com.xweb.starter.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class LogUtil {

    public static void warn(String warnInfo,Object ...messages){
        if (log.isWarnEnabled()){
            log.warn(warnInfo,messages);
        }
    }

    public static void debug(String debugInfo,Object... msgs){
        if (log.isDebugEnabled()){
            log.debug(debugInfo,msgs);
        }
    }

    public static void error(String error,Throwable e) {
        if (log.isErrorEnabled()) {
            if (Objects.nonNull(e)) {
                log.error(error, e);
            } else {
                log.error(error);
            }
        }
    }

    public static void error(String error) {
        error(error,null);
    }

    public static void info(String info,Object... msgs) {
        if (log.isInfoEnabled()){
            log.info(info,msgs);
        }
    }

}
