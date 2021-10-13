package rui.hihoCoder;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public int shortestSubarray(int[] A, int K) {
        int ans = 1_000_000_000;
        int n = A.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + A[i];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            while (!queue.isEmpty() && sum[queue.peekLast()] >= sum[i])
                queue.pollLast();
            while (!queue.isEmpty() && sum[i] - sum[queue.peek()] >= K)
                ans = Math.min(ans, i - queue.poll());
            queue.add(i);
        }
        if (ans == 1_000_000_000) return -1;
        return ans;
    }

    class Solution {
        public int minMalwareSpread(int[][] graph, int[] initial) {
            int N = graph.length;
            DSU dsu = new DSU(N);
            for (int i = 0; i < N; ++i)
                for (int j = i + 1; j < N; ++j)
                    if (graph[i][j] == 1)
                        dsu.union(i, j);

            int[] count = new int[N];
            for (int node : initial)
                count[dsu.find(node)]++;

            int ans = -1, ansSize = -1;
            for (int node : initial) {
                int root = dsu.find(node);
                if (count[root] == 1) {  // unique color
                    int rootSize = dsu.size(root);
                    if (rootSize > ansSize) {
                        ansSize = rootSize;
                        ans = node;
                    } else if (rootSize == ansSize && node < ans) {
                        ansSize = rootSize;
                        ans = node;
                    }
                }
            }

            if (ans == -1) {
                ans = Integer.MAX_VALUE;
                for (int node : initial)
                    ans = Math.min(ans, node);
            }
            return ans;
        }
    }


    class DSU {
        int[] p, sz;

        DSU(int N) {
            p = new int[N];
            for (int x = 0; x < N; ++x)
                p[x] = x;

            sz = new int[N];
            Arrays.fill(sz, 1);
        }

        public int find(int x) {
            if (p[x] != x)
                p[x] = find(p[x]);
            return p[x];
        }

        public void union(int x, int y) {
            int xr = find(x);
            int yr = find(y);
            p[xr] = yr;
            sz[yr] += sz[xr];
        }

        public int size(int x) {
            return sz[find(x)];
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buy = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        PriorityQueue<int[]> sell = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = orders.length;
        for (int i = 0; i < n; i++) {
            int p = orders[i][0];
            int c = orders[i][1];
            int t = orders[i][2];
            if (t == 0) {
                while (!sell.isEmpty() && sell.peek()[0] <= p) {
                    if (sell.peek()[1] >= c) {
                        sell.peek()[1] -= c;
                        c = 0;
                        break;
                    } else {
                        c -= sell.peek()[1];
                        sell.poll();
                    }
                }
                if (c > 0)
                    buy.add(new int[]{p, c});
            } else {
                while (!buy.isEmpty() && buy.peek()[0] >= p) {
                    if (buy.peek()[1] >= c) {
                        buy.peek()[1] -= c;
                        c = 0;
                        break;
                    } else {
                        c -= buy.peek()[1];
                        buy.poll();
                    }
                }
                if (c > 0)
                    sell.add(new int[]{p, c});
            }
        }
        int cnt = 0;
        int mod = 1_000_000_007;
        while (!buy.isEmpty()) {
            System.out.println(Arrays.toString(buy.peek()));
            cnt = (cnt + buy.poll()[1]) % mod;
        }
        while (!sell.isEmpty()) {
            System.out.println(Arrays.toString(sell.peek()));
            cnt = (cnt + sell.poll()[1]) % mod;
        }
        return cnt;
    }

    public int fifteen(int l, int r, int[][] dp) {
        if (l > r) return 0;
        if (l == r) return 1;
        if (dp[l][r] != 0) return dp[l][r];
        int ans = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                ans += fifteen(l + i, r - j, dp);
            }
        }
        dp[l][r] = ans;
        return ans;
    }

    public void fifteenDP() {
        int[][][] dp = new int[11][11][11];
        for (int j = 0; j < 11; j++) {
            for (int k = 0; k < 11; k++) {
                dp[0][j][k] = -1;
            }
        }
        dp[0][0][10] = 1;
        for (int i = 1; i < 11; i++) {
            for (int pa = 0; pa < 11; pa++) {
                for (int pb = 10; pb >= 0; pb--) {
                    if (dp[i - 1][pa][pb] == -1) continue;
                    for (int j = 1; j <= 4; j++) {
                        if (pa + j > 10) continue;
                        for (int k = 1; k <= 4; k++) {
                            if (pb - k < 0) continue;
                            dp[i][pa + j][pb - k] += dp[i - 1][pa][pb];
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (dp[i][j][j] == -1) continue;
                ans += dp[i][j][j];

            }
        }
        System.out.println(ans);
    }

    public int seventeen(int l, int[] v) {
        if (l == 30) return 1;
        int x = 0;
        if (l == 0) {
            v[l] = 1;
            x += seventeen(l + 1, v);
            v[l] = 0;
            v[l] = -1;
            x += seventeen(l + 1, v);
            v[l] = 0;
        } else {
            if (v[l - 1] == -1) {
                v[l] = 1;
                x += seventeen(l + 1, v);
                v[l] = 0;
            } else {
                v[l] = 1;
                x += seventeen(l + 1, v);
                v[l] = 0;
                v[l] = -1;
                x += seventeen(l + 1, v);
                v[l] = 0;
            }
        }
        return x;
    }

    public void seventeenDP() {
        int n = 30;
        int[][] dp = new int[n][2];
        dp[0][0] = 1; // 男
        dp[0][1] = 1; // 女
        for (int i = 1; i < n; i++) {
            dp[i][1] = dp[i - 1][0];
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
        }
        System.out.println(dp[n - 1][0] + dp[n - 1][1]);
    }

    public void eighteenDP(int[] map, int[][] h, int c, int[] sum, int s) {
        sum[s]++;
        if (c == map.length) return;
        eighteenDP(map, h, c + 1, sum, s + map[c]);
        eighteenDP(map, h, c + 1, sum, s);
    }

    static void dfs(int[][] arr, int x, int y, int n, int m, boolean up) {
        if (x >= m || y >= m || x < 0 || y < 0) return;
        if (arr[x][y] != 0) return;
        arr[x][y] = n;
        if (n == 9) n = 0;
        if (up) {
            dfs(arr, x - 1, y, n + 1, m, true);
            dfs(arr, x, y + 1, n + 1, m, false);
        } else {
            dfs(arr, x, y + 1, n + 1, m, false);
            dfs(arr, x + 1, y, n + 1, m, false);
            dfs(arr, x, y - 1, n + 1, m, false);
            dfs(arr, x - 1, y, n + 1, m, true);
        }
    }

    int max = 10;
    double value = 1.6180339887;

    double dianZu(int l, int r, boolean bing) {
        if (l + 1 == r) {
            if (bing) return 1 / 2d;
            else return 2;
        }
        double ans = 0;
        for (int i = l + 1; i < r - 1; i++) {
            ans = dianZu(l, i, false) + dianZu(i + 1, r, false);
            ans = dianZu(l, i, false) + dianZu(i + 1, r, true);
            ans = dianZu(l, i, true) + dianZu(i + 1, r, false);
            ans = dianZu(l, i, true) + dianZu(i + 1, r, true);
        }

        return ans;
    }


    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int max = 0, tempSum = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            while (nums[j] * (j - i) - tempSum > k) {
                tempSum -= nums[i++];
            }
            tempSum += nums[j];
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    public int numberOfSubstrings(String s) {
        int res = 0;
        // 记录 'a', 'b', 'c' 上次出现的位置
        int[] record = {-1, -1, -1};
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                res += Math.min(record[1], record[2]) + 1;
            } else if (s.charAt(i) == 'b') {
                res += Math.min(record[0], record[2]) + 1;
            } else {
                res += Math.min(record[0], record[1]) + 1;
            }
            // 更新 'a', 'b', 'c' 最近出现的位置
            record[s.charAt(i) - 'a'] = i;
        }
        return res;
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.numberOfSubstrings("abcabc"));
    }

}

