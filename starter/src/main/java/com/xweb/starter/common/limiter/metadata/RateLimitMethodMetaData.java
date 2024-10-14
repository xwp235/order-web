package com.xweb.starter.common.limiter.metadata;

import com.xweb.starter.common.limiter.annotation.RateLimit;
import com.xweb.starter.utils.ReflectUtil;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * 当前速率限制的运行时的信息
 */
public class RateLimitMethodMetaData implements MethodMetadata {
    /**
     * 方法
     */
    private final Method method;
    /**
     * 当前运行时方法中的参数
     */
    private final Supplier<Object[]> argsSupplier;
    /**
     * 当前方法的全称
     */
    private final String classMethodName;
    /**
     * 速率限制所用到的注解
     */
    private final RateLimit rateLimit;

    public RateLimitMethodMetaData(Method method, Supplier<Object[]> argsSupplier, RateLimit rateLimit) {
        Assert.notNull(method, "'method' cannot be null");
        Assert.notNull(argsSupplier, "'argsSupplier' cannot be null");
        Assert.notNull(rateLimit, "'rateLimit' cannot be null");
        this.method = method;
        this.argsSupplier = argsSupplier;
        this.rateLimit = rateLimit;
        this.classMethodName = ReflectUtil.getClassMethodName(method);
    }

    @Override
    public String getClassMethodName() {
        return classMethodName;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArgs() {
        return argsSupplier.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Annotation> T getAnnotation() {
        return (T) rateLimit;
    }

}
