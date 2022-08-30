package com.rslakra.core.algos;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Rohtash Lakra
 * @created 3/25/21 3:44 PM
 */
public final class UniqueIterator<E> implements Iterator<E> {

    private final Iterator<E> delegate;
    private final Set<E> visited;
    private boolean exhausted;
    private E next;

    /**
     * @param delegate
     * @param comparator
     */
    private UniqueIterator(final Iterator<E> delegate, final Comparator<E> comparator) {
        this.delegate = delegate;
        this.visited = new TreeSet(comparator);
        this.moveToNext();
    }

    /**
     *
     * @return
     */
    public boolean isExhausted() {
        return exhausted;
    }

    private void moveToNext() {
        boolean hasNext = false;
        while (!hasNext && !this.exhausted) {
            this.exhausted = !this.delegate.hasNext();
            if (!this.exhausted) {
                this.next = this.delegate.next();
                hasNext = this.visited.add(this.next);
            }
        }
    }

    /**
     * @return
     */
    public boolean hasNext() {
        return !this.exhausted;
    }

    public E next() {
        if (this.exhausted) {
            throw new NoSuchElementException();
        } else {
            E result = this.next;
            this.moveToNext();
            return result;
        }
    }

    public void remove() {
        if (this.exhausted) {
            throw new NoSuchElementException();
        } else {
            this.delegate.remove();
        }
    }
}
