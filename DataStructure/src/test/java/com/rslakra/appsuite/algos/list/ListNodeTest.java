package com.rslakra.appsuite.algos.list;

import com.rslakra.appsuite.algos.AlgoUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/19/23 2:18 PM
 */
public class ListNodeTest {

    @Test
    public void testCreateSinglyLinkedList() {
        System.out.println("0x7F: " + 0x7F);
        List<Integer> values = AlgoUtils.randomIntArrayGenerator(10, 20);
        AlgoUtils.printArray(values);
        ListNode head = ListNode.createSinglyLinkedList();
        System.out.println(head);
        System.out.println("The end.");
    }
}
