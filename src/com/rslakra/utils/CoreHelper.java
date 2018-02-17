/**
 * 
 */
package com.rslakra.utils;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author Rohtash Singh Lakra
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
		if(instance == null) {
			synchronized(CoreHelper.class) {
				if(instance == null) {
					instance = new CoreHelper();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 
	 * @param klass
	 * @return
	 */
	public static String getClassPath(Class<?> klass) {
		String classPath = null;
		if(klass != null) {
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
		if(method != null) {
			if(!(method.getName().startsWith("get") || method.getName().startsWith("is"))) {
				return false;
			} else if(method.getParameterTypes().length != 0) {
				return false;
			} else if(void.class.equals(method.getReturnType())) {
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
		if(method != null) {
			if(!method.getName().startsWith("set")) {
				return false;
			} else if(method.getParameterTypes().length != 1) {
				return false;
			} else if(!void.class.equals(method.getReturnType())) {
				return false;
			}
		}
		
		return true;
	}
	
}
