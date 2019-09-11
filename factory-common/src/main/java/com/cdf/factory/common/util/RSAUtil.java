package com.cdf.factory.common.util;


import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class RSAUtil {
	public static final String ENCRYPTION_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	public static final String PROVIDER = "BC";

	public static String PUBLIC_KEY;
	public static String PRIVATE_KEY;



	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	// /**
	// * 生成密钥
	// */
	public static Map<String, Object> initKey() throws Exception {
		/* 初始化密钥生成器 */
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPTION_ALGORITHM);
		keyPairGenerator.initialize(1024);

		/* 生成密钥 */
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put("PublicKey", publicKey);
		keyMap.put("PrivateKey", privateKey);
		return keyMap;
	}

	/**
	 * 取得公钥
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get("PublicKey");
		return Base64.encodeBase64String(key.getEncoded());
	}

	/**
	 * 取得私钥
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get("PrivateKey");
		return Base64.encodeBase64String(key.getEncoded());
	}

	public static PrivateKey getPrivateKey(String base64EncodePrivateKey) throws Exception {
		KeyFactory keyf = KeyFactory.getInstance("RSA", PROVIDER);
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(base64EncodePrivateKey.getBytes()));
		PrivateKey privkey = keyf.generatePrivate(priPKCS8);
		return privkey;
	}

	public static PrivateKey getPrivateKey(String pfxPath, String pfxPassword) {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(pfxPath);
			// If the keystore password is empty(""), then we have to set
			// to null, otherwise it won't work!!!
			char[] nPassword = null;
			if ((pfxPassword == null) || pfxPassword.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = pfxPassword.toCharArray();
			}
			ks.load(fis, nPassword);
			fis.close();
			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements())// we are readin just one certificate.
			{
				keyAlias = (String) enumas.nextElement();
			}
			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			return prikey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 */
	public static byte[] encrypt(byte[] data, String keyString, boolean isPublic) throws Exception {
		Map<String, Object> keyAndFactoryMap = RSAUtil.generateKeyAndFactory(keyString, isPublic);
		KeyFactory keyFactory = RSAUtil.getKeyFactory(keyAndFactoryMap);
		Key key = RSAUtil.getKey(keyAndFactoryMap);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] result = cipher.doFinal(data);
		return result;
	}

	/**
	 * 
	 * @Description 加密
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) throws Exception {
		Map<String, Object> keyAndFactoryMap = RSAUtil.generateKeyAndFactory(PUBLIC_KEY, true);
		KeyFactory keyFactory = RSAUtil.getKeyFactory(keyAndFactoryMap);
		Key key = RSAUtil.getKey(keyAndFactoryMap);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] result = cipher.doFinal(data.getBytes());
		return java.util.Base64.getEncoder().encodeToString(result);
	}

	/**
	 * 解密
	 */
	public static byte[] decrypt(byte[] data, String keyString, boolean isPublic) throws Exception {
		Map<String, Object> keyAndFactoryMap = RSAUtil.generateKeyAndFactory(keyString, isPublic);
		KeyFactory keyFactory = RSAUtil.getKeyFactory(keyAndFactoryMap);
		Key key = RSAUtil.getKey(keyAndFactoryMap);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, key);

		return cipher.doFinal(data);
	}

	/**
	 * 
	 * @Description 解密
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data) throws Exception {
		Map<String, Object> keyAndFactoryMap = RSAUtil.generateKeyAndFactory(PRIVATE_KEY, false);
		KeyFactory keyFactory = RSAUtil.getKeyFactory(keyAndFactoryMap);
		Key key = RSAUtil.getKey(keyAndFactoryMap);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] aa = java.util.Base64.getDecoder().decode(data);
		byte[] result = cipher.doFinal(aa);
		return new String(result, "utf-8");
	}

	/**
	 * 生成钥匙
	 */
	public static Map<String, Object> generateKeyAndFactory(String keyString, boolean isPublic) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(keyString);
		KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPTION_ALGORITHM);
		Key key = null;
		if (isPublic) {
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			key = keyFactory.generatePublic(x509KeySpec);
		} else {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			key = keyFactory.generatePrivate(pkcs8KeySpec);
		}

		Map<String, Object> keyAndFactoryMap = new HashMap<String, Object>(2);
		keyAndFactoryMap.put("key", key);
		keyAndFactoryMap.put("keyFactory", keyFactory);

		return keyAndFactoryMap;
	}

	/**
	 * 从指定对象中获取钥匙
	 */
	public static Key getKey(Map<String, Object> map) {
		if (map.get("key") == null) {
			return null;
		}
		return (Key) map.get("key");
	}

	/**
	 * 从指定对象中获取钥匙工厂
	 */
	public static KeyFactory getKeyFactory(Map<String, Object> map) {
		if (map.get("keyFactory") == null) {
			return null;
		}
		return (KeyFactory) map.get("keyFactory");
	}

	/**
	 * 对信息生成数字签名（用私钥）
	 */
	public static String sign(byte[] data, String keyString) throws Exception {
		Map<String, Object> keyAndFactoryMap = RSAUtil.generateKeyAndFactory(keyString, false);
		Key key = RSAUtil.getKey(keyAndFactoryMap);

		PrivateKey privateKey = (PrivateKey) key;

		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateKey);
		signature.update(data);
		return Base64.encodeBase64String(signature.sign());
	}

	/**
	 * 校验数字签名（用公钥）
	 */
	public static boolean verify(byte[] data, String keyString, String sign) throws Exception {
		Map<String, Object> keyAndFactoryMap = RSAUtil.generateKeyAndFactory(keyString, true);
		Key key = RSAUtil.getKey(keyAndFactoryMap);

		PublicKey publicKey = (PublicKey) key;

		// 取公钥匙对象
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicKey);
		signature.update(data);

		// 验证签名是否正常
		return signature.verify(Base64.decodeBase64(sign));
	}

	/**
	 * 签名
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static String sign(String data, PrivateKey key) {
		try {
			byte[] dataByte = data.getBytes("UTF-8");
			return new String(BASE64.encode(sign(dataByte, key)));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("sign fail!", e);
		}
	}

	/**
	 * 签名
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] sign(byte[] data, PrivateKey key) {
		try {
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initSign(key);
			signature.update(data);
			return signature.sign();
		} catch (Exception e) {
			throw new RuntimeException("sign fail!", e);
		}
	}

	/**
	 * 验证签名
	 *
	 * @param data      数据
	 * @param sign      签名
	 * @param publicKey 公钥
	 * @return
	 */
	public static boolean verifySign(byte[] data, byte[] sign, PublicKey publicKey) {
		try {
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initVerify(publicKey);
			signature.update(data);
			boolean result = signature.verify(sign);
			return result;
		} catch (Exception e) {

			throw new RuntimeException("verifySign fail!", e);
		}
	}

	/**
	 * 验证签名
	 *
	 * @param data     数据
	 * @param sign     签名
	 * @param pubicKey 公钥
	 * @return
	 */
	public static boolean verifySign(String data, String sign, PublicKey pubicKey) {
		try {
			byte[] dataByte = data.getBytes("UTF-8");
			byte[] signByte = BASE64.decode(sign.getBytes("UTF-8"));
			return verifySign(dataByte, signByte, pubicKey);
		} catch (UnsupportedEncodingException e) {

			throw new RuntimeException("verifySign fail! data[" + data + "] sign[" + sign + "]", e);
		}
	}

	/**
	 * 加密
	 *
	 * @param data
	 * @param key
	 * @return
	 */
	public static String encodeToBase64(String data, Key key, String padding) {
		try {
			return new String(BASE64.encodeHeliPay(encode(data, key, padding)));
		} catch (Exception e) {
			throw new RuntimeException("encrypt fail!", e);
		}
	}

	public static byte[] encode(String encodeString, Key key, String padding) throws Exception {
		final Cipher cipher = Cipher.getInstance(padding, PROVIDER);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = encodeString.getBytes("UTF-8");
		byte[] encodedByteArray = new byte[] {};
		for (int i = 0; i < bytes.length; i += 117) {
			byte[] subarray = ArrayUtils.subarray(bytes, i, i + 117);
			byte[] doFinal = cipher.doFinal(subarray);
			encodedByteArray = ArrayUtils.addAll(encodedByteArray, doFinal);
		}
		return encodedByteArray;
	}

	public static PublicKey getPublicKeyByCert(String path) throws Exception {
		CertificateFactory cff = CertificateFactory.getInstance("X.509");
		FileInputStream fis1 = new FileInputStream(path);
		Certificate cf = cff.generateCertificate(fis1);
		PublicKey publicKey = cf.getPublicKey();
		return publicKey;
	}

}
