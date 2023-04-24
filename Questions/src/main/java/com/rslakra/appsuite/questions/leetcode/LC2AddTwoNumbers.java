package com.rslakra.appsuite.questions.leetcode;

/**
 * @author Rohtash Lakra
 * @created 5/1/21 8:59 AM
 */
public class LC2AddTwoNumbers {

    public static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            final StringBuilder sBuilder = new StringBuilder();
            ListNode temp = this;
            while (temp != null) {
                sBuilder.append(temp.val);
                if (temp.next != null) {
                    sBuilder.append(", ");
                }
                temp = temp.next;
            }
            return sBuilder.toString();
        }
    }

    public static ListNode buildList(int[] input) {
        ListNode root = null;
        if (input != null) {
            for (int i = 0; i < input.length; i++) {
                root = addLast(root, input[i]);
            }
        }

        return root;
    }

    private static int getValue(ListNode listNode) {
        return (listNode == null ? 0 : listNode.val);
    }

    private static ListNode addLast(ListNode rootNode, int value) {
        if (rootNode == null) {
            return new ListNode(value);
        } else {
            rootNode.next = addLast(rootNode.next, value);
            return rootNode;
        }
    }

    /**
     * https://leetcode.com/problems/add-two-numbers/
     *
     * @param left
     * @param right
     * @return
     */
    public static ListNode addTwoNumbers(ListNode left, ListNode right) {
        ListNode listNode = null;
        int carry = 0;
        while (left != null || right != null) {
            int sum = getValue(left) + getValue(right) + carry;
            carry = sum / 10;
            sum = sum % 10;
            listNode = addLast(listNode, sum);
            if (left != null) {
                left = left.next;
            }
            if (right != null) {
                right = right.next;
            }
        }

        if (carry > 0) {
            listNode = addLast(listNode, carry);
        }

        return listNode;
    }
}
