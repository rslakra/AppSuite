package com.rslakra.core.http;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class MockHttpClient extends CloseableHttpClient {

    private boolean closed = false;
    private CloseableHttpResponse httpResponse;
    private Throwable executionError;

    MockHttpClient() {
    }

    public CloseableHttpResponse getHttpResponse() {
        return httpResponse;
    }

    void setHttpResponse(CloseableHttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public Throwable getExecutionError() {
        return executionError;
    }

    void setExecutionError(Throwable executionError) {
        this.executionError = executionError;
    }

    public boolean isClosed() {
        return closed;
    }

    @Override
    protected CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context)
        throws IOException {

        if (executionError != null) {
            if (executionError instanceof IOException) {
                throw (IOException) executionError;
            } else if (executionError instanceof RuntimeException) {
                throw (RuntimeException) executionError;
            } else {
                throw new RuntimeException(executionError);
            }
        }

        return httpResponse;
    }

    @Override
    public void close() {
        closed = true;
    }

    @Override
    public HttpParams getParams() {
        throw new UnsupportedOperationException("no implementation for mock object");
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        throw new UnsupportedOperationException("no implementation for mock object");
    }
}
