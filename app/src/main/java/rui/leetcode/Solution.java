package rui.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    //    long[][] dp;
//    long mod = (long) 1e9 + 7;
//
//    public int numberWays(List<List<Integer>> hats) {
//        int n = hats.size();
//        dp = new long[41][(1 << 10) + 1];
//        dp[0][0] = 1;
//        List<List<Integer>> list = new ArrayList<>();
//        for (int i = 0; i < 41; i++) {
//            list.add(new ArrayList<>());
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < hats.get(i).size(); j++) {
//                list.get(hats.get(i).get(j) - 1).add(i);
//            }
//        }
//
//        for (int i = 1; i < 41; i++) {
//            for (int j = 0; j < (1 << 10) + 1; j++) {
//                // 1 不带帽子情况直接转换
//                dp[i][j] = dp[i - 1][j];
//                // 2 从当前帽子中找一个人
//                for (int x : list.get(i)) {
//                    if (((1 << x) & j) == 0) continue;
//                    dp[i][j] += dp[i - 1][j - (1 << x)];
//                }
//            }
//        }
//        for (long[] longs : dp) {
//            System.out.println(Arrays.toString(longs));
//        }
//        return (int) dp[40][(1 << n) - 1];
//    }
//
//    public int numberWays2(List<List<Integer>> hats) {
//        int n = hats.size();
//        int N = 1000000007;
//
//        int maxHats = 40;
////        for (int i = 0; i < n; i++) {
////            for (int h : hats.get(i)) {
////                maxHats = Math.max(maxHats, h);  //找到帽子最大的索引，只需考虑此帽子之前的情况
////            }
////        }
//
//        List<List<Integer>> htp = new ArrayList<>();
//        for (int i = 0; i < maxHats; i++) {
//            htp.add(new ArrayList<>());
//        }
//        //htp.get(j) 表示第j顶帽子被那些人喜欢
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < hats.get(i).size(); j++) {
//                htp.get(hats.get(i).get(j) - 1).add(i); //吐了
//            }
//        }
//
//        int[][] dp = new int[maxHats + 1][1 << 10];
//        //dp[i][j] 表示前i个人(分帽子状态为j)的情况下方案数
//        //(j考虑为一个二进制数，第k位上的01表示第k个人有无分到帽子。j就是一个方案，总方案数为2^n即1<<n）
//
//        dp[0][0] = 1; //回到dp[0][0]状态时为1个方案数！
//        for (int i = 1; i <= maxHats; i++) {
//            for (int j = 0; j < (1 << n); j++) {
//                //1.第i个帽子不分配的情况
//                dp[i][j] = dp[i - 1][j];
//                //2.第i个帽子分给第k个人的情况
//                //此时需要满足第k个人喜欢第i顶帽子,就是从喜欢第i顶帽子人的集合中找出这个人
//                //判断第k个人在二进制方案j的第k个位数上是否为1,为1表示分配给这个人是一种方案，就可以加入这个方案
//                for (int k : htp.get(i - 1)) {
//                    if ((j & (1 << k)) != 0) {
//                        dp[i][j] += dp[i - 1][j - (1 << k)]; //状态转移 加上这个不同方案
//                        // [j-(1<<k)] 表示将k这个人在方案j上的第k位的1去掉即前[i-1]个帽子不再分配给第k个人
//                        // [j-(1<<k)] 写法等同于 [j^(1<<k)]
//                        dp[i][j] %= N;
//                    }
//                }
//            }
//        }
//        for (int[] longs : dp) {
//            System.out.println(Arrays.toString(longs));
//        }
//        return dp[maxHats][(1 << n) - 1];
//
//    }
//
//    int m;
//    int n;
//    int[][] v;
//    char[][] grid;
//
//    boolean dfs(int x, int y, int step) {
//        if (x < 0 || y < 0 || x == m || y == n) return false;
//        if (v[x][y] == 1) {
//            return step >= 4;
//        }
//        boolean ans = false;
//        v[x][y] = 1;
//        if (x + 1 < m && grid[x + 1][y] == grid[x][y]) {
//            ans |= dfs(x + 1, y, step + 1);
//        }
//        if (y + 1 < n && grid[x][y + 1] == grid[x][y]) {
//            ans |= dfs(x, y + 1, step + 1);
//        }
//        if (x - 1 >= 0 && grid[x - 1][y] == grid[x][y]) {
//            ans |= dfs(x - 1, y, step + 1);
//        }
//        if (y - 1 >= 0 && grid[x][y - 1] == grid[x][y]) {
//            ans |= dfs(x, y - 1, step + 1);
//        }
//        return ans;
//    }
//
//    public boolean containsCycle(char[][] grid) {
//        m = grid.length;
//        n = grid[0].length;
//        v = new int[m][n];
//        this.grid = grid;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (v[i][j] == 0 && dfs(i, j, 0)) return true;
//            }
//        }
//        return false;
//    }
//

//    int maxn = 500 + 5;
//    int n;
//    int[][] dp = new int[maxn][maxn];
//    int[] sum;
//    int[] s;
//
//    int dfs(int l, int r) {
//        if (l > r) return 0;
//        if (dp[l][r] != 0) return dp[l][r];
//        for (int i = l; i < r; i++) {
//            int x = sum[i] - sum[l];
//            if (x == 0) x = s[l];
//            int y = sum[r] - sum[i];
//            if (x == y) {
//                dp[l][r] = Math.max(dp[l][r], Math.max(dfs(l, i) + x, dfs(i + 1, r) + x));
//            } else {
//                dp[l][r] = Math.max(dp[l][r], Math.min(dfs(l, i) + x, dfs(i + 1, r) + y));
//            }
//        }
//        return dp[l][r];
//    }
//
//    public int stoneGameV(int[] s) {
//        n = s.length;
//        sum = new int[n + 1];
//        dp = new int[n + 1][n + 1];
//        this.s = s;
//        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + s[i - 1];
//        int dfs = dfs(0, n);
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
//        return dfs;
//    }

    int[] p;
    int[] size;
    int[] cnt;

    int find(int x) {
        while (x != p[x]) {
            p[x] = p[p[x]];
            x = p[x];
        }
        return x;
        // if (x == p[x]) return x;
        // return p[x] = find(p[x]);
    }

    // 序列并查集
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        p = new int[n + 5];
        size = new int[n + 5];
        cnt = new int[n + 5];
        cnt[0] = n;
        for (int i = 1; i <= n + 1; i++) {
            p[i] = i;
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            p[x] = x + 1;
            int fx = find(x);
            cnt[size[x]]--;
            cnt[size[fx]]--;
            size[fx] += size[x] + 1;
            size[x] = 0;
            cnt[size[fx]]++;
            if (cnt[m] > 0) ans = i + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        char[][] c = new char[][]{{'a', 'a', 'a', 'a'}, {'a', 'b', 'b', 'a'}, {'a', 'b', 'b', 'a'}, {'a', 'a', 'a', 'a'}};
//        System.out.println(s.findLatestStep(new int[]{3, 5, 1, 2, 4}, 1));
        System.out.println("b");
    }
}

class A {
    public int subarraysDivByK(int[] A, int K) {
        int sum = 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i : A) {
            i = i % K + K;
            sum += i;
            sum %= K;
            if (map.containsKey(sum)) {
                ans += map.get(sum);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }
}

