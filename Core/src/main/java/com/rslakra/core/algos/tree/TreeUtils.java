package com.rslakra.core.algos.tree;

/**
 * The formulae for calculating the array indices of the various relatives of a node are as follows. The total number of
 * nodes in the tree is n. The index of the node in question is r, which must fall in the range 0 to n−1.
 * <p>
 * Parent(r) =⌊(r−1)/2⌋ if r≠0.
 * <p>
 * Left child(r) =2r+1 if 2r+1<n.
 * <p>
 * Right child(r) =2r+2 if 2r+2<n.
 * <p>
 * Left sibling(r) =r−1 if r is even and r≠0.
 * <p>
 * Right sibling(r) =r+1 if r is odd and r+1<n.
 *
 * @author Rohtash Lakra
 * @created 5/18/22 1:54 PM
 */
public enum TreeUtils {
    INSTANCE;

    /**
     * Parent(r) =⌊(r−1)/2⌋ if r≠0.
     *
     * @param index
     * @return
     */
    public int parentIndex(int index) {
        return -1;
    }

    /**
     * Left child(r) =2r+1 if 2r+1<n.
     *
     * @param index
     * @return
     */
    public int leftChild(int index) {
        return -1;
    }

    /**
     * Right child(r) =2r+2 if 2r+2<n.
     *
     * @param index
     * @return
     */
    public int rightChild(int index) {
        return -1;
    }

    /**
     * Left sibling(r) =r−1 if r is even and r≠0.
     *
     * @param index
     * @return
     */
    public int leftSibling(int index) {
        return -1;
    }

    /**
     * Right sibling(r) =r+1 if r is odd and r+1<n.
     *
     * @param index
     * @return
     */
    public int rightSibling(int index) {
        return -1;
    }
}
