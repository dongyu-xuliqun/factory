package com.cdf.factory.consumer.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.cdf.factory.consumer.social.qq.api.QQ;
import com.cdf.factory.consumer.social.qq.api.QQUserInfo;

/**
 * qq返回的信息为spring social提供的适配器 Created on 2018/1/8 0008.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class QQAdapter implements ApiAdapter<QQ> {
	@Override
	public boolean test(QQ api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();

		values.setProviderUserId(userInfo.getOpenId());// openId 唯一标识
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {

	}
}
