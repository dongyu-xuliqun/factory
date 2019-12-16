package com.cdf.factory.user.service;


import java.util.List;

import com.cdf.factory.user.entity.OrderDO;

public interface OrderService {

	List<OrderDO> listOrders(int status);

	OrderDO getOrder(long id, int status);
	
	boolean saveOrder(OrderDO order);	

	boolean modifyOrder(OrderDO orderDO, int status);

	boolean removeOrder(long id, int status);	
}
