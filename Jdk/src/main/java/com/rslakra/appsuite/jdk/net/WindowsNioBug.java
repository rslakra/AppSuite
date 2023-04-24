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
package com.rslakra.appsuite.jdk.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2009-08-09 03:20:33 PM
 * @since 1.0.0
 */
public class WindowsNioBug implements Runnable {
    /**
     * The connection selector for the server socket. This is used to find
     * connections
     */
    private Selector connectionSelector;

    /**
     * The server socket that is listening for connections
     */
    private ServerSocketChannel ssc;

    /**
     * The socket address that we are listening on. This is an argument to the
     * constructor
     */
    private SocketAddress address;

    public WindowsNioBug() {
        address = new InetSocketAddress(8987);
        System.out.println("Opening socket on port 8987");
        try {
            ssc = ServerSocketChannel.open();
            this.connectionSelector = Selector.open();
            this.ssc.configureBlocking(false);
            this.ssc.socket().bind(address);
            this.ssc.register(this.connectionSelector, SelectionKey.OP_ACCEPT);
        } catch (IOException ex) {
            System.out.println("Error opening the socket on port 8987");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Creating new instance of WindowsNioBug");
        WindowsNioBug bug = new WindowsNioBug();
        Thread t = new Thread(bug);
        t.start();

        System.out.println("Waiting 3 seconds, then attempting to connect");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
        System.out.println("Now attemting to connect");
        try {
            Socket s = new Socket();
            s.connect(bug.address);
            s.close();
            System.out.println("Done connecting");
        } catch (IOException ex1) {
            System.out.println("Error while connecting to server socket");
            ex1.printStackTrace();
        }
        System.out.println(
                "Now, unplug your network cable and wait about 15 seconds.  The loop in listenForConnections() will spin infinately around Selector.select()");
        System.out.println("This is not the behavoir");
    }

    public void run() {
        System.out.println("Listening for connections.  Telnet to port 8987 to prove that it's working");
        listenForConnections();
    }

    /**
     * Listens for connections on the server socket and hands them off to
     * handleConnection(SocketChanel sc) when a connection is received.
     */
    private void listenForConnections() {
        System.out.println("listenForConnections");
        try {
            while (true) {
                // this next operation is the one that is broken
                /**
                 * select all connections that need to be serviced, blocks until
                 * service requiered
                 */
                int keys = connectionSelector.select();

                System.out.println("<accept loop>  connectionSelector.select() has returned " + keys);
                System.out.println("<accept loop> ssc.isOpen() " + ssc.isOpen());
                System.out.println("<accept loop> ssc.isRegistered() " + ssc.isRegistered());
                System.out.println("<accept loop> ssc.isBlocking() " + ssc.isBlocking());

                // iterate through available connections
                for (Iterator i = connectionSelector.selectedKeys().iterator(); i.hasNext(); ) {
                    SelectionKey key = (SelectionKey) i.next();
                    i.remove();
                    System.out.println("<accept loop> Selected key: " + key);
                    ServerSocketChannel readyServer = (ServerSocketChannel) key.channel();
                    // this is non-blocking, returns null if none are ready
                    SocketChannel sc = readyServer.accept();
                    if (sc != null) {
                        System.out.println("<accept loop> The connection was good, closing");
                        sc.close();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("<accept loop> IOException while handling connections");
            ex.printStackTrace();
        } finally {
            System.out.println("<accept loop> exiting AcceptConnectionThread.listenForConnections");
        }
    }
}
