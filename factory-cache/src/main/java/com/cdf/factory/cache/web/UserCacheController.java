/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.cdf.factory.cache.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月24日 下午9:36:42
 * @version V1.0.1
 */
@RestController
public class UserCacheController {

	// @Autowired
	// private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Object> redisCacheTemplate;

	@GetMapping(value = "/cache/user/cacheUser")
	@ResponseBody
	public Map<String, Object> cacheUser() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "000000");
		result.put("msg", "success");
//		User u = new User();
//		u.setId(1L);
//		u.setAge(23);
//		u.setUserName("huangjinjin");
//		u.setPosition("cto");
//		result.put("body", u);
//		redisCacheTemplate.opsForValue().set(String.valueOf(u.getId()), u);
		return result;
	}

	/**
	 * 
	 * 获取缓存信息
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 */
	@GetMapping(value = "/cache/user/getCacheUser/{id}")
	@ResponseBody
	public Map<String, Object> getCacheUser(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "000000");
		result.put("msg", "success");
//		User u = (User) redisCacheTemplate.opsForValue().get(String.valueOf(1));
//		System.out.println(u.getUserName());
//		result.put("body", u);
		return result;
	}
}
