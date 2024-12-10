package com.xweb.starter.modules.security.service;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import com.xweb.starter.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final RedissonClient redissonClient;
    private final OtpUtil otpUtil;

    public String cacheUser(SecureUser secureUser) {
        var mfaId = RandomStringUtils.secure().nextAlphanumeric(12);
        var userCache = redissonClient.getMapCache(Constants.MFA_USER);
        if (!userCache.containsKey(mfaId)) {
            userCache.put(mfaId, secureUser, 300, TimeUnit.SECONDS);
        }
        return mfaId;
    }

    public Optional<SecureUser> retireUser(String mfaId) {
        RMapCache<String,SecureUser> userCache = redissonClient.getMapCache(Constants.MFA_USER);
        if (userCache.containsKey(mfaId)){
            return Optional.of(userCache.get(mfaId));
        }
        return Optional.empty();
    }

    public Optional<SecureUser> verifyTotp(String mfaId,String code) {
        RMapCache<String,SecureUser> userCache = redissonClient.getMapCache(Constants.MFA_USER);
        if (!userCache.containsKey(mfaId)||userCache.get(mfaId)==null){
            return Optional.empty();
        }
        try {
            var cachedUser = userCache.get(mfaId);
            var isValid = otpUtil.verify(cachedUser.getMfaKey(), code);
            if (!isValid){
                return Optional.empty();
            }
            userCache.remove(mfaId);
            return Optional.of(cachedUser);
        }catch(Exception e){
            return Optional.empty();
        }
    }
}
