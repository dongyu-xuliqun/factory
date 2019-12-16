package com.cdf.factory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Slf4j
public class MessageSourceServiceImpl implements MessageSourceService {
    private final MessageSource messageSource;

    public MessageSourceServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String code) {
        return this.getMessage(code, null);
    }

    @Override
    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, null);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) {
        return this.getMessage(code, args, null, locale);
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        if(locale == null) {
            locale = LocaleContextHolder.getLocale();
        }
        return this.messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
