/**
 *
 */
package com.rslakra.core.algos;

import com.rslakra.core.Numbers;

import java.util.HashMap;
import java.util.Map;

/**
 * Base 10 https://en.wikipedia.org/wiki/Decimal
 *
 *
 * @author Rohtash Singh Lakra (rslakra.work@gmail.com)
 * @since 12-19-2019 8:41:57 AM
 */
public class DecimalNumbers {

    private static final boolean DEBUG = false;
    private static final String SPACE = " ";

    //ONES
    private final String[] UNITS = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    //TWOS
    private final String[][] TENS = {
        /* Fully Divisible */
        {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"},

        /* Fully Not Divisible */
        {"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"}
    };

    //place values
    private final Map<Integer, String> placeValues = new HashMap<>();

    public DecimalNumbers() {
        placeValues.put(2, " ");
        placeValues.put(3, "Hundred");
        placeValues.put(4, "Thousand");
        placeValues.put(7, "Million");
        placeValues.put(10, "Billion");
        placeValues.put(13, "Trillion");
        placeValues.put(16, "Quadrillion");
        placeValues.put(19, "Quintillion");
        placeValues.put(22, "Sextillion");
        placeValues.put(25, "Septillion");
        placeValues.put(28, "Octillion");
        placeValues.put(31, "Nonillion");
        placeValues.put(34, "Decillion");
        placeValues.put(37, "Undecillion");
        placeValues.put(40, "Duodecillion");
        placeValues.put(43, "Tredecillion");
        placeValues.put(46, "Quatttuor-decillion");
        placeValues.put(49, "Quindecillion");
        placeValues.put(52, "Sexdecillion");
        placeValues.put(55, "Septen-decillion");
        placeValues.put(58, "Octodecillion");
        placeValues.put(61, "Novemdecillion");
        placeValues.put(64, "Vigintillion");
        placeValues.put(304, "Centillion");
    }

    /**
     * Returns the previous minimum digit.
     * @param digit
     * @return
     */
    private int findDivisorDigit(final int digit) {
        int tempValue = digit;
        while (!placeValues.containsKey(tempValue)) {
            tempValue--;
        }

        return tempValue;
    }

    /**
     * Returns the 2 digit numbers english.
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
     *
     * @param number
     * @return
     */
    public String twoDigitEnglishOptimized(final int number) {
        final StringBuilder wordBuilder = new StringBuilder();
        if (DEBUG) {
            System.out.println("number: " + number);
        }

        int divisor = findDivisorDigit(Numbers.countDecimalDigits(number));
        long minNumber = Numbers.firstNDigitNumber(divisor);
        if (DEBUG) {
            System.out.println("divisor: " + divisor + ", minNumber: " + minNumber);
        }
        wordBuilder.append(toEnglish(number / minNumber));
        wordBuilder.append(SPACE).append(placeValues.get(divisor));
        wordBuilder.append(SPACE).append(toEnglish(number % minNumber));
        return wordBuilder.toString();
    }

    /**
     *Returns the word representation of the number.
     * @param number
     * @return
     */
    public String toEnglish(final long number) {
        if (DEBUG) {
            System.out.println("number: " + number);
        }
        final StringBuilder wordBuilder = new StringBuilder();
        int digits = Numbers.countDecimalDigits(number);
        switch (digits) {
            case 1:
                wordBuilder.append(UNITS[Numbers.toInt(number)]);
                break;
            case 2:
                wordBuilder.append(twoDigitEnglish(Numbers.toInt(number)));
                break;
            default:
                int divisor = findDivisorDigit(digits);
                long minNumber = Numbers.firstNDigitNumber(divisor);
                if (DEBUG) {
                    System.out.println("divisor: " + divisor + ", minNumber: " + minNumber);
                }
                wordBuilder.append(toEnglish(number / minNumber));
                wordBuilder.append(SPACE).append(placeValues.get(divisor));
                wordBuilder.append(SPACE).append(toEnglish(number % minNumber));
                break;
        }

        return wordBuilder.toString();
    }

}
