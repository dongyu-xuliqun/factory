package com.cdf.factory.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
public class MailSendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailSendApplication.class, args);
	}
}
