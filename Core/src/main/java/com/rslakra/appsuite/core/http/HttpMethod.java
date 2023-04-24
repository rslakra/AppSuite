package com.rslakra.appsuite.core.http;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.appsuite.core.ToString;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;

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
    TRACE(HttpTrace.class),
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
     * @param httpMethodName
     * @return
     */
    public static HttpMethod ofString(final String httpMethodName) {
        return Arrays.stream(HttpMethod.values())
            .filter(httpMethod -> httpMethod.name().equalsIgnoreCase(httpMethodName))
            .findFirst().orElse(null);
    }

    /**
     * Returns true if the method equals the <code>httpMethodName</code> otherwise false.
     *
     * @param httpMethodName
     * @param httpMethods
     * @return
     */
    public static boolean isEquals(final String httpMethodName, final HttpMethod... httpMethods) {
        return (BeanUtils.isNotEmpty(httpMethodName) && BeanUtils.isNotEmpty(httpMethods)
                && Arrays.stream(httpMethods)
                    .anyMatch(httpMethod -> httpMethod.name().equalsIgnoreCase(httpMethodName)));
    }

    /**
     * Returns true if the method equals the <code>httpMethodName</code> otherwise false.
     *
     * @param httpMethodName
     * @return
     */
    public static boolean isEquals(final String httpMethodName) {
        return isEquals(httpMethodName, HttpMethod.values());
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
