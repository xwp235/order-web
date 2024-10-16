package com.xweb.starter.utils;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class CaffeineCacheUtil {

    private static LoadingCache<String, Object> caffeineCache;
    private static AsyncCache<String, Object> asyncCaffeineCache;

    // 同步缓存：获取缓存值
    public static Object get(String key) {
        return getLoadingCache().get(key);
    }

    // 同步缓存：手动添加缓存值
    public static void put(String key, Object value) {
        getLoadingCache().put(key, value);
    }

    // 同步缓存：移除缓存
    public static void remove(String key) {
        getLoadingCache().invalidate(key);
    }

    // 异步缓存：获取缓存值，返回 CompletableFuture
    public static CompletableFuture<Object> getAsync(String key) {
        return getAsyncCaffeineCache().get(key, item-> null);
    }

    // 异步缓存：手动添加缓存值
    public void putAsync(String key, Object value) {
        getAsyncCaffeineCache().put(key, CompletableFuture.supplyAsync(() -> value));
    }

    // 异步缓存：移除缓存
    public void removeAsync(String key) {
        getAsyncCaffeineCache().synchronous().invalidate(key);
    }

    private static LoadingCache<String,Object> getLoadingCache() {
        if (Objects.nonNull(caffeineCache)) {
            return caffeineCache;
        }
        // 复用Spring的ObjectMapper以获更高的性能
        caffeineCache = SpringUtil.getBean(LoadingCache.class);
        return caffeineCache;
    }

    private static AsyncCache<String,Object> getAsyncCaffeineCache() {
        if (Objects.nonNull(asyncCaffeineCache)) {
            return asyncCaffeineCache;
        }
        // 复用Spring的ObjectMapper以获更高的性能
        asyncCaffeineCache = SpringUtil.getBean(AsyncCache.class);
        return asyncCaffeineCache;
    }

}