class Solution {
    Node root = new Node();

    class Node {
        Node[] next = new Node[2];
    }

    void build(int num) {
        Node rt = root;
        for (int i = 31; i >= 0; i--) {
            int bit = num >> i & 1;
            if (rt.next[bit] == null) rt.next[bit] = new Node();
            rt = rt.next[bit];
        }
    }

    int search(int num) {
        Node rt = root;
        int ret = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = num >> i & 1;
            if (rt.next[bit ^ 1] != null) {
                rt = rt.next[bit ^ 1];
                ret |= 1 << i;
            } else {
                rt = rt.next[bit];
            }
        }
        return ret;
    }

    public int findMaximumXOR(int[] nums) {
        for (int num : nums) {
            build(num);
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(search(num), max);
        }
        return max;
    }

    public static int getAns(int x) {
        return (pow(x, 3) - 2) / (x - 1);
//        return (pow(x, 4) - 3 * pow(x, 3) + 2 * pow(x, 2) - x + 6) / (x - 5);
    }

    public static int checkAns(int x) {
//        return (pow(x, 4) - 3 * pow(x, 3) + 2 * pow(x, 2) - x + 6) / (x - 5);
        return pow(x, 2) + x;
    }

    public static int pow(int x, int t) {
        return (int) Math.pow(x, t);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        // 先处理 nums2，把对应关系存入哈希表
        for (int i = 0; i < len2; i++) {
            while (!stack.isEmpty() && stack.peekLast() < nums2[i]) {
                map.put(stack.removeLast(), nums2[i]);
            }
            stack.addLast(nums2[i]);
        }

        // 遍历 nums1 得到结果集
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    static class A extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-------");
        }
    }


    public static void main(String[] args) {
        Main m = new Main();
    }
}
