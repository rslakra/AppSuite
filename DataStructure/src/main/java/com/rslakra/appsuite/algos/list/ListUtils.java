package com.rslakra.appsuite.algos.list;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/18/23 7:26 PM
 */
public enum ListUtils {

    INSTANCE;


    /**
     * @param nums
     * @return
     */
    public static ListNode buildLinkedList(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode previous = new ListNode(-1);
        ListNode head = new ListNode(nums[0]);
        previous.next = head;
        for (int i = 1; i < nums.length; i++) {
            head.next = new ListNode(nums[i]);
            head = head.next;
        }
        return previous.next;
    }

    /**
     * @param head
     */
    public static void printList(ListNode head) {
        ListNode temp = head;
        System.out.println();
        while (temp != null) {
            System.out.print(temp.getValue() + "\t");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Creates a singly linked list.
     *
     * @param listValues
     * @return
     */
    public static <T> ListNode createSinglyLinkedList(List<T> listValues) {
        if (listValues == null || listValues.size() == 0) {
            throw new IllegalArgumentException("Please pass in a valid listValues to create a singly linked list.");
        }

        ListNode head = new ListNode(listValues.get(0));
        ListNode current = head;
        for (int i = 1; i < listValues.size(); i++) {
            current.next = new ListNode(listValues.get(i));
            current = current.next;
        }

        return head;
    }
}
