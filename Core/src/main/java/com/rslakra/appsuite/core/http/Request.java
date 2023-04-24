package com.rslakra.appsuite.core.http;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.appsuite.core.ToString;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 3/26/21 3:35 PM
 */
public class Request {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(Request.class);
    private final Map<String, String> headers = new LinkedHashMap<String, String>();
    private URI uri;
    private HttpMethod httpMethod;
    private final Map<String, Object> parameters = new LinkedHashMap<>();
    private HttpEntity payload;

    /**
     * @param builder
     */
    private Request(final RequestBuilder builder) {
        this.headers.putAll(builder.headers);
        this.uri = builder.uri;
        this.httpMethod = builder.httpMethod;
        this.payload = builder.payload;
        this.parameters.putAll(builder.parameters);
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

    /**
     * @param key
     * @param values
     */
    public final void addHeader(final String key, final List<String> values) {
        if (BeanUtils.isNotEmpty(values)) {
            String value = getHeader(key);
            if (BeanUtils.isNotEmpty(value)) {
                values.add(value);
            }
            value = values.stream().collect(Collectors.joining(";"));
            LOGGER.debug("addHeader() key:{}, value:{}", key, value);
            this.headers.put(key, value);
        }
    }

    /**
     * @return
     */
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
            .add("headers", getHeaders())
            .add("httpMethod", getHttpMethod())
            .add("scheme", uri.getScheme())
            .add("host", uri.getHost())
            .add("path", uri.getPath())
            .add("query", uri.getQuery())
            .add("payload", getPayload())
            .toString();
    }

    /**
     * @return
     */
    public static RequestBuilder newBuilder() {
        return new RequestBuilder();
    }


    /**
     * Executes the request and calls the <code>responseHandler</code>, it not null.
     *
     * @param responseHandler
     */
    public void execute(final ResponseHandler responseHandler) {

    }

    /**
     * Executes the request.
     */
    public void execute() {

    }


    /**
     *
     */
    public static class RequestBuilder {

        private final Map<String, String> headers = new HashMap<String, String>();
        private URI uri;
        private HttpMethod httpMethod;
        private HttpEntity payload;
        private final Map<String, Object> parameters = new LinkedHashMap<>();

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
         * @param key
         * @param value
         * @return
         */
        public RequestBuilder addParameter(final String key, final Object value) {
            this.parameters.put(key, value);
            return this;
        }

        /**
         * @param parameters
         * @return
         */
        public RequestBuilder addParameters(final Map<String, Object> parameters) {
            this.parameters.putAll(parameters);
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
