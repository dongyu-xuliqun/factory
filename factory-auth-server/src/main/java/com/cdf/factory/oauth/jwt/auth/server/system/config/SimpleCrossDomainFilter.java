/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司 All rights reserved.
 * 
 * 项目名称：互金网络平台 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 * 任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。
 ***************************************************************************/
package com.cdf.factory.oauth.jwt.auth.server.system.config;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年6月12日 下午10:56:58
 * @version V1.0.1
 */
//public class SimpleCrossDomainFilter extends GenericFilterBean {
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//		String origin = request.getHeader("Origin");
//		if (origin == null) {
//			origin = request.getHeader("Referer");
//		}
//		response.setHeader("Access-Control-Allow-Origin", origin);
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		if (RequestMethod.OPTIONS.toString().equals(request.getMethod())) {
//			String allowMethod = request.getHeader("Access-Control-Request-Method");
//			String allowHeaders = request.getHeader("Access-Control-Request-Headers");
//			// 浏览器缓存预检请求结果时间,单位:秒
//			response.setHeader("Access-Control-Max-Age", "86400");
//			// 允许浏览器在预检请求成功之后发送的实际请求方法名
//			response.setHeader("Access-Control-Allow-Methods", allowMethod);
//			// 允许浏览器发送的请求消息头
//			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
//		}
//		filterChain.doFilter(servletRequest, servletResponse);
//	}
//}
