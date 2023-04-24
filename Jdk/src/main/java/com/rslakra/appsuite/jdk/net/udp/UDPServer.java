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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer extends Thread {
    //
    public final static int DEFAULT_PORT = 1601;
    public final static int DEFAULT_BUFFER_SIZE = 8096;
    //
    private int bufferSize = DEFAULT_BUFFER_SIZE; // in bytes
    protected DatagramSocket datagramSocket;

    public UDPServer(int port, int bufferSize) throws SocketException {
        this.bufferSize = bufferSize;
        this.datagramSocket = new DatagramSocket(port);
    }

    public UDPServer(int port) throws SocketException {
        this(port, DEFAULT_BUFFER_SIZE);
    }

    public UDPServer() throws SocketException {
        this(DEFAULT_PORT);
    } // end Default Constructor

    public void run() {
        byte[] buffer = new byte[bufferSize];
        while (true) {
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            try {
                datagramSocket.receive(incoming); // Populdate data in buffer
                datagramSocket.send(incoming);
                // Print Request on Server
                String message = new String(incoming.getData(), 0, incoming.getLength());
                System.out.println(incoming.getSocketAddress() + " : " + message.toString());
                Thread.yield();
            } catch (IOException e) {
                System.err.println(e);
            }
        } // end while
    } // end run

    // Starting Point of Server
    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer();
            server.start();
        } catch (SocketException e) {
            System.err.println(e);
        }
    }

}
