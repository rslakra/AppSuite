package com.rslakra.appsuite.example.enums;

import com.rslakra.appsuite.core.IOUtils;
import com.rslakra.appsuite.core.json.JSONUtils;
import com.rslakra.appsuite.example.entity.AllCountryAndCurrencyResponse;
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
public class EnumsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(EnumsTest.class);

    @Test
    public void testCurrency() {
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        String json = null;
        try {
            json = JSONUtils.toJson(currencies);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug(json);

        LOGGER.debug("Object from JSON");
        String jsonExtra = "[\"INR\",\"TWD\",\"USD\",\"EUR\"]";
        List<Currency> extraCurrencies = null;
        try {
            extraCurrencies = Arrays.asList(JSONUtils.fromJson(jsonExtra, Currency[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug("extraCurrencies:{}\n", extraCurrencies);

        Set<Currency> setCurrencies = null;
        try {
            setCurrencies = JSONUtils.fromJson(jsonExtra, Set.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.debug("setCurrencies:{}", setCurrencies);
    }

    @Test
    public void testCountry() {
        EnumSet<Country> countries = EnumSet.allOf(Country.class);
        String json = null;
        try {
            json = JSONUtils.toJson(countries);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug(json);

        LOGGER.debug("Object from JSON");
        String jsonExtra = "[\"IN\",\"TW\",\"US\"]";
        List<Country> extraCountries = null;
        try {
            extraCountries = Arrays.asList(JSONUtils.fromJson(jsonExtra, Country[].class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug("{}", extraCountries);

        //
        jsonExtra = "[\"IN\",\"TW\",\"US\",\"CA\"]";
        Set<Country> setCountries = null;
        try {
            setCountries = JSONUtils.fromJson(jsonExtra, Set.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LOGGER.debug("{}", setCountries);
    }


    @Test
    public void testXmlCountryAndCurrency() {
        String xmlData = IOUtils.readContents("CountryAndCurrencies.xml");
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
        String jsonData = IOUtils.readContents("CountryAndCurrencies.json");
        LOGGER.debug("jsonData:" + jsonData);

        AllCountryAndCurrencyResponse response = null;
        //Using Gson Parser (if the json contains values that enum does not contain)
//        response = JsonUtility.INSTANCE.fromJSONString(jsonData, AllCountryAndCurrencyResponse.class);
        try {
            response = JSONUtils.fromJson(jsonData, AllCountryAndCurrencyResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(response);
        LOGGER.debug("{}", response);
//        Assert.assertEquals(response.toString(), jsonData);

    }
}
