package com.cdf.factory.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class FactoryAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryAdminApplication.class, args);
	}
}
