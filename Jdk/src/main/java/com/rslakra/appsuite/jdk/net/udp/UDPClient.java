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
package com.rslakra.appsuite.jdk.net.udp;

import com.rslakra.appsuite.jdk.net.SenderThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by IntelliJ IDEA. User: rohtash.singh Date: Jun 16, 2005 Time:
 * 11:33:47 AM To change this template use Options | File Templates.
 */
public class UDPClient {
    public final static int DEFAULT_PORT = 1601;

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = DEFAULT_PORT;
        try {
            InetAddress iNetAddress = InetAddress.getByName(hostname);
            SenderThread sender = new SenderThread(iNetAddress, port);
            sender.start();
            // ReceiverThread receiver = new ReceiverThread(sender.getSocket());
            // receiver.start();
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (SocketException se) {
            System.err.println(se);
        }
    } // end main
}

//// Sender Thread
// class SenderThread extends Thread {
// private InetAddress server;
// private int port;
// private DatagramSocket socket;
// private boolean stopped = false;
//
// public SenderThread(InetAddress server, int port) throws SocketException {
// this.server = server;
// this.port = port;
// this.socket = new DatagramSocket();
// }
//
// public void halt() {
// this.stopped = true;
// }
//
// public DatagramSocket getSocket() {
// return this.socket;
// }
//
// public void run() {
// try {
// BufferedReader userInput = new BufferedReader(new
//// InputStreamReader(System.in));
// while (true) {
// if (stopped)
// return;
// String theLine = userInput.readLine();
// if (theLine.equals("."))
// break;
// byte[] data = theLine.getBytes();
// DatagramPacket output = new DatagramPacket(data, data.length, server, port);
// socket.send(output);
// Thread.yield();
// }//end while
// } // end try
// catch (IOException e) {
// System.err.println(e);
// }
// } // end run
// } //end Sender Thread

//

class ReceiverThread extends Thread {
    DatagramSocket socket;
    private boolean stopped = false;

    public ReceiverThread(DatagramSocket ds) throws SocketException {
        this.socket = ds;
    }

    public void halt() {
        this.stopped = true;
    }

    public void run() {
        byte[] buffer = new byte[65507];
        while (true) {
            if (stopped)
                return;
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(dp);
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println(s);
                Thread.yield();
            } catch (IOException e) {
                System.err.println(e);
            }
        } // end while
    } // end run
} // end Receiver Thread
