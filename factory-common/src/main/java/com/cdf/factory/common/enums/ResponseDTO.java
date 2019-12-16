package com.cdf.factory.common.enums;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ResponseDTO<T> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer status;
    private String message = "";
    private String error = "";
    private String path = "";
    private String token = UUID.randomUUID().toString();
    private Date timestamp;
    private Long partnerId = 0L;
    private String partnerNo = "";
    private T data;
}
