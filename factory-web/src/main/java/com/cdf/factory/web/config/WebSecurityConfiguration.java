/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.cdf.factory.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月9日 上午10:29:11
 * @version V1.0.1
 */
@SpringBootConfiguration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

	@Autowired
	private MyAuthenticationFailHandler myAuthenticationFailHandler;

	@Bean
	UserDetailsService customUserService() {
		return new MyCustomUserService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 使用自定义UserDetailsService
		auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and().formLogin()
				.loginProcessingUrl("/user/login")
				// 自定义的登录验证成功或失败后的去向
				.successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailHandler)
				// 禁用csrf防御机制(跨域请求伪造)，这么做在测试和开发会比较方便。
				.and().csrf().disable();
	}
}
