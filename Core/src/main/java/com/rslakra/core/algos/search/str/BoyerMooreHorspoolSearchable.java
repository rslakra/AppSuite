package com.rslakra.core.algos.search.str;

import com.rslakra.core.algos.search.MatchedSearch;
import com.rslakra.core.algos.search.Searchable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/11/22 3:51 PM
 */
public class BoyerMooreHorspoolSearchable extends AbstractSearchable implements Searchable {

    public BoyerMooreHorspoolSearchable() {
        super();
    }

    /**
     * Finds and returns all matches search string (pattern) within the input (input) string.
     *
     * @param pattern
     * @param input
     * @return
     */
    @Override
    public Iterable<MatchedSearch> search(String pattern, String input) {
        validate(pattern, input);

        // build a bad match table
        BadMatchTable badMatchTable = new BadMatchTable(pattern);
        int startIndex = 0;
        // iterate till the input string less than pattern
        while (startIndex <= (input.length() - pattern.length())) {
            int fullMatchIndex = pattern.length() - 1;
            // check patterns index matches with pattern index
            while (fullMatchIndex >= 0 && input.charAt(startIndex + fullMatchIndex) == pattern.charAt(fullMatchIndex)) {
                fullMatchIndex--;
            }

            if (fullMatchIndex < 0) {
                // match found, save it
                getMatchedSearches().add(new MatchedSearchImpl(startIndex, pattern.length()));

                // jump to find next pattern
                startIndex += pattern.length();
            } else {
                // jump to find next pattern
                startIndex += badMatchTable.getIndex(input.charAt(startIndex + pattern.length() - 1));
            }
        }

        logTimeTaken();
        return getMatchedSearches();
    }

    private class BadMatchTable {

        private int defaultIndex;
        private Map<Character, Integer> distances;

        /**
         * @param pattern
         */
        public BadMatchTable(final String pattern) {
            defaultIndex = pattern.length();
            distances = new HashMap<>();
            for (int i = 0; i < pattern.length() - 1; i++) {
                distances.put(pattern.charAt(i), (pattern.length() - i - 1));
            }
        }

        /**
         * @param cChar
         * @return
         */
        public int getIndex(char cChar) {
            return distances.getOrDefault(cChar, defaultIndex);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        AbstractSearchable.testSearchable(new BoyerMooreHorspoolSearchable());

    }

}
