package com.rslakra.core.enums;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 8/24/20 6:54 PM
 */
public class CountryAndCurrencyIdTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryAndCurrencyIdTest.class);

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
        LOGGER.debug("id:{}", id);
        Assert.assertEquals(id, CountryAndCurrencyId.cb14);

    }

}
