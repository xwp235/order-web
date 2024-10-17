package com.xweb.starter.common.limiter.aspect;

import com.xweb.starter.common.exception.BusinessException;
import com.xweb.starter.common.exception.enums.BusinessExceptionEnum;
import com.xweb.starter.common.limiter.LimitHandler;
import com.xweb.starter.common.limiter.annotation.RateLimit;
import com.xweb.starter.common.limiter.metadata.MethodMetadata;
import com.xweb.starter.common.limiter.metadata.RateLimitMethodMetaData;
import com.xweb.starter.utils.ReflectUtil;
import com.xweb.starter.utils.RequestUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 速率限制拦截切面处理类
 */
@AllArgsConstructor
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RateLimitAspect {

    /**
     * 缓存方法上的源注解信息。减少反射的开销
     */
    private static final Map<String, RateLimit> RATE_LIMIT_MAP = new ConcurrentHashMap<>();
    private final LimitHandler limitHandler;

    /**
     * 限流注解切面
     *
     * @param pjp {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable 限流异常
     */
    @Around("@annotation(com.xweb.starter.common.limiter.annotation.RateLimit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        var methodMetadata = this.buildMethodMetadata(pjp);
        if (limitHandler.proceed(methodMetadata)) {
            return pjp.proceed();
        } else {
            // 获取当前请求的上下文
            var request = RequestUtil.getServletRequest();
            // 判断请求的类型
            if (!RequestUtil.isAjaxRequest(request)) {
                // 如果是页面请求，重定向到“请求过多”页面
                var response = RequestUtil.getServletResponse();
                if (response != null) {
                    if (request != null) {
                        request.getRequestDispatcher("/too-many-requests")
                                .forward(request, response);
                    } else {
                        throw new BusinessException(BusinessExceptionEnum.REQUEST_TOO_MANY);
                    }
                    return null;
                }
            }
            throw new BusinessException(BusinessExceptionEnum.REQUEST_TOO_MANY);
        }
    }

    /**
     * 获取执行速率限制注解，缓存反射信息
     *
     * @param method          执行方法
     * @param classMethodName 执行类方法名
     * @return 方法对应的注解源信息，如果有，直接返回，如果无，获取放入缓存。
     */
    public RateLimit getRateLimit(Method method, String classMethodName) {
        return RATE_LIMIT_MAP.computeIfAbsent(classMethodName, k -> method.getAnnotation(RateLimit.class));
    }

    private MethodMetadata buildMethodMetadata(ProceedingJoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        var classMethodName = ReflectUtil.getClassMethodName(method);
        var rateLimit = this.getRateLimit(method, classMethodName);
        return new RateLimitMethodMetaData(method, joinPoint::getArgs, rateLimit);
    }

}
