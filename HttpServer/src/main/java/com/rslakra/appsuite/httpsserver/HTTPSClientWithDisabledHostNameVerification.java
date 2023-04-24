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

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

/**
 * This HTTPSClient disables the host name verification for the demo purposes. If
 * you wish to use this code in production, make sure you verifiy the host as
 * per your certificate.
 *
* @author Rohtash Lakra
 */
public class HTTPSClientWithDisabledHostNameVerification {

    static {
        HttpsURLConnection.setDefaultHostnameVerifier(new DefaultHostNameVerifier());
    }

    public static void main(String[] args) {
        // Initialize configuration
        String certFilePath = Constants.pathForCertificate(HTTPSClientWithDisabledHostNameVerification.class);
        System.setProperty("javax.net.ssl.trustStore", certFilePath);
        System.setProperty("javax.net.ssl.trustStoreType", "jks");

        try {
            URL url = Constants.newURL(Constants.getHost(), Constants.getPort(), true);
            HttpsURLConnection client = (HttpsURLConnection) url.openConnection();
            System.out.println("ResponseCode:" + client.getResponseCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
