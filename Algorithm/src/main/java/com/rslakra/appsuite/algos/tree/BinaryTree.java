package com.rslakra.appsuite.algos.tree;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 5/13/22 5:18 PM
 */
public class BinaryTree<V extends Comparable> extends AbstractTree implements Iterable, Comparable {

    private Node<?, V> root;
    private int size;

    public BinaryTree() {
    }

    /**
     * @return
     */
    public Node<?, V> getRoot() {
        return root;
    }

    /**
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds the <code>value</code> to the binary tree.
     *
     * @param value
     * @return
     */
    protected void addNode(final Node<?, V> node, final V value) {
        // Case 1: value is less than the current node value
        if (value.compareTo(node.getValue()) < 0) {
            // if node has left child, add it to the left node
            if (node.hasLeft()) {
                addNode(node.getLeft(), value);
            } else {
                // there is no left child, make it the new left node
                node.setLeft(new Node(null, value));
                node.getLeft().setParent(node);
            }
        } else { // Case 2: Value is equal to or greater than the current value
            // if node has right child, add it to the right node
            if (node.hasRight()) {
                addNode(node.getRight(), value);
            } else {
                // there is no right child, make it the new right node
                node.setRight(new Node(null, value));
                node.getRight().setParent(node);
            }
        }
    }

    /**
     * Adds the <code>value</code> to the binary tree.
     *
     * @param value
     * @return
     */
    public void addNode(final V value) {
        // Case 1: The tree is empty - allocate the head
        if (Objects.isNull(getRoot())) {
            root = new Node<>(null, value);
        } else {
            // Case 2: The tree is not empty so find the right location to insert
            addNode(getRoot(), value);
        }

        //increment the node count
        size++;
    }

    /**
     * Finds and returns the first node containing the specified value. If the value is not found, returns null.  Also
     * returns the parent of the found node (or null) which is used in Remove.
     *
     * @param value
     * @return
     */
    protected Node findNode(final V value) {
        // find node's in the tree with the provided <code>value</code>
        Node current = getRoot();
        while (current != null) {
            int result = current.getValue().compareTo(value);
            if (result > 0) {
                // if the value is less than current, find in left.
                current = current.getLeft();
            } else if (result < 0) {
                // if the value is more than current, find in right.
                current = current.getRight();
            } else { // find a match
                break;
            }
        }

        return current;
    }

    /**
     * Determines if the specified value exists in the binary tree.
     *
     * @param value
     * @return
     */
    public boolean contains(final V value) {
        return (Objects.nonNull(getRoot()) && Objects.nonNull(findNode(value)));
    }

    /**
     * Removes the first occurrence of the provided value from the tree.
     *
     * @param value
     */
    public boolean remove(V value) {
        Node current = findNode(value);
        if (Objects.isNull(current)) {
            return false;
        }

        // decrease the node count
        size--;

        // Get current's parent node
        final Node parent = current.getParent();
        // Case 1: If current node has no right child, then current's left replaces current node.
        if (!current.hasRight()) {
            if (parent == null) {
                root = current.getLeft();
            } else {
                if (parent.isGreaterThan(current)) {
                    /**
                     * if parent value is greater than current value make the current left child a left child of
                     * parent
                     */
                    parent.setLeft(current.getLeft());
                } else if (parent.isLessThan(current)) {
                    /**
                     * if parent value is less than current value make the current left child a right child of parent
                     */
                    parent.setRight(current.getLeft());
                }
            }
        } else if (current.hasRight() && !current.getRight().hasLeft()) {
            // Case 2: If current's right child has no left child, then current's right child replaces current
            current.getRight().setLeft(current.getLeft());

            if (parent == null) {
                root = current.getRight();
            } else {
                int result = parent.compareTo(current);
                if (result > 0) {
                    // if parent value is greater than current value
                    // make the current right child a left child of parent
                    parent.setLeft(current.getRight());
                } else if (result < 0) {
                    // if parent value is less than current value
                    // make the current right child a right child of parent
                    parent.setRight(current.getRight());

                }
            }
        } else { // Case 3: If current's right child has a left child, replace current with current's right child's left-most child

            // find the right's left-most child
            Node<?, V> leftMostParent = current.getRight();
            Node<?, V> leftMost = current.getRight().getLeft();

            while (leftMost.hasLeft()) {
                leftMostParent = leftMost;
                leftMost = leftMost.getLeft();
            }

            // the parent's left subtree becomes the leftmost's right subtree
            leftMostParent.setLeft(leftMost.getRight());

            // assign leftmost's left and right to current's left and right children
            leftMost.setLeft(current.getLeft());
            leftMost.setRight(current.getRight());

            if (parent == null) {
                root = leftMost;
            } else {
                int result = parent.compareTo(current);
                if (result > 0) {
                    // if parent value is greater than current value
                    // make leftmost the parent's left child
                    parent.setLeft(leftMost);
                } else if (result < 0) {
                    // if parent value is less than current value
                    // make leftmost the parent's right child
                    parent.setRight(leftMost);
                }
            }
        }

        return true;
    }

    /**
     * Removes all items from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))} for all {@code x} and {@code y}.  (This implies that
     * {@code x.compareTo(y)} must throw an exception iff {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any class that implements the
     * {@code Comparable} interface and violates this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(Object other) {
        return 0;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return null;
    }
}
