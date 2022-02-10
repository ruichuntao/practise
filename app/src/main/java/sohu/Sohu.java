package sohu;

import java.util.Stack;

public class Sohu {
    public boolean canBeValid(String s, String locked) {
        char[] c = s.toCharArray();
        char[] cc = locked.toCharArray();
        int n = c.length;
        if (n % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (cc[i] == '0') {
                stack.push('*');
            } else {
                if (c[i] == '(') {
                    stack.push('(');
                } else {
                    if (!stack.isEmpty() && (stack.peek() == '(' || stack.peek() == '*')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            if (cc[i] == '0') {
                stack.push('*');
            } else {
                if (c[i] == ')') {
                    stack.push(')');
                } else {
                    if (!stack.isEmpty() && (stack.peek() == ')' || stack.peek() == '*')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int t = 0;
        for(int i = 1; i <= 2; i++) t^=i;
        System.out.println(t);
    }

}
