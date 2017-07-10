/**
 * 
 */
package com.rslakra.utils;

/**
 * @author Rohtash Singh Lakra
 * @created May 24, 2016 11:10:35 AM
 * 
 */
public class LogUtil {
	
	/**
	 * 
	 * @param message
	 */
	public static void debug(String message) {
		System.out.println(message);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public static void error(Throwable throwable) {
		System.err.println(throwable);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public static void debug(String message, Throwable throwable) {
		System.out.println(message);
		error(throwable);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void error(String message) {
		System.err.println(message);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public static void error(String message, Throwable throwable) {
		System.err.println(message);
		error(throwable);
	}
	
}
