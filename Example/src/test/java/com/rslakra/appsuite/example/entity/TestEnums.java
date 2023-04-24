package com.rslakra.appsuite.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import com.rslakra.appsuite.core.ToString;
import com.rslakra.appsuite.core.enums.DistanceUnitType;
import com.rslakra.appsuite.core.enums.DistanceUnitTypeDeserializer;
import com.rslakra.appsuite.core.enums.WeekDayDeserializer;
import com.rslakra.appsuite.core.enums.WeekDays;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 5/7/20 3:26 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class TestEnums {

    @JsonProperty("distance")
    @JsonDeserialize(using = DistanceUnitTypeDeserializer.class)
    private DistanceUnitType distanceUnitType;

    @JsonProperty("weekDay")
    @JsonDeserialize(using = WeekDayDeserializer.class)
    @SerializedName("weekDay")
    private WeekDays weekDays;

//    public TestEnums(@JsonProperty("distance") String distanceUnitType, @JsonProperty("weekDay") String weekDays) {
//        this.distanceUnitType = DistanceUnitType.valueOf(distanceUnitType);
//        this.weekDays = WeekDays.valueOf(weekDays);
//    }

    public TestEnums(DistanceUnitType distanceUnitType, WeekDays weekDays) {
        this.distanceUnitType = distanceUnitType;
        this.weekDays = weekDays;
    }

    /**
     * ToString
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of()
            .add("distanceUnitType=", distanceUnitType)
            .add("weekDays=", weekDays)
            .toString();
    }

}
