package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author Rohtash Lakra
 * @Since 3/19/20 1:35 PM
 */
public enum Sets {
    INSTANCE;

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(Sets.class);

    /**
     * Returns the set of
     *
     * @param values
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> Set<T> asSet(T... values) {
        return INSTANCE.newArraySet(values);
    }

    /**
     * @param values
     * @param <T>
     * @return
     */
    public final <T> Set<T> newArraySet(T... values) {
        return new ArraySet(values);
    }

    /**
     * @param <E>
     */
    private final class ArraySet<E> extends AbstractSet implements Serializable {

        // unique set of values.
        private final List<E> values;

        /**
         * @param values
         */
        private ArraySet(E[] values) {
            LOGGER.debug("ArraySet({})", Arrays.toString(values));
            final List<E> tempList = Arrays.asList(values);
            this.values = new ArrayList<>(tempList.size());
            tempList.forEach(item -> {
                if (!this.values.contains(item)) {
                    this.values.add(item);
                }
            });
        }

        /**
         * @return
         */
        @Override
        public Iterator iterator() {
            return values.iterator();
        }

        /**
         * Returns the size of this array.
         *
         * @return
         */
        @Override
        public int size() {
            return this.values.size();
        }
    }

}
