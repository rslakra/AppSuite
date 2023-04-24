package com.rslakra.appsuite.core.http;

import com.rslakra.appsuite.core.BeanUtils;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.decorators.Decorators;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Decorates Apache HTTP Client with resilence4j to handle circuit breaking and retry logic for synchronous rest(s)
 * invocations. Provides standardized logging for better insights into outbound rest(s) traffic.
 *
 * @author Rohtash Lakra
 * @created 3/26/21 5:42 PM
 */
public final class SyncHttpClient extends AbstractHttpClient implements AutoCloseable {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncHttpClient.class);
    private CloseableHttpClient httpSyncClient;

    /**
     * can only be instantiated from HttpClientBuilder
     */
    protected SyncHttpClient(final HttpClientBuilder builder) {
        super(builder);
        this.httpSyncClient = builder.getHttpSyncClient();
    }

    /**
     * @return
     */
    public CloseableHttpClient getHttpSyncClient() {
        return httpSyncClient;
    }

    /**
     * @param request
     * @return
     */
    public Response<String> execute(final Request request) {
        return execute(request, new StringResponseHandler());
    }

    /**
     * @param request
     * @param responseHandler
     * @param <T>
     * @return
     */
    public <T> Response<T> execute(final Request request, final ResponseHandler<T> responseHandler) {
        BeanUtils.assertNonNull(request, "request is null!");
        BeanUtils.assertNonNull(responseHandler, "responseHandler is null!");

        Supplier<Response<T>> supplier = () -> {
            logHttpRequest(request);

            State state = State.SUCCESS;
            HttpResponse response = null;
            T payload = null;
            Throwable throwable = null;

            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            try {
                response = httpSyncClient.execute(ofApacheRequest(request));
                // please refer ResponseHandler, default accept criteria is rest status code between 200 and 299
                // you can set your own accept criteria to distinguish which response code should be handled as an error
                payload = responseHandler.handleResponse(response);
            } catch (Throwable t) {
                LOGGER.error("execute()", t, "rest call failed for name={}", getClientName());
                state = State.FAILED;
                throwable = t;
                throw new HttpClientException(t);
            } finally {
                stopWatch.stop();
                logHttpResponse(state, request, response, payload, stopWatch.getTime(), throwable);
            }

            return Response.of(response.getStatusLine(), payload);
        };

        try {
            Decorators.DecorateSupplier<Response<T>> decoratedSupplier = Decorators.ofSupplier(supplier);
            if (getCircuitBreaker() != null) {
                decoratedSupplier.withCircuitBreaker(getCircuitBreaker());
            }
            if (getRetry() != null) {
                decoratedSupplier.withRetry(getRetry());
            }

            return decoratedSupplier.get();
        } catch (CallNotPermittedException c) {
            // log rest request/response when circuitbreaker is open
            logHttpRequest(request);
            logHttpResponse(State.CANCELLED, request, null, null, 0, c);
            throw c;
        }
    }

}
