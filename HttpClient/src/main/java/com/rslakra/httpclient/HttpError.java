package com.rslakra.httpclient;

import java.util.List;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @Created May 14, 2022 21:03:55
 */
public class HttpError implements Cloneable {

    private List<Error> errors;

    public HttpError() {
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(final List<Error> errors) {
        this.errors = errors;
    }

    /**
     * Creates exact copy of an object.
     *
     * @return
     * @throws CloneNotSupportedException
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        HttpError httpError = (HttpError) super.clone();
        httpError.setErrors(getErrors());
        return httpError;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "HttpError {errors=" + errors + "}";
    }
}
