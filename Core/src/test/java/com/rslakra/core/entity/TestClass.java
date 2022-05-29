package com.rslakra.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import com.rslakra.core.enums.DistanceUnitType;
import com.rslakra.core.enums.DistanceUnitTypeDeserializer;
import com.rslakra.core.enums.WeekDayDeserializer;
import com.rslakra.core.enums.WeekDays;
import com.rslakra.core.utils.ToString;
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
public class TestClass {

    @JsonProperty("distance")
    @JsonDeserialize(using = DistanceUnitTypeDeserializer.class)
    private DistanceUnitType distanceUnitType;

    @JsonProperty("weekDay")
    @JsonDeserialize(using = WeekDayDeserializer.class)
    @SerializedName("weekDay")
    private WeekDays weekDays;

//    public TestClass(@JsonProperty("distance") String distanceUnitType, @JsonProperty("weekDay") String weekDays) {
//        this.distanceUnitType = DistanceUnitType.valueOf(distanceUnitType);
//        this.weekDays = WeekDays.valueOf(weekDays);
//    }

    public TestClass(DistanceUnitType distanceUnitType, WeekDays weekDays) {
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
