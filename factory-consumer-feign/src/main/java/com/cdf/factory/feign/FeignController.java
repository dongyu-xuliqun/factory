package com.cdf.factory.feign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FeignController {

	@Autowired
	private MFeignClient feignClient;

	@GetMapping("/feign/{wd}")
	public Mono<String> sayHelloWorld2(@PathVariable("wd") String param) {
		String result = feignClient.sayHelloWorld2(param);
		return Mono.just(result);
	}

	@GetMapping("/feign/list")
	public Flux<Integer> list() {
		List<Integer> list = feignClient.list();
		Flux<Integer> userFlux = Flux.fromIterable(list);
		return userFlux;
	}

	@GetMapping("/feign/array")
	public Flux<Integer> array() {
		Integer[] arrays = feignClient.array();
		Flux<Integer> userFlux = Flux.fromArray(arrays);
		return userFlux;
	}

}
