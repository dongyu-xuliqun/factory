package com.cdf.factory.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FactoryEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryEurekaServerApplication.class, args);
	}
}
