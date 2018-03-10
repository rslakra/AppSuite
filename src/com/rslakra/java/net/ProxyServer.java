///******************************************************************************
// * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
// * 
// * This code is licensed to Devamatre under one or more contributor license 
// * agreements. The reproduction, transmission or use of this code, in source 
// * and binary forms, with or without modification, are permitted provided 
// * that the following conditions are met:
// * 1. Redistributions of source code must retain the above copyright
// * 	  notice, this list of conditions and the following disclaimer.
// * 2. Redistributions in binary form must reproduce the above copyright
// *    notice, this list of conditions and the following disclaimer in the
// *    documentation and/or other materials provided with the distribution.
// * 
// * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
// * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
// * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
// * SUCH DAMAGE.
// *      
// * Devamatre reserves the right to modify the technical specifications and or 
// * features without any prior notice.
// *****************************************************************************/
//package com.rslakra.java.net;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.Socket;
//
///**
// * This class uses the Server class to provide a multi-threaded server
// * framework for a relatively simple proxy service. The main() method
// * starts up the server. The nested Proxy class implements the
// * Server.Service interface and provides the proxy service.
// **/
//public class ProxyServer {
//	/**
//	 * Create a Server object, and add Proxy service objects to it to provide
//	 * proxy service as specified by the command-line arguments.
//	 **/
//	public static void main(String[] args) {
//		try {
//			// Check number of args. Must be a multiple of 3 and > 0.
//			if ((args.length == 0) || (args.length % 3 != 0))
//				throw new IllegalArgumentException("Wrong number of args");
//			
//			// Create the Server object
//			Server s = new Server(null, 12); // log stream, max connections
//			
//			// Loop through the arguments parsing (host, remoteport, localport)
//			// tuples. For each, create a Proxy, and add it to the server.
//			int i = 0;
//			while (i < args.length) {
//				String host = args[i++];
//				int remoteport = Integer.parseInt(args[i++]);
//				int localport = Integer.parseInt(args[i++]);
//				s.addService(new Proxy(host, remoteport), localport);
//			}
//		} catch (Exception e) { // Print an error message if anything goes wrong
//			System.err.println(e);
//			System.err.println("Usage: java ProxyServer " + "<host> <remoteport> <localport> ...");
//			System.exit(1);
//		}
//	}
//	
//	/**
//	 * This is the class that implements the proxy service. The serve() method
//	 * will be called when the client has connected. At that point, it must
//	 * establish a connection to the server, and then transfer bytes back and
//	 * forth between client and server. For symmetry, this class implements
//	 * two very similar threads as anonymous classes. One thread copies bytes
//	 * from client to server, and the other copies them from server to client.
//	 * The thread that invoke the serve() method creates and starts these
//	 * threads, then just sits and waits for them to exit.
//	 **/
//	public static class Proxy implements Server.Service {
//		String host;
//		int port;
//		
//		/** Remember the host and port we are a proxy for */
//		public Proxy(String host, int port) {
//			this.host = host;
//			this.port = port;
//		}
//		
//		/** The server invokes this method when a client connects. */
//		public void serve(InputStream in, OutputStream out) {
//			// These are some sockets we'll use. They are final so they can
//			// be used by the anonymous classes defined below.
//			final InputStream from_client = in;
//			final OutputStream to_client = out;
//			final InputStream from_server;
//			final OutputStream to_server;
//			
//			// Try to establish a connection to the specified server and port
//			// and get sockets to talk to it. Tell our client if we fail.
//			final Socket server;
//			try {
//				server = new Socket(host, port);
//				from_server = server.getInputStream();
//				to_server = server.getOutputStream();
//			} catch (Exception e) {
//				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
//				pw.print("Proxy server could not connect to " + host + ":" + port + "\n");
//				pw.flush();
//				pw.close();
//				try {
//					in.close();
//				} catch (IOException ex) {
//				}
//				return;
//			}
//			
//			// Create an array to hold two Threads. It is declared final so
//			// that it can be used by the anonymous classes below. We use an
//			// array instead of two variables because given the structure of
//			// this program two variables would not work if declared final.
//			final Thread[] threads = new Thread[2];
//			
//			// Define and create a thread to copy bytes from client to server
//			Thread c2s = new Thread() {
//				public void run() {
//					// Copy bytes 'till EOF from client
//					byte[] buffer = new byte[2048];
//					int bytes_read;
//					try {
//						while ((bytes_read = from_client.read(buffer)) != -1) {
//							to_server.write(buffer, 0, bytes_read);
//							to_server.flush();
//						}
//					} catch (IOException e) {
//					} finally {
//						// When the thread is done
//						try {
//							server.close(); // close the server socket
//							to_client.close(); // and the client streams
//							from_client.close();
//						} catch (IOException e) {
//						}
//					}
//				}
//			};
//			
//			// Define and create a thread to copy bytes from server to client.
//			// This thread works just like the one above.
//			Thread s2c = new Thread() {
//				public void run() {
//					byte[] buffer = new byte[2048];
//					int bytes_read;
//					try {
//						while ((bytes_read = from_server.read(buffer)) != -1) {
//							to_client.write(buffer, 0, bytes_read);
//							to_client.flush();
//						}
//					} catch (IOException e) {
//					} finally {
//						try {
//							server.close(); // close down
//							to_client.close();
//							from_client.close();
//						} catch (IOException e) {
//						}
//					}
//				}
//			};
//			
//			// Store the threads into the final threads[] array, so that the
//			// anonymous classes can refer to each other.
//			threads[0] = c2s;
//			threads[1] = s2c;
//			
//			// start the threads
//			c2s.start();
//			s2c.start();
//			
//			// Wait for them to exit
//			try {
//				c2s.join();
//				s2c.join();
//			} catch (InterruptedException e) {
//			}
//		}
//	}
//}
