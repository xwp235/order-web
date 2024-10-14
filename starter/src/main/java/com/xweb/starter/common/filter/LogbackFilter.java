package com.xweb.starter.common.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogbackFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        // 获取日志记录器的名称
        var loggerName = event.getLoggerName();
        // 获取当前线程的调用堆栈
        var stackTrace = Thread.currentThread().getStackTrace();

        // 检查是否在特定的类中调用了 mapper 的方法
        for (var element : stackTrace) {
            if (element.getClassName().equals("com.xweb.starter.modules.schedule.jobs.CheckLoginUserSessionJob")) {
                if (loggerName.startsWith("com.xweb.starter.modules.security.mapper")) {
                    return FilterReply.DENY;
                }
            }
        }

        // 默认情况是接受日志
        return FilterReply.NEUTRAL;
    }

}
