package com.cdf.factory.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdf.factory.user.entity.AdminDO;
import com.cdf.factory.user.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminMapper adminMapper;

	@Override
	public List<AdminDO> listAdmins(int value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminDO getAdmin(long id, int value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveAdmin(AdminDO admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyAdmin(AdminDO param, int status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAdmin(long id, int status) {
		// TODO Auto-generated method stub
		return false;
	}

}
