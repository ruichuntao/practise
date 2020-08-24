package rui.leetcode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static int MOD = (int) 1e9 + 7;
    public int mod = (int) 1e9 + 7;
    int x;

    public static int[][] dict = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int cnt;

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

//    public ListNode reverseList(ListNode head) {
//        ListNode pre = null, cur = head, next = null;
//        while (cur != null) {
//            next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        return pre;
//    }


    public void dfs(int d, int c, int p, int pp, String x, boolean flag) {
        if (d >= 10) return;
        if (c == pp && c == 0 || flag) {
            flag = true;
            if (d == 9) {
                cnt++;
                System.out.println(x);
            }
        }
        for (int i = 0; i < 3; i++) {
            dfs(d + 1, i, c, p, x + i, flag);
        }
    }


    public boolean canConvertString(String s, String t, int k) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int m = sc.length;
        int n = tc.length;
        if (m != n) return false;
        while (m > 0) {
            if (sc[--m] != tc[--n]) {
                break;
            }
        }
        int x = 1;
        int i = 0;
        while (x <= k) {
            for (; i < n; ) {
                if (sc[i] == tc[i]) {
                    i++;
                } else {
                    int tmp = 0;
                    if (tc[i] - sc[i] < 0) {
                        tmp = 26 + tc[i] - sc[i];
                    } else
                        tmp = tc[i] - sc[i];
                    if (tmp == x) {
                        i++;
                    }
                    break;
                }
            }
            x++;
        }
        return i == m;
    }

    public int longestAwesome(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] dp = new int[(1 << 10) + 5];
        for (int i = 0; i < (1 << 10) + 5; i++) {
            dp[i] = n + 1;
        }
        int bits = 0, ans = 0;
        dp[0] = -1;
        for (int i = 0; i < n; i++) {
            bits ^= (1 << (c[i] - '0'));
            dp[bits] = Math.min(dp[bits], i);

            ans = Math.max(ans, i - dp[bits]);

            for (int k = 0; k < 10; k++) {
                ans = Math.max(ans, i - dp[bits ^ (1 << k)]);
            }
        }
        return ans;
    }

    int[] sum;
    int max = 1 << 31 - 1;

    public int minCost(int m, int[] cuts) {
        int n = cuts.length;
        sum = new int[n + 2];
        Arrays.sort(cuts);
        System.arraycopy(cuts, 0, sum, 1, n);
        sum[n + 1] = m;
        int[][] dp = new int[n + 2][n + 2];
        for (int len = 1; len <= n + 1; len++) {
            for (int l = 1; l <= n + 1; l++) {
                int r = l + len;
                if (r > n + 1) break;
                dp[l][r] = max;
                for (int k = l; k < r; k++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][k] + dp[k + 1][r] + sum[r] - sum[l - 1]);
                }
            }
        }
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return dp[1][n + 1];
    }

    int[][] dp;
    int[] cost;
    int n;

    public int dfs(int l, int r) {
        if (dp[l][r] != -1) return dp[l][r];
        int max = 0;
        for (int m = l + 1; m < r; m++) {
            int v = 0;
            v = cost[l] * cost[m] * cost[r];
            max = Math.max(max, dfs(l, m) + v + dfs(m, r));
        }
        dp[l][r] = max;
        return dp[l][r];
    }

    public int maxCoins(int[] nums) {
        n = nums.length;
        dp = new int[n + 2][n + 2];
        cost = new int[n + 2];
        cost[0] = cost[n + 1] = 1;
        System.arraycopy(nums, 0, cost, 1, n);
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        int dfs = dfs(0, n + 1);
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return dfs;
    }

    public int maxCoins2(int[] nums) {
        int[] a = new int[nums.length + 2];
        a[0] = 1;
        a[a.length - 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            a[i + 1] = nums[i];
        }
        Integer[][] integers = new Integer[a.length][a.length];
        int helper = helper(a, 0, a.length - 1, integers);
        for (Integer[] i : integers) {
            System.out.println(Arrays.toString(i));
        }
        return helper;
    }

    public int helper(int[] a, int i, int j, Integer[][] memo) {
        // 如果记忆有，直接返回
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int max = 0;
        for (int k = i + 1; k < j; k++) {
            max = Math.max(max,
                    helper(a, i, k, memo) +
                            a[i] * a[k] * a[j] +
                            helper(a, k, j, memo));
        }

        // 记忆
        return memo[i][j] = max;
    }

    int abs(int a) {
        return Math.abs(a);
    }

    public int maxDistance(int[] p, int m) {
        int r = (1 << 31) - 1;
        int l = 0;
        int n = p.length;
        while (l < r) {
            int k = 0;
            int last = 0;
            int mid = (l + r) >>> 1;
            for (int i = 1; i < n; i++) {
                if (abs(p[i] - p[last]) >= mid) {
                    k++;
                    last = i;
                }
            }
            k++;
            if (k <= m) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int minDays(int n) {
        return getMinDay(n);

    }

    private Map<Integer, Integer> maps = new HashMap<>();

    private int getMinDay(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 2;
        Integer re = maps.get(n);
        // 是否之前计算过
        if (re != null) return (int) re;

        int m2 = getMinDay(n / 2) + n % 2;
        int m3 = getMinDay(n / 3) + n % 3;
        int result = Math.min(m2, m3) + 1;
        // 存储结果
        maps.put(n, result);
        return result;
    }

    public int[] getTriggerTime(int[][] in, int[][] re) {
        int n = in.length;
        int s = re.length;
        int[] res = new int[s];
        for (int i = 1; i < n; i++) {
            in[i][0] += in[i - 1][0];
            in[i][1] += in[i - 1][1];
            in[i][2] += in[i - 1][2];
        }
        for (int i = 0; i < s; i++) {
            int l = 0;
            int r = n;
            while (l < r) {
                int m = (l + r) >>> 1;
                if (in[m][0] < re[i][0] || in[m][1] < re[i][1] || in[m][2] < re[i][2]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            if (l < n) res[i] = l + 1;
            else res[i] = -1;
        }
        return res;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        int l = 0;
        int r = m;
        int c = 0;
        int d = 0;
        while (l < r) {
            c = (l + r) >>> 1;
            if (matrix[c][0] > target) {
                r = c - 1;
            } else if (matrix[c][0] < target) {
                l = c;
            } else
                return true;
        }
        c = l;
        l = 0;
        r = n;
        while (l < r) {
            d = (l + r) >>> 1;
            if (matrix[c][d] > target) {
                r = d - 1;
            } else if (matrix[c][d] < target) {
                l = d;
            } else
                return true;
        }
        return false;
    }

    int[] digit = new int[10];
    int[][] mem = new int[10][10];
    int[] num = new int[20];

    int dfs(int len, int x, boolean limit, boolean lead) {
        if (len == -1) {
            if (!lead)
                return 1;
            else
                return 0;
        }
        if (mem[len][x] != -1 && !limit) return mem[len][x];
        int cnt = 0;
        int bound = limit ? digit[len] : 9;
        for (int i = 0; i <= bound; i++) {
            if (lead && i == 0)
                cnt += dfs(len - 1, i, limit && i == bound, lead && i == 0);
            else if (num[i] != 0)
                cnt += dfs(len - 1, i, limit && i == bound, lead && i == 0);
        }
        if (!limit) return mem[len][x] = cnt;
        return cnt;
    }

    public int atMostNGivenDigitSet(String[] D, int N) {
        n = D.length;
        int len = get(N);
        for (int i = 0; i < n; i++) {
            num[Integer.parseInt(D[i])] = 1;
        }
        for (int[] ints : mem) {
            Arrays.fill(ints, -1);
        }
        return dfs(len - 1, 0, true, true);
    }

    int get(int N) {
        int x = 0;
        while (N > 0) {
            digit[x++] = N % 10;
            N /= 10;
        }
        return x;
    }

    public int largestValsFromLabels(int[] v, int[] l, int w, int u) {
        int n = v.length;
        int[][] h = new int[n][2];
        int[] cnt = new int[20001];
        for (int i = 0; i < n; i++) {
            cnt[l[i]]++;
            if (cnt[l[i]] > u) cnt[l[i]] = u;
            h[i][0] = v[i];
            h[i][1] = l[i];
        }
        Arrays.sort(h, (a, b) -> (b[0] - a[0]));
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[h[i][1]] > 0 && w > 0) {
                ans += h[i][0];
                cnt[h[i][1]]--;
                w--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Main x = new Main();
        int[][] in = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int[][] re = new int[][]{{0, 13, 14}, {0, 5, 5}, {0, 4, 18}, {4, 3, 4}};
        int[] t = new int[]{5, 4, 3, 2, 1};
        int[] e = new int[]{1, 1, 2, 2, 3};
        System.out.println(x.largestValsFromLabels(t, e, 3, 1));
        int l = 3, r = 10;

    }
}



