/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.cdf.factory.oauth.jwt.auth.server.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.MacSpi;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月12日 下午4:58:51
 * @version V1.0.1
 */
@RestController
public class Controller {	

	
	/**
	 * 定义用户信息
	 *
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "/user")
	public Authentication getUser(OAuth2Authentication authentication) {
		Authentication oauth = authentication.getUserAuthentication();
		// String user1 = (String) oauth.getPrincipal();
		// System.out.println(user1);
		return authentication;
		// return new ResponseEntity<OAuth2Authentication>(authentication,
		// HttpStatus.OK);
//
//		User user = (User) oauth.getPrincipal();
//		return new ResponseEntity<>(new HashMap<String, Object>() {
//			{
//				put("name", user.getName());
//				put("username", user.getUsername());
//				put("id", user.getId());
//				put("createAt", user.getCreateAt());
//				put("auth", user.getAuthorities());
//
//			}
//		}, HttpStatus.OK);
	}
	
	@RequestMapping("/auth")
	public void auth(@RequestParam(name = "code") String code,
			@RequestParam(name = "state", required = false) String state, HttpServletResponse httpResponse) throws IOException {		
		String url = String.format(
				"http://127.0.0.1:8102/oauth/token?grant_type=authorization_code&code=%s&client_id=barClientIdPassword&client_secret=secret&scope=test&redirect_uri=%s",
				code, "http://127.0.0.1:8102/auth/");		

        HttpResponse response = new DefaultHttpClient().execute(new HttpGet(url));
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            Map<String, String> map = JSONObject.parseObject(result, new TypeReference<Map<String, String>>() {
			});             
            String token = map.get("access_token");
            String refreshToken = map.get("refresh_token");
            String name = map.get("name");
            String username = map.get("username");
            String id = map.get("id");
            String email = "854128573@qq.com";
            String expireTime = map.get("expires_in");
            String jti = map.get("jti");
            // 根据client_id去数据库中找redirect_url
            //TODO
            //拼接参数       
            // 这里可以只获取token，然后再获取用户信息
            String url2 = "http://localhost:4200/#/callback/customer?token="+ token +"&name="+name+"&username="+username+"&id="+id+"&email="+email;// +"&refreshToken=" + refreshToken;            
            httpResponse.sendRedirect(url2);
            return;
        }		
		return;
	}
}
