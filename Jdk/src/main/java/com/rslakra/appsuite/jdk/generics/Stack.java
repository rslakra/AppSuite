package com.rslakra.appsuite.jdk.generics;

/**
 * @author Rohtash Lakra
 * @created 7/24/20 5:19 PM
 */
public class Stack<E> {

    private E[] elements;
    private int index = 0;

    public Stack(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    /**
     * @param element
     */
    public void push(E element) {
        elements[index++] = element;
    }

    /**
     * @return
     */
    public E pop() {
        if (index < 0) {
            throw new RuntimeException("Underflow");
        }

        return elements[--index];
    }


}
