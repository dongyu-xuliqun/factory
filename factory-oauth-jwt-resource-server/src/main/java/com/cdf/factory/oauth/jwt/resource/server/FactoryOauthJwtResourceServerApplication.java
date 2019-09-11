package com.cdf.factory.oauth.jwt.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
public class FactoryOauthJwtResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryOauthJwtResourceServerApplication.class, args);
	}
}
