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
import com.cdf.factory.user.entity.UserDO;
import com.cdf.factory.user.service.UserService;
import com.cdf.factory.user.utils.RestResponseUtil;
import com.github.pagehelper.PageHelper;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
    public ResponseDTO<PageDTO<UserDO>> list(@RequestParam(value=Constants.Page.PAGE_NUMBER, defaultValue=Constants.Page.PAGE_NUMBER_DEFAULT) int pageNumber, @RequestParam(value=Constants.Page.PAGE_SIZE, defaultValue = Constants.Page.PAGE_SIZE_DEFAULT) int pageSize) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, new PageDTO<>(PageHelper.startPage(pageNumber, pageSize)
                .doSelectPageInfo(() -> this.userService.listUsers(StatusEnum.ENABLE.value()))));
    }
	
	@GetMapping("/users/{id:\\d+}")
    public ResponseDTO<UserDO> get(@PathVariable("id") long id) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.userService.getUser(id, StatusEnum.ENABLE.value()));
    }
	
	@PostMapping("/users")
    public ResponseDTO<Boolean> save(@RequestBody RequestDTO<UserDO> request) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.userService.saveUser(request.getParam()));
    }
	
	@PutMapping("/users/{id:\\d+}")
    public ResponseDTO<Boolean> moidify(@PathVariable("id") long id, @RequestBody RequestDTO<UserDO> request) {		
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.userService.modifyUser(request.getParam(), StatusEnum.ENABLE.value()));
    }
	
	@DeleteMapping("/users/{id:\\d+}")
    public ResponseDTO<Boolean> remove(@PathVariable("id") long id) {	
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.userService.removeUser(id, StatusEnum.ENABLE.value()));		
    }
	

}
