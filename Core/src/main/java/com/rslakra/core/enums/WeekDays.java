package com.rslakra.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 5/7/20 3:11 PM
 */
@JsonDeserialize(using = WeekDayDeserializer.class)
//@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
//@JsonNaming(LowerCaseStrategy.class)
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
//@JsonSerialize(using = WeekDaySerializer.class)
@JsonSerialize(using = LowerCaseSerializer.class)
public enum WeekDays {
    SUNDAY,
    MONDAY;

//    SUNDAY("sunday"),
//    MONDAY("monday"),
//    TUESDAY("tuesday"),
//    WEDNESDAY("wednesday"),
//    THURSDAY("thursday"),
//    FRIDAY("friday"),
//    SATURDAY("saturday");
//
//    private String value;
//
//    private WeekDays(final String value) {
//        this.value = value;
//    }
//
//    /**
//     * @return
//     */
//    public String getValue() {
//        return this.value;
//    }
//
//    @Override
//    public String toString() {
//        return getValue();
//    }

    @JsonCreator
    public static WeekDays forValues(@JsonProperty("weekDay") String weekDay) {
        for (WeekDays weekDays : WeekDays.values()) {
            if (weekDays.name().equalsIgnoreCase(weekDay)) {
                return weekDays;
            }
        }

        return null;
    }

    /**
     * @param weekDay
     * @return
     */
    public static WeekDays findByName(final String weekDay) {
        return Arrays.stream(WeekDays.values())
            .filter(e -> e.name().equalsIgnoreCase(weekDay)).findAny().orElse(null);

    }
}