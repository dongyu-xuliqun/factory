package com.cdf.factory.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdf.factory.mail.MailSendApplication;
import com.cdf.factory.mail.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailSendApplication.class)
public class MailSendApplicationTest {

	@Autowired
	private MailService mailService;

	@Test
	public void sendSimpleMailTest() {
		mailService.sendSimpleMail("854128573@qq.com", "244754175@qq.com", "sendSimpleMailTest",
				"sendSimpleMailTestfrom 515768476@qq.com");
	}

	@Test
	public void sendTemplateMailTest() {
		String html = "<html><body>" + " <div> " + "   sendTemplateMailTest from 515768476@qq.com </br>"
				+ "   <b>这是模板邮件</b>" + "</div>" + "</body></html>";
		mailService.sendTemplateMail("854128573@qq.com", "xuliqunpersonal@hotmail.com", "sendTemplateMailTest", html);
	}

	@Test
	public void sendAttachmentsMailTest() {
		String filePath = "D:\\git\\factory\\factory-mail\\src\\main\\java\\com\\cdf\\factory\\mail\\MailSendApplication.java";
		mailService.sendAttachmentsMail("854128573@qq.com", "xuliqunpersonal@hotmail.com", "sendAttachmentsMailTest",
				"sendAttachmentsMailTestfrom 515768476@qq.com", filePath);
	}

	@Test
	public void sendInlineResourceMailTest() {
		String imgId = "img1";
		String content = "<html><body>" + "sendInlineResourceMailTest：<img src=\'cid:" + imgId + "\'>"
				+ "</body></html>";
		String imgPath = "C:\\Users\\Administrator\\Desktop\\1.png";
		mailService.sendInlineResourceMail("854128573@qq.com", "xuliqunpersonal@hotmail.com", "sendAttachmentsMailTest",
				content, imgPath, imgId);
	}
}
