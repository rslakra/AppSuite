package com.rslakra.appsuite.questions.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 7/19/21 12:33 PM
 */
public class LC7ReverseInteger {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(LC7ReverseInteger.class);

    /**
     * Given a signed 32-bit integer x. Return x with its digits reversed. If reversing x causes the value to go outside
     * the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
     * <p>
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     * <p>
     * <p>
     * <p>
     * Example 1: Input: x = 456 Output: 654
     * <p>
     * Example 2: Input: x = -123 Output: -321
     * <p>
     * Example 3: Input: x = 120 Output: 21
     * <p>
     * Example 4: Input: x = 0 Output: 0
     * <p>
     * <p>
     * Constraints:
     * <p>
     * -2^31 <= x <= 2^31 - 1
     *
     * @param x
     * @return
     */
    public int reverseWithString(int x) {
        String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            int revNum = Integer.parseInt(reversed);
            return (x < 0) ? revNum * -1 : revNum;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Complexity Analysis
     * <p>
     * Time Complexity: O(log(x)) There are roughly log10(x) digits in x.
     * <p>
     * Space Complexity: O(1) O(1).
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int rem = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && rem > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && rem < -8)) {
                return 0;
            }
            rev = rev * 10 + rem;
        }
        return rev;
    }

    public static int reverseNumbers(int num) {
        int result = 0;
        while (num != 0) {
            int rem = num % 10;
            int newResult = (result * 10) + (rem); //321
            if ((newResult - rem) / 10 != result) {
                return 0;
            }
            result = newResult;
            num = num / 10;

        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LC7ReverseInteger reverseInteger = new LC7ReverseInteger();
        int num = 12356789;
        LOGGER.debug("reverseWithString:{}", reverseInteger.reverseWithString(num));
        LOGGER.debug("reverse:{}", reverseInteger.reverse(num));
    }


}
