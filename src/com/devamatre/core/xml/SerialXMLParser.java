package com.devamatre.core.xml;

/**
 SerialXMLParser.java

 This class provides a single instance of a serial XML parser. Its methods
 are synchronized where necessary to allow more than one object to use it.

 @author  mark.martino
 @date  2001 JUN 19
 */

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class SerialXMLParser {
	private static SerialXMLParser instance = new SerialXMLParser();

	private static final String BAD_IO = "SAX parser had an I/O problem:  ";
	private static final String BAD_PARSE = "Unable to complete SAX parsing:  ";
	private boolean inUse = false;
	private SAXParser parser = null;

	// PUBLIC ACCESSOR
	public static SerialXMLParser getInstance() {
		return instance;
	}

	public static SerialXMLParser getNewInstance() {
		if (instance.inUse)
			return new SerialXMLParser();
		return instance;
	}

	// CONSTRUCTOR
	private SerialXMLParser() {
	}

	/**
	 * createParser
	 * 
	 * Creates a new parser if one is not already available.
	 */
	private SAXParser createParser() throws ParserConfigurationException,
			SAXException {
		return SAXParserFactory.newInstance().newSAXParser();
	}

	/**
	 * parse
	 * 
	 * @param String
	 *            filePath - complete path for a valid XML file
	 * @param DefaultHandler
	 *            handler - handles XML elements
	 * 
	 *            Parses the submitted input stream using the submitted handler.
	 */
	public void parse(String filePath, DefaultHandler handler)
			throws IOException, SAXException {
		inUse = true;
		parse(new InputSource(filePath), handler);
		inUse = false;
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
	public void parse(InputStream stream, DefaultHandler handler)
			throws IOException, SAXException {
		inUse = true;
		parse(new InputSource(stream), handler);
		inUse = false;
	}

	// public synchronized void parse(File file, DefaultHandler handler)
	// throws IOException, SAXException {
	// try {
	// IntervalTimer timer = SystemResourceManager.getInstance()
	// .getIntervalTimer(
	// TimerPrecisionType.TIMER_PRECISION_TYPE_SEC);
	// for (int i = 0; i < 10; i++) {
	// timer.setAlarmAfter(3, new Runnable() {
	// public void run() {
	// Throwable SAXException;
	// try {
	// if (parser != null)
	// parser = SAXParserFactory.newInstance()
	// .newSAXParser();
	// } catch (SAXException sae) {
	// logger.error(BAD_PARSE, sae);
	//
	// } catch (ParserConfigurationException e) {
	// logger.error(BAD_PARSE, e);
	// }
	//
	// }
	// });
	// if (parser != null)
	// break;
	// }
	//
	// parser.parse(file, handler);
	// parser = null;
	// } catch (IOException ioe) {
	// logger.error(BAD_IO, ioe);
	// throw ioe;
	// }
	// /*
	// * catch( SAXException se ) { logger.error( BAD_PARSE, se ); throw se; }
	// * catch (ParserConfigurationException e) { logger.error( BAD_PARSE, e
	// * ); throw new SAXException("Failed to instantiate parser", e); }
	// */}

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
	public synchronized void parse(InputSource source, DefaultHandler handler)
			throws IOException, SAXException {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(source, handler);
			parser = null;
		} catch (IOException ioe) {
			// logger.error(BAD_IO, ioe);
			throw ioe;
		} catch (SAXException se) {
			// logger.error(BAD_PARSE, se);
			throw se;
		} catch (ParserConfigurationException e) {
			// logger.error(BAD_PARSE, e);
			throw new SAXException("Failed to instantiate parser", e);
		}
	}
}
