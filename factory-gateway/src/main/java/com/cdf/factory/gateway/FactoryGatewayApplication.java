package com.cdf.factory.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
//@RefreshScope
@EnableHystrix
public class FactoryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryGatewayApplication.class, args);
	}
}
