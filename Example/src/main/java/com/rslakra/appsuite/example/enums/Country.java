package com.rslakra.appsuite.example.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rslakra.appsuite.adtech.entity.GeoInfo;
import com.rslakra.appsuite.adtech.entity.WoeIdType;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 3/30/20 3:46 PM
 */
@JsonDeserialize(using = Country.CountryDeserializer.class)
public enum Country {
    IN("India", 23424848),
    TW("Taiwan", 23424971, Language.ZH_TW),
    US("United States", 23424977, Language.EN);

    private final GeoInfo geoInfo;
    private final Language language;

    private Country(String name, long woeId, Language language) {
        this.geoInfo = new GeoInfo(woeId, woeId, WoeIdType.COUNTRY, name);
        this.language = language;
    }

    private Country(String name, long woeId) {
        this(name, woeId, Language.EN);
    }


    public static Country fromString(String value) {
        try {
            return Country.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            //ignore the exception
            return null;
        }
    }

    static class CountryDeserializer extends JsonDeserializer<Country> {

        /**
         * @param jsonParser
         * @param deserializationContext
         * @return
         * @throws IOException
         * @throws JsonProcessingException
         */
        @Override
        public Country deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
            return Country.fromString(jsonParser.getValueAsString());
        }
    }

}
