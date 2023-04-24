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
package com.rslakra.appsuite.jdk.net.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/**
 * @author rohtash.singh Created on May 25, 2005
 */
public class HTTPClient {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// // Check the arguments
			// if ((args.length != 1) && (args.length != 2))
			// throw new IllegalArgumentException("Wrong number of args");
			//
			// Get an output stream to write the URL contents to
			OutputStream to_file;
			// to_file = new FileOutputStream(args[1]);
			to_file = System.out;

			// Now use the URL class to parse the user-specified URL into
			// its various parts.
			URL url = new URL("http://10.0.61.151:1601");
			String protocol = url.getProtocol();
			if (!protocol.equals("http")) // Check that we support the protocol
				throw new IllegalArgumentException("Must use 'http:' protocol");
			String host = url.getHost();
			int port = url.getPort();
			if (port == -1)
				port = 80; // if no port, use the default HTTP port
			String filename = url.getFile();

			// Open a network socket connection to the specified host and port
			Socket socket = new Socket(host, port);

			// Get input and output streams for the socket
			InputStream from_server = socket.getInputStream();
			PrintWriter to_server = new PrintWriter(socket.getOutputStream());

			// Send the HTTP GET command to the Web server, specifying the file
			// This uses an old and very simple version of the HTTP protocol
			to_server.print("GET " + filename + "\n\n");
			to_server.flush(); // Send it right now!

			// Now read the server's response, and write it to the file
			byte[] buffer = new byte[4096];
			int bytes_read;
			while ((bytes_read = from_server.read(buffer)) != -1)
				to_file.write(buffer, 0, bytes_read);

			// When the server closes the connection, we close our stuff
			socket.close();
			to_file.close();
		} catch (Exception e) { // Report any errors that arise
			e.printStackTrace();
		}
	}
}
