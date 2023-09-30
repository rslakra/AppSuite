package com.rslakra.appsuite.questions.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 9/6/23 2:43 PM
 */
public class LC5LongestPalindromicSubstring {

    private static final Logger LOGGER = LoggerFactory.getLogger(LC5LongestPalindromicSubstring.class);

    /**
     * Time Complexity: O(N)
     * <p>
     * Space Complexity: O(1)
     *
     * @param str
     * @return
     */
//    private boolean isPalindrome(String str) {
//        for (int left = 0, right = str.length() - 1; left != right; left++, right--) {
//            if (str.charAt(left) != str.charAt(right)) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    /**
     * @param str
     * @param leftIndex
     * @param rightIndex
     * @return
     */
    private boolean isPalindrome(String str, int leftIndex, int rightIndex) {
        LOGGER.debug("+isPalindrome({}, {}, {})", str, leftIndex, rightIndex);
        for (; leftIndex < rightIndex; leftIndex++, rightIndex--) {
            if (str.charAt(leftIndex) != str.charAt(rightIndex)) {
                LOGGER.debug("-isPalindrome(), leftIndex:{}, rightIndex:{}, result:false", leftIndex, rightIndex);
                return false;
            }
        }

        LOGGER.debug("-isPalindrome(), result:true");
        return true;
    }


    /**
     * Approach 1: Check All Substrings
     *
     * @param str
     * @return
     */
    public String longestPalindromeApproach1(String str) {
        if (str.length() == 1) {
            LOGGER.debug("return str:{}", str);
            return str;
        }

        for (int right = str.length() - 1; right > 0; right--) { // right pointer of string
            LOGGER.debug("right:{}", right);
            for (int left = 0; left < right; left++) { //left pointer of string
                if (isPalindrome(str, left, right)) { // O(n) operation
                    // substring endIndex index is exclusive, so add 1 to the right index to get the substr
                    LOGGER.debug("Returning:{}", str.substring(left, right + 1));
                    return str.substring(left, right + 1);
                }
            }
        }

        LOGGER.debug("return ");
        return "";
    }

    /**
     * @param str
     * @param leftIndex
     * @param rightIndex
     * @return
     */
    private int expendPalindrome(String str, int leftIndex, int rightIndex) {
        LOGGER.debug("+expendPalindrome({}, {}, {})", str, leftIndex, rightIndex);
        while (leftIndex >= 0 && rightIndex < str.length() && str.charAt(leftIndex) == str.charAt(rightIndex)) {
            leftIndex--;
            rightIndex++;
        }

        LOGGER.debug("-expendPalindrome(), result:{}", (rightIndex - leftIndex - 1));
        return (rightIndex - leftIndex - 1);
    }

    /**
     * Time Complexity: O(N)
     * <p>
     * Space Complexity: O(1)
     *
     * @param str
     * @return
     */
    private boolean isPalindrome(String str) {
        return isPalindrome(str, 0, str.length() - 1);
    }

    //    Approach 3: Expand From Centers
    public String longestPalindromeApproach3(String str) {
        int[] ans = {0, 0};
        for (int i = 0; str != null && i < str.length(); i++) {
            LOGGER.debug("i:{}", i);
            int oddLength = expendPalindrome(str, i, i);
            LOGGER.debug("oddLength:{}", oddLength);
            if (oddLength > (ans[1] - ans[0] + 1)) {
                int distance = oddLength / 2;
                LOGGER.debug("distance:{}", distance);
                ans[0] = i - distance;
                ans[1] = i + distance;
                LOGGER.debug("ans:[{}][{}]", ans[0], ans[1]);
            }

            int evenLength = expendPalindrome(str, i, i + 1);
            LOGGER.debug("evenLength:{}", evenLength);
            if (evenLength > (ans[1] - ans[0] + 1)) {
                int distance = (evenLength / 2) - 1;
                LOGGER.debug("distance:{}", distance);
                ans[0] = i - distance;
                ans[1] = i + 1 + distance;
                LOGGER.debug("ans:[{}][{}]", ans[0], ans[1]);
            }
        }

        LOGGER.debug("ans:[{}][{}]", ans[0], ans[1] + 1);
        return str.substring(ans[0], ans[1] + 1);
    }
}
