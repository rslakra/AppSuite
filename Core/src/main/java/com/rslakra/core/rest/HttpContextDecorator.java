package com.rslakra.core.rest;

import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 10:39 AM
 */
public class HttpContextDecorator implements HttpContext {

    // delegate
    protected final HttpContext delegate;

    /**
     *
     */
    public HttpContextDecorator() {
        this(null);
    }

    /**
     * @param delegate
     */
    public HttpContextDecorator(final HttpContext delegate) {
        if (delegate == null) {
            this.delegate = new BasicHttpContext();
        } else {
            this.delegate = new BasicHttpContext(delegate);
        }
    }

    public Object getAttribute(final String name) {
        return this.delegate.getAttribute(name);
    }

    public Object removeAttribute(final String name) {
        return this.delegate.removeAttribute(name);
    }

    public void setAttribute(final String name, final Object value) {
        this.delegate.setAttribute(name, value);
    }
}

