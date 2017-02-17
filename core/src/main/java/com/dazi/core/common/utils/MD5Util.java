package com.dazi.core.common.utils;

import org.springframework.util.Base64Utils;

import java.security.MessageDigest;

public class MD5Util {
	/**
	 * MD5 摘要计算(byte[]).
	 * 
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[] 16 bit digest
	 */
	public static byte[] md5(byte[] src) throws Exception {
		MessageDigest alg = MessageDigest.getInstance("MD5");
		return alg.digest(src);
	}

	/**
	 * MD5 摘要计算(String).
	 * 
	 * @param src
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public static String md5(String src) {
		String str = null;
		try {
			str = CryptUtil.byte2hex(md5(src.getBytes("UTF-8")));
		} catch (Exception e) {
			str = null;
			e.printStackTrace();
		}
		return str;
	}

	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			MessageDigest md = MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16
											// 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节转换成 16
											// 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String digestMD5(String context, String key) {
		try {
			byte[] data = (context + key).getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data);
			return encodeBASE64(md5.digest()).trim();//base64满76位后会增加\r\n, 导致http.header请求报错
		} catch (Exception e) {
			//logger.error("md5 digest fail ", e);
		}
		return null;
	}

	private static String encodeBASE64(byte[] bytes) throws Exception {
		return Base64Utils.encodeToString(bytes);
	}

}
