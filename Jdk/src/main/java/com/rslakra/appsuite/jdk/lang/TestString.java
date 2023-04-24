package com.rslakra.appsuite.jdk.lang;

import java.util.StringTokenizer;

/**
 * * Date: Jun 24, 2005 Time: 12:00:01 PM
 */
public class TestString {

    private static void testStringArray() {
        String[] ss = new String[3];
        String[] translateTable = new String[]{"16x9PB", "4x3", "4x3LB", "16x9"};

        for (int i = 0; i < translateTable.length; i++) {
            System.out.println(translateTable[i]);
        }
    }

    private static void testStrComparision() {
        String ver1 = "he30.mpp1.rush-10_bgc";
        String ver2 = "he30.mpp1.rush-13_default";
        System.out.println(" ver1 == ver2 : " + (ver1.equals(ver2)));

    }

    private static void testStringTokenizer() {
        String values = "One,Two,Three";
        String delimiters = "[,]";
        StringTokenizer strTokenizer = new StringTokenizer(values, delimiters);
        while (strTokenizer.hasMoreElements()) {
            String token = strTokenizer.nextToken();
            System.out.println("token:" + token);
        }
    }

    public static void main(String[] args) {
        String s1 = "Rohtash";
        String s2 = "Rohtash";
        String s3 = new String(" Rohtash");
        String s4 = new String(" Rohtash");
        String s5 = "Lakra";
        String nulls1 = null;
        String nulls2 = null;

        System.out.println("Comparing strings with equals:");
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s1.equals(s5));

        System.out.println("Comparing strings with ==:");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);

        System.out.println("Comparing strings with compareTo():");
        System.out.println(s1.compareTo(s3));
        System.out.println(s1.compareTo(s5));
        System.out.println(System.out.format("%d-%d=%d", (int) 'R', (int) 'L', ('R' - 'L')));

        String[] str = new String[3];
        str[0] = "rohtash";
        str[1] = "singh";
        str[2] = "rsi";
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }

        // final int LINE_WIDTH = 40;
        // String str = "In February of 1995, the most popular server software
        // on the Web was the public domain HTTP daemon developed by Rob McCool
        // at the National Center for Supercomputing Applications, University of
        // Illinois, Urbana-Champaign. However, development of that httpd had
        // stalled after Rob left NCSA in mid-1994, and many webmasters had";
        // System.out.println("" + str);
        // while(str.length() > LINE_WIDTH) {
        // System.out.println("" + str.substring(0,LINE_WIDTH));
        // str = str.substring(LINE_WIDTH);
        // if(str.length() < LINE_WIDTH) {
        // System.out.println(str);
        // }
        // }
        //
        //
        // String cisl_name =
        // "gggfffffffffffffffffffffffffffffffffuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeetttttttttttttttttttttttttttttttssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssrsuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuutttttttttttttttttttttttttt";
        // System.out.println("cisl_name length : " + cisl_name.length());
        //
        // String msg_senderId = "Let''s test this OpenACircle deal before OSH
        // so we can talk about there.";
        // System.out.println("msg_senderId length : " + msg_senderId.length());
        //
        // String assetname = "%u05D5%u05D9%u05D3%u05D0%u05D5
        // %u05E8%u05D0%u05E9%u05D5%u05DF - %u05D4%u05D6%u05DE%u05E0%u05D4
        // %u05DC%u05D0%u05EA%u05E8";
        // System.out.println("assetname length : " + assetname.length());
        //
        // String name = "Rohtash is testing";
        // if(name.contains(null)) {
        // System.out.println("Found");
        // }else {
        // System.out.println("Not Found");
        // }

        // testStringArray();

        // testStrComparision();
        testStringTokenizer();
    }

}
