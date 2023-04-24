package com.rslakra.appsuite.core.http;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.appsuite.core.IOUtils;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Map;

/**
 * Decorates Apache HTTP Client with resilence4j to handle circuit breaking and retry logic for synchronous rest(s)
 * invocations. Provides standardized logging for better insights into outbound rest(s) traffic.
 *
 * @author Rohtash Lakra
 * @created 3/26/21 5:42 PM
 */
public abstract class AbstractHttpClient implements AutoCloseable {

    // LOGGER
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
        this.clientName = builder.getClientName();
        this.circuitBreaker = builder.getCircuitBreaker();
        this.retry = builder.getRetry();
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
                throw new HttpClientException("Unsupported rest request method");
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
        LOGGER.info("logHttpRequest({}), clientName={}", request, getClientName());
    }

    /**
     * @param state
     * @param request
     * @param response
     * @param payload
     * @param durationMillis
     * @param error
     * @param <T>
     */
    protected <T> void logHttpResponse(State state, Request request, HttpResponse response, T payload,
                                       long durationMillis, Throwable error) {
        Integer statusCode = null;
        if (response != null && response.getStatusLine() != null) {
            statusCode = response.getStatusLine().getStatusCode();
        }

        String errorClassName = null;
        String errorMessage = null;
        if (BeanUtils.isNotNull(error)) {
            errorClassName = error.getClass().getCanonicalName();
            errorMessage = error.getMessage();
        }

        // NOTE : enable logging for org.apache.rest.impl.client.* package to capture raw rest response payload
        LOGGER.info(
                "logHttpResponse() - clientName={}, state={}, request={}, duration(ms)={}, statusCode={}, exceptionName={}, exceptionMsg={}",
                getClientName(), state, request, durationMillis, statusCode, errorClassName, errorMessage);
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
