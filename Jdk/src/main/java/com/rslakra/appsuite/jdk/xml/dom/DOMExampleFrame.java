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
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DOMExampleFrame extends JFrame {
    private Document myDocument;

    public DOMExampleFrame() {
        DocumentBuilder myBuilder = null;
        Container c = getContentPane();
        DocumentBuilderFactory myFactory = DocumentBuilderFactory.newInstance();
        try {
            myBuilder = myFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Oh no!");
        }
        try {
            // myDocument = myBuilder.parse(new File("Books.xml"));
            myDocument = myBuilder.parse(getClass().getResourceAsStream("Books.xml"));
            System.out.println("File Found");

            // Elements
            Element rootElement = myDocument.getDocumentElement();
            parseNodes(rootElement);
            // //node list
            // NodeList nodeChildren = rootElement.getChildNodes();
            //
            // if(rootElement != null) {
            // System.out.println(rootElement);
            // }
            // this.add(new JLabel(""));

        } catch (IOException ex) {
            System.out.println("No file!");
        } catch (SAXException ex) {
            System.out.println("Horrible error!");
        }
    }

    private void parseNodes(Element root) {
        ArrayList<Book> books = new ArrayList<Book>();
        NodeList nodes = root.getElementsByTagName("Book");
        for (int ctr = 0, size = nodes.getLength(); ctr < size; ctr++) {
            Node node = nodes.item(ctr);
            System.out.println(node.getNodeName() + " = " + node.getNodeValue());
            if (node instanceof Element) {
                // a child element to process
                Element child = (Element) node;
                String status = child.getAttribute("status");
                System.out.println(status);
            }

            // if(node.hasChildNodes()) {
            // NodeList children = node.getChildNodes();
            // children.item(index)
            // }

            ((Element) node).getElementsByTagName("ISBN");
            root.getElementsByTagName("Book");

            // Book book = new Book();
            // node.getUserData(key)
            // for (int i = 0; i < nodes.getLength(); i++) {
            // tmpEl = (Element) nodeChildren.item (i);
            // NodeList tmpList;
            // Element ISBNvalue;
            // tmpList = tmpEl.getElementsByTagName ("ISBN");
            // ISBNvalue = (Element) tmpList.item (0);
            // Book tmpBook = new Book();
            // myBooks.add (tmpBook); } }
            //
            //
            // books.add(book);
        }

        Node node1 = findNode("Book", root);
        if (node1 != null) {
            System.out.println("Node : " + node1.getNodeName() + " = " + node1.getNodeValue());
        } else {
            System.out.println("node1 : " + node1);
        }
    }

    public Node findNode(String tag, Node rootElement) {
        NodeList nodeChildren = rootElement.getChildNodes();
        Node tmpNode = null;
        String foundTag = rootElement.getLocalName();
        if (foundTag != null && foundTag.equals(tag)) {
            return rootElement;
        }

        for (int i = 0; i < nodeChildren.getLength(); i++) {
            tmpNode = nodeChildren.item(i);
            foundTag = tmpNode.getLocalName();
            if (foundTag != null && tmpNode.getLocalName().equals(tag)) {
                return tmpNode;
            }
        }
        return tmpNode;
    }

    private void parseChildrens(Element node) {
        ArrayList<Book> books = new ArrayList<Book>();
        // NodeList nodes = root.getElementsByTagName("Book");
        // for (int i = 0; i < nodes.getLength(); i++) {
        // Book book = new Book();
        // books.add(book);
        // }

        // for (int i = 0; i < nodeChildren.getLength(); i++) { tmpEl =
        // (Element) nodeChildren.item (i); NodeList tmpList; Element ISBNvalue;
        // tmpList = tmpEl.getElementsByTagName ("ISBN"); ISBNvalue = (Element)
        // tmpList.item (0); Book tmpBook = new Book(); myBooks.add (tmpBook); }
        // }
    }

    public static void main(String[] args) {
        DOMExampleFrame myMain = new DOMExampleFrame();
        myMain.setSize(400, 400);
        myMain.setVisible(true);
    }
}
