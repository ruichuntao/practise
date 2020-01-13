package rui.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MapSum {

    /**
     * Initialize your data structure here.
     */
    Map<String, Integer> map;

    // todo  677. 键值映射 前缀树
    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        if (key.isEmpty())
            return;
        for (int i = 1; i <= key.length(); i++) {
            String temp = key.substring(0, i);
            map.put(temp, map.getOrDefault(temp, 0) + val);
        }
    }

    public int sum(String prefix) {
        if (!map.containsKey(prefix))
            return 0;
        return map.get(prefix);
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("aa", 3);
        System.out.println(mapSum.sum("a"));
        mapSum.insert("aa", 2);
        System.out.println(mapSum.sum("a"));
    }
}
