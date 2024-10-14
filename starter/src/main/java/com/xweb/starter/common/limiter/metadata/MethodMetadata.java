package com.xweb.starter.common.limiter.metadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 运行时的信息
 */
public interface MethodMetadata {

    /**
     * 获得当前运行时方法名
     * {@link Method}
     *
     * @return 运行时方法名
     */
    String getClassMethodName();

    /**
     * 获得当前运行时方法名
     *
     * @return 运行时方法
     */
    Method getMethod();

    /**
     * 获得运行时方法的参数
     *
     * @return 参数数组
     */
    Object[] getArgs();

    /**
     * 获得当前运行的参数
     *
     * @return 获取到的泛型的值
     */
    <T extends Annotation> T getAnnotation();

}
