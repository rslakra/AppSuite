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

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This program waits to receive datagrams sent the specified port.
 * When it receives one, it displays the sending host and prints the
 * contents of the datagram as a string. Then it loops and waits again.
 **/
public class UDPReceive {
	public static final String usage = "Usage: java UDPReceive <port>";
	
	public static void main(String args[]) {
		try {
			if (args.length != 1)
				throw new IllegalArgumentException("Wrong number of args");
			
			// Get the port from the command line
			int port = Integer.parseInt(args[0]);
			
			// Create a socket to listen on the port.
			DatagramSocket dsocket = new DatagramSocket(port);
			
			// Create a buffer to read datagrams into. If anyone sends us a
			// packet containing more than will fit into this buffer, the
			// excess will simply be discarded!
			byte[] buffer = new byte[2048];
			
			// Create a packet to receive data into the buffer
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			// Now loop forever, waiting to receive packets and printing them.
			for (;;) {
				// Wait to receive a datagram
				dsocket.receive(packet);
				
				// Convert the contents to a string, and display them
				String msg = new String(buffer, 0, packet.getLength());
				System.out.println(packet.getAddress().getHostName() + ": " + msg);
				
				// Reset the length of the packet before reusing it.
				// Prior to Java 1.1, we'd just create a new packet each time.
				packet.setLength(buffer.length);
			}
		} catch (Exception e) {
			System.err.println(e);
			System.err.println(usage);
		}
	}
}
