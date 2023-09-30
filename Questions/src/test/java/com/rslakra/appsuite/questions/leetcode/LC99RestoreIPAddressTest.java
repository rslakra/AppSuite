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
 * @created 9/18/23 1:01 PM
 */
public class LC99RestoreIPAddressTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> majorityElementData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: s = "25525511135"
//        Output: ["255.255.11.135","255.255.111.35"]
        inputs.add(new Object[]{"25525511135", Arrays.asList("255.255.11.135", "255.255.111.35")});

//        Example 2:
//        Input: s = "0000"
//        Output: ["0.0.0.0"]
        inputs.add(new Object[]{"0000", Arrays.asList("0.0.0.0")});

//        Example 3:
//        Input: s = "101023"
//        Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
        inputs.add(
            new Object[]{"101023", Arrays.asList("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3")});

        inputs.add(new Object[]{"", new ArrayList<>()});
        return inputs.iterator();
    }

    @Test(dataProvider = "majorityElementData")
    public void testMajorityElement(String input, List<String> result) {
        LC99RestoreIPAddress instance = new LC99RestoreIPAddress();
        assertEquals(result, instance.restoreIpAddresses(input));
    }
}
