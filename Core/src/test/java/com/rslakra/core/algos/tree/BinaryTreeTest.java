//package com.rslakra.core.algos.tree;
//
//import com.rslakra.core.algos.tree.BinaryTree;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
///**
// * @author Rohtash Lakra (rlakra)
// * @created 5/13/22 6:47 PM
// */
//public class BinaryTreeTest {
//
//    @Test
//    public void testInsertTests() {
//        BinaryTree<Integer> tree = new BinaryTree<>();
//        tree.addNode(3);
//        tree.addNode(1);
//        tree.addNode(4);
//        tree.addNode(2);
//
//        Integer[] expected = new Integer[]{3, 1, 2, 4};
//        assertTreePreOrder(tree, expected);
//    }
//
//    @Test
//    public void testRemoveLeaf() {
//        //          3
//        //      1      4
//        //        2
//        BinaryTree<Integer> tree = new BinaryTree<Integer>();
//        tree.addNode(3);
//        tree.addNode(1);
//        tree.addNode(4);
//        tree.addNode(2);
//
//        tree.remove(2);
//        assertTreePreOrder(tree, new Integer[]{3, 1, 4});
//
//        tree.remove(4);
//        assertTreePreOrder(tree, new Integer[]{3, 1});
//
//        tree.remove(1);
//        assertTreePreOrder(tree, new Integer[]{3});
//    }
//
//    @Test
//    public void testRemoveRightChild() {
//        //          3
//        //      1      4
//        //        2
//        BinaryTree<Integer> tree = new BinaryTree<Integer>();
//        tree.addNode(3);
//        tree.addNode(1);
//        tree.addNode(4);
//        tree.addNode(2);
//
//        tree.remove(1);
//        assertTreePreOrder(tree, new Integer[]{3, 2, 4});
//    }
//
//    @Test
//    public void testRemoveLeftChild() {
//        //          3
//        //      2      4
//        //     1
//        BinaryTree<Integer> tree = new BinaryTree<Integer>();
//        tree.addNode(3);
//        tree.addNode(2);
//        tree.addNode(4);
//        tree.addNode(1);
//
//        tree.remove(2);
//        assertTreePreOrder(tree, new Integer[]{3, 1, 4});
//    }
//
//    @Test
//    public void testRemoveTwoChild() {
//        //          10
//        //      5       11
//        //     4  9
//        //    3  7
//        //        8
//        BinaryTree<Integer> tree = new BinaryTree<Integer>();
//        tree.addNode(10);
//        tree.addNode(5);
//        tree.addNode(4);
//        tree.addNode(9);
//        tree.addNode(7);
//        tree.addNode(8);
//        tree.addNode(3);
//        tree.addNode(11);
//
//        //          10
//        //      7       11
//        //     4  9
//        //    3  8
//        //
//        tree.remove(5);
//        assertTreePreOrder(tree, new Integer[]{10, 7, 4, 3, 9, 8, 11});
//    }
//
//    @Test
//    public void testRemoveRootTwoChild() {
//        //          10
//        //      5        20
//        //    4   6    15
//        //           12
//        //             13
//        //               14
//        BinaryTree<Integer> tree = new BinaryTree<Integer>();
//        tree.addNode(10);
//        tree.addNode(5);
//        tree.addNode(4);
//        tree.addNode(6);
//
//        tree.addNode(20);
//        tree.addNode(15);
//        tree.addNode(12);
//        tree.addNode(13);
//        tree.addNode(14);
//
//        //          12
//        //      5        20
//        //    4   6    15
//        //           13
//        //             14
//        //
//        tree.remove(10);
//        assertTreePreOrder(tree, new Integer[]{12, 5, 4, 6, 20, 15, 13, 14});
//
//        //          13
//        //      5        20
//        //    4   6    15
//        //           14
//        //
//        tree.remove(12);
//        assertTreePreOrder(tree, new Integer[]{13, 5, 4, 6, 20, 15, 14});
//    }
//
//    @Test
//    public void testRemoveRootOneChildLeft() {
//        //          3
//        //      2
//        //     1
//        BinaryTree<Integer> tree = new BinaryTree<Integer>();
//        tree.addNode(3);
//        tree.addNode(2);
//        tree.addNode(1);
//
//        tree.remove(3);
//        assertTreePreOrder(tree, new Integer[]{2, 1});
//    }
//
//    @Test
//    public void testRemoveRootOneChildRight() {
//        //          3
//        //            4
//        //              5
//        BinaryTree<Integer> tree = new BinaryTree<Integer>();
//        tree.addNode(3);
//        tree.addNode(4);
//        tree.addNode(5);
//
//        tree.remove(3);
//        assertTreePreOrder(tree, new Integer[]{4, 5});
//    }
//
//    @Test
//    public void testRemoveRootOnly() {
//        BinaryTree<Integer> tree = new BinaryTree<Integer>();
//        tree.addNode(3);
//
//        tree.remove(3);
//        assertTreePreOrder(tree, new Integer[]{});
//    }
//
//    @Test
//    public void testPreOrderCopyTree() {
//        //          10
//        //      5        20
//        //    4   6    15
//        //           12
//        //             13
//        //               14
//        BinaryTree<Integer> expected = new BinaryTree<Integer>();
//        expected.addNode(10);
//        expected.addNode(5);
//        expected.addNode(4);
//        expected.addNode(6);
//
//        expected.addNode(20);
//        expected.addNode(15);
//        expected.addNode(12);
//        expected.addNode(13);
//        expected.addNode(14);
//
//        BinaryTree<Integer> actual = new BinaryTree<Integer>();
////        expected.preOrderTraversal((value) => actual.addNode(value));
//
//        assertTreesSame(actual, expected);
//    }
//
//    private Integer[] treeToPreorderArray(BinaryTree<Integer> tree) {
////        DoublyLinkedList<Integer> actualList = new DoublyLinkedList<Integer>();
////        tree.preOrderTraversal((value) = > actualList.AddTail(value));
////        return actualList.to();
//        return null;
//    }
//
//    private void assertTreesSame(BinaryTree<Integer> actual, BinaryTree<Integer> expected) {
//        assertArraysSame(treeToPreorderArray(actual), treeToPreorderArray(expected));
//    }
//
//    private void assertArraysSame(Integer[] actual, Integer[] expected) {
//        Assert.assertNotNull(actual);
//        Assert.assertNotNull(expected);
//        Assert.assertEquals(expected.length, actual.length);
//        for (Integer i = 0; i < expected.length; i++) {
//            Assert.assertEquals(expected[i], actual[i], String.format("Incorrect value at index {0}", i));
//        }
//    }
//
//    private void assertTreePreOrder(BinaryTree<Integer> tree, Integer[] expected) {
//        Assert.assertEquals(tree.getSize(), expected.length, "Tree count was incorrect");
//        Integer[] actual = treeToPreorderArray(tree);
//        assertArraysSame(actual, expected);
//    }
//
//}
