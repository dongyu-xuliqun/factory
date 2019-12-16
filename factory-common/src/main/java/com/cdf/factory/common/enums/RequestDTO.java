package com.cdf.factory.common.enums;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class RequestDTO<T> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String merchant = "rockyfintech";
    private String channel = "";
    private String token = UUID.randomUUID().toString();
    private Date timestamp = Date.from(Instant.now());
    private Long partnerId = 0L;
    @Valid
    private T param;
}
