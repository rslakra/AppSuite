package com.rslakra.core;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestList {

    /**
     *
     */
    @Test
    public void testListPartition() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int subListSize = 3;
        Collection<List<Integer>> subLists = Utils.INSTANCE.partitionListBySize(intList, subListSize);
        System.out.println("subLists count:" + subLists.size());
        System.out.println("subLists:" + subLists);
        System.out.println();

        intList = Arrays.asList(1, 2, 3);
        subLists = Utils.INSTANCE.partitionListBySize(intList, subListSize);
        System.out.println("subLists count:" + subLists.size());
        System.out.println("subLists:" + subLists);
        System.out.println();

        intList = Arrays.asList();
        subLists = Utils.INSTANCE.partitionListBySize(intList, subListSize);
        System.out.println("subLists count:" + subLists.size());
        System.out.println("subLists:" + subLists);
        System.out.println("subLists isEmpty:" + subLists.isEmpty());
        System.out.println();

        intList = null;
        subLists = Utils.INSTANCE.partitionListBySize(intList, subListSize);
        System.out.println("subLists:" + subLists);
    }

}
