/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.cdf.factory.web.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cdf.factory.web.domain.user.MyUserDetails;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月9日 上午10:30:38
 * @version V1.0.1
 */
@Component
public class MyCustomUserService implements UserDetailsService {

	// @Autowired
	// private SysUserMapper sysUserMapper;

	/**
	 * 登陆验证时，通过username获取用户的所有权限信息
	 * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUserDetails myUserDetails = new MyUserDetails();
		myUserDetails.setUsername(username);
		String passowrd = new BCryptPasswordEncoder().encode("123456");
		System.out.println(passowrd);
		myUserDetails.setPassword(passowrd);
		// SysUser sysUser = sysUserMapper.selectByKeyword(username);
		// myUserDetails.setPassword(sysUser.getPassword());
		return myUserDetails;
	}

	public static void main(String[] args) {
		String passwordString = new BCryptPasswordEncoder().encode("123456");
		System.out.println(passwordString);
	}
}