package rui.leetcode;

public class Talk53 {
    public static void main(String[] args) {
        int a = 0, b = 0, c = 0;
        long[] dp = new long[51];
        dp[0] = 1;
        for (int i = 1; i < 51; i++) {
            dp[i] = Math.min(Math.min(dp[a] * 3, dp[b] * 5), dp[c] * 7);
            if (dp[i] == dp[a] * 3) a++;
            if (dp[i] == dp[b] * 5) b++;
            if (dp[i] == dp[c] * 7) c++;
        }
        System.out.println(dp[50]);
    }
}
