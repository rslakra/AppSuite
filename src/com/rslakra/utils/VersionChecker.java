package com.rslakra.utils;

import java.util.regex.Pattern;

/**
 * Compares the versions.
 * 
 * @author Rohtash Singh Lakra
 * @date 07/12/2017 03:06:28 PM
 */
public final class VersionChecker {
	
	private final boolean DEBUG = false;
	private String currentVersion;
	
	/**
	 * 
	 * @param currentVersion
	 */
	public VersionChecker(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	
	/**
	 * Formats the version.
	 * 
	 * @param version
	 * @param separator
	 * @param maxWidth
	 * @return
	 */
	public String versionFormatter(String version, String separator, int maxWidth) {
		String[] split = Pattern.compile(separator, Pattern.LITERAL).split(version);
		StringBuilder verBuilder = new StringBuilder();
		for(String str : split) {
			verBuilder.append(String.format("%" + maxWidth + 's', str));
		}
		
		if(DEBUG) {
			System.out.println("versionFormatter:" + verBuilder.toString());
		}
		return verBuilder.toString();
	}
	
	/**
	 * 
	 * @param version
	 * @return
	 */
	public String versionFormatter(String version) {
		return versionFormatter(version, ".", 8);
	}
	
	/**
	 * 
	 * @param aVersion
	 * @return
	 */
	public boolean isOlderThan(String aVersion) {
		String s1 = versionFormatter(aVersion);
		String s2 = versionFormatter(currentVersion);
		return (s1.compareTo(s2) > 0 ? true : false);
	}
	
	/**
	 * 
	 * @param aVersion
	 * @return
	 */
	public boolean isLessThan(String aVersion) {
		String s1 = versionFormatter(aVersion);
		String s2 = versionFormatter(currentVersion);
		return (s1.compareTo(s2) < 0 ? true : false);
	}
	
	/**
	 * 
	 * @param aVersion
	 * @param currentVersion
	 * @return
	 */
	public boolean isLessThanEqualTo(String aVersion, String currentVersion) {
		String s1 = versionFormatter(aVersion);
		String s2 = versionFormatter(currentVersion);
		return (s1.compareTo(s2) <= 0 ? true : false);
	}
	
	/**
	 * 
	 * @param aVersion
	 * @return
	 */
	public boolean isLessThanEqualTo(String aVersion) {
		return isLessThanEqualTo(aVersion, currentVersion);
	}
	
	/**
	 * 
	 * @param aVersion
	 * @return
	 */
	public boolean isGreaterThan(String aVersion) {
		String s1 = versionFormatter(aVersion);
		String s2 = versionFormatter(currentVersion);
		return (s1.compareTo(s2) > 0 ? true : false);
	}
	
	/**
	 * 
	 * @param aVersion
	 * @param currentVersion
	 * @return
	 */
	public boolean isGreaterThanEqualTo(String aVersion, String currentVersion) {
		String s1 = versionFormatter(aVersion);
		String s2 = versionFormatter(currentVersion);
		return (s1.compareTo(s2) >= 0 ? true : false);
	}
	
	/**
	 * 
	 * @param aVersion
	 * @return
	 */
	public boolean isGreaterThanEqualTo(String aVersion) {
		return isGreaterThanEqualTo(aVersion, currentVersion);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VersionChecker versionChecker = new VersionChecker("7.10");
		System.out.println("isOlderThan:" + versionChecker.isOlderThan("7.15"));
		System.out.println("isLessThan:" + versionChecker.isLessThan("7.15"));
		System.out.println("isLessThanEqualTo:" + versionChecker.isLessThanEqualTo("7.15"));
		System.out.println("isGreaterThanEqualTo:" + versionChecker.isGreaterThanEqualTo("7.15"));
		System.out.println("isGreaterThan:" + versionChecker.isGreaterThan("7.15"));
	}
	
}
