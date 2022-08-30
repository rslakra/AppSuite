package com.rslakra.core.bos;

/**
 * Aggregated rating associated with an entity. Typically a critical or user rating. E.g., as of June 2020, the movie
 * "The Godfather (1972)" had an IMDb rating of 9.2 out of 10, based on 1.5 million user ratings.
 * <p>
 * More general types Intangible Thing Rating
 *
 * @author Rohtash Lakra
 * @created 1/27/22 10:43 AM
 */
public class AggregatedRating {

    //    Word or phrase used to qualify the meaning of the concept it is associated with.
//        Literal
    private RatingQualifier qualifier;

    //    count of total number of ratings
    private Integer ratingCount;

}
