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
package com.rslakra.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectStreamException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

/**
 * 
 * 
 * @author rohtash.singh
 * @version Apr 11, 2006
 */

public final class NetHelper implements Serializable {

	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = -9076251744849057012L;

	/** instance */
	private static NetHelper instance;

	/** Singleton Pattern. */
	private NetHelper() {
	}

	/**
	 * To make a singleton class serializable, it is not only sufficient to add
	 * implements <code>Serializable</code> to its declaration. To maintain the
	 * singleton guarantee, you must also provide a <code>readResolve</code>
	 * method.
	 * 
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return instance;
	}

	/**
	 * @return NetHelper
	 */
	public synchronized static NetHelper getInstance() {
		if (instance == null) {
			synchronized (NetHelper.class) {
				if (instance == null) {
					instance = new NetHelper();
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int num = 350864394;
		String ipAddress = "10.196.233.20";
		System.out.println("\nOriginal Values, num : " + num + ", ipAddress : " + ipAddress);
		String newIPAddress = NetHelper.toIPAddress(num);
		long ipToNum = NetHelper.toIPNumber(newIPAddress);
		System.out.println("\nAfter Conversion, ipToNum : " + ipToNum + ", newIPAddress : " + newIPAddress);
	}

	/**
	 * 
	 * @param ipNumber
	 * @return
	 */
	public static String toIPAddress(int ipNumber) {
		return (ipNumber & 0xFF) + "." + ((ipNumber >> 8) & 0xFF) + "." + ((ipNumber >> 16) & 0xFF) + "."
				+ ((ipNumber >> 24) & 0xFF);
	}

	/**
	 * 
	 * @param ipAddress
	 * @return
	 */
	public static long toIPNumber(String ipAddress) {
		String[] addrArray = ipAddress.split("\\.");
		long ipNumber = 0;
		for (int i = 0; i < addrArray.length; i++) {
			ipNumber += ((Integer.parseInt(addrArray[i]) % 256 * Math.pow(256, i)));
		}

		return ipNumber;
	}

	/**
	 * Make a BufferedReader to get incoming data.
	 * 
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static BufferedReader getReader(Socket socket) throws IOException {
		return (new BufferedReader(new InputStreamReader(socket.getInputStream())));
	}

	/**
	 * Make a PrintWriter to send outgoing data. This PrintWriter will
	 * automatically flush stream when <code>println(...)</code> is called.
	 * 
	 * 
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static PrintWriter getWriter(Socket socket) throws IOException {
		/* autoFlush set to be true */
		return (new PrintWriter(socket.getOutputStream(), true));
	}
}