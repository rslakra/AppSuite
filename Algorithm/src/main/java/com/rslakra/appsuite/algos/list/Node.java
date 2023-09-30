package com.rslakra.appsuite.algos.list;

import lombok.Data;

import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 8/17/20 3:32 PM
 */
@Data
public class Node<T extends Comparable> implements Comparable<Node> {

    private Node previous;
    private T value;
    private Node next;

    /**
     * @param value
     */
    public Node(T value) {
        this(null, value, null);
    }

    /**
     * @param value
     * @param next
     */
    public Node(T value, Node next) {
        this(null, value, next);
    }

    /**
     * @param previous
     * @param value
     * @param next
     */
    public Node(Node previous, T value, Node next) {
        this.previous = null;
        this.value = value;
        this.next = null;
    }

    /**
     * @return
     */
    public boolean hasPrevious() {
        return Objects.nonNull(getPrevious());
    }

    /**
     * @return
     */
    public boolean hasNext() {
        return Objects.nonNull(getNext());
    }

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))} for all {@code x} and {@code y}.  (This implies that
     * {@code x.compareTo(y)} must throw an exception iff {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any class that implements the
     * {@code Comparable} interface and violates this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(Node other) {
        return (getValue().compareTo(other.getValue()));
    }

    /**
     * Returns true if the node value is greater than the <code>other</code> node's value otherwise false.
     *
     * @param other
     * @return
     */
    public boolean isGreaterThan(Node other) {
        return (this.compareTo(other) > 0);
    }

    /**
     * Returns true if the node value is less than the <code>other</code> node's value otherwise false.
     *
     * @param other
     * @return
     */
    public boolean isLessThan(Node other) {
        return (this.compareTo(other) < 0);
    }

    /**
     * Returns true if the node value is equals to the <code>other</code> node's value otherwise false.
     *
     * @param other
     * @return
     */
    public boolean isEquals(Node other) {
        return (this.compareTo(other) == 0);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getValue(), next);
    }

    /**
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Node)) {
            return false;
        }

        Node node = (Node) object;
        if (!isEquals(node)) {
            return false;
        }

        return Objects.equals(next, node.next);
    }
}
