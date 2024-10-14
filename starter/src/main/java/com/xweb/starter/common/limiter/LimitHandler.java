package com.xweb.starter.common.limiter;

import com.xweb.starter.common.limiter.metadata.MethodMetadata;
import com.xweb.starter.common.limiter.metadata.RateLimitMethodMetaData;

/**
 * 速率限制处理器接口
 */
public interface LimitHandler {

    /**
     * 继续执行
     *
     * @param methodMetadata {@link RateLimitMethodMetaData}
     * @return true 继续执行 false 限流不执行
     */
    boolean proceed(MethodMetadata methodMetadata);
}
