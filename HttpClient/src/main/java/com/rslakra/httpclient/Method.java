/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2018. All rights reserved.
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
package com.rslakra.httpclient;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2017-09-28 12:06:28 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Method {

	/** HEAD */
	HEAD("HEAD"),
	/** GET */
	GET("GET"),
	/** POST */
	POST("POST"),
	/** OPTIONS */
	OPTIONS("OPTIONS"),
	/** PUT */
	PUT("PUT"),
	/** DELETE */
	DELETE("DELETE"),
	/** TRACE */
	TRAC("TRACE"),;

	private String name;

	/**
	 * 
	 * @param name
	 */
	private Method(final String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns true if the request is GET otherwise false.
	 * 
	 * @param urlConnection
	 * @return
	 */
	public static boolean isGetRequest(Method requestMethod) {
		return (Method.GET == requestMethod);
	}

	/**
	 * Returns true if the request is GET otherwise false.
	 * 
	 * @param urlConnection
	 * @return
	 */
	public static boolean isGetRequest(String requestMethod) {
		return isGetRequest(Method.valueOf(requestMethod));
	}

	/**
	 * Returns true if the request is GET otherwise false.
	 * 
	 * @param urlConnection
	 * @return
	 */
	public static boolean isPostRequest(String requestMethod) {
		return (Method.POST == Method.valueOf(requestMethod));
	}
}
