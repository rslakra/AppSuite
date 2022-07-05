package com.rslakra.core.http;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.builder.verify.VerifyHttp.verifyHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.ActionSequence.sequence;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.uri;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

import com.xebialabs.restito.builder.stub.StubHttp;
import com.xebialabs.restito.builder.verify.VerifyHttp;
import com.xebialabs.restito.semantics.Action;
import com.xebialabs.restito.semantics.ActionSequence;
import com.xebialabs.restito.semantics.Condition;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.concurrent.FutureCallback;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/29/21 11:48 AM
 */
public class AsyncHttpClientTest extends AbstractHttpClientTest {

    public AsyncHttpClientTest(String clientName) {
        super(clientName);
    }

    private AsyncHttpClient newAsyncHttpClient() {
        return new HttpClientBuilder("AsyncHttpClientTest")
            .turnOffRetry()
            .turnOffCircuitBreaker()
            .buildAsyncClient();
    }

    @Test
    public void testSuccess() throws URISyntaxException, InterruptedException, TimeoutException, ExecutionException {
        String path = "/success";
        StubHttp.whenHttp(server).match(Condition.get(path)).then(successAction);

        AtomicInteger successCallCount = new AtomicInteger(0);
        AtomicInteger failedCallCount = new AtomicInteger(0);
        AtomicInteger cancelledCallCount = new AtomicInteger(0);
        FutureCallback<Response<String>> futureCallback =
            constructFutureCallback(successCallCount, failedCallCount, cancelledCallCount);

        AsyncHttpClient client = newAsyncHttpClient();
        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);

