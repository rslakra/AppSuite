/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
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
package com.rslakra.testcases;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

import com.rslakra.core.CoreUtils;
import com.rslakra.httpclient.HTTPUtils;
import com.rslakra.httpclient.HTTPUtils.HttpResponse;

public class TestUrlConnection {

    public static void main(String[] args) {
        String urlString = "https://devamatre.com/";
        HttpResponse httpResponse = HTTPUtils.executeGetRequest(urlString, null, true);
        System.out.println(httpResponse.getRequestHeaders());

        String formActionValue = extractFormActionValue(httpResponse.getDataBytes());
        System.out.println("\nformActionValue:\n" + formActionValue);

        String dataString = new String(httpResponse.getDataBytes());
        Pattern pattern = Pattern.compile("\"");
        Matcher matcher = pattern.matcher(dataString);
        if (matcher.matches()) {
            System.out.println("Matched\n");
            System.out.println(matcher.group(1));
        }

        System.out.println(StringEscapeUtils.unescapeHtml3(urlString));
    }

    /**
     * @param bytes
     * @return
     */
    public static String extractFormActionValue(byte[] bytes) {
        String formActionValue = null;
        if (!CoreUtils.isNullOrEmpty(bytes)) {
            final String startString = "<form action=\"";
            final String endString = "\" method=\"post\">";
            BufferedReader bReader = null;
            try {
                bReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                String line = null;
                while ((line = bReader.readLine()) != null) {
                    if (line.trim().startsWith(startString)) {
                        formActionValue = line.substring(line.indexOf(startString) + startString.length(), (line.length() - endString.length()));
                        formActionValue = StringEscapeUtils.unescapeHtml3(formActionValue);
                        break;
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return formActionValue;
    }

}
