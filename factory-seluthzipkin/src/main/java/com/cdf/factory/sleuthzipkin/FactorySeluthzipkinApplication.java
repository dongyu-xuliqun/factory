package com.cdf.factory.sleuthzipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.internal.EnableZipkinServer;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class FactorySeluthzipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactorySeluthzipkinApplication.class, args);
	}
}
