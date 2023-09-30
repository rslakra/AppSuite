package com.rslakra.appsuite.questions.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 9/11/23 9:33 AM
 */
public class LC929UniqueEmailAddresses {

    /**
     * @param emails
     * @return
     */
    public int numUniqueEmails(String[] emails) {
        if (emails == null) {
            return 0;
        }

        Set<String> unique = new HashSet<>();
        for (String email : emails) {
            String[] parts = email.split("@");
            int atIndex = parts[0].indexOf("+");
            if (atIndex != -1) { // if contains + sign
                parts[0] = parts[0].substring(0, atIndex);
            }
            unique.add(parts[0].replace(".", "") + "@" + parts[1]);
        }

        return unique.size();
    }

}
