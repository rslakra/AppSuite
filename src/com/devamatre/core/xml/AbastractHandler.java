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
package com.devamatre.core.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * AbastractHandler.java
 * 
 * The <code>AbastractHandler</code> is the base class of all XML handlers.
 * 
 * 
 * This the base class for handling xml files.
 * 
 * @author Rohtash Singh (rohtash.singh@rsystems.com)
 * @since 2008/09/11
 */
public abstract class AbastractHandler extends DefaultHandler {
	
	protected StringBuffer buffer = null;

	public AbastractHandler() {
		buffer = new StringBuffer();
	}
	
	public abstract void show();

	/**
	 * 
	 */
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		// System.out.println("startElement(), uri : " + uri + ", localName:" + localName + ", qName : " + qName);
		buffer.setLength(0);
	}

	/**
	 * 
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// System.out.println("endElement(), uri : " + uri + ", localName:" + localName + ", qName : " + qName);
	}

	/**
	 * 
	 */
	public final void characters(char ch[], int start, int length) throws SAXException {
		buffer.append(ch, start, length);
	}
}