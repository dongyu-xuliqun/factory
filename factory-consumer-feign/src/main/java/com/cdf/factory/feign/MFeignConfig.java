package com.cdf.factory.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class MFeignConfig {

	@Bean	
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
