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
package com.rslakra.java.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.rslakra.java.xml.sax.BaseSAXParser;
import com.rslakra.logger.LogManager;
import com.rslakra.logger.Logger;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-10 02:04:52 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class ActionHandler extends BaseSAXParser {

	/* logger */
	private final Logger logger = LogManager.getLogger(ActionHandler.class);

	/* The Tags used in MainMenu.xml file */
	private static final String TAG_ACTIONS = "Actions";
	private static final String TAG_ACTION = "Action";
	private static final String TAG_ID = "Id";
	private static final String TAG_NAME = "Name";
	private static final String TAG_CLASS = "Class";
	private static final String TAG_METHOD = "Method";

	// attributes of main menu.
	private List<Action> actions;
	private boolean done = false;
	private Action action;

	/**
	 * Constructor
	 */
	public ActionHandler() {
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
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// reset for each object
		super.startElement(uri, localName, qName, attributes);

		// check for each tag.
		if (TAG_ACTIONS.equals(qName)) {
			actions = new ArrayList<Action>();
		} else if (TAG_ACTION.equals(qName)) {
			reset(); // reset
			action = new Action();
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
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		// check for each tag.
		if (TAG_ACTIONS.equals(qName)) {
			done = true;
		} else if (TAG_ACTION.equals(qName)) {
			actions.add(action);
		} else if (TAG_ID.equals(qName)) {
			action.setId(getBuffer());
		} else if (TAG_NAME.equals(qName)) {
			action.setName(getBuffer());
		} else if (TAG_CLASS.equals(qName)) {
			action.setKlass(getBuffer());
		} else if (TAG_METHOD.equals(qName)) {
			action.setMethod(getBuffer());
		}
	}

	/**
	 *
	 */
	private void reset() {
		if (done) {
			logger.debug("done:" + done);
			actions = null;
		}
		action = null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Action> getActions() {
		return actions;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.devamatre.examples.java.xml.sax.BaseSAXParser#show()
	 */
	@Override
	public void parse() {
		String xmlFilePath = "/Users/singhr/Documents/MyBackup/Repository/Devamatre/Examples/Java/dCore/src/com.devamatre.examples.java/xml/actions.xml";
		SAXParser parser = null;
		// xml parsing
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
			try {
				System.out.println("xmlFilePath:" + xmlFilePath);
				parser.parse(new FileInputStream(xmlFilePath), this);
				List<Action> actions = getActions();
				System.out.println("actions:" + actions.size());
				for (Action action : actions) {
					System.out.println(action);
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

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ActionHandler actionHandler = new ActionHandler();
		actionHandler.parse();
	}

}