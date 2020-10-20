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

    public static void main(String[] args) {

    }
}
