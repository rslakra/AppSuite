package com.rslakra.appsuite.questions.interviewbit;

import java.util.Stack;

/**
 * Problem Description
 * <p>
 * <p>
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time. push(x) -- Push
 * element x onto stack. pop() -- Removes the element on top of the stack. top() -- Get the top element. getMin() --
 * Retrieve the minimum element in the stack. Note that all the operations have to be constant time operations.
 * <p>
 * Questions to ask the interviewer :
 * <p>
 * Q: What should getMin() do on empty stack?
 * <p>
 * A: In this case, return -1.
 * <p>
 * <p>
 * Q: What should pop do on empty stack?
 * <p>
 * A: In this case, nothing.
 * <p>
 * <p>
 * <p>
 * Q: What should top() do on empty stack?
 * <p>
 * A: In this case, return -1
 * <p>
 * NOTE : If you are using your own declared global variables, make sure to clear them out in the constructor.
 *
 * @author Rohtash Lakra
 * @created 9/14/23 12:06 PM
 */
public class MinStack {

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
