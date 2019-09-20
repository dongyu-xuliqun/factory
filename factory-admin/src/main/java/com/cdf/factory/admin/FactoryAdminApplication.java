package com.cdf.factory.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAdminServer
@EnableDiscoveryClient
public class FactoryAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(FactoryAdminApplication.class, args);
	}
}
