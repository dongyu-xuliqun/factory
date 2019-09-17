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
	
	@HystrixCommand(commandKey="authHystrixCommand") //配置全局唯一标识服务的名称，比如，库存系统有一个获取库存服务，那么就可以为这个服务起一个名字来唯一识别该服务，如果不配置，则默认是@HystrixCommand注解修饰的函数的函数名。
	public void authHystrixCommand() {
		System.out.println("触发了断路由");
	}
}
