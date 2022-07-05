package com.rslakra.core.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.core.Utils;
import com.rslakra.core.entity.AllCountryAndCurrencyResponse;
import com.rslakra.core.json.JSONUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 5/8/20 11:25 AM
 */
public class ENumsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(ENumsTest.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testFindEnums() {
        WeekDays weekDays = WeekDays.findByName(WeekDays.SUNDAY.name());
        Assert.assertEquals(WeekDays.SUNDAY, weekDays);

        WeekDays nullEnum = Utils.findEnumByClass(WeekDays.class, "thrursday");
        Assert.assertNull(nullEnum);

        WeekDays weekDayByClass = Utils.findEnumByClass(WeekDays.class, WeekDays.MONDAY.name());
        Assert.assertEquals(WeekDays.MONDAY, weekDayByClass);

        nullEnum = Utils.findEnumByClass(WeekDays.class, "friday");
        Assert.assertNull(nullEnum);
    }


    @Test
    public void testCurrency() {
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        String json = null;
        try {
            json = JSONUtils.INSTANCE.toJson(currencies);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug(json);

        LOGGER.debug("Object from JSON");
        String jsonExtra = "[\"INR\",\"TWD\",\"USD\",\"EUR\"]";
        List<Currency> extraCurrencies = null;
        try {
            extraCurrencies = Arrays.asList(JSONUtils.INSTANCE.fromJson(jsonExtra, Currency[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug("extraCurrencies:{}\n", extraCurrencies);

        //
        Set<Currency> setCurrencies = null;
        try {
            setCurrencies = objectMapper.readValue(jsonExtra, new TypeReference<Set<Currency>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        LOGGER.debug("setCurrencies:{}", setCurrencies);
    }


    @Test
    public void testCountry() {
        EnumSet<Country> countries = EnumSet.allOf(Country.class);
        String json = null;
        try {
            json = JSONUtils.INSTANCE.toJson(countries);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug(json);

        LOGGER.debug("Object from JSON");
        String jsonExtra = "[\"IN\",\"TW\",\"US\"]";
        List<Country> extraCountries = null;
        try {
            extraCountries = Arrays.asList(JSONUtils.INSTANCE.fromJson(jsonExtra, Country[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug("{}", extraCountries);

        //
        jsonExtra = "[\"IN\",\"TW\",\"US\",\"CA\"]";
        Set<Country> setCountries = null;
        try {
            setCountries = objectMapper.readValue(jsonExtra, new TypeReference<Set<Country>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        LOGGER.debug("{}", setCountries);
    }


    @Test
    public void testXmlCountryAndCurrency() {
        String xmlData = Utils.INSTANCE.readFile("CountryAndCurrencies.xml");
        LOGGER.debug(xmlData);

//        AllCountryAndCurrencyResponse response = null;
//        try {
//            XmlUtils.INSTANCE.registerUnmarshaller(AllCountryAndCurrencyResponse.class);
//            response = XmlUtils.INSTANCE.unmarshall(xmlData, AllCountryAndCurrencyResponse.class);
//             LOGGER.debug(response);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//        Assert.assertNotNull(response);
    }

    @Test
    public void testJsonCountryAndCurrency() {
        String jsonData = Utils.INSTANCE.readFile("CountryAndCurrencies.json");
        LOGGER.debug("jsonData:" + jsonData);

        AllCountryAndCurrencyResponse response = null;
        //Using Gson Parser (if the json contains values that enum does not contain)
//        response = JsonUtility.INSTANCE.fromJSONString(jsonData, AllCountryAndCurrencyResponse.class);
        try {
            response = JSONUtils.INSTANCE.fromJson(jsonData, AllCountryAndCurrencyResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(response);
        LOGGER.debug("{}", response);
//        Assert.assertEquals(response.toString(), jsonData);

    }
}
