package com.rslakra.appsuite.questions.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any
 * order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <pre>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * </pre>
 * Example 2:
 * <pre>
 * Input: nums = [1], k = 1
 * Output: [1]
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 9/19/23 3:43 PM
 */
public class LC347TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        SortedSet<Map.Entry<Integer, Integer>> sortedSet = new TreeSet<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                // values might be same and can override the key
                int result = o2.getValue() - o1.getValue();
                if (result == 0) {
                    result = o1.getKey() - o2.getKey();
                }
                return result;
            }
        });
        sortedSet.addAll(map.entrySet());

        int index = 0;
        int[] topKElements = new int[k];
        for (Iterator<Map.Entry<Integer, Integer>> itr = sortedSet.iterator(); itr.hasNext() && k > 0; k--) {
            topKElements[index++] = itr.next().getKey();
        }
        return topKElements;
    }
}
