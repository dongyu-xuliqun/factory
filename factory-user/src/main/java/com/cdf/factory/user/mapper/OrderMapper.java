package com.cdf.factory.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cdf.factory.user.entity.OrderDO;

@Mapper
public interface OrderMapper{

	@Select("select * from user_order where status=#{status}")
	List<OrderDO> selectOrderList(int status);
	
	@Select("select * from user_order where id=#{id} and status=#{status}")
	OrderDO selectOrder(long id, int status);
	
	@Insert("insert into user_order (order_no, merchant_id) value(#{orderNo}, #{merchantId})")
	int insertOrder(OrderDO order);

	@Update("update user_order set picture_url=#{pictureUrl} and status=#{status}")
	int updateOrder(OrderDO order, int status);
	
	@Delete("update user_order set status=0 where id=#{id} and status=#{status}")
	int deleteOrder(long id, int status);
}