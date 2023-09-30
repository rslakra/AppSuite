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
 * @created 9/19/23 4:35 PM
 */
public class LC692TopKFrequentWordsTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> topKFrequentData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: words = ["i","love","leetcode","i","love","coding"], k = 2
//        Output: ["i","love"]
//        Explanation: "i" and "love" are the two most frequent words.
//        Note that "i" comes before "love" due to a lower alphabetical order.
        inputs.add(
            new Object[]{Arrays.asList("i", "love", "leetcode", "i", "love", "coding"), 2, Arrays.asList("i", "love")});

//        Example 2:
//        Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
//        Output: ["the","is","sunny","day"]
//        Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
        inputs.add(new Object[]{Arrays.asList("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"), 4,
                                Arrays.asList("the", "is", "sunny", "day")});

        return inputs.iterator();
    }

    @Test(dataProvider = "topKFrequentData")
    public void testTopKFrequent(List<String> input, int k, List<String> result) {
        LC692TopKFrequentWords instance = new LC692TopKFrequentWords();
        assertEquals(result, instance.topKFrequent(input.toArray(new String[0]), k));
    }
}
