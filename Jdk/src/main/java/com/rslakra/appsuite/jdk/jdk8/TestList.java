package com.rslakra.appsuite.jdk.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestList {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(TestList.class);

    public void testListPartition() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int subListSize = 3;
        Collection<List<Integer>> subLists = JavaUtils.INSTANCE.partitionListBySize(intList, subListSize);
        LOGGER.debug("subLists count:" + subLists.size());
        LOGGER.debug("subLists:" + subLists);

        intList = Arrays.asList(1, 2, 3);
        subLists = JavaUtils.INSTANCE.partitionListBySize(intList, subListSize);
        LOGGER.debug("subLists count:" + subLists.size());
        LOGGER.debug("subLists:" + subLists);

        intList = Arrays.asList();
        subLists = JavaUtils.INSTANCE.partitionListBySize(intList, subListSize);
        LOGGER.debug("subLists count:" + subLists.size());
        LOGGER.debug("subLists:" + subLists);
        LOGGER.debug("subLists isEmpty:" + subLists.isEmpty());

        intList = null;
        subLists = JavaUtils.INSTANCE.partitionListBySize(intList, subListSize);
        LOGGER.debug("subLists:" + subLists);
    }

    /**
     *
     */
    public void testList2Map() {
        List<TestObject> testObjects = new ArrayList<>();
        testObjects.add(new TestObject(1, "First"));
        testObjects.add(new TestObject(2, "Second"));
        testObjects.add(new TestObject(1, "One"));

        final Map<Integer, List<TestObject>> testObjectsMap = new HashMap<>();
        testObjects.forEach(testObject -> {
            List<TestObject> testObjectList = testObjectsMap.getOrDefault(testObject.getId(), new ArrayList<>());
            testObjectsMap.putIfAbsent(testObject.getId(), testObjectList);
            testObjectList.add(testObject);
        });

        LOGGER.debug("{}", testObjectsMap);
    }

    public void testList2Map2() {
        List<TestObject> testObjects = new ArrayList<>();
        testObjects.add(new TestObject(1, "First"));
        testObjects.add(new TestObject(2, "Second"));
        testObjects.add(new TestObject(1, "One"));

        final Map<Integer, List<TestObject>> testObjectsMap = new HashMap<>();
        Map<Integer, List<TestObject>>
            testObjectMap =
            testObjects.stream().collect(Collectors.groupingBy(TestObject::getId));
        LOGGER.debug("{}", testObjectMap);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestList testList = new TestList();
//        testList.testListPartition();
        testList.testList2Map();
        testList.testList2Map2();
    }

}
