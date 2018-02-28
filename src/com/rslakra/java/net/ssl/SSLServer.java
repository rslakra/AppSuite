/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license
 * agreements. The reproduction, transmission or use of this code, in source
 * and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
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
package com.rslakra.java.net.ssl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import com.devamatre.core.IOHelper;
import com.rslakra.java.net.NetConstants;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com) May 24, 2005
 * @version 1.0.0
 * @since 1.0.0
 */
public class SSLServer {

	public static void main(String[] args) {
		char ksPass[] = NetConstants.KEY_PASSWORD.toCharArray();
		char ctPass[] = ksPass;

		BufferedWriter out;
		BufferedReader in;

		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			String filePath = IOHelper.filePath(SSLServer.class);
			filePath = IOHelper.pathString(filePath, "serverCert");
			System.out.println("filePath:" + filePath);
			ks.load(new FileInputStream(filePath), ksPass);
			System.out.println(ks.getProvider());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, ctPass);
			SSLContext sc = SSLContext.getInstance("SSLv3");
			sc.init(kmf.getKeyManagers(), null, null);
			SSLServerSocketFactory ssf = sc.getServerSocketFactory();
			SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(1601);
			IOHelper.logServerSocket(serverSocket);
			SSLSocket socket = (SSLSocket) serverSocket.accept();
			IOHelper.logSocket(socket);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = "Welcome to SSL Server:\n";
			out.write(msg, 0, msg.length());
			out.flush();
			while ((msg = in.readLine()) != null) {
				if (msg.equals("."))
					break;
				char[] a = msg.toCharArray();
				int n = a.length;
				for (int i = 0; i < n / 2; i++) {
					char t = a[i];
					a[i] = a[n - 1 - i];
					a[n - i - 1] = t;
				}
				out.write(a, 0, n);
				out.newLine();
				out.flush();
			}
			out.close();
			in.close();
			socket.close();
			serverSocket.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
