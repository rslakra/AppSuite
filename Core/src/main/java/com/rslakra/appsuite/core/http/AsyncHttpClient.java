package com.rslakra.appsuite.core.http;

import com.rslakra.appsuite.core.IOUtils;
import io.github.resilience4j.decorators.Decorators;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author Rohtash Lakra
 * @created 3/26/21 6:31 PM
 */
public class AsyncHttpClient extends AbstractHttpClient implements AutoCloseable {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncHttpClient.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private CloseableHttpAsyncClient httpAsyncClient;

    /**
     * can only be instantiated from HttpClientBuilder
     *
     * @param builder
     */
    protected AsyncHttpClient(final HttpClientBuilder builder) {
        super(builder);
        this.httpAsyncClient = builder.getHttpAsyncClient();
    }

    /**
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        IOUtils.closeSilently(this);
    }

    public CompletableFuture<Response<String>> executeWithCallback(final Request request) {
        return executeCallback(request, new StringResponseHandler(), FutureCallbacks.ignoreCallback());
    }

    public <T> CompletableFuture<Response<T>> executeWithCallback(final Request request,
                                                                  final ResponseHandler<T> handler) {
        return executeCallback(request, handler, FutureCallbacks.ignoreCallback());
    }

    public CompletableFuture<Response<String>> executeWithCallback(final Request request,
                                                                   final FutureCallback<Response<String>> futureCallback) {
        return executeCallback(request, new StringResponseHandler(), futureCallback);
    }

    /**
     * @param request
     * @param responseHandler
     * @param futureCallback
     * @param <T>
     * @return
     */
    protected <T> CompletableFuture<Response<T>> executeCallback(final Request request,
                                                                 final ResponseHandler<T> responseHandler,
                                                                 final FutureCallback<Response<T>> futureCallback) {
        assert request != null;
        assert futureCallback != null;

        Supplier<CompletionStage<HttpResponse>> supplier = () -> {
            logHttpRequest(request);
            CompletableFuture<HttpResponse> completableFuture = new CompletableFuture<>();
            httpAsyncClient.start();
            httpAsyncClient.execute(ofApacheRequest(request), new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse httpResponse) {
                    logHttpResponse(State.SUCCESS, request, httpResponse, null);
                    completableFuture.complete(httpResponse);
                }

                @Override
                public void failed(Exception ex) {
                    logHttpResponse(State.FAILED, request, null, ex);
                    LOGGER.error("failed() - execute async rest client for name=%s", getClientName(), ex);
                    completableFuture.completeExceptionally(ex);
                }

                @Override
                public void cancelled() {
                    logHttpResponse(State.CANCELLED, request, null, null);
                    LOGGER.error("cancelled() - execute async rest client call cancelled for name=%s", getClientName());
                    completableFuture.cancel(true);
                }
            });
            return completableFuture;
        };

        // decoratedSupplier
        Decorators.DecorateCompletionStage<HttpResponse> decoratedSupplier = Decorators.ofCompletionStage(supplier);
        if (getCircuitBreaker() != null) {
            decoratedSupplier.withCircuitBreaker(getCircuitBreaker());
        }
        if (getRetry() != null) {
            decoratedSupplier.withRetry(getRetry(), scheduler);
        }
        return decoratedSupplier.get().thenApply(
                httpResponse -> {
                    StatusLine statusLine = httpResponse.getStatusLine();
                    T payload = null;
                    Response<T> response = null;
                    try {
                        payload = responseHandler.handleResponse(httpResponse);
                        response = Response.of(statusLine, payload);
                        futureCallback.completed(response);
                        return response;
                    } catch (Throwable t) {
                        LOGGER.error("execute() - handle response failed for name=%s", getClientName(), t);
                        response = Response.of(statusLine);
                        futureCallback.completed(response);
                        return response;
                    }
                }).exceptionally(throwable -> {
            if (throwable instanceof CancellationException) {
                futureCallback.cancelled();
            } else if (throwable instanceof Exception) {
                futureCallback.failed((Exception) throwable);
            } else {
                futureCallback.failed(new RuntimeException(throwable));
            }
            return null;
        }).toCompletableFuture();
    }
}
