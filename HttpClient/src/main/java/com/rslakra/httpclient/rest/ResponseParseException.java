package com.rslakra.httpclient.rest;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 10:46 AM
 */
public class ResponseParseException extends ResponseException {

    private Throwable cause;

    /**
     * @param response
     * @param cause
     */
    public ResponseParseException(HttpResponseDecorator response, Throwable cause) {
        super(response);
        this.cause = cause;
    }

    /**
     * @return
     */
    public Throwable getCause() {
        return this.cause;
    }
}
