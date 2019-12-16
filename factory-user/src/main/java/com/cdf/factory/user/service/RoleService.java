package com.cdf.factory.user.service;

import java.util.List;
import com.cdf.factory.user.entity.RoleDO;

public interface RoleService {

	List<RoleDO> listRoles(int status);

	RoleDO getRole(long id, int status);

	boolean saveRole(RoleDO role);

	boolean modifyRole(RoleDO role, int status);

	boolean removeRole(long id, int status);
}