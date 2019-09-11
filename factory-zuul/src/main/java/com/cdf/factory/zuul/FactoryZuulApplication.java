package com.cdf.factory.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@RefreshScope
public class FactoryZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryZuulApplication.class, args);
	}
}
