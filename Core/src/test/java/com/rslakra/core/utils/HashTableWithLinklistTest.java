package com.rslakra.core.utils;

/**
 * @author Rohtash Lakra
 * @created 11/12/20 11:42 AM
 */
public class HashTableWithLinklistTest {

    public static void main(String[] args) {
        int[] values = {2, 3, 4, 5, 2, 4, 5, 1};
        HashTableWithLinklist<String, Integer> hashTable = new HashTableWithLinklist<>();
        for (int i = 0; i < values.length; i++) {
            hashTable.put(String.valueOf(values[i]), values[i]);
        }

        for (int i = 0; i < values.length; i++) {
            System.out.println(hashTable.get(String.valueOf(values[i])));
        }
    }
}
