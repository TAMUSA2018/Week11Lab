package com.redpois0n.common.util;

import java.util.Random;

public class Util {
	
	/**
	 * Generates a random string
	 * @param len
	 * @return random string
	 */
	public static String randomString(short len) {
		char[] alph = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
		
		StringBuilder b = new StringBuilder();
		
		for (short i = 0; i < len; i++) {
			b.append(alph[new Random().nextInt(alph.length - 1)]);
		}
		
		return b.toString();
	}

}
