package com.rslakra.appsuite.core.http;

import com.rslakra.appsuite.core.BeanUtils;

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

    /**
     * Rate limiting - To prevent abuse, it is standard practice to add some sort of rate limiting to an API.
     * (https://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api)
     * <p>
     * At a minimum, include the following headers:
     * <p>
     * X-Rate-Limit-Limit - The number of allowed requests in the current period X-Rate-Limit-Remaining - The number of
     * remaining requests in the current period X-Rate-Limit-Reset - The number of seconds left in the current period
     * <p>
     * https://www.baeldung.com/spring-bucket4j
     */
    X_RATE_LIMIT_LIMIT("X-Rate-Limit-Limit"),
    X_RATE_LIMIT_REMAINING("X-Rate-Limit-Remaining"),
    X_RATE_LIMIT_RESET("X-Rate-Limit-Reset"),
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
