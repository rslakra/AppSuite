/******************************************************************************
 * Copyright (C) Devamatre/Devmatre Inc. 2009
 * 
 * This code is licensed to Devamatre under one or more contributor license
 * agreements. The reproduction, transmission or use of this code or the
 * snippet is not permitted without prior express written consent of Devamatre.
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the
 * offenders will be liable for any damages. All rights, including but not
 * limited to rights created by patent grant or registration of a utility model
 * or design, are reserved. Technical specifications and features are binding
 * only insofar as they are specifically and expressly agreed upon in a written
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 * http://www.devamatre.com/licenses/license.txt.
 * 
 * Devamatre reserves the right to modify the technical specifications and or
 * features without any prior notice.
 *****************************************************************************/
package com.apparatus.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import com.apparatus.utils.StringHelper;

/**
 * Loads the config.properties files.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
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
	
	public static final String DELIMITER = "=";
	public static final String SERVER_SCHEME = "server.scheme";
	public static final String SERVER_HOST = "server.host";
	public static final String SERVER_PORT = "server.port";
	public static final String ENABLE_CHARLES_PROXY = "enableCharlesProxy";
	public static final String FORCE_OVERRIDE_FOLDERS = "forceOverrideFolders";
	public static final String SERVER_CONNECTION_TIMEOUT = "server.connectionTimeout";
	public static final String SERVER_READ_TIMEOUT = "server.readTimeout";
	public static final String URL_INTERCEPTING_ENABLED = "urlInterceptingEnabled";
	
	/* configProperties */
	private static Properties configProperties = new Properties();
	
	/**
	 * note: the following instantiation should be the last in field block as we
	 * may need values of fields defined above to be initialized first however,
	 * the logger line must come after Config instance instantiation, b/c logger
	 * initialization requires reading config parameter from config file.
	 */
	private static Config instance;
	
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
	
	/**
	 * Returns the singleton instance of this class.
	 * 
	 * @return
	 */
	public static Config getInstance() {
		if(instance == null) {
			synchronized(Config.class) {
				if(instance == null) {
					instance = new Config();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 
	 */
	private void load() {
		properties = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(ROOT_DIR + "/" + TAG_CONFIG_FILE);
			properties.load(inStream);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(inStream != null) {
				try {
					inStream.close();
				} catch(Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
	private void init() {
		// Initalized logger attributes
		log4jOn = getBoolean(TAG_LOG4J_ON, true);
		log4jLevel = properties.getProperty(TAG_LOG4J_LEVEL, TAG_DEFAULT_LEVEL);
		log4jPattern = properties.getProperty(TAG_LOG4J_PATTERN, TAG_DEFAULT_PATTERN);
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
		remoteLogHost = properties.getProperty(TAG_REMOTE_LOG_HOST, TAG_DEFAULT_REMOTE_HOST);
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
		if(str != null) {
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
	
	/**
	 * Merges the specified properties into the configuration properties.
	 * 
	 * @param properties
	 */
	public static void mergeProperties(Properties properties) {
		System.out.println("Merging properties");
		Config.configProperties.putAll(properties);
	}
	
	/**
	 * Returns the string value for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return Config.configProperties.getProperty(key);
	}
	
	/**
	 * Returns the string value for the specified key. If no value is available,
	 * the default value is returned.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getValue(String key, String defaultValue) {
		String value = Config.configProperties.getProperty(key);
		if(StringHelper.isNullOrEmpty(value)) {
			value = defaultValue;
		}
		
		return value;
	}
	
	/**
	 * Returns an int value for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public static int getIntValue(String key) {
		return getIntValue(key, -1);
	}
	
	/**
	 * Returns an int value for the specified key. If no value is available, the
	 * default value is returned.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getIntValue(String key, int defaultValue) {
		String value = getValue(key);
		return (StringHelper.isNullOrEmpty(value) ? defaultValue : Integer.parseInt(value));
	}
	
	/**
	 * Returns the boolean value for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public static boolean getBooleanValue(String key) {
		return getBooleanValue(key, false);
	}
	
	/**
	 * Returns the boolean value for the specified key. If no value is
	 * available, the default value is returned.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanValue(String key, boolean defaultValue) {
		String value = getValue(key);
		return (StringHelper.isNullOrEmpty(value) ? defaultValue : Boolean.valueOf(value));
	}
	
	/**
	 * Returns the long value for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public static long getLongValue(String key) {
		return getLongValue(key, -1l);
	}
	
	/**
	 * Returns the long value for the specified key. If no value is available,
	 * the default value is returned.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static long getLongValue(String key, long defaultValue) {
		String value = getValue(key);
		return (StringHelper.isNullOrEmpty(value) ? defaultValue : Long.parseLong(value));
	}
	
	/**
	 * Returns true if the Charles proxy is enabled otherwise false.
	 * 
	 * @return
	 */
	public static boolean isCharlesProxyEnabled() {
		return getBooleanValue(ENABLE_CHARLES_PROXY, false);
	}
	
	/**
	 * Returns the scheme name supported by the server.
	 * 
	 * @return
	 */
	public static String getServerScheme() {
		return getValue(SERVER_SCHEME);
	}
	
	/**
	 * Returns the name of the server.
	 * 
	 * @return
	 */
	public static String getServerHost() {
		return getValue(SERVER_HOST);
	}
	
	/**
	 * Returns the default server port.
	 * 
	 * @return
	 */
	public static String getServerPort() {
		return getValue(SERVER_PORT);
	}
	
	/**
	 * Returns true if the URL Intercepting is enabled otherwise false.
	 * 
	 * @return
	 */
	public static boolean isUrlInterceptingEnabled() {
		return getBooleanValue(URL_INTERCEPTING_ENABLED, false);
	}
}