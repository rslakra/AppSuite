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
package com.rslakra.core;

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
		String[] tokens = Pattern.compile(separator, Pattern.LITERAL).split(version);
		StringBuilder verBuilder = new StringBuilder();
		for (String token : tokens) {
			verBuilder.append(String.format("%" + maxWidth + 's', token));
		}
		
		if (DEBUG) {
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
	
}