        CompletableFuture<Response<String>> promise =
            client.executeWithCallback(request, futureCallback);
        promise.get(5, TimeUnit.MINUTES);
        Assert.assertEquals(futureCallback.toString(), "HTTP/1.1 200 OK");
        assertEquals(successCallCount.get(), 1);
        assertEquals(failedCallCount.get(), 0);
        assertEquals(cancelledCallCount.get(), 0);
        VerifyHttp.verifyHttp(server).times(1, Condition.uri(path));
    }

    @Test
    public void testBadRequest() throws URISyntaxException, InterruptedException, TimeoutException, ExecutionException {
        String path = "/bad_request";
        StubHttp.whenHttp(server).match(Condition.get(path)).then(Action.status(HttpStatus.BAD_REQUEST_400), Action.stringContent("fail"));

        AtomicInteger successCallCount = new AtomicInteger(0);
        AtomicInteger failedCallCount = new AtomicInteger(0);
        AtomicInteger cancelledCallCount = new AtomicInteger(0);
        FutureCallback<Response<String>> futureCallback =
            constructFutureCallback(successCallCount, failedCallCount, cancelledCallCount);

        AsyncHttpClient client = newAsyncHttpClient();
        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);

        CompletableFuture<Response<String>> promise =
            client.executeWithCallback(request, futureCallback);
        promise.get(5, TimeUnit.MINUTES);
        Assert.assertEquals(futureCallback.toString(), "HTTP/1.1 400 Bad Request");
        assertEquals(successCallCount.get(), 1);
        assertEquals(failedCallCount.get(), 0);
        assertEquals(cancelledCallCount.get(), 0);
        VerifyHttp.verifyHttp(server).times(1, Condition.uri(path));
    }

    @Test
    public void testServiceError()
        throws URISyntaxException, InterruptedException, TimeoutException, ExecutionException {
        String path = "/service_out";
        StubHttp.whenHttp(server).match(Condition.get(path)).then(Action.status(HttpStatus.INTERNAL_SERVER_ERROR_500), Action.stringContent("fail"));

        AtomicInteger successCallCount = new AtomicInteger(0);
        AtomicInteger failedCallCount = new AtomicInteger(0);
        AtomicInteger cancelledCallCount = new AtomicInteger(0);
        FutureCallback<Response<String>> futureCallback =
            constructFutureCallback(successCallCount, failedCallCount, cancelledCallCount);

        AsyncHttpClient client = newAsyncHttpClient();
        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);

        CompletableFuture<Response<String>> promise =
            client.executeWithCallback(request, futureCallback);
        promise.get(5, TimeUnit.MINUTES);
        Assert.assertEquals(futureCallback.toString(), "HTTP/1.1 500 Internal Server Error");
        assertEquals(successCallCount.get(), 1);
        assertEquals(failedCallCount.get(), 0);
        assertEquals(cancelledCallCount.get(), 0);
        VerifyHttp.verifyHttp(server).times(1, Condition.uri(path));
    }

    @Test
    public void testUnreachable()
        throws URISyntaxException, InterruptedException, TimeoutException, ExecutionException {
        AtomicInteger successCallCount = new AtomicInteger(0);
        AtomicInteger failedCallCount = new AtomicInteger(0);
        AtomicInteger cancelledCallCount = new AtomicInteger(0);
        FutureCallback<Response<String>> futureCallback =
            constructFutureCallback(successCallCount, failedCallCount, cancelledCallCount);

        AsyncHttpClient client = newAsyncHttpClient();
        String url = "http://not.exist.com/";
        Request request = newRequest(url, HttpMethod.GET);

        CompletableFuture<Response<String>> promise =
            client.executeWithCallback(request, futureCallback);
        promise.get(5, TimeUnit.MINUTES);
        Assert.assertEquals(futureCallback.toString(), "fail");
        assertEquals(successCallCount.get(), 0);
        assertEquals(failedCallCount.get(), 1);
        assertEquals(cancelledCallCount.get(), 0);
    }

    @Test
    public void testCircuitBreakerAsync()
        throws URISyntaxException, InterruptedException, ExecutionException, TimeoutException {

        String path = "/timeout_and_circuit_break";
        StubHttp.whenHttp(server).match(Condition.get(path)).then(timeoutAction);

        AtomicInteger successCallCount = new AtomicInteger(0);
        AtomicInteger failedCallCount = new AtomicInteger(0);
        AtomicInteger cancelledCallCount = new AtomicInteger(0);
        FutureCallback<Response<String>>
            futureCallback =
            constructFutureCallback(successCallCount, failedCallCount, cancelledCallCount);

        CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(10)
            .minimumNumberOfCalls(5) // will throw CallNotPermittedException after 5 failures
            .build();

        RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(500)
            .setConnectionRequestTimeout(30000)
            .setConnectTimeout(10000)
            .build();

        AsyncHttpClient
            client = new HttpClientBuilder("AsyncHttpClientTest")
            .circuitBreakerConfig(cbConfig)
            .turnOffRetry()
            .requestConfig(requestConfig)
            .buildAsyncClient();
        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);

        for (int i = 0; i < 12; i++) {
            CompletableFuture<Response<String>> promise =
                client.executeWithCallback(request, futureCallback);
            promise.get(5, TimeUnit.MINUTES);
        }
        VerifyHttp.verifyHttp(server).times(5, Condition.uri(path));
        assertEquals(successCallCount.get(), 0);
        assertEquals(failedCallCount.get(), 12);
        assertEquals(cancelledCallCount.get(), 0);
    }

    @Test
    public void testCircuitBreakerAsyncSuccessAfterRecovery()
        throws URISyntaxException, InterruptedException, ExecutionException, TimeoutException {

        String path = "/timeout_then_recover";

        StubHttp.whenHttp(server).match(Condition.get(path)).then(
            ActionSequence.sequence(timeoutAction, timeoutAction, timeoutAction, timeoutAction, timeoutAction, successAction,
                     successAction, successAction, successAction)
        );

        AtomicInteger successCallCount = new AtomicInteger(0);
        AtomicInteger failedCallCount = new AtomicInteger(0);
        AtomicInteger cancelledCallCount = new AtomicInteger(0);
        FutureCallback<Response<String>>
            futureCallback =
            constructFutureCallback(successCallCount, failedCallCount, cancelledCallCount);

        CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(5)
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(5)
            .waitDurationInOpenState(Duration.ofMillis(1000))
            .minimumNumberOfCalls(5) // will throw CallNotPermittedException after 5 failures
            .build();

        RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(500)
            .setConnectionRequestTimeout(30000)
            .setConnectTimeout(10000)
            .build();

        AsyncHttpClient
            client = new HttpClientBuilder("AsyncHttpClientTest")
            .circuitBreakerConfig(cbConfig)
            .turnOffRetry()
            .requestConfig(requestConfig)
            .buildAsyncClient();
        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);

        for (int i = 0; i < 8; i++) {
            CompletableFuture<Response<String>> promise =
                client.executeWithCallback(request, futureCallback);
            promise.get(5, TimeUnit.MINUTES);
        }

        sleep(2000);
        CompletableFuture<Response<String>> promise =
            client.executeWithCallback(request, futureCallback);
        promise.get(5, TimeUnit.MINUTES);

        VerifyHttp.verifyHttp(server).times(6, Condition.uri(path));
        assertEquals(successCallCount.get(), 1);
        assertEquals(failedCallCount.get(), 8);
        assertEquals(cancelledCallCount.get(), 0);
    }

    @Test
    public void testRetryAsyncSuccessAtThirdTime()
        throws URISyntaxException, InterruptedException, TimeoutException, ExecutionException {

        String path = "/success_at_third_time";
        StubHttp.whenHttp(server).match(Condition.get(path)).then(ActionSequence.sequence(timeoutAction, timeoutAction, successAction));

        String url = "http://localhost:" + server.getPort() + path;

        RetryConfig retryConfig = RetryConfig.custom()
            .maxAttempts(3) // 3 retries per call
            .build();
        RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(500)
            .setConnectionRequestTimeout(30000)
            .setConnectTimeout(10000)
            .build();

        AsyncHttpClient
            client = new HttpClientBuilder("AsyncHttpClientTest")
            .turnOffCircuitBreaker()
            .retryConfig(retryConfig)
            .requestConfig(requestConfig)
            .buildAsyncClient();

        Request request = newRequest(url, HttpMethod.GET);
        AtomicInteger successCallCount = new AtomicInteger(0);
        AtomicInteger failedCallCount = new AtomicInteger(0);
        AtomicInteger cancelledCallCount = new AtomicInteger(0);
        CompletableFuture<Response<String>> promise =
            client.executeWithCallback(
                request,
                constructFutureCallback(successCallCount, failedCallCount, cancelledCallCount)
            );

        promise.get(5, TimeUnit.MINUTES);
        sleep(5000);    // wait for mock server to complete status
        VerifyHttp.verifyHttp(server).times(3, Condition.uri(path));
        assertEquals(successCallCount.get(), 1);
        assertEquals(failedCallCount.get(), 0);
        assertEquals(cancelledCallCount.get(), 0);
    }

    @Test
    public void testRetryAsyncServerStillFail()
        throws URISyntaxException, InterruptedException, TimeoutException, ExecutionException {

        String path = "/fail_for_retry";
        StubHttp.whenHttp(server).match(Condition.get(path)).then(timeoutAction);

        String url = "http://localhost:" + server.getPort() + path;

        RetryConfig retryConfig = RetryConfig.custom()
            .maxAttempts(3) // 3 retries per call
            .build();
        RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(500)
            .setConnectionRequestTimeout(30000)
            .setConnectTimeout(10000)
            .build();
        AsyncHttpClient client = new HttpClientBuilder("AsyncHttpClientTest")
            .turnOffCircuitBreaker()
            .retryConfig(retryConfig)
            .requestConfig(requestConfig)
            .buildAsyncClient();
        Request request = newRequest(url, HttpMethod.GET);
        AtomicInteger successCallCount = new AtomicInteger(0);
        AtomicInteger failedCallCount = new AtomicInteger(0);
        AtomicInteger cancelledCallCount = new AtomicInteger(0);
        CompletableFuture<Response<String>> promise =
            client.executeWithCallback(
                request,
                constructFutureCallback(successCallCount, failedCallCount, cancelledCallCount)
            );

        promise.get(5, TimeUnit.MINUTES);
        sleep(5000);    // wait for mock server to complete status
        VerifyHttp.verifyHttp(server).times(3, Condition.uri(path));
        assertEquals(successCallCount.get(), 0);
        assertEquals(failedCallCount.get(), 1);
        assertEquals(cancelledCallCount.get(), 0);
    }

    private <T> FutureCallback<Response<T>> constructFutureCallback(
        AtomicInteger successCallCount,
        AtomicInteger failedCallCount,
        AtomicInteger cancelledCallCount
    ) {
        return new FutureCallback<Response<T>>() {
            private String state = "not called";

            @Override
            public void completed(Response response) {
                state = response.getStatusLine().toString();
                successCallCount.getAndIncrement();
            }

            @Override
            public void failed(Exception e) {
                state = "fail";
                failedCallCount.getAndIncrement();
            }

            @Override
            public void cancelled() {
                state = "cancelled";
                cancelledCallCount.getAndIncrement();
            }

            @Override
            public String toString() {
                return state;
            }
        };
    }
}
