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
package com.rslakra.http;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/07/2017 10:00:53 AM
 */
public final class HttpClient {
	
	private boolean asynchronous;
	private HttpRequest httpRequest;
	
	/**
	 * 
	 * @param asynchronous
	 */
	public HttpClient(final boolean asynchronous) {
		this.asynchronous = asynchronous;
	}
	
	/**
	 * 
	 */
	public HttpClient() {
		this(false);
	}
	
	/**
	 * Returns the asynchronous.
	 * 
	 * @return
	 */
	public boolean isAsynchronous() {
		return asynchronous;
	}
	
	/**
	 * The asynchronous to be set.
	 * 
	 * @param asynchronous
	 */
	public void setAsynchronous(boolean asynchronous) {
		this.asynchronous = asynchronous;
	}
	
	/**
	 * Returns the httpRequest.
	 * 
	 * @return
	 */
	public HttpRequest getHttpRequest() {
		return httpRequest;
	}
	
	/**
	 * The httpRequest to be set.
	 * 
	 * @param httpRequest
	 */
	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	
	/**
	 * 
	 * @param httpMethod
	 * @param urlString
	 */
	public void execute(String httpMethod, String urlString) {
//		if(getHttpRequest() != null) {
//			getHttpRequest().execute();
//		}
	}
	
	/**
	 * 
	 * @param urlString
	 */
	public void execute(String urlString) {
		execute(null, urlString);
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
}
