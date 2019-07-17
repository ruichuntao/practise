package com.example.lib;

import java.util.Stack;

public class MyClass {

    public static void main(String[] args) {
        System.out.println(MyClass.partenMatch("2-[(1+2)*2]["));
    }

    private static boolean partenMatch(String exp) {

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < exp.length(); i++) {
            switch (exp.charAt(i)) {
                case '{':
                case '(':
                case '[':
                    stack.push(exp.charAt(i));
                    break;
                case '}':
                case ')':
                case ']':
                    if (stack.isEmpty()) return false;
                    if (!MyClass.match(stack.pop(), exp.charAt(i))) return false;
                    break;
            }
        }
        return stack.isEmpty();
    }

    private static boolean match(char strright, char strleft) {
        switch (strright) {
            case '{':
                if (strleft == '}') return true;
                break;
            case '(':
                if (strleft == ')') return true;
                break;
            case '[':
                if (strleft == ']') return true;
                break;
            default:
                break;
        }
        return false;
    }

}
