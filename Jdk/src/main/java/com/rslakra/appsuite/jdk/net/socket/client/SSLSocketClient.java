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
package com.rslakra.appsuite.jdk.net.socket.client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/*
 * This example demostrates how to use a SSLSocket as client to
 * send a HTTP request and get response from an HTTPS server.
 * It assumes that the client is not behind a firewall
 */
public class SSLSocketClient {

	public static void main(String[] args) throws Exception {
		try {
			SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket socket = (SSLSocket) factory.createSocket("www.verisign.com", 443);

			/*
			 * send http request
			 *
			 * Before any application data is sent or received, the SSL socket
			 * will do SSL handshaking first to set up the security attributes.
			 *
			 * SSL handshaking can be initiated by either flushing data down the
			 * pipe, or by starting the handshaking by hand.
			 *
			 * Handshaking is started manually in this example because
			 * PrintWriter catches all IOExceptions (including SSLExceptions),
			 * sets an internal error flag, and then returns without rethrowing
			 * the exception.
			 *
			 * Unfortunately, this means any error messages are lost, which
			 * caused lots of confusion for others using this code. The only way
			 * to tell there was an error is to call PrintWriter.checkError().
			 */
			socket.startHandshake();

			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

			out.println("GET / HTTP/1.0");
			out.println();
			out.flush();

			/*
			 * Make sure there were no surprises
			 */
			if (out.checkError())
				System.out.println("SSLSocketClient:  java.io.PrintWriter error");

			/* read response */
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);

			in.close();
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
