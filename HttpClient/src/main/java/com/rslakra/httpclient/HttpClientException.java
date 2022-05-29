package com.rslakra.httpclient;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/26/21 3:29 PM
 */
public class HttpClientException extends RuntimeException {

    /**
     *
     */
    public HttpClientException() {
        super();
    }

    /**
     * @param message
     */
    public HttpClientException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public HttpClientException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    protected HttpClientException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
