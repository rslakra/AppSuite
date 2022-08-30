package com.rslakra.core.bos;

import java.math.BigDecimal;

/**
 * Describes the specific location where an entity can be found and where mail can be received, generally using
 * political boundaries and street names as references, along with other identifiers such as house or apartment
 * numbers.
 * <p>
 * Disjoint types Place ContactInfo OnlineAccount Event FictionalPlace
 *
 * @author Rohtash Lakra
 * @created 1/27/22 10:27 AM
 */
public class Address {

    // Connects a physical address to the entity located there.
// Person or Place or Organization or FictionalCharacter or FictionalPlace
    private Object addressOf;

    // Describes the address type. E.g., HOME, BUSINESS, WORK, MAILING, PHYSICAL.
    private AddressType addressType;

    // Building number of an address listing.
    private String buildingNumber;

    // The latitude that will lead to the center point of a location.
    private BigDecimal centerLatitude;

    // The longitude that leads to the central point of a location
    private BigDecimal centerLongitude;

    // City where a place is located.
    private City city;

    // The country of a physical location or address.
    private Country country;

    // Standardized code to designate a country in an address or other entity. Must conform to ISO 3166 country codes.
// ISO-3166-1-alpha2string	At most one
    private String countryCode;

    // The county of a physical location
    private County county;

    // County Code
// Literal	At most one
    private String countyCode;

    // Used to pinpoint a map or address location.
// Literal	At most one
    private String crossStreet;

    // Department name.
// Literal	At most one
    private String departmentName;

    // Full address to be displayed.
// Literal	At most one
    private Address displayAddress;

    // The second line of a street address, also known as addr2.
// Literal	At most one
    private String extendedAddress;

    // Connects a place to the media works filmed within it.
// Movie or TvEpisode or TvSeries or TvSpecial or TvSeason
    private FilmingLocation filmingLocationOf;

    // Designation of the building floor number for an address. Could also be "basement" or "mezzanine," for instance
// Literal	At most one
    private BigDecimal floorNumber;

    // Latitude of a location
    private BigDecimal latitude;

    // Used in Indian addresses in a way similar to the US cross street concept.
// Literal	At most one
    private String locationLandmark;

    // Longitude of a location
    private BigDecimal longitude;

    // A mail stop is a four-digit code that shows up after a ZIP code in a postal address.
// Literal	At most one
    private String mailStop;

    // Neighborhood of an address or other location. Usually an array.
// Literal	At most one
    private String neighborhoodName;

    // Post office box where mail may be delivered for an organization or person.
// Literal	At most one
    private String postOfficeBox;

    // Unique to British mailing addresses.
// Literal
    private String postTown;

    // The zip or postal code for a location's physical address.
    private String postalCode;

    // Precise zip code if any. Else, similar to postalCode. This corresponds to "postal" from FindLocation. For example: 95089-1019
// Literal	At most one
    private String precisePostalCode;

    // Designates whether a record is the primary value for a set of records. For instance, primary contact or phone number.
    private boolean primary;

    // Raw, unparsed address data.
// Literal	At most one
    private Object rawAddress;

    // Connects an address or contact information to the region it serves. For example, one address for US clients and another for European customers.
// Literal	At most one
    private Object regionServed;

    // State or province where a place is located.
    private State state;

    // standardized code to designate a state.
// Literal	At most one
    private String stateCode;

    // The first line of a street address. Also known as Addr1.
// Literal	At most one
    private String streetAddress;

    // The name of the street in an address listing
// Literal	At most one
    private String streetName;

    // Describes the street type. E.g., AVENUE, LANE.
// Literal	At most one
    private StreetType streetType;

    // Unit value. Eg: G in 1659 Branham Ln Ste G
// Literal	At most one
    private UnitValue unitAddress;

    // Describes the unit type. E.g., SUITE in '1659 Branham Ln, Ste G'.
    private UnitType unitType;

    // Zip code suffix applied for greater specificity.
    private String zipPlusFour;

}
