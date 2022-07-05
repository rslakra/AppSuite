package com.rslakra.core.bos;

/**
 * An ambassador is an official envoy, or high-ranking diplomat who represents a State/Country to another State/Country
 * <p>
 * More general types Person Thing Entity
 *
 * @author Rohtash Lakra (rlakra)
 * @created 1/27/22 10:59 AM
 */
public class Ambassador {

    //    Connects a person to the country he or she represents as an ambassador. That is, the ambassador's home country.
//    GeoPoliticalEntity	At most one
    private GeoPoliticalEntity ambassadorRepresent;

    //    Connects an ambassador to the country he or she is enabling diplomatic relations to.
//    GeoPoliticalEntity	At most one
    private GeoPoliticalEntity ambassadorTo;

}
