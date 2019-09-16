package com.cdf.factory.service.login.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cdf.factory.common.util.MD5Util;
import com.cdf.factory.dao.mysql.UserRepository;
import com.cdf.factory.entity.User;
import com.cdf.factory.service.login.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Map<String, Object> login(String nickName, String password) {
		Map<String, Object> ret = new HashMap<>();
		Map<String, Object> userMap = new HashMap<>();
		// 解密nickName,password
		
		User condition = new User();
		condition.setNickName(nickName);
		Example<User> example = Example.of(condition);
		Optional<User> optionUser = userRepository.findOne(example);
		if (optionUser.isPresent()) {
			User user = optionUser.get();
			// String token= MD5Util.getMD5(user.getId() + System.currentTimeMillis());
			userMap.put("token", MD5Util.getMD5(user.getId() + System.currentTimeMillis()));
			userMap.put("name", user.getNickName());
			userMap.put("avatar", user.getPhoto());// "./assets/tmp/img/avatar.jpg"
			userMap.put("email", user.getEmail());// "854128573@qq.com"
			userMap.put("id", user.getId());
			userMap.put("time", new Date());
			ret.put("user", userMap);
			ret.put("msg", "ok");
			ret.put("code", "200");
			return ret;
		} 
		ret.put("msg", "用户不存在");
		return ret;
	}
}
