package com.rslakra.core.jwt;

/**
 * @author Rohtash Lakra
 * @created 4/15/20 12:21 PM
 */
public enum TokenType {
    CREDIT_CARD, ACH, DEBIT_CARD, POSTPAID;

    /**
     * @param tokenType
     * @return
     */
    public static TokenType of(final String tokenType) {
        return (tokenType == null ? null : TokenType.valueOf(tokenType.toUpperCase()));
    }
}