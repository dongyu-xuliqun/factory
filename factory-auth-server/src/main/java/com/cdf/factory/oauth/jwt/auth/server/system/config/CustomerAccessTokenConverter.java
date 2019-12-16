package com.cdf.factory.oauth.jwt.auth.server.system.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import com.cdf.factory.oauth.jwt.auth.server.entity.SysUser;

//import com.cdf.factory.oauth.jwt.auth.server.entity.User;

/**
 * 自定义CustomerAccessTokenConverter 这个类的作用主要用于AccessToken的转换，
 * 默认使用DefaultAccessTokenConverter 这个装换器
 * DefaultAccessTokenConverter有个UserAuthenticationConverter，这个转换器作用是把用户的信息放入token中，
 * 默认只是放入username
 * <p>
 * 自定义了下这个方法，加入了额外的信息
 * <p>
 * Created by xw on 2017/3/20. 2017-03-20 9:54
 */
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {

	public CustomerAccessTokenConverter() {
		super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
	}

	private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

		@Override
		public Map<String, ?> convertUserAuthentication(Authentication authentication) {
			LinkedHashMap<String, Object> response = new LinkedHashMap<>();
			SysUser sysUser = (SysUser) authentication.getPrincipal();
			response.put("username", authentication.getName());
//			response.put("name", ((SysUser) authentication.getPrincipal()).getRealName());
//			response.put("id", ((SysUser) authentication.getPrincipal()).getId());
//			response.put("createAt", ((SysUser) authentication.getPrincipal()).getCreateTime());
//			if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
//				response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
//			}
			return response;
		}
	}

}
