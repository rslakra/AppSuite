package com.rslakra.appsuite.core.http.rest;

import com.rslakra.appsuite.core.ToString;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 5:54 PM
 */

public enum RestStatus {
    SUCCESS(100, 399),
    FAILURE(400, 999);

    private final int min;
    private final int max;

    /**
     * @param min
     * @param max
     */
    private RestStatus(final int min, final int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     * @return
     */
    public int getMax() {
        return max;
    }

    /**
     * ToString
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(RestStatus.class)
            .add("min", getMin())
            .add("max", getMax())
            .toString();
    }

    /**
     * @param statusCode
     * @return
     */
    public boolean matches(final int statusCode) {
        return (this.min <= statusCode && statusCode <= this.max);
    }

    /**
     * @param statusCode
     * @return
     */
    public static RestStatus find(final int statusCode) {
        RestStatus[] restStatuses = values();
        for (int i = 0; i < restStatuses.length; ++i) {
            if (restStatuses[i].matches(statusCode)) {
                return restStatuses[i];
            }
        }

        throw new IllegalArgumentException("Unknown status: " + statusCode);
    }


}

