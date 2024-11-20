package com.xweb.starter.utils;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.TOTPGenerator;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;

public class OtpUtil {

    private TOTPGenerator totpGenerator;
    private KeyGenerator keyGenerator;

    {
        try {
            keyGenerator = KeyGenerator.getInstance(HMACAlgorithm.SHA512.getHMACName());
            // sha-1和sha-256需要64个字节(512位)的key
            // sha-512需要128个字节(1024位)的key
            keyGenerator.init(128);
            var secretKey = keyGenerator.generateKey().getEncoded();
            totpGenerator = new TOTPGenerator.Builder(secretKey)
                    .withHOTPGenerator(builder -> {
                        builder.withPasswordLength(6);
                        builder.withAlgorithm(HMACAlgorithm.SHA512);
                    })
                    .withPeriod(Duration.ofSeconds(60*5))
                    .build();
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public String createTotp(Instant time) {
        return totpGenerator.at(time);
    }

    public boolean verify(String code) {
        return totpGenerator.verify(code);
    }


}
