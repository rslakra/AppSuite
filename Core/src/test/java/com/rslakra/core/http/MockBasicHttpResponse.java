package com.rslakra.core.http;

import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHttpResponse;

public class MockBasicHttpResponse extends BasicHttpResponse implements CloseableHttpResponse {

    private boolean closed = false;

    public MockBasicHttpResponse(ProtocolVersion ver, int code, String reason) {
        super(ver, code, reason);
    }

    @Override
    public void close() {
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }
}
