package com.rslakra.core.http;

import com.rslakra.core.BeanUtils;
import com.rslakra.core.ToString;
import org.apache.http.client.methods.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 10:22 PM
 */
public enum HttpMethod {
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
    private HttpMethod(final Class<? extends HttpRequestBase> requestType) {
        this.requestType = requestType;
    }

    /**
     * @return
     */
    public final Class<? extends HttpRequestBase> getRequestType() {
        return this.requestType;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(HttpMethod.class)
                .add("requestType", getRequestType())
                .toString();
    }

    /**
     * @param httpMethod
     * @return
     */
    public static HttpMethod of(final String httpMethod) {
        return (BeanUtils.isNull(httpMethod) ? null : HttpMethod.valueOf(httpMethod.toUpperCase()));
    }

    /**
     * Returns true if the method equals the <code>httpMethodName</code> otherwise false.
     *
     * @param httpMethodName
     * @return
     */
    public static boolean isEquals(final String httpMethodName) {
        return (Objects.nonNull(httpMethodName) && Arrays.stream(HttpMethod.values()).anyMatch(httpMethod -> httpMethod.name().equals(httpMethodName)));
    }

    /**
     * Returns true if the httpMethod equals the <code>httpMethod</code> otherwise false.
     *
     * @param httpMethod
     * @return
     */
    public static boolean isEquals(final HttpMethod httpMethod) {
        return (Objects.nonNull(httpMethod) && isEquals(httpMethod.name()));
    }
}
