package com.cdf.factory.oauth.client.config;

//public class WebInterceptorAdapter extends HandlerInterceptorAdapter {
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		// String referer = request.getHeader("Referer");
//		// response.setHeader("Access-Control-Allow-Origin", referer);
//
//		// 解决跨域问题
//		// 控制是否开启与Ajax的Cookie提交方式
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		// 允许哪些Origin发起跨域请求. 这里设置为"*"表示允许所有，通常设置为所有并不安全，最好指定一下
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		// 允许请求的方法
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//		// 表明在多少秒内，不需要再发送预检验请求，可以缓存该结果
//		// response.setHeader("Access-Control-Max-Age", "3600");
//		// 表明它允许跨域请求包含content-type头
//		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//
//	}
//}
