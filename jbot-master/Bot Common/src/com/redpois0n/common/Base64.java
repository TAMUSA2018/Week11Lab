package com.redpois0n.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	
	/**
	 * Encodes a string to base64
	 * @param s
	 * @return the base64 encoded string
	 * @throws Exception
	 */
	public static String encode(String s) throws Exception {
		return new BASE64Encoder().encodeBuffer(s.getBytes("UTF-8"));
	}
	
	/**
	 * Decodes a string from base64
	 * @param s
	 * @return the decoded string from base64
	 * @throws Exception
	 */
	public static String decode(String s) throws Exception {
		return new String(new BASE64Decoder().decodeBuffer(s));
	}

}
