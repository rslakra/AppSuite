/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2022. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, 
 * 	  this list of conditions and the following disclaimer.
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
package com.rslakra.mimecraft;

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
			if (content.length() > 0) {
				content.append('&');
			}
			try {
				content.append(URLEncoder.encode(name, "UTF-8")).append('=').append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new AssertionError(e);
			}
			return this;
		}
		
		/** Create {@link FormEncoding} instance. */
		public FormEncoding build() {
			if (content.length() == 0) {
				throw new IllegalStateException("Form encoded body must have at least one part.");
			}
			return new FormEncoding(content.toString());
		}
	}
	
	private final byte[] data;
	
	private FormEncoding(String data) {
		try {
			this.data = data.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
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
