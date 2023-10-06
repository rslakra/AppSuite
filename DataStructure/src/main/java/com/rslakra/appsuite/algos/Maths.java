package com.rslakra.appsuite.algos;

/**
 * @author Rohtash Lakra
 * @created 9/9/23 6:13 PM
 */
public class Maths {

    /**
     * @param first
     * @param second
     * @return
     */
    public static int gcd(int first, int second) {
        //repeat until both are not same
        while (first != second) {
            // replace the larger number by its difference with the smaller number
            if (first < second) {
                second = second - first;
            } else {
                first = first - second;
            }
        }

        return first; // or return second as both are same
    }

    /**
     * @param first
     * @param second
     * @return
     */
    public static int gcdEuclidean(int first, int second) {
        // swap numbers if first < second
        if (first < second) {
            int temp = first;
            first = second;
            second = temp;
        }

        // repeat until the remainder is not zero
        while (second != 0) {
            int remainder = first % second;
            // swap first with second, and the latter with remainder
            first = second;
            second = remainder;
        }

        return first;
    }


    /**
     * @param first
     * @param second
     * @return
     */
    public static int gcdEuclideanRecursively(int first, int second) {
        // exist point
        if (second == 0) {
            return first;
        }

        // swap numbers if first < second
        if (first < second) {
            int temp = first;
            first = second;
            second = temp;
        }

        int remainder = first % second;
        // swap first with second, and the latter with remainder
        first = second;
        second = remainder;

        // repeat until the remainder is not zero
        return gcdEuclideanRecursively(first, second);
    }


}
