package com.rslakra.core.http;

import org.apache.http.client.methods.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 10:22 AM
 */
public enum Method {
    HEAD(HttpHead.class),
    PATCH(HttpPatch.class),
    OPTIONS(HttpOptions.class),
    GET(HttpGet.class),
    POST(HttpPost.class),
    PUT(HttpPut.class),
    DELETE(HttpDelete.class);

    // requestType
    private final Class<? extends HttpRequestBase> requestType;

    /**
     * @param requestType
     */
    private Method(final Class<? extends HttpRequestBase> requestType) {
        this.requestType = requestType;
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
        return (Objects.isNull(method) ? null : Method.valueOf(method.toUpperCase()));
    }

    /**
     * Returns true if the method equals the <code>httpMethodName</code> otherwise false.
     *
     * @param httpMethodName
     * @return
     */
    public static boolean isEquals(final String httpMethodName) {
        return (Objects.nonNull(httpMethodName) && Arrays.stream(Method.values()).anyMatch(httpMethod -> httpMethod.name().equals(httpMethodName)));
    }

    /**
     * Returns true if the method equals the <code>method</code> otherwise false.
     *
     * @param method
     * @return
     */
    public static boolean isEquals(final Method method) {
        return (Objects.nonNull(method) && isEquals(method.name()));
    }
}
