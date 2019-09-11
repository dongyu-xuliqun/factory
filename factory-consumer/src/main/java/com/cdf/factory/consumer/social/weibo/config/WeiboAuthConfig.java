package com.cdf.factory.consumer.social.weibo.config;

/**
 * Created on 2018/1/12.
 *
 * @author zlf
 * @since 1.0
 */
//@Configuration
//public class WeiboAuthConfig extends SocialAutoConfigurerAdapter {
//
//	@Override
//	protected ConnectionFactory<?> createConnectionFactory() {
//		return new WeiboConnectionFactory(SecurityConstants.DEFAULT_SOCIAL_WEIBO_APP_ID,
//				SecurityConstants.DEFAULT_SOCIAL_WEIBO_APP_SECRET);
//	}
//
//	/**
//	 * /connect/qq POST请求,绑定微信返回connect/weixinConnected视图 /connect/qq
//	 * DELETE请求,解绑返回connect/weixinConnect视图
//	 * 
//	 * @return
//	 */
//	@Bean({ "connect/weiboConnect", "connect/weiboConnected" })
//	@ConditionalOnMissingBean(name = "weiboConnectedView")
//	public View qqConnectedView() {
//		return new SocialConnectView();
//	}
//}
