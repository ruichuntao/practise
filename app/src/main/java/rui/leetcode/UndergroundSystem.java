package rui.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class UndergroundSystem {

    Map<String, Map<Integer, Integer>> mapIn;
    Map<String, Map<Integer, Integer>> mapOut;

    public UndergroundSystem() {
        mapIn = new TreeMap<>();
        mapOut = new TreeMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        Map<Integer, Integer> map;
        if (mapIn.containsKey(stationName)) {
            map = mapIn.get(stationName);
        } else {
            map = new HashMap<>();
            mapIn.put(stationName, map);
        }
        map.put(id, t);
    }

    public void checkOut(int id, String stationName, int t) {
        Map<Integer, Integer> map;
        if (mapOut.containsKey(stationName)) {
            map = mapOut.get(stationName);
        } else {
            map = new HashMap<>();
            mapOut.put(stationName, map);
        }
        map.put(id, t);
    }

    public double getAverageTime(String startStation, String endStation) {
        Map<Integer, Integer> start = mapIn.get(startStation);
        Map<Integer, Integer> end = mapOut.get(endStation);
        int cnt = 0;
        int sum = 0;
        for (Integer key : start.keySet()) {
            if (end.containsKey(key)) {
                cnt++;
                sum += end.get(key) - start.get(key);
            }
        }
        return ((double)sum / (double)cnt);
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));       // 返回 14.0。从 "Paradise"（时刻 8）到 "Cambridge"(时刻 22)的行程只有一趟
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));          // 返回 11.0。总共有 2 躺从 "Leyton" 到 "Waterloo" 的行程，编号为 id=45 的乘客出发于 time=3 到达于 time=15，编号为 id=27 的乘客出发于 time=10 到达于 time=20。所以平均时间为 ( (15-3) + (20-10) ) / 2 = 11.0
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));          // 返回 11.0
        undergroundSystem.checkOut(10, "Waterloo", 38);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
    }
}



/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */