package com.rslakra.appsuite.adtech.entity;

import com.rslakra.appsuite.example.enums.Country;
import com.rslakra.appsuite.example.enums.Currency;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 8/24/20 6:48 PM
 */
@Getter
public enum CountryAndCurrencyId {

    mb01(Country.US, Currency.USD),
    cb10(Country.TW, Currency.TWD),
    cb14(Country.IN, Currency.INR),
    cb15(Country.IN, Currency.USD),
    cb26(Country.US, Currency.EUR);

    private final Country country;
    private final Currency currency;

    /**
     * @param country
     * @param currency
     */
    private CountryAndCurrencyId(final Country country, final Currency currency) {
        this.country = country;
        this.currency = currency;
    }

    /**
     * @param country
     * @param currency
     * @return
     */
    public static CountryAndCurrencyId getCountryAndCurrencyId(Country country, Currency currency) {
        if (country != null && currency != null) {
            return Arrays.stream(values())
                .filter(countryAndCurrencyId -> countryAndCurrencyId.getCountry() == country)
                .filter(countryAndCurrencyId -> countryAndCurrencyId.getCurrency() == currency)
                .distinct().findFirst()
                .orElse(null);
        }

        return null;
    }

}
