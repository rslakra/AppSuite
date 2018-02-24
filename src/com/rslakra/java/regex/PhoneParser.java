/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
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
package com.rslakra.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The <code>Utils</code> class contains the common utility methods.
 * 
 * @author Rohtash Singh
 * @created: May 22, 2013
 * @version 1.0.0
 * @since 1.0.0
 */
public final class PhoneParser {

	/** Phone Validation Regular Expression. */
	// private static String PHONE_REGEX = "^\\+?[0-9. ()-]{10,25}$";
	private static String PHONE_REGEX = "^\\+?[0-9a-zA-Z. ()-]{10,20}$";

	/**
	 * Returns the phone number after removing extra character from it.
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public String parsePhoneNumber(String phoneNumber) {
		StringBuilder sPhoneNumber = new StringBuilder("");
		// String str1 = "";
		String str2 = phoneNumber.toLowerCase();

		for (int i = 0; i < str2.length(); i++) {
			switch (str2.charAt(i)) {
			case '!':
			case '"':
			case '#':
			case '$':
			case '%':
			case '&':
			case '\'':
			case '*':
			case '+':
			case '/':
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case ':':
			case ';':
			case '<':
			case '=':
			case '>':
			case '?':
			case '@':
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':
			case '[':
			case '\\':
			case ']':
			case '^':
			case '_':
			case '`':
			case 'q':
			default:
				// str1 = str1.concat(String.valueOf(phoneNumber.charAt(i)));
				sPhoneNumber.append(phoneNumber.charAt(i));
				break;
			case ' ':
			case '(':
			case ')':
			case ',':
			case '-':
			case '.':
				break;
			case 'a':
			case 'b':
			case 'c':
				// str1 = str1.concat("2");
				sPhoneNumber.append("2");
				break;
			case 'd':
			case 'e':
			case 'f':
				// str1 = str1.concat("3");
				sPhoneNumber.append("3");
				break;
			case 'g':
			case 'h':
			case 'i':
				// str1 = str1.concat("4");
				sPhoneNumber.append("4");
				break;
			case 'j':
			case 'k':
			case 'l':
				// str1 = str1.concat("5");
				sPhoneNumber.append("5");
				break;
			case 'm':
			case 'n':
			case 'o':
				// str1 = str1.concat("6");
				sPhoneNumber.append("6");
				break;
			case 'p':
			case 'r':
			case 's':
				// str1 = str1.concat("7");
				sPhoneNumber.append("7");
				break;
			case 't':
			case 'u':
			case 'v':
				// str1 = str1.concat("8");
				sPhoneNumber.append("8");
				break;
			case 'w':
			case 'x':
			case 'y':
				// str1 = str1.concat("9");
				sPhoneNumber.append("9");
			}
		}

		// return str1;
		return sPhoneNumber.toString();
	}

	/**
	 * It validates the specified phone number is valid or not.
	 * 
	 * @param String
	 *            phoneNumber
	 * @return
	 */
	public boolean isValidPhone(String phoneNumber) {
		Pattern pattern = Pattern.compile(PHONE_REGEX);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PhoneParser utils = new PhoneParser();
		String newPhoneNumber = null;
		String phoneNumber = "+1 (201) 238-6938";
		newPhoneNumber = utils.parsePhoneNumber(phoneNumber);
		System.out.println("phoneNumber:" + phoneNumber + ", newPhoneNumber: " + newPhoneNumber
				+ ", Valid Phone Number:" + utils.isValidPhone(phoneNumber));
		phoneNumber = "(+1) 201-238-6938";
		newPhoneNumber = utils.parsePhoneNumber(phoneNumber);
		System.out.println("phoneNumber:" + phoneNumber + ", newPhoneNumber: " + newPhoneNumber
				+ ", Valid Phone Number:" + utils.isValidPhone(phoneNumber));
		phoneNumber = "+1.201.238.6938";
		newPhoneNumber = utils.parsePhoneNumber(phoneNumber);
		System.out.println("phoneNumber:" + phoneNumber + ", newPhoneNumber: " + newPhoneNumber
				+ ", Valid Phone Number:" + utils.isValidPhone(phoneNumber));
		phoneNumber = "+1 (A01)-BET-MWDU";
		newPhoneNumber = utils.parsePhoneNumber(phoneNumber);
		System.out.println("phoneNumber:" + phoneNumber + ", newPhoneNumber: " + newPhoneNumber
				+ ", Valid Phone Number:" + utils.isValidPhone(phoneNumber));
		phoneNumber = "+1 (B01)-CDU-NXFV";
		newPhoneNumber = utils.parsePhoneNumber(phoneNumber);
		System.out.println("phoneNumber:" + phoneNumber + ", newPhoneNumber: " + newPhoneNumber
				+ ", Valid Phone Number:" + utils.isValidPhone(phoneNumber));
		phoneNumber = "+1 (A01)-BET-MWDUz";
		newPhoneNumber = utils.parsePhoneNumber(phoneNumber);
		System.out.println("phoneNumber:" + phoneNumber + ", newPhoneNumber: " + newPhoneNumber
				+ ", Valid Phone Number:" + utils.isValidPhone(phoneNumber));
	}
}