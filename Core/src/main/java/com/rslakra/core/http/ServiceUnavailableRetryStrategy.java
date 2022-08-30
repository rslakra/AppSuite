package com.rslakra.core.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.protocol.HttpContext;

/**
 * @author Rohtash Lakra
 * @created 2/28/20 10:42 AM
 */
public final class ServiceUnavailableRetryStrategy extends DefaultServiceUnavailableRetryStrategy {

    private final int maxRetries;

    /**
     * @param maxRetries
     * @param retryInterval
     */
    public ServiceUnavailableRetryStrategy(final int maxRetries, final int retryInterval) {
        super(maxRetries, retryInterval);
        this.maxRetries = maxRetries;
    }

    /**
     * @return
     */
    public int getMaxRetries() {
        return maxRetries;
    }

    /**
     * @param response
     * @param executionCount
     * @param context
     * @return
     */
    @Override
    public boolean retryRequest(final HttpResponse response, final int executionCount, final HttpContext context) {
        return executionCount <= maxRetries
               && response.getStatusLine().getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }
}
