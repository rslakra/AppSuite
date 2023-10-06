package com.rslakra.appsuite.algos.tree;

import com.rslakra.appsuite.algos.AlgoUtils;
import com.rslakra.appsuite.core.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * The formulae for calculating the array indices of the various relatives of a node are as follows.
 * <p>
 * The total number of nodes in the tree is n.
 * <p>
 * The index of the node in question is r, which must fall in the range 0 to n−1.
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
     * Parent(r) = ⌊(r−1)/2⌋ if r≠0.
     *
     * @param index
     * @return
     */
    public int parentIndex(int index) {
        return -1;
    }

    /**
     * Left child(r) = 2r+1 if 2r+1<n.
     *
     * @param index
     * @return
     */
    public int leftChild(int index) {
        return -1;
    }

    /**
     * Right child(r) = 2r+2 if 2r+2<n.
     *
     * @param index
     * @return
     */
    public int rightChild(int index) {
        return -1;
    }

    /**
     * Left sibling(r) = r−1 if r is even and r≠0.
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

    /**
     * This method is to construct a normal binary tree.
     * <p>
     * The input reads like this for [5, 3, 6, 2, 4, null, null, 1],
     *
     * <pre>
     *             5
     *           /   \
     *          3     6
     *         / \   / \
     *        2   4 #   #
     *       /
     *      1
     * </pre>
     *
     * @param treeValues
     * @return
     */
    public static TreeNode buildBinaryTree(List<Integer> treeValues) {
        TreeNode root = new TreeNode(treeValues.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < treeValues.size(); i++) {
            TreeNode current = queue.poll();
            if (treeValues.get(i) != null) {
                current.setLeft(new TreeNode(treeValues.get(i)));
                queue.offer(current.getLeft());
            }
            if (++i < treeValues.size() && treeValues.get(i) != null) {
                current.setRight(new TreeNode(treeValues.get(i)));
                queue.offer(current.getRight());
            }
        }

        return root;
    }


    /**
     * Builds the binary tree with the input string.
     *
     * @param input
     * @return
     */
    public static TreeNode buildBinaryTree(String input) {
        if (BeanUtils.isEmpty(input)) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item.trim()));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.setLeft(new TreeNode(leftNumber));
                queue.add(node.getLeft());
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.setRight(new TreeNode(rightNumber));
                queue.add(node.getRight());
            }
        }
        return root;
    }

    /**
     * @param root
     */
    public static void printBinaryTree(TreeNode root) {
        AlgoUtils.println("\nPrinting out the binary tree in a very visual manner as below:\n");

        // imitating from BTreePrinter class
        int maxLevel = TreeUtils.getMaxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    /**
     * Returns the max level of the node.
     *
     * @param root
     * @return
     */
    public static int getMaxLevel(TreeNode root) {
        return (Objects.isNull(root) ? 0 : (Math.max(getMaxLevel(root.getLeft()), getMaxLevel(root.getRight())) + 1));
    }

    /**
     * <pre>
     *             1
     *           /   \
     *          /     \
     *         2       3
     *        / \     / \
     *       4  5    6   7
     * </pre>
     *
     * @param list
     * @param level
     * @param maxLevel
     */
    private static void printNodeInternal(List<TreeNode> list, int level, int maxLevel) {
        if (list.isEmpty() || AlgoUtils.isAllNull(list)) {
            return;
        }

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int gapBetweenNodes = (int) Math.pow(2, (floor + 1)) - 1;

        AlgoUtils.printWhiteSpaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : list) {
            if (node != null) {
                System.out.print(node.getValue());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            AlgoUtils.printWhiteSpaces(gapBetweenNodes);
        }
        System.out.println("");

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < list.size(); j++) {
                AlgoUtils.printWhiteSpaces(firstSpaces - i);
                if (list.get(j) == null) {
                    AlgoUtils.printWhiteSpaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (list.get(j).hasLeft()) {
                    System.out.print("/");
                } else {
                    AlgoUtils.printWhiteSpaces(1);
                }

                AlgoUtils.printWhiteSpaces(i + i - 1);

                if (list.get(j).hasRight()) {
                    System.out.print("\\");
                } else {
                    AlgoUtils.printWhiteSpaces(1);
                }

                AlgoUtils.printWhiteSpaces(edgeLines + edgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }


    /**
     * @param node
     * @param prefix
     * @param isLeft
     */
    public static void printPrettyTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.hasRight()) {
            printPrettyTree(node.getRight(), prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.getValue());

        if (node.hasLeft()) {
            printPrettyTree(node.getLeft(), prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    /**
     * @param node
     */
    public static void printPrettyTree(TreeNode node) {
        printPrettyTree(node, "", true);
    }

    /**
     * @param root
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> getLevelOrders(TreeNode<T> root) {
        List<List<T>> levelOrders = new ArrayList<>();
        if (Objects.nonNull(root)) {
            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<T> levelOrder = new ArrayList<>();
                while (size > 0) {
                    TreeNode<T> node = queue.remove();
                    // print current node
                    levelOrder.add(node.getValue());
                    // add left node if available
                    if (node.getLeft() != null) {
                        queue.add(node.getLeft());
                    }
                    // add right node if available
                    if (node.getRight() != null) {
                        queue.add(node.getRight());
                    }
                    size--;
                }
                levelOrders.add(levelOrder);
            }
        }

        return levelOrders;
    }

    /**
     * Returns the list of items using <code>in-order</code> traversal recursively.
     *
     * @param root
     */
    public static <T> List<T> inOrder(TreeNode root) {
        List<T> inOrder = new ArrayList<>();
        if (Objects.nonNull(root)) {
            inOrder.addAll(inOrder(root.getLeft()));
            inOrder.add((T) root.getValue());
            inOrder.addAll(inOrder(root.getRight()));
        }

        return inOrder;
    }

    /**
     * Returns the list of items using <code>pre-order</code> traversal recursively.
     *
     * @param root
     */
    public static <T> List<T> preOrder(TreeNode root) {
        List<T> inOrder = new ArrayList<>();
        if (Objects.nonNull(root)) {
            inOrder.add((T) root.getValue());
            inOrder.addAll(preOrder(root.getLeft()));
            inOrder.addAll(preOrder(root.getRight()));
        }

        return inOrder;
    }

    /**
     * Returns the list of items using <code>post-order</code> traversal recursively.
     *
     * @param root
     */
    public static <T> List<T> postOrder(TreeNode root) {
        List<T> inOrder = new ArrayList<>();
        if (Objects.nonNull(root)) {
            inOrder.addAll(postOrder(root.getLeft()));
            inOrder.addAll(postOrder(root.getRight()));
            inOrder.add((T) root.getValue());
        }

        return inOrder;
    }

    /**
     * Definition for a binary tree node.
     *
     * <pre>
     *      public class TreeNode {
     *         int value;
     *         TreeNode left;
     *         TreeNode right;
     *
     *         TreeNode(int x) {
     *             value = x;
     *         }
     *     }
     * </pre>
     *
     * @param root
     */
    public static <T> String inOrderTraversal(TreeNode<T> root) {
        final List<String> inOrder = new ArrayList<>();
        if (Objects.nonNull(root)) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                // add 'null' if node is null
                if (node == null) {
                    inOrder.add("null");
                    continue;
                }

                inOrder.add(node.getValue().toString());
                queue.add(node.getLeft());
                queue.add(node.getRight());
            }
        }

        return inOrder.toString();
    }

}
