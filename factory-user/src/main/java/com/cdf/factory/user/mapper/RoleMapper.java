package com.cdf.factory.user.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdf.factory.user.entity.RoleDO;

@Mapper
public interface RoleMapper {

	List<RoleDO> selectRoleList(int status);

	RoleDO selectRole(long id, int status);

	int insertRole(RoleDO role);

	int updateRole(RoleDO role, int status);

	int deleteRole(long id, int status);

}