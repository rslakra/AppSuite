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
package com.rslakra.appsuite.jdk.net.server;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.security.Security;

public class HttpsServer extends HttpServer {
    private static int SSL_SERVER_PORT = 443;

    String KEYSTORE = "servercerts";
    char[] KEYSTOREPW = "rslakra".toCharArray();
    char[] KEYPW = KEYSTOREPW;
    boolean requireClientAuthentication;

    /**
     * @param args
     */
    public static void main(String[] args) {
        HttpsServer server = new HttpsServer(SSL_SERVER_PORT, true);
        server.startServer();
    }

    /**
     * @param port
     * @param requireClientAuthentication
     */
    public HttpsServer(int port, boolean requireClientAuthentication) {
        super(port);
        this.requireClientAuthentication = requireClientAuthentication;
    }

    /**
     * @return
     * @throws Exception
     */
    ServerSocket getServerSocket() throws Exception {
        // Make sure that JSSE is available
        Security.addProvider(new BouncyCastleProvider());
        // A keystore is where keys and certificates are kept
        // Both the keystore and individual private keys should be password
        // protected
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(new FileInputStream(KEYSTORE), KEYSTOREPW);
        // A KeyManagerFactory is used to create key managers
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        // Initialize the KeyManagerFactory to work with our keystore
        kmf.init(keystore, KEYPW);
        // An SSLContext is an environment for implementing JSSE
        // It is used to create a ServerSocketFactory
        SSLContext sslc = SSLContext.getInstance("SSLv3");
        // Initialize the SSLContext to work with our key managers
        sslc.init(kmf.getKeyManagers(), null, null);
        // Create a ServerSocketFactory from the SSLContext
        ServerSocketFactory ssf = sslc.getServerSocketFactory();
        // Socket to me
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(serverPort);
        // Authenticate the client?
        serverSocket.setNeedClientAuth(requireClientAuthentication);
        // Return a ServerSocket on the desired port (443)
        return serverSocket;
    }
}
