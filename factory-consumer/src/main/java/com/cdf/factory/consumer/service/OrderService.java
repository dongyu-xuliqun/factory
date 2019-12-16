package com.cdf.factory.consumer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cdf.factory.consumer.entity.OrderDO;

public interface OrderService {

	Page<OrderDO> findAll(Pageable pageable);

	OrderDO findById(long id);

	boolean deleteById(long id);

}
