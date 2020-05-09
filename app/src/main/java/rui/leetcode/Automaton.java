package rui.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Automaton {
    final int START = 1;
    final int SIGNED = 2;
    final int IN_NUM = 3;
    final int END = 4;
    int state = START;
    Map<Integer, int[]> map;
    int sign = 1;
    long ans = 0;

    public Automaton() {
        map = new HashMap<>();
        map.put(START, new int[]{START, SIGNED, IN_NUM, END});
        map.put(SIGNED, new int[]{END, END, IN_NUM, END});
        map.put(IN_NUM, new int[]{END, END, IN_NUM, END});
        map.put(END, new int[]{END, END, END, END});
    }

    public int get_col(char c) {
        if (c == ' ') return 0;
        if (c == '+' || c == '-') return 1;
        if (c >= '0' && c <= '9') return 2;
        return 3;
    }

    public boolean get(char c) {
        state = map.get(state)[get_col(c)];
        if (state == IN_NUM) {
            ans = ans * 10 + c - '0';
            if (sign == 1) {
                ans = Math.min(ans, Integer.MAX_VALUE);
            } else {
                ans = Math.min(ans, -(long) Integer.MIN_VALUE);
            }
        } else if (state == SIGNED)
            sign = c == '+' ? 1 : -1;
        else if (state == END)
            return true;
        return false;
    }

    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        char[] c = str.toCharArray();
        for (char ch : c) {
            if (automaton.get(ch)){
                break;
            }
        }
        return automaton.sign * ((int) automaton.ans);
    }

    public static void main(String[] args) {
//        System.out.println(Automaton.myAtoi("42324"));
//        System.out.println(Automaton.myAtoi("34534634645645"));
//        System.out.println(Automaton.myAtoi("-34534634645645"));
//        System.out.println(Automaton.myAtoi("sddsg 34534634645645"));
//        System.out.println(Automaton.myAtoi("34534634645645 sdfsfdsf"));
//        System.out.println(Automaton.myAtoi("+-5345"));
//        System.out.println(Automaton.myAtoi("-+5345"));
//        System.out.println(Automaton.myAtoi("-5345"));
//        System.out.println(Automaton.myAtoi("+5345"));
    }
}
