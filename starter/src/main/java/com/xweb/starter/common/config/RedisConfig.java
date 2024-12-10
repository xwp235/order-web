package com.xweb.starter.common.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.core.env.Environment;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import org.redisson.config.Config;
import redis.embedded.util.OS;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "spring.data.redis", value = "host")
public class RedisConfig {

    private final RedisProperties redisProperties;
    private final Environment env;
    private RedisServer redisServer;

    @PostConstruct
    void initialize() {
        // 仅在测试环境和开发环境启用嵌入式 redis
        if (!Arrays.asList(env.getActiveProfiles()).contains("prod")) {
            // 指定 Redis for Windows 的可执行文件路径
            var customProvider = RedisExecProvider.defaultProvider()
                    .override(OS.WINDOWS, "D:\\Softwares\\Redis-5.0.14.1\\redis-server.exe");
            // 创建 RedisServer 实例并传递参数
            redisServer = RedisServer.builder()
                    .redisExecProvider(customProvider)
                    .build();
            redisServer.start();
        }
    }

    @PreDestroy
    void deInit() {
        // 仅在测试环境和开发环境关闭嵌入式 redis
        if (!Arrays.asList(env.getActiveProfiles()).contains("prod")) {
            redisServer.stop();
        }
    }

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() {
        Config config = new Config();
        // sentinel
        if (redisProperties.getSentinel() != null) {
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
            sentinelServersConfig.setMasterName(redisProperties.getSentinel().getMaster());
            val nodes = redisProperties.getSentinel().getNodes();
            sentinelServersConfig.addSentinelAddress(nodes.toArray(new String[0]));
            sentinelServersConfig.setDatabase(redisProperties.getDatabase());
            if (redisProperties.getPassword() != null) {
                sentinelServersConfig.setPassword(redisProperties.getPassword());
            }
        } else { // 单个 Server
            SingleServerConfig singleServerConfig = config.useSingleServer();
            // format as redis://127.0.0.1:7181 or rediss://127.0.0.1:7181 for SSL
            String schema = redisProperties.getSsl().isEnabled() ? "rediss://" : "redis://";
            singleServerConfig.setAddress(schema + redisProperties.getHost() + ":" + redisProperties.getPort());
            singleServerConfig.setDatabase(redisProperties.getDatabase());
            if (redisProperties.getPassword() != null) {
                singleServerConfig.setPassword(redisProperties.getPassword());
            }
        }
        return Redisson.create(config);
    }

}
