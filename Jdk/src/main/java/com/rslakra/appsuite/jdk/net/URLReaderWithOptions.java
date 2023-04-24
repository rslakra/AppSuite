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
package com.rslakra.appsuite.jdk.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/*
 * Using a URL to access resources on a secure site.
 *
 * You can optionally set the following command line options:
 *
 *     -h <secure proxy server hostname>
 *     -p <secure proxy server port>
 *     -k <| separated list of protocol handlers>
 *     -c <enabled cipher suites as a comma separated list>
 *
 */

public class URLReaderWithOptions {
    public static void main(String[] args) throws Exception {

        System.out.println("USAGE: java URLReaderWithOptions "
                + "[-h proxyhost] [-p proxyport] [-k protocolhandlerpkgs] " + "[-c ciphersarray]");

        // initialize system properties
        char option = 'd';
        for (int i = 0; i < args.length; i++) {
            System.out.println(option + ": " + args[i]);
            switch (option) {
                case 'h':
                    System.setProperty("https.proxyHost", args[i]);
                    option = 'd';
                    break;
                case 'p':
                    System.setProperty("https.proxyPort", args[i]);
                    option = 'd';
                    break;
                case 'k':
                    System.setProperty("java.protocol.handler.pkgs", args[i]);
                    option = 'd';
                    break;
                case 'c':
                    System.setProperty("https.cipherSuites", args[i]);
                    option = 'd';
                    break;
                default:
                    // get the next option
                    if (args[i].startsWith("-")) {
                        option = args[i].charAt(1);
                    }
            }
        }

        URL verisign = new URL("https://www.verisign.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(verisign.openStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);

        in.close();
    }
}
