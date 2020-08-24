package rui.leetcode;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            stack.push(x);
        } else {
            int min = stack.peek();
            if (x < min) {
                stack.push(x);
                stack.push(x);
            } else {
                stack.push(x);
                stack.push(min);
            }
        }
    }

    public void pop() {
        stack.pop();
        stack.pop();
    }

    public int top() {
        stack.pop();
        return stack.pop();
    }

    public int getMin() {
        return stack.peek();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(0);
        stack.push(3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }
}