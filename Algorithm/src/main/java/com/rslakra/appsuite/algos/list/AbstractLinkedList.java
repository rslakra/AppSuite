package com.rslakra.appsuite.algos.list;

/**
 * @author Rohtash Lakra
 * @created 5/20/22 11:53 AM
 */
public abstract class AbstractLinkedList<T> extends AbstractList<T> implements Comparable<T> {

    /**
     * @param value
     * @return
     */
    public abstract boolean addHead(T value);

    /**
     * @param value
     * @return
     */
    public abstract boolean addTail(T value);

    /**
     * @param value
     * @return
     */
    public abstract boolean addNode(T value);

    /**
     * @param value
     * @return
     */
    public abstract T find(T value);

    /**
     * @param value
     * @return
     */
    public abstract boolean removeHead(T value);

    /**
     * @param value
     * @return
     */
    public abstract boolean removeTail(T value);

    /**
     * @param value
     * @return
     */
    public abstract boolean removeNode(T value);

//    public abstract boolean addHead(Object value);
//
//    public abstract boolean addTail(Object value);
//
//    public abstract boolean addNode(Object value);
//
//    public abstract Object find(Object value);
//
//    public abstract boolean removeHead(Object value);
//
//    public abstract boolean removeTail(Object value);
//
//    public abstract boolean removeNode(Object value);
}
