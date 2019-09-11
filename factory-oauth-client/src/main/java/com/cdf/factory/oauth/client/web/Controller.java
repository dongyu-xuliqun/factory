/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.cdf.factory.oauth.client.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月4日 下午5:44:12
 * @version V1.0.1
 */
@RestController
public class Controller {

	@RequestMapping("/login/account")
	public Map<String, Object> account(String username) {
		Map<String, Object> user = new HashMap<>();
		user.put("token", "123456789");
		user.put("name", "xuliqun");
		user.put("email", "854128573@qq.com");
		user.put("id", "111");
		user.put("time", new Date());
		// System.out.println("come on " + msg);
		Map<String, Object> ret = new HashMap<>();
		ret.put("user", user);
		ret.put("msg", "ok");
		ret.put("code", "200");
		return ret;
	}

	@RequestMapping({ "/user", "/me" })
	public Map<String, Object> user(OAuth2Authentication auth) {
		// auth.getUserAuthentication();
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("name", auth.getName());
		// map.put("id", ((Map) auth.getUserAuthentication().getPrincipal()).get("id"));
		map.put("token", ((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue());
		return map;
	}

	@RequestMapping("/auth")
	public void auth(@RequestParam(name = "code") String code,
			@RequestParam(name = "state", required = false) String state,
			@RequestParam(name = "callbackUrl") String callbackUrl, HttpServletResponse response) throws IOException {

		response.sendRedirect(String.format(
				"http://127.0.0.1:8102/oauth/token?grant_type=authorization_code&code=%s&client_id=barClientIdPassword&client_secret=secret&scope=test&redirect_uri=%s",
				code, "http://localhost:4200/#/callback/customer/"));

//				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
//				this.wxAppId, URLEncoder.encode(this.wxRedirectUri), "snsapi_userinfo", returnUrl);
	}

	@RequestMapping({ "user2/login" })
	public Map<String, Object> login(OAuth2Authentication auth) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("name", auth.getName());
		// map.put("id", ((Map) auth.getUserAuthentication().getPrincipal()).get("id"));
		map.put("token", ((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue());
		return map;
	}

//	@RequestMapping({ "/oauth2/authorize" })
//	public void oauth(@RequestParam String redirect_uri, HttpServletRequest request, HttpServletResponse response,
//			OAuth2Authentication auth) throws IOException {
////		Map<String, Object> map = new LinkedHashMap<>();
////		map.put("name", auth.getName());
////		map.put("id", ((Map) auth.getUserAuthentication().getDetails()).get("id"));
//		String redictUrl = request.getParameter("redirect_uri");
//		response.sendRedirect(redictUrl + "?token=");
//	}
}
