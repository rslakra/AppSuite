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

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.devamatre.core.CoreHelper;

/**
 * The <class>ResourceManager</class> class contains the locale-specific
 * objects. When your program needs a locale-specific resource, they can be
 * loaded from the resource bundle that is appropriate for the current user's
 * locale.
 *
 * This allows you to write programs that can: i) be easily localized, or
 * translated, into different languages. ii) handle multiple locales at once
 * and, iii) be easily modified later to support even more locales.
 *
 * Resource bundles belong to families whose members share a common base name,
 * but whose names also have additional components that identify their locales.
 * For example, the base name of a family of resource bundles might be
 * "resources". The family should have a default resource bundle which simply
 * has the same name as its family - "resources" - and will be used as the
 * bundle of last resort. If a specific locale is not supported. The family can
 * then provide as many locale-specific members as needed, for example a
 * Switzerland one named "resources_ch" etc.
 * 
 * @author Rohtash Singh
 * @version Apr 15, 2006
 */
public class ResourceManager {
	
	/* Name of the resource bundle to use */
	private static final String BASE_RESOURCE_NAME = "resources";
	
	/* List of our ResourceManager objects */
	private static Map bundles = null;
	
	/* Internal Resource Bundle */
	private ResourceBundle resourceBundle = null;
	
	/**
	 * Constructor, private access, instead of global access
	 */
	private ResourceManager(String bundleName) {
		
		try {
			resourceBundle = ResourceBundle.getBundle(bundleName);
		} catch (MissingResourceException mre) {
			throw new IllegalStateException("No ResourceBundle found for bundleName : '" + bundleName + "'");
		}
	}
	
	/**
	 * Gets the instance for a given class Resource bundle is on the form:
	 * packagename.resources.<ClassName>.properties
	 *
	 * @param c
	 *            the class for which we want a bundle
	 * @return I18n object
	 */
	public static ResourceManager getResources(Class<?> klass) {
		String resBundleName = createResouceBundleName(klass);
		System.out.println("resBundleName: " + resBundleName);
		return getResourceManager(resBundleName);
	}
	
	private static String createResouceBundleName(Class<?> klass) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(klass.getPackage().getName());
		buffer.append(".");
		buffer.append(BASE_RESOURCE_NAME);
		buffer.append(".");
		// buffer.append(CoreHelper.getInstance().getClassName(klass));
		return buffer.toString();
	}
	
	/**
	 * Gets the instance for a given package
	 * 
	 * @param resBundleName
	 *            the name of the resource bundler for which we want a bundle.
	 * @return ResourceManager Object
	 */
	private static ResourceManager getResourceManager(String resBundleName) {
		// check coding errors.
		if (CoreHelper.isNull(resBundleName)) {
			throw new NullPointerException("Resouce BundleName is Null!");
		}
		
		// check programming errors.
		if (bundles == null) {
			// initialize the bundles map
			bundles = new HashMap();
		}
		// get resource manager from the bundles map.
		ResourceManager resManager = (ResourceManager) bundles.get(resBundleName);
		/*
		 * Check resource manager for the passed bundle name, exists or not. If
		 * found, return the same, no need to create the new one.
		 */
		if (resManager != null) {
			return resManager;
		}
		/*
		 * No resource manager exists for the passed bundle name, so creating
		 * new one.
		 */
		resManager = new ResourceManager(resBundleName);
		// add in the map for the resource bundle name
		bundles.put(resBundleName, resManager);
		return resManager;
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
	 * This method returns the value of the gieve key.
	 * 
	 * @param key
	 *            the keystring to retrieve
	 * @return the value for the given key or the key if the value can not be
	 *         found.
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
}
