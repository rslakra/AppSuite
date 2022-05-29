package com.rslakra.httpclient;

import com.rslakra.core.utils.ToString;
import org.apache.http.HttpEntity;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/26/21 3:35 PM
 */
public class Request {

    // avoid NullPointerException
    private final Map<String, String> headers = new HashMap<String, String>();
    private URI uri;
    private HttpMethod httpMethod;
    private HttpEntity payload;

    /**
     * @param builder
     */
    private Request(final RequestBuilder builder) {
        this.headers.putAll(builder.headers);
        this.uri = builder.uri;
        this.httpMethod = builder.httpMethod;
        this.payload = builder.payload;
    }

    /**
     * @return
     */
    protected Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * @param key
     * @return
     */
    public final String getHeader(final String key) {
        return getHeaders().get(key);
    }

    /**
     * @param key
     * @param value
     */
    public final void addHeader(final String key, final String value) {
        this.headers.put(key, value);
    }

    public final URI getUri() {
        return uri;
    }

    /**
     * @return
     */
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    /**
     * @return
     */
    public final HttpEntity getPayload() {
        return payload;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Request.class)
                .add("headers", headers)
                .add("httpMethod", getHttpMethod())
                .add("scheme", uri.getScheme())
                .add("host", uri.getHost())
                .add("path", uri.getPath())
                .add("query", uri.getQuery())
                .add("payload", this.getPayload())
                .toString();
    }

    /**
     * @return
     */
    public static RequestBuilder newBuilder() {
        return new RequestBuilder();
    }

    /**
     *
     */
    public static class RequestBuilder {

        // avoid NP
        private final Map<String, String> headers = new HashMap<String, String>();
        private URI uri;
        private HttpMethod httpMethod;
        private HttpEntity payload;

        private RequestBuilder() {
        }

        /**
         * @param uri
         * @return
         */
        public RequestBuilder setUri(URI uri) {
            this.uri = uri;
            return this;
        }

        /**
         * @param httpMethod
         * @return
         */
        public RequestBuilder setHttpMethod(final HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        /**
         * @param payload
         * @return
         */
        public RequestBuilder setPayload(final HttpEntity payload) {
            this.payload = payload;
            return this;
        }

        /**
         * @param key
         * @param value
         * @return
         */
        public RequestBuilder addHeader(final String key, final String value) {
            this.headers.put(key, value);
            return this;
        }

        /**
         * @param headers
         * @return
         */
        public RequestBuilder addHeaders(final Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        /**
         * @return
         */
        public Request build() {
            assert uri != null;
            assert httpMethod != null;
            return new Request(this);
        }
    }

}
