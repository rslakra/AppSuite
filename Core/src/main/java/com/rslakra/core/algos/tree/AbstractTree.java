package com.rslakra.core.algos.tree;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/17/22 2:05 PM
 */
public abstract class AbstractTree<V extends Comparable> implements Iterable, Comparable {

    /**
     * Returns the parent node index based on the provided index.
     *
     * @param index
     * @return
     */
    public static int getParentIndex(final int index) {
        return ((index - 1) / 2);
    }

    /**
     * Returns the left node index based on the provided index.
     *
     * @param index
     * @return
     */
    public static int getLeftIndex(final int index) {
        return (2 * index + 1);
    }

    /**
     * Returns the right node index based on the provided index.
     *
     * @param index
     * @return
     */
    public static int getRightIndex(final int index) {
        return (2 * index + 2);
    }


}
