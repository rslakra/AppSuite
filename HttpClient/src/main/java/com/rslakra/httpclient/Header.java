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

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2017-09-28 12:06:28 PM
 * @since 1.0.0
 */
public enum Header {

    /* Common Constants */
    /**
     * http
     */
    SCHEMA_HTTP("http"),
    /**
     * https
     */
    SCHEMA_HTTPS("https"),
    /**
     * HTTP/1.1
     */
    HTTP_VERSION("HTTP/1.1"),

    /* Header */

    /**
     * Accept
     */
    ACCEPT("Accept"),
    /**
     * Accept-Encoding
     */
    ACCEPT_ENCODING("Accept-Encoding"),
    /**
     * Accept-Language
     */
    ACCEPT_LANGUAGE("Accept-Language"),
    /**
     * Content-Type
     */
    CONTENT_TYPE("Content-Type"),
    /**
     * Content-Length
     */
    CONTENT_LENGTH("Content-Length"),
    /**
     * Expires
     */
    EXPIRES("Expires"),
    /**
     * Pragma
     */
    PRAGMA("Pragma"),
    /**
     * public
     */
    PRAGMA_PUBLIC("public"),
    /**
     * no-cache
     */
    PRAGMA_NO_CACHE("no-cache"),
    /**
     * Cache-Control
     */
    CACHE_CONTROL("Cache-Control"),
    /**
     * User-Agent
     */
    USER_AGENT("User-Agent"),
    /**
     * Content-Disposition
     */
    CONTENT_DISPOSITION("Content-Disposition"),
    /**
     * Set-Cookie
     */
    SET_COOKIE("Set-Cookie"),
    /**
     * Cookie
     */
    COOKIE("Cookie"),
    /**
     * Location
     */
    LOCATION("Location"),
    /**
     * Server
     */
    SERVER("Server"),
    /**
     * Transfer-Encoding
     */
    TRANSFER_ENCODING("Transfer-Encoding"),

    /* Header Values Here */

    /**
     * 200
     */
    STATUS_CODE_OK("200"),
    /**
     * 45
     */
    CONNECTION_TIMEOUT_IN_SECONDS("45"),
    /**
     * 45
     */
    READ_TIMEOUT_IN_SECONDS("45"),
    /**
     * ACCEPT ALL
     */
    ACCEPT_ALL("*/*"),
    /**
     * gzip, deflate
     */
    ENCODING_GZIP_DEFLATE("gzip, deflate"),
    /**
     * en-us
     */
    EN_US("en-us"),
    /**
     * en-US,en;q=0.5
     */
    EN_US_Q("en-US,en;q=0.5"),
    /**
     * fileName=
     */
    FILE_NAME_EQUAL("fileName="),
    /**
     * application/x-www-form-urlencoded
     */
    CONTENT_TYPE_FORM_URLENCODED("application/x-www-form-urlencoded"),
    /**
     * application/x-www-form-urlencoded;charset=UTF-8
     */
    CONTENT_TYPE_FORM_URLENCODED_UTF8("application/x-www-form-urlencoded;charset=UTF-8"),
    /**
     * application/x-java-serialized-object
     */
    APPLICATION_JAVA_X_SERIALIZED_OBJECT("application/x-java-serialized-object"),
    /**
     * multipart/form-data
     */
    MULTIPART_FORM_DATA("multipart/form-data"),
    /**
     * application/json
     */
    CONTENY_TYPE_JSON("application/json"),

    /** Add More Headers Here. */
    ;

    private String name;

    /**
     * @param name
     */
    private Header(final String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return
     */
    public static int connectionTimeoutInMillis() {
        return Integer.parseInt(CONNECTION_TIMEOUT_IN_SECONDS.getName()) * 1000;
    }

    /**
     * @return
     */
    public static int readTimeoutInMillis() {
        return Integer.parseInt(READ_TIMEOUT_IN_SECONDS.getName()) * 1000;
    }

}
