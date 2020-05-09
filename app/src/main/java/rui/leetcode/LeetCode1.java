package rui.leetcode;

import android.support.v7.util.DiffUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.PriorityBlockingQueue;

import static java.lang.System.out;

public class LeetCode1 {

    private int row;
    private int col;
    int a[][];
    private int[][] dicts = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final int MOD = 1_000_000_007;

    private boolean isInArea(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return false;
        }
        return true;
    }

    private boolean isInArea(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return false;
        }
        return true;
    }


    public int numWays(int steps, int arrLen) {
        int MOD = 1_000_000_007;
        int[][] dp = new int[steps + 1][steps + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            // 注意: steps = i 时，我们最远能走到的是 i 和 arrLen-1 的较小者
            for (int j = 0; j <= Math.min(i, arrLen - 1); j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                    dp[i][j] %= MOD;
                }
                if (j < i) {
                    dp[i][j] += dp[i - 1][j + 1];
                    dp[i][j] %= MOD;
                }
            }
        }
        return dp[steps][0];

    }

    //826. 安排工作以达到最大收益

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[] maxs = new int[profit.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < profit.length; i++) {
            if (map.containsKey(difficulty[i])) {
                int temp = map.get(difficulty[i]);
                if (temp > profit[i]) {
                    continue;
                }
            }
            map.put(difficulty[i], profit[i]);
        }
        Arrays.sort(difficulty);
        int max = map.get(difficulty[0]);
        maxs[0] = max;
        for (int i = 1; i < maxs.length; i++) {
            if (map.get(difficulty[i]) > max) {
                max = map.get(difficulty[i]);
            }
            maxs[i] = max;
        }
        Arrays.sort(worker);
        int ans = 0;
        int index = 0;
        int last = 0;
        for (int i = 0; i < difficulty.length; ) {
            if (worker[index] >= difficulty[i]) {
                last = maxs[i];
                i++;
            } else {
                ans += last;
                index++;
                if (index == worker.length) {
                    break;
                }
            }
        }
        for (int i = index; i < worker.length; i++) {
            ans += last;
        }
        return ans;
    }

    //417. 太平洋大西洋水流问题(题意理解错了o(╥﹏╥)o)
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] pacific = new int[m][n];
        int[][] atlantic = new int[m][n];

        //从海洋边界开始
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    pacificAtlanticDFS(matrix, pacific, i, j, matrix[i][j]);
                }
                if (i == m - 1 || j == n - 1) {
                    pacificAtlanticDFS(matrix, atlantic, i, j, matrix[i][j]);
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] == 1 && atlantic[i][j] == 1) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void pacificAtlanticDFS(int[][] matrix, int[][] aux, int i, int j, int pre) {
        //判断边界
        if (i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[0].length - 1
                //已经流到过了
                || aux[i][j] == 1
                //不能流动
                || matrix[i][j] < pre) {
            return;
        }
        aux[i][j] = 1;
        pacificAtlanticDFS(matrix, aux, i - 1, j, matrix[i][j]);
        pacificAtlanticDFS(matrix, aux, i + 1, j, matrix[i][j]);
        pacificAtlanticDFS(matrix, aux, i, j - 1, matrix[i][j]);
        pacificAtlanticDFS(matrix, aux, i, j + 1, matrix[i][j]);
    }

    //542. 01 矩阵（动态规划）
    public int[][] updateMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = 10001;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j] + 1);
                }
                if (j > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1] + 1);
                }

            }
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if (i < matrix.length - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i + 1][j] + 1);
                }
                if (j < matrix[0].length - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
                }
            }
        }
        return matrix;
    }

    //542. 01 矩阵（BFS）
    public int[][] updateMatrixBFS(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    // 将所有 0 元素作为 BFS 第一层
                    queue.add(new int[]{i, j});
                } else {
                    matrix[i][j] = row + col;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            // 搜索上下左右四个方向
            for (int[] v : dicts) {
                int r = s[0] + v[0], c = s[1] + v[1];
                if (r >= 0 && r < row
                        && c >= 0 && c < col
                        && matrix[r][c] > matrix[s[0]][s[1]] + 1) {
                    matrix[r][c] = matrix[s[0]][s[1]] + 1;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return matrix;
    }

    //491. 递增子序列
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        int[] v = new int[201];
        findSubsequencesDFS(ans, nums, 0, new LinkedList<>(), v);
        return new ArrayList<>(ans);
    }

    public void findSubsequencesDFS(Set<List<Integer>> ans, int[] nums, int d, LinkedList<Integer> list, int[] v) {
        if (list.size() > 1) {
            ans.add(new LinkedList<>(list));
        }
        for (int i = d; i < nums.length; i++) {
            if (list.isEmpty()) {
                list.add(nums[i]);
            } else if (nums[i] >= list.peekLast()) {
                list.add(nums[i]);
            } else {
                continue;
            }
            findSubsequencesDFS(ans, nums, i + 1, list, v);
            list.removeLast();
        }
    }

    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            String s = String.valueOf(i);
            if ((s.length() & 1) == 0) {
                ans++;
            }
        }
        return ans;
    }

    boolean ans = true;

    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length < k || nums.length % k != 0) {
            return false;
        }

        Arrays.sort(nums);
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int needKSetCount = nums.length / k;
        int kCount = 0;
        for (int num : nums) {
            int curNumCount = countMap.get(num);
            if (curNumCount == 0) {
                continue;
            }
            countMap.put(num, curNumCount - 1);
            for (int i = 1; i < k; i++) {
                int count = countMap.getOrDefault(num + i, 0);
                if (count == 0) {
                    return false;
                }
                countMap.put(num + i, count - 1);
            }
            kCount++;
            if (kCount == needKSetCount) {
                return true;
            }
        }
        return true;
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        int start = 0;
        int end = minSize;
        Map<String, Integer> map = new HashMap<>();
        while (end <= s.length()) {
            String temp = s.substring(start, end);
            if (isUse(temp, maxLetters)) {
                int curlen = map.getOrDefault(temp, 0) + 1;
                map.put(temp, curlen);
                ans = Math.max(ans, curlen);
            }
            start++;
            end++;
        }

        return ans;
    }

    public boolean isUse(String s, int max) {
        int temp = 0;
        int[] h = new int[26];
        char[] c = s.toCharArray();
        for (char c1 : c) {
            if (h[c1 - 'a'] == 0) {
                temp++;
            }
            h[c1 - 'a']++;
            if (temp > max) {
                return false;
            }
        }
        return true;
    }

    //838. 推多米诺(队列)
    public String pushDominoesQueue(String dominoes) {
        char[] cs = dominoes.toCharArray();
        LinkedList<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (queue.isEmpty()) {
                if (c == 'L') {
                    sb.append(c);
                } else if (c == 'R') {
                    queue.add(c);
                } else {
                    queue.add(c);
                }
            } else {
                if (c == 'L') {
                    if (queue.peekLast() == 'R') {
                        sb.append(queue.removeLast());
                        sb.append(c);
                    } else if (queue.peek() == 'R') {
                        queue.add(c);
                        for (int i = 0; i < queue.size() / 2; i++) {
                            sb.append('R');
                        }
                        if ((queue.size() & 1) == 1) {
                            sb.append('.');
                        }
                        for (int i = 0; i < queue.size() / 2; i++) {
                            sb.append('L');
                        }
                        queue.clear();
                    } else {
                        queue.add(c);
                        for (int i = 0; i < queue.size(); i++) {
                            sb.append('L');
                        }
                        queue.clear();
                    }
                } else if (c == 'R') {
                    if (queue.peekLast() == 'R') {
                        sb.append(c);
                    } else if (queue.peek() == 'R') {
                        for (int i = 0; i < queue.size(); i++) {
                            sb.append('R');
                        }
                        queue.clear();
                        queue.add(c);
                    } else {
                        for (int i = 0; i < queue.size(); i++) {
                            sb.append('.');
                        }
                        queue.clear();
                        queue.add(c);
                    }
                } else {
                    queue.add(c);
                }
            }
        }
        if (!queue.isEmpty()) {
            if (queue.peek() == 'R') {
                for (int i = 0; i < queue.size(); i++) {
                    sb.append('R');
                }
            } else {
                for (int i = 0; i < queue.size(); i++) {
                    sb.append('.');
                }
            }
        }
        return sb.toString();
    }

    //845. 数组中的最长山脉(双指针，细节处理渣)
    public int longestMountain(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int start = 0;
        int end = 1;
        int ans = 1;
        int pre = A[0];
        boolean isDown;
        boolean isUped = false;
        isDown = A[end] < pre;
        while (end < A.length) {
            if (A[end] > pre) {
                if (isDown) {
                    start = end - 1;
                }
                isDown = false;
                isUped = true;
            } else if (A[end] < pre) {
                if (!isDown) {
                    isDown = true;
                }
                if (isUped) {
                    ans = Math.max(ans, end - start + 1);
                }
            } else {
                isDown = false;
                start = end;
                isUped = false;
            }
            pre = A[end];
            end++;
        }
        if (isDown && isUped) {
            ans = Math.max(ans, end - start);
        }
        if (ans > 2) {
            return ans;
        }
        return 0;
    }

    //838. 推多米诺(双指针)
    public String pushDominoes(String dominoes) {
        char[] c = dominoes.toCharArray();
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 0;
        while (end < c.length) {
            if (c[end] == '.') {
                end++;
            } else if (c[end] == 'L') {
                if (c[start] == 'R') {
                    int len = end - start + 1;
                    for (int i = 0; i < len / 2; i++) {
                        sb.append('R');
                    }
                    if ((len & 1) == 1) {
                        sb.append('.');
                    }
                    for (int i = 0; i < len / 2; i++) {
                        sb.append('L');
                    }

                } else {
                    int len = end - start + 1;
                    for (int i = 0; i < len; i++) {
                        sb.append('L');
                    }
                }
                end++;
                start = end;
            } else {
                if (c[start] == 'R' && start != end) {
                    int len = end - start;
                    for (int i = 0; i < len; i++) {
                        sb.append('R');
                    }
                } else if (c[start] == '.') {
                    int len = end - start;
                    for (int i = 0; i < len; i++) {
                        sb.append('.');
                    }
                }
                start = end;
                end++;
            }
        }
        if (start != end) {
            int len = end - start;
            if (c[start] == 'R') {
                for (int i = 0; i < len; i++) {
                    sb.append('R');
                }
            } else {
                for (int i = 0; i < len; i++) {
                    sb.append('.');
                }
            }
        }
        return sb.toString();
    }

    //todo 904. 水果成篮(待优化)
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> map = new HashMap<>();
        int end = 0;
        int start = 0;
        int ans = 0;
        while (end < tree.length) {
            map.put(tree[end], map.getOrDefault(tree[end], 0) + 1);
            while (map.size() > 2) {
                if (tree[start] != tree[end]) {
                    map.put(tree[start], map.get(tree[start]) - 1);
                    if (map.get(tree[start]) == 0) {
                        map.remove(tree[start]);
                    }
                    start++;
                }
            }
            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }

    //todo 923. 三数之和的多种可能
    public int threeSumMulti(int[] A, int target) {
        int[] h = new int[A.length];
        int ans = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length - 2; i++) {
            int temp = A[i];
            int start = i + 1;
            int end = A.length - 1;
            int curstart = 1;
            int curend = 1;
            while (start < end) {
                if (temp + A[start] + A[end] < target) {
                    start++;
                } else if (temp + A[start] + A[end] > target) {
                    end--;
                } else if (temp + A[start] + A[end] == target) {
                    if (A[start] == A[start + 1]) {
                        curstart++;
                        start++;
                    } else if (A[end] == A[end - 1]) {
                        end--;
                        curend++;
                    } else {
                        start++;
                        end--;
                        h[i] += curstart * curend;
                        curstart = 1;
                        curend = 1;
                    }
                    if (start == end && (curstart != 1 || curend != 1)) {
                        h[i]++;
                    }
                }
            }
        }
        for (int i : h) {
            ans += i;
        }
        return ans;
    }


    //全局倒置与局部倒置
    public boolean isIdealPermutation(int[] A) {
        if (A.length < 3) {
            return true;
        }
        int max = A[0];
        for (int i = 2; i < A.length; i++) {
            if (A[i] < max) {
                return false;
            }
            max = Math.max(max, A[i - 1]);

        }
        return true;
    }

    //todo 795. 区间子数组个数 待优化
    public int numSubarrayBoundedMaxOPT(int[] A, int L, int R) {
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            int max = A[i];
            if (max > R) {
                continue;
            }
            for (int j = i; j < A.length; j++) {
                if (A[j] > max) {
                    max = A[j];
                }
                if (max > R) {
                    break;
                }
                if (max >= L && max <= R) {
                    ans++;
                }
            }
        }
        return ans;
    }

    //795. 区间子数组个数
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int maxR = 0;
        int maxL = 0;
        int curNum = 0;
        for (int i : A) {
            if (i <= R) {
                curNum++;
                maxR += curNum;
            } else {
                curNum = 0;
            }
        }
        curNum = 0;
        for (int i : A) {
            if (i < L) {
                curNum++;
                maxL += curNum;
            } else {
                curNum = 0;
            }
        }
        return maxR - maxL;
    }

    //915. 分割数组
    public int partitionDisjoint(int[] A) {
        int ans = 0;
        int curMax = A[0];
        int leftMax = A[0];
        int index = 0;
        while (index < A.length) {
            if (leftMax > A[index]) {
                if (leftMax < curMax) {
                    leftMax = curMax;
                }
                ans = index + 1;
            }
            if (curMax < A[index]) {
                curMax = A[index];
            }
            index++;
        }
        if (ans == 0) {
            return 1;
        }
        return ans;
    }

    //769. 最多能完成排序的块（想不到）
    public int maxChunksToSorted(int[] arr) {
        if (arr.length == 1) {
            return 1;
        }
        int ret = 0;
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ret++;
            }
        }
        return ret;
    }

    //792. 匹配子序列的单词数
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        char[] c = S.toCharArray();
        HashSet<String> T = new HashSet<>();
        HashSet<String> F = new HashSet<>();
        for (String word : words) {
            if (T.contains(word)) {
                ans++;
                continue;
            } else if (F.contains(word)) {
                continue;
            }
            char[] h = word.toCharArray();
            int start = 0;
            int end = 0;
            while (end < c.length) {
                if (c[end] == h[start]) {
                    start++;
                    if (start == h.length) {
                        ans++;
                        break;
                    }
                }
                end++;
            }
            if (end == c.length) {
                F.add(word);
            } else {
                T.add(word);
            }
        }
        return ans;
    }

    //832. 翻转图像
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] ans = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            if ((A[i].length & 1) == 1) {
                ans[i][A[i].length / 2] = A[i][A[i].length / 2];
                ans[i][A[i].length / 2] ^= 1;
            }
            int start = 0;
            int end = A[i].length - 1;
            while (start < end) {
                ans[i][start] = A[i][end];
                ans[i][end] = A[i][start];
                ans[i][start] ^= 1;
                ans[i][end] ^= 1;
                start++;
                end--;
            }
        }
        return ans;
    }

    //926. 将字符串翻转到单调递增
    public int minFlipsMonoIncr(String S) {
        int[] h1 = new int[S.length()];
        int[] h2 = new int[S.length()];
        int count = 0;
        int min = Integer.MAX_VALUE;
        char[] C = S.toCharArray();
        for (int i = 0; i < C.length; i++) {
            if (C[i] == '1') {
                count++;
            }
            h1[i] = count;
        }
        count = 0;
        for (int i = C.length - 1; i >= 0; i--) {
            if (C[i] == '0') {
                count++;
            }
            h2[i] = count;
        }
        for (int i = 0; i < C.length; i++) {
            min = Math.min(min, h1[i] + h2[i]);
        }
        return min - 1;
    }

    //926. 将字符串翻转到单调递增
    public int minFlipsMonoIncrDP(String S) {
        int numOfOne = 0;
        int len = S.length();
        char[] c = S.toCharArray();
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            if (c[i] == '1') {
                dp[i] = (i == 0 ? 0 : Math.min(dp[i - 1], numOfOne + 1));
                numOfOne++;
            } else {
                dp[i] = (i == 0 ? 0 : Math.min(dp[i - 1] + 1, numOfOne));
            }

        }
        return dp[len - 1];
    }

    //448. 找到所有数组中消失的数字
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    //532. 数组中的K-diff数对
    public int findPairs(int[] nums, int k) {
        int ans = 0;
        Arrays.sort(nums);
        if (nums.length < 2) {
            return 0;
        }
        int start = 0;
        int end = 1;
        int old = Integer.MIN_VALUE;
        while (end < nums.length) {
            if (old == nums[end]) {
                end++;
                continue;
            }
            int temp = nums[end] - nums[start];
            old = nums[end];
            if (temp == k) {
                start++;
                end++;
                ans++;
            } else if (temp < k) {
                end++;
            } else if (temp > k) {
                start++;
                if (start == end) {
                    end++;
                }
            }
        }
        return ans;
    }

    //LCP 1. 猜数字
    public int game(int[] guess, int[] answer) {
        int ans = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == answer[i]) {
                ans++;
            }
        }
        return ans;
    }

    //771. 宝石与石头
    public int numJewelsInStones(String J, String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        for (char c : s) {
            if (J.indexOf(c) != -1) {
                ans++;
            }
        }
        return ans;
    }

    //1108. IP 地址无效化
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    //950. 按递增顺序显示卡牌
    public int[] deckRevealedIncreasing(int[] deck) {
        LinkedList<Integer> list = new LinkedList<>();
        Arrays.sort(deck);
        int[] ans = new int[deck.length];
        for (int i = deck.length - 1; i >= 0; i--) {
            if (list.size() < 2) {
                list.addFirst(deck[i]);
            } else {
                list.addFirst(list.removeLast());
                list.addFirst(deck[i]);
            }
        }
        out.println(list);
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    //5303. 解码字母到整数映射
    public String freqAlphabets(String s) {
        char[] c = s.toCharArray();
        int end = c.length - 1;
        StringBuilder sb = new StringBuilder();
        while (end >= 0) {
            if (c[end] == '#') {
                int temp = Integer.parseInt((String.valueOf(c[end - 2]))) * 10 + Integer.parseInt((String.valueOf(c[end - 1])));
                sb.insert(0, ((char) (96 + temp)));
                end -= 3;
            } else {
                int temp = Integer.parseInt((String.valueOf(c[end])));
                sb.insert(0, ((char) (96 + temp)));
                end--;
            }
        }
        return sb.toString();
    }

    //5304. 子数组异或查询
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] h = new int[arr.length + 1];
        int[] ans = new int[queries.length];
        h[0] = 0;
        for (int i = 1; i <= arr.length; i++) {
            h[i] = h[i - 1] ^ arr[i - 1];
        }
        for (int i = 0; i < queries.length; i++) {
            ans[i] = h[queries[i][1] + 1] ^ h[queries[i][0]];
        }
        return ans;
    }

    //5305. 获取你好友已观看的视频
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        List<String> ans = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        int[] v = new int[friends.length];
        queue.add(new int[]{id, 0});
        v[id] = 1;
        HashMap<String, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (level == cur[1]) {
                for (int i = 0; i < watchedVideos.get(cur[0]).size(); i++) {
                    if (!map.containsKey(watchedVideos.get(cur[0]).get(i))) {
                        ans.add(watchedVideos.get(cur[0]).get(i));
                    }
                    map.put(watchedVideos.get(cur[0]).get(i),
                            map.getOrDefault(watchedVideos.get(cur[0]).get(i), 0) + 1);
                }
            } else {
                for (int j = 0; j < friends[cur[0]].length; j++) {
                    if (v[friends[cur[0]][j]] == 0) {
                        v[friends[cur[0]][j]] = 1;
                        queue.add(new int[]{friends[cur[0]][j], cur[1] + 1});
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> h = new ArrayList<>(map.entrySet());
        Collections.sort(h, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().intValue() == o2.getValue().intValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o1.getValue() - o2.getValue();
            }
        });
        ans.clear();
        for (Map.Entry<String, Integer> entry : h) {
            ans.add(entry.getKey());
        }
        return ans;
    }

    //962. 最大宽度坡（偷鸡）
    public int maxWidthRampBAD(int[] A) {
        int ans = 0;
        int min = A[0];
        int index = 0;
        boolean reverse = true;
        int[] h = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > min) {
                reverse = false;
                h[i] = 1;
                ans = Math.max(i - index, ans);
            } else {
                index = i;
                min = A[i];
            }
        }
        if (reverse) {
            return 0;
        }
        for (int i = 0; i < A.length; i++) {
            if (h[i] == 1) {
                continue;
            }
            for (int j = 0; j < A.length; j++) {
                if (A[j] >= A[i]) {
                    ans = Math.max(j - i, ans);
                }
            }
        }
        return ans;
    }

    //962. 最大宽度坡
    public int maxWidthRamp(int[] A) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (A[stack.peek()] >= A[i]) {
                stack.push(i);
            }
        }
        for (int i = A.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[i] >= A[stack.peek()]) {
                ans = Math.max(ans, i - stack.pop());
            }
            if (stack.isEmpty()) {
                return ans;
            }
        }
        return ans;
    }

    //6. Z 字形变换
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        boolean odd = true;
        int index = 0;
        int len = 0;
        char[][] c = new char[numRows][(s.length() / (2 * numRows - 2)) * 2 + 2];
        for (char[] chars : c) {
            Arrays.fill(chars, '*');
        }
        char[] sc = s.toCharArray();
        for (int i = 0; i < sc.length; ) {
            if (odd) {
                c[len][index] = sc[i];
                len++;
                i++;
                if (len == numRows) {
                    index++;
                    len -= 2;
                    odd = false;
                }
            } else {
                if (len == 0) {
                    index++;
                    odd = true;
                } else {
                    c[len][index] = sc[i];
                    len--;
                    i++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                if (c[i][j] != '*') {
                    sb.append(c[i][j]);
                }
            }
        }
        return sb.toString();
    }

    //先序遍历与中序遍历构建二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, 0, preorder.length - 1, map);
    }

    public TreeNode buildTree(int[] preorder, int preLeft, int preRight, int inLeft, int inRight, Map<Integer, Integer> map) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int pivot = preorder[preLeft];
        TreeNode node = new TreeNode(pivot);
        int pivotIndex = map.get(pivot);
        node.left = buildTree(preorder, preLeft + 1, pivotIndex - inLeft + preLeft,
                inLeft, pivotIndex - 1, map);
        node.right = buildTree(preorder, pivotIndex - inLeft + preLeft + 1, preRight,
                pivotIndex + 1, inRight, map);
        return node;

    }

    //TODO 164. 最大间距（桶排序解法待优化）
    public int maximumGap(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        if (nums.length < 2) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }
        int ans = 0;
        int oldmin = min;
        int oldmax = max;
        for (int num : nums) {
            if (num == oldmax || num == oldmin) {
                continue;
            }
            if (max - num > num - min) {
                ans = max - num;
                min = num;
            } else {
                ans = num - min;
                max = num;
            }
        }
        return ans;
    }

    //424. 替换后的最长重复字符
    public int characterReplacement(String s, int k) {
        int[] h = new int[26];
        int ans = 0;
        char[] c = s.toCharArray();
        int start = 0;
        int end = 0;
        int max = 0;
        while (end < c.length) {
            h[c[end] - 65]++;
            max = Math.max(max, h[c[end] - 65]);
            while (end - start + 1 - max > k) {
                h[c[start] - 65]--;
                start++;
            }
            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }

    //784. 字母大小写全排列
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        char[] c = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        char[] h = new char[c.length];
        letterCasePermutationDFS(ans, c, 0, h);
        return ans;
    }

    public void letterCasePermutationDFS(List<String> list, char[] c, int index, char[] h) {
        if (index == c.length) {
            list.add(new String(h));
            return;
        }
        if (c[index] >= 65 && c[index] <= 122) {
            h[index] = Character.toUpperCase(c[index]);
            letterCasePermutationDFS(list, c, index + 1, h);
            h[index] = Character.toLowerCase(c[index]);
            letterCasePermutationDFS(list, c, index + 1, h);
        } else {
            h[index] = c[index];
            letterCasePermutationDFS(list, c, index + 1, h);
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        class NodeLevel {
            Node node;
            int level;
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<NodeLevel> queue = new LinkedList<>();
        NodeLevel nl = new NodeLevel();
        nl.node = root;
        nl.level = 0;
        queue.add(nl);
        int curLevel = 0;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            NodeLevel level = queue.poll();
            if (level.level != curLevel) {
                ans.add(new ArrayList<>(list));
                list.clear();
                curLevel++;
            }
            list.add(level.node.val);
            if (level.node.neighbors != null) {
                for (int i = 0; i < level.node.neighbors.size(); i++) {
                    NodeLevel temp = new NodeLevel();
                    temp.node = level.node.neighbors.get(i);
                    temp.level = level.level + 1;
                    queue.add(temp);
                }
            }
        }
        if (!list.isEmpty()) {
            ans.add(new ArrayList<>(list));
        }
        return ans;
    }

    //589. N叉树的前序遍历
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        preorderDFS(ans, root);
        return ans;
    }

    public void preorderDFS(List<Integer> list, Node root) {
        list.add(root.val);
        if (root.neighbors == null) {
            return;
        }
        for (int i = 0; i < root.neighbors.size(); i++) {
            preorderDFS(list, root.neighbors.get(i));
        }
    }

    //TODO 554. 砖墙（自己想的勉强AC代码）
    public int leastBricksLJ(List<List<Integer>> wall) {
        int ans = wall.size();
        int width = 0;
        for (List<Integer> list : wall) {
            for (Integer integer : list) {
                width += integer;
            }
            break;
        }
        int maxWidth = 0;
        List<Set<Integer>> setList = new ArrayList<>();
        for (int i = 0; i < wall.size(); i++) {
            int temp = 0;
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < wall.get(i).size(); j++) {
                temp += wall.get(i).get(j);
                if (j == wall.get(i).size() - 2) {
                    maxWidth = Math.max(temp, maxWidth);
                }
                set.add(temp);
            }
            setList.add(set);
        }
        for (int i = 1; i <= maxWidth; i++) {
            int temp = 0;
            for (int j = 0; j < wall.size(); j++) {
                if (!setList.get(j).contains(i)) {
                    temp++;
                }
            }
            ans = Math.min(temp, ans);
        }
        return ans;
    }

    //554. 砖墙
    public int leastBricks(List<List<Integer>> wall) {
        int ans = wall.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int temp = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                temp += list.get(i);
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        for (Integer integer : map.keySet()) {
            ans = Math.min(ans, wall.size() - map.get(integer));
        }
        return ans;
    }

    //459. 重复的子字符串
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int i = len / 2; i > 0; i--) {
            if (len % i == 0) {
                int end = i;
                while (s.substring(0, i).equals(s.substring(end, end + i))) {
                    end += i;
                    if (end == s.length()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //929. 独特的电子邮件地址
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            int end = email.lastIndexOf('@');
            int start = email.indexOf('+');
            String first = email.substring(0, end);
            String back = email.substring(end);
            if (start != -1) {
                first = first.substring(0, start);
            }
            set.add(first.replace(".", "") + back);
        }
        return set.size();
    }

    //872. 叶子相似的树
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        leafValDFS(root1, list1);
        leafValDFS(root2, list2);
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).intValue() != list2.get(i).intValue()) {
                return false;
            }
        }
        return true;
    }

    public void leafValDFS(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            list.add(node.val);
            return;
        }
        leafValDFS(node.left, list);
        leafValDFS(node.right, list);
    }

    //783. 二叉搜索树结点最小距离
    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        minDiffInDFS(root, list);
        int minDiff = Integer.MAX_VALUE;
        Collections.sort(list);
        for (int i = 1; i < list.size(); i++) {
            minDiff = Math.min(minDiff, list.get(i) - list.get(i - 1));
        }
        return minDiff;
    }

    public void minDiffInDFS(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        minDiffInDFS(root.left, list);
        minDiffInDFS(root.right, list);
    }

    //754. 到达终点数字
    public int reachNumber(int target) {
        int sum = 1;
        int index = 1;
        if (target < 0) {
            target = -target;
        }
        while (target > sum || ((sum - target) & 1) == 1) {
            index++;
            sum += index;
        }
        return index;
    }

    //1014. 最佳观光组合
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0;
        int preMax = A[0];
        for (int i = 1; i < A.length; i++) {
            ans = Math.max(ans, preMax + A[i] - i);
            preMax = Math.max(preMax, A[i] + i);
        }
        return ans;
    }

    //974. 和可被 K 整除的子数组
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

    //1018. 可被 5 整除的二进制前缀
    public List<Boolean> prefixesDivBy5(int[] A) {
        int sum = 0;
        List<Boolean> list = new ArrayList<>();
        for (int i : A) {
            sum = (sum * 2 + i) % 5;
            list.add(sum % 5 == 0);
        }
        return list;
    }

    //todo 1052. 爱生气的书店老板
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ans = 0;
        int slide = 0;
        int max = 0;
        int start = 0;
        int end = 0;
        int angry = 0;
        int curStart = 0;
        int curEnd = 0;
        while (end < X) {
            slide += customers[end];
            angry += grumpy[end];
            end++;
        }
        while (end < customers.length) {
            slide = slide + customers[end] - customers[start];
            if (angry != 0) {
                if (max < slide) {
                    max = slide;
                    curStart = start + 1;
                    curEnd = end;
                }
            }
            angry = angry + grumpy[end] - grumpy[start];
            end++;
            start++;
        }
        start = 0;
        end = customers.length - 1;
        while (start < curStart) {
            if (grumpy[start] == 0) {
                ans += customers[start];
            }
            start++;
        }
        while (end > curEnd) {
            if (grumpy[end] == 0) {
                ans += customers[end];
            }
            end--;
        }
        ans += max;
        return ans;
    }

    //1248. 统计「优美子数组」
    public int numberOfSubarrays(int[] nums, int k) {
        int ans = 0;
        int[] h = new int[nums.length + 1];
        int sum = 0;
        h[0] = 1;
        for (int num : nums) {
            if ((num & 1) == 1) {
                sum++;
            }
            if (sum - k >= 0 && h[sum - k] != 0) {
                ans += h[sum - k];
            }
            h[sum]++;
        }
        return ans;
    }

    //5307. 将整数转换为两个无零整数的和
    public int[] getNoZeroIntegers(int n) {
        int[] ans = new int[2];
        int start = 1;
        while (start < n) {
            if (isZero(start) && isZero(n - start)) {
                ans[0] = start;
                ans[1] = n - start;
                return ans;
            }
            start++;
        }
        return ans;
    }

    public boolean isZero(int n) {
        String s = String.valueOf(n);
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '0') {
                return false;
            }
        }
        return true;
    }


    //5308. 或运算的最小翻转次数
    public int minFlips(int a, int b, int c) {
        int ans = 0;
        StringBuilder aB = new StringBuilder(Integer.toBinaryString(a));
        StringBuilder bB = new StringBuilder(Integer.toBinaryString(b));
        StringBuilder cB = new StringBuilder(Integer.toBinaryString(c));
        int len = Math.max(aB.length(), Math.max(bB.length(), cB.length()));
        while (aB.length() < len) {
            aB.insert(0, '0');
        }
        while (bB.length() < len) {
            bB.insert(0, '0');
        }
        while (cB.length() < len) {
            cB.insert(0, '0');
        }
        char[] ah = aB.toString().toCharArray();
        char[] bh = bB.toString().toCharArray();
        char[] ch = cB.toString().toCharArray();
        int start = 0;
        while (start < len) {
            if (ah[start] == '1' && bh[start] == '1') {
                if (ch[start] != '1') {
                    ans += 2;
                }
            } else if (ah[start] == '1' || bh[start] == '1') {
                if (ch[start] != '1') {
                    ans++;
                }
            } else {
                if (ch[start] == '1') {
                    ans++;
                }
            }
            start++;
        }
        return ans;
    }

    //5143. 解压缩编码列表
    public int[] decompressRLElist(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length / 2; i++) {
            for (int j = 0; j < nums[i * 2]; j++) {
                ans.add(nums[i * 2 + 1]);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    //5145. 祖父节点值为偶数的节点和
    int sumAns = 0;

    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumEvenGrandparentDFS(root, root.left, root.right);
        return sumAns;
    }

    public void sumEvenGrandparentDFS(TreeNode root, TreeNode left, TreeNode right) {
        if (root == null) {
            return;
        }
        if (left != null) {
            if ((root.val & 1) == 0) {
                if (left.left != null) {
                    sumAns += left.left.val;
                }
                if (left.right != null) {
                    sumAns += left.right.val;
                }
            }
            sumEvenGrandparentDFS(left, left.left, left.right);
        }
        if (right != null) {
            if ((root.val & 1) == 0) {
                if (right.left != null) {
                    sumAns += right.left.val;
                }
                if (right.right != null) {
                    sumAns += right.right.val;
                }
            }
            sumEvenGrandparentDFS(right, right.left, right.right);
        }
    }

    //5144. 矩阵区域和
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int[][] h = new int[mat.length][mat[0].length];
        int[][] ans = new int[mat.length][mat[0].length];
        for (int i = 0; i < h.length; i++) {
            for (int j = 0; j < h[i].length; j++) {
                if (i == 0 && j == 0) {
                    h[i][j] = mat[i][j];
                } else if (i == 0) {
                    h[i][j] = h[i][j - 1] + mat[i][j];
                } else if (j == 0) {
                    h[i][j] = h[i - 1][j] + mat[i][j];
                } else {
                    h[i][j] = h[i - 1][j] + h[i][j - 1] - h[i - 1][j - 1] + mat[i][j];
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                int maxi = Math.min(i + K, ans.length - 1);
                int maxj = Math.min(j + K, ans[0].length - 1);
                int mini = i - K - 1;
                int minj = j - K - 1;
                if (mini >= 0 && minj >= 0) {
                    ans[i][j] = h[maxi][maxj] - h[mini][maxj] - h[maxi][minj] + h[mini][minj];
                } else if (mini >= 0) {
                    ans[i][j] = h[maxi][maxj] - h[mini][maxj];
                } else if (minj >= 0) {
                    ans[i][j] = h[maxi][maxj] - h[maxi][minj];
                } else {
                    ans[i][j] = h[maxi][maxj];
                }
            }
        }
        return ans;
    }

    //5146. 不同的循环子字符串(暴力枚举)
    public int distinctEchoSubstrings(String text) {
        int ans = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < text.length(); i++) {
            for (int j = i + 1; j <= text.length(); j++) {
                if (((j - i) & 1) == 1) {
                    continue;
                }
                String temp = text.substring(i, j);
                if (isEchoString(temp)) {
                    set.add(temp);
                }
            }
        }
        return set.size();
    }

    public boolean isEchoString(String s) {
        char[] c = s.toCharArray();
        int mid = c.length / 2;
        int start = 0;
        while (mid < c.length) {
            if (c[start] != c[mid]) {
                return false;
            }
            start++;
            mid++;
        }
        return true;
    }


    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = mat[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int k = 1; k <= Math.min(m, n); k++) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i - k < 0 || j - k < 0) {
                        continue;
                    }
                    int tmp = dp[i][j] - dp[i - k][j] - dp[i][j - k] + dp[i - k][j - k];
                    if (tmp <= threshold) {
                        ans = Math.max(ans, k);
                    }
                }
            }
        }
        return ans;
    }

    //961. 重复 N 次的元素
    public int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i : A) {
            if (set.contains(i)) {
                return i;
            }
            set.add(i);
        }
        return -1;
    }

    //1232. 缀点成线
    public boolean checkStraightLine(int[][] coordinates) {
        boolean sameX = true;
        boolean sameY = true;
        for (int i = 1; i < coordinates.length; i++) {
            if (coordinates[i][1] != coordinates[i - 1][1]) {
                sameY = false;
            }
            if (coordinates[i][0] != coordinates[i - 1][0]) {
                sameX = false;
            }
        }
        if (sameX || sameY) {
            return true;
        }
        float k = ((float) (coordinates[1][1] - coordinates[0][1])) / ((float) (coordinates[1][0] - coordinates[0][0]));
        for (int i = 2; i < coordinates.length; i++) {
            if (((((float) (coordinates[i][1] - coordinates[i - 1][1])) / ((float) (coordinates[i][0] - coordinates[i - 1][0])))) != k) {
                return false;
            }
        }
        return true;
    }

    //239. 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k];

        return ans;
    }

    //1026. 节点与其祖先之间的最大差值
    int maxAncestorAns = 0;

    public int maxAncestorDiff(TreeNode root) {
        maxAncestorDiffDFS(root, root.val, root.val);
        return maxAncestorAns;
    }

    public void maxAncestorDiffDFS(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        maxAncestorAns = Math.max(maxAncestorAns, max - min);
        maxAncestorDiffDFS(root.left, min, max);
        maxAncestorDiffDFS(root.right, min, max);
    }

    //1262. 可被三整除的最大和
    public int maxSumDivThree(int[] nums) {
        int minodd1 = 10001, minodd2 = 10001;
        int mineven1 = 10001, mineven2 = 10001;
        int sum = 0;
        for (int num : nums) {
            if (num % 3 == 1) {
                if (num < minodd1) {
                    minodd2 = minodd1;
                    minodd1 = num;
                } else if (num < minodd2) {
                    minodd2 = num;
                }
            } else if (num % 3 == 2) {
                if (num < mineven1) {
                    mineven2 = mineven1;
                    mineven1 = num;
                } else if (num < mineven2) {
                    mineven2 = num;
                }
            }
            sum += num;
        }
        if (sum % 3 == 1) {
            sum = Math.max(sum - minodd1, sum - mineven1 - mineven2);
        } else if (sum % 3 == 2) {
            sum = Math.max(sum - mineven1, sum - minodd1 - minodd2);
        }
        return sum;

    }

    //1300. 转变数组后最接近目标值的数组和
    public int findBestValue(int[] arr, int target) {
        int start = 0;
        int end = 100_000;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (bestSum(0, mid, arr) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (Math.abs(bestSum(0, start, arr) - target) < Math.abs(bestSum(0, start - 1, arr) - target)) {
            return start;
        }
        return start - 1;
    }

    //练习一下带返回值的递归
    public int bestSum(int i, int threshold, int[] arr) {
        if (i == arr.length) {
            return 0;
        }
        return bestSum(i + 1, threshold, arr) + Math.min(arr[i], threshold);
    }

    //1319. 连通网络的操作次数(无向图)
    public int makeConnectedgraph(int n, int[][] connections) {

        List<Integer>[] graph;

        boolean[] visited;

        if (connections.length < n - 1) {
            return -1;
        }
        // 构建无向图 初始化
        graph = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] connection : connections) {
            graph[connection[0]].add(connection[1]);
            graph[connection[1]].add(connection[0]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                makeConnectedDFS(i, graph, visited);
                res++;
            }
        }
        return res - 1;
    }

    private void makeConnectedDFS(int node, List<Integer>[] graph, boolean[] visited) {
        visited[node] = true;
        for (Integer nei : graph[node]) {
            if (!visited[nei]) {
                makeConnectedDFS(nei, graph, visited);
            }
        }
    }

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        int ans = 0;

        class UnionFind {
            private int[] parent;
            private int count;

            public UnionFind(int count) {
                this.count = count;
                parent = new int[count];
                for (int i = 0; i < count; i++) {
                    parent[i] = i;
                }
            }

            private int find(int x) {
                while (parent[x] != x) {
                    parent[x] = parent[parent[x]];
                    x = parent[x];
                }
//                return parent[x] == x ? x : (parent[x] = find(parent[x]));
                return x;
            }

            public boolean union(int x, int y) {
                int rootx = find(x);
                int rooty = find(y);
                if (rootx == rooty) {
                    return false;
                }
                parent[rootx] = rooty;
                count--;
                return true;
            }
        }

        UnionFind unionFind = new UnionFind(n);

        for (int[] connection : connections) {
            if (!unionFind.union(connection[0], connection[1])) {
                ans++;
            }
        }

        if (ans < unionFind.count - 1) {
            return -1;
        }

        return unionFind.count - 1;
    }

    //130.被围绕的区域（并查集）
    public void solve(char[][] board) {

        if (board.length == 0) {
            return;
        }

        class UnionFind {
            private int[] parent;

            public UnionFind(int n) {
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public void union(int x, int y) {
                int rootx = find(x);
                int rooty = find(y);
                if (rootx != rooty) {
                    parent[rootx] = rooty;
                }
            }

            public int find(int root) {
                return parent[root] == root ? root : (parent[root] = find(parent[root]));
//                while (root != parent[root]) {
//                    parent[root] = parent[parent[root]];
//                    root = parent[root];
//                }
//                return root;
            }

            public boolean isConnected(int x, int y) {
                return find(x) == find(y);
            }
        }

        int row = board.length;
        int col = board[0].length;

        UnionFind unionFind = new UnionFind(row * col + 1);
        int origin = row * col;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                        unionFind.union(i * col + j, origin);
                    } else {
                        if (i > 0 && board[i - 1][j] == 'O') {
                            unionFind.union(i * col + j, (i - 1) * col + j);
                        }
                        if (j > 0 && board[i][j - 1] == 'O') {
                            unionFind.union(i * col + j, i * col + j - 1);
                        }
                        if (j < col - 1 && board[i][j + 1] == 'O') {
                            unionFind.union(i * col + j, i * col + j + 1);
                        }
                        if (i < row - 1 && board[i + 1][j] == 'O') {
                            unionFind.union(i * col + j, (i + 1) * col + j);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (unionFind.isConnected(i * col + j, origin)) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    //200. 岛屿数量（并查集）
    public int numIslands(char[][] grid) {

        class UnionFind {
            private int[] parent;
            private int count;

            private UnionFind(char[][] grid) {
                int row = grid.length;
                int col = grid[0].length;
                parent = new int[row * col];
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[i][j] == '1') {
                            parent[i * col + j] = i * col + j;
                            count++;
                        }
                    }
                }
            }

            private int find(int node) {
                while (node != parent[node]) {
                    parent[node] = parent[parent[node]];
                    node = parent[node];
                }
                return parent[node];
            }

            private void union(int x, int y) {
                int rootx = find(x);
                int rooty = find(y);
                if (rootx != rooty) {
                    count--;
                    parent[rootx] = rooty;
                }
            }

        }

        int row = grid.length;
        int col = grid[0].length;
        UnionFind find = new UnionFind(grid);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    for (int[] dict : dicts) {
                        int dictx = dict[0];
                        int dicty = dict[1];
                        if (isInArea(grid, i + dictx, j + dicty) && grid[i + dictx][j + dicty] == '1') {
                            find.union(i * col + j, (i + dictx) * col + j + dicty);
                        }
                    }
                }
            }
        }

        return find.count;
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ans = new ArrayList<>();
        int[][] dicts = new int[][]{{1, 1}, {-1, 1}, {-1, -1}, {1, -1}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int[][] chess = new int[8][8];
        for (int[] queen : queens) {
            chess[queen[0]][queen[1]] = 1;
        }

        for (int[] dict : dicts) {
            queensAttacktheKingDFS(chess, dict, king[0], king[1], ans);
        }
        return ans;
    }

    public void queensAttacktheKingDFS(int[][] chess, int[] dict, int x, int y, List<List<Integer>> ans) {
        int newx = x + dict[0];
        int newy = y + dict[1];
        if (!isInArea(chess, newx, newy)) {
            return;
        }
        if (chess[newx][newy] == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(newx);
            list.add(newy);
            ans.add(list);
            return;
        }
        queensAttacktheKingDFS(chess, dict, newx, newy, ans);
    }

    //935. 骑士拨号器DFS
    int knightDialerAns = 0;

    public int knightDialer(int N) {
        if (N == 1) {
            return 10;
        }
        int[][] phone = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {-1, 0, -1}
        };
        for (int i = 0; i < phone.length; i++) {
            for (int j = 0; j < phone[0].length; j++) {
                if (phone[i][j] != -1) {
                    knightDialerDFS(i, j, 1, N, phone);
                }
            }
        }
        return knightDialerAns;
    }

    private boolean isInPhone(int[][] phone, int i, int j) {
        if (i < 0 || j < 0 || i >= phone.length || j >= phone[0].length) {
            return false;
        }
        if ((i == 3 && j == 0) || (i == 3 && j == 2) || (i == 1 && j == 1)) {
            return false;
        }
        return true;
    }

    private int[][] phoneDicts = new int[][]{{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, -2}, {-1, 2}, {-2, 1}, {-2, -1}};

    public void knightDialerDFS(int i, int j, int index, int N, int[][] phone) {
        if (index == N) {
            knightDialerAns++;
            knightDialerAns %= 100_000_007;
            return;
        }
        for (int[] dict : phoneDicts) {
            int newx = i + dict[0];
            int newy = j + dict[1];
            if (isInPhone(phone, newx, newy)) {
                knightDialerDFS(newx, newy, index + 1, N, phone);
            }
        }
    }

    //todo 935. 骑士拨号器DP（优化）
    public int knightDialerDP(int N) {
        if (N == 1) {
            return 10;
        }
        int ans = 0;
        int[][] phone = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        for (int k = 1; k < N; k++) {
            int[][] temp = new int[4][3];
            for (int i = 0; i < phone.length; i++) {
                for (int j = 0; j < phone[i].length; j++) {
                    for (int[] dict : phoneDicts) {
                        int newi = i + dict[0];
                        int newy = j + dict[1];
                        if (isInPhone(phone, newi, newy) && isInPhone(phone, i, j)) {
                            temp[newi][newy] += phone[i][j];
                            temp[newi][newy] %= 1_000_000_007;

                        }
                    }
                }
            }
            phone = temp;
        }
        for (int i = 0; i < phone.length; i++) {
            for (int j = 0; j < phone[i].length; j++) {
                ans += phone[i][j];
                ans %= 1_000_000_007;
            }
        }
        return ans;
    }

    //1219. 黄金矿工
    int maxGold = 0;

    public int getMaximumGold(int[][] grid) {
        int[][] v = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    getMaximumGoldDFS(i, j, grid, v, 0);
                }
            }
        }
        return maxGold;
    }

    public void getMaximumGoldDFS(int i, int j, int[][] grid, int[][] v, int curMax) {
        if (!isInArea(grid, i, j)) {
            return;
        }
        if (v[i][j] == 1 || grid[i][j] == 0) {
            return;
        }
        v[i][j] = 1;
        curMax += grid[i][j];
        maxGold = Math.max(maxGold, curMax);
        for (int[] dict : dicts) {
            int newi = i + dict[0];
            int newj = j + dict[1];
            getMaximumGoldDFS(newi, newj, grid, v, curMax);
        }
        v[i][j] = 0;
    }

    //598. 范围求和 II
    public int maxCount(int m, int n, int[][] ops) {
        int curM = m;
        int curN = n;
        for (int[] op : ops) {
            curM = Math.min(op[0], curM);
            curN = Math.min(op[1], curN);
        }
        return curM * curN;
    }

    //98. 验证二叉搜索树
    TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        return isValidDFS(root);
    }

    private boolean isValidDFS(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidDFS(root.left)) {
            return false;
        }
        if (pre != null && pre.val >= root.val) {
            return false;
        }
        pre = root;
        return isValidDFS(root.right);
    }

    //647. 回文子串
    public int countSubstrings(String s) {
        char[] c = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            if (i != dp.length - 1) {
                if (c[i + 1] == c[i]) {
                    dp[i][i + 1] = 1;
                    ans++;
                }
            }
            dp[i][i] = 1;
            ans++;
            int start = i - 1;
            int end = i + 1;
            while (start >= 0 && end <= dp.length - 1) {
                if (c[start] == c[end]) {
                    if (dp[start + 1][end - 1] == 1) {
                        dp[start][end] = 1;
                        ans++;
                    } else {
                        break;
                    }
                }
                start--;
                end++;
            }
            start = i - 1;
            end = i + 2;
            while (start >= 0 && end <= dp.length - 1) {
                if (c[start] == c[end]) {
                    if (dp[start + 1][end - 1] == 1) {
                        dp[start][end] = 1;
                        ans++;
                    } else {
                        break;
                    }
                }
                start--;
                end++;
            }
        }
        return ans;
    }

    //5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }
        char[] c = s.toCharArray();
        String ans = String.valueOf(c[0]);
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            if (i != dp.length - 1) {
                if (c[i + 1] == c[i]) {
                    dp[i][i + 1] = 1;
                    if (2 > ans.length()) {
                        ans = s.substring(i, i + 2);
                    }
                }
            }
            dp[i][i] = 1;
            int start = i - 1;
            int end = i + 1;
            while (start >= 0 && end <= dp.length - 1) {
                if (c[start] == c[end]) {
                    if (dp[start + 1][end - 1] == 1) {
                        dp[start][end] = 1;
                        if (end + 1 - start > ans.length()) {
                            ans = s.substring(start, end + 1);
                        }
                    } else {
                        break;
                    }
                }
                start--;
                end++;
            }
            start = i - 1;
            end = i + 2;
            while (start >= 0 && end <= dp.length - 1) {
                if (c[start] == c[end]) {
                    if (dp[start + 1][end - 1] == 1) {
                        dp[start][end] = 1;
                        if (end + 1 - start > ans.length()) {
                            ans = s.substring(start, end + 1);
                        }
                    } else {
                        break;
                    }
                }
                start--;
                end++;
            }
        }
        return ans;
    }

    //1144. 递减元素使数组呈锯齿状
    public int movesToMakeZigzag(int[] nums) {
        int odd = 0;
        int even = 0;
        for (int i = 0; i < nums.length; i++) {
            int min = Integer.MAX_VALUE;
            if (i - 1 >= 0) {
                min = Math.min(min, nums[i - 1]);
            }
            if (i + 1 < nums.length) {
                min = Math.min(min, nums[i + 1]);
            }
            if (nums[i] >= min) {
                if ((i & 1) == 1) {
                    odd += nums[i] - min + 1;
                } else {
                    even += nums[i] - min + 1;
                }
            }
        }
        return Math.min(odd, even);
    }

    //todo 1074. 元素和为目标值的子矩阵数量（优化）
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int ans = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + matrix[i - 1][j - 1] - dp[i - 1][j - 1];
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                for (int x = i; x < dp.length; x++) {
                    for (int y = j; y < dp[x].length; y++) {
                        if (x == i && y == j) {
                            if (dp[x][y] == target) {
                                ans++;
                            }
                        } else if (x == i) {
                            if (dp[x][y] - dp[x][y - j] == target) {
                                ans++;
                            }
                        } else if (y == j) {
                            if (dp[x][y] - dp[x - i][y] == target) {
                                ans++;
                            }
                        } else {
                            if (dp[x][y] - dp[x - i][y] - dp[x][y - j] + dp[x - i][y - j] == target) {
                                ans++;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    //1031. 两个非重叠子数组的最大和
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int res = 0;
        int len = A.length;
        int[] dp = new int[A.length + 1];
        for (int i = 1; i < A.length + 1; i++) {
            dp[i] = dp[i - 1] + A[i - 1];
        }
        int maxL = 0;
        int maxR = 0;
        for (int i = L; i + M <= len; ++i) {
            maxL = Math.max(maxL, dp[i] - dp[i - L]);
            res = Math.max(res, maxL + dp[i + M] - dp[i]);
        }
        for (int i = M; i + L <= len; ++i) {
            maxR = Math.max(maxR, dp[i] - dp[i - M]);
            res = Math.max(res, maxR + dp[i + L] - dp[i]);
        }
        return res;
    }

    //671. 二叉树中第二小的节点

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return -1;
        }
        int left = root.left.val;
        int right = root.right.val;

        if (left == root.val) {
            left = findSecondMinimumValue(root.left);
        }

        if (right == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }
        if (left != -1) {
            return left;
        }
        return right;
    }

    //5315. 6 和 9 组成的最大数字
    public int maximum69Number(int num) {
        char[] c = String.valueOf(num).toCharArray();
        int start = 0;
        while (start < c.length) {
            if (c[start] == '6') {
                c[start] = '9';
                break;
            }
            start++;
        }
        return Integer.valueOf(new String(c));
    }

    //5316. 竖直打印单词
    public List<String> printVertically(String s) {
        List<String> ans = new ArrayList<>();
        String[] temp = s.split(" ");
        int maxLen = 0;
        for (String s1 : temp) {
            maxLen = Math.max(s1.length(), maxLen);
        }
        for (int i = 0; i < maxLen; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].length() <= i) {
                    sb.append(" ");
                } else {
                    sb.append(temp[j].charAt(i));
                }
            }
            while (sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
            ans.add(new String(sb));
        }
        return ans;
    }

    //5317. 删除给定值的叶子节点
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        TreeNode ans = new TreeNode(0);
        ans.left = root;
        removeDFS(ans, target);
        return ans.left;
    }

    public void removeDFS(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        removeDFS(root.left, target);
        removeDFS(root.right, target);
        if (root.left != null && root.left.val == target && root.left.left == null && root.left.right == null) {
            root.left = null;
        }
        if (root.right != null && root.right.val == target && root.right.left == null && root.right.right == null) {
            root.right = null;
        }
    }

    //790. 多米诺和托米诺平铺
    public int numTilings(int N) {
        int[][] dp = new int[2][2];
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;
        dp[0][0] = 1;
        for (int i = 2; i <= N; i++) {
            int[][] dp1 = new int[2][2];
            dp1[0][0] = dp[1][1] % MOD;
            dp1[0][1] = (dp[0][0] % MOD + dp[1][0] % MOD) % MOD;
            dp1[1][0] = (dp[0][0] % MOD + dp[0][1] % MOD) % MOD;
            dp1[1][1] = ((dp[0][0] % MOD + dp[1][0] % MOD) % MOD + (dp[0][1] % MOD + dp[1][1] % MOD) % MOD) % MOD;
            dp = dp1;
        }
        return dp[1][1];
    }

    //todo 96. 不同的二叉搜索树(玩赖解法，找规律)
    public int numTrees(int n) {
        int[] ans = new int[]{1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700, 1767263190};
        return ans[n];
    }


    //226. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //todo 206. 反转链表(还是不理解这种递归)
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }


    //todo 115. 不同的子序列（DFS超时打击）
    public int numDistinct(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        return numDistinctDFS(sc, tc, 0, 0);
    }

    public int numDistinctDFS(char[] s, char[] c, int si, int ci) {
        if (ci == c.length) {
            return 1;
        }
        if (si == s.length) {
            return 0;
        }
        if (s.length - si < c.length - ci) {
            return 0;
        }
        int count = 0;
        if (s[si] == c[ci]) {
            count = numDistinctDFS(s, c, si + 1, ci + 1) + numDistinctDFS(s, c, si + 1, ci);

        } else {
            count = numDistinctDFS(s, c, si + 1, ci);
        }
        return count;
    }

    // 115. 不同的子序列（DP）
    public int numDistinctDP(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }

    //82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode ans = new ListNode(0);
        ans.next = head;
        ListNode pre = ans;
        ListNode cur = head;
        ListNode next = head.next;
        boolean isEqual = false;
        while (next != null) {
            if (cur.val == next.val) {
                next = next.next;
                isEqual = true;
            } else {
                if (isEqual) {
                    pre.next = next;
                    cur = next;
                    next = next.next;
                    isEqual = false;
                } else {
                    pre = cur;
                    cur = next;
                    next = next.next;
                }
            }
        }
        if (isEqual) {
            pre.next = next;
        }
        return ans.next;
    }

    public int guess(int num) {
        int pick = 1167880583;
        if (num > pick) {
            return -1;
        } else if (num < pick) {
            return 1;
        } else {
            return 0;
        }
    }

    //374. 猜数字大小
    public int guessNumber(int n) {
        int start = 0;
        int end = n + 1;
        int pick = (start + end) >>> 1;
        while (guess(pick) != 0) {
            if (guess(pick) == -1) {
                end = pick;
                pick = (start + end) >>> 1;
            } else {
                start = pick;
                pick = (start + end) >>> 1;
            }
        }
        return pick;
    }

    //todo 92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int m, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode ans = new ListNode(0);
        ans.next = head;
        int start = 1;
        while (head != null) {
            if (start >= m && start <= n) {
                stack.add(head);
            }
            head = head.next;
            start++;

        }

        return ans.next;
    }

    //530. 二叉搜索树的最小绝对差
    int minimumAns = Integer.MAX_VALUE;
    TreeNode rootPre = null;

    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceDFS(root);
        return minimumAns;
    }

    public void getMinimumDifferenceDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinimumDifferenceDFS(root.left);
        if (rootPre != null) {
            minimumAns = Math.min(Math.abs(root.val - rootPre.val), minimumAns);
        }
        rootPre = root;
        getMinimumDifferenceDFS(root.right);
    }

    //91. 解码方法
    public int numDecodings(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        char[] c = s.toCharArray();
        if (c[0] == '0') {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        return numDecodingsDFS(0, 0, c, map);
    }

    public int numDecodingsDFS(int i, int cur, char[] c, Map<String, Integer> map) {
        if (i > c.length) {
            return 0;
        }
        if (cur > 26) {
            return 0;
        }
        if (cur <= 26 && i == c.length) {
            return 1;
        }
        if (c[i] == '0') {
            return 0;
        }
        if (map.containsKey(i + "$" + (i + 1))) {
            return map.get(i + "$" + (i + 1));
        }
        cur = c[i] - '0';
        int x = numDecodingsDFS(i + 1, cur, c, map);
        int y = 0;
        if (i + 1 < c.length) {
            cur = cur * 10 + c[i + 1] - '0';
            y = numDecodingsDFS(i + 2, cur, c, map);
        }
        map.put(i + "$" + (i + 1), x + y);
        return x + y;
    }

    //744. 寻找比目标字母大的最小字母
    public char nextGreatestLetter(char[] letters, char target) {
        int min = Integer.MAX_VALUE;
        for (char letter : letters) {
            if (letter <= target) {
                min = Math.min(min, 26 - (target - letter));
            } else {
                min = Math.min(letter - target, min);
            }
        }
        if (target + min > 'z') {
            return ((char) (target + min - 26));
        }
        return ((char) (target + min));
    }

    //1218. 最长定差子序列
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i - difference)) {
                int temp = map.get(i - difference);
                temp++;
                ans = Math.max(temp, ans);
                map.put(i, temp);
            } else {
                map.put(i, 1);
            }
        }
        return ans;
    }

    //205. 同构字符串
    public boolean isIsomorphic(String s, String t) {
        return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
    }

    private boolean isIsomorphicHelper(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }
        return true;
    }

    //848. 字母移位
    public String shiftingLetters(String S, int[] shifts) {
        char[] temp = S.toCharArray();
        for (int i = shifts.length - 1; i >= 0; i--) {
            if (i == shifts.length - 1) {
                shifts[shifts.length - 1] %= 26;
            } else {
                shifts[i] = (shifts[i] % 26 + shifts[i + 1] % 26) % 26;
            }
            char cur = ((char) (temp[i] + shifts[i]));
            if (cur > 'z') {
                temp[i] = ((char) (cur - 'z' + 'a' - 1));
            } else {
                temp[i] = cur;
            }
        }
        return new String(temp);
    }

    //371. 两整数之和
    public int getSum(int a, int b) {
        while (b != 0) {
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }

    //todo 994. 腐烂的橘子
    public int orangesRotting(int[][] grid) {
        int ans = 0;
        return ans;
    }

    //5155.数组序号转换
    public int[] arrayRankTransform(int[] arr) {
        int[] h = new int[arr.length];
        System.arraycopy(arr, 0, h, 0, arr.length);
        Arrays.sort(arr);
        int[] ans = new int[arr.length];
        Map<Integer, Integer> map = new HashMap<>();
        int index = 1;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                map.put(arr[i], index++);
            } else {
                if (arr[i] != arr[i - 1]) {
                    map.put(arr[i], index++);
                }
            }
        }
        for (int i = 0; i < h.length; i++) {
            ans[i] = map.get(h[i]);
        }
        return ans;
    }

    //5320. 餐厅过滤器
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ans = new ArrayList<>(restaurants.length);
        Arrays.sort(restaurants, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[1] == o1[1]) {
                    return o2[0] - o1[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });
        for (int i = 0; i < restaurants.length; i++) {
            ans.add(restaurants[i][0]);
        }
        if (veganFriendly == 1) {
            for (int i = 0; i < restaurants.length; i++) {
                if (restaurants[i][2] == 0) {
                    ans.remove(((Integer) restaurants[i][0]));
                }
            }
        }
        for (int i = 0; i < restaurants.length; i++) {
            if (restaurants[i][3] > maxPrice) {
                ans.remove(((Integer) restaurants[i][0]));
            }
            if (restaurants[i][4] > maxDistance) {
                ans.remove(((Integer) restaurants[i][0]));
            }
        }

        return ans;
    }

    //1328. 破坏回文串
    public String breakPalindrome(String palindrome) {
        if (palindrome.isEmpty() || palindrome.length() == 1) {
            return "";
        }
        char[] c = palindrome.toCharArray();
        int start = 0;
        while (start < c.length / 2) {
            if (c[start] != 'a') {
                c[start] = 'a';
                break;
            }
            start++;
        }
        if (start == c.length / 2) {
            c[c.length - 1] = 'b';
        }
        return new String(c);
    }


    List<String> ws = new ArrayList<>();
    Set<String> set = new HashSet<>();

    public void dfs(int ll, int ls, int rl, int rs, String s, boolean dict) {
        if (ll < 0 || ls < 0 || rs < 0 || rl < 0) {
            return;
        }
        if (ls != 0 && ls > ll) {
            return;
        }
        if (rs != 0 && rs > rl) {
            return;
        }
        if (ll == 0 && ls == 0) {
            out.println(s);
        }
        if (set.contains("" + ll + ls + rl + rs)) {
            return;
        } else {
            set.add("" + ll + ls + rl + rs);
        }
        if (dict) {
            dict = !dict;
            dfs(ll - 1, ls - 1, rl + 1, rs + 1, s + "-l" + "-s" + "$R", dict);
            dfs(ll - 1, ls, rl + 1, rs, s + "-l" + "$R", dict);
            dfs(ll, ls - 1, rl, rs + 1, s + "-s" + "$R", dict);
            dfs(ll - 2, ls, rl + 2, rs, s + "-2l" + "$R", dict);
            dfs(ll, ls - 2, rl, rs + 2, s + "-2s" + "$R", dict);
        } else {
            dict = !dict;
            dfs(ll + 1, ls + 1, rl - 1, rs - 1, s + "-l" + "-s" + "$L", dict);
            dfs(ll + 1, ls, rl - 1, rs, s + "-l" + "$L", dict);
            dfs(ll, ls + 1, rl, rs - 1, s + "-s" + "$L", dict);
            dfs(ll + 2, ls, rl - 2, rs, s + "-2l" + "$L", dict);
            dfs(ll, ls + 2, rl, rs - 2, s + "-2s" + "$L", dict);
        }

    }

    public List<String> wolfSheep(int wolf, int sheep) {
        dfs(wolf, sheep, 0, 0, "$l", true);
        return ws;
    }

    public boolean isMatch(String s, String p) {
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int lens = sc.length;
        int lenp = pc.length;
        int[][] dp = new int[lenp + 1][lens + 1];
        dp[0][0] = 1;
        if (s.isEmpty()) {
            if (pc.length % 2 != 0) {
                return false;
            }
            int start = 1;
            while (start < lenp) {
                if (pc[start] != '*') {
                    return false;
                }
                start += 2;
            }
            return true;
        }
        for (int i = 1; i <= lenp; i++) {
            for (int j = 1; j <= lens; j++) {
                if (pc[i - 1] == '*') {
                    dp[i][0] = dp[i - 1][0];
                    dp[i][j] = dp[i - 1][j];
                } else if (i != lenp && pc[i] == '*') {
                    dp[i][0] = dp[i - 1][0];
                    if (pc[i - 1] == '.' || (pc[i - 1] == sc[j - 1])) {
                        dp[i][j] = dp[i - 1][j - 1] | dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else if (pc[i - 1] == sc[j - 1] || pc[i - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        for (int[] d : dp) {
            out.println(Arrays.toString(d));
        }
        return dp[lenp][lens] == 1;
    }

    public int minTaps(int n, int[] ranges) {
        int[] dp = new int[n + 2];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= n + 1; i++) {
            int left = Math.max(0, i - 1 - ranges[i - 1]) + 1;
            int right = Math.min(n + 1, i - 1 + ranges[i - 1]) + 1;
            if (dp[left - 1] == -1) {
                continue;
            }
            for (int j = left; j <= right; j++) {
                if (dp[j] == -1) {
                    dp[j] = dp[left - 1] + 1;
                } else {
                    dp[j] = Math.min(dp[j], dp[left - 1] + 1);
                }
            }
        }
        out.println(Arrays.toString(dp));
        return dp[n + 1];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int nu = 0;
        for (String s : strs) {
            if (s.isEmpty()) {
                nu++;
                continue;
            }
            char[] c = s.toCharArray();
            int one = 0;
            int zero = 0;
            for (char ch : c) {
                if (ch == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }
        for (int[] d : dp) {
            out.println(Arrays.toString(d));
        }
        return dp[m][n] + nu;
    }


    public int minimumDeleteSum(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int len1 = c1.length;
        int len2 = c2.length;
        int sum1 = 0;
        int sum2 = 0;
        for (char ch : c1) {
            sum1 += ch;
        }
        for (char ch : c2) {
            sum2 += ch;
        }
        int ans = 0;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + c1[i - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int d : dp[i]) {
                out.print(((char) d) + ",");
            }
            out.println();
        }
        return sum1 + sum2 - dp[len1][len2] * 2;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int ans = Integer.MAX_VALUE;
        // 当前节点，当前钱数以及步数
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] > K) {
                continue;
            } else if (cur[0] == dst) {
                ans = Math.min(ans, cur[2]);
                continue;
            }
            for (int[] flight : flights) {
                if (flight[0] == cur[0]) {
                    if (dst != flight[1]) {
                        queue.add(new int[]{flight[1], cur[1] + 1, cur[2] + flight[2]});
                    } else {
                        queue.add(new int[]{flight[1], cur[1], cur[2] + flight[2]});
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int numRollsToTarget(int d, int f, int target) {
        int[][] a = new int[d + 1][target + 1];
        int i;
        int j;
        int k;
        a[0][0] = 1;
        for (i = 1; i <= d; i++) {
            for (j = 1; j <= f; j++) {
                for (k = j; k <= target; k++) {
                    a[i][k] = (a[i][k] + a[i - 1][k - j]) % 1000000007;
                }
            }
        }
        return a[d][target];
    }

    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        boolean dict = true;
        int cur = 1000;
        for (int i = 0; i < len; i++) {
            if (nums[i] < cur && nums[i] > 1000) {
                continue;
            }
            int idx = i;
            dict = nums[idx] > 0;
            int step = 1;
            cur++;
            while (nums[idx] != cur) {
                if (nums[idx] < cur && nums[idx] > 1000) {
                    break;
                }
                int next = (idx + nums[idx] % len + len) % len;
                if (next == idx) {
                    break;
                }
                nums[idx] = cur;
                if (dict) {
                    if (nums[next] == cur && step > 1) {
                        return true;
                    } else if (nums[next] < 0) {
                        break;
                    }
                    step++;
                } else {
                    if (nums[next] == cur && step > 1) {
                        return true;
                    } else if (nums[next] > 0) {
                        break;
                    }
                    step++;
                }
                idx = next;
            }
        }
        return false;
    }

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != 0 && j != 0) {
                    continue;
                }
                List<Integer> list = new ArrayList<>();
                int x = i;
                int y = j;
                while (x < m && y < n) {
                    list.add(mat[x++][y++]);
                }
                Collections.sort(list);
                int z = 0;
                x = i;
                y = j;
                while (z < list.size()) {
                    mat[x++][y++] = list.get(z++);
                }
            }
        }
        return mat;
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int m = v1.length;
        int n = v2.length;
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            while (idx < v1[i].length()) {
                if (v1[i].charAt(idx) == '0') {
                    idx++;
                } else {
                    break;
                }
            }
            if (idx == v1[i].length()) {
                sb.append('0');
            } else {
                sb.append(v1[i].substring(idx, v1[i].length()));
            }
            v1[i] = sb.toString();
        }
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            while (idx < v2[i].length()) {
                if (v2[i].charAt(idx) == '0') {
                    idx++;
                } else {
                    break;
                }
            }
            if (idx == v2[i].length()) {
                sb.append('0');
            } else {
                sb.append(v2[i].substring(idx, v2[i].length()));
            }
            v2[i] = sb.toString();
        }
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (v1[i].equals(v2[j])) {
                i++;
                j++;
            } else if (v1[i].compareTo(v2[j]) < 0) {
                return -1;
            } else {
                return 1;
            }
        }
        while (i < m) {
            if (!"0".equals(v1[i])) {
                return 1;
            }
            i++;
        }
        while (j < n) {
            if (!"0".equals(v2[j])) {
                return -1;
            }
            j++;
        }
        return 0;
    }

    public String sortString(String s) {
        StringBuilder ans = new StringBuilder();
        boolean isSort = true;
        char[] c = s.toCharArray();
        int len = c.length;
        int[] h = new int[26];
        for (char ch : c) {
            h[ch - 'a']++;
        }
        int size = 0;
        int idx = 0;
        while (size < len) {
            if (isSort) {
                if (h[idx] != 0) {
                    ans.append((char) (idx + 'a'));
                    size++;
                    h[idx]--;
                }
                idx++;
                if (idx == 26) {
                    idx = 25;
                    isSort = false;
                }
            } else {
                if (h[idx] != 0) {
                    ans.append((char) (idx + 'a'));
                    size++;
                    h[idx]--;
                }
                idx--;
                if (idx == -1) {
                    idx = 0;
                    isSort = true;
                }
            }
        }
        return ans.toString();
    }


    int ans1 = 0;

    public void dfs(TreeNode root, int dict, int sum) {
        if (root == null) {
            return;
        }
        ans1 = Math.max(ans1, sum);
        if (dict == 0) {
            dfs(root.right, 1, sum + 1);
            dfs(root.left, -1, sum + 1);
        } else if (dict == -1) {
            dfs(root.left, 0, 1);
            dfs(root.right, 1, sum + 1);
        } else {
            dfs(root.left, -1, sum + 1);
            dfs(root.right, 0, 1);
        }
    }

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 0, 1);
        return ans1 - 1;
    }

    public int c2i(char c) {
        switch (c) {
            case 'a':
                return 0;
            case 'e':
                return 1;
            case 'i':
                return 2;
            case 'o':
                return 3;
            case 'u':
                return 4;
        }
        return -1;
    }

    public int findTheLongestSubstring(String s) {
        int[] status = new int[32];
        int n = s.length(), ans = 0;
        int[] cnt = new int[5];
        Arrays.fill(status, n);
        status[0] = -1;
        char[] c = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (c2i(c[i]) != -1) {
                cnt[c2i(c[i])]++;
            }
            int tmp = 0;
            for (int k = 0; k < 5; k++) {
                tmp += (1 << k) * (cnt[k] & 1);
            }
            if (status[tmp] == n) {
                status[tmp] = i;
            }
            ans = Math.max(ans, i - status[tmp]);
        }
        return ans;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        if (p.val == q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < N; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] path : paths) {
            graph.get(path[0] - 1).add(path[1] - 1);
            graph.get(path[1] - 1).add(path[0] - 1);
        }
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            boolean[] used = new boolean[5];
            for (int adj : graph.get(i)) {
                used[res[adj]] = true;
            }
            for (int j = 1; j <= 4; j++) {
                if (!used[j]) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    public TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = dfs(root.left);
        TreeNode right = dfs(root.right);
        root.left = null;
        root.right = null;
        if (left != null) {
            root.right = left;
        }
        TreeNode tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        tmp.right = right;
        return root;
    }

    public void flatten(TreeNode root) {
        dfs(root);
    }

    List<List<Integer>> list;
    int[] h;
    boolean anss = false;

    public void dfs(int x, int group) {
        if (h[x] == group) {
            return;
        }
        if (h[x] == -group) {
            anss = true;
            return;
        }
        h[x] = group;
        for (int i = 0; i < list.get(x).size(); i++) {
            dfs(list.get(x).get(i), -group);
        }
    }

    public boolean possibleBipartition(int N, int[][] dislikes) {
        h = new int[N + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= N + 1; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] dis : dislikes) {
            list.get(dis[0]).add(dis[1]);
            list.get(dis[1]).add(dis[0]);
        }

        for (int i = 1; i <= N; i++) {
            if (h[i] == 0) {
                anss = false;
                dfs(i, 1);
                if (anss) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] quickSort(int start, int end, int[] nums, int k) {
        int index = partion(start, end, nums);
        if (index == k) {
            return Arrays.copyOf(nums, k);
        }
        return end > k ? quickSort(start, index - 1, nums, k) : quickSort(index + 1, end, nums, k);
    }

    public int partion(int start, int end, int[] nums) {
        int pioxt = nums[start];
        while (start < end) {
            while (start < end) {
                if (pioxt > nums[end]) {
                    int tmp = nums[start];
                    nums[start] = nums[end];
                    nums[end] = tmp;
                    break;
                }
                end--;
            }
            while (start < end) {
                if (pioxt < nums[start]) {
                    int tmp = nums[start];
                    nums[start] = nums[end];
                    nums[end] = tmp;
                    break;
                }
                start++;
            }
        }
        return start;
    }

    public List<Integer> maxmin(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int max = nums[0];
        int min = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < max && nums[i] >= min) {
                ans.add(i);
                min = nums[i];
            }
            if (nums[i] > max) {
                min = max;
                max = nums[i];
            }
        }
        return ans;
    }

    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        int[][] dp = new int[n][n / 3 + 1];
        int res = 0;
        for (int i = 0; i < n - 1; i++) { // 不包含最后一个披萨
            for (int j = 0; j < n / 3; j++) {
                dp[i][j + 1] = Math.max(i > 0 ? dp[i - 1][j + 1] : 0, i - 2 >= 0 ? dp[i - 2][j] + slices[i] : slices[i]);
            }
            res = Math.max(res, dp[i][n / 3]);
        }

        out.println(Arrays.deepToString(dp));

        dp = new int[n][n / 3 + 1];
        for (int i = 1; i < n; i++) { // 不包含第一个披萨
            for (int j = 0; j < n / 3; j++) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], i - 2 >= 0 ? dp[i - 2][j] + slices[i] : slices[i]);
            }
            res = Math.max(res, dp[i][n / 3]);
        }
        out.println(Arrays.deepToString(dp));

        return res;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int i = 0; i < preorder.length; i++) {
            root = add(root, preorder[i]);
        }
        return root;
    }

    public TreeNode add(TreeNode root, int num) {
        if (root == null) {
            return new TreeNode(num);
        }
        if (root.val > num) {
            root.left = add(root.left, num);
        } else {
            root.right = add(root.right, num);
        }
        return root;
    }

    public int exchangeBits(int num) {
        int ans = 1;
        for (int i = 0; i < 32; i += 2) {
            int a = num & (1 << i);
            int b = num & (1 << (i + 1));
            out.println(a + "@" + b);
            ans |= (a << 1);
            ans |= (b >> 1);
            out.println(a + "@" + b);
        }
        return ans;
    }

    public int minimumSwap(String s1, String s2) {
        int ans = 0;
        int c1x = 0;
        int c2x = 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] == c2[i]) {
                continue;
            }
            if (c1[i] == 'A') {
                c1x++;
                if (c1x == 2) {
                    ans++;
                    c1x = 0;
                }
            }
            if (c2[i] == 'A') {
                c2x++;
                if (c2x == 2) {
                    ans++;
                    c2x = 0;
                }
            }
        }
        if (c1x != c2x) {
            return -1;
        }
        if (c1x == 1) {
            return ans + 2;
        } else {
            return ans;
        }
    }

    int[] arr;

    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new ArrayList<>();
        List<Integer> slist = new ArrayList<>();
        int len = asteroids.length;
        arr = asteroids;
        int s = 0;
        int e = len - 1;
        while (s < len) {
            if (arr[s] > 0) {
                break;
            }
            slist.add(arr[s]);
            s++;
        }
        while (e >= 0) {
            if (arr[e] < 0) {
                break;
            }
            list.add(0, arr[e]);
            e--;
        }
        if (s > e) {
            return arr;
        }
        Stack<Integer> stack = new Stack<>();
        out:
        while (s <= e) {
            // boolean flag = false;
            while (!stack.isEmpty() && arr[s] < 0) {
                int tmp = stack.peek();
                if (tmp == arr[s]) {
                    stack.pop();
                    s++;
                    continue out;
                } else if (tmp > -arr[s]) {
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty() && arr[s] < 0) {
                slist.add(arr[s]);
            }
            if (arr[s] > 0) {
                stack.push(arr[s]);
            }
            s++;
        }
        while (!stack.isEmpty()) {
            list.add(0, stack.pop());
        }
        int[] ans = new int[slist.size() + list.size()];
        out.println(slist);
        out.println(list);
        return ans;
    }

    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        int[] sum = new int[n + 1];
        int tmp = n;
        while (tmp >= K) {
            tmp = tmp - K + 1;
        }
        if (tmp != 1) {
            return -1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }
        out.println(Arrays.toString(sum));
        for (int l = K; l <= n; l += (K - 1)) { // 区间长度
            for (int i = 1; i <= n - l + 1; i++) { // 区间左端点
                int j = i + l - 1;//区间右端点
                if (j > n) {
                    break;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) { //中间点切分
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);
                }

                out.print(i + " + " + j + " ->" + dp[i][j]);
                out.println();
            }
        }
        for (int[] d : dp) {
            out.println(Arrays.toString(d));
        }
        return dp[1][n];
    }

    public int bag(int[] nums, int[] value, int max) {
        int n = nums.length;
        int[] dp = new int[max + 1];
        int[][] path = new int[n][max + 1];
        for (int i = 0; i < n; i++) {
            for (int j = max; j >= nums[i]; j--) {
                if (dp[j] < dp[j - nums[i]] + nums[i]) {
                    dp[j] = dp[j - nums[i]] + nums[i];
                    path[i][j] = 1;
                }
            }
        }

        for (int[] ints : path) {
            out.println(Arrays.toString(ints));
        }
        int i = n - 1, j = max; //V：背包容量。n个物品
        while (i >= 0 && j >= 0) {
            if (path[i][j] == 1)//物品i在j里
            {
                out.println(i);
                j -= nums[i];  //读完了物品i，找下一个背包状态
            }
            i--;
        }


        return dp[max];
    }

    int[] A;
    int len;

    public boolean splitArraySameAverage(int[] A) {
        int sum = 0;
        Arrays.sort(A);
        for (int a : A) {
            sum += a;
        }
        this.A = A;
        if (A.length == 1) {
            return false;
        }
        len = A.length;
        return dfs(len - 1, 0, sum, 0);
    }

    public boolean dfs(int i, int b, int c, int n) {
        if (i < 0) {
            return false;
        }
        if (n != 0 && n != len && ((double) b / (double) n) == (double) c / (double) (len - n)) {
            return true;
        }
        boolean ans = false;
        ans = dfs(i - 1, b + A[i], c - A[i], n + 1) || dfs(i - 1, b, c, n);
        return ans;
    }

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                if ((num & (1 << i)) == 1) {
                    cnt++;
                }
            }
