package com.rslakra.core.algos.tree;

import com.rslakra.core.algos.tree.RedBlackTree;
import com.rslakra.core.enums.ENumsTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 8/17/20 4:46 PM
 */
public class RedBlackTreeTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(RedBlackTreeTest.class);

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        //add nodes
        for (int i = 65; i < 97; i++) {
            tree.addNode(Character.valueOf((char) i), Character.valueOf((char) i));
        }

        Character value = (Character) tree.get('R');
        LOGGER.debug("{}",value);

        value = (Character) tree.get('E');
        LOGGER.debug("{}",value);

    }
}
