/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.cdf.factory.oauth.jwt.resource.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月11日 下午5:54:06
 * @version V1.0.1
 */
@RestController
public class Controller {

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public ResponseEntity authDemo(OAuth2Authentication auth) {
		// 获取当前用户资源
		Map user = (Map) auth.getPrincipal();
		return new ResponseEntity<>(new HashMap<String, Object>() {
			{
				put("username", user.get("user_name"));
				put("name", user.get("name"));
				put("createAt", user.get("createAt"));
				put("auth", "OK");
			}
		}, HttpStatus.OK);
	}
}
