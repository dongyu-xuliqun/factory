package com.cdf.factory.user.utils;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.cdf.factory.common.enums.ResponseDTO;

public class RestResponseUtil {
    public static <T> ResponseDTO<T> response(HttpStatus httpStatus, String message, T data) {
        return response(httpStatus, message, "", data);
    }

    public static <T> ResponseDTO<T> response(HttpStatus httpStatus, String message, String token, T data) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setStatus(httpStatus.value());
        if(StringUtils.isEmpty(message)) {
            response.setMessage(httpStatus.getReasonPhrase());
        } else {
            response.setMessage(message);
        }
        if(!StringUtils.isEmpty(token)) {
            response.setToken(token);
        }
//        response.setTimestamp(Date.from(Instant.now()));
        response.setData(data);
        return response;
    }

    public static <T> ResponseDTO<T> ok(String message, T data) {
        return ok(message, "", data);
    }

    public static <T> ResponseDTO<T> ok(String message, String token, T data) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.OK.value());
        if(StringUtils.isEmpty(message)) {
            response.setMessage(HttpStatus.OK.getReasonPhrase());
        } else {
            response.setMessage(message);
        }
        if(!StringUtils.isEmpty(token)) {
            response.setToken(token);
        }
//        response.setTimestamp(Date.from(Instant.now()));
        response.setData(data);
        return response;
    }

    public static <T> ResponseDTO<T> error(String message, T data) {
        return error(message,"", data);
    }

    public static <T> ResponseDTO<T> error(String message, String token, T data) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        if(StringUtils.isEmpty(message)) {
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        } else {
            response.setMessage(message);
        }
        if(!StringUtils.isEmpty(token)) {
            response.setToken(token);
        }
//        response.setTimestamp(Date.from(Instant.now()));
        response.setData(data);
        return response;
    }
}
