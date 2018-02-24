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

import java.util.Map;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/07/2017 10:00:42 AM
 */
public interface HttpRequest {
	
	/**
	 * Returns the headers.
	 * 
	 * @return
	 */
	public Map<String, String> getHeaders();
	
	/**
	 * The headers to be set.
	 * 
	 * @param headers
	 */
	public void setHeaders(Map<String, String> headers);
	
	/**
	 * Returns the parameters.
	 * 
	 * @return
	 */
	public Map<String, Object> getParameters();
	
	/**
	 * The parameters to be set.
	 * 
	 * @param parameters
	 */
	public void setParameters(Map<String, Object> parameters);
	
	/**
	 * Executes the request and calls the <code>responseHandler</code>, it not
	 * null.
	 * 
	 * @param responseHandler
	 */
	public void execute(ResponseHandler responseHandler);
	
	/**
	 * Executes the request.
	 */
	public void execute();
	
}
