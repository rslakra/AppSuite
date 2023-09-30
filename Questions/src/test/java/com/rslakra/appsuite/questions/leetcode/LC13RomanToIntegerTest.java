package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/17/23 10:53 AM
 */
public class LC13RomanToIntegerTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> romanToIntegerData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: s = "III"
//        Output: 3
//        Explanation: III = 3.
        inputs.add(new Object[]{"III", 3});

//        Example 2:
//        Input: s = "LVIII"
//        Output: 58
//        Explanation: L = 50, V= 5, III = 3.
        inputs.add(new Object[]{"LVIII", 58});

//        Example 3:
//        Input: s = "MCMXCIV"
//        Output: 1994
//        Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
        inputs.add(new Object[]{"MCMXCIV", 1994});

//        Example 4:
//        Input: s = "IV"
//        Output: 4
//        Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
        inputs.add(new Object[]{"IV", 4});

//        Example 5:
//        Input: s = "MCMLXXIV"
//        Output: 1974
//        Explanation: M = 1000, CM = 900, LXX = 70 and IV = 4.
        inputs.add(new Object[]{"MCMLXXIV", 1974});

        inputs.add(new Object[]{"", 0});
        return inputs.iterator();
    }

    @Test(dataProvider = "romanToIntegerData")
    public void testRomanToInteger(String input, int result) {
        LC13RomanToInteger instance = new LC13RomanToInteger();
        assertEquals(result, instance.romanToIntApproach1(input));
        assertEquals(result, instance.romanToIntApproach2(input));
        assertEquals(result, instance.romanToIntApproach3(input));
    }
}
