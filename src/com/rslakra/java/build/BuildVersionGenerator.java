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
package com.rslakra.java.build;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import com.devamatre.core.CoreHelper;
import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

/**
 * 
 * Build Supported Format.
 * 
 * release = major.minor[.build[.revision]], or
 * 
 * release = major.minor[.maintenance[.build]]
 * 
 * 0.0 - alpha, 0.1 - beta, 0.2 - release, 1.0 - final
 * 
 * Note:-
 * 
 * <pre>
 * Log4j.xml file is configured as parameter of the JVM.
 * -Dlog4j.configuration=log4j.xml
 * </pre>
 * 
 * @author Rohtash Singh
 * @version 1.0.0
 */
public class BuildVersionGenerator {

	/* logger */
	private static Logger log = LogManager.getLogger(BuildVersionGenerator.class);
	private static String BUILD_VERSION = "build.version";
	private static String PROPERTY_FILE = ".properties";
	private static String TXT_FILE = ".txt";
	private static String CREATED = "created";
	private static String RELEASE = "release";
	private static String MAJOR = "major";
	private static String MINOR = "minor";
	private static String BUILD = "build";
	private static String REVISION = "revision";

	/* build version */
	private Properties buildProps = new Properties();
	private StringBuilder buildVersion = new StringBuilder();

	/**
	 * Loads the build configuration file to know the build version.
	 */
	private BuildVersionGenerator() {
		load(null);
	}

	/**
	 * Loads current build version.
	 */
	private void load(String buildFilePath) {
		log.debug("+load(" + buildFilePath + ")");
		InputStream iStream = null;
		/* if no build file name provided, use default. */
		if (CoreHelper.isNullOrEmpty(buildFilePath)) {
			buildFilePath = "/" + BUILD_VERSION + PROPERTY_FILE;
		}
		log.debug("buildFilePath:" + buildFilePath);
		iStream = getClass().getResourceAsStream(buildFilePath);
		/* clear existing loaded build version details. */
		buildProps.clear();

		// check stream is initialized.
		if (null != iStream) {
			try {
				buildProps.load(iStream);
				log.debug("Build file [" + buildFilePath + "] loaded successfully!");
			} catch (IOException ex) {
				log.error("Error while lading buildFilePath:" + buildFilePath, ex);
				/* set default build version. */
				setDefaultBuildVersion();
			}
		}
		log.debug("-load()");
	}

	/**
	 * Sets the default build version.
	 */
	private void setDefaultBuildVersion() {
		buildProps.put(CREATED, new Date());
		buildProps.put(RELEASE, "D-v");
		buildProps.put(MAJOR, "0");
		buildProps.put(MINOR, "0");
		buildProps.put(BUILD, "0");
		buildProps.put(REVISION, "0");
	}

	/**
	 * Returns the current version of the build.
	 * 
	 * @return
	 */
	private String setBuildVersion() {
		// log.debug("+setBuildVersion()");

		/* update buildVersion */
		Enumeration<Object> props = buildProps.elements();
		for (int index = 0, size = buildProps.size(); props.hasMoreElements() && index < size; index++) {
			buildVersion.append(props.nextElement());
			/* append .(dot) only after v and before last number. */
			if (index > 0 && index < (size - 1)) {
				buildVersion.append(".");
			}
		}

		log.debug("setBuildVersion(), buildVersion:" + buildVersion.toString());
		return buildVersion.toString();
	}

	/**
	 * Returns the current version of the build.
	 * 
	 * @return
	 */
	public static String getBuildVersion() {
		return new BuildVersionGenerator().setBuildVersion();
	}

	/*
	 * public static String getTimestampINHex() { if (minor == null) { new
	 * BuildVersionUtil(); }
	 * 
	 * String[] tokens = buildNumber.split("\\."); int a =
	 * Integer.parseInt(tokens[0]); int b = Integer.parseInt(tokens[1]); int c =
	 * (a+b)/400;
	 * 
	 * String hex = Integer.toHexString(c); return hex; }
	 */

	// public static String getVaultArchiveVersion() {
	// if (minor == null) {
	// new BuildVersionGenerator();
	// }
	//
	// StringBuilder vBuilder = new StringBuilder();
	// vBuilder.append(major + ".");
	// vBuilder.append(minor + ".");
	// String[] tokens = buildVersion.split("\\.");
	//
	// int a = Integer.parseInt(tokens[0]);
	// String date = Integer.toHexString((a / 400));
	// vBuilder.append(date + ".");
	//
	// int b = Integer.parseInt(tokens[1]);
	// String time = Integer.toHexString(b / 4);
	// vBuilder.append(time);
	// return vBuilder.toString();
	// }

	public static void main(String[] args) {
		System.out.println(BuildVersionGenerator.getBuildVersion());
		// System.out.println(BuildVersionGenerator.getVaultArchiveVersion());
	}
}