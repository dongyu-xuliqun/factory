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
import com.cdf.factory.user.entity.AdminDO;
import com.cdf.factory.user.entity.PageDTO;
import com.cdf.factory.user.service.AdminService;
import com.cdf.factory.user.utils.RestResponseUtil;
import com.github.pagehelper.PageHelper;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/admins")
    public ResponseDTO<PageDTO<AdminDO>> list(@RequestParam(value=Constants.Page.PAGE_NUMBER, defaultValue=Constants.Page.PAGE_NUMBER_DEFAULT) int pageNumber, @RequestParam(value=Constants.Page.PAGE_SIZE, defaultValue = Constants.Page.PAGE_SIZE_DEFAULT) int pageSize) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, new PageDTO<>(PageHelper.startPage(pageNumber, pageSize)
                .doSelectPageInfo(() -> this.adminService.listAdmins(StatusEnum.ENABLE.value()))));
    }
	
	@GetMapping("/admins/{id:\\d+}")
    public ResponseDTO<AdminDO> get(@PathVariable("id") long id) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.adminService.getAdmin(id, StatusEnum.ENABLE.value()));
    }
	
	@PostMapping("/admins")
    public ResponseDTO<Boolean> save(@RequestBody RequestDTO<AdminDO> request) {		
        return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.adminService.saveAdmin(request.getParam()));
    }
	
	@PutMapping("/admins/{id:\\d+}")
    public ResponseDTO<Boolean> moidify(@PathVariable("id") long id, @RequestBody RequestDTO<AdminDO> request) {		
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.adminService.modifyAdmin(request.getParam(), StatusEnum.ENABLE.value()));
    }
	
	@DeleteMapping("/admins/{id:\\d+}")
    public ResponseDTO<Boolean> remove(@PathVariable("id") long id) {	
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.adminService.removeAdmin(id, StatusEnum.ENABLE.value()));		
    }
}
