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
package com.rslakra.appsuite.jdk.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author rohtash.singh
 *         May 24, 2005
 */
public class ProcessConnection extends Thread {
	private Socket client;
	private BufferedReader bReader;
	private DataOutputStream outputStream;
	
	public ProcessConnection(Socket socket) {
		this.client = socket;
		try {
			bReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			outputStream = new DataOutputStream(client.getOutputStream());
		} catch(IOException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		// Start Thread.
		this.start();
	}
	
	public void run() {
		try {
			// get a request and parse it.
			String request = bReader.readLine();
			System.out.println("Request: " + request);
			StringTokenizer st = new StringTokenizer(request);
			if((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
				if((request = st.nextToken()).startsWith("/")) {
					request = request.substring(1);
				}
				
				if(request.equals("")) {
					request = request + "index.html";
				}
				System.out.println("request : " + request);
				sendData(outputStream, new File(request));
			} else {
				outputStream.writeBytes("400 Bad Request");
			}
			client.close();
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * Read the requested file and ships it
	 * to the browser if found.
	 */
	public static void sendData(DataOutputStream outputStream, File file) throws Exception {
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int len = (int) file.length();
			byte[] buf = new byte[len];
			in.readFully(buf);
			in.close();
			outputStream.writeBytes("HTTP/1.0 200 OK\r\n");
			outputStream.writeBytes("Content-Length:" + len + "\r\n");
			outputStream.writeBytes("Content-Type: text/html\r\n\r\n");
			outputStream.write(buf);
			outputStream.flush();
		} catch(Exception e) {
			outputStream.writeBytes("<html><head><title>error</title></head><body>\r\n\r\n");
			outputStream.writeBytes("HTTP/1.0 400 " + e.getMessage() + "\r\n");
			outputStream.writeBytes("Content-Type: text/html\r\n\r\n");
			outputStream.writeBytes("</body></html>");
			outputStream.flush();
		} finally {
			outputStream.close();
		}
	}
}
