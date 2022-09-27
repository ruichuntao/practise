package rui.google;

//
//2
//2 4 5
//10 10 100 30
//80 50 10 50
//3 2 3
//80 80
//15 50
//20 10

//Case #1: 250

import java.util.*;

public class Solution {


    //    Definition of Interval:
    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    public List<Interval> insert(List<Interval> intervals, Interval inter) {
        // write your code here
        List<Interval> ans = new ArrayList<>();
        Collections.sort(intervals, (a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);
        int n = intervals.size();
        int s, e, sp = -1, ep = -1;
        int i = 0;
        for (; i < n; i++) {
            Interval cur = intervals.get(i);
            if (sp == -1) {
                if (inter.start <= cur.start) {
                    sp = i;
                    s = inter.start;
                } else if (inter.start <= cur.end) {
                    sp = i;
                    s = cur.start;
                } else {
                    ans.add(cur);
                }
            }
            if (inter.end <= cur.start) {
                ep = i;
                e = inter.end;
                break;
            } else if (inter.end <= cur.end) {
                ep = i;
                e = cur.end;
                break;
            } else {
                ans.add(cur);
            }
        }

        return ans;
    }

    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        int s = 1, n = nums.length;
        long ans = 0;
//        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < n; ) {
            if (nums[i] > s && k > 0) {
                System.out.print(s + ",");
                ans += s;
                s++;
                k--;
            } else {
                s = nums[i] + 1;
                i++;
            }
            if (k == 0) break;
        }
        s = nums[n - 1] + 1;
        while (k > 0) {
            ans += s;
            k--;
            s++;
        }
        return ans;
    }

    int[] ans = new int[12];
    int[] arrows;
    int max = 0;

    void fun(int[] cur, int num) {
        if (num == 0) {
            int sum = 0;
            for (int i = 0; i < 12; i++) {
                if (cur[i] > arrows[i]) sum += i;
            }
            if (sum > max) {
                max = sum;
                System.arraycopy(cur, 0, ans, 0, 12);
            }
            return;
        }
        for (int i = 0; i < 12; i++) {
            cur[i]++;
            fun(cur, num - 1);
            cur[i]--;
        }
    }

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        arrows = aliceArrows;
        fun(new int[12], numArrows);
        return ans;
    }

    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[m].length;
        int[][] arr = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[j][i] = matrix[i][j];
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.transpose(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
