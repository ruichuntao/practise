package rui.bean;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack = new Stack<>();
    //    Stack<Integer> min = new Stack<>();
    int min = Integer.MAX_VALUE;

    public MinStack() {

    }

    public void push(int x) {
        min = Math.min(min, x);
        stack.push(x);
        stack.push(min);
//        if (min.isEmpty())
//            min.push(x);
//        else if (x <= min.peek())
//            min.push(x);
    }

    public void pop() {
        stack.pop();
        stack.pop();
        if (stack.isEmpty())
            min = Integer.MAX_VALUE;
        else
            min = stack.peek();
//        if (stack.peek().equals(min.peek())) {
//            stack.pop();
//            min.pop();
//        } else
//            stack.pop();
    }

    public int top() {
        int temp = stack.pop();
        int ans = stack.peek();
        stack.push(temp);
//        return stack.peek();
        return ans;
    }

    public int getMin() {
//        return min.peek();
        return stack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
//        System.out.println(minStack.getMin());
        minStack.push(2147483646);
//        System.out.println(minStack.getMin());
        minStack.push(2147483647);
//        System.out.println(minStack.getMin());
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        System.out.println("=-----测试结束-----=");

        minStack.push(3);
        System.out.println(minStack.getMin());

        minStack.push(4);
        System.out.println(minStack.getMin());

        minStack.push(3);
        System.out.println(minStack.getMin());

        minStack.push(2);
        System.out.println(minStack.getMin());
        minStack.push(-2);
        System.out.println(minStack.getMin());
        minStack.push(3);
        System.out.println(minStack.getMin());
        minStack.push(0);
        System.out.println(minStack.getMin());
        System.out.println("----min end----");
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("----pop 1----");
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("----pop 2----");

        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("----pop 3----");

        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("----pop 4----");

        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("----pop 5----");
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("----pop 6----");
    }
}
