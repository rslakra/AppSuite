package com.rslakra.appsuite.questions.interviewbit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/14/23 12:13 PM
 */
public class MinStackTest {

    @DataProvider
    public Iterator<Object[]> uniqueEmailsData() {
        List<Object[]> inputs = new ArrayList<>();

//        Input: emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
//        Output: 2
//        Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails.
        inputs.add(new Object[]{new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
                                             "testemail+david@lee.tcode.com"}, 2});

//        Example 2:
//        Input: emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
//        Output: 3
        inputs.add(new Object[]{new String[]{"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"}, 3});

        return inputs.iterator();
    }

    //    @Test(dataProvider = "uniqueEmailsData")
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
