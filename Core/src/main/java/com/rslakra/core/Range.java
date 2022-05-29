package com.rslakra.core;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/25/21 3:51 PM
 */

/**
 * @author Rohtash Lakra
 * @created 4/14/20 8:12 PM
 */
public final class Range {

    private final int from;
    private final int to;
    private final boolean reverse;

    /**
     * @param from
     * @param to
     * @param reverse
     */
    private Range(final int from, final int to, final boolean reverse) {
        this.from = from;
        this.to = to;
        this.reverse = reverse;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public boolean isReverse() {
        return reverse;
    }

    public static RangeBuilder newBuilder() {
        return new RangeBuilder();
    }

    /**
     *
     */
    public static class RangeBuilder {

        private int from;
        private int to;
        private boolean reverse;

        public RangeBuilder() {
        }

        public RangeBuilder setFrom(final int from) {
            this.from = from;
            return this;
        }

        public RangeBuilder setTo(final int to) {
            this.to = to;
            return this;
        }

        public RangeBuilder setReverse(final boolean reverse) {
            this.reverse = reverse;
            return this;
        }

        public Range build() {
            return new Range(this.from, this.to, this.reverse);
        }
    }
}

