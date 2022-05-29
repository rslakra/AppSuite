package com.rslakra.core.enums;

import org.junit.Test;

/**
 * @author Rohtash Lakra
 * @created 3/30/20 4:19 PM
 */
public class TestGeoInfo {

    @Test
    public void testGeoInfo() {
//        String bookingCountry = "US";
//        String bookingCurrency = "TWD";

        String bookingCountry = "US";
        String bookingCurrency = "EUR";

        Country country = null;//Country.valueOf(bookingCountry);
        Currency currency = null;//Currency.valueOf(bookingCurrency);
        if (Country.TW != country || Currency.TWD != currency) {
            System.out.println("Invalid advertiser id, country:" + country.toString() + ", currency:" +
                               currency.toString());
            throw new RuntimeException("Invalid Input!");
        }

    }
}
