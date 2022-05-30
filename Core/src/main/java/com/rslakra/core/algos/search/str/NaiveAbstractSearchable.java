package com.rslakra.core.algos.search.str;

import com.rslakra.core.algos.search.MatchedSearch;
import com.rslakra.core.algos.search.Searchable;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/11/22 1:24 PM
 */
public class NaiveAbstractSearchable extends AbstractSearchable implements Searchable {

    public NaiveAbstractSearchable() {
        super();
    }


    /**
     * Finds and returns all matches search string (pattern) within the input (input) string.
     *
     * @param input
     * @param pattern
     * @return
     */
    @Override
    public Iterable<MatchedSearch> search(String pattern, String input) {
        validate(pattern, input);

        for (int index = 0; index < input.length() - pattern.length(); index++) {
            int matchCount = 0;
            while (input.charAt(index + matchCount) == pattern.charAt(matchCount)) {
                // check next character
                matchCount++;

                // check if more characters need to find
                if (pattern.length() == matchCount) {
                    // match found, save it
                    getMatchedSearches().add(new MatchedSearchImpl(index, matchCount));

                    // jump to find next pattern
                    index += matchCount - 1;
                    break;
                }
            }
        }

        logTimeTaken();
        return getMatchedSearches();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        testSearchable(new NaiveAbstractSearchable());
    }
}
