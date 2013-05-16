/******************************************************************************
 * Copyright (C) Devamatre Technologies 2009
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
package com.devamatre.core.lang.io;

import java.io.FilenameFilter;
import java.io.File;
import java.util.regex.Pattern;

/**
 * 
 * TODO: Enter description here ...
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class ExpressionFileFilter implements FilenameFilter {

	/** pattern */
	private Pattern pattern;

	/**
	 * 
	 * @param expression
	 */
	public ExpressionFileFilter(String expression) {
		pattern = Pattern.compile(expression);
		System.out.println("pattern: " + pattern);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	public boolean accept(File dir, String name) {
		System.out.println(dir + ", " + name);
		// return pattern.matcher(new File(name).getName()).matches();
		// return pattern.matcher(dir.getName()).matches();
		boolean result = pattern.matcher(name).matches();
		System.out.println("result: " + result);
		return result;
	}
}