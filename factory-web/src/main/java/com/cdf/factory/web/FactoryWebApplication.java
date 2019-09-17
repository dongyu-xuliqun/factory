package com.cdf.factory.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
public class FactoryWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryWebApplication.class, args);
	}
}
