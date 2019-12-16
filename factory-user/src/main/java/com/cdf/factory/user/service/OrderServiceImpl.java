package com.cdf.factory.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdf.factory.user.entity.OrderDO;
import com.cdf.factory.user.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper orderMapper;
	
	@Override
	public List<OrderDO> listOrders(int status) {
		return this.orderMapper.selectOrderList(status);
	}

	@Override
	public OrderDO getOrder(long id, int status) {
		return this.orderMapper.selectOrder(id, status);
	}
	
	@Override
	public boolean saveOrder(OrderDO order) {
		return this.orderMapper.insertOrder(order) > 0;
	}
	
	@Override
	public boolean modifyOrder(OrderDO orderDO, int status) {
		return this.orderMapper.updateOrder(orderDO, status) > 0;
	}

	@Override
	public boolean removeOrder(long id, int status) {		
		return this.orderMapper.deleteOrder(id, status) > 0;
	}		
}
