package com.rslakra.servers;

public interface Constants {
	interface Keys {
		String CACHE_CONTROL = "Cache-Control";
		String PRAGMA = "Pragma";
		String EXPIRES = "Expires";
		
		String USER_NAME = "userName";
		String PASSWORD = "password";
		
		String JSESSIONID = "JSESSIONID";
		
	}
	
	interface Values {
		String CACHE_CONTROL = "no-cache, no-store, must-revalidate";
		String NO_CACHE = "no-cache";
		String TEXT_HTML = "text/html";
		String TEXT_HTML_UTF8 = "text/html;charset=UTF-8";
		
		String USER_NAME = "admin";
		String PASSWORD = "password";
	}
}
