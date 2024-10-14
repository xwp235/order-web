package com.xweb.starter.common.limiter.annotation;

import com.xweb.starter.common.limiter.strategy.IpKeyGenerateStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 速率限制注解类
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RateLimit {

    /*
     * 唯一标示，支持SpEL表达式
     */
    String key() default "";

    /*
     * 限定阈值
     * 时间间隔 interval 范围内超过该数量会触发锁
     */
    int count();

    /*
     * 时间间隔，默认 3 分钟
     * 例如 5s 五秒，6m 六分钟，7h 七小时，8d 八天
     */
    String interval() default "3m";

    /**
     * 限制策略
     */
    String[] strategy() default {IpKeyGenerateStrategy.TYPE};

}
