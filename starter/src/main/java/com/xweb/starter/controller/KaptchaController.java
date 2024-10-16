package com.xweb.starter.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xweb.starter.utils.CaffeineCacheUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("kaptcha")
@RequiredArgsConstructor
public class KaptchaController {

    private final DefaultKaptcha defaultKaptcha;

    @GetMapping("image-code/{imageCodeToken}")
    public void imageCode(@PathVariable String imageCodeToken, HttpServletResponse httpServletResponse) throws Exception {
        var jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生成验证码字符串
            var createText = defaultKaptcha.createText();
            // 将生成的验证码放入缓存(5分钟后过期)，后续验证的时候用到
            CaffeineCacheUtil.put(imageCodeToken, createText);
            // 使用验证码字符串生成验证码图片
            var challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "png", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        var captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader(HttpHeaders.CACHE_CONTROL, "no-store");
        httpServletResponse.setHeader(HttpHeaders.PRAGMA, "no-cache");
        httpServletResponse.setDateHeader(HttpHeaders.EXPIRES, 0);
        httpServletResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
        try(
                var responseOutputStream = httpServletResponse.getOutputStream()
        ) {
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
        }
    }

}
