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
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class sends the specified text or file as a datagram to the
 * specified port of the specified host.
 **/
public class UDPSend {
	public static final String usage = "Usage: java UDPSend <hostname> <port> <msg>...\n" + "   or: java UDPSend <hostname> <port> -f <file>";
	
	public static void main(String args[]) {
		try {
			// Check the number of arguments
			if (args.length < 3)
				throw new IllegalArgumentException("Wrong number of args");
			
			// Parse the arguments
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			
			// Figure out the message to send.
			// If the third argument is -f, then send the contents of the file
			// specified as the fourth argument. Otherwise, concatenate the
			// third and all remaining arguments and send that.
			byte[] message;
			if (args[2].equals("-f")) {
				File f = new File(args[3]);
				int len = (int) f.length(); // figure out how big the file is
				message = new byte[len]; // create a buffer big enough
				FileInputStream in = new FileInputStream(f);
				int bytes_read = 0, n;
				do { // loop until we've read it all
					n = in.read(message, bytes_read, len - bytes_read);
					bytes_read += n;
				} while ((bytes_read < len) && (n != -1));
			} else { // Otherwise, just combine all the remaining arguments.
				String msg = args[2];
				for (int i = 3; i < args.length; i++)
					msg += " " + args[i];
				message = msg.getBytes();
			}
			
			// Get the internet address of the specified host
			InetAddress address = InetAddress.getByName(host);
			
			// Initialize a datagram packet with data and address
			DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
			
			// Create a datagram socket, send the packet through it, close it.
			DatagramSocket dsocket = new DatagramSocket();
			dsocket.send(packet);
			dsocket.close();
		} catch (Exception e) {
			System.err.println(e);
			System.err.println(usage);
		}
	}
}
