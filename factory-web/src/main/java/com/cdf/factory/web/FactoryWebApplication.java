package com.cdf.factory.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableHystrix
public class FactoryWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryWebApplication.class, args);
	}
}
