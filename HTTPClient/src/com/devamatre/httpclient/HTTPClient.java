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
package com.devamatre.httpclient;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2017-09-28 12:06:28 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface HTTPClient {

	String SCHEMA_HTTP = "http".intern();
	String SCHEMA_HTTPS = "https".intern();
	String HTTP_VERSION = "HTTP/1.1".intern();
	int STATUS_CODE_OK = 200;

	int HTTP_CONNECTION_TIMEOUT_SECONDS = 45;
	int HTTP_READ_TIMEOUT_SECONDS = 45;

	/**
	 * Returns the asynchronous.
	 * 
	 * @return
	 */
	public boolean isAsynchronous();

	/**
	 * The asynchronous to be set.
	 * 
	 * @param asynchronous
	 */
	public void setAsynchronous(boolean asynchronous);

	/**
	 * Returns the httpRequest.
	 * 
	 * @return
	 */
	public HttpRequest getHttpRequest();

	/**
	 * The httpRequest to be set.
	 * 
	 * @param httpRequest
	 */
	public void setHttpRequest(HttpRequest httpRequest);

	/**
	 * 
	 * @param httpMethod
	 * @param urlString
	 */
	public void execute(String httpMethod, String urlString);

	/**
	 * 
	 * @param urlString
	 */
	public void execute(String urlString);

}
