package com.rslakra.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Rohtash Lakra
 * @created 5/7/20 6:10 PM
 */
//@JsonDeserialize(using = DistanceUnitTypeDeserializer.class)
@JsonSerialize(using = LowerCaseSerializer.class)
public enum DistanceUnitType {
    MILE,
    KILOMETER,
    METER,
    CENTIMETER,
    MILLIMETER;


    @JsonCreator
    public static DistanceUnitType forValues(@JsonProperty("distance") String distance) {
        for (DistanceUnitType distanceUnitType : DistanceUnitType.values()) {
            if (distanceUnitType.name().equalsIgnoreCase(distance)) {
                return distanceUnitType;
            }
        }

        return null;
    }
}
