package com.rslakra.core.bos;

/**
 * An aerodrome with extended facilities where aircrafts may load and unload passengers and cargo. E.g., "San Jose
 * International Airport".
 * <p>
 * More general types Place Thing Entity TransportHub Structure
 *
 * @author Rohtash Lakra (rlakra)
 * @created 1/27/22 10:47 AM
 */
public class Airport {

    //    Identifier for an airline or airport, defined by the International Air Transport Association (IATA). Note that IATA codes may not be unique. After an airline is delisted, IATA can make the code available for reuse after six months and can issue "controlled duplicates" to regional airlines whose destinations are not likely to overlap.
    private String iataCode;

    //    Identifier for an airline or aviation facility (e.g. airport, weather station, etc), defined by the International Civil Aviation Organization (ICAO).
    private String icaoCode;

    //    Connects an organization with the cities it serves.
//    GeoPoliticalEntity
    private GeoPoliticalEntity serviceArea;


}
