package com.rslakra.core.algos.heap;

import java.util.Comparator;

/**
 * @author Rohtash Lakra
 * @created 5/17/22 2:17 PM
 */
public class MinHeap<V extends Comparable> extends Heap<V> implements Comparable {

    public MinHeap() {
        super(new Comparator<V>() {
            @Override
            public int compare(V o1, V o2) {
                return 0;
            }
        });
    }
}
