package com.rslakra.core.algos.tree;

import com.rslakra.core.algos.tree.RedBlackTree;

/**
 * @author Rohtash Lakra
 * @created 8/17/20 4:46 PM
 */
public class RedBlackTreeTest {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        //add nodes
        for (int i = 65; i < 97; i++) {
            tree.addNode(Character.valueOf((char) i), Character.valueOf((char) i));
        }

        Character value = (Character) tree.get('R');
        System.out.println(value);

        value = (Character) tree.get('E');
        System.out.println(value);

    }
}
