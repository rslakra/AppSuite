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
package com.rslakra.appsuite.jdk.net.socket.server;


import com.rslakra.appsuite.core.IOUtils;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

/**
 * Created by IntelliJ IDEA. User: rohtash.singh Date: May 27, 2005 Time:
 * 2:36:33 PM To change this template use Options | File Templates.
 * <p>
 * A simple file server that can serve Http get request in both clear and secure
 * channel
 */
public class HttpServer implements Runnable {
    // public class HttpServer extends ClassServer {
    private ServerSocket server = null;// Server

    private static int DEFAULT_PORT = 1601; // On Which server accepts requests.
    private static String ROOT_DIR = System.getProperty("user.dir");
    private static final boolean AUTHENTICATE_CLIENT = false;// If true client
    // authentication
    // will require.
    // Server Types
    public static final String TLS_SSL_FILE_SERVER = "TLS/SSL";
    public static final String PLAIN_SOCKET_FILE_SERVER = "PlainSocket";

    public HttpServer(ServerSocket serverSocket) {
        server = serverSocket;
        startServer();//
    }

    // Start a New Server for Listing.
    private void startServer() {
        (new Thread(this)).start();
    }

    public void run() {
        Socket socket;
        // Accept connection from server
        try {
            socket = server.accept();
        } catch (IOException ex) {
            System.err.println("Error while accepting request from server : " + server);
            ex.printStackTrace();
            return;
        }
        // Create a new Thread to accept connections.
        startServer();
        //
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            try {
                // Get Path of class file from Header
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // method to extract path from file
                String path = getPath(in);
                System.out.println("Path : " + path);
                byte[] byteCode = getBytes("");
                // Send byteCode in resonse (assumes HTTP/1.0 or later)
                try {
                    out.writeBytes("HTTP/1.0 200 OK\r\n");
                    out.writeBytes("Content-Length: " + byteCode.length + "\r\n");
                    out.writeBytes("Content-Type: text/html\r\n\r\n");
                    out.write(byteCode);
                    out.flush();
                } catch (IOException ioEx) {
                    ioEx.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            // Either log error or show on output stream
            System.out.println("Error writing response : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String getPath(BufferedReader in) throws IOException {
        final String GET_CLASS = "GET /";
        String path = "";
        String line = in.readLine();
        // extract class from GET line
        if (line.startsWith(GET_CLASS)) {
            line = line.substring(GET_CLASS.length(), line.length() - 1).trim();
            int index = line.indexOf(' ');
            if (index != -1) {
                path = line.substring(0, index);
            }
        }
        // eat rest of header
        do {
            line = in.readLine();
        } while ((line.length() != 0) && (line.charAt(0) != '\r') && (line.charAt(0) != '\n'));

        if (path.length() != 0) {
            return path;
        } else {
            throw new IOException("Malformed Header!");
        }
    }

    /**
     * Returns an array of bytes containing the bytes for the file represented by
     * the argument <b>path</b>.
     *
     * @param path
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public byte[] getBytes(String path) throws IOException, FileNotFoundException {
        File file = new File(ROOT_DIR + File.separator + path);
        int len = (int) file.length();
        if (len == 0) {
            throw new IOException(file.getAbsolutePath() + " length is zero(0).");
        } else {
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            byte[] byteCode = new byte[len];
            in.readFully(byteCode);
            IOUtils.closeSilently(in);

            return byteCode;
        }
    }// End of getBytes()

    private static ServerSocketFactory getServerSocketFactory(String sType) {
        if (sType.equals("TLS")) {
            try {
                // Set up Key Manager to do Server Authentication
                char[] password = "passphrase".toCharArray();
                // TSL File Server
                SSLContext sslContext = SSLContext.getInstance("TLS");
                // Certificate Type
                KeyManagerFactory kmFactory = KeyManagerFactory.getInstance("SunX509");
                KeyStore keyStore = KeyStore.getInstance("JKS"); // Java Key
                // Store

                // Server Certificate File Name
                String serverCertFile = "testkeys";
                keyStore.load(new FileInputStream(serverCertFile), password);
                kmFactory.init(keyStore, password);
                // Key Manager, Trust Manager, Secure Random
                sslContext.init(kmFactory.getKeyManagers(), null, null);
                return sslContext.getServerSocketFactory();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            return ServerSocketFactory.getDefault();
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            ServerSocketFactory ssFactory = getServerSocketFactory(PLAIN_SOCKET_FILE_SERVER);
            ServerSocket serverSocket = ssFactory.createServerSocket(DEFAULT_PORT);
            if (AUTHENTICATE_CLIENT) {
                ((SSLServerSocket) serverSocket).setNeedClientAuth(AUTHENTICATE_CLIENT);
            }
            new HttpServer(serverSocket);
            System.out.println("HttpServer Started.");
        } catch (Exception ex) {
            System.err.println("Unable to start HttpServer!");
            ex.printStackTrace();
        }
    }
}
