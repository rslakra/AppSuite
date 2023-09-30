package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/25/23 12:12 PM
 */
public class LC721AccountsMergeTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> accountsMergeData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//        Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//        Explanation:
//        The first and second John's are the same person as they have the common email "johnsmith@mail.com".
//        The third John and Mary are different people as none of their email addresses are used by other accounts.
//        We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
//['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
        List<List<String>> inputList = new ArrayList<>();
        inputList.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        inputList.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        inputList.add(Arrays.asList("Mary", "mary@mail.com"));
        inputList.add(Arrays.asList("John", "johnnybravo@mail.com"));

        List<List<String>> resultList = new ArrayList<>();
        resultList.add(Arrays.asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"));
        resultList.add(Arrays.asList("Mary", "mary@mail.com"));
        resultList.add(Arrays.asList("John", "johnnybravo@mail.com"));
        inputs.add(new Object[]{inputList, resultList});

//        Example 2:
//        Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
//        Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]

        inputList = new ArrayList<>();
        inputList.add(Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"));
        inputList.add(Arrays.asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"));
        inputList.add(Arrays.asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"));
        inputList.add(Arrays.asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"));
        inputList.add(Arrays.asList("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co"));

        resultList = new ArrayList<>();
        resultList.add(Arrays.asList("Ethan", "Ethan0@m.co", "Ethan4@m.co", "Ethan5@m.co"));
        resultList.add(Arrays.asList("Gabe", "Gabe0@m.co", "Gabe1@m.co", "Gabe3@m.co"));
        resultList.add(Arrays.asList("Hanzo", "Hanzo0@m.co", "Hanzo1@m.co", "Hanzo3@m.co"));
        resultList.add(Arrays.asList("Kevin", "Kevin0@m.co", "Kevin3@m.co", "Kevin5@m.co"));
        resultList.add(Arrays.asList("Fern", "Fern0@m.co", "Fern1@m.co", "Fern5@m.co"));
        inputs.add(new Object[]{inputList, resultList});

        return inputs.iterator();
    }

    @Test(dataProvider = "accountsMergeData")
    public void testAccountsMerge(List<List<String>> input, List<List<String>> result) {
        LC721AccountsMerge instance = new LC721AccountsMerge();
        assertEquals(result, instance.accountsMerge(input));
    }
}
