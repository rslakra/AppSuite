package com.apparatus.junit.utils;

import com.apparatus.utils.LogHelper;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since May 12, 2015 10:46:28 AM
 */
public class DebugUtilTest {
	
	public static void main(String[] args) {
		Long objLong = new Long(0L);
		System.out.println(objLong.toString());
		LogHelper.printRequest(null, false);
	}
	
}
