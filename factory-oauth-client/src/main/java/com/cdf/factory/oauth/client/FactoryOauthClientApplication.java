package com.cdf.factory.oauth.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
@EnableOAuth2Client
@EnableAuthorizationServer
public class FactoryOauthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryOauthClientApplication.class, args);
	}
}
