package com.rslakra.appsuite.questions.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Given an array of strings words and an integer k, return the k most frequent strings.
 * <p>
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their
 * lexicographical order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <pre>
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 * </pre>
 * Example 2:
 * <pre>
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 9/19/23 4:32 PM
 */
public class LC692TopKFrequentWords {

    /**
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        SortedSet<Map.Entry<String, Integer>> sortedSet = new TreeSet<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // values might be same and can override the key
                int result = o2.getValue() - o1.getValue();
                if (result == 0) {
                    result = o1.getKey().compareTo(o2.getKey());
                }
                return result;
            }
        });
        sortedSet.addAll(map.entrySet());

        List<String> topKWords = new ArrayList<>();
        for (Iterator<Map.Entry<String, Integer>> itr = sortedSet.iterator(); itr.hasNext() && k > 0; k--) {
            topKWords.add(itr.next().getKey());
        }

        return topKWords;
    }
}