//            System.out.println(cnt);
            if (cnt % 3 == 1) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public int add(int a, int b) {
        while (b != 0) {
            int tmp = a ^ b;
            b = (a & b) << 1;
            a = tmp;
        }
        return a;
    }

    int[] nums;
//    int ans1 = 0;

    public void megeSort(int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >>> 1;
        megeSort(left, mid);
        megeSort(mid + 1, right);
        int l = right - left + 1;
        int x = left;
        int j = mid + 1;
        int[] tmp = new int[l];
        int idx = 0;
        while (left <= mid && j <= right) {
            if (nums[left] <= nums[j]) {
                tmp[idx++] = nums[left++];
            } else {
                tmp[idx++] = nums[j++];
                ans1++;
            }
        }
        while (left <= mid) {
            tmp[idx++] = nums[left++];
        }
        while (j <= right) {
            tmp[idx++] = nums[j++];
            ans1++;
        }
        for (int i = 0; i < l; i++) {
            nums[i + x] = tmp[i];
        }
    }

    public int reversePairs(int[] nums) {
        this.nums = nums;
        megeSort(0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        return ans1;
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode odd = new ListNode(0);
        ListNode even = new ListNode(1);
        ListNode ans = odd;
        ListNode tmp = even;
        boolean is = true;
        while (head != null) {
            if (is) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }
            head = head.next;
            is = !is;
        }
        odd.next = tmp.next;
        return ans.next;
    }

    class Trie {
        public Trie[] next = new Trie[26];
        public boolean isEnd = false;

        public void insert(String word) {
            Trie root = this;
            char[] c = word.toCharArray();
            for (char ch : c) {
                if (root.next[ch - 'a'] == null) root.next[ch - 'a'] = new Trie();
                root = root.next[ch - 'a'];
            }
            root.isEnd = true;
        }

    }

    public int respace(String[] dict, String sen) {
        Trie trie = new Trie();
        for (String s : dict) {
            trie.insert(s);
        }
        char[] c = sen.toCharArray();
        int m = sen.length();
        int[] dp = new int[m + 1];
        for (int i = m - 1; i >= 0; i--) {
            Trie rt = trie;
            dp[i] = m - i;
            for (int j = i; j < m; j++) {
                if (rt.next[c[j] - 'a'] == null) {
                    dp[i] = Math.min(dp[i], j - i + 1 + dp[j + 1]);
                    break;
                }
                rt = rt.next[c[j] - 'a'];
                dp[i] = Math.min(dp[i], rt.isEnd ? dp[j + 1] : j - i + 1 + dp[j + 1]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[0];
    }

    public String reformat(String s) {
        char[] c = s.toCharArray();
        int m = c.length;
        String num = "";
        String wod = "";
        for (int i = 0; i < m; i++) {
            if (c[i] >= '0' && c[i] <= '9') {
                num += c[i];
            } else {
                wod += c[i];
            }
        }
        String ans = "";
        if (Math.abs(num.length() - wod.length()) > 1)
            return "";
        int nl = num.length();
        int wl = wod.length();
        if (nl > wl) {
            int i = 0;
            for (i = 0; i < wl; i++) {
                ans += num.charAt(i);
                ans += wod.charAt(i);
            }
            ans += num.charAt(i);
        } else {
            int i = 0;
            for (i = 0; i < nl; i++) {
                ans += num.charAt(i);
                ans += wod.charAt(i);
            }
            ans += wod.charAt(i);
        }
        return ans;
    }

    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            int i = 0, j = res;
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if (res == j) res++;
        }
        return res;
    }

    public int pileBox(int[][] boxs) {
        Arrays.sort(boxs, (a, b) -> (a[0] == b[0] ? a[1] == b[1] ? b[2] - a[2] : b[1] - a[1] : b[0] - a[0]));
        int n = boxs.length;
        int[][] tails = new int[n][3];
        int ans = 0;
        for (int[] box : boxs) {
            int i = 0, j = ans;
            while (i < j) {
                int m = (i + j) >>> 1;
                if (box[0] < tails[m][0] && box[1] < tails[m][1] && box[2] < tails[m][2]) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            tails[j] = box;
            if (j == ans)
                ans++;
        }
        return ans;
    }

    public int bestSeqAtIndex(int[] height, int[] weight) {
        int ans = 0;
        int len = height.length;
        int[][] merge = new int[len][2];
        for (int i = 0; i < len; i++) {
            merge[i][0] = height[i];
            merge[i][1] = weight[i];
        }
        Arrays.sort(merge, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int[][] tails = new int[len][2];
        for (int[] mg : merge) {
            int i = 0, j = ans;
            while (i < j) {
                int m = (i + j) >>> 1;
                if (tails[m][0] > mg[0] && tails[m][1] > mg[1]) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            tails[j] = mg;
            if (j == ans) ans++;
        }
        return ans;
    }

    //[100,-23,-23,404,100,23,23,23,3,404]
    //那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }

    public String reorganizeString(String S) {
        int[] map = new int[26];
        int[] seq = new int[26];
        char[] c = S.toCharArray();
        for (char ch : c) {
            map[ch - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int max = 0;
            int choice = -1;
            for (int i = 0; i < 26; i++) {
                if (map[i] > max && seq[i] == 0) {
                    max = map[i];
                    choice = i;
                }
            }
            if (choice == -1)
                break;
            sb.append((char) ('a' + choice));
            map[choice]--;
            for (int i = 0; i < 26; i++) {
                if (i == choice)
                    seq[i] = 1;
                else
                    seq[i] = 0;
            }
        }
        return sb.length() == S.length() ? sb.toString() : "";
    }

    public double champagneTower(int poured, int qr, int qg) {
        double[][] dp = new double[100][100];
        dp[0][0] = poured;
        for (int i = 1; i < 100; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) dp[i][j] = (Math.max(dp[i - 1][j], 1) - 1) / 2;
                else if (j == i) dp[i][j] = (Math.max(dp[i - 1][j - 1], 1) - 1) / 2;
                else
                    dp[i][j] = (Math.max(dp[i - 1][j], 1) - 1) / 2 + (Math.max(dp[i - 1][j - 1], 1) - 1) / 2;
            }
        }
        if (dp[qr][qg] >= 1) return 1;
        else return dp[qr][qg];
    }

    public int minTime(int[] piles, int m) {
        int max = Integer.MAX_VALUE;
        int min = 1;
        while (min < max) {
            int mid = min + (max - min) / 2;
            int x = 0;
            int i = 0;
            int k = mid;
            while (i < piles.length) {
                if (k - piles[i] >= 0) {
                    k -= piles[i];
                } else {
                    k = mid;
                    k -= piles[i];
                    x++;
                }
                i++;
            }
            if (x >= m)
                min = mid + 1;
            else
                max = mid;
        }
        int cur = 0;
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        System.out.println(min);
        for (int i = 0; i < piles.length; ) {
            sum += piles[i];
            cur = Math.max(cur, piles[i]);
            if (sum < min) {
                i++;
                continue;
            } else {
                sum = 0;
                ans = Math.max(ans, min - cur);
                i++;
            }
        }
        return ans;
    }

    //求和树
    public int sumTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            int z = root.val;
            root.val = 0;
            return z;
        }
        int left = sumTree(root.left);
        int right = sumTree(root.right);
        int tmp = left + right + root.val;
        root.val = left + right;
        return tmp;
    }

    public int findTheCity(int n, int[][] edges, int thre) {
        int[][] map = new int[n][n];
        for (int[] m : map) {
            Arrays.fill(m, 10001);
        }
        for (int[] e : edges) {
            map[e[0]][e[1]] = e[2];
            map[e[1]][e[0]] = e[2];
        }
        for (int i = 0; i < n; i++) {
            map[i][i] = 0;
        }
        int[] mem = new int[n];
        for (int i = 0; i < n; i++) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{i, 0});
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0];
                int dis = cur[1];
                for (int j = 0; j < n; j++) {
                    if (dis + map[x][j] <= map[i][j]) {
                        map[i][j] = dis + map[x][j];
                        queue.add(new int[]{j, dis + map[x][j]});
                    }
                }
            }
        }
        int min = n;
        int ans = n;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (map[i][j] <= thre) {
                    cnt++;
                }
            }
            if (cnt <= min) {
                min = cnt;
                ans = i;
            }
        }
        System.out.println(Arrays.deepToString(map));
        return ans;
    }



    public static void main(String[] args) {
        LeetCode1 t = new LeetCode1();
        int[] nums = new int[]{1, 2, 3, 3};
        int[] nums2 = new int[]{100, 150, 90, 190, 95, 110};
        int[] nums3 = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[][] boxs = new int[][]{
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}
        };

