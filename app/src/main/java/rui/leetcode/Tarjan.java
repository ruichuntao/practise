package rui.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Tarjan算法
public class Tarjan {

    List<List<Integer>> map = new ArrayList<>(); // 图
    List<List<Integer>> res = new ArrayList<>(); // 割边结果
    int[] dfn; // 当前节点时间戳，下标表示节点，值为时间戳
    int[] low; // 当前节点的父节点时间戳，下标表示节点，值为时间戳
    int[] v; // 当前是否访问过
    int time = 1; // 时间戳
    // tarjan 牛逼

    public void dfs(int cur, int pre) {
        dfn[cur] = low[cur] = time++;
        for (int next : map.get(cur)) {
            if (next == pre) continue;
            if (dfn[next] == 0) {
                dfs(next, cur);
                low[cur] = Math.min(low[cur], low[next]);
                if (low[next] > dfn[cur]) {
                    res.add(Arrays.asList(cur, next));
                }
            } else {
                low[cur] = Math.min(dfn[next], low[cur]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> c) {
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        for (int i = 0; i < c.size(); i++) {
            List<Integer> tmp = c.get(i);
            map.get(tmp.get(0)).add(tmp.get(1));
            map.get(tmp.get(1)).add(tmp.get(0));
        }
        dfn = new int[n];
        low = new int[n];
        dfs(0, -1);
        return res;
    }

    public static void main(String[] args) {
        Tarjan s = new Tarjan();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0, 1));
        list.add(Arrays.asList(0, 2));
        list.add(Arrays.asList(2, 1));
        list.add(Arrays.asList(3, 1));
        System.out.println(s.criticalConnections(4, list));
    }

}
