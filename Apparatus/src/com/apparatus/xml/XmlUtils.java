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

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * XmlUtils.java
 * 
 * The <code>XmlUtils</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @date Aug 21, 2010 1:06:19 PM
 */
public final class XmlUtils implements Serializable, DefaultXmlTags {

	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = -8984322113878597995L;

	/* singleton instance */
	private static XmlUtils instance;

	private XmlUtils() {
	}

	/**
	 * To make a singleton class serializable, it is not only sufficient to add
	 * implements <code>Serializable</code> to its declaration. To maintain the
	 * singleton guarantee, you must also provide a <code>readResolve</code>
	 * method.
	 * 
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return instance;
	}

	/**
	 * Single entry point.
	 * 
	 * @return
	 */
	public synchronized static XmlUtils getInstance() {
		if (instance == null) {
			instance = new XmlUtils();
		}
		return instance;
	}

/**
     * This method takes a string and converts the '<', '>', '&', '"', ''' to their XML
     * escape sequences, like "&lt;", "&lt;" etc.
     *
     * <p>In addition, ASCII control characters that are not permitted in XML documents
     * will be removed.</p>
     *
     * @param   in  in the text to be converted.
     *
     * @return  - the input string with the characters '<' and '>' replaced with the
     *          escape sequences
     */
	public String escapeXML(String in) {
		char ch;
		int i = 0;
		int last = 0;
		char[] input = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3));

		for (; i < len; i++) {
			ch = input[i];

			if (ch > '>') {
				continue;
			} else if (ch == '<') {

				if (i > last) {
					out.append(input, last, i - last);
				}

				last = i + 1;
				out.append(TAG_ENCODE_LT);
			} else if (ch == '>') {

				if (i > last) {
					out.append(input, last, i - last);
				}

				last = i + 1;
				out.append(TAG_ENCODE_GT);
			} else if (ch == '&') {

				if (i > last) {
					out.append(input, last, i - last);
				}

				last = i + 1;
				out.append(TAG_ENCODE_AMP);
			} else if (ch == '"') {

				if (i > last) {
					out.append(input, last, i - last);
				}

				last = i + 1;
				out.append(TAG_ENCODE_QUOTE);
			} else if (ch == '\'') {

				if (i > last) {
					out.append(input, last, i - last);
				}

				last = i + 1;
				out.append(TAG_ENCODE_APOS);
			} else if ((ch < 0x20)
					&& ((ch != 0x09) && (ch != 0x0A) && (ch != 0x0D))) {

				// Omit ASCII control characters, as per XML specification
				// (see http://www.w3.org/TR/2000/REC-xml-20001006#charsets);
				//
				if (i > last) {
					out.append(input, last, i - last);
				}

				last = i + 1;
			}
		} // end for

		if (last == 0) {
			return in;
		}

		if (i > last) {
			out.append(input, last, i - last);
		}

		return out.toString();
	} // end method escapeXML

	/**
	 * This method returns the string representation of an XML element as
	 * <name>value</name>
	 * 
	 * @param name
	 * @param value
	 * 
	 * @return
	 */
	public String generateElement(String name, int value) {
		return generateElement(name, String.valueOf(value));
	}

	/**
	 * This method returns the string representation of an XML element as
	 * <name>value</name>
	 * 
	 * @param name
	 * @param value
	 * 
	 * @return
	 */
	public String generateElement(String name, boolean value) {
		return generateElement(name, String.valueOf(value));
	}

	/**
	 * This method returns the string representation of an XML element as
	 * <name>value</name>
	 * 
	 * @param name
	 * @param value
	 * 
	 * @return
	 */
	public String generateElement(String name, long value) {
		return generateElement(name, String.valueOf(value));
	}

	/**
	 * This method returns the string representation of an XML element as
	 * <name>value</name>
	 * 
	 * @param name
	 * @param value
	 * 
	 * @return
	 */
	public String generateElement(String name, double value) {
		return generateElement(name, String.valueOf(value));
	}

	/**
	 * This method returns the string representation of an XML element as
	 * <name>value</name>
	 * 
	 * @param name
	 * @param value
	 * 
	 * @return
	 */
	public String generateElement(String name, String value) {

		if (value == null) {
			return generateEmptyElement(name);
		}

		StringBuffer buf = new StringBuffer();

		buf = buf.append("<");
		buf = buf.append(name);
		buf = buf.append(">");

		buf = buf.append(escapeXML(value));

		buf = buf.append("</");
		buf = buf.append(name);
		buf = buf.append(">\n");

		return buf.toString();
	}

	/**
	 * This method returns the string representation of an XML element start tag
	 * as <name>
	 * 
	 * @param name
	 * 
	 * @return
	 */
	public String generateStartElement(String name) {
		StringBuffer buf = new StringBuffer();
		buf = buf.append("<");
		buf = buf.append(name);
		buf = buf.append(">\n");

		return buf.toString();
	}

	/**
	 * This method returns the string representation of an xml element end tag
	 * as </name>
	 * 
	 * @param name
	 * 
	 * @return
	 */
	public String generateEndElement(String name) {
		StringBuffer buf = new StringBuffer();

		buf = buf.append("</");
		buf = buf.append(name);
		buf = buf.append(">\n");

		return buf.toString();
	}

	/**
	 * This method returns the string representation of an xml empty element as
	 * <name />
	 * 
	 * @param name
	 * 
	 * @return
	 */
	public String generateEmptyElement(String name) {
		StringBuffer buf = new StringBuffer();

		buf = buf.append("<");
		buf = buf.append(name);
		buf = buf.append(" />\n");

		return buf.toString();
	}

	/**
	 * This method returns the string representation of an xml element as <name
	 * attnames1="attvalues1" attnames2="attvalues2" ... />
	 * 
	 * @param name
	 * @param attNames
	 * @param attValues
	 * 
	 * @return
	 */
	public String generateElement(String name, String[] attNames,
			String[] attValues) {
		StringBuffer buf = new StringBuffer();
		buf = buf.append("<");
		buf = buf.append(name);

		for (int i = 0; i < attNames.length; i++) {
			buf.append(" ");
			buf.append(attNames[i]);
			buf.append("=\"");
			buf.append(escapeXML(attValues[i]));
			buf.append("\"");
		}

		buf = buf.append(" />\n");

		return buf.toString();
	}

	/**
	 * This method returns the string representation of an XML element as <name
	 * attnames1="attvalues1" attnames2="attvalues2" ... >value</name>
	 * 
	 * @param name
	 * @param value
	 * @param attNames
	 * @param attValues
	 * 
	 * @return
	 */
	public String generateElement(String name, String value, String[] attNames,
			String[] attValues) {
		StringBuffer buf = new StringBuffer();
		buf = buf.append("<");
		buf = buf.append(name);

		for (int i = 0; i < attNames.length; i++) {
			buf.append(" ");
			buf.append(attNames[i]);
			buf.append("=\"");
			buf.append(escapeXML(attValues[i]));
			buf.append("\"");
		}

		buf = buf.append(">");

		buf = buf.append(escapeXML(value));

		buf = buf.append("</");
		buf = buf.append(name);
		buf = buf.append(">\n");

		return buf.toString();
	}

	/**
	 * This method returns the string representation of an XML element start tag
	 * as <name attnames1="attvalues1" attnames2="attvalues2" ... >
	 * 
	 * @param name
	 * @param attNames
	 * @param attValues
	 * 
	 * @return
	 */
	public String generateElementStart(String name, String[] attNames,
			String[] attValues) {
		StringBuffer buf = new StringBuffer();
		buf = buf.append("<");
		buf = buf.append(name);

		for (int i = 0; i < attNames.length; i++) {
			buf.append(" ");
			buf.append(attNames[i]);
			buf.append("=\"");
			buf.append(escapeXML(attValues[i]));
			buf.append("\"");
		}

		buf = buf.append(">\n");

		return buf.toString();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String in = "this is a simple & working test<>";
		System.out.println(XmlUtils.getInstance().escapeXML(in));
		String element = "Address";
		System.out.println(XmlUtils.getInstance().generateElement("Address",
				"V & P. O. Kanheli"));
		System.out
				.println(XmlUtils.getInstance().generateStartElement(element));
		System.out.println(XmlUtils.getInstance().generateElement("City",
				new String[] { "CODE", "ZIP" },
				new String[] { "01262", "124001" }));
		System.out
				.println(XmlUtils.getInstance().generateElement("State",
						"Haryana", new String[] { "Region" },
						new String[] { "North" }));
		System.out.println(XmlUtils.getInstance().generateEndElement(element));
	}
}