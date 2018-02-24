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

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceUtils {
	private static String RESOURCE_NAME = "resources";
	private static String BASE_NAME = "ResouceBundleBaseName";
	private static Map bundles;
	private static ResourceBundle bundle;
	private static ResourceUtils resBundle;

	private ResourceUtils(String bundleName) {
		try {
			bundle = ResourceBundle.getBundle(bundleName);
		} catch (MissingResourceException ex) {
			System.out.println("Can't Find Bundle for BaseName : '" + bundleName + "'");
			ex.printStackTrace();
		}
	}

	public static ResourceUtils getResourceBundle(Class klass) {
		String bundleName = getBundleName(klass);
		System.out.println("bundleName : " + bundleName);
		if (bundles == null) {
			bundles = new HashMap();
		}
		resBundle = (ResourceUtils) bundles.get(bundleName);
		if (resBundle != null) {
			return resBundle;
		}
		resBundle = new ResourceUtils(bundleName);
		bundles.put(bundleName, resBundle);
		return resBundle;
	}

	private static String getBundleName(Class klass) {
		String className = klass.getName();
		System.out.println("className : " + className);
		int index = className.lastIndexOf(".");
		className = className.substring(index + 1);
		System.out.println("className : " + className);
		String packageName = klass.getPackage().getName();
		System.out.println("packageName : " + packageName);
		String bundleName = packageName + "." + RESOURCE_NAME + "." + className + ".properties";
		return bundleName;
	}

	public String getMessage(String key) {
		if (null == key) {
			return key;
		}
		return bundle.getString(key);
	}

	public static void main(String[] args) {
		ResourceUtils rUtils = ResourceUtils.getResourceBundle(ResourceUtils.class);
		System.out.println(rUtils.getMessage(BASE_NAME));
	}
}