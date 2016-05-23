/***************************************************************************
 *  Copyright (C) Myrio - A Siemens Company 2006
 *
 *  The reproduction, transmission or use of this document  or its contents
 *  is not  permitted without  prior express written consent of Myrio.
 *  Offenders will be liable for damages. All rights, including  but not
 *  limited to rights created by patent grant or registration of a utility
 *  model or design, are reserved.
 *
 *  Myrio reserves the right to modify technical specifications and features.
 *
 *  Technical specifications and features are binding only insofar as they
 *  are specifically and expressly agreed upon in a written contract.
 *
 **************************************************************************/

package com.apparatus.testcases.xml;

import java.util.Date;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * DOCUMENT ME!
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @created 30/05/2007 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
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
	public void startElement(String uri, String localName, String rawName,
			Attributes attributes) {

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
			String file = TestDOMParser.class.getResource("TestParser.xml")
					.toExternalForm();
			parser.setContentHandler(testSAXParser);
			parser.setErrorHandler(testSAXParser);
			parser.parse(file);
			System.out.println("End Time: " + new Date());
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
} // end class TestSAXParser
