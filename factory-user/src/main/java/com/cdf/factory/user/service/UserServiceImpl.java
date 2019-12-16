package com.cdf.factory.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdf.factory.user.entity.OrderDO;
import com.cdf.factory.user.entity.UserDO;
import com.cdf.factory.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public List<UserDO> listUsers(int status) {
		return this.userMapper.selectList(status);
	}

	@Override
	public UserDO getUser(long id, int status) {
		return this.userMapper.selectUser(id, status);
	}

	@Override
	public boolean saveUser(UserDO user) {
		return this.userMapper.insertUser(user) > 0;
	}

	@Override
	public boolean modifyUser(UserDO order, int status) {
		return this.userMapper.updateUser(order, status) > 0;
	}

	@Override
	public boolean removeUser(long id, int status) {
		return this.userMapper.deleteUser(id, status) > 0;
	}

}
