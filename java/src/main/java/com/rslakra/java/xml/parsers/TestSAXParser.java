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
package com.rslakra.java.xml.parsers;

import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version 05/30/2007
 */
public class TestSAXParser extends DefaultHandler {

	/** tagCount */
	int tagCount = 0;

	/**
	 * DOCUMENT ME!
	 *
	 * @param uri
	 * @param localName
	 * @param rawName
	 * @param attributes
	 */
	public void startElement(String uri, String localName, String rawName, Attributes attributes) {

		if (rawName.equals("name")) {
			tagCount++;
		}
	}

	/**
	 * DOCUMENT ME!
	 */
	public void endDocument() {
		System.out.println("There are " + tagCount + " <name> elements.");
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			TestSAXParser testSAXParser = new TestSAXParser();
			SAXParser parser = new SAXParser();
			String file = TestDOMParser.class.getResource("TestParser.xml").toExternalForm();
			parser.setContentHandler(testSAXParser);
			parser.setErrorHandler(testSAXParser);
			parser.parse(file);
			System.out.println("End Time: " + new Date());
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
