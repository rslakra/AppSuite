package com.rslakra.jdk;


import org.apache.commons.lang3.StringUtils;

public class StringUtilsExampleV1 {

    public static void main(String[] args) {

        System.err.println(StringUtils.abbreviate("Take time off working", 0, 10));
        System.err.println(StringUtils.capitalize("vanderLust"));
        System.err.println(StringUtils.center("MTV", 7, '='));
        System.err.println(StringUtils.removeEnd("temperature", "ure"));
        System.err.println(StringUtils.chop("Dane"));
        System.err.println(StringUtils.contains("Dorothy", "oro"));
        System.err.println(StringUtils.containsNone("r u m t", new char[]{'r', 'o'}));
        System.err.println(StringUtils.containsOnly("r u m t", new char[]{'r', 'o'}));
        System.err.println(StringUtils.countMatches("arthur", "r"));
        System.err.println(StringUtils.deleteWhitespace("f f f f"));
        System.err.println(StringUtils.difference("govern", "government"));
        System.err.println(StringUtils.getLevenshteinDistance("govern", "government"));

    }
}
