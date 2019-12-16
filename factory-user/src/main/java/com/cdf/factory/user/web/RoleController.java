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
import com.cdf.factory.user.entity.PageDTO;
import com.cdf.factory.user.service.RoleService;
import com.cdf.factory.user.utils.RestResponseUtil;
import com.github.pagehelper.PageHelper;
import com.cdf.factory.user.entity.RoleDO;

@RestController
public class RoleController{

	@Autowired
	RoleService roleService;

	@GetMapping("/roles")
	public ResponseDTO<PageDTO<RoleDO >> list(@RequestParam(value = Constants.Page.PAGE_NUMBER, defaultValue = Constants.Page.PAGE_NUMBER_DEFAULT) int pageNumber, @RequestParam(value = Constants.Page.PAGE_SIZE, defaultValue = Constants.Page.PAGE_SIZE_DEFAULT) int pageSize) {
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, new PageDTO<>(PageHelper.startPage(pageNumber, pageSize)
                .doSelectPageInfo(() -> this.roleService.listRoles(StatusEnum.ENABLE.value()))));
	}

	@GetMapping("/roles/{id:\\d+}")	public ResponseDTO<RoleDO> get(@PathVariable("id") long id) {
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.roleService.getRole(id, StatusEnum.ENABLE.value()));
	}

	@PostMapping("/roles")
	public ResponseDTO<Boolean> save(@RequestBody RequestDTO<RoleDO> request) {
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.roleService.saveRole(request.getParam()));
	}

	@PutMapping("/roles/{id:\\d+}")
	public ResponseDTO<Boolean> modify(@PathVariable("id") long id, @RequestBody RequestDTO<RoleDO> request) {
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.roleService.modifyRole(request.getParam(), StatusEnum.ENABLE.value()));
	}

	@DeleteMapping("/roles/{id:\\d+}")
	public ResponseDTO<Boolean> remove(@PathVariable("id") long id) {
		return RestResponseUtil.ok(Constants.ResponseMessage.REQUEST_SUCCESS, this.roleService.removeRole(id, StatusEnum.ENABLE.value()));
	}
}