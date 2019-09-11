package com.cdf.factory.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
public class FactoryCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryCacheApplication.class, args);
	}
}
