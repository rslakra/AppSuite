package com.rslakra.appsuite.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import com.rslakra.appsuite.core.enums.DistanceUnitType;
import com.rslakra.appsuite.core.enums.DistanceUnitTypeDeserializer;
import com.rslakra.appsuite.core.enums.WeekDayDeserializer;
import com.rslakra.appsuite.core.enums.WeekDays;

/**
 * @author Rohtash Lakra
 * @created 5/7/20 3:26 PM
 */
public class ClassTest {

    @JsonProperty("distance")
    @JsonDeserialize(using = DistanceUnitTypeDeserializer.class)
    private DistanceUnitType distanceUnitType;

    @JsonProperty("weekDay")
    @JsonDeserialize(using = WeekDayDeserializer.class)
    @SerializedName("weekDay")
    private WeekDays weekDays;

    public ClassTest() {
    }

//    public TestEnums(@JsonProperty("distance") String distanceUnitType, @JsonProperty("weekDay") String weekDays) {
//        this.distanceUnitType = DistanceUnitType.valueOf(distanceUnitType);
//        this.weekDays = WeekDays.valueOf(weekDays);
//    }

    public ClassTest(DistanceUnitType distanceUnitType, WeekDays weekDays) {
        this.distanceUnitType = distanceUnitType;
        this.weekDays = weekDays;
    }

    public DistanceUnitType getDistanceUnitType() {
        return distanceUnitType;
    }

    public void setDistanceUnitType(DistanceUnitType distanceUnitType) {
        this.distanceUnitType = distanceUnitType;
    }

    public WeekDays getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(WeekDays weekDays) {
        this.weekDays = weekDays;
    }
}
