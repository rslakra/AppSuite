package com.rslakra.core;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author Rohtash Lakra
 * @Since 3/19/20 1:35 PM
 */
public class Sets {

    /**
     * Returns the set of
     *
     * @param values
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> Set<T> asSet(T... values) {
        return new ArraySet(values);
    }

    /**
     * @param <E>
     */
    private static class ArraySet<E> extends AbstractSet implements Serializable {

        // unique set of values.
        private final List<E> values;

        ArraySet(E[] values) {
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

//        /**
//         * @return
//         */
//        @Override
//        public Spliterator spliterator() {
//            return this.values.spliterator();
//        }
//
//        /**
//         * @param filter
//         * @return
//         */
//        @Override
//        public boolean removeIf(Predicate filter) {
//            return this.values.removeIf(filter);
//        }
//
//        /**
//         * @return
//         */
//        @Override
//        public Stream stream() {
//            return this.values.stream();
//        }
//
//        @Override
//        public Stream parallelStream() {
//            return this.values.parallelStream();
//        }
//
//        /**
//         * @param action
//         */
//        @Override
//        public void forEach(Consumer action) {
//            this.values.forEach(action);
//        }
    }

}
