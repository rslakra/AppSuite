package com.rslakra.appsuite.questions.leetcode;

import com.rslakra.appsuite.algos.tree.TreeNode;

/**
 * Definition for a binary tree node.
 *
 * <pre>
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 9/29/23 8:11 AM
 */
public class LC226InvertBinaryTree {

    /**
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.hasLeft() && root.hasRight()) {
            TreeNode tempNode = root.getLeft();
            root.setLeft(root.getRight());
            root.setRight(tempNode);
        } else if (root.hasLeft()) {
            root.setRight(root.getLeft());
        } else if (root.hasRight()) {
            root.setLeft(root.getLeft());
        }

        return root;
    }

}
