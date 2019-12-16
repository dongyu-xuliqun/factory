package com.cdf.factory.user.service;

import java.util.List;

import com.cdf.factory.user.entity.UserDO;

public interface UserService {

	List<UserDO> listUsers(int status);

	UserDO getUser(long id, int status);

	boolean saveUser(UserDO user);

	boolean modifyUser(UserDO user, int status);

	boolean removeUser(long id, int status);

}
