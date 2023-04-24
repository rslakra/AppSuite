package com.rslakra.appsuite.example.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 3/30/20 3:46 PM
 */
@JsonDeserialize(using = Currency.CurrencyDeserializer.class)
public enum Currency {
    INR, TWD, USD, EUR;

    public static Currency fromString(String value) {
        for (Currency currency : values()) {
            if (currency.name().equalsIgnoreCase(value)) {
                return currency;
            }
        }

        return null;
    }

    static class CurrencyDeserializer extends JsonDeserializer<Currency> {

        @Override
        public Currency deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
            return Currency.fromString(jsonParser.getValueAsString());
        }
    }
}


