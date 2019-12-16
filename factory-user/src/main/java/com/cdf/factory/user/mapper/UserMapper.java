package com.cdf.factory.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.cdf.factory.user.entity.UserDO;

@Mapper
public interface UserMapper{	
	List<UserDO> selectList(int status);	
	UserDO selectUser(long id, int status);	
	int insertUser(UserDO user);
	int updateUser(UserDO user, int status);
	int deleteUser(long id, int status);
}