package com.lms.api.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

/**
 * 加密处理工具类
 * 
 * @author Y.H
 * @data 2017-05-18
 */
public class EncryptUtility {

	// 初始化向量
	public static byte[] iv = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	        0x00};

	// 加密类
	private static Rijndael rijndael;
	
	/**
	 * MD5和Base64加密
	 * 
	 * @param str 进行加密的字符串
	 * @return 加密后字符串
	 */
	public static String encode(final String str) throws Exception {
		// MD5加密
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		// Base64加密
		return encodeBase64(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
	}

	/**
	 * Base64解密
	 * 
	 * @param baseStr 加密字符串
	 * @return 解密后字符串
	 * @throws UnsupportedEncodingException UnsupportedEncoding异常
	 */
	public static byte[] decodeBase64(final String baseStr) throws UnsupportedEncodingException {
		return decodeBase64(baseStr.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Base64解密
	 * 
	 * @param bytes 进行解密的字符数组
	 * @return 解密后字符串
	 */
	public static byte[] decodeBase64(final byte[] bytes) {
		return Base64.decodeBase64(bytes);
	}

	/**
	 * Base64加密
	 * 
	 * @param bytes 进行加密的字符数组
	 * @return 加密后字符串
	 */
	public static String encodeBase64(final byte[] bytes) {
		return new String(Base64.encodeBase64(bytes));
	}

	/**
	 * MD5加密
	 * 
	 * @param str 进行加密的字符串
	 * @return 加密后字符串
	 */
	public static String encodeMD5(final String str) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes(StandardCharsets.UTF_8));
		return new BigInteger(1, md.digest()).toString(16);
	}

	/**
	 * 将文件进行Base64加密
	 *
	 * @param file 文件对象
	 * @return Base64加密字符串
	 * @throws Exception 异常
	 */
	public static String encodeBase64FileToStr(File file) throws Exception {
		return encodeBase64(FileUtils.readFileToByteArray(file));
	}

	/**
	 * 将Base64加密的文件保存成文件
	 *
	 * @param image 文件Base64字符串
	 * @param file 文件对象
	 * @throws Exception 异常
	 */
	public static void decodeBase64StrToFile(String image, File file) throws Exception {
		byte[] bytes = decodeBase64(image.getBytes(StandardCharsets.UTF_8));
		if (file.exists()) {
			file.delete();
		}
		FileUtils.writeByteArrayToFile(file, bytes);
	}

	/**
	 * AES256加密
	 * 
	 * @param content 需要加密的内容
	 * @param secretKey 密钥
	 * @return 加密后的内容
	 * @throws Exception 异常
	 */
	public static byte[] encodeAES256(byte[] bytes, String secretKey) throws Exception {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		SecretKeySpec newKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
		return cipher.doFinal(bytes);
	}

	/**
	 * AES256加密
	 * 
	 * @param content 需要加密的内容
	 * @param secretKey 密钥
	 * @return 加密后的内容
	 * @throws Exception 异常
	 */
	public static String encodeAES256(String content, String secretKey) throws Exception {
		return encodeBase64(encodeAES256(content.getBytes(StandardCharsets.UTF_8), secretKey));
	}

	/**
	 * AES256解密
	 * 
	 * @param content 需要解密的内容
	 * @param secretKey 密钥
	 * @return 解密后的内容
	 * @throws Exception 异常
	 */
	public static String decodeAES256(String content, String secretKey) throws Exception {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		SecretKeySpec newKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
		return new String(cipher.doFinal(decodeBase64(content)), StandardCharsets.UTF_8);
	}

	/**
	 * AES256加密
	 * 
	 * @param bytes 需要加密的内容
	 * @param secretKey 密钥
	 * @throws Exception 异常
	 */
	public static byte[] encodept(byte[] bytes, String secretKey){
		initialize(secretKey);
		byte[] bytesCopy = new byte[((bytes.length + 15) / 16) * 16 ];
		System.arraycopy(bytes, 0, bytesCopy, 0, bytes.length);
		byte[] pt = new byte[((bytesCopy.length + 15) / 16) * 16 ];
		
		byte[] block = new byte[16];
		for (int ii = 0; ii < pt.length; ii += 16) {
			byte[] ct = new byte[16];
			System.arraycopy(bytesCopy, ii, ct, 0, 16);
			rijndael.encrypt(ct, block);
			System.arraycopy(block, 0, pt, ii, 16);
		}
		return pt;
	}

	/**
	 * AES256解密
	 * 
	 * @param bytes 需要解密的内容
	 * @param secretKey 密钥
	 * @throws Exception 异常
	 */
	public static byte[] decrypt(byte[] bytes, String secretKey) throws Exception{
		initialize(secretKey);
		byte[] pt = new byte[bytes.length];
		byte[] block = new byte[16];
		for (int ii = 0; ii < bytes.length; ii += 16) {
			byte[] ct = new byte[16];
			System.arraycopy(bytes, ii, ct, 0, 16);
			rijndael.decrypt(ct, block);
			System.arraycopy(block, 0, pt, ii, 16);
		}
		return pt;
	}

	/**
	 * AES256初始化
	 * 
	 */
	public static void initialize(String secretKey) {
		try {
			rijndael = new Rijndael();
			secretKey += "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu";
			rijndael.makeKey(secretKey.getBytes(), 256);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
}

