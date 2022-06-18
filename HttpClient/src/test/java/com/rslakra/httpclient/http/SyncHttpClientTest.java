package com.rslakra.httpclient.http;

import com.rslakra.core.IOUtils;
import com.rslakra.core.json.JSONUtils;
import com.rslakra.httpclient.*;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.retry.RetryConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.builder.verify.VerifyHttp.verifyHttp;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.ActionSequence.sequence;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.uri;
import static java.lang.Thread.sleep;
import static org.testng.Assert.*;

public class SyncHttpClientTest extends AbstractHttpClientTest {

    public SyncHttpClientTest(String name) {
        super(name);
    }

    private SyncHttpClient newSyncHttpClient() {
        return new HttpClientBuilder("SyncHttpClientTest").buildSyncClient();
    }

    @Test
    public void testSuccessWithoutHandler() throws Exception {

        String path = "/success";
        whenHttp(server).match(get(path)).then(successAction);

        SyncHttpClient client = newSyncHttpClient();
        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);

        Response<String> response = client.execute(request);

        assertNotNull(response);
        assertNotNull(response.getStatusLine());
        assertEquals(response.getStatusLine().getStatusCode(), 200);
        assertNotNull(response.getPayload());
        assertEquals(response.getPayload(), "success");

        verifyHttp(server).once(uri(path));
    }

    private MockObject newMockObject() {
        return newMockObject(MockStatus.ON);
    }

    private MockObject newMockObject(final MockStatus status) {
        final MockObject mockObject = new MockObject();
        mockObject.setId(123L);
        mockObject.setName("MockObject");
        mockObject.setStatus(status);
        return mockObject;
    }

    @Test
    public void testWithHandler() throws Exception {
        MockObject mockObject = newMockObject();
        String json = JSONUtils.toJson(mockObject);
        String path = "/success_handler";
        whenHttp(server).match(get(path)).then(stringContent(json));
        String url = "http://localhost:" + server.getPort() + path;

        ResponseHandler<MockObject> responseHandler = new ResponseHandler<MockObject>() {
            @Override
            public MockObject handleResponse(HttpResponse response) throws IOException {
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                MockObject mockObject = JSONUtils.fromJson(in, MockObject.class);
                IOUtils.closeSilently(in);
                return mockObject;
            }
        };

        SyncHttpClient client = newSyncHttpClient();
        Request request = newRequest(url, HttpMethod.GET);
        Response<MockObject> response = client.execute(request, responseHandler);

        assertNotNull(response);
        assertNotNull(response.getStatusLine());
        assertEquals(response.getStatusLine().getStatusCode(), 200);
        assertNotNull(response.getPayload());
        assertEquals(response.getPayload().toString(), mockObject.toString());
        verifyHttp(server).once(uri(path));
    }

    @Test
    public void testCircuitBreaker() throws Exception {

        String path = "/timeout_circuit_breaker";

        whenHttp(server).match(get(path)).then(timeoutAction);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(500)
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(10000)
                .build();

        CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slidingWindowType(SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5) // will throw CallNotPermittedException after 5 failures
                .build();

        SyncHttpClient client =
                new HttpClientBuilder("SyncHttpClientTest")
                        .requestConfig(requestConfig)
                        .circuitBreakerConfig(cbConfig)
                        .turnOffRetry()
                        .buildSyncClient();

        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);

        int circuitBreakerExceptionCount = 0;
        int nonCircuitBreakerExceptionCount = 0;

        for (int i = 0; i < 12; i++) {
            try {
                Response<?> response = client.execute(request);
                assertNotSame(response.getStatusLine().getStatusCode(), 200);
            } catch (CallNotPermittedException e) {
                circuitBreakerExceptionCount++;
            } catch (Exception e) {
                nonCircuitBreakerExceptionCount++;
            }
        }

        // The server adds its called times after delay while our call is back when socket time out
        // So sleep is needed to wait for the server to add to its called count
        sleep(2000);
        assertEquals(circuitBreakerExceptionCount, 12 - 5);
        assertEquals(nonCircuitBreakerExceptionCount, 5);
        verifyHttp(server).times(5, uri(path));
    }

    @Test
    public void testCircuitBreakerSuccessAfterRecovery() throws Exception {

        String path = "/timeout_then_recover";

        whenHttp(server).match(get(path)).then(
                sequence(timeoutAction, timeoutAction, timeoutAction, timeoutAction, timeoutAction, successAction,
                        successAction, successAction, successAction)
        );

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(500)
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(10000)
                .build();

        CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(5)
                .slidingWindowType(SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .minimumNumberOfCalls(5) // will throw CallNotPermittedException after 5 failures
                .build();

        SyncHttpClient client = new HttpClientBuilder("SyncHttpClientTest")
                .requestConfig(requestConfig)
                .circuitBreakerConfig(cbConfig)
                .turnOffRetry()
                .buildSyncClient();

        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);

        int circuitBreakerExceptionCount = 0;
        int nonCircuitBreakerExceptionCount = 0;

        for (int i = 0; i < 8; i++) {
            try {
                Response<String> response = client.execute(request);
                assertNotSame(response.getStatusLine().getStatusCode(), 200);
            } catch (CallNotPermittedException e) {
                circuitBreakerExceptionCount++;
            } catch (Exception e) {
                nonCircuitBreakerExceptionCount++;
            }
        }

        // The server adds its called times after delay while our call is back when socket time out
        // So sleep is needed to wait for the server to add to its called count
        sleep(2000);
        assertEquals(circuitBreakerExceptionCount, 8 - 5);
        assertEquals(nonCircuitBreakerExceptionCount, 5);

        Response<?> response = client.execute(request);
        verifyHttp(server).times(6, uri(path));
        assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void testRetrySuccessAtThirdTime() throws Exception {

        String path = "/success_at_third";
        whenHttp(server).match(get(path)).then(sequence(timeoutAction, timeoutAction, successAction));

        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3) // 3 retries per call
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(500)
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(10000)
                .build();

        SyncHttpClient client = new HttpClientBuilder("SyncHttpClientTest")
                .turnOffCircuitBreaker()
                .retryConfig(retryConfig)
                .requestConfig(requestConfig)
                .buildSyncClient();

        String url = "http://localhost:" + server.getPort() + path;

        Request request = newRequest(url, HttpMethod.GET);

        int failedCallCount = 0;
        try {
            Response<?> response = client.execute(request);
            assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (HttpClientException e) {
            failedCallCount++;
        }

        // The server adds its called times after delay while our call is back when socket time out
        // So sleep is needed to wait for the server to add to its called count
        sleep(2000);
        verifyHttp(server).times(3, uri(path));
        assertEquals(failedCallCount, 0);
    }

    @Test
    public void testRetryServerStillFail() throws Exception {

        String path = "/retry_still_fail";
        whenHttp(server).match(get(path)).then(timeoutAction);

        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3) // 3 retries per call
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(500)
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(10000)
                .build();

        SyncHttpClient client = new HttpClientBuilder("SyncHttpClientTest")
                .turnOffCircuitBreaker()
                .retryConfig(retryConfig)
                .requestConfig(requestConfig)
                .buildSyncClient();

        String url = "http://localhost:" + server.getPort() + path;
        Request request = newRequest(url, HttpMethod.GET);
        int failedCallCount = 0;
        try {
            Response<?> response = client.execute(request);
        } catch (HttpClientException e) {
            failedCallCount++;
        }

        // The server adds it's called times after delay while our call is back when socket time out
        // So sleep is needed to wait for the server to add to its called count
        sleep(2000);
        verifyHttp(server).times(3, uri(path));
        assertEquals(failedCallCount, 1);
    }
}
