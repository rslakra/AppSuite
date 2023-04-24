package com.rslakra.appsuite.algos.text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 11/6/20 3:54 PM
 */
public class StringCodec {

    private static final String DELIMITER = "#";

    public StringCodec() {
    }

    /**
     * Encodes the list of strings to a single string.
     *
     * @param listStrings
     * @return
     */
    public String encode(List<String> listStrings) {
        final StringBuilder sBuilder = new StringBuilder();
        for (String str : listStrings) {
            sBuilder.append(str.length()).append(DELIMITER).append(str);
        }

        return sBuilder.toString();
    }

    /**
     * Decodes a single string to the list of strings.
     *
     * @param encoded
     * @return
     */
    public List<String> decode(String encoded) {
        final List<String> listStrings = new ArrayList<String>();
        int i = 0;
        while (i < encoded.length()) {
            int index = encoded.indexOf(DELIMITER, i);
            int size = Integer.valueOf(encoded.substring(i, index));
            int nextIndex = index + size + 1;
            listStrings.add(encoded.substring(index + 1, nextIndex));
            i = nextIndex;
        }

        return listStrings;
    }

}
