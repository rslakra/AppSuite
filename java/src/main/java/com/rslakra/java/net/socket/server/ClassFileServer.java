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
package com.rslakra.java.net.socket.server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import com.devamatre.core.IOUtility;

/* ClassFileServer.java -- a simple file server that can server
 * Http get request in both clear and secure channel
 *
 * The ClassFileServer implements a ClassServer that
 * reads files from the file system. See the
 * doc for the "Main" method for how to run this
 * server.
 */

public class ClassFileServer extends ClassServer {

	private String docroot;

	private static int DefaultServerPort = 2001;

	/**
	 * Constructs a ClassFileServer.
	 *
	 * @param path the path where the server locates files
	 */
	public ClassFileServer(ServerSocket ss, String docroot) throws IOException {
		super(ss);
		this.docroot = docroot;
	}

	/**
	 * Returns an array of bytes containing the bytes for the file represented by
	 * the argument <b>path</b>.
	 *
	 * @return the bytes for the file
	 * @exception FileNotFoundException if the file corresponding to <b>path</b>
	 *                                  could not be loaded.
	 */
	public byte[] getBytes(String path) throws IOException {
		System.out.println("reading: " + path);
		File f = new File(docroot + File.separator + path);
		int length = (int) (f.length());
		if (length == 0) {
			throw new IOException("File length is zero: " + path);
		} else {
			FileInputStream fin = new FileInputStream(f);
			DataInputStream in = new DataInputStream(fin);

			byte[] bytecodes = new byte[length];
			in.readFully(bytecodes);
			IOUtility.closeSilently(fin, in);
			return bytecodes;
		}
	}

	/**
	 * Main method to create the class server that reads files. This takes two
	 * command line arguments, the port on which the server accepts requests and the
	 * root of the path. To start up the server: <br>
	 * <br>
	 *
	 * <code>   java ClassFileServer <port> <path>
	 * </code><br>
	 * <br>
	 *
	 * <code>   new ClassFileServer(port, docroot);
	 * </code>
	 */
	public static void main(String args[]) {
		System.out.println("USAGE: java ClassFileServer port docroot [TLS [true]]");
		System.out.println("");
		System.out.println("If the third argument is TLS, it will start as\n"
				+ "a TLS/SSL file server, otherwise, it will be\n" + "an ordinary file server. \n"
				+ "If the fourth argument is true,it will require\n" + "client authentication as well.");

		int port = DefaultServerPort;
		String docroot = "";

		if (args.length >= 1) {
			port = Integer.parseInt(args[0]);
		}

		if (args.length >= 2) {
			docroot = args[1];
		}
		String type = "PlainSocket";
		if (args.length >= 3) {
			type = args[2];
		}
		try {
			ServerSocketFactory ssf = ClassFileServer.getServerSocketFactory(type);
			ServerSocket ss = ssf.createServerSocket(port);
			if (args.length >= 4 && args[3].equals("true")) {
				((SSLServerSocket) ss).setNeedClientAuth(true);
			}
			new ClassFileServer(ss, docroot);
		} catch (IOException e) {
			System.out.println("Unable to start ClassServer: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static ServerSocketFactory getServerSocketFactory(String type) {
		if (type.equals("TLS")) {
			SSLServerSocketFactory ssf = null;
			try {
				// set up key manager to do server authentication
				SSLContext ctx;
				KeyManagerFactory kmf;
				KeyStore ks;
				char[] passphrase = "passphrase".toCharArray();

				ctx = SSLContext.getInstance("TLS");
				kmf = KeyManagerFactory.getInstance("SunX509");
				ks = KeyStore.getInstance("JKS");

				ks.load(new FileInputStream("testkeys"), passphrase);
				kmf.init(ks, passphrase);
				ctx.init(kmf.getKeyManagers(), null, null);

				ssf = ctx.getServerSocketFactory();
				return ssf;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return ServerSocketFactory.getDefault();
		}
		return null;
	}
}
