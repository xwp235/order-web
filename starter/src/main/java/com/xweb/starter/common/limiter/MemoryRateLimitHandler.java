package com.xweb.starter.common.limiter;

import com.xweb.starter.common.limiter.annotation.RateLimit;
import com.xweb.starter.common.limiter.metadata.MethodMetadata;
import com.xweb.starter.utils.LogUtil;
import io.github.bucket4j.Bucket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
public class MemoryRateLimitHandler implements LimitHandler {


    private final RateLimitKeyParser rateLimitKeyParser;
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean proceed(MethodMetadata methodMetadata) {
        RateLimit rateLimit = methodMetadata.getAnnotation();
        var interval = Duration.parse("PT"+rateLimit.interval());
        String key = rateLimitKeyParser.buildKey(methodMetadata, rateLimit.key(), rateLimit.strategy(), true);

        Bucket bucket = buckets.computeIfAbsent(key, k -> createBucket(rateLimit.count(), interval));
        boolean allowed = bucket.tryConsume(1);
        LogUtil.warn("rate.limit.key = {}", key);
        return allowed;
    }

    // 一个容量为 capacity 个令牌，且以每 interval 补充 capacity 个令牌速度的带宽。
    private Bucket createBucket(long capacity, Duration interval) {
        return Bucket.builder()
                .addLimit(limit -> limit.capacity(capacity).refillGreedy(capacity,interval))
                .build();
    }

}
