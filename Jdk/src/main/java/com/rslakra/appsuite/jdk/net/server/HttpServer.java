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

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

// Small, simple HTTP server
public class HttpServer {
    private static int SERVER_PORT = 80;
    int serverPort;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new HttpServer(SERVER_PORT);
    }

    /**
     * @param port
     */
    public HttpServer(int port) {
        serverPort = port;
        startServer();
    }

    /**
     * @return
     */
    public String getServerName() {
        String className = this.getClass().getName();
        return className.substring(className.lastIndexOf(".") + 1);
    }

    /**
     *
     */
    public void startServer() {
        System.out.println(getServerName() + " version 1.0");
        try {
            // Get a server socket
            ServerSocket server = getServerSocket(serverPort);
            // Let us know that you're listening
            System.out.println(getServerName() + " is listening on port " + server.getLocalPort() + ".");
            do {
                // Accept a connection
                Socket client = server.accept();
                // Handle the connection with a separate thread
                new HTTPServerThread(client);
                // (new HTTPServerThread(client)).start();
            } while (true);
        } catch (Exception ex) {
            System.out.println("Unable to listen on " + serverPort + ".");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // Get a server socket on the hard-wired server port
    ServerSocket getServerSocket(int port) throws Exception {
        return new ServerSocket(port);
    }
}

// Handle a single server connection
class HTTPServerThread implements Runnable {
    // class HTTPServerThread extends Thread {
    Socket client;

    // Keep track of the client socket
    public HTTPServerThread(Socket client) {
        this.client = client;
    }

    // Thread entry point
    public void run() {
        try {
            // Display info about the connection
            describeConnection(client);
            // Create a stream to send data to the client
            BufferedOutputStream outStream = new BufferedOutputStream(client.getOutputStream());
            HTTPInputStream inStream = new HTTPInputStream(client.getInputStream());
            // Get the client's request
            HTTPRequest request = inStream.getRequest();
            // Display info about it
            request.log();
            // Sorry, we only handle gets
            if (request.isGetRequest())
                processGetRequest(request, outStream);
            System.out.println("Request completed. Closing connection.");

            inStream.close();
        } catch (IOException ex) {
            System.out.println("IOException occurred when processing request.");
        }
        try {
            client.close();
        } catch (IOException ex) {
            System.out.println("IOException occurred when closing socket.");
        }
    }

    // Display info about the connection
    void describeConnection(Socket client) {
        String destName = client.getInetAddress().getHostName();
        String destAddr = client.getInetAddress().getHostAddress();
        int destPort = client.getPort();
        System.out.println("Accepted connection to " + destName + " (" + destAddr + ") on port " + destPort + ".");
    }

    // Process an HTTP GET
    void processGetRequest(HTTPRequest request, BufferedOutputStream outStream) throws IOException {
        /*
         * If you want to use this in a secure environment then you should place some
         * restrictions on the requested file name
         */
        String fileName = request.getFileName();
        File file = new File(fileName);
        // Give them the requested file
        if (file.exists())
            sendFile(outStream, file);
        else
            System.out.println("File " + file.getCanonicalPath() + " does not exist.");
    }

    // A simple HTTP 1.0 response
    void sendFile(BufferedOutputStream out, File file) {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int len = (int) file.length();
            byte buffer[] = new byte[len];
            in.readFully(buffer);
            in.close();
            out.write("HTTP/1.0 200 OK\r\n".getBytes());
            out.write(("Content-Length: " + buffer.length + "\r\n").getBytes());
            out.write("Content-Type: text/html\r\n\r\n".getBytes());
            out.write(buffer);
            out.flush();
            out.close();
            System.out.println("File sent: " + file.getCanonicalPath());
            System.out.println("Number of bytes: " + len);
        } catch (Exception ex) {
            try {
                out.write(("HTTP/1.0 400 " + "No can do" + "\r\n").getBytes());
                out.write("Content-Type: text/html\r\n\r\n".getBytes());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            System.out.println("Error retrieving " + file);
        }
    }
}

// Convenience class for reading client requests
class HTTPInputStream extends FilterInputStream {
    public HTTPInputStream(InputStream in) {
        super(in);
    }

    // Get a line
    public String readLine() throws IOException {
        StringBuffer result = new StringBuffer();
        boolean finished = false;
        boolean cr = false;
        do {
            int ch = -1;
            ch = read();
            if (ch == -1)
                return result.toString();
            result.append((char) ch);
            if (cr && ch == 10) {
                result.setLength(result.length() - 2);
                return result.toString();
            }
            if (ch == 13)
                cr = true;
            else
                cr = false;
        } while (!finished);
        return result.toString();
    }

    // Get the whole request
    public HTTPRequest getRequest() throws IOException {
        HTTPRequest request = new HTTPRequest();
        String line;
        do {
            line = readLine();
            if (line.length() > 0)
                request.addLine(line);
            else
                break;
        } while (true);
        return request;
    }
}

// Used to process GET requests
class HTTPRequest {
    Vector lines = new Vector();

    public HTTPRequest() {
    }

    public void addLine(String line) {
        lines.addElement(line);
    }

    // Is this a GET or isn't it?
    boolean isGetRequest() {
        if (lines.size() > 0) {
            String firstLine = (String) lines.elementAt(0);
            if (firstLine.length() > 0)
                if (firstLine.substring(0, 3).equalsIgnoreCase("GET"))
                    return true;
        }
        return false;
    }

    // What do they want to get?
    String getFileName() {
        if (lines.size() > 0) {
            String firstLine = (String) lines.elementAt(0);
            String fileName = firstLine.substring(firstLine.indexOf(" ") + 1);
            System.out.println("fileName : " + fileName);
            int index = fileName.indexOf(" ");
            if (index != -1)
                fileName = fileName.substring(0, index);
            try {
                if (fileName.charAt(0) == '/')
                    fileName = fileName.substring(1);
            } catch (StringIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
            if (fileName.equals(""))
                fileName = "index.htm";
            if (fileName.charAt(fileName.length() - 1) == '/')
                fileName += "index.htm";
            return fileName;
        } else
            return "";
    }

    // Display some info so we know what's going on
    void log() {
        System.out.println("Received the following request:");
        for (int i = 0; i < lines.size(); ++i)
            System.out.println((String) lines.elementAt(i));
    }
}
