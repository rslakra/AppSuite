package com.rslakra.appsuite.algos.tree;

import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 8/17/20 3:34 PM
 */
public class RedBlackTree<K extends Comparable, V extends Comparable> {

    public class Node {

        public static final boolean RED = true;
        public static final boolean BLACK = false;

        private K key;
        private V value;
        private Node left, right;
        private boolean color;

        /**
         * @param key
         * @param value
         * @param color
         */
        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root;

    public RedBlackTree() {
    }

    /**
     * Return ture if the node is  not null and color = RED otherwise false.
     *
     * @param node
     * @return
     */
    public boolean isRed(Node node) {
        return (node == null ? false : node.color == Node.RED);
    }

    /**
     * Returns the value for the key.
     *
     * @param key
     * @return
     */
    public V get(K key) {
        Node temp = root;
        while (temp != null) {
            int result = key.compareTo(temp.key);
            if (result < 0) {
                temp = temp.left;
            } else if (result > 0) {
                temp = temp.right;
            } else {
                return temp.value;
            }
        }

        return null;
    }


    /**
     * Adds the new node and balance the tree.
     * <p>
     * Balance Criteria:
     * <p>
     * 1. Right child red, left child black - rotate left.
     * <p>
     * 2. Left child, left-left grandchild red - rotate right. 3.
     * <p>
     * 3. Both children red - flip colors.
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node put(Node node, K key, V value) {
        // add new node here.
        if (node == null) {
            return new Node(key, value, Node.RED);
        }

        //Replace current node or add new node as child.
        int result = key.compareTo(node.key);
        if (result == 0) {
            node.value = value;
        } else if (result < 0) {
            node.left = put(node.left, key, value);
        } else if (result > 0) {
            node.right = put(node.right, key, value);
        }

        //balance red-black tree.
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        } else if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        } else if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /**
     * Adds the new node at the right place.
     *
     * @param key
     * @param value
     */
    public void addNode(K key, V value) {
        root = put(root, key, value);
    }

    /**
     * Rotates the left.
     * <p>
     * Right child red, left child black - rotate left.
     *
     * @param node
     * @return
     */
    public Node rotateLeft(Node node) {
        Objects.requireNonNull(node, "Node must not be null!");
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Node.RED;
        return temp;
    }

    /**
     * Right rotation.
     * <p>
     * Left child, left-left grandchild red - rotate right.
     *
     * @param node
     * @return
     */
    public Node rotateRight(Node node) {
        Objects.requireNonNull(node, "Node must not be null!");
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Node.RED;

        return temp;
    }

    /**
     * Flips the colors.
     * <p>
     * Both children red - flip colors.
     *
     * @param node
     */
    public void flipColors(Node node) {
        Objects.requireNonNull(node, "Node must not be null!");
        assert !isRed(node);
        assert isRed(node.left);
        assert isRed(node.right);
        node.color = Node.RED;
        node.left.color = Node.BLACK;
        node.right.color = Node.BLACK;
    }

}
