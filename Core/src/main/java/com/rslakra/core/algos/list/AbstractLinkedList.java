package com.rslakra.core.algos.list;

/**
 * @author Rohtash Lakra
 * @created 5/20/22 11:53 AM
 */
public abstract class AbstractLinkedList<T> implements Comparable<T> {

    private int size;

    /**
     * Returns the number of elements in this list.  If this list contains more than {@code Integer.MAX_VALUE} elements,
     * returns {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     *
     */
    protected void incrementSize() {
        size++;
    }

    /**
     *
     */
    protected void decrementSize() {
        size--;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return (size() == 0);
    }


    public abstract boolean addHead(T value);

    public abstract boolean addTail(T value);

    public abstract boolean addNode(T value);

    public abstract T find(T value);

    public abstract boolean contains(T value);

    public abstract boolean removeHead(T value);

    public abstract boolean removeTail(T value);

    public abstract boolean removeNode(T value);

}
