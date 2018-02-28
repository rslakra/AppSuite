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
import java.io.InputStream;
import java.io.OutputStream;

public final class Utils {
	private Utils() {
		// No instances.
	}
	
	static void copyStream(InputStream in, OutputStream out, byte[] buffer) throws IOException {
		int count;
		while((count = in.read(buffer)) != -1) {
			out.write(buffer, 0, count);
		}
	}
	
	static void isNotNull(Object obj, String message) {
		if(obj == null) {
			throw new IllegalStateException(message);
		}
	}
	
	static void isNull(Object obj, String message) {
		if(obj != null) {
			throw new IllegalStateException(message);
		}
	}
	
	static void isNotEmpty(String thing, String message) {
		isNotNull(thing, message);
		if("".equals(thing.trim())) {
			throw new IllegalStateException(message);
		}
	}
	
	static void isNotZero(int value, String message) {
		if(value != 0) {
			throw new IllegalStateException(message);
		}
	}
}