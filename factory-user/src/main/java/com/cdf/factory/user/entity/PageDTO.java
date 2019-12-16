package com.cdf.factory.user.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.github.pagehelper.PageInfo;

@Getter
@Setter
public class PageDTO<T> implements Serializable {
	private static final long serialVersionUID = 1L;	
    private long total;
    protected List<T> list;

    public PageDTO(PageInfo<T> page) {
        if (page != null) {
        	BeanUtils.copyProperties(page, this);            
        }
    }

    public PageDTO() {
    }
}
