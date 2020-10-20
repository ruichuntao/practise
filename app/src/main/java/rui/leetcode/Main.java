package rui.leetcode;

import java.util.*;

class Main {

    long mod = (long) (1e9 + 7);
    int min = -(1 << 20);

    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 0 代表当前最小值，1 代表当前最大值
        long[][][] dp = new long[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long tmin = Long.MAX_VALUE;
                long tmax = Long.MIN_VALUE;
                if (i == 0 && j == 0) {
                    dp[i][j][0] = grid[i][j];
                    dp[i][j][1] = grid[i][j];
                } else if (i == 0) {
                    tmin = Math.min(dp[i][j - 1][0] * grid[i][j], tmin);
                    tmin = Math.min(dp[i][j - 1][1] * grid[i][j], tmin);
                    tmax = Math.max(dp[i][j - 1][0] * grid[i][j], tmax);
                    tmax = Math.max(dp[i][j - 1][1] * grid[i][j], tmax);
                    dp[i][j][0] = tmin;
                    dp[i][j][1] = tmax;
                } else if (j == 0) {
                    tmin = Math.min(dp[i - 1][j][0] * grid[i][j], tmin);
                    tmin = Math.min(dp[i - 1][j][1] * grid[i][j], tmin);
                    tmax = Math.max(dp[i - 1][j][0] * grid[i][j], tmax);
                    tmax = Math.max(dp[i - 1][j][1] * grid[i][j], tmax);
                    dp[i][j][0] = tmin;
                    dp[i][j][1] = tmax;
                } else {
                    tmin = Math.min(dp[i - 1][j][0] * grid[i][j], tmin);
                    tmin = Math.min(dp[i][j - 1][0] * grid[i][j], tmin);
                    tmin = Math.min(dp[i - 1][j][1] * grid[i][j], tmin);
                    tmin = Math.min(dp[i][j - 1][1] * grid[i][j], tmin);
                    dp[i][j][0] = tmin;
                    tmax = Math.max(dp[i - 1][j][0] * grid[i][j], tmax);
                    tmax = Math.max(dp[i][j - 1][0] * grid[i][j], tmax);
                    tmax = Math.max(dp[i - 1][j][1] * grid[i][j], tmax);
                    tmax = Math.max(dp[i][j - 1][1] * grid[i][j], tmax);
                    dp[i][j][1] = tmax;
                }
            }
        }
        return (int) dp[m - 1][n - 1][1];
    }

    public String customSortString(String S, String T) {
        char[] o = S.toCharArray();
        char[] c = T.toCharArray();
        int m = o.length;
        int n = c.length;
        List<Character> chars = new ArrayList<>();
        int idx = 0;
        Map<Character, Integer> map = new HashMap<>();
        idx = 0;
        for (char oc : o) map.put(oc, idx++);
        idx = 0;
        int r = n - 1;
        char[] ans = new char[n];
        for (char oc : c) {
            if (map.get(oc) != null)
                chars.add(oc);
            else {
                ans[r--] = oc;
            }
        }
        Collections.sort(chars, (a, b) -> map.get(a) - map.get(b));
        idx = 0;
        for (char oc : chars) ans[idx++] = oc;
        return new String(ans);
    }

    boolean check(String s) {
        int n = s.length();
        int r = n >> 1;
        int l = 0;
        while (r <= n) {
            String str = s.substring(l, r);
            StringBuilder sb = new StringBuilder(str);
            sb.reverse();
            String newStr = new String(sb);
            if (str.equals(newStr)) {
                return true;
            }
            l++;
            r++;
        }
        return false;
    }

    public boolean checkPalindromeFormation(String a, String b) {
        String sa = a + b;
        String sb = b + a;
        return check(sa) || check(sb);
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.checkPalindromeFormation("pvhmupgqeltozftlmfjjde", "yjgpzbezspnnpszebzmhvp"));
    }

    private static void googleRound1() {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int n = sc.nextInt();
            int max = sc.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
            }
            Arrays.sort(arr);
            for (int j = 0; j < n; j++) {
                if (max >= arr[j]) {
                    ans[i]++;
                    max -= arr[j];
                } else
                    break;
            }
        }
        for (int i = 0; i < m; i++) {
            System.out.println("Case #" + (i + 1) + ": " + ans[i]);
        }
    }

//    @Override
//    public int compareTo(Object o) {
//        return 0;
//    }
}

class Solution1 {
    /**
     * @param k: An integer
     * @param n: An integer
     * @return: An integer denote the count of digit k in 1..n
     */

    int[][] digit = new int[20][20];
    int[] num = new int[20];
    int len = 0;
    int k, n;

    void get(int n) {
        while (n != 0) {
            num[len++] = n % 10;
            n /= 10;
        }
    }

    int dfs(int l, boolean limit, boolean flag, int a) {
        if (l < 0) return a;
        if (digit[l][a] != -1 && !limit) return digit[l][a];
        int tmp = 0;
        int up = limit ? num[l] : 9;
        for (int i = 0; i <= up; i++) {
            if (k == 0) {
                if (flag && i == 0) {
                    tmp = tmp + dfs(l - 1, limit && i == up, true, a);
                } else {
                    tmp = tmp + dfs(l - 1, limit && i == up, false, a + (i == k ? 1 : 0));
                }
            } else {
                tmp = tmp + dfs(l - 1, limit && i == up, i == 0, a + (i == k ? 1 : 0));
            }
        }
        if (!limit && tmp != 0)
            digit[l][a] = tmp;
        return tmp;
    }

    public int digitCounts(int k, int n) {
        // write your code here
        this.k = k;
        this.n = n;
        get(n);
        for (int[] d : digit) Arrays.fill(d, -1);
        return dfs(len - 1, true, true, 0);
    }

    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        List<Map.Entry<Integer, Double>> ans = new ArrayList<>();
        double[][] dp = new double[n + 1][6 * n + 1];
        dp[0][0] = 1;
        double p = 1d / 6;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 6 * n; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j < k) break;
                    dp[i][j] += dp[i - 1][j - k] * p;
                }
            }
        }
        for (int j = 1; j <= 6 * n; j++) {
            if (dp[n][j] != 0)
                ans.add(new AbstractMap.SimpleEntry<>(j, dp[n][j]));
        }
        return ans;
    }

    public int numWays(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int one = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == '1') one++;
        }
        if (one % 3 != 0) return 0;
        if (one == 0) {
            if (n == 3) return 1;
            int t = n - 3;
            int la = 1;
            while (t > 0) {
                la *= t--;
            }
            return la;
        }
        int o = one / 3;
        int l = 0;
        long lo = 0;
        long ro = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == '1') {
                l++;
            }
            if (l == o) {
                lo++;
            }
            if (l == 2 * o) {
                ro++;
            }
        }
        lo--;
        ro--;
        long la = 1;
        while (lo > 0) {
            la *= lo--;
        }
        int ra = 1;
        while (ro > 0) {
            ra *= ro--;
        }
        return (int) (la * ra % (int) (1e9 + 7));
    }


    public static void main(String[] args) {
        Solution1 s = new Solution1();
    }

}
