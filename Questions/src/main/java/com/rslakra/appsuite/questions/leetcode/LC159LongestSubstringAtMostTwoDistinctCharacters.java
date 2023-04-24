package com.rslakra.appsuite.questions.leetcode;

import com.rslakra.appsuite.core.Payload;

/**
 * @author Rohtash Lakra
 * @created 3/29/23 6:07 PM
 */
public class LC159LongestSubstringAtMostTwoDistinctCharacters {

    /**
     * https://aaronice.gitbook.io/lintcode/two_pointers/longest-substring-with-at-most-two-distinct-characters
     */
    public int longestSubstringWithAtMostTwoDistinctCharacters(String str) {
        int longest = 0;
        if (str == null || str.isEmpty()) {
            return longest;
        }

        int jumpIndex = 0;
        Payload<Character, Integer> count = Payload.newBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            count.put(ch, count.getOrDefault(ch, 0) + 1);
            while (count.size() > 2) {
                char ch2 = str.charAt(jumpIndex);
                count.put(ch2, count.get(ch2) - 1);
                if (count.get(ch2) == 0) {
                    count.remove(ch2);
                }
                jumpIndex++;
            }
            longest = Math.max(longest, i - jumpIndex + 1);
        }

        return longest;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LC159LongestSubstringAtMostTwoDistinctCharacters
            longestSubstring =
            new LC159LongestSubstringAtMostTwoDistinctCharacters();

        Payload<String, Integer> payloads = Payload.newBuilder()
            .ofPair("eceba", 3)
            .ofPair("ccaabbb", 5);

        // iterate
        payloads.entrySet().forEach(entry -> System.out.println(
            entry.getValue() + ", result:" + longestSubstring.longestSubstringWithAtMostTwoDistinctCharacters(
                entry.getKey())));
    }

}
