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
package com.rslakra.testCases.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Rohtash Singh Lakra
 */
public class TestApacheHttpClasses {
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		final String urlString = "https://qawest.meetx.org/";
		
		CloseableHttpResponse httpResponse = null;
		
		try {
			HttpGet httpGet = new HttpGet(urlString);
			httpResponse = httpClient.execute(httpGet);
			/**
			 *
			 * The underlying HTTP connection is still held by the response
			 * object to allow the response content to be streamed directly from
			 * the network socket.
			 * In order to ensure correct deallocation of system resources the
			 * user MUST call CloseableHttpResponse#close() from a finally
			 * clause.
			 * Please note that if response content is not fully consumed the
			 * underlying connection cannot be safely re-used and will be shut
			 * down and discarded by the connection manager.
			 */
			
			try {
				System.out.println(httpResponse.getStatusLine());
				Header[] headers = httpGet.getAllHeaders();
				for (Header header : headers) {
					System.out.println(header.getName() + "=" + header.getValue());
				}
				
				HttpEntity httpEntity = httpResponse.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(httpEntity);
			} finally {
				if (httpResponse != null) {
					httpResponse.close();
				}
			}
			
			// HttpPost httpPost = new HttpPost(urlString);
			// List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			// nvps.add(new BasicNameValuePair("username", "vip"));
			// nvps.add(new BasicNameValuePair("password", "secret"));
			// httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			// CloseableHttpResponse response2 = httpclient.execute(httpPost);
			//
			// try {
			// System.out.println(response2.getStatusLine());
			// HttpEntity entity2 = response2.getEntity();
			// // do something useful with the response body
			// // and ensure it is fully consumed
			// EntityUtils.consume(entity2);
			// } finally {
			// response2.close();
			// }
		} finally {
			httpClient.close();
		}
	}
	
}
