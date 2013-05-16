/******************************************************************************
 * Copyright (C) Devamatre Technologies 2009
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.core.config;

import java.util.Locale;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Loads the config.properties files.
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 14/04/2006 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class Config {

	// Common Properties
	private final String ROOT_DIR = System.getProperty("user.dir");

	// Logger Properties
	private static final String TAG_CONFIG_FILE = "Config.properties";
	private static final String TAG_LOG4J_ON = "log4jOn";
	private static final String TAG_LOG4J_LEVEL = "log4jLevel";
	private static final String TAG_LOG4J_PATTERN = "log4jPattern";
	private static final String TAG_FORCE_LOG_TO_CONSOLE = "forceLogToConsole";

	// Locale Properties
	private static final String TAG_LANGUAGE = "Language";
	private static final String TAG_COUNTRY = "Country";
	private static final String TAG_VARIANT = "Variant";

	// Extra Attributes
	private static final String TAG_ENABLE_RMI_SERVER = "EnableRMIServer";
	private static final String TAG_REMOTE_LOG_HOST = "RemoteLogHost";

	private static final String TAG_DEFAULT_REMOTE_HOST = "localhost";
	private static final String TAG_DEFAULT_LEVEL = "WARN";
	private static final String TAG_DEFAULT_PATTERN = "%r [%t] %-5p [%-22.22c{1}:%L] - %m%n";

	/**
	 * note: the following instantiation should be the last in field block as we
	 * may need values of fields defined above to be initialized first however,
	 * the logger line must come after Config instance instantiation, b/c logger
	 * initialization requires reading config parameter from config file.
	 */
	private static Config instance = new Config();

	private Locale locale;
	private Properties properties;

	// Logger Attributes with default values
	private boolean log4jOn;
	private String log4jLevel;
	private String log4jPattern;
	private boolean forceLogToConsole;

	// Extra Attributes
	/**
	 * ATH Requirement: Do not remove --- This flag is used to distinguish
	 * between production operation (false) and ATH operation (true).
	 * 
	 * Formerly this flag was specified as a Config.property, but this is now
	 * set via a JVM argument.
	 */
	private boolean enableRMIServer;
	private String remoteLogHost;

	// Locale Attributes with default values
	private String language;
	private String country;
	private String variant;

	/**
	 * Creates a new Config object.
	 */
	private Config() {
		load(); // load property file
		init(); // initialize the attributes of the class.
	}

	public static Config getInstance() {
		return instance;
	}

	private void load() {
		properties = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(ROOT_DIR + "/" + TAG_CONFIG_FILE);
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	private void init() {
		// Initalized logger attributes
		log4jOn = getBoolean(TAG_LOG4J_ON, true);
		log4jLevel = properties.getProperty(TAG_LOG4J_LEVEL, TAG_DEFAULT_LEVEL);
		log4jPattern = properties.getProperty(TAG_LOG4J_PATTERN,
				TAG_DEFAULT_PATTERN);
		forceLogToConsole = getBoolean(TAG_FORCE_LOG_TO_CONSOLE, true);

		// Initalized Locale attributes
		// Figure out our locale. Note this is only for legacy purposes;
		// supported
		// language information is now supposed to come from global install
		// document.
		// This locale information is only here as a fallback.
		language = properties.getProperty(TAG_LANGUAGE, "en");
		country = properties.getProperty(TAG_COUNTRY, "US");
		variant = properties.getProperty(TAG_VARIANT, "");
		locale = new Locale(language, country, variant);
		//
		// try {
		// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// dbf.newDocumentBuilder();
		// }
		// catch (FactoryConfigurationError e) {
		// }
		// catch (ParserConfigurationException e) {
		// }

		enableRMIServer = getBoolean(TAG_ENABLE_RMI_SERVER, false);
		remoteLogHost = properties.getProperty(TAG_REMOTE_LOG_HOST,
				TAG_DEFAULT_REMOTE_HOST);
	} // end method initAfterLoad

	/**
	 * The method returns the boolean value for the property.
	 * 
	 * @param propName
	 *            which value to find in properties.
	 * @param bDefault
	 *            Default value for the property.
	 * @return value of property. If not found, the default value will be
	 *         returned.
	 */
	public boolean getBoolean(String propName, boolean bDefault) {
		boolean result = bDefault;
		String str = properties.getProperty(propName);
		if (str != null) {
			str = str.trim();
			result = Boolean.valueOf(str).booleanValue();
		}
		return result;
	}

	//
	// /**
	// * Returns $param.name$
	// *
	// * @param propName
	// * @param iDefault
	// *
	// * @return
	// */
	// public int getInt(String propName, int iDefault) {
	// int result = iDefault;
	// String str = properties.getProperty(propName);
	//
	// if (str != null) {
	//
	// try {
	// result = Integer.parseInt(str);
	// }
	// catch (NumberFormatException nfe) {
	// System.out.println("nfe : " + nfe);
	// }
	// }
	//
	// return result;
	// }
	//
	// private long getLong(String propName, long lDefault) {
	// long result = lDefault;
	// String str = properties.getProperty(propName);
	//
	// if (str != null) {
	//
	// try {
	// result = Long.parseLong(str);
	// }
	// catch (Exception e) {
	// }
	// }
	//
	// return result;
	// }

	/**
	 * Figure out our locale. Note this is only for legacy purposes; supported
	 * language information is now supposed to come from global install
	 * document. This locale information is only here as a fallback.
	 * 
	 * @return * @return
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @return Returns the log4jLevel.
	 */
	public String getLog4jLevel() {
		return log4jLevel;
	}

	/**
	 * @return Returns the log4jOn.
	 */
	public boolean isLog4jOn() {
		return log4jOn;
	}

	/**
	 * @return Returns the log4jPattern.
	 */
	public String getLog4jPattern() {
		return log4jPattern;
	}

	/**
	 * @return Returns the variant.
	 */
	public String getVariant() {
		return variant;
	}

	/**
	 * @return Returns the enableRMIServer.
	 */
	public boolean isEnableRMIServer() {
		return enableRMIServer;
	}

	/**
	 * @return Returns the remoteLogHost.
	 */
	public String getRemoteLogHost() {
		return remoteLogHost;
	}

	/**
	 * @return Returns the forceLogToConsole.
	 */
	public boolean isForceLogToConsole() {
		return forceLogToConsole;
	}

	/**
	 * @return Returns the rOOT_DIR.
	 */
	public String getROOT_DIR() {
		return ROOT_DIR;
	}
}