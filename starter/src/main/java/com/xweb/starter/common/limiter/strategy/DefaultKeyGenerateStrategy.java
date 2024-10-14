package com.xweb.starter.common.limiter.strategy;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.common.limiter.metadata.MethodMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 默认key策略
 */
@Component
public class DefaultKeyGenerateStrategy implements IKeyGenerateStrategy {

    private final static String TYPE = "key-generate-default";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getKey(MethodMetadata methodMetadata, String parseKey) {
        String result = methodMetadata.getClassMethodName();
        if (StringUtils.hasLength(parseKey)) {
            result += Constants.COLON + parseKey;
        }
        return result;
    }

}
