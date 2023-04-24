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
package com.rslakra.appsuite.jdk.net.socket.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Based on ClassServer.java in tutorial/rmi
 */
public abstract class ClassServer implements Runnable {
	
	private ServerSocket server = null;
	
	/**
	 * Constructs a ClassServer based on <b>ss</b> and obtains a file's
	 * bytecodes using the method <b>getBytes</b>.
	 *
	 */
	protected ClassServer(ServerSocket ss) {
		server = ss;
		newListener();
	}
	
	/**
	 * Returns an array of bytes containing the bytes for the file represented
	 * by the argument <b>path</b>.
	 *
	 * @return the bytes for the file
	 * @exception FileNotFoundException
	 *                if the file corresponding to <b>path</b> could not be
	 *                loaded.
	 * @exception IOException
	 *                if error occurs reading the class
	 */
	public abstract byte[] getBytes(String path) throws IOException, FileNotFoundException;
	
	/**
	 * The "listen" thread that accepts a connection to the server, parses the
	 * header to obtain the file name and sends back the bytes for the file (or
	 * error if the file is not found or the response was malformed).
	 */
	public void run() {
		Socket socket;
		
		// accept a connection
		try {
			socket = server.accept();
		} catch (IOException e) {
			System.out.println("Class Server died: " + e.getMessage());
			e.printStackTrace();
			return;
		}
		
		// create a new thread to accept the next connection
		newListener();
		
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			try {
				// get path to class file from header
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String path = getPath(in);
				// retrieve bytecodes
				byte[] bytecodes = getBytes(path);
				// send bytecodes in response (assumes HTTP/1.0 or later)
				try {
					out.writeBytes("HTTP/1.0 200 OK\r\n");
					out.writeBytes("Content-Length: " + bytecodes.length + "\r\n");
					out.writeBytes("Content-Type: text/html\r\n\r\n");
					out.write(bytecodes);
					out.flush();
				} catch (IOException ie) {
					ie.printStackTrace();
					return;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// write out error response
				out.writeBytes("HTTP/1.0 400 " + e.getMessage() + "\r\n");
				out.writeBytes("Content-Type: text/html\r\n\r\n");
				out.flush();
			}
			
		} catch (IOException ex) {
			// eat exception (could log error to log file, but
			// write out to stdout for now).
			System.out.println("error writing response: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * Create a new thread to listen.
	 */
	private void newListener() {
		(new Thread(this)).start();
	}
	
	/**
	 * Returns the path to the file obtained from parsing the HTML header.
	 */
	private static String getPath(BufferedReader in) throws IOException {
		String line = in.readLine();
		String path = "";
		// extract class from GET line
		if (line.startsWith("GET /")) {
			line = line.substring(5, line.length() - 1).trim();
			int index = line.indexOf(' ');
			if (index != -1) {
				path = line.substring(0, index);
			}
		}
		
		// eat the rest of header
		do {
			line = in.readLine();
		} while ((line.length() != 0) && (line.charAt(0) != '\r') && (line.charAt(0) != '\n'));
		
		if (path.length() != 0) {
			return path;
		} else {
			throw new IOException("Malformed Header");
		}
	}
}
