/******************************************************************************
 * Copyright (C) Devamatre Inc 2006-2018. All rights reserved.
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

package com.rslakra.java.events.customized;

import java.util.Locale;

/**
 * This <class>ListAllAvailableLocale</class> class
 *
 * @author Rohtash Singh
 * @version Feb 3, 2006
 */

public class ListAllAvailableLocale {

	public ListAllAvailableLocale() {
		Locale[] locales = Locale.getAvailableLocales();

		System.out.println("================= All Availabe Locale ==================");
		System.out.println("Language\tCountry\t\tLocale Name");

		for (int i = 0; i < locales.length; i++) {

			// Get the 2-letter language code
			String language = locales[i].getLanguage();

			// Get the 2-letter country code; may be equal to ""
			String country = locales[i].getCountry();

			// Get localized name suitable for display to the user
			String locName = locales[i].getDisplayName();
			System.out.println(language + "\t\t" + country + "\t\t" + locName);
		}

		System.out.println("==================================================");
	}

	public void defaultLocale() {

		/**
		 *
		 * Setting the Default Locale
		 *
		 * There are two ways to change the default locale. The first is to set
		 * it on the command line: > java -Duser.language=2-char-language-code
		 * -Duser.region=2-char-country-code MyApp
		 *
		 * // Set only language code > java -Duser.language=fr -Duser.region=
		 * MyApp // Set language and country code > java -Duser.language=fr
		 * -Duser.region=CA MyApp
		 *
		 * The second way to change the default locale is to call
		 * Locale.setDefault():
		 *
		 * // Get default locale Locale locale = Locale.getDefault();
		 *
		 * // Set the default locale to pre-defined locale
		 * Locale.setDefault(Locale.FRENCH);
		 *
		 * // Set the default locale to custom locale locale = new Locale("fr",
		 * "CA"); Locale.setDefault(locale);
		 */
	}

	public static void main(String[] args) {
		new ListAllAvailableLocale();
	}
}
