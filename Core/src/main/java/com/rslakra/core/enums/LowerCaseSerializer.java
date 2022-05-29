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
public class LowerCaseSerializer extends StdSerializer<Object> {

    public LowerCaseSerializer() {
        super((Class) null);
    }

    public LowerCaseSerializer(Class classType) {
        super(classType);
    }

    public void serialize(Object type, JsonGenerator generator, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        generator.writeString(type.toString().toLowerCase());
    }
}