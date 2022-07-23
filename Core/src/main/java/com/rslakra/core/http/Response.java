package com.rslakra.core.http;

import com.rslakra.core.BeanUtils;
import com.rslakra.core.IOUtils;
import com.rslakra.core.ToString;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicStatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/26/21 6:07 PM
 */
public final class Response<T> implements Cloneable {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);

    private final Map<String, List<String>> requestHeaders = new LinkedHashMap<>();
    private final Map<String, List<String>> responseHeaders = new LinkedHashMap<>();
    private StatusLine statusLine;
    private T payload;
    private byte[] dataBytes;
    private Throwable error;

    /**
     * @param statusLine
     * @param payload
     */
    public Response(final StatusLine statusLine, final T payload) {
        this.statusLine = statusLine;
        this.payload = payload;
    }

    /**
     * @return
     */
    public Map<String, List<String>> getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * @param requestHeaders
     */
    public void addRequestHeaders(final Map<String, List<String>> requestHeaders) {
        if (BeanUtils.isNotEmpty(requestHeaders)) {
            this.requestHeaders.putAll(requestHeaders);
        }
    }

    /**
     * @param key
     * @param values
     */
    public void addRequestHeader(final String key, final List<String> values) {
        if (this.requestHeaders.containsKey(key)) {
            this.requestHeaders.get(key).addAll(values);
        } else {
            this.requestHeaders.put(key, values);
        }
    }

    private void addRequestHeader(final String key, String value) {
        addResponseHeader(key, Arrays.asList(value));
    }

    /**
     * @return
     */
    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * @param responseHeaders
     */
    public void addResponseHeaders(final Map<String, List<String>> responseHeaders) {
        if (BeanUtils.isNotEmpty(responseHeaders)) {
            this.responseHeaders.putAll(responseHeaders);
        }
    }

    /**
     * @param key
     * @param values
     */
    public void addResponseHeader(final String key, final List<String> values) {
        if (this.responseHeaders.containsKey(key)) {
            this.responseHeaders.get(key).addAll(values);
        } else {
            this.responseHeaders.put(key, values);
        }
    }

    private void addResponseHeader(final String key, String value) {
        addResponseHeader(key, Arrays.asList(value));
    }

    /**
     * @return
     */
    public StatusLine getStatusLine() {
        return statusLine;
    }

    /**
     * @param statusLine
     */
    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    /**
     * @return
     */
    public T getPayload() {
        return payload;
    }

    /**
     * @param payload
     */
    public void setPayload(T payload) {
        this.payload = payload;
    }

    /**
     * @return
     */
    public byte[] getDataBytes() {
        return dataBytes;
    }

    /**
     * @param dataBytes
     */
    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    /**
     * @return
     */
    public Throwable getError() {
        return error;
    }

    /**
     * @param error
     */
    public void setError(Throwable error) {
        this.error = error;
    }

    /**
     * @return
     */
    public boolean isSuccess() {
        return (BeanUtils.isNotNull(getStatusLine()) && getStatusLine().getStatusCode() == 200);
    }

    /**
     * @return
     */
    public boolean isError() {
        return BeanUtils.isNotNull(getError());
    }

    /**
     * Returns the response type.
     *
     * @return
     */
    public String getMimeType() {
        String mimeType = null;
        if (BeanUtils.isNotEmpty(responseHeaders)) {
            mimeType = responseHeaders.get(IOUtils.CONTENT_TYPE).get(0);
            if (mimeType.indexOf(";") != -1) {
                mimeType = mimeType.substring(0, mimeType.indexOf(";")).trim();
            }
        }

        return mimeType;
    }

    /**
     * Creates exact copy of an object.
     *
     * @return
     * @see java.lang.Object#clone()
     */
    public Response clone() {
        Response cloneResponse = null;
        try {
            cloneResponse = (Response) super.clone();
            cloneResponse.addRequestHeaders(this.getRequestHeaders());
            cloneResponse.addResponseHeaders(this.getResponseHeaders());
            cloneResponse.setStatusLine(this.getStatusLine());
            cloneResponse.setDataBytes(this.getDataBytes());
            cloneResponse.setError(this.getError());
        } catch (CloneNotSupportedException ex) {
            LOGGER.error(ex.getMessage(), ex);
            // This should never happen
            throw new InternalError(ex.toString());
        }

        return cloneResponse;
    }

    /**
     * Returns the string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToString.of(Response.class)
            .add("statusLine", getStatusLine())
            .add("requestHeaders", getRequestHeaders())
            .add("responseHeaders", getResponseHeaders())
            .add("payload", getPayload())
            .add("dataBytes", IOUtils.toUTF8String(getDataBytes()))
            .add("error", BeanUtils.toString(getError()))
            .add("error", getError())
            .toString();
    }

    /**
     * @param statusLine
     * @param payload
     * @param <T>
     * @return
     */
    public static <T> Response of(final StatusLine statusLine, final T payload) {
        return new Response(statusLine, payload);
    }

    /**
     * @param statusLine
     * @param <T>
     * @return
     */
    public static <T> Response of(final StatusLine statusLine) {
        return of(statusLine, null);
    }

    /**
     * @param protocol
     * @param statusCode
     * @param reasonPhrase
     * @param <T>
     * @return
     */
    public static <T> Response of(final String protocol, final int statusCode, final String reasonPhrase) {
        return of(new BasicStatusLine(new ProtocolVersion(protocol, 1, 1), statusCode, reasonPhrase));
    }
}
