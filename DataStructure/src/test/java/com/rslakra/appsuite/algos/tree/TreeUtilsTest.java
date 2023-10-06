package com.rslakra.appsuite.algos.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/28/23 5:56 PM
 */
public class TreeUtilsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(TreeUtilsTest.class);

    /**
     * Print Binary Tree
     */
    @Test
    public void testBuildBinaryTreeWithList() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
    }

    /**
     * Print Binary Tree
     */
    @Test
    public void testBuildBinaryTreeWithString() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree("1, 2, 3, 4, 5, 6, 7");
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
    }

    /**
     * Print Binary Tree
     */
    @Test
    public void testPrintBinaryTree() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
        TreeUtils.printBinaryTree(root);
    }

    /**
     * Print Pretty Tree
     */
    @Test
    public void testPrintPrettyTree() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
        TreeUtils.printPrettyTree(root);
    }

    /**
     *
     */
    @Test
    public void testGetMaxLevel() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
        int maxLevel = TreeUtils.getMaxLevel(root);
        assertEquals(3, maxLevel);
    }

    /**
     *
     */
    @Test
    public void testGetLevelOrders() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        LOGGER.debug("root:{}", root);
        assertNotNull(root);
        List<List<Integer>> levelOrders = TreeUtils.getLevelOrders(root);
        LOGGER.debug("levelOrders:{}", levelOrders);
        assertNotNull(levelOrders);
        assertEquals(3, levelOrders.size());
        assertEquals(1, levelOrders.get(0).size());
        assertEquals(2, levelOrders.get(1).size());
        assertEquals(4, levelOrders.get(2).size());
    }


    /**
     *
     */
    @Test
    public void testInOrder() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
        TreeUtils.printPrettyTree(root);
        List<Integer> inOrder = TreeUtils.inOrder(root);
        assertNotNull(inOrder);
        LOGGER.debug("inOrder:{}", inOrder);
    }

    /**
     *
     */
    @Test
    public void testPreOrder() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
        TreeUtils.printPrettyTree(root);
        List<Integer> preOrder = TreeUtils.preOrder(root);
        assertNotNull(preOrder);
        LOGGER.debug("preOrder:{}", preOrder);
    }

    /**
     *
     */
    @Test
    public void testPostOrder() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
        TreeUtils.printPrettyTree(root);
        List<Integer> postOrder = TreeUtils.postOrder(root);
        assertNotNull(postOrder);
        LOGGER.debug("postOrder:{}", postOrder);
    }

    /**
     *
     */
    @Test
    public void testInOrderTraversal() {
        TreeNode<Integer> root = TreeUtils.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertNotNull(root);
        LOGGER.debug("root:{}", root);
        String inOrderString = TreeUtils.inOrderTraversal(root);
        assertNotNull(inOrderString);
        LOGGER.debug("inOrderString:{}", inOrderString);
    }

}
