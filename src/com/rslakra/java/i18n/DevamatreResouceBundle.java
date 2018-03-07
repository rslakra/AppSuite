/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009 - 2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, 
 * 	  this list of conditions and the following disclaimer.
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

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

/**
 * DevamatreResouceBundle.java
 * 
 * The <code>LakraResouceBundle</code> handles the resources based on locale.
 * TODO Define Purpose here
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @date Aug 9, 2009 2:22:31 PM
 */
public class DevamatreResouceBundle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* Logger */
	private static final Logger logger = LogManager.getLogger(DevamatreResouceBundle.class);
	
	/* baseName */
	private String baseName;
	
	/* locale */
	private Locale locale;
	
	/* resourceBundles */
	private HashMap<String, String> resourceBundles = new HashMap<String, String>();
	
	/**
	 * 
	 * @param baseName
	 * @param language
	 * @param country
	 */
	public DevamatreResouceBundle(String baseName) {
		this(baseName, Locale.getDefault());
	}
	
	/**
	 * 
	 * @param baseName
	 * @param language
	 * @param country
	 */
	public DevamatreResouceBundle(String baseName, Locale locale) {
		if (logger.isDebugEnabled()) {
			logger.debug("+DevamatreResouceBundle(" + baseName + ", " + locale + ")");
		}
		/*
		 * if baseName is null or empty, the resource file is searched inside
		 * the same package of the resource loading class.
		 */
		if (baseName == null || "".equals(baseName)) {
			this.baseName = getClass().getPackage().getName().replace(".", "/").intern() + "/MyResources";
		} else {
			this.baseName = baseName;
		}
		// if locale is null, the default locale is used.
		this.locale = (locale == null ? Locale.getDefault() : locale);
		loadLocale();
		if (logger.isDebugEnabled()) {
			logger.debug("-DevamatreResouceBundle()");
		}
	}
	
	/**
	 * Default Constructor.
	 * 
	 * @param baseName
	 * @param language
	 * @param country
	 */
	public DevamatreResouceBundle(String baseName, String language, String country) {
		if (logger.isDebugEnabled()) {
			logger.debug("+DevamatreResouceBundle(" + baseName + ", " + language + ", " + country + ")");
		}
		// this(baseName, new Locale(language, country));
		/*
		 * if baseName is null or empty, the resource file is searched inside
		 * the same package of the resource loading class.
		 */
		if (baseName == null || "".equals(baseName)) {
			this.baseName = getClass().getPackage().getName().replace(".", "/").intern() + "/MyResources";
		} else {
			this.baseName = baseName;
		}
		
		// if language is null or empty, the default language "en" is used.
		if (language == null || "".equals(language)) {
			language = "en";
		}
		// if language is null or empty, the default country "US" is used.
		if (country == null || "".equals(country)) {
			country = "US";
		}
		this.locale = new Locale(language, country);
		loadLocale();
		if (logger.isDebugEnabled()) {
			logger.debug("-DevamatreResouceBundle()");
		}
	}
	
	/**
	 *
	 */
	private void loadLocale() {
		if (logger.isDebugEnabled()) {
			logger.debug("+loadLocale(), baseName:" + getBaseName() + ", local:" + getLocal());
		}
		
		ResourceBundle resBundle = null;
		try {
			resBundle = ResourceBundle.getBundle(getBaseName().trim(), getLocal());
		} catch (MissingResourceException mre) {
			logger.error("Could not find bunlde for basename: '" + (getBaseName() + getLocal().toString()) + "' , mre : " + mre);
			mre.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("resBundle : " + resBundle);
		}
		Enumeration<String> objEnum = resBundle.getKeys();
		while (objEnum.hasMoreElements()) {
			String key = objEnum.nextElement();
			if (logger.isDebugEnabled()) {
				logger.debug("key : " + key);
			}
			if (!resourceBundles.containsKey(key)) {
				String value = resBundle.getString(key);
				if (logger.isDebugEnabled()) {
					logger.debug("value : " + value);
				}
				resourceBundles.put(key, value);
			}
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("-loadLocale()");
		}
	}
	
	private Locale getLocal() {
		return locale;
	}
	
	private String getBaseName() {
		return baseName;
	}
	
	/**
	 * This method returns the value for the key. If does not not found, it
	 * ruturns the key back.
	 * 
	 * @param key
	 *            - which value to be returned.
	 * @return value of the key.
	 */
	public String getValue(String key) {
		if (logger.isDebugEnabled()) {
			logger.debug("+getValue(" + key + ")");
		}
		String value = key;
		if (resourceBundles.containsKey(key)) {
			value = (String) resourceBundles.get(key);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("+getValue(), value : " + value);
		}
		return value;
	}
	
	/**
	 * 
	 * @param klass
	 * @return
	 */
	public static DevamatreResouceBundle getResourceBundle(Class<?> klass) {
		return new DevamatreResouceBundle(null);
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// LogManager.configure(System.getProperty("user.dir"));
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
		Logger logger = LogManager.getLogger(DevamatreResouceBundle.class);
		final String baseName = "org/devamatre/resources/MyResources";
		
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(null);
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle("");
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(baseName);
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(baseName, new Locale("de", "DE"));
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(baseName, "de", "DE");
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(baseName, "en", "US");
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(baseName, null);
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(baseName, null, "US");
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(baseName, "", "US");
		// DevamatreResouceBundle devResouceBundle = new
		// DevamatreResouceBundle(baseName, "de", null);
		DevamatreResouceBundle devResouceBundle = new DevamatreResouceBundle(baseName, "de", "");
		
		logger.info(devResouceBundle.getValue("name"));
		logger.info(devResouceBundle.getValue("password"));
	}
}