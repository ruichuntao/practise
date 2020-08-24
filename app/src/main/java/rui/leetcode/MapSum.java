package rui.leetcode;

import java.util.HashMap;

public class MapSum {
    int sum = 0;
    MapSum[] next = new MapSum[128];
    HashMap<String, Integer>  map = new HashMap<>();

    /** Initialize your data structure here. */
    public MapSum() {

    }

    public void insert(String key, int val) {
        int pre = 0;
        if (map.containsKey(key)) {
            pre = map.get(key);
        }
        map.put(key, val);
        MapSum root = this;
        char[] c = key.toCharArray();
        for (char ch : c) {
            if (root.next[ch] == null) {
                root.next[ch] = new MapSum();
            }
            root = root.next[ch];
            root.sum += val - pre;
        }

    }

    public int sum(String prefix) {
        MapSum root = this;
        char[] c = prefix.toCharArray();
        for (char ch : c) {
            if (root.next[ch] == null) return 0;
            root = root.next[ch];
        }
        return root.sum;
    }

    public static void main(String[] args) {
        MapSum sum = new MapSum();
        sum.insert("aa",3);
        System.out.println(sum.sum("a"));
        sum.insert("aa",2);
        System.out.println(sum.sum("a"));
    }
}
