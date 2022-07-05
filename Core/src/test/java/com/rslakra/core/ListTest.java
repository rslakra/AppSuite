package com.rslakra.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(ListTest.class);

    /**
     *
     */
    @Test
    public void testListPartition() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int subListSize = 3;
        Collection<List<Integer>> subLists = Utils.INSTANCE.partitionListBySize(intList, subListSize);
        LOGGER.debug("subLists count:" + subLists.size());
        LOGGER.debug("subLists:" + subLists);

        intList = Arrays.asList(1, 2, 3);
        subLists = Utils.INSTANCE.partitionListBySize(intList, subListSize);
        LOGGER.debug("subLists count:" + subLists.size());
        LOGGER.debug("subLists:" + subLists);

        intList = Arrays.asList();
        subLists = Utils.INSTANCE.partitionListBySize(intList, subListSize);
        LOGGER.debug("subLists count:" + subLists.size());
        LOGGER.debug("subLists:" + subLists);
        LOGGER.debug("subLists isEmpty:" + subLists.isEmpty());

        intList = null;
        subLists = Utils.INSTANCE.partitionListBySize(intList, subListSize);
        LOGGER.debug("subLists:" + subLists);
    }

}
