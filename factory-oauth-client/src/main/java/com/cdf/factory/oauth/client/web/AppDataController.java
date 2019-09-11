/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司 All rights reserved.
 * 
 * 项目名称：互金网络平台 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 * 任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。
 ***************************************************************************/
package com.cdf.factory.oauth.client.web;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年6月12日 下午4:31:12
 * @version V1.0.1
 */
//@RestController
//public class AppDataController {
//	@GetMapping("/assets/tmp/{filePath}")
//	public String getAppData(@PathVariable String filePath) {
//		Resource data = new ClassPathResource("/assets/tmp/".concat(filePath));
//		String jsonFile;
//		try {
//			jsonFile = new String(FileCopyUtils.copyToByteArray(data.getFile()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			jsonFile = StringUtils.EMPTY;
//		}
////		JSONObject json = JSONObject.parseObject(new String(FileCopyUtils.copyToByteArray(data.getFile())));
////		JSONObject json2 = JSONObject.parseObject(FileCopyUtils.copyToString(new FileReader(data.getFile())));
//		return jsonFile;
//	}
//
//	@GetMapping("/assets/tmp/i18n/{filePath}")
//	public String getI18nData(@PathVariable String filePath) {
//		Resource data = new ClassPathResource("/assets/tmp/i18n/".concat(filePath));
//		String jsonFile;
//		try {
//			jsonFile = new String(FileCopyUtils.copyToByteArray(data.getFile()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			jsonFile = StringUtils.EMPTY;
//		}
////		JSONObject json = JSONObject.parseObject(new String(FileCopyUtils.copyToByteArray(data.getFile())));
////		JSONObject json2 = JSONObject.parseObject(FileCopyUtils.copyToString(new FileReader(data.getFile())));
//		return jsonFile;
//	}
//}