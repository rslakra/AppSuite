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

import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;
import com.rslakra.java.enums.FileType;

/**
 * The <code>I18NUtils</code> class handles the resource bundle for the
 * component.
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @date Aug 9, 2009 2:40:38 PM
 * @version 1.0
 * @since 1.0
 * 
 */
public final class I18NUtility implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 4267957230247911068L;
	
	/** RESOURCE_BUNDLE_NAME */
	private static final String RESOURCE_BUNDLE_NAME = "resources";
	
	/** logger */
	private static final Logger logger = LogManager.getLogger(I18NUtility.class);
	
	/** resourceBundles */
	private static Map<String, I18NUtility> resourceBundles = null;
	
	/** resourceBundle - Internal Resource Bundle */
	private ResourceBundle resourceBundle = null;
	
	/**
	 * 
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class
	 *            name
	 * 
	 *            See {@link #getBundle(String, Locale, ClassLoader) getBundle}
	 *            for a complete description.
	 */
	private I18NUtility(String baseName) {
		if (logger.isDebugEnabled()) {
			logger.debug("+I18NUtility(" + baseName + ")");
		}
		
		try {
			resourceBundle = ResourceBundle.getBundle(baseName);
		} catch (MissingResourceException mre) {
			logger.error("Error while finding resource bundle for '" + baseName + "', mre: " + mre);
			mre.printStackTrace();
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("-I18NUtility()");
		}
	}
	
	/**
	 * To make a singleton class serializable, it is not only sufficient to add
	 * implements <code>Serializable</code> to its declaration. To maintain the
	 * singleton guarantee, you must also provide a <code>readResolve</code>
	 * method.
	 * 
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return this;
	}
	
	/**
	 * Gets the instance for a given class Resource bundle is on the form:
	 * packagename.resources.<ClassName>.properties
	 * 
	 * @param c
	 *            the class for which we want a bundle
	 * @return I18n object
	 */
	public static I18NUtility getResourceBundle(Class<?> klass) {
		// String bundleName = klass.getPackage().getName() + "." +
		// RESOURCE_BUNDLE_NAME + "." + CoreHelper.getClassName(klass);
		// return getResourceBundle(bundleName);
		return null;
	}
	
	/**
	 * Gets the instance for a given package
	 * 
	 * @param resBundleName
	 *            - fully qualified class for resource bundle name.
	 * @return
	 */
	public static I18NUtility getResourceBundle(String resBundleName) {
		logger.debug("+getInstance(" + resBundleName + ")");
		
		/* check resource bundles is initialized or not. */
		if (resourceBundles == null) {
			resourceBundles = new HashMap<String, I18NUtility>();
		}
		
		I18NUtility resBundle = resourceBundles.get(resBundleName);
		logger.debug("resBundle:" + resBundle);
		
		/*
		 * if no resource bundle exist for this resource, create new and add in
		 * cache to use later.
		 */
		if (resBundle == null) {
			resBundle = new I18NUtility(resBundleName);
			logger.debug("resBundleName:" + resBundleName + ", resBundle:" + resBundle);
			resourceBundles.put(resBundleName, resBundle);
		}
		
		logger.debug("-getInstance(), resBundle:" + resBundle);
		return resBundle;
	}
	
	/**
	 * Gets the formatted string with the given arguments
	 * 
	 * @param key
	 *            the key string on which to apply arguments
	 * @param args
	 *            the object arguments for the formatter
	 * @return the formatted string
	 */
	public String getValue(String key, Object[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("+getValue(" + key + ", " + args + ")");
		}
		
		String value = getValue(key);
		if (logger.isDebugEnabled()) {
			logger.debug("value: " + value);
		}
		value = MessageFormat.format(value, args);
		
		if (logger.isDebugEnabled()) {
			logger.debug("-getValue(), value: " + value);
		}
		return value;
	}
	
	/**
	 * Returns the value of the specified key. If there is no key found, the
	 * <code><<key>></code> is return as value.
	 * 
	 * @param key
	 *            the key string to retrieve
	 * @return the value for the given key or the key if the value can not be
	 *         found.
	 */
	public String getValue(String key) {
		if (logger.isDebugEnabled()) {
			logger.debug("+getValue(" + key + ")");
		}
		
		// key not found, return the key as value
		String value = "<<" + key + ">>";
		try {
			value = resourceBundle.getString(key);
		} catch (MissingResourceException mre) {
			logger.error("No value found for the key: " + key + ", mre: " + mre);
			// value = key;
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("-getValue(), value: " + value);
		}
		return value;
	}
	
	/**
	 * Gets the formatted string with the given arguments
	 * 
	 * @param key
	 *            the key string on which to apply arguments
	 * @param obj
	 *            the object argument for the formatter
	 * @return the formatted string
	 */
	public String getValue(String key, Object obj) {
		return getValue(key, new Object[] { obj });
	}
	
	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getIntValue(String key, int defValue) {
		try {
			return Integer.parseInt(getValue(key));
		} catch (NumberFormatException ex) {
			return defValue;
		}
	}
	
	/**
	 * 
	 * @param fileName
	 * @param loader
	 * @return
	 */
	public Properties loadProperties(String fileName, ClassLoader classLoader, final boolean loadAsResourceBundle) {
		String fileType = FileType.PROPERTIES.getFileType();
		if (fileName == null) {
			throw new IllegalArgumentException("Invalid fileName: " + fileName);
		}
		
		/* remove backslash from the beginning of file name. */
		if (fileName.startsWith("/")) {
			fileName = fileName.substring(1);
		}
		
		Properties result = new Properties();
		InputStream inputStream = null;
		try {
			/* initialize class loader if null */
			if (classLoader == null) {
				classLoader = ClassLoader.getSystemClassLoader();
			}
			
			/* load as resource bundle */
			if (loadAsResourceBundle) {
				/* remove extension of file from file name. */
				if (fileName.endsWith(fileType)) {
					fileName = fileName.substring(0, fileName.length() - fileType.length());
				}
				/* replace backslash (/) with dot (.) */
				fileName = fileName.replace('/', '.');
				
				/* throws MissingResourceException on lookup failures: */
				final ResourceBundle rBundle = ResourceBundle.getBundle(fileName, Locale.getDefault(), classLoader);
				
				/* traversal resources */
				for (Enumeration<String> keys = rBundle.getKeys(); keys.hasMoreElements();) {
					final String key = keys.nextElement();
					final String value = rBundle.getString(key);
					result.put(key, value);
				}
			} else { /* otherwise as normal property file */
				/* replace dots (.) with backslash (/) */
				fileName = fileName.replace('.', '/');
				
				/* Returns null on lookup failures: */
				inputStream = classLoader.getResourceAsStream(fileName);
				if (inputStream != null) {
					/* can throw IOException */
					result.load(inputStream);
				}
			}
		} catch (Exception ex) {
			result = null;
			System.err.println("Error while loading properties from fileName: " + fileName + ", ex: " + ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Throwable throwable) {
					System.err.println("Error while closing inputStream: " + inputStream);
				}
			}
		}
		
		if (result == null) {
			throw new IllegalArgumentException("Unable to load [" + fileName + "] as a " + (loadAsResourceBundle ? "resource bundle" : "classloader resource"));
		}
		
		return result;
	}
	
	/**
	 * A convenience overload of {@link #loadProperties(String, ClassLoader)}
	 * that uses the current thread's context ClassLoader.
	 * 
	 * @param fileName
	 * @return
	 */
	public Properties loadProperties(final String fileName, final boolean loadAsResourceBundle) {
		return loadProperties(fileName, Thread.currentThread().getContextClassLoader(), loadAsResourceBundle);
	}
	
	/**
	 * Starting Point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/* configure logger */
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
		
		/* load resources for the class. */
		I18NUtility resBundle = I18NUtility.getResourceBundle(I18NUtility.class);
		System.out.println("Hello, " + resBundle.getValue("Salutation") + " " + resBundle.getValue("Name"));
		System.out.println(resBundle.getValue("JobTitle"));
	}
}