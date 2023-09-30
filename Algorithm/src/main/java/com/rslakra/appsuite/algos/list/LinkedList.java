package com.rslakra.appsuite.algos.list;

/**
 * @author Rohtash Lakra
 * @created 5/20/22 11:53 AM
 */
public class LinkedList<T extends Comparable> extends AbstractLinkedList<T> {

    private Node<T> root;

    public LinkedList() {
    }

    /**
     * @param value
     * @return
     */
    @Override
    public boolean addHead(T value) {
        return false;
    }

    /**
     * @param value
     * @return
     */
    @Override
    public boolean addTail(T value) {
        return false;
    }

    /**
     * @param value
     * @return
     */
    @Override
    public boolean addNode(T value) {
        return false;
    }

    /**
     * @param value
     * @return
     */
    @Override
    public T find(T value) {
        return null;
    }

    /**
     * @param value
     * @return
     */
    @Override
    public boolean removeHead(T value) {
        return false;
    }

    /**
     * @param value
     * @return
     */
    @Override
    public boolean removeTail(T value) {
        return false;
    }

    /**
     * @param value
     * @return
     */
    @Override
    public boolean removeNode(T value) {
        return false;
    }
}
