/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license
 * agreements. The reproduction, transmission or use of this code, in source
 * and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
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
package com.rslakra.appsuite.jdk.net.ssl;

import com.rslakra.appsuite.core.IOUtils;
import com.rslakra.appsuite.jdk.net.NetConstants;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

/*
 * Created on May 24, 2005
 */
public class SSLClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
        PrintStream out = System.out;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            SSLSocket client = (SSLSocket) f.createSocket(NetConstants.CLIENT_HOST, NetConstants.HTTP_PORT);
            IOUtils.logSocket(client);
            client.startHandshake();
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String msg = "Welcome to Client:\n";
            while ((msg = r.readLine()) != null) {
                out.println(msg);
                msg = in.readLine();
                w.write(msg, 0, msg.length());
                w.newLine();
                w.flush();
            }
            w.close();
            r.close();
            client.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
