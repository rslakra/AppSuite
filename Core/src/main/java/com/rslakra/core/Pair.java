package com.rslakra.core;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 4/7/21 5:17 PM
 */
public final class Pair<L, R> implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable {

    public static final Pair<?, ?>[] EMPTY_ARRAY = new Pair[0];
    public static final Pair NULL = of((Object) null, (Object) null);
    private final L left;
    private final R right;

    /**
     * @param left
     * @param right
     */
    private Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * @return
     */
    public L getLeft() {
        return this.left;
    }

    /**
     * @return
     */
    public R getRight() {
        return this.right;
    }

    /**
     * @param value
     * @return
     */
    public R setValue(R value) {
        throw new UnsupportedOperationException();
    }

    public L getKey() {
        return this.getLeft();
    }

    public R getValue() {
        return this.getRight();
    }

    /**
     * @param other
     * @return
     */
    @Override
    public int compareTo(Pair<L, R> other) {
        return (new CompareToBuilder()).append(this.getLeft(), other.getLeft())
                .append(this.getRight(), other.getRight()).toComparison();
    }

    /**
     * @param object
     * @return
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        } else if (object == null || !(object instanceof Map.Entry)) {
            return false;
        } else {
            Map.Entry<?, ?> other = (Map.Entry) object;
            return Objects.equals(this.getKey(), other.getKey()) && Objects.equals(this.getValue(), other.getValue());
        }
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return (Objects.hashCode(this.getKey()) ^ Objects.hashCode(this.getValue()));
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "(" + this.getLeft() + ',' + this.getRight() + ')';
    }

    /**
     * @param format
     * @return
     */
    public String toString(String format) {
        return String.format(format, this.getLeft(), this.getRight());
    }

    /**
     * Returns the Pair object.
     *
     * @param left
     * @param right
     * @param <L>
     * @param <R>
     * @return
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair(left, right);
    }

    /**
     * Returns the Map.Entry pair.
     *
     * @param pair
     * @param <L>
     * @param <R>
     * @return
     */
    public static <L, R> Pair<L, R> of(Map.Entry<L, R> pair) {
        return (pair == null ? NULL : of(pair.getKey(), pair.getValue()));
    }

    /**
     * @param <L>
     * @param <R>
     * @return
     */
    public static <L, R> Pair<L, R>[] emptyArray() {
        return (Pair[]) EMPTY_ARRAY;
    }

}
