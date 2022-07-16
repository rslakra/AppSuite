package com.rslakra.core.http;

import com.rslakra.core.BeanUtils;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.Getter;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLContext;

/**
 * Builder for <code>HttpClientBuilder</code>.
 * <p>
 * Allows customization of rest client, resilience4j, and SSL configuration.
 *
 * @author Rohtash Lakra (rlakra)
 * @created 3/26/21 5:40 PM
 */
@Getter
public final class HttpClientBuilder {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientBuilder.class);
    private static final String DEFAULT_USER_AGENT = "HttpClient";
    private static final int DEFAULT_SOCKET_TIMEOUT = 60000;
    private static final int DEFAULT_CONN_REQUEST_TIMEOUT = 30000;
    private static final int DEFAULT_CONN_TIMEOUT = 10000;

    private String clientName;
    private CircuitBreakerConfig circuitBreakerConfig;
    private boolean enableCircuitBreaker = true;
    private RetryConfig retryConfig;
    private boolean enableRetry = true;
    private RequestConfig requestConfig;
    private CloseableHttpClient httpSyncClient;
    private CloseableHttpAsyncClient httpAsyncClient;
    private CircuitBreaker circuitBreaker;
    private Retry retry;
    private HttpRoutePlanner routePlanner;

    /**
     * @param clientName used as the resilience4j registry key.
     */
    public HttpClientBuilder(final String clientName) {
        BeanUtils.notNull(clientName, "clientName must provide!");
        this.clientName = clientName;
    }

    /**
     * @return
     */
    public HttpClientBuilder turnOffCircuitBreaker() {
        this.enableCircuitBreaker = false;
        return this;
    }

    /**
     * @param circuitBreakerConfig
     * @return
     */
    public HttpClientBuilder circuitBreakerConfig(CircuitBreakerConfig circuitBreakerConfig) {
        this.circuitBreakerConfig = circuitBreakerConfig;
        return this;
    }

    /**
     * @return
     */
    public HttpClientBuilder turnOffRetry() {
        this.enableRetry = false;
        return this;
    }

    /**
     * @param retryConfig
     * @return
     */
    public HttpClientBuilder retryConfig(RetryConfig retryConfig) {
        this.retryConfig = retryConfig;
        return this;
    }

    /**
     * @param requestConfig
     * @return
     */
    public HttpClientBuilder requestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
        return this;
    }

    /**
     * @param httpSyncClient
     * @return
     */
    public HttpClientBuilder httpSyncClient(CloseableHttpClient httpSyncClient) {
        this.httpSyncClient = httpSyncClient;
        return this;
    }

    /**
     * @param httpAsyncClient
     * @return
     */
    public HttpClientBuilder httpAsyncClient(CloseableHttpAsyncClient httpAsyncClient) {
        this.httpAsyncClient = httpAsyncClient;
        return this;
    }

    /**
     * @param routePlanner
     * @return
     */
    public HttpClientBuilder routePlanner(HttpRoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
        return this;
    }

    /**
     *
     */
    private void buildCircuitBreaker() {
        if (BeanUtils.isNull(circuitBreakerConfig)) {
            circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(25)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(50)
                .minimumNumberOfCalls(10)
                .slowCallDurationThreshold(Duration.ofMillis(5000))
                .slowCallRateThreshold(50)
                .build();
        }

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();
        Optional<CircuitBreakerConfig> oldConfig = registry.getConfiguration(clientName);
        if (oldConfig.isPresent()) {
            LOGGER.warn("buildCircuitBreaker() - overriding resilience4j circuit breaker config. name={}", clientName);
        }

        circuitBreaker = registry.circuitBreaker(clientName, circuitBreakerConfig);
    }

    private void buildRetry() {
        if (BeanUtils.isNull(retryConfig)) {
            retryConfig = RetryConfig.custom()
                .maxAttempts(3)
                .intervalFunction(IntervalFunction.ofExponentialBackoff())
                .build();
        }

        RetryRegistry registry = RetryRegistry.ofDefaults();
        Optional<RetryConfig> oldConfig = registry.getConfiguration(clientName);
        if (oldConfig.isPresent()) {
            LOGGER.warn("buildRetry() - overriding resilience4j circuit breaker config. name=%s",
                        clientName);
        }

        retry = registry.retry(clientName, retryConfig);
    }

    /**
     * @return
     */
    private RequestConfig getDefaultRequestConfig() {
        if (BeanUtils.isNull(requestConfig)) {
            requestConfig = RequestConfig.custom()
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                .setConnectionRequestTimeout(DEFAULT_CONN_REQUEST_TIMEOUT)
                .setConnectTimeout(DEFAULT_CONN_TIMEOUT)
                .build();
        }

        return requestConfig;
    }

    /**
     * @return
     */
    public static List<BasicHeader> getDefaultHeaders() {
        return Arrays.asList(
            new BasicHeader(HttpHeaders.ACCEPT, HTTPUtils.WILDCARD),
            new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, HTTPUtils.WILDCARD)
        );
    }

    private void buildHttpClient() {
        org.apache.http.impl.client.HttpClientBuilder builder = org.apache.http.impl.client.HttpClientBuilder.create()
            .setUserAgent(DEFAULT_USER_AGENT)
            .setDefaultRequestConfig(getDefaultRequestConfig())
            .setConnectionReuseStrategy(new DefaultConnectionReuseStrategy())
            .setDefaultHeaders(getDefaultHeaders())
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(Integer.MAX_VALUE)
            .disableRedirectHandling()
            .setSSLContext(buildSSLContext());

        if (BeanUtils.isNull(routePlanner)) {
            builder.setRoutePlanner(routePlanner);
        }

        httpSyncClient = builder.build();
    }

    private void buildHttpAsyncClient() {
        HttpAsyncClientBuilder asyncBuilder = HttpAsyncClientBuilder.create()
            .setUserAgent(DEFAULT_USER_AGENT)
            .setDefaultRequestConfig(getDefaultRequestConfig())
            .setConnectionReuseStrategy(new DefaultConnectionReuseStrategy())
            .setDefaultHeaders(getDefaultHeaders())
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(Integer.MAX_VALUE)
            .setSSLContext(buildSSLContext());

        if (routePlanner != null) {
            asyncBuilder.setRoutePlanner(routePlanner);
        }

        httpAsyncClient = asyncBuilder.build();
    }

    private SSLContext buildSSLContext() {
        SSLContext sslContext = null;
        try {
            LOGGER.warn("buildSSLContext() - Not using mTLS+Athenz for clientName=%s", clientName);
            sslContext = SSLContext.getDefault();
        } catch (Exception ex) {
            LOGGER.error("buildSSLContext()", ex, "Error building SSLContext");
            throw new HttpClientException(ex);
        }

        return sslContext;
    }

    public SyncHttpClient buildSyncClient() {
        if (enableRetry) {
            buildRetry();
        }
        if (enableCircuitBreaker) {
            buildCircuitBreaker();
        }
        if (BeanUtils.isNull(httpSyncClient)) {
            buildHttpClient();
        }

        return new SyncHttpClient(this);
    }

    public AsyncHttpClient buildAsyncClient() {
        if (enableRetry) {
            buildRetry();
        }
        if (enableCircuitBreaker) {
            buildCircuitBreaker();
        }
        if (BeanUtils.isNull(httpAsyncClient)) {
            buildHttpAsyncClient();
        }

        return new AsyncHttpClient(this);
    }

}
