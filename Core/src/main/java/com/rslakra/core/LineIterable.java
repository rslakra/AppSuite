package com.rslakra.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Rohtash Lakra
 * @created 1/21/21 10:51 AM
 */
public class LineIterable implements Iterable<Character> {

    private final CharSequence textContent;

    public LineIterable(final CharSequence textContent) {
        this.textContent = textContent;
    }

    /**
     *
     */
    private class CharIterator implements Iterator<Character> {

        private int index;

        private CharIterator() {
            index = 0;
        }

        /**
         * @return
         */
        @Override
        public boolean hasNext() {
            return (index < textContent.length());
        }

        /**
         * @return
         */
        @Override
        public Character next() {
            if (this.hasNext()) {
                return Character.valueOf(textContent.charAt(index++));
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() method is not supported");
        }
    }

    /**
     * @return
     */
    public Iterator<Character> iterator() {
        return new CharIterator();
    }
}
