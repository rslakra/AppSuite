package com.rslakra.core;

import com.rslakra.core.entity.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: rlakra
 * @Since: 9/11/19 12:24 PM
 */
public class TestCollections {

    void testListVsMap() {
        List<Color> listColors = Arrays.asList(new Color(1, "Red"), new Color(2, "Green"), new Color(3, "Blue"));
        System.out.println(listColors);
        final Map<Integer, Color> mapColors = new HashMap<>();
        listColors.stream().forEach(color -> mapColors.put(color.getId(), color));
        System.out.println(mapColors);
        Map<Integer, Color> colors = listColors.stream().collect(Collectors.toMap(Color::getId, color -> color));
        System.out.println(colors);
    }

    private Set<Long> filledLongSet(int count, Random random) {
        Set<Long> longSet = new HashSet<>();
        for (int ctr = 0; ctr < count; ctr++) {
            longSet.add(Long.valueOf(random.nextInt(10)));
        }

        return longSet;
    }

    void testSetsVsSet() {
        Random random = new Random();
        Set<Long> longSet = filledLongSet(5, random);
        System.out.println(longSet);
        Map<Long, Set<Long>> mapLongSets = new HashMap<>();
        mapLongSets.put(1L, filledLongSet(2, random));
        mapLongSets.put(2L, filledLongSet(3, random));
        mapLongSets.put(3L, filledLongSet(4, random));
        System.out.println(mapLongSets);

        //retain
        mapLongSets.values().forEach(s -> s.retainAll(longSet));
        System.out.println("After Retain");
        System.out.println(mapLongSets);
        Map<Long, Set<Long>> setTemp = new HashMap<>(mapLongSets);

        System.out.println("Remove keys, which value set is empty.");
        //remove entry that value set is empty
        setTemp.forEach((k, v) -> {
            if (v.size() == 0) {
                mapLongSets.remove(k);
            }
        });
        System.out.println(mapLongSets);
    }

    public void convertToMapFromObjectArrayList() {
        System.out.println("convertToMapFromObjectArrayList");
        Object[] objects = new Object[]{
            new Object[]{Long.valueOf(1), Integer.valueOf(10)}
        };

        List<Object[]> listObjects = Arrays.stream(objects).map(entry -> (Object[]) entry).collect(Collectors.toList());
        Map<Long, Integer>
            records =
            listObjects.stream().collect(Collectors.toMap(key -> Long.valueOf(key[0].toString()),
                                                          value -> Integer.valueOf(value[1].toString())));
        System.out.println(records);

        //multi-records
        objects = new Object[]{
            new Object[]{Long.valueOf(333533902L), Integer.valueOf(2)},
            new Object[]{Long.valueOf(7308537810L), Integer.valueOf(1)},
            new Object[]{Long.valueOf(333533993L), Integer.valueOf(2)},
            };

        listObjects = Arrays.stream(objects).map(entry -> (Object[]) entry).collect(Collectors.toList());

        records =
            listObjects.stream().collect(Collectors.toMap(key -> Long.valueOf(key[0].toString()),
                                                          value -> Integer.valueOf(value[1].toString())));
        System.out.println(records);

        // no records
        objects = new Object[0];
        listObjects = Arrays.stream(objects).map(entry -> (Object[]) entry).collect(Collectors.toList());
        records =
            listObjects.stream().collect(Collectors.toMap(key -> Long.valueOf(key[0].toString()),
                                                          value -> Integer.valueOf(value[1].toString())));
        System.out.println(records);
    }

    private final String toKey(final String type, final String parentType, Long parentId) {
        return (String.format("%s-%s-%d", type, parentType, parentId));
    }

    public void convertObjectArrayListToMap() {
        System.out.println("convertObjectArrayListToMap");
        //multi-records
        List<Object[]> listObjects = new ArrayList<Object[]>();
        listObjects.add(new Object[]{"WOEID", "CAMPAIGN", 333533902L, Integer.valueOf(2)});
        listObjects.add(new Object[]{"WOEID", "ADGROUP", 7308537810L, Integer.valueOf(1)});
        listObjects.add(new Object[]{"WOEID", "CAMPAIGN", 333533993L, Integer.valueOf(2)});

        Map<Long, Integer>
            records =
            listObjects.stream().collect(Collectors.toMap(key -> Long.valueOf(key[2].toString()),
                                                          value -> Integer.valueOf(value[3].toString())));
        System.out.println(records);

        System.out.println();
        Map<String, Integer>
            keyRecords =
            listObjects.stream().collect(
                Collectors.toMap(key -> toKey(key[0].toString(), key[1].toString(), Long.valueOf(key[2].toString())),
                                 value -> Integer.valueOf(value[3].toString())));
        System.out.println(keyRecords);
    }

    public static void main(String[] args) {
        TestCollections testCollections = new TestCollections();
        testCollections.testListVsMap();
        System.out.println();
        testCollections.testSetsVsSet();
        testCollections.convertToMapFromObjectArrayList();
        testCollections.convertObjectArrayListToMap();
    }

}
