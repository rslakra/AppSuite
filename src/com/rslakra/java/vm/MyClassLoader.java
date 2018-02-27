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
package com.rslakra.java.vm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2010-02-26 09:23:41 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyClassLoader extends ClassLoader {

	private static final int BUFFER_SIZE = 8192;

	public MyClassLoader() {
		super(MyClassLoader.class.getClassLoader());
	}

	/**
	 * 
	 * @param className
	 * @param resolve
	 * @return
	 * @throws ClassNotFoundException
	 * @see java.lang.ClassLoader#loadClass(java.lang.String, boolean)
	 */
	protected synchronized Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
		log("Loading class: " + className + ", resolve: " + resolve);

		// 1. is this class already loaded?
		Class<?> _Class = findLoadedClass(className);
		if (_Class != null) {
			return _Class;
		}

		// 2. get class file name from class name
		String clsFile = className.replace('.', '/') + ".class";

		// 3. get bytes for class
		byte[] classBytes = null;
		try {
			InputStream in = getResourceAsStream(clsFile);
			byte[] buffer = new byte[BUFFER_SIZE];
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int length = -1;
			while ((length = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
				out.write(buffer, 0, length);
			}
			classBytes = out.toByteArray();
		} catch (IOException e) {
			log("ERROR loading class file: " + e);
		}

		if (classBytes == null) {
			throw new ClassNotFoundException("Cannot load class: " + className);
		}

		// 4. turn the byte array into a Class
		try {
			_Class = defineClass(className, classBytes, 0, classBytes.length);
			if (resolve) {
				resolveClass(_Class);
			}
		} catch (SecurityException e) {
			// loading core java classes such as java.lang.String
			// is prohibited, throws java.lang.SecurityException.
			// delegate to parent if not allowed to load class
			_Class = super.loadClass(className, resolve);
		}

		return _Class;
	}

	private static void log(String s) {
		System.out.println(s);
	}
}