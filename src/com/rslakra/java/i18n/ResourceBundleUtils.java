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

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

/**
 * ResourceBundleUtils.java
 * 
 * The <code>ResourceBundleUtils</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.lakra@devamatre.com)
 * @date Aug 9, 2009 2:40:38 PM
 */
public final class ResourceBundleUtils implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 7283372414185836492L;
	
	/* Name of the resource bundle to use */
	private static final String RESOURCE_BUNDLE_NAME = "resources";
	
	/* logger */
	private static final Logger log = LogManager.getLogger(ResourceBundleUtils.class);
	
	/* List of our ResourceBundleMap objects */
	private static Map<String, ResourceBundleUtils> bundles = null;
	
	/* Internal Resource Bundle */
	private ResourceBundle resourceBundle = null;
	
	/**
	 * Constructor (private access, use getInstance instead)
	 * 
	 * @param packageName
	 *            name of the package for the bundle
	 * 
	 */
	// private ResourceBundleMap(String packageName) {
	// /**
	// * Resource bundle is on the form: packagename.resources.properties
	// */
	// String bundleName = packageName + "." + RESOURCE_BUNDLE_NAME;
	// try {
	// resourceBundle = ResourceBundle.getBundle(bundleName);
	// } catch (MissingResourceException mre) {
	// String err = "Error when trying to get a ResourceBundle for package : '"
	// + packageName + "'";
	// throw new IllegalStateException(err);
	// }
	// }
	
	private ResourceBundleUtils(String bundleName) {
		if (log.isDebugEnabled()) {
			log.debug("+ResourceBundleUtils(" + bundleName + ")");
		}
		try {
			resourceBundle = ResourceBundle.getBundle(bundleName);
		} catch (MissingResourceException mre) {
			log.error("Error while finding ResourceBundle for : '" + bundleName + "', ,mre : " + mre);
			// throw new
			// IllegalStateException("Error while finding ResourceBundle for :
			// '"
			// + bundleName + "'");
			mre.printStackTrace();
		}
		if (log.isDebugEnabled()) {
			log.debug("-ResourceBundleUtils()");
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
	public static ResourceBundleUtils getInstance(Class<?> klass) {
		// String bundleName = klass.getPackage().getName() + "." +
		// RESOURCE_BUNDLE_NAME + "." + CoreUtility.getClassName(klass);
		// return getInstance(bundleName);
		return null;
	}
	
	/**
	 * Gets the instance for a given package
	 * 
	 * @param packageName
	 *            the package for which we want a bundle
	 * @return I18n object
	 */
	public static ResourceBundleUtils getInstance(String resBundleName) {
		if (log.isDebugEnabled()) {
			log.debug("+getInstance(" + resBundleName + "), bundles: " + bundles);
		}
		
		if (bundles == null) {
			bundles = new HashMap<String, ResourceBundleUtils>();
		}
		
		ResourceBundleUtils resBundleMap = (ResourceBundleUtils) bundles.get(resBundleName);
		if (log.isDebugEnabled()) {
			log.debug("resBundleMap: " + resBundleMap);
		}
		if (resBundleMap != null) {
			return resBundleMap;
		}
		resBundleMap = new ResourceBundleUtils(resBundleName);
		if (log.isDebugEnabled()) {
			log.debug("resBundleName : " + resBundleName + ", resBundleMap : " + resBundleMap);
		}
		bundles.put(resBundleName, resBundleMap);
		
		if (log.isDebugEnabled()) {
			log.debug("-getInstance(), resBundleMap: " + resBundleMap);
		}
		return resBundleMap;
	}
	
	/**
	 * Gets the formatted string with the given arguments
	 * 
	 * @param key
	 *            the keystring on which to apply arguments
	 * @param args
	 *            the object arguments for the formatter
	 * @return the formatted string
	 */
	public String getValue(String key, Object[] args) {
		if (log.isDebugEnabled()) {
			log.debug("+getValue(" + key + ", " + args + ")");
		}
		String value = getValue(key);
		if (log.isDebugEnabled()) {
			log.debug("value : " + value);
		}
		value = MessageFormat.format(value, args);
		if (log.isDebugEnabled()) {
			log.debug("-getValue(), value : " + value);
		}
		return value;
	}
	
	/**
	 * Gets the value of the given key
	 * 
	 * @param key
	 *            the keystring to retrieve
	 * @return the value for the given key or the key if the value can not be
	 *         found.
	 */
	public String getValue(String key) {
		if (log.isDebugEnabled()) {
			log.debug("+getValue(" + key + ")");
		}
		String value = key;
		// No bundle, return key
		if (null != resourceBundle) {
			try {
				value = resourceBundle.getString(key);
			} catch (MissingResourceException mre) {
				log.error("No Value found for the key : " + key + ", mre : " + mre);
				// key not found, return the key as value
				value = key;
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("-getValue(), value : " + value);
		}
		return value;
	}
	
	/**
	 * Gets the formatted string with the given arguments
	 * 
	 * @param key
	 *            the keystring on which to apply arguments
	 * @param obj
	 *            the object argument for the formatter
	 * @return the formatted string
	 */
	public String getValue(String key, Object obj) {
		return getValue(key, new Object[] { obj });
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
		ResourceBundleUtils resBundle = ResourceBundleUtils.getInstance(ResourceBundleUtils.class);
		System.out.println("Hello, " + resBundle.getValue("Title") + " " + resBundle.getValue("Name"));
	}
}