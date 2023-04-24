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
package com.rslakra.appsuite.jdk.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

/**
 * DomParser.java
 * <p>
 * The <code>DomParser</code> TODO Define Purpose here
 *
 * @author Rohtash Singh (rohtash.lakra@devamatre.com)
 * @date Aug 10, 2009 10:55:05 PM
 */
public class DomParser {
    private static final String TAG_TEXT = "#text";
    private static final String TAG_TRUE = "true";
    private static final String TAG_DATA = "Data";

    private Vector<Hashtable<String, String>> vector = null;
    private Document dom;

    public DomParser() {
        vector = new Vector<Hashtable<String, String>>();
    }

    public Vector<Hashtable<String, String>> parseXmlFile(String file) {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        InputSource inStream = new InputSource();
        try {
            if (file != null && !file.equals("")) {
                dom = docBuilderFactory.newDocumentBuilder().parse(new File(file));
                if (!parseDocument()) {
                    vector = null;
                }
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return vector;
    }

    private boolean parseDocument() {
        boolean parsed = false;
        // get the root elememt
        Element docEle = dom.getDocumentElement();
        String actionStatus = null;
        Element data = null;
        if (docEle != null) {
            actionStatus = getTextValue(docEle, "ACTIONSTATUS");
        }
        if (actionStatus != null && TAG_TRUE.equalsIgnoreCase(actionStatus)) {
            NodeList nodeList = docEle.getElementsByTagName(TAG_DATA);
            if (nodeList != null && nodeList.getLength() > 0) {
                data = (Element) nodeList.item(0); // get Data element
                if (data != null) {
                    for (Node child = data.getFirstChild(); child != null; child = child.getNextSibling()) {
                        if (TAG_TEXT.equals(child.getNodeName())) { // #text
                            continue;
                        }
                        Hashtable<String, String> hashTable = new Hashtable<String, String>();
                        int i = 0;
                        for (Node innerChild = child.getFirstChild(); innerChild != null; innerChild = innerChild
                                .getNextSibling()) {
                            if (TAG_TEXT.equals(innerChild.getNodeName())) { // #text
                                continue;
                            }
                            for (Node superInnerChild = innerChild
                                    .getFirstChild(); superInnerChild != null; superInnerChild = superInnerChild
                                    .getNextSibling()) {
                                if (TAG_TEXT.equals(superInnerChild.getNodeName())) { // #text
                                    hashTable.put("type", child.getNodeName());
                                } else {
                                    if (superInnerChild.getFirstChild() != null) {
                                        String strNodeName = superInnerChild.getNodeName();
                                        String strNodeValue = superInnerChild.getFirstChild().getNodeValue();
                                        System.out.println(
                                                "strNodeName : " + strNodeName + ", strNodeValue : " + strNodeValue);
                                        hashTable.put(strNodeName, strNodeValue);
                                    }
                                }
                            }
                            vector.add(i, (Hashtable<String, String>) hashTable.clone());
                            hashTable.clear();
                            i++;
                        }
                    }
                }
            }
            parsed = true;
        }
        return parsed;
    }

    /**
     * This method returns node value.
     *
     * @param element
     * @param tagName
     * @return
     */
    private String getTextValue(Element element, String tagName) {
        String nodeValue = null;
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            nodeValue = ((Element) nodeList.item(0)).getFirstChild().getNodeValue();
        }
        return nodeValue;
    }

    public static void main(String[] args) {
        new DomParser().parseXmlFile("actions.xml");
    }
}
