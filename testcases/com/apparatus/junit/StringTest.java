package com.apparatus.junit;

import com.apparatus.StringSplitter;
import com.apparatus.utils.ObjectHelper;
import com.apparatus.utils.StringHelper;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since May 12, 2015 10:46:32 AM
 */
public class StringTest {
	
	/**
	 * 
	 * @param oldVersion
	 * @param newVersion
	 * @return
	 */
	public static boolean isNewVersion(String oldVersion, String newVersion) {
		return (!StringHelper.isNullOrEmpty(newVersion) && newVersion.compareTo(oldVersion) > 0);
	}
	
	/**
	 * 
	 */
	public static void testIsNewVersion() {
		String oldVersion = "";
		String newVersion = "11.0.20150413.101148";
		System.out.println("isNewVersion:" + isNewVersion(oldVersion, newVersion));
		
	}
	
	/**
	 * 
	 */
	public static void testStringSplitter() {
		System.out.println("Testing NULL string.");
		String[] tokens = null;
		StringSplitter stringSplitter = new StringSplitter(',');
		tokens = stringSplitter.split();
		ObjectHelper.print(tokens);
		
		String string = ",,,one,two,three,,,,five,";
		System.out.println();
		System.out.println("Testing Valid string.");
		// stringSplitter = new StringSplitter(string, ',');
		// tokens = stringSplitter.split();
		tokens = stringSplitter.split(string);
		ObjectHelper.print(tokens);
		
		System.out.println("Testing Valid string while excluding empty strings.");
		stringSplitter = new StringSplitter(string, ',', true);
		tokens = stringSplitter.split();
		// tokens = stringSplitter.split(string, true);
		ObjectHelper.print(tokens);
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testIsNewVersion();
		testStringSplitter();
	}
	
}
