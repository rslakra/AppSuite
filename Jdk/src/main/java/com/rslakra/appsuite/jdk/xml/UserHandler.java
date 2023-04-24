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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserHandler extends XmlHandler {
    private static String TAG_USERS = "Users";
    private static String TAG_USER = "User";
    private static String TAG_ID = "Id";
    private static String TAG_NAME = "Name";
    private static String TAG_PASSWORD = "Password";

    private ArrayList users = null;
    private User user = null;

    public UserHandler() {

    }

    public void startDocument() throws SAXException {
        // System.out.println("startDocument()");
        users = new ArrayList();
    }

    public void endDocument() throws SAXException {
        // System.out.println("endDocument()");
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        // System.out.println("startElement(), uri : " + uri + ", localName:"
        // + localName + ", qName : " + qName);
        super.startElement(uri, localName, qName, atts);
        if (TAG_USER.equals(qName)) {
            user = new User();
            user.setId(atts.getValue(TAG_ID)); // Id
        } else if (TAG_NAME.equals(qName)) { // Name

        } else if (TAG_PASSWORD.equals(qName)) { // Password

        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        // System.out.println("endElement(), uri : " + uri + ", localName:"
        // + localName + ", qName : " + qName);
        super.endElement(uri, localName, qName);
        if (TAG_USER.equals(qName)) {
            users.add(user);
            user = null;
        } else if (TAG_NAME.equals(qName)) { // Name
            user.setName(buffer.toString());
        } else if (TAG_PASSWORD.equals(qName)) { // Password
            user.setPassword(buffer.toString());
        }
    }

    public User[] getUsers() {
        User[] toUsers = new User[users.size()];
        return (User[]) users.toArray(toUsers);
    }

    public void show() {
        System.out.println("==================================================");
        System.out.println("\t\tUsers");
        System.out.println("==================================================");
        User[] users = getUsers();
        for (int i = 0; i < users.length; i++) {
            System.out.print(users[i]);
            System.out.println();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String file = "C:\\Data\\Rohtash\\Examples\\src\\lakra\\xml\\User.xml";
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            UserHandler handler = new UserHandler();

            try {
                parser.parse(new File(file), handler);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            handler.show();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
