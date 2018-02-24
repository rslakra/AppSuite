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
package com.rslakra.java.i18n;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * ReadLocalized.java
 * 
 * The <code>ReadLocalized</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @date Aug 9, 2009 2:36:32 PM
 */
public class ReadLocalized {
	private final String baseName = "org/devamatre/resources/Login";
	private Locale locale[];

	public ReadLocalized() {
		// rslutility.resoucebundle
		getProperties("propFileName");
		// Us locale
		ResourceBundle resBundle = ResourceBundle.getBundle(baseName, Locale.US);
		Enumeration objEnum = resBundle.getKeys();
		String keyUs = "";
		while (objEnum.hasMoreElements()) {
			keyUs = (String) objEnum.nextElement();
			System.out.println(keyUs + " = " + resBundle.getString(keyUs));
		}

		// Germany locale
		ResourceBundle resBundle1 = ResourceBundle.getBundle(baseName, Locale.GERMANY);
		Enumeration enum1 = resBundle1.getKeys();
		String key = "";
		while (enum1.hasMoreElements()) {
			key = (String) enum1.nextElement();
			System.out.println(key + " = " + resBundle1.getString(key));
		}

		/*
		 * //Code for determining ISO language and country codes. Locale
		 * locGermany = Locale.GERMANY;
		 * System.out.println(locGermany.getLanguage());
		 * System.out.println(locGermany.getCountry());
		 */
	}

	private Properties getProperties(String propFileName) {
		Properties prop = new Properties();
		// try {
		// prop.load(getClass().getResourceAsStream(
		// "rslutility/resoucebundle/MyResources/"));
		Enumeration objEnum = prop.propertyNames();
		System.out.println("\nProperties of Files : ");
		while (objEnum.hasMoreElements()) {
			String str = (String) objEnum.nextElement();
		}
		// } catch(IOException ex) {
		// System.out.println("IOException : " + ex.getMessage());
		// ex.printStackTrace();
		// }
		return prop;
	}

	public static void main(String args[]) {
		new ReadLocalized();
		Locale locGermany = Locale.GERMANY;
		System.out.println(locGermany.getLanguage());
		System.out.println(locGermany.getCountry());
	}
}
