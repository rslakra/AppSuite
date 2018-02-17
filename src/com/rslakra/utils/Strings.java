package com.rslakra.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Rohtash Singh Lakra
 * @date 01/30/2018 01:41:46 PM
 */
public class Strings {
	
	/**
	 * Returns true if the string contains only digits. The $ avoids a partial
	 * match, i.e. 1b.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isDigits(String string) {
		return (string != null && string.length() > 0 && string.matches("^[0-9]*$"));
	}
	
	/**
	 * 
	 * @param string
	 * @param useLamda
	 * @return
	 */
	public static boolean isDigits(String string, boolean useLamda) {
		if(useLamda) {
			return (string != null && string.chars().allMatch(x -> Character.isDigit(x)));
		} else {
			return isDigits(string);
		}
	}
	
	/**
	 * 
	 * @param data
	 */
	public static void logTokens(String data) {
		System.out.println("data:" + data);
		if(data != null) {
			try {
				String decoded = URLDecoder.decode(data, "utf-8");
				System.out.println("decoded:" + decoded);
				String[] tokens = decoded.split("&");
				for(int i = 0; i < tokens.length; i++) {
					String[] token = tokens[i].split("=");
					if(token.length > 1) {
						System.out.println(token[0] + "=" + token[1]);
					} else {
						System.out.println(token[0] + "=");
					}
				}
			} catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String digits = "2";
		System.out.println(digits + " is digits=" + isDigits(digits));
		System.out.println(digits + " is digits (lamda)=" + isDigits(digits, true));
		digits = "1234a";
		System.out.println(digits + " is digits=" + isDigits(digits));
		System.out.println(digits + " is digits (lamda)=" + isDigits(digits, true));
		digits = "1234$";
		System.out.println(digits + " is digits=" + isDigits(digits));
		System.out.println(digits + " is digits (lamda)=" + isDigits(digits, true));
		digits = "1234!";
		System.out.println(digits + " is digits=" + isDigits(digits));
		System.out.println(digits + " is digits (lamda)=" + isDigits(digits, true));
		digits = "0123.4";
		System.out.println(digits + " is digits=" + isDigits(digits));
		System.out.println(digits + " is digits (lamda)=" + isDigits(digits, true));
	}
	
}
