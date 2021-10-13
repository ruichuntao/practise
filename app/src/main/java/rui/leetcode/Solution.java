package rui.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

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

    public int backPackV(int[] nums, int target) {
        // write your code here
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 0;
        int ans = 0;
//        for (int i = 1; i <= n; i++) {
//            for (int j = target; j >= nums[i - 1]; j--) {
//                dp[j] = Math.max(dp[j], dp[j - nums[i - 1]] + nums[i - 1]);
//            }
//        }
//        for (int i = 1; i <= n; i++) {
//            for (int j = nums[i - 1]; j <= target; j++) {
//                dp[j] = Math.max(dp[j], dp[j - nums[i - 1]] + nums[i - 1]);
//            }
//        }
        System.out.println(Arrays.toString(dp));
        return dp[target];
    }

    public int woodCut(int[] L, int k) {
        // write your code here
        int n = L.length;
        int max = 0;
        for (int ll : L) if (ll > max) max = ll;
        int l = 0;
        int r = max;
        while (l < r) {
            int m = (l + r) >>> 1;
            int x = getX(L, n, m);
            if (x < k) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (getX(L, n, l) == k)
            return l;
        return l - 1;
    }

    private int getX(int[] L, int n, int m) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            if (L[i] >= m) {
                x += L[i] / m;
            }
        }
        return x;
    }

    boolean check(int m) {
        int x = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + nums[i] <= m) {
                sum += nums[i];
            } else {
                sum = nums[i];
                x++;
            }
        }
        return x >= t;
    }


    int t;
    int[] nums;
    int n;

    public int splitArray(int[] nums, int t) {
        // write your code here
        this.nums = nums;
        this.t = t;
        n = nums.length;
        int r = 1 << 20;
        int l = 0;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (check(m)) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        if (check(l)) return l;
        return l - 1;
    }

    public String replaceDigits(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        for (int i = 1; i < n; i += 2) {
            c[i] = (char) (c[i - 1] + c[i] - '0');
        }
        return new String(c);
    }

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length;
        Arrays.sort(rooms, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
            }
        });
        int k = queries.length;
        int[] ans = new int[k];
        int t = 0;
        for (int[] q : queries) {
            int p = q[0], ms = q[1];
            int l = 0, r = n, y = n;
            int tmp = (int) 1e9;
            int id = tmp;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (rooms[mid][1] < ms) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            while (r < y) {
                int mid = (y + r) >>> 1;
//                if ()
                if (tmp > Math.abs(p - mid)) {
                    id = mid;
                    tmp = Math.abs(p - mid);
                } else if (tmp == Math.abs(p - mid)) {
                    id = Math.min(id, mid);
                }
            }
            if (tmp != (int) 1e9)
                ans[t++] = id;
            else
                ans[t++] = -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.closestRoom(new int[][]{{2, 2}, {1, 2}, {3, 2}}, new int[][]{{3, 1}, {3, 3}, {5, 2}}));
    }


}


class Solution1 {

    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        int l = 0, r = 0, res = 0;
        while (r < nums.length) {
            while (!maxQueue.isEmpty() && nums[r] > maxQueue.peekLast())
                maxQueue.removeLast();
            while (!minQueue.isEmpty() && nums[r] < minQueue.peekLast())
                minQueue.removeLast();
            maxQueue.add(nums[r]);
            minQueue.add(nums[r]);
            r++;
            while (maxQueue.peek() - minQueue.peek() > limit) {
                if (maxQueue.peek() == nums[l]) maxQueue.remove();
                if (minQueue.peek() == nums[l]) minQueue.remove();
                l += 1;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }

    public int minimumXORSum(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int maxn = 1 << n;
        int[][] dp = new int[m + 1][maxn + 1];
        for (int i = 1; i <= m; i++) Arrays.fill(dp[i], -1);
        for (int i = 1; i <= m; i++) {
            for (int z = 0; z < n; z++) {
                for (int j = 0; j < maxn; j++) {
                    if (((j >> z) & 1) == 1) continue;
                    if (dp[i][j | (1 << z)] == -1 || dp[i][j | (1 << z)] > dp[i - 1][j] + (nums1[i - 1] ^ nums2[z]))
                        dp[i][j | (1 << z)] = dp[i - 1][j] + nums1[i - 1] ^ nums2[z];
                }
            }
        }
        return dp[m][maxn - 1];
    }

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        System.out.println(1 << 3 - 1);
        System.out.println((1 << 3) - 1);
    }

}



