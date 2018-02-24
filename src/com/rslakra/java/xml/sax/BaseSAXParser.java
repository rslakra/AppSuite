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
package com.rslakra.java.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.rslakra.logger.LogManager;
import com.rslakra.logger.Logger;

/**
 * BaseSAXParser.java
 * 
 * The <code>BaseSAXParser</code> is the base class of all XML handlers.
 * 
 * 
 * This the base class for handling xml files.
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @since 2008/09/11
 */
public abstract class BaseSAXParser extends DefaultHandler {

	/* logger */
	private final Logger logger = LogManager.getLogger(BaseSAXParser.class);

	/* buffer for collecting text */
	private final StringBuilder buffer = new StringBuilder();

	/**
	 * 
	 */
	public BaseSAXParser() {
		resetBuffer();
	}

	public abstract void parse();

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		logger.debug("startElement(), uri:" + uri + ", localName:" + localName + ", qName:" + qName + ", attributes:"
				+ attributes);
		resetBuffer();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		logger.debug("endElement(), uri : " + uri + ", localName:" + localName + ", qName : " + qName);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public final void characters(char[] chars, int start, int length) throws SAXException {
		buffer.append(chars, start, length);
	}

	/**
	 * Returns the current buffer.
	 * 
	 * @return
	 */
	public final String getBuffer() {
		return buffer.toString();
	}

	/**
	 * Returns the current buffer.
	 * 
	 * @return
	 */
	protected final void resetBuffer() {
		buffer.setLength(0);
	}
}