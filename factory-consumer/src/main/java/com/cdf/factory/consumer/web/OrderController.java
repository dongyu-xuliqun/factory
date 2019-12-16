package com.cdf.factory.consumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdf.factory.common.enums.ResponseDTO;
import com.cdf.factory.consumer.entity.OrderDO;
import com.cdf.factory.consumer.service.OrderService;

@RestController
public class OrderController {
	
//	@Autowired
//	OrderService orderService;
//	
//	@GetMapping("/order/list")
//    public ResponseDTO<PageDTO<OrderDO>> getDictList(@RequestParam("page") int page, @RequestParam("size") int size) {
//		
//	    Sort sort = new Sort(Direction.DESC, "id");
//	    Pageable pageable = PageRequest.of(page-1, size, sort);        
//		Page<OrderDO> findAll = orderService.findAll(pageable);
//		findAll.getContent();
//		findAll.getTotalElements();
//		findAll.getPageable();		
//		
//		ResponseDTO<PageDTO<OrderDO>> response = new ResponseDTO<>();
//        response.setCode(HttpStatus.OK.value());
//        response.setStatus(HttpStatus.OK.value());
//        response.setData(new PageDTO(findAll));
//        return response;
//    }
//	
//	@GetMapping("/order/{id:\\d+}")
//    public ResponseDTO<OrderDO> getOrder(@PathVariable("id") long id) {		
//	  
//		OrderDO order = orderService.findById(id);	
//		
//		ResponseDTO<OrderDO> response = new ResponseDTO<>();
//        response.setCode(HttpStatus.OK.value());
//        response.setStatus(HttpStatus.OK.value());
//        response.setData(order);
//        return response;
//    }
//	
//	@PostMapping("/order/remove/{id:\\d+}")
//    public ResponseDTO<Boolean> deleteOrder(@PathVariable("id") long id) {	
//	  
//		boolean bool = orderService.deleteById(id);			
//		ResponseDTO<Boolean> response = new ResponseDTO<>();
//        response.setCode(HttpStatus.OK.value());
//        response.setStatus(HttpStatus.OK.value());
//        response.setData(bool);
//        return response;
//    }
	
//	@PostMapping("/order/{id:\\d+}")
//    public ResponseDTO<OrderDO> save(@PathVariable("id") long id, @RequestBody OrderDO orderDO) {		
//	  
//		OrderDO order = orderService.save(id,orderDO);	
//		
//		ResponseDTO<OrderDO> response = new ResponseDTO<>();
//        response.setCode(HttpStatus.OK.value());
//        response.setStatus(HttpStatus.OK.value());
//        response.setData(order);
//        return response;
//    }
}
