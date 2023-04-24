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
package com.rslakra.appsuite.jdk.net.emails;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA. User: rohtash.singh Date: Sep 4, 2006 Time:
 * 10:53:40 PM To change this template use File | Settings | File Templates.
 */
public class SendMail {

    private Socket smtpSocket = null;
    private DataOutputStream os = null;
    private DataInputStream is = null;

    public void send() {
        Date dDate = new Date();
        DateFormat dFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);
        String m_sHostName = "localhost";

        try { // Open port to server
            smtpSocket = new Socket(m_sHostName, 1601);
            os = new DataOutputStream(smtpSocket.getOutputStream());
            is = new DataInputStream(smtpSocket.getInputStream());

            if (smtpSocket != null && os != null && is != null) {
                // Connection was made. Socket is ready for use.
                // [ Code to send email will be placed in here. ]
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("Host " + m_sHostName + "unknown");
        }
        // Now that you have made the connection it is time to add the code to
        // send off the email. This is pretty straight forward so I will add
        // comments into the code sample itself.
        try {
            os.writeBytes("HELO\r\n");
            // You will add the email address that the server
            // you are using know you as.
            os.writeBytes("MAIL From: <you@yourcompany.com>\r\n");

            // Who the email is going to.
            os.writeBytes("RCPT To: <theperson@theircompany.com>\r\n");
            // IF you want to send a CC then you will have to add this
            os.writeBytes("RCPT Cc: <theCC@anycompany.com>\r\n");

            // Now we are ready to add the message and the
            // header of the email to be sent out.
            os.writeBytes("DATA\r\n");

            os.writeBytes("X-Mailer: Via Java\r\n");
            os.writeBytes("DATE: " + dFormat.format(dDate) + "\r\n");
            os.writeBytes("From: Me <me@mycompany.com>\r\n");
            os.writeBytes("To:  YOU <you@yourcompany.com>\r\n");

            // Again if you want to send a CC then add this.
            os.writeBytes("Cc: CCDUDE <CCPerson@theircompany.com>\r\n");

            // Here you can now add a BCC to the message as well
            os.writeBytes("RCPT Bcc: BCCDude<BCC@invisiblecompany.com>\r\n");

            String sMessage = "Your subjectline.";

            os.writeBytes("Subject: Your subjectline here\r\n");
            os.writeBytes(sMessage + "\r\n");
            os.writeBytes("\r\n.\r\n");
            os.writeBytes("QUIT\r\n");

            // Now send the email off and check the server reply.
            // Was an OK is reached you are complete.
            String responseline = null;
            while ((responseline = is.readLine()) != null) { // System.out.println(responseline);
                if (responseline.indexOf("Ok") != -1)
                    break;
            }
        } catch (Exception e) {
            System.out.println("Cannot send email as an error occurred.");
        }
    }

    public static void main(String[] args) {
        new SendMail().send();
    }

}
