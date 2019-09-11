package com.cdf.factory.service.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class LoginController {
	
	@GetMapping("login")
	public Mono<String> sayHelloWorld(@PathVariable("msg") String msg) {
		
		
		System.out.println("come on " + msg);
        return Mono.just("sc-provider receive : " +msg);		
    }
}
