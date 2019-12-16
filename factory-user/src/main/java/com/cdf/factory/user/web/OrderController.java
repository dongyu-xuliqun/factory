package com.cdf.factory.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdf.factory.common.constants.Constants;
import com.cdf.factory.common.enums.RequestDTO;
import com.cdf.factory.common.enums.ResponseDTO;
import com.cdf.factory.common.enums.StatusEnum;
import com.cdf.factory.user.entity.OrderDO;
import com.cdf.factory.user.entity.PageDTO;
import com.cdf.factory.user.service.OrderService;
import com.cdf.factory.user.utils.RestResponseUtil;
import com.github.pagehelper.PageHelper;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
    public ResponseDTO<PageDTO<OrderDO>> list(@RequestParam(value=Constants.Page.PAGE_NUMBER, defaultValue=Constants.Page.PAGE_NUMBER_DEFAULT) int pageNumber, @RequestParam(value=Constants.Page.PAGE_SIZE, defaultValue = Constants.Page.PAGE_SIZE_DEFAULT) int pageSize) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, new PageDTO<>(PageHelper.startPage(pageNumber, pageSize)
                .doSelectPageInfo(() -> this.orderService.listOrders(StatusEnum.ENABLE.value()))));
    }
	
	@GetMapping("/orders/{id:\\d+}")
    public ResponseDTO<OrderDO> get(@PathVariable("id") long id) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.orderService.getOrder(id, StatusEnum.ENABLE.value()));
    }
	
	@PostMapping("/orders")
    public ResponseDTO<Boolean> save(@RequestBody RequestDTO<OrderDO> request) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.orderService.saveOrder(request.getParam()));
    }
	
	@PutMapping("/orders/{id:\\d+}")
    public ResponseDTO<Boolean> moidify(@PathVariable("id") long id, @RequestBody RequestDTO<OrderDO> request) {		
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.orderService.modifyOrder(request.getParam(), StatusEnum.ENABLE.value()));
    }
	
	@DeleteMapping("/orders/{id:\\d+}")
    public ResponseDTO<Boolean> remove(@PathVariable("id") long id) {	
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.orderService.removeOrder(id, StatusEnum.ENABLE.value()));		
    }	
}
