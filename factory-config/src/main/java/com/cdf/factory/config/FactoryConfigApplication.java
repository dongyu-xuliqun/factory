package com.cdf.factory.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigServer
@EnableDiscoveryClient
public class FactoryConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryConfigApplication.class, args);
	}
}
