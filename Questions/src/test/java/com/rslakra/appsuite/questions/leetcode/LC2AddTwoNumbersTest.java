package com.rslakra.appsuite.questions.leetcode;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 5/1/21 9:01 AM
 */
public class LC2AddTwoNumbersTest {

    @DataProvider
    public Iterator<Object[]> addTwoNumbersData() {
        List<Object[]> inputs = new ArrayList<>();
        inputs.add(new Object[]{
            LC2AddTwoNumbers.buildList(new int[]{2, 4, 3}),
            LC2AddTwoNumbers.buildList(new int[]{5, 6, 4}),
            LC2AddTwoNumbers.buildList(new int[]{7, 0, 8})
        });

        inputs.add(new Object[]{
            LC2AddTwoNumbers.buildList(new int[]{0}),
            LC2AddTwoNumbers.buildList(new int[]{0}),
            LC2AddTwoNumbers.buildList(new int[]{0})
        });

        inputs.add(new Object[]{
            LC2AddTwoNumbers.buildList(new int[]{9, 9, 9, 9, 9, 9, 9}),
            LC2AddTwoNumbers.buildList(new int[]{9, 9, 9, 9}),
            LC2AddTwoNumbers.buildList(new int[]{8, 9, 9, 9, 0, 0, 0, 1})
        });

        return inputs.iterator();
    }


    /**
     * @param expectedNode
     * @param resultNode
     */
    private void assertListNodeEquals(LC2AddTwoNumbers.ListNode expectedNode, LC2AddTwoNumbers.ListNode resultNode) {
        while (expectedNode != null && resultNode != null) {
            assertEquals(expectedNode.getValue(), resultNode.getValue());
            expectedNode = expectedNode.getNext();
            resultNode = resultNode.getNext();
        }
    }

    @Test(dataProvider = "addTwoNumbersData")
    public void testAddTwoNumbers(LC2AddTwoNumbers.ListNode left, LC2AddTwoNumbers.ListNode right,
                                  LC2AddTwoNumbers.ListNode expectedResult) {
        assertListNodeEquals(expectedResult, LC2AddTwoNumbers.addTwoNumbers(left, right));
    }

}
