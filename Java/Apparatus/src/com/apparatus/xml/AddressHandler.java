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

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA. User: rohtash.singh Date: Sep 4, 2006 Time: 2:09:06
 * PM To change this template use File | Settings | File Templates.
 */
public class AddressHandler extends DefaultHandler {
	/* The Tags used in MainMenu.xml file */
	private static final String TAG_ADDRESSES = "Addresses";
	private static final String TAG_ADDRESS = "Address";
	private static final String TAG_STREET = "Street";
	private static final String TAG_CITY = "City";
	private static final String TAG_STATE = "State";
	private static final String TAG_PIN_CODE = "PinCode";
	private static final String TAG_COUNTRY = "Country";
	// attributes of main menu.
	Address address;
	// buffer for collecting text
	private final StringBuffer strBuffer = new StringBuffer();
	private ArrayList<Address> addresses;
	private boolean done = false;

	/**
	 * Constructor
	 */
	public AddressHandler() {
		super();
	}

	/**
	 * This method parses the xml file contents.
	 * 
	 * @param uri
	 * @param localName
	 * @param qName
	 * @param attributes
	 * @throws org.xml.sax.SAXException
	 * 
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
	 *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// reset for each menu and menu item
		strBuffer.setLength(0);
		// check for each tag.
		if (TAG_ADDRESSES.equals(qName)) {
			addresses = new ArrayList<Address>();
		} else if (TAG_ADDRESS.equals(qName)) {
			reset(); // reset
			address = new Address();
		}
	}

	/**
	 * 
	 * 
	 * @param uri
	 * @param localName
	 * @param qName
	 * @throws SAXException
	 * 
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// check for each tag.
		if (TAG_ADDRESSES.equals(qName)) {
			done = true;
		} else if (TAG_ADDRESS.equals(qName)) {
			addresses.add(address);
		} else if (TAG_STREET.equals(qName)) {
			address.setStreet(strBuffer.toString());
		} else if (TAG_CITY.equals(qName)) {
			address.setCity(strBuffer.toString());
		} else if (TAG_STATE.equals(qName)) {
			address.setState(strBuffer.toString());
		} else if (TAG_PIN_CODE.equals(qName)) {
			address.setPinCode(strBuffer.toString());
		} else if (TAG_COUNTRY.equals(qName)) {
			address.setCountry(strBuffer.toString());
		}
	}

	/**
	 * 
	 * @param chars
	 * @param start
	 * @param length
	 * @throws SAXException
	 */
	public void characters(char[] chars, int start, int length)
			throws SAXException {

		// append data in strBuffer
		strBuffer.append(String.valueOf(chars, start, length));
	}

	/**
     *
     */
	private void reset() {
		if (done) {
			System.out.println("done:" + done);
			addresses = null;
		}
		address = null;
	}

	public ArrayList<Address> getAddresses() {
		return done ? addresses : null;
	}

	public static void main(String[] args) {
		AddressHandler handler = new AddressHandler();
		String file = "C:/Data/Rohtash/Examples/Java/Examples/src/org/crocus/corm/xml/Address.xml";
		SAXParser parser = null;
		// xml parsing
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
			try {
				System.out.println("file:" + file);
				parser.parse(new FileInputStream(file), handler);
				ArrayList<Address> list = handler.getAddresses();
				System.out.println("list.size:" + list.size());
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Address add = (Address) itr.next();
					System.out.println("Street:" + add.getStreet());
					System.out.println("City:" + add.getCity());
					System.out.println("State:" + add.getState());
					System.out.println("PinCode:" + add.getPinCode());
					System.out.println("Country:" + add.getCountry());
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
}