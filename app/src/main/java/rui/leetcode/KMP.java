package rui.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
    int[] next;

    public void build(String pattern) {
        char[] p = pattern.toCharArray();
        int n = p.length;
        next = new int[n + 1];
        int i = 0;
        int j = next[0] = -1;
        while (i < n) {
            if (j == -1 || p[i] == p[j]) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
    }



    public List<Integer> match(String pattern, String text) {
        List<Integer> list = new ArrayList<>();
        char[] p = pattern.toCharArray();
        char[] t = text.toCharArray();
        int n = p.length;
        int m = t.length;
        build(pattern);
        for (int i = 0, j = 0; i < m; ++i) {
            while (j > 0 && t[i] != p[j]) j = next[j];
            if (t[i] == p[j]) ++j;
            if (j == n) {
                list.add(i - n + 1);
                j = next[j];
            }
        }
        return list;
    }

    public String longestPrefix(String s) {
        build(s);
        int n = s.length();
        String ans = s.substring(0, next[n]);
        return ans;
    }

    public static void main(String[] args) {
        KMP p = new KMP();
        System.out.println(p.match("issip", "mississippi"));
    }

}
