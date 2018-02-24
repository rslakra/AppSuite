/******************************************************************************
 * Copyright (C) Devamatre Inc 2008. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
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
