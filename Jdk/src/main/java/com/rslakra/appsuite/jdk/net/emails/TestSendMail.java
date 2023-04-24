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
package com.rslakra.appsuite.jdk.net.emails;

/**
 * @author rohtash.singh
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * This program sends e-mail using a mailto: URL
 **/
public class TestSendMail {
    public static void main(String[] args) {
        try {
            System.getProperties().put("mail.host", "devamatre.com");
            // System.getProperties().put("mail.host",
            // InetAddress.getLocalHost().getHostName());
            System.out.println("mail.host : " + System.getProperty("mail.host"));
            // A Reader stream to read from the console
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            // Ask the user for the from, to, and subject lines
            String user = System.getProperty("user.name");
            String from = "<" + user + "@devamatre.com>";
            // from.append(InetAddress.getLocalHost().getHostName());
            System.out.println("From: " + from);
            // String from = in.readLine();
            System.out.print("To: ");
            String to = in.readLine();
            System.out.print("Subject: ");
            String subject = in.readLine();

            // Establish a network connection for sending mail
            URL u = new URL("mailto:" + to); // Create a mailto: URL
            URLConnection c = u.openConnection(); // Create its URLConnection
            c.setDoInput(false); // Specify no input from it
            c.setDoOutput(true); // Specify we'll do output
            System.out.println("Connecting..."); // Tell the user
            System.out.flush(); // Tell them right now
            c.connect(); // Connect to mail host
            PrintWriter out = // Get output stream to host
                    new PrintWriter(new OutputStreamWriter(c.getOutputStream()));

            // Write out mail headers. Don't let users fake the From address
            out.print("From: " + from + "\n");
            // out.print("From: \"" + from + "\" <" +
            // System.getProperty("user.name") + "@" +
            // InetAddress.getLocalHost().getHostName() + ">\n");
            out.print("To: " + to + "\n");
            out.print("Subject: " + subject + "\n");
            out.print("\n"); // blank line to end the list of headers

            // Now ask the user to enter the body of the message
            System.out.println("Enter the message. " + "End with a '.' on a line by itself.");
            // Read message line by line and send it out.
            String line;
            for (; ; ) {
                line = in.readLine();
                if ((line == null) || line.equals("."))
                    break;
                out.print(line + "\n");
            }

            // Close (and flush) the stream to terminate the message
            out.close();
            // Tell the user it was successfully sent.
            System.out.println("Message sent.");
        } catch (Exception e) { // Handle any exceptions, print error message.
            System.err.println(e);
            // System.err.println("Usage: java SendMail [<mailhost>]");
        }
    }
}
