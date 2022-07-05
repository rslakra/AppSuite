package com.rslakra.core.http;

import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class MockHttpClientBuilder {

    private MockBasicHttpResponse httpResponse;
    private BasicHttpEntity httpEntity;
    private Throwable executionError;

    public MockHttpClientBuilder prepareResponseCode(int code) {
        return prepareResponseCode(HttpVersion.HTTP_1_1, code, "");
    }

    public MockHttpClientBuilder prepareResponseCode(int code, String reason) {
        return prepareResponseCode(HttpVersion.HTTP_1_1, code, reason);
    }

    public MockHttpClientBuilder prepareResponseCode(ProtocolVersion ver, int code, String reason) {
        httpResponse = new MockBasicHttpResponse(ver, code, reason);
        return this;
    }

    public MockHttpClientBuilder prepareResponseContent(String content, String contentType) {
        return prepareResponseContent(content, contentType, StandardCharsets.UTF_8.name());
    }

    public MockHttpClientBuilder prepareResponseContent(
        String content, String contentType, String contentEncoding) {

        httpEntity = new BasicHttpEntity();
        try {
            httpEntity.setContent(new ByteArrayInputStream(content.getBytes(contentEncoding)));
            httpEntity.setContentLength(content.length());
            httpEntity.setContentEncoding(contentEncoding);
            httpEntity.setContentType(contentType);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public MockHttpClientBuilder prepareResponseContent(
        InputStream inputStream, String contentType, String contentEncoding, long contentLength) {

        httpEntity = new BasicHttpEntity();
        httpEntity.setContent(inputStream);
        httpEntity.setContentLength(contentLength);
        httpEntity.setContentEncoding(contentEncoding);
        httpEntity.setContentType(contentType);

        return this;
    }

    public MockHttpClientBuilder prepareExecutionError(Throwable t) {
        executionError = t;
        return this;
    }

    public MockHttpClient build() {
        MockHttpClient httpClient = new MockHttpClient();
        httpClient.setHttpResponse(httpResponse);
        if (httpResponse != null) {
            httpResponse.setEntity(httpEntity);
        }
        httpClient.setExecutionError(executionError);
        return httpClient;
    }
}
