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
package com.rslakra.java.i10n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author rohtash.singh
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ResourceBundleMap {
	/* Name of the resource bundle to use */
	private static final String RESOURCE_BUNDLE_NAME = "resources";
	
	/* List of our ResourceBundleMap objects */
	private static Map bundles = null;
	
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
	
	private ResourceBundleMap(String bundleName) {
		try {
			resourceBundle = ResourceBundle.getBundle(bundleName);
		} catch (MissingResourceException mre) {
			throw new IllegalStateException("Error while finding ResourceBundle for : '" + bundleName + "'");
		}
	}
	
	private static String getClassName(Class klass) {
		int index = klass.getName().trim().lastIndexOf(".");
		System.out.println("index : " + index);
		String className = klass.getName().trim().substring(index + 1);
		System.out.println("className : " + className);
		return className;
	}
	
	private static String getPackageName(Class klass) {
		String packageName = klass.getPackage().getName();
		System.out.println("packageName : " + packageName);
		return packageName;
	}
	
	/**
	 * Gets the instance for a given class Resource bundle is on the form:
	 * packagename.resources.<ClassName>.properties
	 * 
	 * @param c
	 *            the class for which we want a bundle
	 * @return I18n object
	 */
	public static ResourceBundleMap getInstance(Class c) {
		String bundleName = getPackageName(c) + "." + RESOURCE_BUNDLE_NAME + "." + getClassName(c);
		System.out.println("bundleName : " + bundleName);
		return getInstance(bundleName);
	}
	
	/**
	 * Gets the instance for a given package
	 * 
	 * @param packageName
	 *            the package for which we want a bundle
	 * @return I18n object
	 */
	public static ResourceBundleMap getInstance(String resBundleName) {
		if (bundles == null) {
			bundles = new HashMap();
		}
		ResourceBundleMap resBundleMap = (ResourceBundleMap) bundles.get(resBundleName);
		if (resBundleMap != null) {
			return resBundleMap;
		}
		resBundleMap = new ResourceBundleMap(resBundleName);
		bundles.put(resBundleName, resBundleMap);
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
	public String getString(String key, Object[] args) {
		String value = getString(key);
		return MessageFormat.format(value, args);
	}
	
	/**
	 * Gets the value of the given key
	 * 
	 * @param key
	 *            the keystring to retrieve
	 * @return the value for the given key or the key if the value can not be
	 *         found
	 */
	public String getString(String key) {
		String value = null;
		// No bundle, return key
		if (resourceBundle == null) {
			return key;
		}
		try {
			value = resourceBundle.getString(key);
		} catch (MissingResourceException mre) {
			// key not found, return the key
			value = key;
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
	public String getString(String key, Object obj) {
		return getString(key, new Object[] { obj });
	}
	
	public static void main(String[] args) {
		ResourceBundleMap resBundle = ResourceBundleMap.getInstance(ResourceBundleMap.class);
		System.out.println("Hello, " + resBundle.getString("Title") + " " + resBundle.getString("Name"));
	}
}
