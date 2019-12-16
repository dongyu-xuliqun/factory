package com.cdf.factory.user.fegin;

import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;
import com.cdf.factory.common.enums.RequestDTO;
import com.cdf.factory.common.enums.ResponseDTO;

public class UserFeignFallback  implements UserFeignClient {
	private final Throwable cause;

	public UserFeignFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public ResponseDTO<JSONObject> blackUserPlatform(RequestDTO<Void> request) {
		return null;
	}
	
	private <T> ResponseDTO<T> getDefaultFallback(T data) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
//        response.setMessage(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        response.setMessage(this.cause.getMessage());
        response.setData(data);
        return response;
    }
}
