package com.rslakra.core.algos.search.str;

import com.rslakra.core.algos.search.MatchedSearch;
import com.rslakra.core.algos.search.Searchable;
import com.rslakra.core.BeanUtils;
import com.rslakra.core.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/11/22 1:06 PM
 */
public abstract class AbstractSearchable implements Searchable {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(AbstractSearchable.class);
    private List<MatchedSearch> matchedSearches;
    private StopWatch stopWatch = new StopWatch();

    public AbstractSearchable() {
        matchedSearches = new ArrayList<>();
    }

    /**
     * @return
     */
    protected List<MatchedSearch> getMatchedSearches() {
        return matchedSearches;
    }

    protected void logTimeTaken() {
        stopWatch.stopTimer();
        LOGGER.debug("{}", stopWatch);
    }

    /**
     * @param pattern
     * @param input
     */
    protected void validate(final String pattern, final String input) {
        Objects.requireNonNull(pattern, "The search pattern must provide!");
        Objects.requireNonNull(input, "The input string must provide!");

        // reset the matched searches
        stopWatch.startTimer();
        getMatchedSearches().clear();
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
        return null;
    }

    /**
     * @param matchedSearches
     */
    public static void logSearchable(final Iterable<MatchedSearch> matchedSearches) {
        if (BeanUtils.isNotNull(matchedSearches)) {
            final AtomicInteger totalCount = new AtomicInteger(0);
            matchedSearches.forEach(matchedSearch -> {
                totalCount.incrementAndGet();
                LOGGER.debug("matchedSearch:{}", matchedSearch);
            });
            LOGGER.info("totalCount:{}", totalCount.get());
        }
    }

    /**
     * @param searchable
     */
    public static void testSearchable(final Searchable searchable) {
        LOGGER.debug("+testSearchable({})", searchable);
        String input = "The quick brown fox jumps over the little lazy dog.";
        String pattern = "fox";
        LOGGER.debug("Searching pattern:{}", pattern);
        Iterable<MatchedSearch> matchedSearches = searchable.search(pattern, input);
        logSearchable(matchedSearches);

        input = "Some books are to be tasted, others to be swallowed, and some few to be chewed and digested.";
        pattern = "to";
        LOGGER.debug("\n");
        LOGGER.debug("Searching pattern:{}", pattern);
        matchedSearches = searchable.search(pattern, input);
        logSearchable(matchedSearches);

        int strLength = 1000;
        input = "";
        for (int i = 0; i < strLength; i++) {
            input += "The truth is out there.";
        }
        pattern = "truth";
        LOGGER.debug("\n");
        LOGGER.debug("Searching pattern:{}", pattern);
        matchedSearches = searchable.search(pattern, input);
        logSearchable(matchedSearches);

        LOGGER.debug("-testSearchable({})", searchable);
    }
}
