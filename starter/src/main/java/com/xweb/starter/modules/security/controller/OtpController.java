package com.xweb.starter.modules.security.controller;

import com.xweb.starter.common.exception.BusinessException;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import com.xweb.starter.modules.security.req.SendOtpReq;
import com.xweb.starter.modules.security.service.UserCacheService;
import com.xweb.starter.utils.OtpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("otp")
@RequiredArgsConstructor
public class OtpController {

    private final UserCacheService userCacheService;
    private final SecurityContextRepository securityContextRepository;
    private final OtpUtil otpUtil;

    @PostMapping
    public void send(@RequestBody SendOtpReq req) {
        userCacheService.retireUser(req.getMfaId())
                .map(authentication-> {
                    var user = (SecureUser)authentication.getPrincipal();
                    var otp = otpUtil.createTotp(user.getMfaKey());
                    return Pair.of(user,otp);
                })
                .ifPresentOrElse(pair->{
                    var user = pair.getFirst();
                    var otp = pair.getSecond();
                    System.out.println(otp.at(Instant.now()));
                    if (StringUtils.equals(req.getMfaType(),"EMAIL")) {
                        System.out.println(user.getEmail());
                    } else {
                        System.out.println(user.getMobile());
                    }
                }, () -> {
                    throw new BusinessException("",500,"INFO",false);
                });
    }

    @PostMapping("{mfaId}")
    public SecureUser verify(@PathVariable String mfaId, @RequestParam String code, HttpServletRequest request, HttpServletResponse response) {
        return userCacheService.verifyTotp(mfaId,code)
                .map(authentication->{
                    var securityContextHolderStrategy = SecurityContextHolder
                            .getContextHolderStrategy();
                    var context = securityContextHolderStrategy.createEmptyContext();
                    context.setAuthentication(authentication);
                    securityContextHolderStrategy.setContext(context);
                    securityContextRepository.saveContext(context, request, response);
                    return (SecureUser)authentication.getPrincipal();
                }).orElseThrow(() -> new BusinessException("",500,"INFO",false));
    }

}
