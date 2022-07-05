package com.rslakra.core.rest;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 5:54 PM
 */

public enum Status {
    SUCCESS(100, 399),
    FAILURE(400, 999);

    private final int min;
    private final int max;

    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * @param statusCode
     * @return
     */
    public boolean matches(int statusCode) {
        return this.min <= statusCode && statusCode <= this.max;
    }

    /**
     * @param statusCode
     * @return
     */
    public static Status find(int statusCode) {
        Status[] codes = values();
        for (int i = 0; i < codes.length; ++i) {
            if (codes[i].matches(statusCode)) {
                return codes[i];
            }
        }

        throw new IllegalArgumentException("Unknown status: " + statusCode);
    }

    /**
     * @param min
     * @param max
     */
    private Status(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

