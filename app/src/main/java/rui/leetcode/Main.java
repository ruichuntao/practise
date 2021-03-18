package rui.leetcode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    int fun(int[] low, int b, int l) {
        int n = low.length;
        int ans = 0, sum = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            if (low[i] > low[i - 1]) {
                int cost = low[i] - low[i - 1];
                queue.add(cost);
                while (queue.size() > l) {
                    sum += queue.poll();
                }
                if (sum > b) break;
            }
            ans = i;
        }
        return ans;
    }


    public static void main(String[] args) {
        Main m = new Main();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void googleRound1() {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
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

    static class Solution {
        public int minimumMountainRemovals(int[] nums) {
            int n = nums.length;
            // 前面递增0，前面递减1
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (nums[j] > nums[i]) dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            int[] dp2 = new int[n];
            Arrays.fill(dp2, 1);
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j >= 0; j--) {
                    if (nums[j] > nums[i]) dp2[j] = Math.max(dp2[j], dp2[i] + 1);
                }
            }
            int ans = 10000;
            for (int i = 1; i < n - 1; i++) {
                ans = Math.min(ans, n - (dp[i] + dp2[i]) - 1);
            }
            return ans;
        }

        public int numSubarraysWithSum(int[] A, int S) {
            int max = A.length;
            int[] h = new int[max];
            h[0] = 1;
            int ans = 0;
            int cur = 0;
            for (int a : A) {
                cur += a;
                if (cur - S >= 0) {
                    ans += h[cur - S];
                }
                h[cur]++;
            }
            return ans;
        }

        public String maximumBinaryString(String binary) {
            char[] c = binary.toCharArray();
            int n = c.length;
//            String ans = "";
            boolean first = false;
            // 10 - 01
            // 00 - 10
            for (int i = 0; i < n; i++) {
                if (c[i] == '1' && !first) {
                    first = true;
                } else if (c[i] == '1' && i < n - 1 && c[i + 1] == '0') {
                    c[i] = '0';
                    c[i + 1] = '1';
                }
            }
            for (int i = 0; i < n; i++) {
                if (c[i] == '0' && i < n - 1 && c[i + 1] == '0') {
                    c[i] = '1';
                    c[i + 1] = '0';
                }
            }
            return new String(c);
        }

        public int maxDistToClosest(int[] seats) {
            int n = seats.length;
//            int r = (int) 1e5;
            int r = 5;
            int l = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (seats[i] == 1) list.add(i);
            }
            int m = list.size();
            if (m == 1) {
                if (list.get(0) == n - 1 || list.get(0) == 0) return n - 1;
                else
                    return Math.max(list.get(0), n - 1 - list.get(0));
            }
            //int ans = 1;
            int mid = 1;
            out:
            while (l < r) {
                mid = (l + r) >>> 1;
                for (int i = 0; i < m - 1; i++) {
                    if ((list.get(i + 1) - list.get(i)) / 2 > mid) {
                        l = mid + 1;
                        continue out;
                    }
                }
                r = mid;
            }
            return mid;
        }

        public int numWays(String[] words, String target) {
            int mod = (int) 1e9 + 7;
            int m = words.length;
            int n = words[0].length();
            int k = target.length();
            int[][][] f = new int[n + 1][m + 1][k + 1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= k; j++) {
                    f[0][i][j] = 1;
                }
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    for (int z = i; z <= k; z++) {
                        if (words[j - 1].charAt(i - 1) == target.charAt(z - 1)) {
                            f[i][j - 1][z] += f[i - 1][j - 1][z - 1];
                        }
                    }
                }
            }
            System.out.println(Arrays.deepToString(f));
            return f[n][m][k];
        }

        public static int shortestSubarray(int[] A, int K) {
            int ans = 5_000_000_0;
            int l = 0, r = 0;
            int n = A.length;
            int sum = 0;
            int pre = r;
            while (r < n && l <= r) {
                if (pre != r)
                    sum += A[r];
                pre = r;
                if (sum >= K) {
                    ans = Math.min(r - l + 1, ans);
                    sum -= A[l];
                    l++;
                } else {
                    r++;
                }

            }

            return ans == 5_000_000_0 ? -1 : ans;
        }

        public static void main(String[] args) {

//             跳台阶（每次x步）
//
//            int n = 10, x = 3;
//            int[] dp = new int[n + 1]; // 计算结果数，常用的做法将dp[0] = 1;
//            dp[0] = 1;
//            for (int i = 1; i <= n; i++) {
//                for (int j = 0; j <= Math.min(x, i); j++) {
//                    dp[i] += dp[i - j];
//                }
//            }
//            System.out.println(dp[n]);
//            int n = 10;
//            if (n == 0 || n == 1) System.out.println(1);
//            int pre = 1, cur = 1, nxt = 0;
//            for (int i = 2; i < n; i++) {
//                nxt = pre + cur;
//                pre = cur;
//                cur = nxt;
//            }
//            System.out.println(nxt);

//            int n = 100;
//            StringBuilder s = new StringBuilder();
//            for (int i = 31; i >= 0; i--) {
//                if ((n & (1 << i)) == 1 << i) s.append("1");
//                else s.append(0);
//            }
//            System.out.println(s.toString());
//            for (int i = 0; i < 32; i++) {
//                if (((n >> i) & 1) == 1) s.append("1");
//                else s.append(0);
//            }
//            System.out.println(s.reverse().toString());
//            int x = 2, n = 10, ans = 1;
//            for (int i = 1; i <= n; i++) {
//                ans *= x;
//            }
//            System.out.println(ans);

//            while (n != 0) {
//                if ((n & 1) == 1) ans *= x;
//                x *= x;
//                n >>= 1;
//            }
//            System.out.println(ans);


            // int ttt;
            // 0 是道路  1 是障碍物
            // 地图是5 x 5
            // 起点在0，0  终点在m - 1，n - 1
//            int[][] dict = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};// bfs常用方向辅助数组
//            Queue<int[]> queue = new ArrayDeque<>();
//            int m = 5, n = 5;
//            int[][] v = new int[m][n];// 同为辅助数组，防止无限进入遍历
//            int[][] map = new int[][]{// 地图数组
//                    {0, 0, 0, 0, 0},
//                    {0, 0, 1, 0, 0},
//                    {0, 1, 0, 0, 1},
//                    {0, 0, 1, 0, 0},
//                    {0, 0, 0, 1, 0},
//            };
//            queue.add(new int[]{0, 0, 0});
//            v[0][0] = 1;
//            while (!queue.isEmpty()) {
//                int[] cur = queue.poll();
//                int x = cur[0];
//                int y = cur[1];
//                int step = cur[2];
//                if (x == m - 1 && y == n - 1) {
//                    System.out.println(step);
//                    break;
//                }
//                for (int[] d : dict) {
//                    int nx = x + d[0];
//                    int ny = y + d[1];
//                    if (nx >= 0 && ny >= 0 && nx < m && ny < n && v[nx][ny] == 0 && map[nx][ny] == 0) {
//                        v[nx][ny] = 1;
//                        queue.add(new int[]{nx, ny, step + 1});
//                    }
//                }
//            }
//            System.out.println(shortestSubarray(new int[]{2, -1, 2}, 3));
        }

    }
}



