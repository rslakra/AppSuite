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
package com.rslakra.java.net;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The file server to be developed will provide the following services: List the
 * files that can be downloaded. Send the selected file. Process each request in
 * a separate thread.
 *
 * There are two classes that form the server: FileServer - sets up the server.
 * ConnectionHandler - services the requests for sending files to clients.
 *
 * First comes the FileServer class. It does the following tasks: Checks the
 * existence of the directory name specified. Sets up the server. Delegates the
 * requests to be handled to an object of the ConnectionHandler class.
 *
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @since Aug 3, 2010
 *
 */
public class FileServer {

	static final int LISTENING_PORT = 3210;

	public static void main(String[] args) {

		File directory; // The directory from which gets the files that it
						// serves.
		ServerSocket listener; // Listens for connection requests.
		Socket connection; // A socket for communicating with a client.

		/*
		 * Check that there is a command-line argument. If not, print a usage
		 * message and end.
		 */
		if (args.length == 0) {
			System.out.println("Usage: java FileServer <directory>");
			return;
		}

		/*
		 * Get the directory name from the command line, and make it into a file
		 * object. Check that the file exists and is in fact a directory.
		 */
		directory = new File(args[0]);
		if (!directory.exists()) {
			System.out.println("Specified directory does not exist.");
			return;
		}
		if (!directory.isDirectory()) {
			System.out.println("The specified file is not a directory.");
			return;
		}

		/*
		 * Listen for connection requests from clients. For each connection,
		 * create a separate Thread of type ConnectionHandler to process it. The
		 * ConnectionHandler class is defined below. The server runs until the
		 * program is terminated, for example by a CONTROL-C.
		 */

		try {
			listener = new ServerSocket(LISTENING_PORT);
			System.out.println("Listening on port " + LISTENING_PORT);
			while (true) {
				connection = listener.accept();
				/*
				 * TODO: Uncomment the below line and resolve errors.
				 */
				// new ConnectionHandler(directory, connection);
			}
		} catch (Exception e) {
			System.out.println("Server shut down unexpectedly.");
			System.out.println("Error: " + e);
			return;
		}
	} // end main()
}