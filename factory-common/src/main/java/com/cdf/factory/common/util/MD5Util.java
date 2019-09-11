package com.cdf.factory.common.util;

import java.security.MessageDigest;

public class MD5Util {

	public static String getMD5(String str) {
		return getMD5(str, "utf-8", 1);
	}

	/**
	 * MD5加密方法
	 * 
	 * @param str
	 * @param encoding
	 * @param no_Lower_Upper
	 * @return
	 */
	public static String getMD5(String str, String encoding, int no_Lower_Upper) {
		if (null == encoding)
			encoding = "utf-8";
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(str.getBytes(encoding));
			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (no_Lower_Upper == 0) {
			return sb.toString();
		}
		if (no_Lower_Upper == 1) {
			return sb.toString().toLowerCase();
		}
		if (no_Lower_Upper == 2) {
			return sb.toString().toUpperCase();
		}
		return null;
	}

}