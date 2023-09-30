package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rslakra.appsuite.questions.interviewbit.MinStack;
import org.junit.jupiter.api.Test;

/**
 * @author Rohtash Lakra
 * @created 9/14/23 12:23 PM
 */
public class LC155MinStackTest {

    @Test
    public void testMinStack() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        assertEquals(-3, minStack.getMin());
        minStack.pop();
        minStack.top();    // return 0
        assertEquals(0, minStack.top());
        minStack.getMin(); // return -2
        assertEquals(-2, minStack.getMin());
    }

}
