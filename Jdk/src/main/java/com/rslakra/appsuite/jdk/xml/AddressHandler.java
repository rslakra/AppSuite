/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
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
package com.rslakra.appsuite.jdk.xml;

import com.rslakra.appsuite.jdk.xml.sax.BaseSAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: rohtash.singh Date: Sep 4, 2006 Time: 2:09:06
 * PM To change this template use File | Settings | File Templates.
 */
public class AddressHandler extends BaseSAXParser {

    /* The Tags used in MainMenu.xml file */
    private static final String TAG_ADDRESSES = "Addresses";
    private static final String TAG_ADDRESS = "Address";
    private static final String TAG_STREET = "Street";
    private static final String TAG_CITY = "City";
    private static final String TAG_STATE = "State";
    private static final String TAG_PIN_CODE = "PinCode";
    private static final String TAG_COUNTRY = "Country";

    private boolean done = false;
    private ArrayList<Address> addresses;
    private Address address;

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
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        ;

        // check for each tag.
        if (TAG_ADDRESSES.equals(qName)) {
            addresses = new ArrayList<Address>();
        } else if (TAG_ADDRESS.equals(qName)) {
            reset(); // reset
            address = new Address();
        }
    }

    /**
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        // check for each tag.
        if (TAG_ADDRESSES.equals(qName)) {
            done = true;
        } else if (TAG_ADDRESS.equals(qName)) {
            addresses.add(address);
        } else if (TAG_STREET.equals(qName)) {
            address.setStreet(getBuffer());
        } else if (TAG_CITY.equals(qName)) {
            address.setCity(getBuffer());
        } else if (TAG_STATE.equals(qName)) {
            address.setState(getBuffer());
        } else if (TAG_PIN_CODE.equals(qName)) {
            address.setPinCode(getBuffer());
        } else if (TAG_COUNTRY.equals(qName)) {
            address.setCountry(getBuffer());
        }
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

    /**
     * @return
     */
    public List<Address> getAddresses() {
        return done ? addresses : null;
    }

    /**
     * (non-Javadoc)
     *
     * @see com.devamatre.examples.java.xml.sax.BaseSAXParser#show()
     */
    @Override
    public void parse() {
        String xmlFilePath = "/Users/singhr/Documents/MyBackup/Repository/Devamatre/Examples/Java/dCore/src/com.devamatre.examples.java/xml/Address.xml";
        SAXParser parser = null;
        // xml parsing
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            try {
                System.out.println("xmlFilePath:" + xmlFilePath);
                parser.parse(new FileInputStream(xmlFilePath), this);
                List<Address> addresses = getAddresses();
                System.out.println("addresses:" + addresses.size());
                for (Address address : addresses) {
                    System.out.println(address);
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
     * @param args
     */
    public static void main(String[] args) {
        AddressHandler addressHandler = new AddressHandler();
        addressHandler.parse();
    }
}
