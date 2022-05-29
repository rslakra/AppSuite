package com.rslakra.httpclient;

import com.rslakra.core.utils.ToString;
import org.apache.http.StatusLine;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/26/21 6:07 PM
 */
public class Response<T> {

    private StatusLine statusLine;
    private T payload;

    /**
     * @param statusLine
     * @param payload
     */
    protected Response(final StatusLine statusLine, final T payload) {
        this.statusLine = statusLine;
        this.payload = payload;
    }

    /**
     * @return
     */
    public StatusLine getStatusLine() {
        return statusLine;
    }

    /**
     * @return
     */
    public T getPayload() {
        return payload;
    }

    /**
     * @return
     */
    public String toString() {
        return ToString.of(Response.class)
                .add("statusLine", getStatusLine())
                .add("payload", getPayload())
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
}
