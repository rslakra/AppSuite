/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2018. All rights reserved.
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
package com.rslakra.httpclient;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.Arrays;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2017-09-28 12:06:28 PM
 * @since 1.0.0
 */
public enum Method {

    HEAD(HttpHead.class),
    PATCH(HttpPatch.class),
    OPTIONS(HttpOptions.class),
    GET(HttpGet.class),
    POST(HttpPost.class),
    PUT(HttpPut.class),
    DELETE(HttpDelete.class);;

    private String name;


    // requestType
    private final Class<? extends HttpRequestBase> requestType;
    /**
     * @param requestType
     */
    private Method(final Class<? extends HttpRequestBase> requestType) {
        this.requestType = requestType;
    }
//
//
//    /**
//     * @param name
//     */
//    private Method(final String name) {
//        this.name = name;
//    }

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns true if the request is GET otherwise false.
     *
     * @param requestMethod
     * @return
     */
    public static boolean isGetRequest(Method requestMethod) {
        return (Method.GET == requestMethod);
    }

    /**
     * Returns true if the request is GET otherwise false.
     *
     * @param requestMethod
     * @return
     */
    public static boolean isGetRequest(String requestMethod) {
        return isGetRequest(Method.valueOf(requestMethod));
    }

    /**
     * Returns true if the request is GET otherwise false.
     *
     * @param requestMethod
     * @return
     */
    public static boolean isPostRequest(String requestMethod) {
        return (Method.POST == Method.valueOf(requestMethod));
    }

    /**
     * @return
     */
    public final Class<? extends HttpRequestBase> getRequestType() {
        return this.requestType;
    }

    /**
     * @param method
     * @return
     */
    public static Method of(final String method) {
        return (method == null ? null : Method.valueOf(method.toUpperCase()));
    }

    /**
     * Returns true if the method equals the <code>method</code> otherwise false.
     *
     * @param method
     * @return
     */
    public static boolean equals(final String method) {
        return (method == null ? false : Arrays.stream(Method.values())
                .anyMatch(httpMethod -> httpMethod.name().equals(method)));
    }

    /**
     * Returns true if the method equals the <code>method</code> otherwise false.
     *
     * @param method
     * @return
     */
    public static boolean equals(final Method method) {
        return (method == null ? false : Arrays.stream(Method.values())
                .anyMatch(httpMethod -> httpMethod.name().equals(method.name())));
    }
}
