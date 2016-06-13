package com.rslakra.servers;

public interface Constants {
	
	interface Keys {
		/* Headers */
		String CACHE_CONTROL = "Cache-Control";
		String PRAGMA = "Pragma";
		String EXPIRES = "Expires";
		
		String USER_NAME = "userName";
		String PASSWORD = "password";
		
		String JSESSIONID = "JSESSIONID";
		
	}
	
	interface Values {
		/* Header Values */
		String CACHE_CONTROL = "no-cache, no-store, must-revalidate";
		String NO_CACHE = "no-cache";
		
		/* Content Types */
		String TEXT_HTML = "text/html";
		String TEXT_HTML_UTF8 = "text/html; charset=UTF-8";
		String TEXT_XML = "text/xml";
		String APPLICATION_JSON = "application/json";
		String APPLICATION_JSON_UTF8 = "application/json; charset=UTF-8";
		String APPLICATION_PDF = "application/pdf";
	}
}
