package com.cdf.factory.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
//@EnableHystrix
@EnableHystrixDashboard
//@EnableEurekaClient
@EnableTurbine
@EnableDiscoveryClient
public class FactoryMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryMonitorApplication.class, args);
	}
}
