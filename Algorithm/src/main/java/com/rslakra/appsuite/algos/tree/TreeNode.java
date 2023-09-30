package com.rslakra.appsuite.algos.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 9/18/23 7:12 PM
 */
@Getter
@Setter
public class TreeNode<T> {

    private T value;
    private TreeNode left;
    private TreeNode right;

    /**
     * @param value
     */
    public TreeNode(T value) {
        this(null, value, null);
    }

    /**
     * @param left
     * @param value
     * @param right
     */
    public TreeNode(TreeNode left, T value, TreeNode right) {
        this.left = left;
        this.value = value;
        this.right = right;
    }

    /**
     * Returns true if the left node is not null otherwise false.
     *
     * @return
     */
    public boolean hasLeft() {
        return (getLeft() != null);
    }

    /**
     * Returns true if the right node is not null otherwise false.
     *
     * @return
     */
    public boolean hasRight() {
        return (getRight() != null);
    }

    /**
     * @return
     */
    public boolean isLeafNode() {
        return (!hasLeft() && !hasRight());
    }

    /**
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof TreeNode)) {
            return false;
        }

        TreeNode treeNode = (TreeNode) object;
        if (value != treeNode.value) {
            return false;
        }

        if (getLeft() != null ? !getLeft().equals(treeNode.getLeft()) : treeNode.getLeft() != null) {
            return false;
        }

        return Objects.equals(getRight(), treeNode.getRight());
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getLeft(), getRight());
    }

    @Override
    public String toString() {
        return "TreeNode{" + "value=" + value + ", left=" + left + ", right=" + right + '}';
    }
}
