package com.rslakra.core.http;

import com.rslakra.core.BeanUtils;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 7/21/22 5:07 PM
 */
public enum Header {
    WILDCARD("*/*"),
    KEEP_ALIVE("keep-alive"),
    ACCEPT("Accept"),
    ACCEPT_ENCODING("Accept-Encoding"),
    ACCEPT_LANGUAGE("Accept-Language"),
    CONTENT_TYPE("Content-Type"),
    CONTENT_LENGTH("Content-Length"),

    EXPIRES("Expires"),
    PRAGMA("Pragma"),
    PRAGMA_PUBLIC("public"),
    CACHE_CONTROL("Cache-Control"),
    USER_AGENT("User-Agent"),
    CONTENT_DISPOSITION("Content-Disposition"),

    SET_COOKIE("Set-Cookie"),
    COOKIE("Cookie"),
    LOCATION("Location"),
    SERVER("Server"),
    TRANSFER_ENCODING("Transfer-Encoding"),

    REQUEST_TRACER("Request-Tracer"),
    DEVICE_ID("Device-Id"),
    CLIENT_ID("Client-Id"),
    ;

    private final String headerName;

    /**
     * @param headerName
     */
    private Header(final String headerName) {
        this.headerName = headerName;
    }

    /**
     * @return
     */
    public String getName() {
        return headerName;
    }

    /**
     * @param headerName
     * @return
     */
    public static Header forName(final String headerName) {
        return (BeanUtils.isEmpty(headerName) ? null : Arrays.stream(values())
            .filter(header -> header.getName().equalsIgnoreCase(headerName)).findAny().get());
    }
}
