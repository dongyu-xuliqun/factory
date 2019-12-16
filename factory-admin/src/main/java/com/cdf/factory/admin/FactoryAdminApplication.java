package com.cdf.factory.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
@RefreshScope
public class FactoryAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(FactoryAdminApplication.class, args);
	}
}
