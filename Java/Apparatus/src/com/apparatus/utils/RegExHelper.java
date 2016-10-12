package com.apparatus.utils;

public class RegExHelper {

	public static final String URL_REGEX = "/^(http|https|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$/i";
	public static final String REGEX_USERNAME = "/^[a-z0-9_-]{3,16}$/";
	public static final String REGEX_PASSWORD = "/^[a-z0-9_-]{6,18}$/";
	public static final String REGEX_HEXA = "/^#?([a-f0-9]{6}|[a-f0-9]{3})$/";
	// public static final String REGEX_EMAIL =
	// "/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/";
	// public static final String REGEX_URL =
	// "/^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/";
	// public static final String REGEX_IP_ADDRESS =
	// "/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/";
	// public static final String REGEX_HTML_TAG =
	// "/^<([a-z]+)([^<]+)*(?:>(.*)<\/\1>|\s+\/>)$/";

	public static boolean isValidExpression(String regexPattern, String urlString) {
		return (!StringHelper.isNullOrEmpty(urlString) && urlString.matches(regexPattern));
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Username Validation
		String urlString = "rohtashsingh";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(REGEX_USERNAME, urlString));
		urlString = "rohtash.singh";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(REGEX_USERNAME, urlString));

		// Password Validation
		urlString = "rohtashsingh";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(REGEX_PASSWORD, urlString));
		urlString = "rohtash.singh";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(REGEX_PASSWORD, urlString));

		// Hexa Validation
		urlString = "rohtashsingh";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(REGEX_HEXA, urlString));
		urlString = "rohtash.singh";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(REGEX_HEXA, urlString));

		// URL Validation
		urlString = "https://mbp-rohtash";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
		urlString = "https://mbp-rohtash/";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
		urlString = "http://mbp-rohtash/";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
		urlString = "www.mbp-rohtash.com";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
		urlString = "ftp://mbp-rohtash";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
		urlString = "mbp-rohtash.com";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
		urlString = "mbp-rohtash";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
		urlString = "mbp-rohtash";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
		urlString = "HTTPS://mbp-rohtash/";
		System.out.println(urlString + " is valid:" + RegExHelper.isValidExpression(URL_REGEX, urlString));
	}

}
