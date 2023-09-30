package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/11/23 9:34 AM
 */
public class LC929UniqueEmailAddressesTest {


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

    @Test(dataProvider = "uniqueEmailsData")
    public void testNumUniqueEmails(String[] emails, int result) {
        LC929UniqueEmailAddresses instance = new LC929UniqueEmailAddresses();
        assertEquals(result, instance.numUniqueEmails(emails));
    }


}
