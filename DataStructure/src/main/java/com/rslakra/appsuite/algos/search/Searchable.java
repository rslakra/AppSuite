package com.rslakra.appsuite.algos.search;

/**
 * @author Rohtash Lakra
 * @created 5/11/22 12:57 PM
 */
public interface Searchable {

    /**
     * Finds and returns all matches search string (pattern) within the input (input) string.
     *
     * @param pattern
     * @param input
     * @return
     */
    Iterable<MatchedSearch> search(String pattern, String input);

}
