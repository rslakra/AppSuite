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
package com.rslakra.java.net.http;

public interface Constants {
	
	String SCHEMA_HTTP = "http".intern();
	String SCHEMA_HTTPS = "https".intern();
	String HTTP_VERSION = "HTTP/1.1".intern();
	int STATUS_CODE_OK = 200;
	
	int HTTP_CONNECTION_TIMEOUT_SECONDS = 45;
	int HTTP_READ_TIMEOUT_SECONDS = 45;
	
	interface Headers {
		String ACCEPT = "Accept";
		String ACCEPT_ENCODING = "Accept-Encoding".intern();
		String ACCEPT_LANGUAGE = "Accept-Language".intern();
		String CONTENT_TYPE = "Content-Type".intern();
		String CONTENT_LENGTH = "Content-Length".intern();
		
		String EXPIRES = "Expires".intern();
		String PRAGMA = "Pragma".intern();
		String PRAGMA_PUBLIC = "public".intern();
		String CACHE_CONTROL = "Cache-Control".intern();
		String USER_AGENT = "User-Agent".intern();
		String CONTENT_DISPOSITION = "Content-Disposition".intern();
		
		String SET_COOKIE = "Set-Cookie".intern();
		String COOKIE = "Cookie".intern();
		String LOCATION = "Location".intern();
		String SERVER = "Server".intern();
		String TRANSFER_ENCODING = "Transfer-Encoding".intern();
	}
	
	interface HeaderValues {
		
		String ACCEPT_ALL = "*/*".intern();
		
		String ENCODING_GZIP_DEFLATE = "gzip, deflate".intern();
		
		String EN_US = "en-us".intern();
		String EN_US_Q = "en-US,en;q=0.5".intern();
		
		String FILE_NAME_EQUAL = "fileName=".intern();
		
		String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded".intern();
		String CONTENT_TYPE_FORM_URLENCODED_UTF8 = "application/x-www-form-urlencoded;charset=UTF-8".intern();
		String APPLICATION_JAVA_X_SERIALIZED_OBJECT = "application/x-java-serialized-object".intern();
		String MULTIPART_FORM_DATA = "multipart/form-data".intern();
		String CONTENY_TYPE_JSON = "application/json".intern();
		String PRAGMA_NO_CACHE = "no-cache".intern();
	}
	
	interface Methods {
		String HEAD = "HEAD".intern();
		String GET = "GET".intern();
		String POST = "POST".intern();
		String OPTIONS = "OPTIONS".intern();
		String PUT = "PUT".intern();
		String DELETE = "DELETE".intern();
		String TRACE = "TRACE".intern();
	}
}