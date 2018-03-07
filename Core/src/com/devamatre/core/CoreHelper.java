/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009 - 2018. All rights reserved.
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
package com.devamatre.core;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2008-02-22 08:18:05 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public final class CoreHelper {
	
	/* instance */
	private static CoreHelper instance;
	
	private CoreHelper() {
	}
	
	/**
	 * Returns the singleton instance of this object.
	 * 
	 * @return
	 */
	public static CoreHelper getInstance() {
		if (instance == null) {
			synchronized (CoreHelper.class) {
				if (instance == null) {
					instance = new CoreHelper();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * Returns the OS name.
	 * 
	 * @return
	 */
	public static String getOSName() {
		return System.getProperty("os.name");
	}
	
	/**
	 * Returns the Java VM Name.
	 * 
	 * @return
	 */
	public static String getJVMName() {
		return System.getProperty("java.vm.name");
	}
	
	/**
	 * Returns true if the object is null otherwise false.
	 * 
	 * @param object
	 * @return
	 */
	public static <T> boolean isNull(T object) {
		return (object == null);
	}
	
	/**
	 * Returns true if the object is not null otherwise false.
	 * 
	 * @param object
	 * @return
	 */
	public static <T> boolean isNotNull(T object) {
		return (!isNull(object));
	}
	
	/**
	 * Returns true if an array is null otherwise false.
	 * 
	 * @param objects
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(T[] objects) {
		return (isNull(objects) || objects.length == 0);
	}
	
	/**
	 * Returns true if the bytes array is either null or empty otherwise false.
	 * 
	 * @param bytes
	 * @return
	 */
	public static boolean isNullOrEmpty(byte... array) {
		return (isNull(array) || array.length == 0);
	}
	
	/**
	 * Returns true if either the string is null or empty otherwise false.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string) {
		return (isNull(string) || string.isEmpty() || string.trim().length() == 0);
	}
	
	/**
	 * Returns true if the given string is neither null nor empty otherwise
	 * false.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNotNullOrEmpty(String string) {
		return (!isNullOrEmpty(string));
	}
	
	/**
	 * Returns true if the StringBuilder is null or empty otherwise false.
	 * 
	 * @param stringBuilder
	 * @return
	 */
	public static boolean isNullOrEmpty(StringBuilder stringBuilder) {
		return (isNull(stringBuilder) || stringBuilder.length() == 0);
	}
	
	/**
	 * Returns true if the collection is either null or empty otherwise false.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(Collection<T> collection) {
		return (isNull(collection) || collection.isEmpty());
	}
	
	/**
	 * Returns true if the map is either null or empty otherwise false.
	 * 
	 * @param map
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(Map<?, ?> map) {
		return (isNull(map) || map.isEmpty());
	}
	
	/**
	 * 
	 * @param klass
	 * @return
	 */
	public static String getClassPath(Class<?> klass) {
		String classPath = null;
		if (klass != null) {
			classPath = klass.getPackage().getName().replace(".", File.separator);
		}
		
		return classPath;
	}
	
	/**
	 * Returns true if the method is getter otherwise false.
	 * 
	 * @param method
	 * @return
	 */
	public static boolean isGetter(Method method) {
		if (method != null) {
			if (!(method.getName().startsWith("get") || method.getName().startsWith("is"))) {
				return false;
			} else if (method.getParameterTypes().length != 0) {
				return false;
			} else if (void.class.equals(method.getReturnType())) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Returns true if the method is setter otherwise false.
	 * 
	 * @param method
	 * @return
	 */
	public static boolean isSetter(Method method) {
		if (method != null) {
			if (!method.getName().startsWith("set")) {
				return false;
			} else if (method.getParameterTypes().length != 1) {
				return false;
			} else if (!void.class.equals(method.getReturnType())) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String toTitleCase(CharSequence string) {
		return string.toString();
	}
	
}
