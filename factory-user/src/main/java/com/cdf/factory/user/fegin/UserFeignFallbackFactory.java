package com.cdf.factory.user.fegin;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class UserFeignFallbackFactory  implements FallbackFactory<UserFeignFallback>{

	@Override
	public UserFeignFallback create(Throwable cause) {
		// UserFeignFallback类也可以不要，此处实现内部类即可，把UserFeignFallback改成UserFeignClient
		return new UserFeignFallback(cause);
	}
}
