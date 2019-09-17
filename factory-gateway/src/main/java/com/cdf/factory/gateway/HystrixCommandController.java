package com.cdf.factory.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HystrixCommandController {

	@RequestMapping("/hystrixTimeout")
	public void hystrixTimeout() {
		System.out.println("触发了断路由");
	}
	
	@HystrixCommand(commandKey="authHystrixCommand")
	public void authHystrixCommand() {
		System.out.println("触发了断路由");
	}
}
