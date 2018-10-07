package org.milan.hackerearth;

import java.util.Stack;

/**
 * Balance Brackets
 *
 * @author Milan Rathod
 */
public class BalanceBrackets {

    public boolean checkBalance(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else if ((ch == ')') && (!stack.isEmpty())) {

                if (((char) stack.peek() == '(' && ch == ')')) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                if (ch == ')') {
                    return false;
                }
            }
        }
        if (stack.isEmpty())
            return true;
        else
            return false;
    }

}