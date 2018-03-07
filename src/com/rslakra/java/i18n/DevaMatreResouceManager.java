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

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

public final class DevaMatreResouceManager {
	private static final String RESOURCE_NAME = "resources";
	
	/* logger */
	private static final Logger logger = LogManager.getLogger(DevaMatreResouceManager.class);
	
	private static Map<String, DevaMatreResouceManager> bundles;
	private ResourceBundle resBundle = null;
	
	/**
	 * 
	 * @param bundleName
	 */
	private DevaMatreResouceManager(String bundleName) {
		if (logger.isDebugEnabled()) {
			logger.debug("+ResourceBundleUtils(" + bundleName + ")");
		}
		try {
			resBundle = ResourceBundle.getBundle(bundleName);
		} catch (MissingResourceException mre) {
			logger.error("Error while finding ResourceBundle for : '" + bundleName + "', ,mre : " + mre);
			// throw new
			// IllegalStateException("Error while finding ResourceBundle for :
			// '"
			// + bundleName + "'");
			mre.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("-ResourceBundleUtils()");
		}
	}
	
	/**
	 * 
	 * @param klass
	 * @return
	 */
	public static DevaMatreResouceManager getResourceBundle(Class<?> klass) {
		String bundleName = getBundleName(klass);
		if (logger.isDebugEnabled()) {
			logger.debug("+getResourceBundle(" + bundleName + ")");
		}
		if (bundles == null) {
			bundles = new HashMap<String, DevaMatreResouceManager>();
		}
		DevaMatreResouceManager devResBundleManager = bundles.get(bundleName);
		if (logger.isDebugEnabled()) {
			logger.debug("devResBundleManager : " + devResBundleManager);
		}
		if (devResBundleManager != null) {
			return devResBundleManager;
		}
		devResBundleManager = new DevaMatreResouceManager(bundleName);
		if (logger.isDebugEnabled()) {
			logger.debug("bundleName : " + bundleName + ", devResBundleManager : " + devResBundleManager);
		}
		
		bundles.put(bundleName, devResBundleManager);
		if (logger.isDebugEnabled()) {
			logger.debug("-getInstance(), devResBundleManager : " + devResBundleManager);
		}
		
		return devResBundleManager;
	}
	
	/**
	 * 
	 * @param klass
	 * @return
	 */
	private static String getBundleName(Class<?> klass) {
		String className = klass.getName();
		System.out.println("className : " + className);
		int index = className.lastIndexOf(".");
		className = className.substring(index + 1);
		System.out.println("className : " + className);
		String packageName = klass.getPackage().getName();
		System.out.println("packageName : " + packageName);
		String bundleName = packageName + "." + RESOURCE_NAME + "." + className;
		return bundleName;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		String value = key;
		if (null != resBundle) {
			try {
				value = resBundle.getString(key);
			} catch (MissingResourceException mre) {
				System.out.println("No Value found for key: " + key + ", mre : " + mre);
				// // key not found, return the key as value
				// value = key;
			}
		}
		return value;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
		DevaMatreResouceManager devResBundleManager = DevaMatreResouceManager.getResourceBundle(DevaMatreResouceManager.class);
		Logger logger = LogManager.getLogger(DevaMatreResouceManager.class);
		logger.debug(devResBundleManager.getMessage("Title"));
		logger.debug(devResBundleManager.getMessage("fName"));
		logger.debug(devResBundleManager.getMessage("mName"));
		logger.debug(devResBundleManager.getMessage("lName"));
		logger.debug(devResBundleManager.getMessage("address"));
	}
}