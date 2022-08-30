package com.rslakra.core.bos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Captures the acceptance rate or selectivity: i.e. the percentage of students who are admitted. The lower the
 * percentage, the more selective the school is.
 *
 * @author Rohtash Lakra
 * @created 1/26/22 4:55 PM
 */
public class AcceptanceRate {

    // Connects an acceptance rate to its educational institution.
    private EducationalInstitution acceptanceRateOf;

    // Percentage of students admitted in the educational institution.
    private BigDecimal acceptanceRatePercent;

    // Reference date for a specific data properties. E.g. the population of France was 67,128,000 as of 2015.
    private Date referenceDate;

}
