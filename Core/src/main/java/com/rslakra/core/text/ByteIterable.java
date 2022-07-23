package com.rslakra.core.text;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @Created Jul 22, 2022 19:15:15
 */
public class ByteIterable implements Iterable<Byte> {

    private final CharSequence textContent;

    /**
     * @param textContent
     */
    public ByteIterable(final CharSequence textContent) {
        this.textContent = textContent;
    }

    /**
     * @return
     */
    public Iterator<Byte> iterator() {
        return new ByteIterator(textContent);
    }
}
