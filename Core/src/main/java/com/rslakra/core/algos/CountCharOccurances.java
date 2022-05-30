package com.rslakra.core.algos;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Rohtash Lakra
 * @Since 3/18/20 10:09 AM
 */
public class CountCharOccurances {

    public void printCharOccurancesCount(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        System.out.println(map);

//        for (Map.Entry m : map.entrySet()) {
//            System.out.println(m.getKey() + " occurred " + m.getValue());
//        }

    }

    public static void main(String[] args) {
        CountCharOccurances count = new CountCharOccurances();
        count.printCharOccurancesCount("verizon media");
    }
}
