package com.cdf.factory.feign;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "service", fallback = MFeignClientFallback.class, configuration = MFeignConfig.class)
public interface MFeignClient {

	// 这是被请求微服务的地址，也就是provider的地址
	@GetMapping(value = "/test/{msg}")
	String sayHelloWorld2(@PathVariable("msg") String msg);
	
	@GetMapping(value = "/test/list")
	List<Integer> list();
	
	@GetMapping(value = "/test/array")
	Integer[] array();
}
