package com.rslakra.appsuite.algos.map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 9/19/23 3:08 PM
 */
public enum MapUtils {
    INSTANCE;


    private static class MapEntryComparator<K extends Comparable, V extends Comparable>
        implements Comparator<Map.Entry<K, V>> {

        @Override
        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            int result = (((Comparable<V>) o2.getValue()).compareTo(o1.getValue()));
            // values might be same and can override the key
            if (result == 0) {
                result = (((Comparable<K>) o1.getKey()).compareTo(o2.getKey()));
            }

            return result;
        }
    }


    /**
     * Sorts the map by value.
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
//        list.sort(Map.Entry.comparingByValue());

//        list.sort(new Comparator<Map.Entry<K, V>>() {
//            @Override
//            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
//                return ((Comparable<V>) o2.getValue()).compareTo(o1.getValue());
//            }
//        });

        list.sort(new MapEntryComparator<K, V>());

//        Stream<Map.Entry<K,V>> sorted =
//            map.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue());

        Map<K, V> results = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            results.put(entry.getKey(), entry.getValue());
        }

        return results;
    }
}
