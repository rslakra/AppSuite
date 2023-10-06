/**
 *
 */
package com.rslakra.appsuite.algos;

import com.rslakra.appsuite.core.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Base 10 https://en.wikipedia.org/wiki/Decimal
 *
 * @author Rohtash Lakra
 * @created 12-19-2019 8:41:57 AM
 */
public class DecimalNumbers {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(DecimalNumbers.class);

    private static final boolean DEBUG = false;
    private static final String SPACE = " ";

    //ONES
    private static final String[]
        UNITS =
        {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    //TWOS
    private static final String[][] TENS = {
        /* Fully Divisible */
        {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"},

        /* Fully Not Divisible */
        {"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"}};

    //place values
    private static final Map<Integer, String> PLACE_VALUES = new HashMap<>();

    public DecimalNumbers() {
        PLACE_VALUES.put(2, " ");
        PLACE_VALUES.put(3, "Hundred");
        PLACE_VALUES.put(4, "Thousand");
        PLACE_VALUES.put(7, "Million");
        PLACE_VALUES.put(10, "Billion");
        PLACE_VALUES.put(13, "Trillion");
        PLACE_VALUES.put(16, "Quadrillion");
        PLACE_VALUES.put(19, "Quintillion");
        PLACE_VALUES.put(22, "Sextillion");
        PLACE_VALUES.put(25, "Septillion");
        PLACE_VALUES.put(28, "Octillion");
        PLACE_VALUES.put(31, "Nonillion");
        PLACE_VALUES.put(34, "Decillion");
        PLACE_VALUES.put(37, "Undecillion");
        PLACE_VALUES.put(40, "Duodecillion");
        PLACE_VALUES.put(43, "Tredecillion");
        PLACE_VALUES.put(46, "Quatttuor-decillion");
        PLACE_VALUES.put(49, "Quindecillion");
        PLACE_VALUES.put(52, "Sexdecillion");
        PLACE_VALUES.put(55, "Septen-decillion");
        PLACE_VALUES.put(58, "Octodecillion");
        PLACE_VALUES.put(61, "Novemdecillion");
        PLACE_VALUES.put(64, "Vigintillion");
        PLACE_VALUES.put(304, "Centillion");
    }

    /**
     * Returns the previous minimum digit.
     *
     * @param digit
     * @return
     */
    private int findDivisorDigit(final int digit) {
        int tempValue = digit;
        while (!PLACE_VALUES.containsKey(tempValue)) {
            tempValue--;
        }

        return tempValue;
    }

    /**
     * Returns the 2 digit numbers english.
     *
     * @param number
     * @return
     */
    public String twoDigitEnglish(final int number) {
        // fully divisible row contains TEN, TWENTY,..., NINETY
        if (number % 10 == 0) {
            return TENS[0][(number / 10)];
        } else if (number > 10 && number < 20) {
            return TENS[1][(number % 10)];
        } else {
            return TENS[0][(number / 10)] + SPACE + UNITS[number % 10];
        }
    }

    /**
     * @param number
     * @return
     */
    public String twoDigitEnglishOptimized(final int number) {
        final StringBuilder wordBuilder = new StringBuilder();
        if (DEBUG) {
            LOGGER.debug("number: " + number);
        }

        int divisor = findDivisorDigit(MathUtils.countDecimalDigits(number));
        long minNumber = MathUtils.firstNDigitNumber(divisor);
        if (DEBUG) {
            LOGGER.debug("divisor: " + divisor + ", minNumber: " + minNumber);
        }
        wordBuilder.append(toEnglish(number / minNumber));
        wordBuilder.append(SPACE).append(PLACE_VALUES.get(divisor));
        wordBuilder.append(SPACE).append(toEnglish(number % minNumber));
        return wordBuilder.toString();
    }

    /**
     * Returns the word representation of the number.
     *
     * @param number
     * @return
     */
    public String toEnglish(final long number) {
        if (DEBUG) {
            LOGGER.debug("number: " + number);
        }
        final StringBuilder wordBuilder = new StringBuilder();
        int digits = MathUtils.countDecimalDigits(number);
        switch (digits) {
            case 1:
                wordBuilder.append(UNITS[MathUtils.toInteger(number)]);
                break;
            case 2:
                wordBuilder.append(twoDigitEnglish(MathUtils.toInteger(number)));
                break;
            default:
                int divisor = findDivisorDigit(digits);
                long minNumber = MathUtils.firstNDigitNumber(divisor);
                if (DEBUG) {
                    LOGGER.debug("divisor: " + divisor + ", minNumber: " + minNumber);
                }
                wordBuilder.append(toEnglish(number / minNumber));
                wordBuilder.append(SPACE).append(PLACE_VALUES.get(divisor));
                wordBuilder.append(SPACE).append(toEnglish(number % minNumber));
                break;
        }

        return wordBuilder.toString();
    }

}
