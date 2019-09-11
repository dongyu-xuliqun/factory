package com.cdf.factory.feign;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MFeignClientFallback implements MFeignClient {

	@Override
	public String sayHelloWorld2(String msg) {
		// TODO Auto-generated method stub
		return "fallback";
	}

	@Override
	public List<Integer> list() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public Integer[] array() {
		// TODO Auto-generated method stub
		return new Integer[0];
	}

}
