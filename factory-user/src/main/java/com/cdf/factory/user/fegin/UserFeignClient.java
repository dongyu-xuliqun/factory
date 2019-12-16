package com.cdf.factory.user.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.cdf.factory.common.enums.RequestDTO;
import com.cdf.factory.common.enums.ResponseDTO;

@FeignClient(name = "user", fallbackFactory = UserFeignFallbackFactory.class)
public interface UserFeignClient {

	@PostMapping("/black/user")
    ResponseDTO<JSONObject> blackUserPlatform(@RequestBody RequestDTO<Void> request);
}
