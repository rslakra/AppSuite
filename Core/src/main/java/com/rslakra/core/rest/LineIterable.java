package com.rslakra.core.rest;

import java.util.Iterator;

/**
 * @author Rohtash Lakra
 * @created 1/21/21 10:51 AM
 */
public class LineIterable implements Iterable<String> {

    private final CharSequence delegate;

    public LineIterable(final CharSequence delegate) {
        this.delegate = delegate;
    }

    public Iterator<String> iterator() {
        return null;
//        return IOGroovyMethods.iterator(new CharSequenceReader(this.delegate));
    }
}
