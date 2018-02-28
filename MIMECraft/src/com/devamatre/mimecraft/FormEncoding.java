/******************************************************************************
 * Copyright (C) Devamatre Inc 2009
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.mimecraft;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

/**
 * <a href="http://www.w3.org/MarkUp/html-spec/html-spec_8.html#SEC8.2.1">HTML
 * 2.0</a>-compliant
 * form data.
 */
public final class FormEncoding implements Part {
	private static final Map<String, String> HEADERS = Collections.singletonMap("Content-Type", "application/x-www-form-urlencoded");
	
	/** Fluent API to build {@link FormEncoding} instances. */
	public static class Builder {
		private final StringBuilder content = new StringBuilder();
		
		/** Add new key-value pair. */
		public Builder add(String name, String value) {
			if(content.length() > 0) {
				content.append('&');
			}
			try {
				content.append(URLEncoder.encode(name, "UTF-8")).append('=').append(URLEncoder.encode(value, "UTF-8"));
			} catch(UnsupportedEncodingException e) {
				throw new AssertionError(e);
			}
			return this;
		}
		
		/** Create {@link FormEncoding} instance. */
		public FormEncoding build() {
			if(content.length() == 0) {
				throw new IllegalStateException("Form encoded body must have at least one part.");
			}
			return new FormEncoding(content.toString());
		}
	}
	
	private final byte[] data;
	
	private FormEncoding(String data) {
		try {
			this.data = data.getBytes("UTF-8");
		} catch(UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unable to convert input to UTF-8: " + data, e);
		}
	}
	
	@Override
	public Map<String, String> getHeaders() {
		return HEADERS;
	}
	
	@Override
	public void writeBodyTo(OutputStream stream) throws IOException {
		stream.write(data);
	}
}