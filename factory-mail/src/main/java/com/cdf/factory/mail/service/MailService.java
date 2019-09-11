/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.cdf.factory.mail.service;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月18日 下午9:25:34
 * @version V1.0.1
 */
public interface MailService {

	/**
	 * @Description 文本
	 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	void sendSimpleMail(String from, String to, String subject, String content);

	/**
	 * @Description 富文本
	 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	void sendTemplateMail(String from, String to, String subject, String content);

	/**
	 * @Description
	 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 */
	void sendAttachmentsMail(String from, String to, String subject, String content, String filePath);

	/**
	 * @Description
	 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param imgPath
	 * @param imgId
	 */
	void sendInlineResourceMail(String from, String to, String subject, String content, String imgPath, String imgId);

}
