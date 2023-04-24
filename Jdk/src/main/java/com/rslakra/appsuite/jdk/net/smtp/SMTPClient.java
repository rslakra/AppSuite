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
package com.rslakra.appsuite.jdk.net.smtp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SMTPClient {
    public static void main(String[] args) {
        Socket smtpSocket = null;
        DataOutputStream os = null;
        DataInputStream is = null;
        try {
            smtpSocket = new Socket("localhost", 25);
            os = new DataOutputStream(smtpSocket.getOutputStream());
            is = new DataInputStream(smtpSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
        // If everything has been initialized then we want to write some data
        // to the socket we have opened a connection to on port 25
        if (smtpSocket != null && os != null && is != null) {
            try {
                // The capital string before each colon has a special meaning to
                // SMTP
                // you may want to read the SMTP specification, RFC1822/3
                os.writeBytes("Hello \n");
                os.writeBytes("Mail From: rohtash.lakra@devamatre.com\n");
                os.writeBytes("RCPT To: rohtash.lakra@devamatre.com\n");
                os.writeBytes("Data\n");
                os.writeBytes("From: rohtash.lakra@devamatre.com\n");
                os.writeBytes("Subject: Testing Socket Programming\n");
                os.writeBytes("Hi There\n"); // message body
                os.writeBytes("\n.\n");
                os.writeBytes("QUIT");
                // keep on reading from/to the socket till we receive the "Ok"
                // from SMTP,
                // once we received that then we want to break.
                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("Ok") != -1) {
                        break;
                    }
                }
                // clean up: close the output stream, input stream and socket.
                os.close();
                is.close();
                smtpSocket.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }
}
