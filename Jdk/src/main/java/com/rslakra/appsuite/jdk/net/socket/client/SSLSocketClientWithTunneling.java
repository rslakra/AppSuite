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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/*
 * @(#)SSLSocketClientWithTunneling.java	1.3 01/05/10
 *
 * Copyright 1995-2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 * notice, this  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduct the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any 
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND 
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY 
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY 
 * DAMAGES OR LIABILITIES  SUFFERED BY LICENSEE AS A RESULT OF  OR 
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR 
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE 
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, 
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER 
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF 
 * THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility. 
 */
import java.net.Socket;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/*
 * This example illustrates how to do proxy Tunneling to access a
 * secure web server from behind a firewall.
 *
 * Please set the following Java system properties
 * to the appropriate values:
 *
 *   https.proxyHost = <secure proxy server hostname>
 *   https.proxyPort = <secure proxy server port>
 */

public class SSLSocketClientWithTunneling {
	
	public static void main(String[] args) throws Exception {
		new SSLSocketClientWithTunneling().doIt("www.verisign.com", 443);
	}
	
	String tunnelHost;
	int tunnelPort;
	
	public void doIt(String host, int port) {
		try {
			
			/*
			 * Let's setup the SSLContext first, as there's a lot of
			 * computations to be done. If the socket were created before the
			 * SSLContext, the server/proxy might timeout waiting for the client
			 * to actually send something.
			 */
			SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			
			/*
			 * Set up a socket to do tunneling through the proxy. Start it off
			 * as a regular socket, then layer SSL over the top of it.
			 */
			tunnelHost = System.getProperty("https.proxyHost");
			tunnelPort = Integer.getInteger("https.proxyPort").intValue();
			
			Socket tunnel = new Socket(tunnelHost, tunnelPort);
			doTunnelHandshake(tunnel, host, port);
			
			/*
			 * Ok, let's overlay the tunnel socket with SSL.
			 */
			SSLSocket socket = (SSLSocket) factory.createSocket(tunnel, host, port, true);
			
			/*
			 * register a callback for handshaking completion event
			 */
			socket.addHandshakeCompletedListener(new HandshakeCompletedListener() {
				public void handshakeCompleted(HandshakeCompletedEvent event) {
					System.out.println("Handshake finished!");
					System.out.println("\t CipherSuite:" + event.getCipherSuite());
					System.out.println("\t SessionId " + event.getSession());
					System.out.println("\t PeerHost " + event.getSession().getPeerHost());
				}
			});
			
			/*
			 * send http request
			 *
			 * See SSLSocketClient.java for more information about why there is
			 * a forced handshake here when using PrintWriters.
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
			tunnel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Tell our tunnel where we want to CONNECT, and look for the right reply.
	 * Throw IOException if anything goes wrong.
	 */
	private void doTunnelHandshake(Socket tunnel, String host, int port) throws IOException {
		OutputStream out = tunnel.getOutputStream();
		String msg = "CONNECT " + host + ":" + port + " HTTP/1.0\n" + "User-Agent: HttpClient\r\n\r\n";
		byte b[];
		try {
			/*
			 * We really do want ASCII7 -- the http protocol doesn't change with
			 * locale.
			 */
			b = msg.getBytes("ASCII7");
			
		} catch (UnsupportedEncodingException ignored) {
			/*
			 * If ASCII7 isn't there, something serious is wrong, but Paranoia
			 * Is Good (tm)
			 */
			b = msg.getBytes();
		}
		out.write(b);
		out.flush();
		
		/*
		 * We need to store the reply so we can create a detailed error message
		 * to the user.
		 */
		byte reply[] = new byte[200];
		int replyLen = 0;
		int newlinesSeen = 0;
		boolean headerDone = false; /* Done on first newline */
		
		InputStream in = tunnel.getInputStream();
		boolean error = false;
		
		while (newlinesSeen < 2) {
			int i = in.read();
			if (i < 0) {
				throw new IOException("Unexpected EOF from proxy");
			}
			if (i == '\n') {
				headerDone = true;
				++newlinesSeen;
			} else if (i != '\r') {
				newlinesSeen = 0;
				if (!headerDone && replyLen < reply.length) {
					reply[replyLen++] = (byte) i;
				}
			}
		}
		
		/*
		 * Converting the byte array to a string is slightly wasteful in the
		 * case where the connection was successful, but it's insignificant
		 * compared to the network overhead.
		 */
		String replyStr;
		try {
			replyStr = new String(reply, 0, replyLen, "ASCII7");
		} catch (UnsupportedEncodingException ignored) {
			replyStr = new String(reply, 0, replyLen);
		}
		
		/* We asked for HTTP/1.0, so we should get that back */
		if (!replyStr.startsWith("HTTP/1.0 200")) {
			throw new IOException("Unable to tunnel through " + tunnelHost + ":" + tunnelPort + ".  Proxy returns \"" + replyStr + "\"");
		}
		
		/* tunneling Handshake was successful! */
	}
}
