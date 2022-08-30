package com.rslakra.core.bos;

/**
 * A company that provides flights for passengers.
 * <p>
 * More general types Company Thing Entity Organization
 *
 * @author Rohtash Lakra
 * @created 1/27/22 10:46 AM
 */
public class Airline {

    // Identifier for an airline or airport, defined by the International Air Transport Association (IATA). Note that IATA codes may not be unique. After an airline is delisted, IATA can make the code available for reuse after six months and can issue "controlled duplicates" to regional airlines whose destinations are not likely to overlap.
    private String iataCode;

    // Identifier for an airline or aviation facility (e.g. airport, weather station, etc), defined by the International Civil Aviation Organization (ICAO).
    private String icaoCode;

}
