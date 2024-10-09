package com.xweb.starter.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtil {

    public static String getMessage(String key,String ...values) {
        var messageSource = SpringUtil.getBean(MessageSource.class);
        var locale = LocaleContextHolder.getLocale();
        if (values.length > 0) {
            return messageSource.getMessage(key,values, locale);
        }
        return messageSource.getMessage(key,null, locale);
    }

    public static String getLocale() {
        var localeStr =  LocaleContextHolder.getLocale().toString();
        if (StringUtils.isNotBlank(localeStr)) {
            return "en";
        }
        return localeStr.split("_")[0];
    }

}
