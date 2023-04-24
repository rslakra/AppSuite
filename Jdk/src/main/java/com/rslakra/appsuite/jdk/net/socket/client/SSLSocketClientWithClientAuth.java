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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/*
 * This example shows how to set up a key manager to do client
 * authentication if required by server.
 *
 * This program assumes that the client is not inside a firewall.
 * The application can be modified to connect to a server outside
 * the firewall by following SSLSocketClientWithTunneling.java.
 */
public class SSLSocketClientWithClientAuth {

	public static void main(String[] args) throws Exception {
		String host = null;
		int port = -1;
		String path = null;
		for (int i = 0; i < args.length; i++)
			System.out.println(args[i]);

		if (args.length < 3) {
			System.out.println("USAGE: java SSLSocketClientWithClientAuth " + "host port requestedfilepath");
			System.exit(-1);
		}

		try {
			host = args[0];
			port = Integer.parseInt(args[1]);
			path = args[2];
		} catch (IllegalArgumentException e) {
			System.out.println("USAGE: java SSLSocketClientWithClientAuth " + "host port requestedfilepath");
			System.exit(-1);
		}

		try {

			/*
			 * Set up a key manager for client authentication if asked by the
			 * server. Use the implementation's default TrustStore and
			 * secureRandom routines.
			 */
			SSLSocketFactory factory = null;
			try {
				SSLContext ctx;
				KeyManagerFactory kmf;
				KeyStore ks;
				char[] passphrase = "passphrase".toCharArray();

				ctx = SSLContext.getInstance("TLS");
				kmf = KeyManagerFactory.getInstance("SunX509");
				ks = KeyStore.getInstance("JKS");

				ks.load(new FileInputStream("testkeys"), passphrase);

				kmf.init(ks, passphrase);
				ctx.init(kmf.getKeyManagers(), null, null);

				factory = ctx.getSocketFactory();
			} catch (Exception e) {
				throw new IOException(e.getMessage());
			}

			SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

			/*
			 * send http request
			 *
			 * See SSLSocketClient.java for more information about why there is
			 * a forced handshake here when using PrintWriters.
			 */
			socket.startHandshake();

			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			out.println("GET " + path + " HTTP/1.0");
			out.println();
			out.flush();

			/*
			 * Make sure there were no surprises
			 */
			if (out.checkError())
				System.out.println("SSLSocketClient: java.io.PrintWriter error");

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
