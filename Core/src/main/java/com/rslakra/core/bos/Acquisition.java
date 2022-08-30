package com.rslakra.core.bos;

import java.math.BigDecimal;

/**
 * Reified relationship that describes a transaction in which the ownership of a company or other business organization
 * is transferred or consolidated with another.
 *
 * @author Rohtash Lakra
 * @created 1/26/22 4:59 PM
 */
public class Acquisition {

    // Connects an acquisition to its acquiree.
    private Company acquiree;

    // Connects an acquisition to its acquirer.
    private Organization acquirer;

    // Describes how much of the acquiree is being acquired by the acquirer in case of partial acquisition. E.g, 51%.
    private BigDecimal acquisitionPercentage;

    // The form of payment. E.g., "cash", "stock", "debt", "1/2 cash - 1/2 stock", etc.
    private AcquisitionTerm acquisitionTerm;

    // Describes the acquisition type. E.g, ACQUISITION, MERGER.
    private AcquisitionType acquisitionType;

    // Represent an amount of money spent.
    private BigDecimal amountSpent;

    // ISO-4217 currency code.
    private Currency currencyCode;

    // The structure of the acquired organization after the acquisition. E.g., subsidiary, combined, etc.
    private DispositionTerm dispositionTerm;

}
