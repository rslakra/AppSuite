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

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * DOCUMENT ME!
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @created 30/05/2007 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestDOMParser {

	/**
	 * DOCUMENT ME!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			DOMParser parser = new DOMParser();
			String file = TestDOMParser.class.getResource("TestParser.xml")
					.toExternalForm();
			System.out.println("Path : " + file);
			parser.parse(file);
			Document doc = parser.getDocument();
			NodeList nodes = doc.getElementsByTagName("name");
			System.out
					.println("There are " + nodes.getLength() + "  elements.");

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

} // end class TestDOMParser
