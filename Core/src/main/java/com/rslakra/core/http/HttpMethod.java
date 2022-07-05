package com.rslakra.core.http;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/26/21 3:42 PM
 */
public enum HttpMethod {
    HEAD,
    PATCH,
    OPTIONS,
    GET,
    POST,
    PUT,
    DELETE;

    /**
     *
     * @param httpMethod
     * @return
     */
    public static HttpMethod of(final String httpMethod) {
        return (httpMethod == null ? null : HttpMethod.valueOf(httpMethod.toUpperCase()));
    }
}
