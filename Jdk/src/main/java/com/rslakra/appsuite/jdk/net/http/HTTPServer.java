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

import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements a multithreaded simple HTTP server that supports the
 * GET request method. It listens on port 44, waits client requests, and serves
 * documents.
 * 
 * @author rohtash.singh Created on May 24, 2005
 */

public class HTTPServer {
	// The port number which the server will be listening on
	public static final int HTTP_PORT = 8080;

	public ServerSocket getServer() throws Exception {
		return new ServerSocket(HTTP_PORT);
	}

	// multi-threading -- create a new connection for each request
	public void run() {
		ServerSocket listen;
		try {
			listen = getServer();
			while (true) {
				Socket client = listen.accept();
				// ProcessConnection cc = new ProcessConnection(client);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}

	// main program
	public static void main(String argv[]) throws Exception {
		HTTPServer httpserver = new HTTPServer();
		httpserver.run();
	}
}
