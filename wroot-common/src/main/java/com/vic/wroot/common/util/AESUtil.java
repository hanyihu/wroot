package com.vic.wroot.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public final class AESUtil {
	private static String IV = "6747008732479143";

	/**
	 * 加密数据
	 */
	public static String encrypt(String paramStr, String key) throws Exception {
		String resultObj = null;
		byte[] byteContent = paramStr.getBytes("UTF-8");
		// 注意，为了能与 iOS 统一
		// 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
		byte[] enCodeFormat = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
		byte[] initParam = IV.getBytes();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		// 指定加密的算法、工作模式和填充方式
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encryptedBytes = cipher.doFinal(byteContent);
		// 同样对加密后数据进行 base64 编码
//		resultObj = Base64.encode(encryptedBytes);
		resultObj = Base64.encodeBase64String(encryptedBytes);
		return resultObj;
	}

	/**
	 * 解密密数据
	 */
	public static String decrypt(String paramStr, String key) throws Exception {
		String resultObj = null;
		byte[] encryptedBytes = Base64.decodeBase64(paramStr);
		byte[] enCodeFormat = key.getBytes();
		SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
		byte[] initParam = IV.getBytes();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		byte[] result = cipher.doFinal(encryptedBytes);
		resultObj = new String(result, "UTF-8");
		return resultObj;
	}

}
