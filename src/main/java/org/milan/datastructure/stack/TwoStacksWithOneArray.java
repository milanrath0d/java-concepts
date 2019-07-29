package org.milan.datastructure.stack;

/**
 * Problem: Implement two stacks with one array
 * Refer {@link @https://www.geeksforgeeks.org/implement-two-stacks-in-an-array/}
 *
 * @author Milan Rathod
 */
public class TwoStacksWithOneArray {

    private static final String STACK_1 = "Stack1";

    private int[] stack;

    private int top1;

    private int top2;

    public TwoStacksWithOneArray(int size) {
        stack = new int[size];
        top1 = -1;
        top2 = size;
    }

    public void push(String stackType, int item) {
        if (isOverflow()) {
            System.out.println(stackType + " is full");
        } else if (stackType.equals(STACK_1)) {
            stack[++top1] = item;
        } else {
            stack[--top2] = item;
        }
    }

    public int pop(String stackType) {
        int item;
        if (isUnderflow(stackType)) {
            System.out.println(stackType + " is empty");
            return Integer.MIN_VALUE;
        } else if (stackType.equals(STACK_1)) {
            return stack[top1--];
        } else {
            return stack[top2++];
        }
    }

    private boolean isOverflow() {
        return top2 - top1 == 1;
    }

    private boolean isUnderflow(String stackType) {
        if (stackType.equals(STACK_1)) {
            return top1 == -1;
        } else {
            return top2 == stack.length;
        }
    }
}
