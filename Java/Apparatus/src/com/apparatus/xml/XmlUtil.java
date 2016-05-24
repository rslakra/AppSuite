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
 * offenders will be liable for any damages. All rights, including but not
 * limited to rights created by patent grant or registration of a utility model
 * or design, are reserved. Technical specifications and features are binding
 * only insofar as they are specifically and expressly agreed upon in a written
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 * http://www.devamatre.com/licenses/license.txt.
 * 
 * Devamatre reserves the right to modify the technical specifications and or
 * features without any prior notice.
 *****************************************************************************/
package com.apparatus.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;

/**
 * The <code>XmlUtils</code> class provides utility method which helps to
 * generate the XML elements as well as to parse the XML file using the handle
 * for that xml.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0
 * @since 1.0
 */
public final class XmlUtil implements Serializable, DefaultXmlTags {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = -9050232236900594416L;
	
	/** logger */
	private static final Logger logger = LogManager.getLogger(XmlUtil.class);
	
	/** singleton instance */
	private static XmlUtil instance;
	
	private boolean parsing = false;
	
	/** don't allow to create instance outside. */
	private XmlUtil() {
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
	public synchronized static XmlUtil getInstance() {
		if(instance == null) {
			instance = new XmlUtil();
		}
		return instance;
	}
	
	/**
	 * Returns true if the parsing is going on otherwise false.
	 * 
	 * @return
	 */
	public boolean isParsing() {
		return parsing;
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
	 * Returns the string representation of an XML element as
	 * <name>value</name>.
	 * 
	 * @param name
	 * @param value
	 * 
	 * @return
	 */
	public String generateElement(String name, String value) {
		if(value == null || value.trim().length() == 0) {
			return generateEmptyElement(name);
		} else {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<");
			sBuilder.append(name);
			sBuilder.append(">");
			sBuilder.append(escapeXML(value));
			sBuilder.append("</");
			sBuilder.append(name);
			sBuilder.append(">\n");
			return sBuilder.toString();
		}
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
		StringBuilder sBuilder = new StringBuilder();
		
		sBuilder.append("<");
		sBuilder.append(name);
		sBuilder.append(">\n");
		
		return sBuilder.toString();
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
		StringBuilder sBuilder = new StringBuilder();
		
		sBuilder.append("</");
		sBuilder.append(name);
		sBuilder.append(">\n");
		
		return sBuilder.toString();
	}
	
	/**
	 * Returns the string representation of an empty XML element as <name />.
	 * 
	 * @param name
	 * @return
	 */
	public String generateEmptyElement(String name) {
		StringBuilder sBuilder = new StringBuilder();
		
		sBuilder.append("<");
		sBuilder.append(name);
		sBuilder.append(" />\n");
		
		return sBuilder.toString();
	}
	
	/**
	 * This method returns the string representation of an XML element as <name
	 * attNames[0]="attValues[0]" attNames[1]="attValues[1]" ... />
	 * 
	 * @param name
	 * @param attNames
	 * @param attValues
	 * 
	 * @return
	 */
	public String generateElement(String name, String[] attNames, String[] attValues, boolean empty) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<");
		sBuilder.append(name);
		
		/* add attributes */
		for(int index = 0; index < attNames.length; index++) {
			sBuilder.append(" ");
			sBuilder.append(attNames[index]);
			sBuilder.append("=\"");
			sBuilder.append(escapeXML(attValues[index]));
			sBuilder.append("\"");
		}
		
		/* check its an empty element or not */
		if(empty) {
			sBuilder.append(" />\n");
		} else {
			sBuilder.append(">\n");
		}
		
		return sBuilder.toString();
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
	public String generateElement(String name, String value, String[] attNames, String[] attValues) {
		StringBuffer buf = new StringBuffer();
		buf = buf.append("<");
		buf = buf.append(name);
		
		for(int index = 0; index < attNames.length; index++) {
			buf.append(" ");
			buf.append(attNames[index]);
			buf.append("=\"");
			buf.append(escapeXML(attValues[index]));
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
     * It takes a string and converts the special characters like '<', '>', 
     * '&', '"', ''' to their XML escape sequences, like "&lt;", "&lt;" etc.
     *
     * <p>In addition, ASCII control characters that are not permitted in XML 
     * documents will be removed.</p>
     *
     * @param str - in the text to be converted.
     * @return String - the input string with the characters '<' and '>' replaced 
     * with the escape sequences
	 */
	public String escapeXML(String str) {
		// if (logger.isDebugEnabled()) {
		// logger.debug("escapeXML(" + str + ")");
		// }
		
		int index = 0;
		int last = 0;
		char[] chars = str.toCharArray();
		StringBuilder sBuilder = new StringBuilder();
		while(index < chars.length) {
			// if (logger.isDebugEnabled()) {
			// logger.debug("chars[" + index + "] = " + chars[index]);
			// }
			switch(chars[index]) {
				case '>':
					if(index > last) {
						sBuilder.append(chars, last, index - last);
					}
					last = index + 1;
					sBuilder.append(TAG_ENCODE_GT);
					break;
				case '<':
					if(index > last) {
						sBuilder.append(chars, last, index - last);
					}
					last = index + 1;
					sBuilder.append(TAG_ENCODE_LT);
					break;
				case '&':
					if(index > last) {
						sBuilder.append(chars, last, index - last);
					}
					last = index + 1;
					sBuilder.append(TAG_ENCODE_AMP);
					break;
				case '"':
					if(index > last) {
						sBuilder.append(chars, last, index - last);
					}
					last = index + 1;
					sBuilder.append(TAG_ENCODE_QUOTE);
					break;
				case '\'':
					if(index > last) {
						sBuilder.append(chars, last, index - last);
					}
					last = index + 1;
					sBuilder.append(TAG_ENCODE_APOS);
					break;
				default:
					if((chars[index] < 0x20) && ((chars[index] != 0x09) && (chars[index] != 0x0A) && (chars[index] != 0x0D))) {
						/*
						 * Exclude ASCII control characters. For more
						 * information,
						 * see XML specification (see
						 * http://www.w3.org/TR/2000/REC-xml-20001006#charsets);
						 */
						if(index > last) {
							sBuilder.append(chars, last, index - last);
						}
						last = index + 1;
					}
					break;
			}
			// if (logger.isDebugEnabled()) {
			// logger.debug("sBuilder: " + sBuilder.toString());
			// }
			index++;
		}
		
		if(last == 0) {
			return str;
		}
		if(index > last) {
			sBuilder.append(chars, last, index - last);
		}
		
		// if (logger.isDebugEnabled()) {
		// logger.debug("-escapeXML(), result: " + sBuilder.toString());
		// }
		return sBuilder.toString();
	}
	
	/**
	 * Replaces special chars with escape sequences. *
	 * 
	 * @param str
	 */
	public String replaceEscape(String str) {
		StringBuilder sBuilder = new StringBuilder();
		str.replaceAll("&", "&amp;");
		str.replaceAll(">", "&gt;");
		str.replaceAll("<", "&lt;");
		str.replaceAll("\"", "&quot;");
		str.replaceAll("\'", "&apos;");
		
		return sBuilder.toString();
	}
	
	/**
	 * Returns the XML file path for the class.
	 * 
	 * @param klass
	 * @param xmlFileName
	 * @return
	 */
	public String getFilePath(Class<?> klass, String xmlFileName) {
		return klass.getResource(xmlFileName).toExternalForm().substring("file:\\".length());
	}
	
	/**
	 * Returns the new parser object.
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public SAXParser createParser() throws ParserConfigurationException, SAXException {
		return SAXParserFactory.newInstance().newSAXParser();
	}
	
	/**
	 * It parses the specified XML file using the specified handler.
	 * 
	 * 
	 * @param xmlFilePath
	 *            - complete path for a valid XML file.
	 * @param saxParser
	 *            - which handles XML elements.
	 * @throws IOException
	 * @throws SAXException
	 */
	public void parse(String xmlFilePath, DefaultSAXParser saxParser) throws IOException, SAXException {
		parse(new InputSource(xmlFilePath), saxParser);
	}
	
	/**
	 * parse
	 * 
	 * @param InputStream
	 *            stream - valid XML input stream
	 * @param DefaultHandler
	 *            handler - handles XML elements
	 * 
	 *            Parses the submitted input stream using the submitted handler.
	 */
	public void parse(InputStream stream, DefaultSAXParser saxParser) throws IOException, SAXException {
		parse(new InputSource(stream), saxParser);
	}
	
	/**
	 * parse
	 * 
	 * @param InputSource
	 *            source - valid XML input source
	 * @param DefaultHandler
	 *            handler - handles XML elements
	 * 
	 *            Parses the submitted input stream using the submitted handler.
	 */
	public void parse(InputSource source, DefaultSAXParser saxParser) throws IOException, SAXException {
		logger.debug("+parse(" + source + ", " + saxParser + ")");
		
		try {
			/* block level synchronization. */
			synchronized(this) {
				SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
				parsing = true;
				parser.parse(source, saxParser);
				parsing = false;
				parser = null;
			}
		} catch(IOException ioe) {
			logger.error("SAX parser had an I/O error!", ioe);
			throw ioe;
		} catch(SAXException saxEx) {
			logger.error("SAX parsing failed!", saxEx);
			throw saxEx;
		} catch(ParserConfigurationException pcEx) {
			logger.error("SAX parsing failed!", pcEx);
			throw new SAXException("Parser Instantiate exception!", pcEx);
		}
		
		logger.debug("-parse()");
	}
	
	/**
	 * DOCUMENT ME!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		XmlUtil xmlUtils = XmlUtil.getInstance();
		
		String original = "this is a simple & working test<>";
		String modified = xmlUtils.escapeXML(original);
		System.out.println("original: " + original);
		System.out.println("modified: " + modified);
		original = xmlUtils.escapeXML(modified);
		System.out.println("original: " + modified);
		System.out.println();
		
		StringBuilder sBuilder = new StringBuilder();
		String element = "Address";
		sBuilder.append(xmlUtils.generateElement(element, "V & P. O. Kanheli"));
		sBuilder.append(xmlUtils.generateStartElement(element));
		sBuilder.append(xmlUtils.generateElement("City", new String[] { "CODE", "ZIP"}, new String[] { "01262", "124001"}, true));
		sBuilder.append(xmlUtils.generateElement("State", "Haryana", new String[] { "Region"}, new String[] { "North"}));
		sBuilder.append(xmlUtils.generateEndElement(element));
		System.out.println(sBuilder.toString());
	}
}