package com.rslakra.appsuite.questions.leetcode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * @author Rohtash Lakra
 * @created 5/1/21 8:59 AM
 */
public class LC2AddTwoNumbers {

    public static class ListNode {

        private int value;
        private ListNode next;

        ListNode() {
        }

        ListNode(int value) {
            this.value = value;
        }

        ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public ListNode getNext() {
            return next;
        }

        @Override
        public String toString() {
            final StringBuilder sBuilder = new StringBuilder();
            ListNode temp = this;
            while (temp != null) {
                sBuilder.append(temp.value);
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
        return (listNode == null ? 0 : listNode.value);
    }

    private static ListNode addLast(ListNode rootNode, int value) {
        if (rootNode == null) {
            return new ListNode(value);
        } else {
            rootNode.next = addLast(rootNode.next, value);
            return rootNode;
        }
    }

    private static ListNode addTail(ListNode parentNode, int value) {
        parentNode.next = new ListNode(value);
        return parentNode.next;
    }

    /**
     * https://leetcode.com/problems/add-two-numbers/
     *
     * @param left
     * @param right
     * @return
     */
    public static ListNode addTwoNumbers(ListNode left, ListNode right) {
        ListNode headNode = null;
        ListNode tailNode = null;
        int carry = 0;
        while (left != null || right != null) {
            int sum = getValue(left) + getValue(right) + carry;
            carry = sum / 10;
            sum = sum % 10;
            if (headNode == null) {
                headNode = new ListNode(sum);
                tailNode = headNode;
            } else {
                tailNode = addTail(tailNode, sum);
            }
            if (left != null) {
                left = left.next;
            }
            if (right != null) {
                right = right.next;
            }
        }

        if (carry > 0) {
            tailNode = addTail(tailNode, carry);
        }

        return headNode;
    }
}
