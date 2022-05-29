package com.rslakra.httpclient;

import com.rslakra.core.IOUtils;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Map;

/**
 * Decorates Apache HTTP Client with resilence4j to handle circuit breaking and retry logic for synchronous http(s)
 * invocations. Provides standardized logging for better insights into outbound http(s) traffic.
 *
 * @author Rohtash Lakra (rlakra)
 * @created 3/26/21 5:42 PM
 */
public abstract class AbstractHttpClient implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpClient.class);

    private String clientName;
    private CircuitBreaker circuitBreaker;
    private Retry retry;

    protected enum State {
        SUCCESS,
        FAILED,
        CANCELLED;
    }

    /**
     * Calls only from HttpClientBuilder.
     *
     * @param builder
     */
    protected AbstractHttpClient(final HttpClientBuilder builder) {
        this.clientName = builder.clientName;
        this.circuitBreaker = builder.circuitBreaker;
        this.retry = builder.retry;
    }

    /**
     * @return
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @return
     */
    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }

    /**
     * @return
     */
    public Retry getRetry() {
        return retry;
    }

    /**
     * can be called explicitly by client if they are managing keep-alive connections
     */
    @Override
    public void close() throws Exception {
        IOUtils.closeSilently(this);
    }

    /**
     * @param request
     * @return
     */
    protected final HttpRequestBase ofApacheRequest(final Request request) {
        HttpRequestBase apacheRequest;
        final HttpMethod httpMethod = request.getHttpMethod();
        final URI uri = request.getUri();
        final HttpEntity payload = request.getPayload();

        switch (httpMethod) {
            case GET:
                apacheRequest = new HttpGet(uri);
                break;
            case POST:
                apacheRequest = new HttpPost(uri);
                if (payload != null) {
                    ((HttpPost) apacheRequest).setEntity(payload);
                }
                break;
            case PUT:
                apacheRequest = new HttpPut(uri);
                if (payload != null) {
                    ((HttpPut) apacheRequest).setEntity(payload);
                }
                break;
            case DELETE:
                apacheRequest = new HttpDelete(uri);
                break;
            default:
                throw new HttpClientException("Unsupported http request method");
        }

        if (MapUtils.isNotEmpty(request.getHeaders())) {
            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                apacheRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }

        return apacheRequest;
    }

    /**
     * @param request
     */
    protected final void logHttpRequest(final Request request) {
        LOGGER.info("logHttpRequest(), name=%s, request=%s, headers=%s", clientName, request,
                request.getHeaders());
    }

    /**
     * @param state
     * @param request
     * @param response
     * @param payload
     * @param durationMillis
     * @param t
     * @param <T>
     */
    protected <T> void logHttpResponse(State state, Request request, HttpResponse response, T payload,
                                       long durationMillis, Throwable t) {
        Integer statusCode = null;
        if (response != null && response.getStatusLine() != null) {
            statusCode = response.getStatusLine().getStatusCode();
        }

        String exceptionClassName = null;
        String exceptionMsg = null;
        if (t != null) {
            exceptionClassName = t.getClass().getCanonicalName();
            exceptionMsg = t.getMessage();
        }

        // NOTE : enable logging for org.apache.http.impl.client.* package to capture raw http response payload
        LOGGER.info(
                "logHttpResponse() - name=%s, state=%s, request=%s, duration(ms)=%d, statusCode=%d, exceptionName=%s, exceptionMsg=%s",
                clientName, state, request, durationMillis, statusCode, exceptionClassName, exceptionMsg);
    }

    /**
     * @param state
     * @param request
     * @param response
     * @param ex
     */
    protected void logHttpResponse(State state, Request request, HttpResponse response, Exception ex) {
        logHttpResponse(state, request, response, null, -1, ex);
    }
}
