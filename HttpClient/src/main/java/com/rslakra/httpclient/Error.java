package com.rslakra.httpclient;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @Created May 14, 2022 21:02:56
 */
public class Error {
    private String field;
    private String message;

    public Error() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Error {" + "field='" + field + '\'' + ", message='" + message + '\'' + "}";
    }
}
