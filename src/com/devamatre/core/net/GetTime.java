/***************************************************************************
 * Copyright (C) Lakra Software Inc. 2009
 *
 * The reproduction, transmission or use of this document or its contents is
 * not permitted without prior express written consent of Lakra Software Inc.
 * Offenders will be liable for damages. All rights, including  but not 
 * limited to rights created by patent grant or registration of a utility
 * model or design, are reserved.
 *
 * Lakra Software Inc. reserves the right to modify technical specifications
 * and features.
 *
 * Technical specifications and features are binding only insofar as they
 * are specifically and expressly agreed upon in a written contract.
 * 
 * See license.txt for more details about the allowed used of this software.
 *
 * Created on Aug 9, 2009
 * Rohtash Singh (rohtash.singh@gmail.com)
 **************************************************************************/
package com.devamatre.core.net;

import java.io.*;
import java.net.*;

public class GetTime {
	final private static int DAYTIME_PORT = 13;

	public static void main(String args[]) throws IOException {
//    if (args.length == 0) {
//      System.err.println("Please specify daytime host");
//      System.exit(-1);
//    }
//    String host = args[0];
    String host = "10.0.55.125";
    byte message[] = new byte[256];
    InetAddress address = InetAddress.getByName(host);
    System.out.println("Checking at: " + address);
    DatagramPacket packet = new DatagramPacket(message, message.length, address, DAYTIME_PORT);
    DatagramSocket socket = new DatagramSocket();
    socket.send(packet);
    packet = new DatagramPacket(message, message.length);
    socket.receive(packet);
    String time = new String(packet.getData());
    System.out.println("The time at " + host + " is: " + time);
    socket.close();
  }
}