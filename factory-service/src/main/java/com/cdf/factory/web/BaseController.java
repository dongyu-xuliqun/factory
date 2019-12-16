package com.cdf.factory.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import com.cdf.factory.config.BossProperties;
import com.cdf.factory.service.MessageSourceService;

@RestController
@RefreshScope
public class BaseController{

    @Autowired
    public BossProperties properties;
    
    @Autowired
    protected MessageSourceService messageService;

    protected String getMessage(String code) {
        return this.messageService.getMessage(code);
    }

    protected String getMessage(String code, Object[] args) {
        return this.messageService.getMessage(code, args);
    }

    protected String getMessage(String code, Object[] args, Locale locale) {
        return this.messageService.getMessage(code, args, locale);
    }

    protected String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return this.messageService.getMessage(code, args, defaultMessage, locale);
    }
}
