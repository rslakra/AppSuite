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
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
* @author Rohtash Lakra
 */
public class HTTPSServer {

    private int port;
    private boolean isServerDone = false;

    public static void main(String[] args) {
        HTTPSServer server = new HTTPSServer(Constants.getPort());
        server.run();
    }

    HTTPSServer(int port) {
        this.port = port;
    }

    // Start to run the server
    public void run() {
        SSLContext sslContext = Constants.createSSLContext(HTTPSServer.class);

        try {
            // Create server socket factory
            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();

            // Create server socket
            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(this.port);

            System.out.println("SSL server started");
            while (!isServerDone) {
                SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

                // Start the server thread
                new ServerThread(sslSocket).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Thread handling the socket from client.
    static class ServerThread extends Thread {

        private SSLSocket sslSocket = null;

        ServerThread(SSLSocket sslSocket) {
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

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("Inut : " + line);

                    if (line.trim().isEmpty()) {
                        break;
                    }
                }

                // Write data
                printWriter.print("HTTP/1.1 200\r\n");
                printWriter.flush();

                sslSocket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
