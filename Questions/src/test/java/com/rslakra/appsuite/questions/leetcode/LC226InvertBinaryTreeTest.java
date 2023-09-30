package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rslakra.appsuite.algos.tree.TreeNode;
import com.rslakra.appsuite.algos.tree.TreeUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/29/23 8:11 AM
 */
public class LC226InvertBinaryTreeTest {


    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> invertTreeData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: root = [4,2,7,1,3,6,9]
//        Output: [4,7,2,9,6,3,1]
        inputs.add(new Object[]{
            TreeUtils.buildBinaryTree(Arrays.asList(4, 2, 7, 1, 3, 6, 9)),
            TreeUtils.buildBinaryTree(Arrays.asList(4, 7, 2, 9, 6, 3, 1))
        });

        return inputs.iterator();
    }

    /**
     * @param input
     * @param result
     */
    @Test(dataProvider = "invertTreeData")
    public void testInvertTree(TreeNode input, TreeNode result) {
        LC226InvertBinaryTree instance = new LC226InvertBinaryTree();
        assertEquals(result, instance.invertTree(input));
    }
}
