package com.cdf.factory.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdf.factory.user.entity.RoleDO;
import com.cdf.factory.user.mapper.RoleMapper;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<RoleDO> listRoles(int status) {
		return this.roleMapper.selectRoleList(status);
	}

	@Override
	public RoleDO getRole(long id, int status) {
		return this.roleMapper.selectRole(id, status);
	}

	@Override
	public boolean saveRole(RoleDO role) {
		return this.roleMapper.insertRole(role) > 0;
	}

	@Override
	public boolean modifyRole(RoleDO role, int status) {
		return this.roleMapper.updateRole(role, status) > 0;
	}

	@Override
	public boolean removeRole(long id, int status) {
		return this.roleMapper.deleteRole(id, status) > 0;
	}
}