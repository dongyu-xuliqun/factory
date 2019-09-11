package com.cdf.factory.consumer.social.qq.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.cdf.factory.consumer.social.qq.api.QQ;

/**
 * Created on 2018/1/8 0008.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}
}
