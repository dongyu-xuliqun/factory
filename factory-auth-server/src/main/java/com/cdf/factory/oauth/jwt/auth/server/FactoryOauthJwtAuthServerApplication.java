package com.cdf.factory.oauth.jwt.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableAuthorizationServer已经在配置类上添了注解了
//@EnableHystrix
public class FactoryOauthJwtAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryOauthJwtAuthServerApplication.class, args);
	}
	
	/*@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}*/
}
