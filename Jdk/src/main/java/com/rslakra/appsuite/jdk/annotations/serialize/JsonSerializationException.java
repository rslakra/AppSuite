package com.rslakra.appsuite.jdk.annotations.serialize;

/**
 * @author Rohtash Lakra
 * @created 10/3/19 9:04 AM
 */
public class JsonSerializationException extends RuntimeException {

    /**
     *
     */
    public JsonSerializationException() {
        super();
    }

    /**
     * @param message
     */
    public JsonSerializationException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public JsonSerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public JsonSerializationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public JsonSerializationException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
