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
package com.rslakra.java.net.ssl;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

/**
 * @author Rohtash Singh
 *
 *         This class implements a multithreaded simple HTTP server that
 *         supports
 *         the GET request method. It listens on port 44, waits client requests,
 *         and serves documents.
 *         443 is the default port number for HTTPs.
 *         It is important to note that port numbers between 0 and 1023 are
 *         reserved.
 *         If you run HttpsServer on a different port number,
 *         the url should be: https://localhost:portnumber but if you run it on
 *         443
 *         then the URL is: https://localhost.
 *
 */

public class HttpsServer implements SSLConnection {
	
	// The port number which the server will be listening on
	// private static String keystore = "lakra/net/serverCert";
	private static char[] keystorepass = getPassword();
	private static char[] keypassword = getPassword();
	
	// keystorepass and keypassword are same in my case so i
	// have given them in a method.
	private static char[] getPassword() {
		return "rohtashlakra".toCharArray();
	}
	
	public String getCertificate() {
		return "lakra/net/serverCert";
	}
	
	/**
	 * The rest of the JSSE related code is in the getServer method:
	 * It access the serverkeys keystore.
	 * The JKS is the Java KeyStore (a type of keystore created by keytool).
	 * The KeyManagerFactory is used to create an X.509 key manager for the
	 * keystore.
	 * An SSLContext is an environment for implementing JSSE.
	 * It is used to create a ServerSocketFactory that in turn used
	 * to create a SSLServerSocket. Although we specify SSL 3.0,
	 * the implementation that is returned will often support
	 * other protocol versions, such as TLS 1.0. Older browsers,
	 * however, use SSL 3.0 more widely.
	 * Note that by default client authentication is not required.
	 * For if you wish for your server to require client authentication,
	 * use: serversocket.setNeedClientAuth(true).
	 *
	 * @return
	 * @throws Exception
	 */
	public ServerSocket getServer() throws Exception {
		String alias = "qusay";
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream(getCertificate()), keystorepass);
		System.out.println("Server Certificate : " + ks.getCertificate(alias));
		
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, keypassword);
		
		SSLContext sslContext = SSLContext.getInstance("SSLv3");
		sslContext.init(kmf.getKeyManagers(), null, null);
		
		ServerSocketFactory ssFactory = sslContext.getServerSocketFactory();
		SSLServerSocket serversocket = (SSLServerSocket) ssFactory.createServerSocket(HTTPS_PORT);
		
		return serversocket;
		// return new ServerSocket(HTTPS_PORT);
	}
	
	// multi-threading -- create a new connection for each request
	public void run() {
		ServerSocket listen;
		
		try {
			listen = getServer();
			
			while (true) {
				Socket client = listen.accept();
				System.out.println("" + client.getInetAddress());
				// ProcessConnection cc = new ProcessConnection(client);
				new ConnProcess(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:" + e.getMessage());
		}
	}
	
	// main program
	public static void main(String[] argv) throws Exception {
		HttpsServer httpsServer = new HttpsServer();
		httpsServer.run();
	}
}
