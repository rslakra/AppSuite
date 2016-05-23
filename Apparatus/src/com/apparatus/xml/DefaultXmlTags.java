/******************************************************************************
 * Copyright (C) Devamatre/Devmatre Inc. 2009
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
package com.apparatus.xml;

/**
 * 
 * TODO: Enter description here ...
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DefaultXmlTags extends XmlTag {

	/** Default Tags */
	String TAG_ERROR = "Error";
	String TAG_ERROR_CODE = "ErrorCode";
	String TAG_ERROR_MESSAGE = "ErrorMessage";
	
	/** Default Buffer Size constant */
	int TAG_BUFFER_SIZE = 256;

	/** TAG_ENCODE_QUOTE */
	char[] TAG_ENCODE_QUOTE = "&quot;".toCharArray();

	/** TAG_ENCODE_AMP */
	char[] TAG_ENCODE_AMP = "&amp;".toCharArray();

	/** TAG_ENCODE_LT */
	char[] TAG_ENCODE_LT = "&lt;".toCharArray();

	/** TAG_ENCODE_GT */
	char[] TAG_ENCODE_GT = "&gt;".toCharArray();

	/** TAG_ENCODE_APOS */
	char[] TAG_ENCODE_APOS = "&apos;".toCharArray();
}