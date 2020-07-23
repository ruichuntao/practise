package rui.leetcode;

import java.util.*;
import java.math.*;

public class Main {

    public static int MOD = (int) 1e9 + 7;
    public int mod = (int) 1e9 + 7;


    public static int[][] dict = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int numberOfArrays(String s, int k) {
        char[] c = s.toCharArray();
        int m = c.length;
        int[] dp = new int[m + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j < 9; j++) {
                if (j > i) break;
                if (c[i - j] - '0' == 0) continue;
                int sum = 0;
                for (int z = i - j; z < i; z++) {
                    sum *= 10;
                    sum += c[z] - '0';
                }
                if (sum <= k && sum > 0) dp[i] = (dp[i] + dp[i - j]) % MOD;
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[m];
    }

    public int numFactoredBinaryTrees(int[] A) {
        int m = A.length;
        long[] dp = new long[m];
        Arrays.fill(dp, 1);
        Arrays.sort(A);
        long ans = 0l;
        for (int i = 0; i < m; i++) {
            int l = 0;
            int r = i;
            while (l < r) {
                if (A[l] * A[l] == A[i]) {
                    dp[i] = (dp[i] + dp[l] * dp[l]) % mod;
                    break;
                } else if (A[r] * A[r] == A[i]) {
                    dp[i] = (dp[i] + dp[r] * dp[r]) % mod;
                    break;
                } else if (A[l] * A[r] < A[i]) {
                    l++;
                } else if (A[l] * A[r] > A[i]) {
                    r--;
                } else {
                    dp[i] = (dp[i] + dp[l] * dp[r] * 2) % mod;
                    l++;
                    r--;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int) ans;
    }

    public boolean isUnique(String astr) {
        long low64 = 0;
        long high64 = 0;

        for (char c : astr.toCharArray()) {
            if (c >= 64) {
                long bitIndex = 1L << c - 64;
                if ((high64 & bitIndex) != 0) {
                    return false;
                }

                high64 |= bitIndex;
            } else {
                long bitIndex = 1L << c;
                if ((low64 & bitIndex) != 0) {
                    return false;
                }

                low64 |= bitIndex;
            }

        }

        return true;
    }

    public boolean dfs(TreeNode a, TreeNode b) {
        if (b == null) return true;
        if (a == null) return false;
        if (a.val != b.val) return false;
        return dfs(a.left, b.left) && dfs(a.right, b.right);

    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) return false;
        if (A == null) return false;
        return isSubStructure(A.left, B) || isSubStructure(A.right, B) || dfs(A, B);
    }



    public static void main(String[] args) {
        Main t = new Main();
        TreeNode a = Codec.deserialize("[3,4,5,1,2]");
        TreeNode b = Codec.deserialize("[4,1]");
        System.out.println(t.isSubStructure(a, b));
    }

}
