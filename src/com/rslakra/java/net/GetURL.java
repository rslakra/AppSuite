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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * This simple program uses the URL class and its openStream() method to
 * download the contents of a URL and copy them to a file or to the console.
 **/
public class GetURL {
	public static void main(String[] args) {
		InputStream in = null;
		OutputStream out = null;
		try {
			// Check the arguments
			if ((args.length != 1) && (args.length != 2))
				throw new IllegalArgumentException("Wrong number of args");
			
			// Set up the streams
			URL url = new URL(args[0]); // Create the URL
			in = url.openStream(); // Open a stream to it
			if (args.length == 2) // Get an appropriate output stream
				out = new FileOutputStream(args[1]);
			else
				out = System.out;
			
			// Now copy bytes from the URL to the output stream
			byte[] buffer = new byte[4096];
			int bytes_read;
			while ((bytes_read = in.read(buffer)) != -1)
				out.write(buffer, 0, bytes_read);
		}
		// On exceptions, print error message and usage message.
		catch (Exception e) {
			System.err.println(e);
			System.err.println("Usage: java GetURL <URL> [<filename>]");
		} finally { // Always close the streams, no matter what.
			try {
				in.close();
				out.close();
			} catch (Exception e) {
			}
		}
	}
}
