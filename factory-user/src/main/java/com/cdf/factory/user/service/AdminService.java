package com.cdf.factory.user.service;

import java.util.List;

import com.cdf.factory.user.entity.AdminDO;

public interface AdminService {

	List<AdminDO> listAdmins(int status);

	AdminDO getAdmin(long id, int status);

	boolean saveAdmin(AdminDO admin);

	boolean modifyAdmin(AdminDO admin, int status);

	boolean removeAdmin(long id, int status);

}
