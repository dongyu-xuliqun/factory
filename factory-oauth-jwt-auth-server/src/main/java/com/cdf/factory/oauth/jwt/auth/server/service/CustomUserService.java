package com.cdf.factory.oauth.jwt.auth.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cdf.factory.oauth.jwt.auth.server.entity.SysUser;
//import com.cdf.factory.oauth.jwt.auth.server.entity.User;
import com.cdf.factory.oauth.jwt.auth.server.repository.SysUserRepository;
//import com.cdf.factory.oauth.jwt.auth.server.repository.UserRepository;

/**
 * 自定义用户获取方式
 * <p>
 * Created by xw on 2017/3/17. 2017-03-17 20:13
 */
public class CustomUserService implements UserDetailsService {

//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	SysUserRepository sysUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = sysUserRepository.findOneByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return user;

//		MyUserDetails myUserDetails = new MyUserDetails();
//		myUserDetails.setUsername(username);
//		String passowrd = new BCryptPasswordEncoder().encode("123456");
//		System.out.println(passowrd);
//		myUserDetails.setPassword(passowrd);
//		 SysUser sysUser = sysUserMapper.selectByKeyword(username);
//		 myUserDetails.setPassword(sysUser.getPassword());
//		return myUserDetails;
	}
}
