package com.rslakra.appsuite.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.appsuite.core.json.JSONUtils;
import com.rslakra.appsuite.example.enums.Country;
import com.rslakra.appsuite.example.enums.Currency;

import java.io.IOException;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 5/19/20 12:50 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryAndCurrency {

    @JsonProperty("BookingCountry")
    public Country bookingCountry;

    @JsonProperty("BillingCountry")
    public Country billingCountry;

    @JsonProperty("Currency")
    public Set<Currency> supportedCurrencies;

    @JsonProperty("DefaultCurrency")
    public Currency defaultCurrency;

    /**
     * @return
     */
    @Override
    public String toString() {
        try {
            return JSONUtils.toJson(this);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
//        return String.format("CountryAndCurrency <bookingCountry:%s, billingCountry:%s, supportedCurrencies:%s, "
//                             + "defaultCurrency:%s>",
//                             bookingCountry, billingCountry, supportedCurrencies, defaultCurrency);
    }
}
