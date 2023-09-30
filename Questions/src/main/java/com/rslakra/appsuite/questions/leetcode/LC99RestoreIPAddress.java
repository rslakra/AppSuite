package com.rslakra.appsuite.questions.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255
 * (inclusive) and cannot have leading zeros.
 * <p>
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and
 * "192.168@1.1" are invalid IP addresses. Given a string s containing only digits, return all possible valid IP
 * addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You
 * may return the valid IP addresses in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <pre>
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * </pre>
 * Example 3:
 * <pre>
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 9/18/23 1:01 PM
 */
public class LC99RestoreIPAddress {

    private static final Logger LOGGER = LoggerFactory.getLogger(LC99RestoreIPAddress.class);

    public List<String> restoreIpAddresses(String s) {
        LOGGER.debug("+restoreIpAddresses({})", s);
        List<String> result = new ArrayList<>();
        backTrack(s, 0, new ArrayList<Integer>(), result);
        LOGGER.debug("+restoreIpAddresses(), result:{}", result);
        return result;
    }

    /**
     * @param ips
     * @return
     */
    private String ipBuilder(final List<Integer> ips) {
        StringBuilder ipBuilder = new StringBuilder();
        for (int i = 0; i < ips.size(); i++) {
            ipBuilder.append(ips.get(i));
            if (i < ips.size() - 1) {
                ipBuilder.append('.');
            }
        }

        return ipBuilder.toString();
    }

    private void backTrack(String s, int index, List<Integer> current, List<String> result) {
        LOGGER.debug("+backTrack(), s:{}, index:{}, current:{}, result:{}", s, index, current, result);
        LOGGER.debug("index:{}, s.length:{}, current.size:{}", index, s.length(), current.size());
        if (index == s.length()) {
            if (current.size() == 4) {
                result.add(ipBuilder(current));
                LOGGER.debug("result:{}", result);
            }
            LOGGER.debug("-backTrack({}), return", index);
            return;
        }

        int currentDigit = s.charAt(index) - '0';
        LOGGER.debug("currentDigit:{}", currentDigit);
        current.add(currentDigit); //1,0
        backTrack(s, index + 1, current, result);
        LOGGER.debug("Before #1st remove, index:{}, current:{}, size:{}", index, current, current.size());
        current.remove(current.size() - 1);
        LOGGER.debug("After #1st remove, index:{}, current:{}, size:{}", index, current, current.size());

        if (current.size() > 0) {
            int last = current.get(current.size() - 1);
            int candidate = last * 10 + currentDigit;
            LOGGER.debug("last:{}, candidate:{}", last, candidate);
            if (last != 0 && candidate <= 255) {
                LOGGER.debug("Before #2nd remove, index:{}, current:{}, size:{}", index, current, current.size());
                current.remove(current.size() - 1);
                current.add(candidate);
                LOGGER.debug("After #2nd remove and adding candidate, index:{},  current:{}, size:{}", index, current,
                             current.size());
                backTrack(s, index + 1, current, result);
            }
        }
        LOGGER.debug("-backTrack(), result:{}", result);
    }

}
