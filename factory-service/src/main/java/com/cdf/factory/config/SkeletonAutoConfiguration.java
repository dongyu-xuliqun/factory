package com.cdf.factory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cdf.factory.service.MessageSourceService;
import com.cdf.factory.service.MessageSourceServiceImpl;

@Configuration
@ConditionalOnClass({MessageSource.class, MessageSourceService.class})
@EnableConfigurationProperties(BossProperties.class)
public class SkeletonAutoConfiguration {
    @Autowired
    private MessageSource messageSource;   
    @Bean
    @ConditionalOnMissingBean
    public MessageSourceService messageSourceService() {
        return new MessageSourceServiceImpl(this.messageSource);
    }
}
