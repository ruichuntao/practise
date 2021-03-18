package rui.leetcode;

import java.util.Arrays;

public class Talk51 {

    static int n = 7, k = 4;
    //    static int[] split = new int[]{32, 35, 39, 9, 38, 27, 26, 43, 1, 37, 33, 31, 10, 25, 5, 41, 13, 11, 19, 40, 30, 16, 36, 21, 34, 8, 6, 2, 12, 14, 7, 22, 3, 24, 42, 29, 4, 17, 44};
    //    static int[][] dp = new int[n][n];
    static int[][] dp = new int[k + 1][k + 1];
        static int[] split = new int[]{3, 5, 1, 4};
    static int[] sum = new int[k + 2];

    int dfs(int l, int r) {
        if (l > r) return 0;
        if (dp[l][r] != 0) return dp[l][r];
        int max = 1 << 20;
        for (int i = l; i < r; i++) {
            int m = dfs(l, i) + dfs(i + 1, r) + sum[r + 1] - sum[l];
            dp[l][r] = Math.min(max, m);
        }
        return dp[l][r];
    }

    public static void main(String[] args) {
//        Talk51 t = new Talk51();
//        Arrays.sort(split);
//        for (int i = 1; i <= k; i++) {
//            sum[i] = split[i - 1];
//        }
//        sum[k + 1] = n;
//        int s = t.dfs(0, k);
//        System.out.println(dp[0][k]);
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        System.out.println(1 << 10);
    }
}
