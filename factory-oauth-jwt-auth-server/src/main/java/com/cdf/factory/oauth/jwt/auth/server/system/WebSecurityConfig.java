package com.cdf.factory.oauth.jwt.auth.server.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import com.cdf.factory.oauth.jwt.auth.server.service.CustomUserService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		// 存储内存
//		auth.inMemoryAuthentication().withUser("demo").password("123").roles("USER")
//				.and().withUser("buxiaoxia").password("123").roles("ADMIN");
		// 使用自定义认证用户信息
		auth.userDetailsService(customUserService())
				// 使用MD5加密校验
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

//	@Override
//	protected void configure(final HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin()
//				.permitAll();
//	}

	// 注入自定义的用户获取
	@Bean
	UserDetailsService customUserService() {
		return new CustomUserService();
	}
}
