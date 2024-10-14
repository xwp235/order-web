package com.xweb.starter.common.limiter;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.common.limiter.metadata.MethodMetadata;
import com.xweb.starter.common.limiter.strategy.DefaultKeyGenerateStrategy;
import com.xweb.starter.common.limiter.strategy.IKeyGenerateStrategy;
import com.xweb.starter.utils.LogUtil;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 速率限制唯一标示 key 解析器
 */
@Component
public class RateLimitKeyParser {

    private final ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
    private final ExpressionParser parser = new SpelExpressionParser();
    private final List<IKeyGenerateStrategy> keyGenerateStrategyList;
    private final IKeyGenerateStrategy defaultKeyGenerateStrategy;

    public RateLimitKeyParser(List<IKeyGenerateStrategy> keyGenerateStrategyList) {
        this.keyGenerateStrategyList = keyGenerateStrategyList;
        // 默认key生成策略
        defaultKeyGenerateStrategy = new DefaultKeyGenerateStrategy();
    }

    /**
     * 构建唯一标示 KEY
     *
     * @param useDefaultStrategy 是否使用默认的key策略
     */
    public String buildKey(MethodMetadata methodMetadata, String spELKey, String[] strategies, boolean useDefaultStrategy) {
        var key = new StringBuilder();

        // SpEL Key 解析
        var parseKey = Optional.ofNullable(spELKey)
                .filter(StringUtils::hasLength)
                .map(str -> {
                    Method method = methodMetadata.getMethod();
                    Object[] args = methodMetadata.getArgs();
                    return this.parser(spELKey, method, args);
                }).orElse("");
        if (useDefaultStrategy) {
            key.append(defaultKeyGenerateStrategy.getKey(methodMetadata, parseKey));
        }
        if (key.isEmpty()) {
            LogUtil.warn("The generated key is empty, then will use the default strategy");
            key.append(defaultKeyGenerateStrategy.getKey(methodMetadata, parseKey));
        }
        // 组装自定义策略
        for (var str : strategies) {
            keyGenerateStrategyList.stream()
                    .filter(t -> Objects.equals(t.getType(), str))
                    .findFirst()
                    .map(rateLimitStrategy ->
                        rateLimitStrategy.getKey(methodMetadata, parseKey)
                    )
                    .filter(StringUtils::hasLength)
                    .ifPresent(currentGenerateKey -> {
                                if (!key.isEmpty()) {
                                    key.append(Constants.COLON);
                                }
                                key.append(currentGenerateKey);
                            }
                    );
        }
        return key.toString();
    }

    public String parser(String key, Method method, Object[] args) {
        var context = new MethodBasedEvaluationContext(null, method, args, nameDiscoverer);
        var objKey = parser.parseExpression(key).getValue(context);
        return ObjectUtils.nullSafeToString(objKey);
    }

}
