package com.rslakra.java.vm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-22 07:37:36 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class DevaMatreClassLoader extends ClassLoader {

	private static final int BUFFER_SIZE = 8192;

	public DevaMatreClassLoader() {
		super(DevaMatreClassLoader.class.getClassLoader());
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
		Class klass = findLoadedClass(className);
		if (klass != null) {
			return klass;
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
			klass = defineClass(className, classBytes, 0, classBytes.length);
			if (resolve) {
				resolveClass(klass);
			}
		} catch (SecurityException e) {
			// loading core java classes such as java.lang.String
			// is prohibited, throws java.lang.SecurityException.
			// delegate to parent if not allowed to load class
			klass = super.loadClass(className, resolve);
		}

		return klass;
	}

	private static void log(String s) {
		System.out.println(s);
	}
}