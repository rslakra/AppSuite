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
package com.rslakra.appsuite.jdk.net.socket.client;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA. User: rohtash.singh Date: May 30, 2005 Time:
 * 11:01:36 AM To change this template use Options | File Templates.
 * <p>
 * <p>
 * This example illustrates how to do proxy Tunneling to access a secure web
 * server from behind a firewall.
 * Please set the following Java system properties to the appropriate values:
 * https.proxyHost = <secure proxy server hostname> https.proxyPort = <secure
 * proxy server port>
 */

public class HttpsClient {
    private String proxyHost;
    private int proxyPort;

    /**
     * @param host
     * @param port
     */
    public HttpsClient(String host, int port) {
        setProxyHostAndPort();
        try {
            /*
             * Let's setup the SSLContext first, as there's a lot of
             * computations to be done. If the socket were created before the
             * SSLContext, the server/proxy might timeout waiting for the client
             * to actually send something.
             */
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            /*
             * Set up a socket to do tunneling through the proxy. Start it off
             * as a regular socket, then layer SSL over the top of it.
             */
            this.proxyHost = System.getProperty("https.proxyHost");
            this.proxyPort = Integer.getInteger("https.proxyPort").intValue();
            System.out.println("proxyHost : " + getProxyHost());
            System.out.println("proxyHost : " + getProxyPort());
            Socket socket = new Socket(getProxyHost(), getProxyPort());
            doHandshaking(socket, host, port);

            /*
             * Ok, let's overlay the tunnel socket with SSL.
             */
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(socket, host, port, true);

            /*
             * register a callback for handshaking completion event
             */
            sslSocket.addHandshakeCompletedListener(new HandshakeCompletedListener() {
                public void handshakeCompleted(HandshakeCompletedEvent event) {
                    System.out.println("Handshaking Completed!");
                    System.out.println("CipherSuite : " + event.getCipherSuite());
                    System.out.println("Session : " + event.getSession());
                    System.out.println("PeerHost : " + event.getSession().getPeerHost());
                }
            });

            /*
             * send http request
             * See SSLSocketClient.java for more information about why there is
             * a forced handshake here when using PrintWriters.
             */
            sslSocket.startHandshake();

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();
            /*
             * Make sure there were no surprises
             */
            if (out.checkError())
                System.out.println("HttpsClient:  java.io.PrintWriter error");

            // Read Response
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Clinet:" + line);
            }

            in.close();
            out.close();
            sslSocket.close();
            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setProxyHostAndPort() {
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyHost", "443");
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    /*
     * Tell our socket where we want to CONNECT, and look for the right reply.
     * Throw IOException if anything goes wrong.
     */
    private void doHandshaking(Socket skt, String host, int port) throws IOException {
        OutputStream out = skt.getOutputStream();
        String msg = "CONNECT " + host + ":" + port + " HTTP/1.0\n" + "User-Agent: HttpClient\r\n\r\n";
        System.out.println("msg : " + msg);
        byte[] bytes;
        try {
            /*
             * We really do want ASCII7 -- the http protocol doesn't change with
             * locale.
             */
            bytes = msg.getBytes("ASCII7");

        } catch (UnsupportedEncodingException ignored) {
            /*
             * If ASCII7 isn't there, something serious is wrong, but Paranoia
             * Is Good (tm)
             */
            bytes = msg.getBytes();
        }
        System.out.println("bytes : " + bytes.toString());
        out.write(bytes);
        out.flush();
        /*
         * We need to store the reply so we can create a detailed error message
         * to the user.
         */
        byte reply[] = new byte[200];
        int replyLen = 0;
        int newlinesSeen = 0;
        boolean headerDone = false; /* Done on first newline */
        InputStream in = skt.getInputStream();
        while (newlinesSeen < 2) {
            int input = in.read();
            System.out.println("input : " + input);
            if (input <= 0) {
                throw new IOException("Unexpected EOF from Server!");
            }
            if (input == '\n') {
                headerDone = true;
                ++newlinesSeen;
            } else if (input != '\r') {
                newlinesSeen = 0;
                if (!headerDone && replyLen < reply.length) {
                    reply[replyLen++] = (byte) input;
                }
            }
        }
        /*
         * Converting the byte array to a string is slightly wasteful in the
         * case where the connection was successful, but it's insignificant
         * compared to the network overhead.
         */
        String replyStr;
        try {
            replyStr = new String(reply, 0, replyLen, "ASCII7");
        } catch (UnsupportedEncodingException ignored) {
            replyStr = new String(reply, 0, replyLen);
            System.out.println(ignored.getMessage());
        }

        /* We asked for HTTP/1.0, so we should get that back */
        if (!replyStr.startsWith("HTTP/1.0 200")) {
            throw new IOException("Unable to socket through " + getProxyHost() + ":" + getProxyPort() + ".  Proxy returns \"" + replyStr + "\"");
        }
        /* tunneling Handshake was successful! */
    }

    // Main Method
    public static void main(String[] args) {
        new HttpsClient("www.myrio.com", 443);
    }

}
