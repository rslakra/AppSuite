package com.rslakra.appsuite.questions.leetcode;

import java.util.Stack;

/**
 * @author Rohtash Lakra
 * @created 9/14/23 12:24 PM
 */
public class LC155MinStack {

    static class Pair {

        int element;
        int min;

        Pair(int element, int min) {
            this.element = element;
            this.min = min;
        }
    }

    private final Stack<Pair> stack = new Stack<>();
    private int min = Integer.MAX_VALUE;

    public void push(int element) {
        min = Math.min(element, min);
        stack.push(new Pair(element, min));
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            if (stack.isEmpty()) {
                min = Integer.MAX_VALUE;
            } else {
                min = stack.peek().min;
            }
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }

        return stack.peek().element;
    }

    public int getMin() {
        if (stack.isEmpty()) {
            return -1;
        }

        return stack.peek().min;
    }

}
