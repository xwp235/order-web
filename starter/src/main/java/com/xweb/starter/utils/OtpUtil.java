package com.xweb.starter.utils;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.TOTPGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Base64;

/**
 * TOTP认证流程
 * 1. 用户名密码登录-依据用户的usingMfa属性来决定是否起用多因子验证
 * 2. 生成totp-从数据库中调出属于这个用户的key，生成totp
 * 3. 选择发送方法
 * 4. 通知客户端二次验证-返回一个未授权的响应，为了和用户名密码认证失败的响应区分开，在header中加入X-Authenticate：mfa,realm=请求id
 */
@Component
public class OtpUtil {

    private static final int PASSWORD_LEN = 6;

    private KeyGenerator keyGenerator;

    @PostConstruct
    public void init() {
        try {
            keyGenerator = KeyGenerator.getInstance(HMACAlgorithm.SHA512.getHMACName());
            // sha-1和sha-256需要64个字节(512位)的key
            // sha-512需要128个字节(1024位)的key
            keyGenerator.init(128);
        } catch (NoSuchAlgorithmException e) {
        }
    }

//    {
//        try {
//            keyGenerator = KeyGenerator.getInstance(HMACAlgorithm.SHA512.getHMACName());
//            // sha-1和sha-256需要64个字节(512位)的key
//            // sha-512需要128个字节(1024位)的key
//            keyGenerator.init(128);
//        } catch (NoSuchAlgorithmException e) {
//        }
//    }

    public TOTPGenerator createTotp(String key) {
        var secretKey = decodeKeyFromString(key);
        return new TOTPGenerator.Builder(secretKey.getEncoded())
                .withHOTPGenerator(builder -> {
                    builder.withPasswordLength(PASSWORD_LEN);
                    builder.withAlgorithm(HMACAlgorithm.SHA512);
                })
                .withPeriod(Duration.ofSeconds(60*5))
                .build();
    }

    public boolean verify(TOTPGenerator generator, String code) {
        return generator.verify(code);
    }

    public Key generateKey() {
        return keyGenerator.generateKey();
    }

    public String encodeKeyToString(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public String encodeKeyToString() {
        return encodeKeyToString(generateKey());
    }

    public Key decodeKeyFromString(String strKey) {
        return new SecretKeySpec(Base64.getDecoder().decode(strKey),HMACAlgorithm.SHA512.getHMACName());
    }


//    public static void main(String[] args) {
//        OtpUtil util = new OtpUtil();
//        var key = util.generateKey();
//        System.out.println(key);
//        String encodeKey =util.encodeKeyToString(key);
//        System.out.println(encodeKey);
//        System.out.println(util.decodeKeyFromString(encodeKey));
//    }
}
