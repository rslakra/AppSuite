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
package com.rslakra.jdk.net.socket.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by IntelliJ IDEA. User: rohtash.singh Date: May 27, 2005 Time:
 * 5:35:29 PM To change this template use Options | File Templates.
 *
 * How to use a SSLSocket as client to send a HTTP request and get response from
 * an HTTPS server. It assumes that the client is not behind a firewall
 *
 */

public class HttpClient {
	public static final String HOST = "www.verisign.com";
	public static final int PORT = 443;

	private static void sendHTTPRequest() throws Exception {
		SSLSocketFactory sslSF = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket sslSocket = (SSLSocket) sslSF.createSocket(HOST, PORT);
		System.out.println("Host Address : " + sslSocket.getInetAddress().getHostAddress());
		System.out.println("Host Name : " + sslSocket.getInetAddress().getHostName());
		/*
		 * send http request
		 *
		 * Before any application data is sent or received, the SSL socket will
		 * do SSL handshaking first to set up the security attributes.
		 *
		 * SSL handshaking can be initiated by either flushing data down the
		 * pipe, or by starting the handshaking by hand.
		 *
		 * Handshaking is started manually in this example because PrintWriter
		 * catches all IOExceptions (including SSLExceptions), sets an internal
		 * error flag, and then returns without rethrowing the exception.
		 *
		 * Unfortunately, this means any error messages are lost, which caused
		 * lots of confusion for others using this code. The only way to tell
		 * there was an error is to call PrintWriter.checkError().
		 */
		sslSocket.startHandshake();
		System.out.println("Handshaking");
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
		out.println("GET / HTTP/1.0");
		out.println();
		out.flush();

		// Make sure there was no surprise.
		if (out.checkError()) {
			System.out.println("SSLSocket java.io.PrintWriter Error!");
		}
		// Read Response.
		BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println("HttpClient : " + line);
		}
		in.close();
		out.close();
		sslSocket.close();

	}// End

	public static void main(String[] args) {
		try {
			sendHTTPRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
