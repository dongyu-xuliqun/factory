package com.cdf.factory.oauth.jwt.auth.server.system.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.cdf.factory.oauth.jwt.auth.server.entity.SysUser;
//import com.cdf.factory.oauth.jwt.auth.server.entity.User;

/**
 *
 * token生成携带的信息
 *
 */
public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		final Map<String, Object> additionalInfo = new HashMap<>();
		SysUser user = (SysUser) authentication.getUserAuthentication().getPrincipal();
		additionalInfo.put("name", user.getRealName());
		additionalInfo.put("username", user.getUsername());
		additionalInfo.put("authorities", user.getAuthorities());
		additionalInfo.put("id", user.getId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
