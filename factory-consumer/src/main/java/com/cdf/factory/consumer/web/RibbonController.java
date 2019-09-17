package com.cdf.factory.consumer.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/consumer")
public class RibbonController {

	@Autowired
	private RestTemplate restTemplate;

	// @Autowired
	// private LoadBalancerClient loadBalancerClient;

	@PostMapping("/login")
	public String login(@RequestBody String json, HttpServletRequest request) {
		return "";
	}

	@GetMapping("/ribbon/{wd}")
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	public String sayHelloWorld(@PathVariable("wd") String parm) {
		String res = restTemplate.getForObject("http://demo-service/test/" + parm, String.class);
		return res;
	}

	@GetMapping("/ribbon/list/{wd}")
	@HystrixCommand(fallbackMethod = "fallbackMethodList")
	public String list(@PathVariable("wd") String parm) {
		String res = restTemplate.getForObject("http://demo-service/test/" + parm, String.class);
		return res;
	}

	public String fallbackMethod(@PathVariable("wd") String parm) {
		return "fallback";
	}

	public String fallbackMethodList(@PathVariable("wd") String parm) {
		return "fallback";
	}
}
