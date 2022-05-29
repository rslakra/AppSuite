package com.rslakra.core.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 5/7/20 5:43 PM
 */
public class WeekDaySerializer extends StdSerializer<WeekDays> {

    public WeekDaySerializer() {
        super(WeekDays.class);
    }

    public WeekDaySerializer(Class classType) {
        super(classType);
    }

    public void serialize(WeekDays weekDays, JsonGenerator generator, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        generator.writeString(weekDays.name().toLowerCase());
//        generator.writeStartObject();
//        generator.writeFieldName(weekDays.getValue());
////        generator.writeString(weekDays.name().toLowerCase());
//        generator.writeEndObject();
    }
}