//        String s = "[10,-2,8,-4,6,7,5]";
//        TreeNode root = Codec.deserialize(s);

//        int[][] grid = new int[][]{};
//        out.println(t.reversePairs(nums2));
//        StringBuilder sb = new StringBuilder();
//        String[] s = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
//        System.out.println(t.findAllConcatenatedWordsInADict(s));
//        ListNode node = new ListNode(1);
//        node.next = new ListNode(2);
//        node.next.next = new ListNode(3);
//        node.next.next.next = new ListNode(4);
//        node.next.next.next.next = new ListNode(5);
//        node.next.next.next.next.next = new ListNode(4);
//        node.next.next.next.next.next.next = new ListNode(5);
//        node.next.next.next.next.next.next.next = new ListNode(5);
//        TreeNode node = new TreeNode(10);
//        node.left = new TreeNode(-2);
//        node.right = new TreeNode(6);
//        node.left.left = new TreeNode(8);
//        node.left.right = new TreeNode(-4);
//        node.right.left = new TreeNode(7);
//        node.right.right = new TreeNode(5);
//        node.right.right.left.right = new TreeNode(1);
//        node.right.right.left.right.right = new TreeNode(1);
//        t.sumTree(node);
//        out.println(Codec.serialize(node));
//        out.println(t.lowestCommonAncestor(node, node.left, node.left.right.right));
//        String s = "abcdefghijklmnopqrstuvwxyz";
//        char[] c = s.toCharArray();
//        for (int i = 0; i < c.length; i++) {
//            System.out.println(i + " " + c[i]);
//        }
//        System.out.println(t.findTheLongestSubstring("tyrwpvlifrgjghlcicyocusukhmjbkfkzsjhkdrtsztchhazhmcircxcauajyzlppedqyzkcqvffyeekjdwqtjegerxbyktzvrxwgfjnrfbwvhiycvoznriroroamkfipazunsabwlseseeiimsmftchpafqkquovuxhhkpvphwnkrtxuiuhbcyqulfqyzgjjwjrlfwwxotcdtqsmfeingsxyzbpvmwulmqfrxbqcziudixceytvvwcohmznmfkoetpgdntrndvjihmxragqosaauthigfjergijsyivozzfrlpndygsmgjzdzadsxarjvyxuecqlszjnqvlyqkadowoljrmkzxvspdummgraiutxxxqgotqnxwjwfotvqglqavmsnmktsxwxcpxhuujuanxueuymzifycytalizwnvrjeoipfoqbiqdxsnclcvoafqwfwcmuwitjgqghkiccwqvloqrxbfjuxwriltxhmrmfpzitkwhitwhvatmknyhzigcuxfsosxetioqfeyewoljymhdwgwvjcdhmkpdfbbztaygvbpwqxtokvidtwfdhmhpomyfhhjorsmgowikpsdgcbazapkmsjgmfyuezaamevrbsmiecoujabrbqebiydncgapuexivgvomkuiiuuhhbszsflntwruqblrnrgwrnvcwixtxycifdebgnbbucqpqldkberbovemywoaxqicizkcjbmbxikxeizmzdvjdnhqrgkkqzmspdeuoqrxswqrajxfglmqkdnlescbjzurknjklikxxqqaqdekxkzkscoipolxmcszbebqpsizhwsxklzulmjotkrqfaeivhsedfynxtbzdrviwdgicusqucczgufqnaslpwzjhgtphnovlrgz"));
//        node.right.left = new TreeNode(2);
//        node.right.right.right = new TreeNode(5);
//
//        ListNode node1 = t.reverseList(node);
//
//        Node node = new Node(1);
//        List<Node> list = new ArrayList<>();
//        Node node1 = new Node(3);
//        list.add(node1);
//        list.add(new Node(2));
//        list.add(new Node(4));
//        node.children = list;
//        List<Node> list1 = new ArrayList<>();
//        list1.add(new Node(5));
//        list1.add(new Node(6));
//        node1.children = list1;

    }
}
