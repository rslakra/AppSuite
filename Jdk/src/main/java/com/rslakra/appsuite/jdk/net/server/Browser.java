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
package com.rslakra.appsuite.jdk.net.server;

/**
 * Created by IntelliJ IDEA. User: rohtash.singh Date: May 31, 2005 Time:
 * 5:21:08 PM To change this template use Options | File Templates.
 */

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;

// A simple text-based browser
public class Browser {
    static String urlGoogle = "https://www.google.com/";
    static String urlHttps = "https://10.0.61.151:443/broker/fcs/stbProperties?MACAddress=000FFE31B22F&OpCode=get";

    private String urlString;

    // You must supply the URL to be browsed
    public static void main(String[] args) throws Exception {
        Browser browser = new Browser(urlGoogle);
        browser.run();
    }

    // Construct a browser object
    public Browser(String urlString) {
        this.urlString = urlString;
        /**
         * The trick to implementing SSL on the client is to register JSSE and
         * set the java.protocol.handler.pkgs system property so that JSSE
         * automatically is used to handle https URLs.
         */
        secureBrowser();
        mutualAuthenticate("clientjssecacerts", "clientpw");
    }

    public void mutualAuthenticate(String keyStore, String password) {
        /**
         * On the client side, we must tell the underlying SSL implementation
         * which keystore to use and which password to use to access the
         * keystore.
         */
        String path = getClientPath() + keyStore;
        System.out.println("path : " + path);
        System.setProperty("javax.net.ssl.keyStore", path);
        System.setProperty("javax.net.ssl.keyStorePassword", password);
    }

    public String getClientPath() {
        String pack = this.getClass().getPackage().getName();
        String path = System.getProperty("user.dir");
        path += File.separator + "src" + pack + File.separator;
        return path;
    }

    // Get the URL
    public void run() throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
        System.out.println("THE HEADERS");
        System.out.println("-----------");
        for (int i = 1; ; ++i) {
            String key;
            String value;
            if ((key = urlc.getHeaderFieldKey(i)) == null)
                break;
            if ((value = urlc.getHeaderField(i)) == null)
                break;
            System.out.println("KEY: " + key);
            System.out.println("VALUE: " + value);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        String line;
        System.out.println("THE CONTENT");
        System.out.println("-----------");
        while ((line = reader.readLine()) != null)
            System.out.println(line);
    }

    public void secureBrowser() {
        // Register JSSE
        Security.addProvider(new BouncyCastleProvider());
        // Here's the trick!
        // Simply set the protocol handler property to use SSL.
        System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
    }
}
