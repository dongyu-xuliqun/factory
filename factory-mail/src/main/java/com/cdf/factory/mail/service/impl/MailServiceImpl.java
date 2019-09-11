/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.cdf.factory.mail.service.impl;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cdf.factory.mail.service.MailService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月18日 下午9:23:35
 * @version V1.0.1
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 文本 Description
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @see com.cdf.factory.mail.service.impl.mail.service.MailService#sendSimpleMail(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendSimpleMail(String from, String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			log.info("simple mail had send。");
		} catch (Exception e) {
			log.error("send mail error", e);
		}
	}

	/**
	 * 富文本
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @param subject
	 * 
	 * @param content
	 * 
	 */
	@Override
	public void sendTemplateMail(String from, String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(message);
			log.info("send template success");
		} catch (Exception e) {
			log.error("send template eror", e);
		}
	}

	/**
	 * 
	 * 附件
	 *
	 * 
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @param subject
	 * 
	 * @param content
	 * 
	 * @param filePath
	 * 
	 */
	@Override
	public void sendAttachmentsMail(String from, String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			mailSender.send(message);
			log.info("send mail with attach success。");
		} catch (Exception e) {
			log.error("send mail with attach success", e);
		}
	}

	/**
	 * 
	 * 发送内嵌图片
	 *
	 * 
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @param subject
	 * 
	 * @param content
	 * 
	 * @param imgPath
	 * 
	 * @param imgId
	 * 
	 */
	@Override
	public void sendInlineResourceMail(String from, String to, String subject, String content, String imgPath,
			String imgId) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			FileSystemResource res = new FileSystemResource(new File(imgPath));
			helper.addInline(imgId, res);
			mailSender.send(message);
			log.info("send inner resources success。");
		} catch (Exception e) {
			log.error("send inner resources fail", e);
		}
	}
}