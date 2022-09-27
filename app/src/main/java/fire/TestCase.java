package fire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import rui.leetcode.ListNode;

public class TestCase {

    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[a] * 2, Math.min(dp[b] * 3, dp[c] * 5));
            if (dp[i] == dp[a] * 2) a++;
            if (dp[i] == dp[b] * 3) b++;
            if (dp[i] == dp[c] * 5) c++;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        TestCase s = new TestCase();
        System.out.println(s);
    }
}
