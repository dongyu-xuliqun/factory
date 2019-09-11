package com.cdf.factory;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
@EnableRabbit
public class FactoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryServiceApplication.class, args);
	}
}
