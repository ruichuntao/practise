package rui.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CityBusRoutes {
    int MAXN = 501;
    // 路线
    int[] v = new int[MAXN];

    public int numBusesToDestination(int[][] routes, int S, int T) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int m = routes.length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j : routes[i]) {
                map.computeIfAbsent(j, k -> new ArrayList<>());
                map.get(j).add(i);
                if (j == S) {
                    // 车站，路线，步数
                    queue.add(new int[]{j, i});
                }
            }
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int s = queue.size();
            for (int i = 0; i < s; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int r = cur[1];
                if (x == T) {
                    return step;
                }
                for (int nr : map.get(x)) {
                    if (v[nr] == 1) continue;
                    v[nr] = 1;
                    for (int nx : routes[nr]) {
                        queue.add(new int[]{nx, nr});
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        CityBusRoutes r = new CityBusRoutes();
        int[][] route = new int[][]{
                {1, 9, 12, 20, 23, 24, 35, 38},
                {10, 21, 24, 31, 32, 34, 37, 38, 43},
                {10, 19, 28, 37},
                {8},
                {14, 19},
                {11, 17, 23, 31, 41, 43, 44},
                {21, 26, 29, 33},
                {5, 11, 33, 41},
                {4, 5, 8, 9, 24, 44}};
        System.out.println(r.numBusesToDestination(route, 37, 28));
    }

}
