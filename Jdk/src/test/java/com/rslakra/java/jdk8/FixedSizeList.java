package com.rslakra.java.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 6/21/23 6:20 PM
 */
public class FixedSizeList {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> names = Arrays.asList(new String[3]);
        names.set(0, "Rohtash");
        names.set(1, "Singh");
        names.set(2, "Lakra");
        names.add("No Name");
    }

}
