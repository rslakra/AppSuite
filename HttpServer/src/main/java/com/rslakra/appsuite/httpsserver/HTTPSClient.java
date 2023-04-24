/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2022. All rights reserved.
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
package com.rslakra.appsuite.httpsserver;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
* @author Rohtash Lakra
 * <p>
 * https://127.0.0.1:7516/
 */
public class HTTPSClient {

    private String host;
    private int port;

    /**
     * @param args
     */
    public static void main(String[] args) {
        HTTPSClient client = new HTTPSClient(Constants.getHost(), Constants.getPort());
        client.run();
    }

    /**
     * @param host
     * @param port
     */
    HTTPSClient(String host, int port) {
        // Create the and initialize the SSLContext
        this.host = host;
        this.port = port;
    }

    // Start to run the server
    public void run() {
        SSLContext sslContext = Constants.createSSLContext(HTTPSClient.class);

        try {
            // Create socket factory
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // Create socket
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(this.host, this.port);

            System.out.println("SSL client started");
            new ClientThread(sslSocket).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Thread handling the socket to server
    static class ClientThread extends Thread {

        private SSLSocket sslSocket = null;

        ClientThread(SSLSocket sslSocket) {
            this.sslSocket = sslSocket;
        }

        public void run() {
            sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());

            try {
                // Start handshake
                sslSocket.startHandshake();

                // Get session after the connection is established
                SSLSession sslSession = sslSocket.getSession();

                System.out.println("SSLSession :");
                System.out.println("\tProtocol : " + sslSession.getProtocol());
                System.out.println("\tCipher suite : " + sslSession.getCipherSuite());

                // Start handling application content
                InputStream inputStream = sslSocket.getInputStream();
                OutputStream outputStream = sslSocket.getOutputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));

                // Write data
                printWriter.println("Hello Server");
                printWriter.println();
                printWriter.flush();

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("Inut : " + line);

                    if (line.trim().equals("HTTP/1.1 200\r\n")) {
                        break;
                    }
                }

                sslSocket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
