package com.rslakra.appsuite.adtech.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 5/19/20 12:56 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllCountryAndCurrencyResponse {

    @JsonProperty("bookingCountryAndCurrenciesList")
    public List<CountryAndCurrency> entryList;

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("AllCountryAndCurrencyResponse <entryList=%s>", entryList);
    }
}
