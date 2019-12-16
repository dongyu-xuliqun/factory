package com.cdf.factory.common.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDTO<T> extends RequestDTO<T> {
    int pageNum = 1;
    int pageSize = 10;
}
