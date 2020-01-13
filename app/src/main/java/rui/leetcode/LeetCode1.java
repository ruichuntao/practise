package rui.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class LeetCode1 {

    private int row;
    private int col;
    private int[][] dicts = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numWays(int steps, int arrLen) {
        int MOD = 100_000_007;
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
                if (temp > profit[i])
                    continue;
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
                if (index == worker.length)
                    break;
            }
        }
        for (int i = index; i < worker.length; i++) {
            ans += last;
        }
        return ans;
    }

    //98. 验证二叉搜索树(二叉树太难了)
    TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pre != null && pre.val >= root.val) return false;
        pre = root;
        return isValidBST(root.right);
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
                if (matrix[i][j] != 0)
                    matrix[i][j] = 10001;
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
        if (list.size() > 1)
            ans.add(new LinkedList<>(list));
        for (int i = d; i < nums.length; i++) {
            if (list.isEmpty())
                list.add(nums[i]);
            else if (nums[i] >= list.peekLast())
                list.add(nums[i]);
            else
                continue;
            findSubsequencesDFS(ans, nums, i + 1, list, v);
            list.removeLast();
        }
    }

    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            String s = String.valueOf(i);
            if ((s.length() & 1) == 0)
                ans++;
        }
        return ans;
    }

    boolean ans = true;

    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length < k || nums.length % k != 0)
            return false;

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
            if (temp > max)
                return false;
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
                if (c == 'L')
                    sb.append(c);
                else if (c == 'R') {
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
        if (A.length < 3)
            return 0;
        int start = 0;
        int end = 1;
        int ans = 1;
        int pre = A[0];
        boolean isDown;
        boolean isUped = false;
        isDown = A[end] < pre;
        while (end < A.length) {
            if (A[end] > pre) {
                if (isDown)
                    start = end - 1;
                isDown = false;
                isUped = true;
            } else if (A[end] < pre) {
                if (!isDown)
                    isDown = true;
                if (isUped)
                    ans = Math.max(ans, end - start + 1);
            } else {
                isDown = false;
                start = end;
                isUped = false;
            }
            pre = A[end];
            end++;
        }
        if (isDown && isUped)
            ans = Math.max(ans, end - start);
        if (ans > 2)
            return ans;
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
                    if (map.get(tree[start]) == 0)
                        map.remove(tree[start]);
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
        if (A.length < 3)
            return true;
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
            if (max > R)
                continue;
            for (int j = i; j < A.length; j++) {
                if (A[j] > max) {
                    max = A[j];
                }
                if (max > R)
                    break;
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
            } else
                curNum = 0;
        }
        curNum = 0;
        for (int i : A) {
            if (i < L) {
                curNum++;
                maxL += curNum;
            } else
                curNum = 0;
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
        if (ans == 0)
            return 1;
        return ans;
    }

    //769. 最多能完成排序的块（想不到）
    public int maxChunksToSorted(int[] arr) {
        if (arr.length == 1)
            return 1;
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
        if (nums.length < 2)
            return 0;
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
            if (guess[i] == answer[i])
                ans++;
        }
        return ans;
    }

    //771. 宝石与石头
    public int numJewelsInStones(String J, String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        for (char c : s) {
            if (J.indexOf(c) != -1)
                ans++;
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
            if (list.size() < 2)
                list.addFirst(deck[i]);
            else {
                list.addFirst(list.removeLast());
                list.addFirst(deck[i]);
            }
        }
        System.out.println(list);
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
                if (o1.getValue().intValue() == o2.getValue().intValue())
                    return o1.getKey().compareTo(o2.getKey());
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
        if (reverse)
            return 0;
        for (int i = 0; i < A.length; i++) {
            if (h[i] == 1)
                continue;
            for (int j = 0; j < A.length; j++) {
                if (A[j] >= A[i])
                    ans = Math.max(j - i, ans);
            }
        }
        return ans;
    }

    //962. 最大宽度坡
    public int maxWidthRamp(int[] A) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            if (stack.isEmpty())
                stack.push(i);
            else if (A[stack.peek()] >= A[i])
                stack.push(i);
        }
        for (int i = A.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[i] >= A[stack.peek()]) {
                ans = Math.max(ans, i - stack.pop());
            }
            if (stack.isEmpty())
                return ans;
        }
        return ans;
    }

    //6. Z 字形变换
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
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
                if (c[i][j] != '*')
                    sb.append(c[i][j]);
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
        if (preLeft > preRight || inLeft > inRight)
            return null;
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
        if (nums.length < 2)
            return 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }
        int ans = 0;
        int oldmin = min;
        int oldmax = max;
        for (int num : nums) {
            if (num == oldmax || num == oldmin)
                continue;
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
        if (root == null)
            return ans;
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
            if (level.node.children != null)
                for (int i = 0; i < level.node.children.size(); i++) {
                    NodeLevel temp = new NodeLevel();
                    temp.node = level.node.children.get(i);
                    temp.level = level.level + 1;
                    queue.add(temp);
                }
        }
        if (!list.isEmpty())
            ans.add(new ArrayList<>(list));
        return ans;
    }

    //589. N叉树的前序遍历
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        preorderDFS(ans, root);
        return ans;
    }

    public void preorderDFS(List<Integer> list, Node root) {
        list.add(root.val);
        if (root.children == null)
            return;
        for (int i = 0; i < root.children.size(); i++) {
            preorderDFS(list, root.children.get(i));
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
                    if (end == s.length())
                        return true;
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
        if (list1.size() != list2.size())
            return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).intValue() != list2.get(i).intValue())
                return false;
        }
        return true;
    }

    public void leafValDFS(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
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
        if (root == null)
            return;
        list.add(root.val);
        minDiffInDFS(root.left, list);
        minDiffInDFS(root.right, list);
    }

    //754. 到达终点数字
    public int reachNumber(int target) {
        int sum = 1;
        int index = 1;
        if (target < 0)
            target = -target;
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
            if (grumpy[start] == 0)
                ans += customers[start];
            start++;
        }
        while (end > curEnd) {
            if (grumpy[end] == 0)
                ans += customers[end];
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
        int ans[] = new int[2];
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
            if (c[i] == '0')
                return false;
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
                if (ch[start] != '1')
                    ans += 2;
            } else if (ah[start] == '1' || bh[start] == '1') {
                if (ch[start] != '1')
                    ans++;
            } else {
                if (ch[start] == '1')
                    ans++;
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
        if (root == null)
            return 0;
        sumEvenGrandparentDFS(root, root.left, root.right);
        return sumAns;
    }

    public void sumEvenGrandparentDFS(TreeNode root, TreeNode left, TreeNode right) {
        if (root == null)
            return;
        if (left != null) {
            if ((root.val & 1) == 0) {
                if (left.left != null)
                    sumAns += left.left.val;
                if (left.right != null)
                    sumAns += left.right.val;
            }
            sumEvenGrandparentDFS(left, left.left, left.right);
        }
        if (right != null) {
            if ((root.val & 1) == 0) {
                if (right.left != null)
                    sumAns += right.left.val;
                if (right.right != null)
                    sumAns += right.right.val;
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
                if (i == 0 && j == 0)
                    h[i][j] = mat[i][j];
                else if (i == 0) {
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
                if (((j - i) & 1) == 1)
                    continue;
                String temp = text.substring(i, j);
                if (isEchoString(temp))
                    set.add(temp);
            }
        }
        return set.size();
    }

    public boolean isEchoString(String s) {
        char[] c = s.toCharArray();
        int mid = c.length / 2;
        int start = 0;
        while (mid < c.length) {
            if (c[start] != c[mid])
                return false;
            start++;
            mid++;
        }
        return true;
    }

    //todo 5309. 连通网络的操作次数(并查集)
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1)
            return -1;
        int[] v = new int[connections.length];
        int[] h = new int[n];
        int ans = 0;
        for (int i = 0; i < h.length; i++) {
            if (h[i] == 0) {
                h[i] = 1;
                connectedDFS(v, connections, i, h);
                ans++;
            }
        }
        return ans - 1;
    }

    public void connectedDFS(int[] v, int[][] connections, int c, int[] h) {
        for (int i = 0; i < connections.length; i++) {
            if (v[i] == 1)
                continue;
            if (h[connections[i][0]] == 1) {
                h[connections[i][1]] = 1;
                v[i] = 1;
            }
            connectedDFS(v, connections, i + 1, h);
        }
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

    public static void main(String[] args) {
        LeetCode1 t = new LeetCode1();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] nums2 = new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int[] nums3 = new int[]{61, 33, 68, 38, 63, 45, 1, 10, 53, 23, 66, 70, 14, 51, 94, 18, 28, 78, 100, 16};
        int[][] matrix = new int[][]{{2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}};
//        TreeNode node = new TreeNode(6);
//        node.left = new TreeNode(7);
//        node.left.right = new TreeNode(7);
//        node.left.right.left = new TreeNode(1);
//        node.left.right.right = new TreeNode(4);
//        node.left.left = new TreeNode(2);
//        node.left.left.left = new TreeNode(9);
//        node.right = new TreeNode(8);
//        node.right.left = new TreeNode(1);
//        node.right.right = new TreeNode(3);
//        node.right.right.right = new TreeNode(5);
        System.out.println(t.maxSideLength(matrix, 2));

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
