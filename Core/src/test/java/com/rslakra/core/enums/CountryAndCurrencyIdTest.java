package com.rslakra.core.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rohtash Lakra
 * @created 8/24/20 6:54 PM
 */
public class CountryAndCurrencyIdTest {

    @Test
    public void testCountryAndCurrencyId() {
        CountryAndCurrencyId id = CountryAndCurrencyId.getCountryAndCurrencyId(null, null);
        Assert.assertNull(id);

        id = CountryAndCurrencyId.getCountryAndCurrencyId(Country.IN, null);
        Assert.assertNull(id);

        id = CountryAndCurrencyId.getCountryAndCurrencyId(null, Currency.INR);
        Assert.assertNull(id);

        id = CountryAndCurrencyId.getCountryAndCurrencyId(Country.IN, Currency.INR);
        Assert.assertNotNull(id);
        System.out.println(id);
        Assert.assertEquals(id, CountryAndCurrencyId.cb14);

    }

}
