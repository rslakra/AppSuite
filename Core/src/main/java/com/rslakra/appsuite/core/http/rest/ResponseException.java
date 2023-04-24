package com.rslakra.appsuite.core.http.rest;

import org.apache.http.client.HttpResponseException;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 10:41 AM
 */
public class ResponseException extends HttpResponseException {

    private HttpResponseDecorator response;

    /**
     * @param response
     */
    public ResponseException(HttpResponseDecorator response) {
        super(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
        this.response = response;
    }

    /**
     * @return
     */
    public HttpResponseDecorator getResponse() {
        return this.response;
    }

}
