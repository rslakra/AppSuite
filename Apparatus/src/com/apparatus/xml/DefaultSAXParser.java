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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.apparatus.Constants;
import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;

/**
 * This is the base SAX parsing handler which also handles the errors if any.
 * All the SAX parsing handler classes must extend this class. It adds some
 * extra error handling methods but does not override any default handler
 * methods. The only method which is overridden is character(...) to get the
 * value of element.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class DefaultSAXParser extends DefaultHandler implements DefaultXmlTags {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = -679343913809072841L;
	
	/** logger */
	private static final Logger logger = LogManager.getLogger(DefaultSAXParser.class, false);
	
	/** errorCode */
	private String errorCode;
	
	/** errorMessage */
	private String errorMessage;
	
	/** sBuilder */
	private StringBuilder sBuilder;
	
	/**
	 * Default Constructor
	 */
	public DefaultSAXParser() {
		super();
		sBuilder = new StringBuilder();
	}
	
	/**
	 * Returns the errorCode.
	 * 
	 * @return
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * Returns the errorMessage.
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * Returns the string representation of buffer.
	 * 
	 * @return
	 */
	protected String getBuffer() {
		return sBuilder.toString();
	}
	
	/**
	 * Resets the properties of AbstractSAXHander. It must call before the user
	 * of each handler to allow reuse of error code and message.
	 */
	protected void reset() {
		logger.debug("+reset()");
		
		// Clear the error messages to allow reuse.
		errorCode = null;
		errorMessage = null;
		
		logger.debug("-reset()");
	}
	
	/**
	 * It receives notification of the beginning of the document. It resets the
	 * properties of <code>AbstractSAXHandler</code> to reuse and gives calls an
	 * abstract method start document post.
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	public final void startDocument() throws SAXException {
		logger.debug("+startDocument()");
		
		reset();
		postStartDocument();
		
		logger.debug("-startDocument()");
	}
	
	/**
	 * It receives notification of the start of an element. It resets the string
	 * builder to reuse and calls the abstract method to handle elements.
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		logger.debug("+startElement(" + uri + ", " + localName + ", " + qName + ", " + attributes + ")");
		
		sBuilder.setLength(0);
		postStartElement(uri, localName, qName, attributes);
		logger.debug("-startElement()");
	}
	
	/**
	 * It receives notification of the end of an element.
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public final void endElement(String uri, String name, String qName) {
		logger.debug("+endElement(" + uri + ", " + name + ", " + qName + ")");
		
		if(!preEndElement(uri, name, qName)) {
			/* error handling */
			if(TAG_ERROR.equals(qName)) {
				if(TAG_ERROR_CODE.equals(qName)) {
					errorCode = sBuilder.toString();
				} else if(TAG_ERROR_MESSAGE.equals(qName)) {
					errorMessage = sBuilder.toString();
				}
			}
		}
		logger.debug("-endElement()");
	}
	
	/**
	 * Receive notification of character data inside an element.
	 * 
	 * @param chars
	 * @param start
	 * @param length
	 * @exception SAXException
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public final void characters(char chars[], int start, int length) {
		sBuilder.append(chars, start, length);
	}
	
	/**
	 * This method is called from the startDocument() method after
	 * resetting the properties of this object.
	 */
	protected void postStartDocument() {
		
	}
	
	/**
	 * This method is called after resting the internal string builder.
	 * 
	 * @param uri
	 * @param localName
	 * @param qName
	 * @param attributes
	 */
	protected void postStartElement(String uri, String localName, String qName, Attributes attributes) {
		
	}
	
	/**
	 * This method is called from the endElement() method to parsing of the
	 * document.
	 * 
	 * @param uri
	 * @param name
	 * @param qName
	 * @return
	 */
	protected boolean preEndElement(String uri, String name, String qName) {
		return false;
	}
	
	/**
	 * This method returns true if there is no error while parsing. The error
	 * code and error message properties should be NULL. If the error code and
	 * error message are not NULL, it will return false.
	 * 
	 * It error code and error messages can be retrieved by using the getter
	 * methods provided by this object.
	 * 
	 * It should call only after completion of parsing to know the status.
	 * 
	 * 
	 * @returns boolean - true if there was no parsing error otherwise false.
	 */
	public boolean checkStatus() {
		logger.debug("+checkStatus()");
		
		boolean status = false;
		/* check if any parsing error */
		if(errorCode != null && errorCode.trim().length() > 0) {
			StringBuilder msgBuilder = new StringBuilder(TAG_BUFFER_SIZE);
			msgBuilder.append(Constants.NEWLINE).append(getClass().getSimpleName()).append(" <ErrorCode:").append(errorCode).append(", Message:").append(errorMessage).append(">").append(Constants.NEWLINE);
			logger.error(msgBuilder.toString());
			status = false;
		} else {
			status = true;
		}
		
		logger.debug("-checkStatus(), status: " + status);
		return status;
	}
}