package rui.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    MyCalendar() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar() ;
        calendar.book(10,20);
        calendar.book(1,10);
        calendar.book(20,30);
        calendar.book(25,30);
    }
}
