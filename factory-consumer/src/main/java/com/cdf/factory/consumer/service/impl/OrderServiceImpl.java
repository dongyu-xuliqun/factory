package com.cdf.factory.consumer.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdf.factory.consumer.entity.OrderDO;
import com.cdf.factory.consumer.repository.OrderRepository;
import com.cdf.factory.consumer.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Page<OrderDO> findAll(Pageable pageable) {		
	    return orderRepository.findAllByStatus(1, pageable);
	}
	
	@Override
	public OrderDO findById(long id) {
		Optional<OrderDO> order = orderRepository.findByIdAndStatus(id, 1);		
		if(order.isPresent()) {
			return order.get();
		}		
		return new OrderDO();
	}
	
	@Override
	@Transactional
	public boolean deleteById(long id) {		
		return orderRepository.updateStatusById(0,LocalDateTime.now(), id) > 0;		
	}

}
