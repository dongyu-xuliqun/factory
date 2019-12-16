package com.cdf.factory.oauth.jwt.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FactoryOauthJwtResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryOauthJwtResourceServerApplication.class, args);
	}
}
