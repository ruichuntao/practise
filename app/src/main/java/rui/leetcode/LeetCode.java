package rui.leetcode;

import android.text.SpannableStringBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class LeetCode {

    int[][] dict = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public String absc = "1241";

    public boolean isInArea(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
            return false;
        return true;
    }

    public static void reorderOddEven(int[] arr) {
        // 对于输入的数组为空，或者长度小于2的只接返回
        if (arr == null || arr.length < 2) {
            return;
        }

        // 从左向右记录偶数的位置
        int start = 0;
        // 从右向左记录奇数的位置
        int end = arr.length - 1;
        // 开始调整奇数和偶数的位置
        while (start < end) {
            // 找偶数
            while (start < end && arr[start] % 2 != 0) {
                start++;
            }
            // 找奇数
            while (start < end && arr[end] % 2 == 0) {
                end--;
            }

            // 找到后就将奇数和偶数交换位置
            // 对于start=end的情况，交换不会产生什么影响
            // 所以将if判断省去了
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }
    }

    public static void quickSort(int[] A, int left, int right) {

        if (left < right) {
            // 一次划分
            int mid = partion(A, left, right);
            quickSort(A, 0, mid - 1);
            quickSort(A, mid + 1, right);
        }
    }

    public static void swap(int[] A, int l, int r) {
        int tmp = A[l];
        A[l] = A[r];
        A[r] = tmp;

    }

    public static int partion(int[] a, int left, int right) {
        // 轴值，默认选取数组的第一个数字
        while (left < right) {
            while (left < right && a[left] <= a[right]) {
                right--;
            }
            if (left < right) {
                swap(a, left, right);
            }
            while (left < right && a[left] <= a[right]) {
                left++;
            }
            if (left < right) {
                swap(a, left, right);
            }
        }
        return left;
    }


    public static void hebing() {
        int a[] = {1, 13, 15, 17, 19};
        int b[] = {2, 4, 6, 8, 10};
        int c[] = new int[a.length + b.length];
        int x = 0, y = 0;
        for (int i = 0; i < c.length; i++) {
            if (x == a.length) {
                System.arraycopy(b, y, c, i, c.length - i);
                break;
            }
            if (y == b.length) {
                System.arraycopy(a, x, c, i, c.length - i);
                break;
            }
            if (a[x] < b[y]) {
                c[i] = a[x];
                x++;
            } else {
                c[i] = b[y];
                y++;
            }
        }
//        if (x ==a.length){
//            for (int i = y; i < b.length; i++) {
//                c[i+a.length] = b[y++];
//            }
//        }else if (y==b.length){
//            for (int i = x; i < a.length; i++) {
//                c[i+b.length] = a[x++];
//            }
//        }
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
        }
    }

    public static int[] merge(int[] a, int[] b) {
        int lena = a.length;
        int lenb = b.length;
        int[] c = new int[lena + lenb];
        int i = 0, j = 0, k = 0;//分别代表数组a ,b , c 的索引
        while (i < lena && j < lenb) {
            if (a[i] < b[j])
                c[k++] = a[i++];
            else
                c[k++] = b[j++];
        }
        while (i < lena)
            c[k++] = a[i++];
        while (j < lenb)
            c[k++] = b[j++];
        return c;
    }

    public static void permutation(char[] chars, int begin) {
        // 如果是最后一个元素了，就输出排列结果
        if (chars.length - 1 == begin) {
            System.out.print(new String(chars) + " ");
        } else {
            char tmp;
            // 对当前还未处理的字符串进行处理，每个字符都可以作为当前处理位置的元素
            for (int i = begin; i < chars.length; i++) {
                // 下面是交换元素的位置
                tmp = chars[begin];
                chars[begin] = chars[i];
                chars[i] = tmp;

                // 处理下一个位置
                permutation(chars, begin + 1);
            }
        }
    }


    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i];
            int j = i - 1;
            //从右向左比较元素的同时，进行元素复制
            for (; j >= 0 && insertValue < array[j]; j--) {
                array[j + 1] = array[j];
            }
            //insertValue的值插入适当位置
            array[j + 1] = insertValue;
        }
    }

    private static int[] hebing(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];

        return c;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = l1.val + l2.val;
        int d = 0;
        if (c >= 10) {
            c -= 10;
            d = 1;
        }
        ListNode node = new ListNode(c);
        ListNode head = node;
        while (l1.next != null || l2.next != null) {
            int a = 0, b = 0;
            if (l1.next != null) {
                a = l1.next.val;
                l1 = l1.next;
            }
            if (l2.next != null) {
                b = l2.next.val;
                l2 = l2.next;
            }
            c = a + b + d;
            ListNode node1 = new ListNode(0);
            if (c >= 10) {
                c -= 10;
                node1.val = c;
                d = 1;
            } else {
                node1.val = c;
                d = 0;
            }
            node.next = node1;
            node = node1;
        }
        ListNode node1 = new ListNode(node.val);
        if (d != 0) {
            node1.val = d;
            node.next = node1;
        }
        return head;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    private int max = 0;
    private List<Character> list = new ArrayList<>();

    public int longest(String s) {
        if (s.length() == 0) {
            return max;
        }
        for (int i = 0; i < s.length(); i++) {
            if (list.isEmpty()) {
                list.add(s.charAt(i));
                if (list.size() >= max) {
                    max = list.size();
                }
            } else if (list.contains(s.charAt(i))) {
                if (list.size() >= max) {
                    max = list.size();
                }
                list.clear();
                break;
            } else {
                list.add(s.charAt(i));
                if (list.size() >= max) {
                    max = list.size();
                }
            }
        }
        return longest(s.substring(1));
    }


    public boolean isHui(String s) {
        boolean hui = true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                hui = false;
            }
        }
        return hui;
    }

    public String convertNew(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    //我的二维数组版本
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int length = s.length();
        int one = numRows * 2 - 2;
        int num = length / one * 2;
        if (length < one) {
            num = 1;
        }
        int yu = length % one;
        if (yu > numRows) {
            num += 2;
        } else {
            num += 1;
        }
        char[][] shuzu = new char[numRows][num];
        int site = 0;
        for (int i = 0; i < num; i++) {
            int x = 0;
            for (int j = 0; j < numRows; j++) {
                if (site == s.length()) {
                    break;
                }
                if (i % 2 != 0) {
                    if (numRows == 2) {
                        break;
                    }
                    x++;
                    shuzu[numRows - j - 2][i] = s.charAt(site++);
                    if (x == numRows - 2) {
                        break;
                    }
                } else
                    shuzu[j][i] = s.charAt(site++);
            }
        }
        char c[] = new char[length];
        int cc = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < num; j++) {
                if (shuzu[i][j] != 0)
                    c[cc++] = shuzu[i][j];
                System.out.print(shuzu[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println(String.valueOf(c));
        return String.valueOf(c);
    }

    public int reverse(int x) {
        if (x == -2147483647) {
            return 0;
        }
        String s = String.valueOf(x < 0 ? -x : x);
        Stack<Character> stack = new Stack<>();
        int l = s.length();
        int i = 0;
        while (i < l) {
            stack.add(s.charAt(i));
            i++;
        }
        char[] c = new char[l];
        int t = 0;
        while (!stack.empty()) {
            c[t] = stack.pop();
            t++;
        }
        long l1 = Long.valueOf(String.valueOf(c));
        if (l1 > Integer.MAX_VALUE) {
            return 0;
        }
        if (x < 0)
            return -Integer.valueOf(String.valueOf(c));
        else
            return Integer.valueOf(String.valueOf(c));
    }

    public int reverseLeet(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public int myAtoi(String str) {
        str = str.trim();
        if (str.equals("") || str.equals("-") || str.equals("+")) {
            return 0;
        }
        if (str.charAt(0) == '+') {
            str = str.substring(1);
            if (str.charAt(0) < '0' || str.charAt(0) > '9') {
                return 0;
            }
        }
        if (str.charAt(0) != '-' && str.charAt(0) != '+') {
            if (str.charAt(0) < '0' || str.charAt(0) > '9') {
                return 0;
            }
        }
        int a = 0;
        StringBuilder builder = new StringBuilder();
        if (str.charAt(0) == '-') {
            builder.append(str.charAt(0));
            String ss = str.substring(1);
            while (ss.charAt(0) == '0') {
                ss = ss.substring(1);
                if (ss.equals("")) {
                    return 0;
                }
            }
            str = "-" + ss;
            a = 1;
            while (str.charAt(a) >= '0' && str.charAt(a) <= '9') {
                builder.append(str.charAt(a));
                a++;
                if (a == str.length()) {
                    break;
                }
            }
        } else {
            while (str.charAt(0) == '0') {
                str = str.substring(1);
                if (str.equals("")) {
                    return 0;
                }
            }
            while (str.charAt(a) >= '0' && str.charAt(a) <= '9') {
                builder.append(str.charAt(a));
                a++;
                if (a == str.length()) {
                    break;
                }
            }
        }
        str = builder.toString();
        if (str.equals("-") || str.equals("+") || str.equals("")) {
            return 0;
        }
        if (str.length() >= 12 && str.charAt(0) == '-') {
            return -2147483648;
        } else if (str.length() >= 11 && str.charAt(0) != '-') {
            return 2147483647;
        }
        long l = Long.valueOf(str);
        if (l > Integer.MAX_VALUE) {
            return 2147483647;
        } else if (l < Integer.MIN_VALUE) {
            return -2147483648;
        } else {
            return Integer.valueOf(str);
        }
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int y = 0;
        int n = x;
        while (x > 0) {
            int z = x % 10;
            x /= 10;
            y = y * 10 + z;
        }
        return n == y;
    }

    //盛水最多的容器(自己双循环版本)
    public int maxAreaLJ(int[] height) {
        int maxA = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                if (height[i] < height[j])
                    maxA = maxA > height[i] * (j - i) ? maxA : height[i] * (j - i);
                else
                    maxA = maxA > height[j] * (j - i) ? maxA : height[j] * (j - i);
            }
        }
        return maxA;
    }

    //盛水最多的容器
    public int maxArea(int[] height) {
        int max = 0;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            if (height[start] < height[end]) {
                int temp = height[start] * (end - start);
                if (temp > max)
                    max = temp;
                start++;
            } else {
                int temp = height[end] * (end - start);
                if (temp > max)
                    max = temp;
                end--;
            }
        }
        return max;
    }


    public int maxAreaLeet(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    //失败
//    public int maxArea1(int[] height, int n) {
//        if (n == 1) {
//            if (height[0] < height[1])
//                return height[0] * height[0];
//            else
//                return height[1] * height[1];
//        }
//        if (height[n - 1] * height[0] > height[n - 1] * height[n - 2]) {
//            if (height[0] < height[n - 1])
//                maxA = height[0] * height[0] > maxA ? height[0] * height[0] : maxA;
//            else
//                maxA = height[n - 1] * height[n - 1] * (n - 2) > maxA ? height[n - 1] * height[n - 1] : maxA;
//        } else {
//            if (height[n - 2] < height[n - 1])
//                maxA = height[n - 2] * height[n - 2] > maxA ? height[n - 2] * height[n - 2] : maxA;
//            else
//                maxA = height[n - 1] * height[n - 1] > maxA ? height[n - 1] * height[n - 1] : maxA;
//        }
//        int x = maxArea1(height, n - 1);
//        if (maxA < x) {
//            return x;
//        } else {
//            return maxA;
//        }
//    }

    public String intToRoman(int num) {
        Stack<Character> strings = new Stack<>();
        int yu = num % 10;
        if (yu == 9) {
            strings.add('X');
            strings.add('I');
        } else if (yu >= 5) {
            for (int i = 0; i < yu - 5; i++) {
                strings.add('I');
            }
            strings.add('V');
        } else if (yu == 4) {
            strings.add('V');
            strings.add('I');
        } else {
            for (int i = 0; i < yu; i++) {
                strings.add('I');
            }
        }
        yu = (num % 100) / 10;
        if (yu == 9) {
            strings.add('C');
            strings.add('X');
        } else if (yu >= 5) {
            for (int i = 0; i < yu - 5; i++) {
                strings.add('X');
            }
            strings.add('L');
        } else if (yu == 4) {
            strings.add('L');
            strings.add('X');
        } else {
            for (int i = 0; i < yu; i++) {
                strings.add('X');
            }
        }
        yu = (num % 1000) / 100;
        if (yu == 9) {
            strings.add('M');
            strings.add('C');
        } else if (yu >= 5) {
            for (int i = 0; i < yu - 5; i++) {
                strings.add('C');
            }
            strings.add('D');
        } else if (yu == 4) {
            strings.add('D');
            strings.add('C');
        } else {
            for (int i = 0; i < yu; i++) {
                strings.add('C');
            }
        }
        yu = num / 1000;
        for (int i = 0; i < yu; i++) {
            strings.add('M');
        }
        StringBuilder builder = new StringBuilder();
        while (!strings.empty()) {
            builder.append(strings.pop());
        }
        return builder.toString();
    }

    //垃圾暴力枚举
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        boolean qzero = false;
        for (int i = 0; i < nums.length - 2; i++) {
            int x = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                int y = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int z = nums[k];
                    if (x + y + z == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(x);
                        list.add(y);
                        list.add(z);
                        if (x == 0 && y == 0 && z == 0 && !qzero) {
                            lists.add(list);
                            qzero = true;
                            set.add(list);
                            break;
                        }
                        boolean no = true;
                        for (List list1 : set) {
                            if (list1.contains(x) && list1.contains(y) && list1.contains(z)) {
                                no = false;
                                break;
                            }
                        }
                        if (no)
                            lists.add(list);
                        set.add(list);
                    }
                }
            }
        }
        return lists;
    }

    //阿里算法
    public List<List<Integer>> threeSumAli(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if (nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    L++;
                    R--;
                } else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = target;
        int len = nums.length;
        int min = nums[0] + nums[1] + nums[2];
        int max = nums[len - 3] + nums[len - 2] + nums[len - 1];
        for (int i = 0; i < len; i++) {
            if (nums[i] >= max) break;
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    if (sum > min) {
                        min = sum;
                    }
                    L++;
                } else if (sum > target) {
                    if (sum < max) {
                        max = sum;
                    }
                    R--;
                }
            }
        }
        if (ans - min > max - ans) {
            ans = max;
        } else {
            ans = min;
        }
        return ans;
    }

    private List<List<String>> lists = new ArrayList<>();
    private ArrayList<String> linkedList = new ArrayList<>();

    public void huisu(int start, ArrayList nums) {
        if (start == nums.size()) {
            lists.add(new ArrayList<>(nums));
        }
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            huisu(start + 1, nums);
            Collections.swap(nums, start, i);
        }
    }

    //交换全排列
    public List<List<String>> permute(String[] nums) {
        for (String num : nums) {
            linkedList.add(num);
        }
        huisu(0, linkedList);
        return lists;
    }

    //  4数之和双指针
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length < 4)
            return lists;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            int x = nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (nums[j] == nums[j - 1]) continue;
                int L = j + 1;
                int R = nums.length - 1;
                int y = nums[j];
                while (L < R) {
                    if (L > j + 1 && nums[L - 1] == nums[L]) {
                        L++;
                        continue;
                    }
                    int sum = x + y + nums[L] + nums[R];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(x);
                        list.add(y);
                        list.add(nums[L]);
                        list.add(nums[R]);
                        lists.add(list);
                        L++;
                        R--;
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }
        return lists;
    }

    //回溯法解决电话号码的字母组合，芮春涛牛逼
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.equals("")) {
            return list;
        }
        String s[] = new String[10];
        s[2] = "abc";
        s[3] = "def";
        s[4] = "ghi";
        s[5] = "jkl";
        s[6] = "mno";
        s[7] = "pqrs";
        s[8] = "tuv";
        s[9] = "wxyz";
        letterDG(list, digits, 0, new StringBuilder(), s);
        return list;
    }

    public void letterDG(List<String> list, String digits, int n, StringBuilder sb, String s[]) {
        if (sb.length() == digits.length()) {
            list.add(sb.toString());
            return;
        }
        for (int i = n; i < digits.length(); i++) {
            int d = Integer.valueOf(String.valueOf(digits.charAt(i)));
            for (int j = 0; j < 4; j++) {
                if (d != 7 && d != 9) {
                    if (j == 3) {
                        break;
                    }
                }
                sb.append(s[d].charAt(j));
                letterDG(list, digits, i + 1, sb, s);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }

    //双指针移除链表上倒数的节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        ListNode node1 = head;
        ListNode node2 = head;
        int y = 0;
        boolean disn = false;
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            if (n >= 0) {
                return null;
            }
        }
        while (node1 != null) {
            node1 = node1.next;
            y++;
            if (disn) {
                node2 = node;
                node = node2.next;
            }
            if (y == n)
                disn = true;
        }
        if (node == head) {
            head = head.next;
        } else {
            node2.next = node.next;
        }
        return head;
    }

    public boolean isValid(String s) {
        boolean isvalid = true;
        Stack<Character> characters = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[')
                characters.add(c);
            else if (characters.empty()) {
                isvalid = false;
                break;
            } else if (c == ')') {
                if ('(' != characters.pop()) {
                    isvalid = false;
                    break;
                }
            } else if (c == '}') {
                if ('{' != characters.pop()) {
                    isvalid = false;
                    break;
                }
            } else if (c == ']') {
                if ('[' != characters.pop()) {
                    isvalid = false;
                    break;
                }
            }
        }
        if (!characters.empty()) {
            isvalid = false;
        }
        return isvalid;
    }


    //n 个子排列
    public List<String> pailien(String s, int n) {
        List<String> strings = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int[] v = new int[s.length()];
        diguiPai(sb, strings, s, n, 0, v);
        return strings;
    }

    public void diguiPai(StringBuilder sb, List<String> list, String s, int n, int k, int[] v) {
        if (sb.length() == n) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = k; j < n; j++) {
                if (v[i] == 1) continue;
                v[i] = 1;
                sb.append(s.charAt(i));
                diguiPai(sb, list, s, n, j + 1, v);
                v[i] = 0;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    //全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;

    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        findgenerateParenthesis(n, n, "", res);
        return res;
    }

    //left和right分别表示左右括号的个数
    public void findgenerateParenthesis(int left, int right, String out, List<String> res) {
        if (left < 0 || right < 0 || left > right) {  //left > right是")("的情况
            return;
        }
        if (left == 0 && right == 0) {
            res.add(out);
            return;
        }
        findgenerateParenthesis(left - 1, right, out + "(", res);//回溯
        findgenerateParenthesis(left, right - 1, out + ")", res);
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        ListNode node1 = head.next.next;
        head.next = node1;
        node.next = head;
        while (head.next != null && head.next.next != null) {
            ListNode node2 = head.next;
            ListNode node3 = head.next.next;
            head.next = node3;
            node2.next = node3.next;
            node3.next = node2;
            head = head.next.next;
        }
        return node;
    }

    public int removeDuplicates(int[] nums) {
        int x = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[x] != nums[i]) {
                nums[++x] = nums[i];
            }
        }
        return x + 1;
    }

    private LinkedList<Integer> lklist = new LinkedList<>();
    private boolean first = false;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) {
            return lklist;
        }
        if (nums.length == 1) {
            lklist.add(nums[0]);
            return lklist;
        }
        Arrays.sort(nums);
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(-1);
        diguiSubset(nums, list1, 0);
        lklist.removeFirst();
        return lklist;
    }

    public void diguiSubset(int[] nums, LinkedList<Integer> list1, int k) {
        if (list1.size() > lklist.size())
            lklist = new LinkedList<>(list1);
        for (int i = k; i < nums.length; i++) {
            if (lklist.contains(nums[i]))
                continue;
            if (list1.size() != 0 && nums[i] % list1.getLast() == 0) {
                list1.add(nums[i]);
                diguiSubset(nums, list1, i + 1);
                list1.removeLast();
            }
        }

    }

    public int removeElement(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] != val) {
                left++;
                continue;
            }
            if (nums[right] == val) {
                right--;
                continue;
            }
            nums[left] = nums[right];
            right--;

        }
        return left;
    }

    //下一个排列
    public void nextPermutation(int[] nums) {
        int l = nums.length - 1;
        if (l == 0)
            return;
        while (l > 0) {
            if (nums[l] <= nums[l - 1]) {
                l--;
            } else {
                break;
            }
        }
        if (l == 0) {
            Arrays.sort(nums);
            return;
        }
        int l1 = nums.length - 1;
        while (nums[l - 1] >= nums[l1]) {
            l1--;
            if (l1 == -1)
                return;
        }
        int temp = nums[l1];
        nums[l1] = nums[l - 1];
        nums[l - 1] = temp;
        int len = nums.length - 1;
        int x = len - l + 2;
        for (int i = 0; i < x / 2; i++) {
            int t = nums[len - i];
            nums[len - i] = nums[l + i];
            nums[l + i] = t;
        }
    }

    //旋转数组搜索
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int ans = -1;
        int l = nums.length - 1;
        int s = 0;
        if (target < nums[0]) {
            while (target < nums[l] && nums[0] >= nums[l]) {
                l--;
                if (l == -1)
                    return -1;
            }
            if (target == nums[l])
                ans = l;
        } else {
            while (target > nums[s] && nums[0] <= nums[s]) {
                s++;
                if (s == nums.length)
                    return -1;
            }
            if (target == nums[s]) {
                ans = s;
            }
        }
        return ans;
    }

    //在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        int ans[] = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        if (nums.length == 0)
            return ans;
        if (nums.length == 1) {
            if (nums[0] == target) {
                ans[0] = ans[1] = 0;
            }
            return ans;
        }
        int start = 0;
        int end = nums.length - 1;
        if (nums[start] == target && nums[end] == target) {
            ans[0] = start;
            ans[1] = end;
            return ans;
        }
        while (start < end) {
            if (target >= nums[start]) {
                if (target == nums[start])
                    break;
                else {
                    start++;
                }
            }
            if (target <= nums[end]) {
                if (target == nums[end])
                    break;
                else {
                    end--;
                }
            }
        }
        if (start >= end && nums[start] != target)
            return ans;
        if (start == end) {
            ans[0] = start;
            ans[1] = start;
        } else if (start < nums.length - end) {
            end = start;
            while (target == nums[end])
                end++;
            ans[0] = start;
            ans[1] = end - 1;
        } else {
            start = end;
            while (target == nums[start])
                start--;
            ans[0] = start + 1;
            ans[1] = end;
        }
        return ans;
    }

    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        if (target > nums[end])
            return end + 1;
        if (target < nums[start])
            return 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target)
                    break;
                else start = mid + 1;
            } else if (nums[mid] >= target) {
                if (nums[mid] == target)
                    break;
                else end = mid - 1;
            }
        }
        if (start >= end)
            return start;
        else
            return mid;
    }

    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (set.contains(board[i][j]) && board[i][j] != '.')
                    return false;
                else
                    set.add(board[i][j]);
            }
            set.clear();
        }
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (set.contains(board[i][j]) && board[i][j] != '.')
                    return false;
                else
                    set.add(board[i][j]);
            }
            set.clear();
        }
        for (int l = 0; l < 3; l++) {
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (set.contains(board[i + l * 3][j + k * 3]) && board[i + l * 3][j + k * 3] != '.')
                            return false;
                        else
                            set.add(board[i + l * 3][j + k * 3]);
                    }
                }
                set.clear();
            }
        }
        return true;
    }

    //自己写的柱状图中最大的矩形，比较暴力
    public int largestRectangleAreaLJ(int[] heights) {
        if (heights.length == 1)
            return heights[0];
        int start = 0;
        int end = heights.length - 1;
        int ans = 0;
        while (start <= end) {
            if (heights[start] == 0) {
                start++;
                continue;
            }
            if (heights[end] == 0) {
                end--;
                continue;
            }
            int startt = start;
            int endt = end;
            int minl = heights[start];
            int minr = heights[end];
            while (startt <= end) {
                if (minl >= heights[startt]) {
                    minl = heights[startt];
                }
                int temp = (startt - start + 1) * minl;
                if (temp > ans) {
                    ans = temp;
                }
                if (minr >= heights[endt]) {
                    minr = heights[endt];
                }
                temp = (end - endt + 1) * minr;
                if (temp > ans) {
                    ans = temp;
                }
                startt++;
                endt--;
            }
            int temps = (end - start + 1) * (minl < minr ? minl : minr);
            if (temps > ans) {
                ans = temps;
            }
            if (heights[start] <= heights[end]) {
                start++;
            } else {
                end--;
            }
        }
        return ans;

    }

    //柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        return maxarea;
    }

    public void uglyNum(int sum, int u, Set<Integer> list) {
        if (u > sum)
            return;
        else
            list.add(u);
        uglyNum(sum, u * 2, list);
        uglyNum(sum, u * 3, list);
        uglyNum(sum, u * 5, list);
    }

    public boolean isUgly(int num) {
        if (num <= 0)
            return false;
        while (num != 1) {
            if (num % 2 == 0)
                num /= 2;
            else if (num % 3 == 0)
                num /= 3;
            else if (num % 5 == 0)
                num /= 5;
            else
                return false;
        }
        return true;
    }

    private int maximalAns = 1;
    private int maximalHeight = 0;
    private int maximalWidth = 0;
    private int firstL = 0;
    private int firstR = 0;

    //最大矩形自己写的并没有AC，递归不是万能
    public int maximalRectangleLJ(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maximalHeight = 1;
                maximalWidth = 1;
                firstL = j;
                firstR = i;
                if (matrix[i][j] != 0)
                    maximalRectangleDiGui(matrix, j, i, true);

            }
        }
        return maximalAns;
    }

    public void maximalRectangleDiGui(char[][] matrix, int left, int right, boolean flag) {
        if (left == -1 || right == -1 || left == matrix[0].length || right == matrix.length)
            return;
        if (flag) {
            if (matrix[right][left] == '0')
                return;
            else {
                if (left - firstL >= maximalWidth)
                    return;
            }
        } else {
            if (matrix[right][left] == '0')
                return;
            else {
                if (right - firstR >= maximalHeight)
                    return;
            }
        }
        maximalWidth = Math.max(maximalWidth, left - firstL + 1);
        maximalHeight = Math.max(maximalHeight, right - firstR + 1);
        if (maximalHeight * maximalWidth > maximalAns)
            maximalAns = maximalHeight * maximalWidth;
        maximalRectangleDiGui(matrix, left + 1, right, true);
        maximalRectangleDiGui(matrix, left, right + 1, false);
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0 && temp < nums[i]) {
                temp = nums[i];
                if (temp > ans)
                    ans = temp;
                continue;
            }
            if (nums[i] >= 0 && temp < 0) {
                temp = nums[i];
                if (temp > ans)
                    ans = temp;
                continue;
            }
            if (nums[i] + temp >= 0) {
                temp += nums[i];
            } else {
                if (ans > 0)
                    temp = 0;
                else
                    temp = ans;
            }
            if (temp > ans)
                ans = temp;
        }
        return ans;
    }

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int ans = 0;
        int temp = 0;
        if (start == destination)
            return 0;
        if (start < destination) {
            for (int i = start; i < destination; i++) {
                ans += distance[i];
            }
            for (int i = destination; i < distance.length + start; i++) {
                temp += distance[i % distance.length];
            }
        } else {
            for (int i = destination; i < start; i++) {
                ans += distance[i];
            }
            for (int i = start; i < distance.length + destination; i++) {
                temp += distance[i % distance.length];
            }
        }
        return ans < temp ? ans : temp;
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists = new ArrayList<>();
        if (n == 0)
            return lists;
        int x;
        if (n > 9)
            x = 9;
        else
            x = n;
        combinationSum3DiGui(x, k, n, 1, new LinkedList<>(), lists, 0);
        return lists;
    }

    public void combinationSum3DiGui(int x, int k, int n, int index, LinkedList<Integer> list, List<List<Integer>> lists, int sum) {
        if (sum > n) {
            return;
        }
        if (list.size() == k && sum != n) {
            return;
        }
        if (list.size() == k && sum == n) {
            lists.add(new LinkedList<>(list));
            return;
        }
        for (int i = index; i <= x; i++) {
            sum += i;
            list.add(i);
            combinationSum3DiGui(x, k, n, i + 1, list, lists, sum);
            sum -= i;
            list.removeLast();
        }
    }


    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num))
                return true;
            else
                set.add(num);
        }
        return false;
    }

    int count = 0;


    //接雨水
    public int trap(int[] height) {
        return 0;
    }

    public int minSubArrayLen(int s, int[] nums) {
        int ans = nums.length + 1;
        int start = 0, end = 0;
        int temp = 0;
        while (start < nums.length && end < nums.length) {
            if (nums[end] + temp < s)
                temp += nums[end++];
            else {
                ans = Math.min(end - start + 1, ans);
                temp -= nums[start++];
            }
        }
        return ans == nums.length + 1 ? 0 : ans;
    }

    //跳跃游戏
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max)
                return false;
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

    //错误的跳跃游戏❌
    public boolean canJumpLJ(int[] nums) {
        if (nums.length == 0)
            return true;
        if (nums[0] >= nums.length - 1) {
            return true;
        }
        return canJumpDiGui(nums, 0, nums.length - 1);
    }

    private boolean canJumpDiGui(int[] nums, int index, int sum) {
        if (sum <= 0)
            return true;
        if (index >= nums.length - 1)
            return true;
        if (nums[index] >= nums.length - 1)
            return true;
        for (int i = 1; i <= nums[index]; i++) {
            if (canJumpDiGui(nums, i + index, sum - nums[index]))
                return true;
        }
        return false;
    }

    // 合并区间
    public int[][] mergeLJ(int[][] intervals) {
        if (intervals.length == 0)
            return intervals;
        List<Integer[]> list = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> b[0] - a[0]);
        int min = intervals[0][0];
        int max = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (max >= intervals[i][0] && max <= intervals[i][1]) {
                max = intervals[i][1];
                if (intervals[i][0] < min)
                    min = intervals[i][0];
            } else if (intervals[i][1] >= max && intervals[i][0] <= max) {
                max = intervals[i][1];
                if (intervals[i][0] < min)
                    min = intervals[i][0];
            } else if (intervals[i][1] >= min && intervals[i][1] <= max) {
                if (intervals[i][0] < min)
                    min = intervals[i][0];
            } else {
                Integer[] temp = new Integer[2];
                temp[0] = min;
                temp[1] = max;
                list.add(temp);
                min = intervals[i][0];
                max = intervals[i][1];
            }
        }
        Integer[] temp = new Integer[2];
        temp[0] = min;
        temp[1] = max;
        list.add(temp);
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }
        return ans;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        int mergeCount = 0;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i][1] >= intervals[j][0] && intervals[i][0] <= intervals[j][1]) {
                    if (intervals[i][1] > intervals[j][1]) {
                        intervals[j][1] = intervals[i][1];
                    }
                    if (intervals[i][0] < intervals[j][0]) {
                        intervals[j][0] = intervals[i][0];
                    }
                    intervals[i] = null;
                    mergeCount++;
                    break;
                }
            }
        }
        int[][] result = new int[intervals.length - mergeCount][];
        for (int i = 0, j = 0; j < intervals.length; j++) {
            if (intervals[j] != null) {
                result[i++] = intervals[j];
            }
        }
        return result;

    }

    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        int left = 1;
        int right = 1;
        for (int i = 0; i < ans.length; i++) {
            ans[i] = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            ans[i] *= left;
            left *= nums[i];

            ans[nums.length - 1 - i] *= right;
            right *= nums[nums.length - 1 - i];
        }
        return ans;
    }

    public int maximalRectangle(char[][] matrix) {
        int maxarea = 0;
        if (matrix.length == 0)
            return 0;
        int[] heights = new int[matrix[0].length];
        for (int k = 0; k < matrix.length; k++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[k][j] == '1')
                    heights[j] += 1;
                else
                    heights[j] = 0;
            }
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < heights.length; i++) {
                while (stack.peek() != -1 && heights[i] < heights[stack.peek()])
                    maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
                stack.push(i);
            }
            while (stack.peek() != -1)
                maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxarea;
    }

    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int[][] v = new int[n][n];
        generateMatrixDiGui(ans, v, 0, 0, 1, true);
        return ans;
    }

    private void generateMatrixDiGui(int[][] ans, int[][] v, int row, int column, int index, boolean dict) {
        if (row == -1 || column == -1 || row == ans.length || column == ans.length) {
            return;
        }
        if (v[column][row] == 1) {
            return;
        }
        v[column][row] = 1;
        ans[column][row] = index;
        if (!dict) {
            generateMatrixDiGui(ans, v, row + 1, column, index + 1, false);
            generateMatrixDiGui(ans, v, row, column + 1, index + 1, false);
            generateMatrixDiGui(ans, v, row - 1, column, index + 1, false);
            generateMatrixDiGui(ans, v, row, column - 1, index + 1, true);
        } else {
            generateMatrixDiGui(ans, v, row, column - 1, index + 1, true);
            generateMatrixDiGui(ans, v, row + 1, column, index + 1, false);
            generateMatrixDiGui(ans, v, row, column + 1, index + 1, false);
            generateMatrixDiGui(ans, v, row - 1, column, index + 1, false);
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }


    public int lengthOfLastWord(String s) {
        s = s.trim();
        int ans = 0;
        int end = s.length();
        while (end > 0) {
            if (s.charAt(end - 1) == ' ') {
                ans = s.length() - end;
                break;
            }
            end--;
            if (end == 0) {
                ans = s.length() - end;
                break;
            }
        }
        return ans;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0)
            return 0;
        int[][] v = new int[obstacleGrid.length][obstacleGrid[0].length];
        return uniquePathsWithObstaclesDiGui(obstacleGrid, obstacleGrid[0].length - 1, obstacleGrid.length - 1, v);
    }

    public int uniquePathsWithObstaclesDiGui(int[][] obstacleGrid, int left, int up, int[][] v) {
        if (obstacleGrid[up][left] == 1)
            return 0;
        if (left == 0 && up == 0)
            return 1;
        if (left == 0)
            return uniquePathsWithObstaclesDiGui(obstacleGrid, left, up - 1, v);
        if (up == 0)
            return uniquePathsWithObstaclesDiGui(obstacleGrid, left - 1, up, v);
        if (v[up][left] != 0)
            return v[up][left];
        else {
            int temp = uniquePathsWithObstaclesDiGui(obstacleGrid, left - 1, up, v) + uniquePathsWithObstaclesDiGui(obstacleGrid, left, up - 1, v);
            v[up][left] = temp;
            return temp;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode l = head;
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        if (length == 0)
            return null;
        if (k % length == 0)
            return l;
        k = k % length;
        ListNode pre = l;
        ListNode end = l;
        ListNode temp = l;
        int i = 0;
        while (end.next != null) {
            end = end.next;
            if (i == k) {
                pre = pre.next;
            } else {
                i++;
            }
            if (end.next == null) {
                temp = pre;
                pre = pre.next;
                temp.next = null;
                break;
            }
        }
        end.next = l;
        return pre;
    }

    public int minPathSum(int[][] grid) {
        if (grid.length == 0)
            return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (i == 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else {
                    grid[i][j] = Math.min(grid[i][j] + grid[i - 1][j], grid[i][j] + grid[i][j - 1]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public int[] plusOne(int[] digits) {
        int jinwei = 1;
        boolean plus = false;
        int i = 0;
        for (i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + jinwei;
            if (digits[i] != 10) {
                break;
            } else {
                if (i == 0)
                    plus = true;
                digits[i] -= 10;
            }
        }
        if (plus) {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            return ans;
        }
        return digits;
    }

    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int la = a.length() - 1;
        int lb = b.length() - 1;
        int carry = 0;
        while (la >= 0 && lb >= 0) {
            int temp = a.charAt(la) - '0' + b.charAt(lb) - '0' + carry;
            if (temp > 1) {
                carry = 1;
                temp -= 2;
            } else
                carry = 0;
            ans.insert(0, temp);
            la--;
            lb--;
        }
        while (la >= 0) {
            int temp = a.charAt(la) - '0' + carry;
            if (temp > 1) {
                carry = 1;
                temp -= 2;
            } else
                carry = 0;
            ans.insert(0, temp);
            la--;
        }
        while (lb >= 0) {
            int temp = b.charAt(lb) - '0' + carry;
            if (temp > 1) {
                carry = 1;
                temp -= 2;
            } else
                carry = 0;
            ans.insert(0, temp);
            lb--;
        }
        if (carry == 1) {
            ans.insert(0, 1);
        }
        return ans.toString();
    }

    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        double ans = 1;
        while (ans != (ans + x / ans) / 2) {
            ans = (ans + x / ans) / 2;
        }
        return ((int) ans);
    }

    public int climbStairs(int n) {
        int v[] = new int[n + 1];
        return climbStairsDiGui(n, v);
    }

    private int climbStairsDiGui(int n, int[] v) {
        if (n == 0 || n == 1)
            return 1;
        else {
            if (v[n] != 0) return v[n];
            else {
                int temp = climbStairsDiGui(n - 1, v) + climbStairsDiGui(n - 2, v);
                v[n] = temp;
                return temp;
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        boolean col0_flag = false;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) col0_flag = true;
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0_flag) matrix[i][0] = 0;
        }
    }

    public String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();
        boolean isZero = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Long.parseLong("" + nums[i] + nums[j]) < Long.parseLong("" + nums[j] + nums[i])) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
            if (nums[i] != 0)
                isZero = false;
            sb.append(nums[i]);
        }
        if (isZero)
            return "0";
        return sb.toString();
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i])
                    temp++;
            }
            list.add(temp);
        }
        return list;
    }

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int mid = (nums.length + 1) / 2;
        int n1[] = new int[mid];
        int n2[] = new int[nums.length - mid];
        for (int i = 0; i < mid; i++) {
            n1[i] = nums[i];
            if (mid + i == nums.length)
                break;
            n2[i] = nums[mid + i];
        }
        int x = n1.length - 1;
        int y = n2.length - 1;
        int z = 0;
        while (z < nums.length) {
            nums[z] = n1[x];
            x--;
            z++;
            if (y == -1)
                return;
            nums[z] = n2[y];
            y--;
            z++;
        }
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> rs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] < 0) {
                rs.add(Math.abs(nums[i]));
            } else {
                nums[Math.abs(nums[i]) - 1] *= -1;
            }
        }
        return rs;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    list.add(1);
                } else {
                    list.add(lists.get(i - 2).get(j - 2) + lists.get(i - 2).get(j - 1));
                }
            }
            lists.add(list);
        }
        return lists;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            list.add(1);
            for (int j = i - 1; j > 0; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }
        return list;
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        if (timeSeries.length == 0)
            return ans;
        ans = duration;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] - timeSeries[i - 1] > duration) {
                ans += duration;
            } else {
                ans += timeSeries[i] - timeSeries[i - 1];
            }
        }
        return ans;
    }

    public int shipWithinDays(int[] weights, int D) {
        int lo = 0, hi = 0;
        for (int weight : weights) {
            hi += weight;
            if (lo < weight)
                lo = weight;
        }
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canShip(weights, D, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean canShip(int[] weights, int D, int K) {
        int cur = K;
        for (int weight : weights) {
            if (cur < weight) {
                cur = K;
                D--;
            }
            cur -= weight;
        }
        return D > 0;
    }

    //买卖股票
    public int maxProfit(int[] prices) {
        int ans = 0;
        if (prices.length == 0 || prices.length == 1)
            return 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i];
            else
                ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

    //买卖股票无限次
    public int maxProfitII(int[] prices) {
        int ans = 0;
        if (prices.length == 0 || prices.length == 1)
            return 0;
        int min = prices[0];
        int max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > max)
                max = prices[i];
            else {
                ans += max - min;
                min = prices[i];
                max = prices[i];
            }
        }
        if (max > min)
            ans += max - min;
        return ans;
    }

    //买卖股票III的错误答案
    public int maxProfitIII(int[] prices) {
//        int ans = 0;
        int ansm = 0, ansM = 0;
        if (prices.length == 0 || prices.length == 1)
            return 0;
        int min = prices[0];
        int max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i];
            else {
                ansm = Math.max(ansm, max - min);
                if (ansm > ansM) {
                    ansM = ansm ^ ansM;
                    ansm = ansm ^ ansM;
                    ansM = ansm ^ ansM;
                }
//                min = prices[i];
                max = prices[i];
            }
        }
        if (max > min) {
            ansm = Math.max(ansm, max - min);
            if (ansm > ansM) {
                ansM = ansm ^ ansM;
                ansm = ansm ^ ansM;
                ansM = ansm ^ ansM;
            }
        }
        return ansm + ansM;
    }

    public int arrayNesting(int[] nums) {
        if (nums.length == 0)
            return 0;
        int ans = 1, index = 0, temp;
        int[] h = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (h[i] == 1) continue;
            temp = ans;
            ans = 1;
            index = i;
            while (i != nums[index]) {
                index = nums[index];
                ans++;
                h[index] = 1;
            }
            ans = Math.max(temp, ans);
        }
        return ans;
    }


    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        if (nums.length < 3)
            return 0;
        for (int i = nums.length - 1; i > 1; i--) {
            int lo = 0;
            int hi = i - 1;
            while (lo < hi) {
                if (nums[lo] + nums[hi] > nums[i]) {
                    ans += hi - lo;
                    hi--;
                } else {
                    lo++;
                }
            }
        }
        return ans;
    }


    public String dayOfTheWeek(int day, int month, int year) {
        String[] ans = new String[]{"Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday"};
        int[] mday = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int l = 0;
        int x = 1971;
        boolean ping = false;
        while (x < year) {
            if (x % 4 == 0)
                if (x % 100 == 0)
                    if (x % 400 == 0)
                        l += 366;
                    else
                        l += 365;
                else
                    l += 366;
            else
                l += 365;
            x++;
        }
        if (year % 4 == 0)
            if (year % 100 == 0)
                ping = year % 400 == 0;
            else
                ping = true;
        else
            ping = false;
        if (ping)
            mday[1] = 29;
        for (int i = 0; i < month - 1; i++) {
            l += mday[i];
        }
        l += day;
        return ans[l % 7];
    }

    public int leastInterval(char[] tasks, int n) {
        if (n == 0 || tasks.length == 0)
            return tasks.length;
        int[] h = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            h[tasks[i] - 65]++;
        }
        Arrays.sort(h);
        int max = h[25];
        int ans = (max - 1) * n + max;
        int i = 24;
        while (i >= 0 && h[i] == max) {
            ans++;
            i--;
        }
        return Math.max(ans, tasks.length);
    }

    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i + 1;
        }
        if (k == 1)
            return ans;
        boolean b = true;
        int x = 0;
        int h = n - k;
        int l = n;
        int t = n - k + 1;
        while (h < l) {
            if (x == 2) {
                n--;
                t++;
                x = 0;
            }
            if (b) {
                ans[h] = n;
                b = false;
                x++;
            } else {
                ans[h] = t;
                b = true;
                x++;
            }
            h++;
        }
        return ans;
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] counters = new int[n];
        for (int[] booking : bookings) {
            counters[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                counters[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; ++i) {
            counters[i] += counters[i - 1];
        }
        return counters;
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 0;
        if (nums[0] > nums[1])
            return 0;
        if (nums[nums.length - 2] < nums[nums.length - 1])
            return nums.length - 1;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
                return i;
            if (nums[nums.length - 1 - i] > nums[nums.length - i - 2] && nums[nums.length - i - 1] > nums[nums.length - i])
                return nums.length - 1 - i;
        }
        return 0;
    }

    public int maximumSwap(int num) {
        char[] arr = (num + "").toCharArray();
        int[] digitIdx = new int[10];//数字 0-9 出现的最后位置 digitIdx[a] 表示数字 a 出现的最后位置
        for (int i = 0; i < arr.length; ++i) {
            digitIdx[arr[i] - '0'] = i;
        }
        for (int i = 0; i < arr.length; ++i) {//遍历高位
            for (int a = 9; a > arr[i] - '0'; --a) {//遍历比最高位大的数字 a
                if (digitIdx[a] > i) {//若后面存在比当前最高位的数字大的数字,则交换他们 并返回
                    char tmp = arr[i];
                    arr[i] = arr[digitIdx[a]];
                    arr[digitIdx[a]] = tmp;
                    return Integer.parseInt(new String(arr));
                }
            }
        }
        return num;
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(arr);
//        int min = Integer.MAX_VALUE;
        int min = (arr[arr.length - 1] - arr[0]) / (arr.length - 1);
        for (int i = 0; i < arr.length - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) < min) {
                min = Math.abs(arr[i] - arr[i + 1]);
                lists.clear();
                List<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                list.add(arr[i + 1]);
                lists.add(list);
            } else if (Math.abs(arr[i] - arr[i + 1]) == min) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                list.add(arr[i + 1]);
                lists.add(list);
            }
        }
        return lists;
    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] ans = new int[queries.length];
        int[] h = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            h[i] = f(words[i]);
        }
        Arrays.sort(h);
        for (int i = 0; i < queries.length; i++) {
            int t = f(queries[i]);
            int start = 0;
            int end = words.length - 1;
            if (t >= h[h.length - 1]) {
                ans[i] = 0;
                continue;
            }
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (h[mid] > t)
                    end = mid;
                else
                    start = mid + 1;
            }
            ans[i] = words.length - start;
        }
        return ans;
    }

    private int f(String s) {
        int len = 1;
        char[] c = s.toCharArray();
        Arrays.sort(c);
        int i = 1;
        while (i < c.length) {
            if (c[0] == c[i])
                len++;
            else
                break;
            i++;
        }
        return len;
    }

    public int countCharacters(String[] words, String chars) {
        int ans = 0;
        char[] c = chars.toCharArray();
        char[][] cs = new char[words.length][];
        for (int i = 0; i < words.length; i++) {
            cs[i] = words[i].toCharArray();
            Arrays.sort(cs[i]);
        }
        Arrays.sort(c);
        outer:
        for (char[] value : cs) {
            int s = 0;
            int e = 0;
            while (e < c.length) {
                if (s == value.length)
                    break;
                if (value[s] > c[e]) {
                    e++;
                } else if (value[s] == c[e]) {
                    e++;
                    s++;
                } else {
                    continue outer;
                }
            }
            if (s == value.length)
                ans += s;
        }
        return ans;
    }

    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        int temp = 0;
        int[] t = new int[dominoes.length];
        int[] h = new int[dominoes.length];
        for (int i = 0; i < dominoes.length; i++) {
            temp = 0;
            if (h[i] == 1)
                continue;
            for (int j = i + 1; j < dominoes.length; j++) {
                if ((dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1]) || (dominoes[i][1] == dominoes[j][0] && dominoes[i][0] == dominoes[j][1])) {
                    h[j] = 1;
                    temp++;
                }
            }
            t[i] = temp;
        }
        for (int i = 0; i < t.length; i++) {
            ans += t[i] * (t[i] + 1) / 2;
        }
        return ans;
    }


    public int findContentChildren(int[] g, int[] s) {
        int ans = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gl = g.length - 1;
        int sl = s.length - 1;
        while (gl >= 0 && sl >= 0) {
            if (s[sl] >= g[gl]) {
                ans++;
                sl--;
            }
            gl--;
        }
        return ans;
    }

    public boolean lemonadeChange(int[] bills) {
        //边界判断
        if (bills[0] != 5)
            return false;
        int[] b = new int[2];
        for (int bill : bills) {
            if (bill == 5) {
                b[0]++;
            } else if (bill == 10) {
                if (b[0] == 0)
                    return false;
                else {
                    b[0]--;
                    b[1]++;
                }
            } else {
                if (b[1] == 0) {
                    if (b[0] < 3)
                        return false;
                    else
                        b[0] -= 3;
                } else {
                    if (b[0] == 0)
                        return false;
                    else {
                        b[0]--;
                        b[1]--;
                    }
                }
            }
        }
        return true;
    }


    public int findMin(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums[nums.length - 1] > nums[0])
            return nums[0];
        int end = nums.length - 1;
        while (end > 0) {
            if (nums[end] > nums[end - 1])
                end--;
            else
                break;
        }
        return nums[end];
    }

    public int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] + numbers[end] < target) {
                start++;
            } else if (numbers[start] + numbers[end] > target) {
                end--;
            } else if (numbers[start] + numbers[end] == target) {
                break;
            }
        }
        int[] ans = new int[2];
        ans[0] = start + 1;
        ans[1] = end + 1;
        return ans;
    }

    public void rotate(int[] nums, int k) {
        if (nums.length == 0 || nums.length == 1)
            return;
        k = k % nums.length;
        if (k == 0)
            return;
        while (k >= 1) {
            int temp = nums[0];
            for (int j = 1; j < nums.length; j++) {
                temp = temp ^ nums[j];
                nums[j] = temp ^ nums[j];
                temp = temp ^ nums[j];
            }
            nums[0] = temp;
            k--;
        }
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums.length == 0)
            return ans;
        int end = 1;
        LinkedList<Integer> ls = new LinkedList<>();
        ls.add(nums[0]);
        while (end < nums.length) {
            if (nums[end - 1] == nums[end] - 1) {
                ls.add(nums[end]);
            } else {
                if (ls.size() == 1) {
                    ans.add(ls.getFirst().toString());
                } else {
                    ans.add(ls.getFirst() + "->" + ls.getLast());
                }
                ls.clear();
                ls.add(nums[end]);
            }
            end++;
        }
        if (ls.size() != 0) {
            if (ls.size() == 1) {
                ans.add(ls.getFirst().toString());
            } else {
                ans.add(ls.getFirst() + "->" + ls.getLast());
            }
        }
        return ans;
    }

    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow)
                break;
        }
        int finder = 0;
        while (true) {
            finder = nums[finder];
            slow = nums[slow];
            if (slow == finder)
                break;
        }
        return slow;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int start = 0;
        int end = 0;
        while (end < nums.length) {
            if (nums[end] != 1) {
                ans = Math.max(ans, end - start);
                start = end + 1;
            }
            end++;
        }
        ans = Math.max(ans, end - start);
        return ans;
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0)
                ans += nums[i];
        }
        return ans;
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (r * c != nums.length * nums[0].length)
            return nums;
        int[][] ans = new int[r][c];
        int[] h = new int[r * c];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                h[index++] = nums[i][j];
            }
        }
        index = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = h[index++];
            }
        }
        return ans;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return false;
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow)
                return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode node = null;
        if (head == null || head.next == null || head.next.next == null)
            return node;
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        boolean cirle = false;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                cirle = true;
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        if (cirle) {
            node = head;
            while (node != slow) {
                node = node.next;
                slow = slow.next;
            }
        }
        return node;
    }

    public boolean isPalindrome(String s) {
        char[] c = s.toCharArray();
        if (c.length <= 1)
            return true;
        int start = 0;
        int end = c.length - 1;
        while (start < end) {
            char ts = Character.toUpperCase(c[start]);
            char te = Character.toUpperCase(c[end]);
            if ((ts > 47 && ts < 58) || (ts > 64 && ts < 91)) {

            } else {
                start++;
                continue;
            }
            if ((te > 47 && te < 58) || (te > 64 && te < 91)) {

            } else {
                end--;
                continue;
            }
            if (ts == te) {
                start++;
                end--;
            } else
                return false;
        }
        return true;
    }

    public int minEatingSpeed(int[] piles, int H) {
        int max = Integer.MAX_VALUE;
        int min = 1;
        while (min < max) {
            int mid = min + (max - min) / 2;
            int x = 0;
            int i = 0;
            while (i < piles.length) {
                if (piles[i] >= mid) {
                    if (piles[i] % mid == 0) {
                        x += piles[i] / mid;
                    } else
                        x += piles[i] / mid + 1;
                } else
                    x += 1;
                i++;
            }
            if (x > H)
                min = mid + 1;
            else
                max = mid;
        }
        return min;
    }


    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0
                    && (i - 1 == -1 || flowerbed[i - 1] == 0)
                    && (i + 1 == flowerbed.length || flowerbed[i + 1] == 0)) {
                n--;
                if (n == 0)
                    return true;
                flowerbed[i] = 1;
            }
        }
        return n <= 0;
    }

    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1)
                return false;
        }
        return true;
    }

    public int minDeletionSize(String[] A) {
        int ans = 0;
        if (A.length == 1 || A[0].length() == 1)
            return 0;
        for (int j = 0; j < A[0].length(); j++) {
            for (int i = 1; i < A.length; i++) {
                if (A[i].charAt(j) - A[i - 1].charAt(j) < 0) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public int[] advantageCount(int[] A, int[] B) {
        if (A.length == 1)
            return A;
        int[] ans = new int[A.length];
        int[] h = new int[A.length];
        Arrays.sort(A);
        Arrays.fill(ans, A[A.length - 1]);
        for (int j = 0; j < B.length; j++) {
            int temp = -1;
            for (int i = 0; i < A.length; i++) {
                if (h[i] == 1) continue;
                if (A[i] > B[j] && A[i] <= ans[j]) {
                    ans[j] = A[i];
                    temp = i;
                    break;
                }
            }
            if (temp == -1) {
                for (int i = 0; i < h.length; i++) {
                    if (h[i] == 0) {
                        ans[j] = A[i];
                        h[i] = 1;
                        break;
                    }
                }
            } else
                h[temp] = 1;
        }
        return ans;
    }

    public int balancedStringSplit(String s) {
        if (s.length() == 1)
            return 0;
        int ans = 0;
        int sL = 0;
        int sR = 0;
        int start = 0;
        while (start < s.length()) {
            if (s.charAt(start) == 'L')
                sL++;
            else if (s.charAt(start) == 'R')
                sR++;
            if (sL == sR) {
                ans++;
                sL = 0;
                sR = 0;
            }
            start++;
        }
        return ans;
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int ans = 1;
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (temp == nums[i]) {
                continue;
            }
            if (i == nums.length - 1) {
                ans++;
            } else if (temp > nums[i] && nums[i] < nums[i + 1]) {
                ans++;
                temp = nums[i];
            } else if (temp < nums[i] && nums[i] > nums[i + 1]) {
                temp = nums[i];
                ans++;
            }
        }
        return ans;
    }

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        while (num.indexOf('0') == 0) {
            num = num.substring(1);
        }
        if (k == num.length())
            return "0";
        if (k == 0)
            return num;
        char[] chars = num.toCharArray();
        int i = 0;
        outer:
        for (i = 0; i < chars.length; i++) {
            if (stack.isEmpty())
                stack.push(chars[i]);
            else if (chars[i] >= stack.peek()) {
                stack.push(chars[i]);
            } else if (chars[i] < stack.peek()) {
                while (chars[i] < stack.peek()) {
                    stack.pop();
                    k--;
                    if (k == 0)
                        break outer;
                    if (stack.isEmpty())
                        break;
                }
                stack.push(chars[i]);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (k != 0) {
            stack.pop();
            k--;
        }
        while (!stack.isEmpty()) {
            ans.insert(0, stack.pop());
        }
        ans.append(num.substring(i));
        String s = ans.toString();
        while (s.indexOf('0') == 0) {
            s = s.substring(1);
        }
        return s.isEmpty() ? "0" : s;
    }


    public int minCostToMoveChips(int[] chips) {
        if (chips.length < 2)
            return chips.length;
        int[] h = new int[chips.length];
        int count = 0;
        int max = 0;
        int index = 0;
        int ans = 0;
        h[index] = 1;
        for (int i = 1; i < chips.length; i++) {
            if (chips[i] == chips[i - 1]) {
                h[index]++;
                if (h[index] > max) {
                    max = h[index];
                    count = index;
                }
            } else {
                h[++index] = 1;
            }
        }
        for (int i = 0; i < h.length; i++) {
            if (i == count)
                continue;
            if (h[i] == 0)
                break;
            if ((count - i) % 2 != 0) {
                ans += h[i];
            }
        }
        return ans;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] h = new int[nums1.length + nums2.length];
        int s1 = 0;
        int s2 = 0;
        int index = 0;
        while (s1 < nums1.length && s2 < nums2.length) {
            if (nums1[s1] <= nums2[s2]) {
                h[index] = nums1[s1];
                s1++;
            } else {
                h[index] = nums2[s2];
                s2++;
            }
            index++;
        }
        while (s1 < nums1.length) {
            h[index] = nums1[s1];
            index++;
            s1++;
        }
        while (s2 < nums2.length) {
            h[index] = nums2[s2];
            index++;
            s2++;
        }
        if (h.length % 2 == 0)
            return ((double) (h[h.length / 2 - 1] + h[h.length / 2])) / 2;
        else
            return h[h.length / 2];
    }

    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0)
            return people;
        for (int i = 0; i < people.length; i++) {
            for (int j = 0; j < people.length; j++) {
                if (people[i][0] < people[j][0]) {
                    int[] temp = people[i];
                    people[i] = people[j];
                    people[j] = temp;
                } else if (people[i][0] == people[j][0]) {
                    if (people[i][1] > people[j][1]) {
                        int[] temp = people[i];
                        people[i] = people[j];
                        people[j] = temp;
                    }
                }
            }
        }
        ArrayList<int[]> list = new ArrayList<>();
        list.add(people[people.length - 1]);
        for (int i = people.length - 2; i >= 0; i--) {
            list.add(people[i][1], people[i]);
        }
        int[][] ans = new int[people.length][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int ans = 0;
        Arrays.sort(intervals, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        int s = 0;
        int e = 1;
        while (e < intervals.length) {
            if (intervals[s][1] <= intervals[e][0]) {
                s = e;
            } else if (intervals[s][1] >= intervals[e][1]) {
                s = e;
                ans++;
            } else {
                ans++;
            }
            e++;
        }
        return ans;
    }

    public int wangzhe(int[][] matrix) {
        //当前血量
        int[][] dp = new int[matrix.length][matrix[0].length];
        //当前人物攻击
        int attack = 100;
        //当前人物的血量初始化
        dp[0][0] = matrix[0][0] + 100;
        //dp[i][j - 1] <= 0 和 dp[i - 1][j] <= 0  表示他上一个值的时候就死了不能继续使用
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (i == 0) {                //初始化首行
                    if (dp[i][j - 1] <= 0)
                        dp[i][j] = 0;
                    else
                        dp[i][j] = dp[i][j - 1] + matrix[i][j];
                } else if (j == 0) {        //初始化首列
                    if (dp[i - 1][j] <= 0)
                        dp[i][j] = 0;
                    else
                        dp[i][j] = dp[i - 1][j] + matrix[i][j];
                } else {
                    if (dp[i][j - 1] <= 0 && dp[i - 1][j] <= 0) {
                        dp[i][j] = 0;
                    } else if (dp[i][j - 1] <= 0) {
                        dp[i][j] = dp[i - 1][j] + matrix[i][j];
                    } else if (dp[i - 1][j] <= 0) {
                        dp[i][j] = dp[i][j - 1] + matrix[i][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j] + matrix[i][j], dp[i][j - 1] + matrix[i][j]);
                    }
                }
            }
        }
        //打印dp数组
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + ",");
            }
            System.out.println();
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] h = new int[gas.length];
        int temp = 0;
        for (int i = 0; i < gas.length; i++) {
            h[i] = gas[i] - cost[i];
            temp += h[i];
        }
        if (temp < 0)
            return -1;
        int start = 0;
        int sum = 0;
        int index = 0;
        while (start < h.length) {
            sum += h[start];
            if (sum < 0) {
                index = start + 1;
                sum = 0;
            }
            start++;
        }
        return index;
    }

    //分发糖果
    public int candy(int[] ratings) {
        if (ratings.length < 2)
            return ratings.length;
        int start = 0;
        int ans = 0;
        int up = 0;
        Stack<Integer> stack = new Stack<>();
        while (start < ratings.length) {
            if (stack.isEmpty()) {
                stack.add(ratings[start]);
            } else if (ratings[start] <= stack.peek()) {
                up = 1;
                stack.push(ratings[start]);
            } else if (ratings[start] > stack.peek()) {
                int temp = 1;
                up++;
                ans += up;
                while (!stack.isEmpty()) {
                    ans += temp;
                    temp++;
                    stack.pop();
                }
            }
            start++;
        }
        int temp = 1;
        while (!stack.isEmpty()) {
            ans += temp;
            temp++;
            stack.pop();
        }
        return ans;
    }

    public int findMinArrowShots(int[][] points) {
        int ans = 1;
        if (points.length < 2)
            return points.length;
        Arrays.sort(points, (a, b) -> (a[0] - b[0]));
        int min = points[0][0];
        int max = points[0][1];
        int s = 1;
        while (s < points.length) {
            if (points[s][0] <= max) {
                max = Math.min(max, points[s][1]);
                min = Math.max(min, points[s][0]);
            } else {
                min = points[s][0];
                max = points[s][1];
                ans++;
            }
            s++;
        }
        return ans;
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        int start = 0;
        int index = 0;
        Set<Character> set = new HashSet<>();
        while (start < S.length()) {
            if (set.contains(S.charAt(start))) {
                start++;
                continue;
            } else
                set.add(S.charAt(start));
            if (ans.isEmpty())
                ans.add(index, S.lastIndexOf(S.charAt(start)) + 1);
            else {
                if (start < ans.get(index)) {
                    if (S.lastIndexOf(S.charAt(start)) + 1 > ans.get(index)) {
                        ans.set(index, S.lastIndexOf(S.charAt(start)) + 1);
                    }
                } else {
                    index++;
                    ans.add(index, S.lastIndexOf(S.charAt(start)) + 1);
                }
            }
            start++;
        }
        for (int i = ans.size() - 1; i > 0; i--) {
            ans.set(i, ans.get(i) - ans.get(i - 1));
        }
        return ans;
    }

    public int numRescueBoats(int[] people, int limit) {
        if (people.length == 1)
            return 1;
        int[] h = new int[people.length];
        int ans = 0;
        int s = 0;
        int e = people.length - 1;
        while (s < e) {
            if (h[s] == 1) {
                s++;
                continue;
            }
            if (people[s] + people[e] <= limit) {
                while (s < e) {
                    if (people[s] + people[e] <= limit) {
                        s++;
                    } else {
                        h[s - 1] = 1;
                        break;
                    }
                }
            } else {
                e--;
            }
            ans++;
        }
        return ans;
    }

    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> lists = new ArrayList<>();
        if (S.length() < 3)
            return lists;
        char[] chars = S.toCharArray();
        int start = 0;
        int end = 0;
        while (end < chars.length) {
            if (chars[start] == chars[end]) {
                end++;
            } else {
                if (end - start >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end - 1);
                    lists.add(list);
                }
                start = end;
            }
        }
        if (end - start >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(end - 1);
            lists.add(list);
        }
        return lists;
    }

    // 超时
    public int consecutiveNumbersSum(int N) {
        int ans = 1;
        if (N < 3)
            return ans;
        int start = 1;
        int end = 3;
        int sum = 3;
        if (N > 3 && (N & 1) == 1)
            ans++;
        while (end <= N / 3 + 2) {
            if (sum == N) {
                ans++;
                sum += end++;
            } else if (sum > N) {
                sum -= start++;
            } else if (sum < N) {
                sum += end++;
            }
        }
        return ans;
    }

    // 三角形最小路径和
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] ans = new int[triangle.get(triangle.size() - 1).size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                ans[j] = Math.min(ans[j], ans[j + 1]) + triangle.get(i).get(j);
            }
        }
        return ans[0];
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(cost[i] + dp[i - 1], cost[i] + dp[i - 2]);
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        if (nums.length == 0)
            return 0;
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < nums.length + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[dp.length - 1];
    }

    int numTileans = 0;

    public int numTilePossibilities(String tiles) {
        char[] c = tiles.toCharArray();
        Arrays.sort(c);
        int[] h = new int[c.length];
        numTilePossibilitiesDiGui(c, 0, h);
        return numTileans;
    }

    public void numTilePossibilitiesDiGui(char[] c, int index, int[] h) {
        if (index == c.length + 1)
            return;
        numTileans++;
        for (int i = 0; i < c.length; i++) {
            if (h[i] == 1) continue;
            if (i > 0 && c[i] == c[i - 1] && h[i - 1] == 0) {
                continue;
            }
            h[i] = 1;
            numTilePossibilitiesDiGui(c, index + 1, h);
            h[i] = 0;
        }
    }

    //类似贪心的最低票价解法，但是不正确
    public int mincostTicketsTanXin(int[] days, int[] costs) {
        int start = 0;//day[end]-[start]+1与30天对比
        int mid = 0;//day[end]-[mid]+1与7天对比
        int end = 0;//滑动当天天数
        int ans = 0;//答案
        int day7 = 0;//当前第七天的结果
        int day30 = 0;//当前第三十天的结果
        while (end < days.length) {
            if (days[end] - days[start] + 1 <= 30) {
                if (days[end] - days[mid] + 1 <= 7) {
                    day7 = Math.min((end - mid + 1) * costs[0], costs[1]);
                    end++;
                } else {
                    day30 += day7;
                    mid = end;
                }
            } else {
                day30 += day7;
                start = end;
                mid = end;
                ans += Math.min(day30, costs[2]);
                day30 = 0;
                day7 = 0;
            }
        }
        ans += day30 + day7;
        return ans;
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days[days.length - 1] + 1];
        int index = 0;
        int a = costs[0];
        int b = costs[1];
        int c = costs[2];
        for (int i = 1; i < dp.length; i++) {
            if (i == days[index]) {
                if (i > 30) {
                    c = Math.min(Math.min(dp[i - 1] + costs[0], dp[i - 7] + costs[1]), dp[i - 30] + costs[2]);
                }
                if (i > 7) {
                    b = Math.min(dp[i - 1] + costs[0], dp[i - 7] + costs[1]);
                }
                a = dp[i - 1] + costs[0];
                dp[i] = Math.min(b, c);
                dp[i] = Math.min(dp[i], a);
                index++;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }

    public int distributeCandies(int[] candies) {
        int ans = 1;
        Arrays.sort(candies);
        for (int i = 1; i < candies.length; i++) {
            if (candies[i] != candies[i - 1])
                ans++;
        }
        return Math.min(ans, candies.length / 2);
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        combineDiGui(1, n, k, new LinkedList<>(), ans);
        return ans;
    }

    public void combineDiGui(int index, int n, int k, LinkedList<Integer> list, List<List<Integer>> lists) {
        if (list.size() == k) {
            lists.add(new LinkedList<>(list));
            return;
        }
        for (int i = index; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            combineDiGui(i + 1, n, k, list, lists);
            list.removeLast();
        }

    }

    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int cur = 0;
        while (cur <= end) {
            if (nums[cur] == 0) {
                int temp = nums[cur];
                nums[cur++] = nums[start];
                nums[start++] = temp;
            } else if (nums[cur] == 2) {
                int temp = nums[cur];
                nums[cur] = nums[end];
                nums[end--] = temp;
            } else {
                cur++;
            }
        }
    }

    public int minAddToMakeValid(String S) {
        Stack<Character> stack = new Stack<>();
        char[] chars = S.toCharArray();
        for (char c : chars) {
            if (stack.isEmpty())
                stack.push(c);
            else {
                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else
                    stack.push(c);
            }
        }
        return stack.size();
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ans = new int[nums1.length];
        int sm = 0;
        int sn = 0;
        int d = 0;
        while (sm < m && sn < n) {
            if (nums1[sm] < nums2[sn])
                ans[d] = nums1[sm++];
            else
                ans[d] = nums2[sn++];
            d++;
        }
        while (sm < m) {
            ans[d] = nums1[sm++];
            d++;
        }
        while (sn < n) {
            ans[d] = nums2[sn++];
            d++;
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = ans[i];
        }
    }

    public ListNode deleteDuplicatesI(ListNode head) {
        if (head == null || head.next == null)
            return head;
        Set<Integer> set = new HashSet<>();
        ListNode ans = head;
        set.add(head.val);
        while (head != null && head.next != null) {
            if (set.contains(head.next.val)) {
                head.next = head.next.next;
            } else {
                set.add(head.next.val);
                head = head.next;
            }
        }
        return ans;
    }

    //被围绕的区域dfs解法
    public void solveDFS(char[][] board) {
        if (board.length == 0)
            return;
        char[][] h = new char[board.length][board[0].length];
        for (int i = 0; i < h.length; i++) {
            for (int j = 0; j < h[i].length; j++) {
                h[i][j] = 'X';
            }
        }
        int[][] v = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1) {
                    if (board[i][j] == 'O' && v[i][j] == 0) {
                        solveDiGui(board, h, v, i, j);
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = h[i][j];
            }
        }
    }

    public void solveDiGui(char[][] board, char[][] h, int[][] v, int left, int right) {
        if (left == -1 || right == -1 || left == board.length || right == board[0].length)
            return;
        if (v[left][right] == 1)
            return;
        if (board[left][right] == 'X') {
            v[left][right] = 1;
            return;
        }
        if (board[left][right] == 'O') {
            h[left][right] = 'O';
            v[left][right] = 1;
            solveDiGui(board, h, v, left + 1, right);
            solveDiGui(board, h, v, left, right + 1);
            solveDiGui(board, h, v, left - 1, right);
            solveDiGui(board, h, v, left, right - 1);
        }
    }

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //被围绕的区域bfs解法
    public void solve(char[][] board) {
        if (board.length == 0)
            return;
        char[][] h = new char[board.length][board[0].length];
        for (int i = 0; i < h.length; i++) {
            for (int j = 0; j < h[i].length; j++) {
                h[i][j] = 'X';
            }
        }
        int[][] v = new int[board.length][board[0].length];
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1) {
                    if (board[i][j] == 'O' && v[i][j] == 0) {
                        solveBFS(board, h, v, i, j, queue);
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = h[i][j];
            }
        }
    }

    private void solveBFS(char[][] board, char[][] h, int[][] v, int i, int j, Queue<Node> queue) {
        queue.add(new Node(i, j));
        v[i][j] = 1;
        h[i][j] = 'O';
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x - 1 >= 0 && board[node.x - 1][node.y] == 'O' && v[node.x - 1][node.y] == 0) {
                v[node.x - 1][node.y] = 1;
                h[node.x - 1][node.y] = 'O';
                queue.add(new Node(node.x - 1, node.y));
            }
            if (node.y - 1 >= 0 && board[node.x][node.y - 1] == 'O' && v[node.x][node.y - 1] == 0) {
                v[node.x][node.y - 1] = 1;
                h[node.x][node.y - 1] = 'O';
                queue.add(new Node(node.x, node.y - 1));

            }
            if (node.x + 1 < board.length && board[node.x + 1][node.y] == 'O' && v[node.x + 1][node.y] == 0) {
                v[node.x + 1][node.y] = 1;
                h[node.x + 1][node.y] = 'O';
                queue.add(new Node(node.x + 1, node.y));
            }
            if (node.y + 1 < board[0].length && board[node.x][node.y + 1] == 'O' && v[node.x][node.y + 1] == 0) {
                v[node.x][node.y + 1] = 1;
                h[node.x][node.y + 1] = 'O';
                queue.add(new Node(node.x, node.y + 1));

            }
        }
    }


    public int singleNumber(int[] nums) {
        if (nums.length == 0)
            return 0;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] ^ nums[i - 1];
        }
        return nums[nums.length - 1];
    }

    //todo 3个重复数字找1个（不会）
    public int singleNumberII(int[] nums) {
        // sum取和太大
        int a = 0, b = 0;
        for (int x : nums) {
            b = (b ^ x) & ~a;
            a = (a ^ x) & ~b;
        }
        return b;
    }

    public boolean palindrome(String s) {
        char[] c = s.toCharArray();
        int st = 0;
        int en = s.length() - 1;
        while (st <= en) {
            if (st == en)
                return true;
            if (st == en - 1 && c[st] == c[en])
                return true;
            if (c[st] == c[en]) {
                st++;
                en--;
            } else
                return false;
        }
        return false;
    }

    //暴力法
    public String longestPalindrome(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length(); j >= i; j--) {
                String temp = s.substring(i, j);
                if (palindrome(temp)) {
                    if (temp.length() > ans.length()) {
                        ans = temp;
                    }
                    break;
                }
            }
        }
        return ans;
    }

    //动态规划解最长回文子串
    public String longestPalindromeDP(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int longestPalindrome = 1;
        String longestPalindromeStr = s.substring(0, 1);
        boolean[][] dp = new boolean[len][len];
        // abcdedcba
        //   l   r
        // 如果 dp[l, r] = true 那么 dp[l + 1, r - 1] 也一定为 true
        // 关键在这里：[l + 1, r - 1] 一定至少有 2 个元素才有判断的必要
        // 因为如果 [l + 1, r - 1] 只有一个元素，不用判断，一定是回文串
        // 如果 [l + 1, r - 1] 表示的区间为空，不用判断，也一定是回文串
        // [l + 1, r - 1] 一定至少有 2 个元素 等价于 l + 1 < r - 1，即 r - l >  2

        // 写代码的时候这样写：如果 [l + 1, r - 1]  的元素小于等于 1 个，即 r - l <=  2 ，就不用做判断了

        // 因为只有 1 个字符的情况在最开始做了判断
        // 左边界一定要比右边界小，因此右边界从 1 开始
        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                // 区间应该慢慢放大
                // 状态转移方程：如果头尾字符相等并且中间也是回文
                // 在头尾字符相等的前提下，如果收缩以后不构成区间（最多只有 1 个元素），直接返回 True 即可
                // 否则要继续看收缩以后的区间的回文性
                // 重点理解 or 的短路性质在这里的作用
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > longestPalindrome) {
                        longestPalindrome = r - l + 1;
                        longestPalindromeStr = s.substring(l, r + 1);
                    }
                }
            }
        }
        return longestPalindromeStr;
    }

    //一维动态规划超时
    public int maxProduct(int[] nums) {
        int[] dp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (j == i)
                    dp[i] = nums[i];
                else
                    dp[i] = dp[i] * nums[j];
                if (max < dp[i])
                    max = dp[i];
            }
        }
        return max;
    }


    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target)
                dp[nums[i]] = 1;
        }
        for (int i = nums[0]; i < dp.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j] && dp[i - nums[j]] != 0)
                    dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }

    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0;
        int preMax = 0;
        for (int i = 0; i < A.length; i++) {
            ans = Math.max(ans, preMax + A[i] - i);
            preMax = Math.max(preMax, A[i] + i);
        }
        return ans;
    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;
        int ans = 0;
        int[][] h = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && h[i][j] == 0) {
                    ans++;
                    numIslandsDiGui(h, grid, i, j);
                }
            }
        }
        return ans;
    }

    public void numIslandsDiGui(int[][] h, char[][] grid, int x, int y) {
        if (x == -1 || y == -1 || x == grid.length || y == grid[0].length)
            return;
        if (h[x][y] == 1)
            return;
        if (grid[x][y] == '0') {
            return;
        }
        h[x][y] = 1;
        numIslandsDiGui(h, grid, x + 1, y);
        numIslandsDiGui(h, grid, x, y + 1);
        numIslandsDiGui(h, grid, x - 1, y);
        numIslandsDiGui(h, grid, x, y - 1);
    }

    public int numIslandsBFS(char[][] grid) {
        if (grid.length == 0)
            return 0;
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islandBFS(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    public void islandBFS(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        grid[i][j] = '0';
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if (x + 1 < grid.length && grid[x + 1][y] == '1') {
                queue.add(new int[]{x + 1, y});
                grid[x + 1][y] = '0';
            }
            if (y + 1 < grid[0].length && grid[x][y + 1] == '1') {
                queue.add(new int[]{x, y + 1});
                grid[x][y + 1] = '0';
            }
            if (y - 1 >= 0 && grid[x][y - 1] == '1') {
                queue.add(new int[]{x, y - 1});
                grid[x][y - 1] = '0';
            }
            if (x - 1 >= 0 && grid[x - 1][y] == '1') {
                queue.add(new int[]{x - 1, y});
                grid[x - 1][y] = '0';
            }
        }
    }


    public int robII(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        int[] dpI = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0];
        dpI[0] = 0;
        dpI[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        for (int i = 2; i < nums.length; i++) {
            dpI[i] = Math.max(dpI[i - 1], nums[i] + dpI[i - 2]);
        }
        return Math.max(dp[dp.length - 2], dpI[dpI.length - 1]);
    }

    public int brokenCalc(int X, int Y) {
        int ans = 0;
        if (X >= Y)
            return X - Y;
        while (Y > X) {
            if ((Y & 1) == 1)
                Y++;
            else
                Y /= 2;
            ans++;
        }
        if (X > Y)
            ans += X - Y;
        return ans;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
//        if (k < 1) return 0;
//        int ans = 0;
//        int start = 0;
//        int end = 0;
//        int sum = 1;
//        while (end < nums.length) {
//            sum *= nums[end];
//            while (sum >= k) sum /= nums[start++];
//            ans += end - start + 1;
//            end++;
//        }
//        return ans;
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            ans += right - left + 1;
        }
        return ans;

    }

    public int findUnsortedSubarray(int[] nums) {
        //排序算法太慢
//        int start = 0;
//        int end = nums.length - 1;
//        int[] h = new int[nums.length];
//        System.arraycopy(nums, 0, h, 0, h.length);
//        Arrays.sort(h);
//        while (start < end) {
//            if (h[start] == nums[start])
//                start++;
//            if (h[end] == nums[end])
//                end--;
//            if (h[start] != nums[start] && h[end] != nums[end])
//                break;
//        }
//        if (end > start)
//            return end - start + 1;
//        return 0;
        //input check
        if (nums == null || nums.length == 0)
            return 0;
        //
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int rightBorder = -1, leftBorder = nums.length;
        //从左往右遍历
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max)
                max = nums[i];
            else
                rightBorder = i;
        }
        //从右往左遍历
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] <= min)
                min = nums[i];
            else
                leftBorder = i;
        }
        return rightBorder > leftBorder ? rightBorder - leftBorder + 1 : 0;
    }


    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        String[] ans = new String[]{""};
        int[] h = new int[n + 1];
        getPermutationDiGui(sb, h, n, k, 0, ans);
        return ans[0];
    }

    public void getPermutationDiGui(StringBuilder sb, int[] h, int n, int k, int index, String[] ans) {
        if (sb.length() == n) {
            count++;
        }
        if (count == k) {
            count++;
            ans[0] = sb.toString();
            return;
        }
        if (count > k)
            return;
        if (sb.length() == n) {
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (h[i] == 1) continue;
            h[i] = 1;
            sb.append(i);
            getPermutationDiGui(sb, h, n, k, index + 1, ans);
            sb.deleteCharAt(sb.length() - 1);
            h[i] = 0;
        }
    }


    public int findDuplicateII(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
            if (nums[nums[i] - 1] == nums[i] && i != nums[i] - 1)
                return nums[i];
        }
        return 0;
    }


    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int ans = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    if (matrix[i][j] == '0')
                        dp[i][j] = 0;
                    else if (matrix[i - 1][j] == '1' && matrix[i][j - 1] == '1' && matrix[i - 1][j - 1] == '1') {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                }
                ans = Math.max(dp[i][j] * dp[i][j], ans);
            }

        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j] + " ,");
//            }
//            System.out.println();
//        }
        return ans;
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> lists = new ArrayList<>();
        if (n < 4)
            return lists;
        char[][] ch = new char[n][n];
//        int[][] h = new int[n][n];
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < ch[i].length; j++) {
                ch[i][j] = '.';
            }
        }
        NQueensDiGui(ch, 0, 0);
        return lists;
    }

    public void NQueensDiGui(char[][] ch, int x, int y) {
        for (int i = x; i < ch.length; i++) {

            for (int j = y; j < ch.length; j++) {
                if (queenSafe(ch, i, j)) {
                    ch[i][j] = 'Q';
                    if (i == ch.length - 1) {
                        System.out.println(Arrays.deepToString(ch));
                        return;
                    }
                    NQueensDiGui(ch, x + 1, 0);
                    ch[i][j] = '.';
                } else {
                    if (y == ch.length - 1)
                        return;
                    NQueensDiGui(ch, x, y + 1);
                }
            }
        }
    }

    public boolean queenSafe(char[][] ch, int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (ch[i][y] == 'Q')
                return false;
        }
        for (int j = y; j >= 0; j--) {
            if (ch[x][j] == 'Q')
                return false;
        }
        int tx = x;
        int ty = y;
        while (x >= 0 && y >= 0) {
            if (ch[x--][y--] == 'Q')
                return false;

        }
        while (tx >= 0 && ty <= ch.length - 1) {
            if (ch[tx--][ty++] == 'Q')
                return false;
        }
        return true;
    }

    public boolean isHappy(int n) {
        while (n != 1) {
            if (n == 4)
                return false;
            int temp = 0;
            while (n >= 1) {
                temp += Math.pow(n % 10, 2);
                n /= 10;
            }
            n = temp;
        }
        return true;
    }

    public ListNode reverseListME(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = reverseListME(head.next);
        head.next.next = head;
        head.next = null;
        return p;

//        if (head == null)
//            return head;
//        ListNode node = head;
//        while (head.next != null) {
//            ListNode temp = head.next;
//            head.next = head.next.next;
//            temp.next = node;
//            node = temp;
//        }
//        return node;
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int n2 = 0, n3 = 0, n5 = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.min(dp[n2] * 2, Math.min(dp[n3] * 3, dp[n5] * 5));
            if (dp[i] == dp[n2] * 2) n2++;
            if (dp[i] == dp[n3] * 3) n3++;
            if (dp[i] == dp[n5] * 5) n5++;
        }
        return dp[dp.length - 1];
    }

    //移动零
    public void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                count++;
            else
                nums[i - count] = nums[i];
        }
        for (int i = nums.length - count; i < nums.length; i++) {
            nums[i] = 0;
        }
        System.out.println(Arrays.toString(nums));
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return head;
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode ans = temp;
        while (head != null && head.val < x) {
            temp = temp.next;
            head = head.next;
        }
        while (head != null && head.next != null) {
            if (head.next.val < x) {
                ListNode node = head.next;
                head.next = head.next.next;
                node.next = temp.next;
                temp.next = node;
            } else
                head = head.next;
        }
        return ans.next;
    }

    public int findMaxLength(int[] nums) {
        int ans = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                count--;
            else
                count++;
            if (map.containsKey(count)) {
                ans = Math.max(i - map.get(count) + 1, ans);
            } else
                map.put(count, i + 1);
        }
        return ans;
    }

    //重构字符串
    public String reorganizeString(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c : S.toCharArray())
            counts[c - 'a'] += 100;
        for (int i = 0; i < 26; ++i)
            counts[i] += i;
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code : counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N + 1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= N) t = 0;
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }

    public boolean stoneGame(int[] piles) {
        int[] h = new int[piles.length];
        int start = 0;
        int end = piles.length - 1;
        boolean isFirst = true;
        for (int i = 0; i < h.length; i++) {
            if (isFirst)
                h[i] = piles[start++];
            else
                h[i] = piles[end--];
            isFirst = !isFirst;
        }
        int[] dp = new int[piles.length];
        dp[0] = Math.min(h[0], h[1]);
        dp[1] = Math.max(h[0], h[1]);
        for (int i = 2; i < h.length; i += 2) {
            if (dp[i - 1] + h[i] < dp[i - 2] + h[i + 1]) {
                dp[i + 1] = dp[i - 2] + h[i + 1];
                dp[i] = dp[i - 1] + h[i];
            } else {
                dp[i] = dp[i - 2] + h[i + 1];
                dp[i + 1] = dp[i - 1] + h[i];
            }
        }
        return dp[dp.length - 1] > dp[dp.length - 2];
    }

    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 == -1 || grid[i - 1][j] == 0)
                        ans++;
                    if (i + 1 == grid.length || grid[i + 1][j] == 0)
                        ans++;
                    if (j - 1 == -1 || grid[i][j - 1] == 0)
                        ans++;
                    if (j + 1 == grid[i].length || grid[i][j + 1] == 0)
                        ans++;
                }
            }
        }
        return ans;
    }

    //二分搜索
    public int searchII(int[] nums, int target) {
        int index = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                start = mid + 1;
            else if (nums[mid] > target)
                end = mid - 1;
        }
        return index;
    }

    public int squareCount = 4;
    public boolean isSquare = false;

    //火柴拼正方形
    public boolean makesquare(int[] nums) {
        if (nums.length < 4)
            return false;
        int sum = 0;
        int side = 0;
        Arrays.sort(nums);
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0)
            return false;
        else
            side = sum / 4;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > side)
                return false;
        }
        if (nums[nums.length - 1] < side && nums[nums.length - 1] + nums[0] > side)
            return false;
        int[] h = new int[nums.length];
        makesquareDiGui(nums, h, side, 0, 0);
        return isSquare;
    }

    public void makesquareDiGui(int[] nums, int[] h, int side, long sum, int d) {
        if (squareCount == 0) {
            isSquare = true;
            return;
        }
        if (sum == side) {
            squareCount--;
            sum = 0;
        } else if (sum > side) {
            return;
        }
        for (int i = d; i < nums.length; i++) {
            if (h[i] == 1) continue;
            h[i] = 1;
            sum += nums[i];
            makesquareDiGui(nums, h, side, sum, i + 1);
            h[i] = 0;
            sum -= nums[i];
        }
    }

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        char[] cs = S.toCharArray();
        char[] ct = T.toCharArray();
        int s1 = 0;
        int c1 = 0;
        while (s1 < cs.length) {
            if (cs[s1] == '#') {
                if (!stackS.isEmpty())
                    stackS.pop();
            } else
                stackS.push(cs[s1]);
            s1++;
        }
        while (c1 < ct.length) {
            if (ct[c1] == '#') {
                if (!stackT.isEmpty())
                    stackT.pop();
            } else
                stackT.push(ct[c1]);
            c1++;
        }
        if (stackS.size() != stackT.size())
            return false;
        while (!stackS.isEmpty()) {
            if (stackS.pop() != stackT.pop())
                return false;
        }
        return true;
    }

    public String[] findWords(String[] words) {
        Character[] c1 = new Character[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
        Character[] c2 = new Character[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
        Character[] c3 = new Character[]{'z', 'x', 'c', 'v', 'b', 'n', 'm'};
        Set<Character> set1 = new HashSet<>(Arrays.asList(c1));
        Set<Character> set2 = new HashSet<>(Arrays.asList(c2));
        Set<Character> set3 = new HashSet<>(Arrays.asList(c3));
        List<String> ans = new ArrayList<>();
        outer:
        for (String word : words) {
            char[] h = word.toLowerCase().toCharArray();
            int start = 0;
            if (set1.contains(h[start])) {
                while (start < h.length) {
                    if (!set1.contains(h[start++]))
                        continue outer;
                }
            } else if (set2.contains(h[start])) {
                while (start < h.length) {
                    if (!set2.contains(h[start++]))
                        continue outer;
                }
            } else if (set3.contains(h[start])) {
                while (start < h.length) {
                    if (!set3.contains(h[start++]))
                        continue outer;
                }
            }
            ans.add(word);
        }
        return ans.toArray(new String[ans.size()]);
    }

    //灯泡开关
    public int bulbSwitch(int n) {
        return ((int) Math.sqrt(n));
    }

    //动态规划并非是用单调栈（动态规划不理解）
    public int maxCoins(int[] nums) {
        //dp[i][j]代表i->j的最大值
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        //左右+1方便操作。 nums[-1] = nums[n] = 1
        int[] newNums = new int[nums.length + 2];
        for (int i = 1; i < newNums.length - 1; i++) {
            newNums[i] = nums[i - 1];
        }
        newNums[0] = 1;
        newNums[newNums.length - 1] = 1;
        //从2开始，保证最少3个
        for (int j = 2; j < newNums.length; j++) {
            //遍历所有的可能性，0-2...0-n,1-3...1-n,...
            for (int i = 0; i < newNums.length - j; i++) {
                for (int k = i + 1; k < i + j; k++) {
                    dp[i][i + j] = Math.max(dp[i][i + j], dp[i][k] + dp[k][i + j] + newNums[i] * newNums[k] * newNums[i + j]);
                }
            }
        }
        return dp[0][newNums.length - 1];
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.sort(coins);
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (coin == i)
                    dp[i] = 1;
                else if (coin < i) {
                    if (dp[i - coin] == Integer.MAX_VALUE)
                        continue;
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public void gameOfLife(int[][] board) {
        if (board.length == 0)
            return;
        int[][] h = new int[board.length + 2][board[0].length + 2];
        for (int i = 1; i < h.length - 1; i++) {
            for (int j = 1; j < h[i].length - 1; j++) {
                h[i][j] = board[i - 1][j - 1];
            }
        }
        for (int i = 1; i < h.length - 1; i++) {
            for (int j = 1; j < h[i].length - 1; j++) {
                if (isAlive(h, i, j))
                    board[i - 1][j - 1] = 1;
                else
                    board[i - 1][j - 1] = 0;
            }
        }
    }

    public boolean isAlive(int[][] h, int i, int j) {
        int round = 0;
        if (h[i - 1][j] == 1)
            round++;
        if (h[i - 1][j - 1] == 1)
            round++;
        if (h[i][j + 1] == 1)
            round++;
        if (h[i + 1][j + 1] == 1)
            round++;
        if (h[i + 1][j] == 1)
            round++;
        if (h[i + 1][j - 1] == 1)
            round++;
        if (h[i][j - 1] == 1)
            round++;
        if (h[i - 1][j + 1] == 1)
            round++;
        if (h[i][j] == 0) {
            if (round == 3)
                return true;
            else
                return false;
        } else {
            if (round == 2 || round == 3)
                return true;
            else
                return false;
        }
    }

    //279. 完全平方数
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        List<Integer> list = new ArrayList<>();
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            if (Math.pow((int) Math.sqrt(i), 2) == i) {
                dp[i] = 1;
                list.add(i);
            } else {
                for (Integer integer : list) {
                    dp[i] = Math.min(dp[i], dp[i - integer] + dp[integer]);
                }
            }
        }
        return dp[n];
    }

    //279. 完全平方数BFS
    public int numSquaresBFS(int n) {
        int[] v = new int[n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 1});
        v[n] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int temp = cur[0];
            int step = cur[1];
            for (int i = 1; i < n; i++) {
                int t = temp - i * i;
                if (t > 0 && v[t] == 0) {
                    v[t] = 1;
                    queue.add(new int[]{t, step + 1});
                } else if (t < 0)
                    break;
                else if (t == 0) {
                    return step;
                }
            }
        }
        return -1;
    }

    public int integerBreak(int n) {
        if (n == 2)
            return 1;
        else if (n == 3)
            return 2;
        int[] dp = new int[n + 1];
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
        }
        return dp[n];
    }

    //移位二维矩阵
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        if (grid.length == 0)
            return null;
        int[] h = new int[grid.length * grid[0].length];
        int index = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                h[(index + k) % h.length] = grid[i][j];
                index++;
            }
        }
        index = 0;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < grid[i].length; j++) {
                temp.add(h[index++]);
            }
            list.add(temp);
        }
        return list;
    }

    //最大被3整除和
    public int maxSumDivThree(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans += num;
        }
        if (ans % 3 == 0)
            return ans;
        else if (ans % 3 == 1) {

            return ans;
        } else {
            return ans;
        }
    }

    // 无价值01背包
    public int backPack(int m, int[] A) {
        int[] dp = new int[m + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int v = m; v >= A[i - 1]; v--) {
                dp[v] = Math.max(dp[v], dp[v - A[i - 1]] + A[i - 1]);
            }
        }
        return dp[m];
    }

    //有价值01背包
    public int backPack(int m, int[] A, int[] V) {
        int[] dp = new int[m + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int v = m; v >= A[i - 1]; v--) {
                dp[v] = Math.max(dp[v], dp[v - A[i - 1]] + V[i - 1]);
            }
        }
        return dp[m];
    }

    //青蛙过河
    public boolean canCross(int[] stones) {
        if (stones[1] != 1)
            return false;
        List<Set<Integer>> lists = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        while (index < stones.length) {
            map.put(stones[index], index++);
        }
        for (int i = 0; i < stones.length; i++) {
            lists.add(new HashSet<>());
        }
        lists.add(new HashSet<>());
        lists.get(1).add(1);
        for (int i = 1; i < lists.size(); i++) {
            Set<Integer> temp = new HashSet<>(lists.get(i));
            for (Integer step : temp) {
                int next1 = stones[i] + step;
                int next2 = stones[i] + step + 1;
                int next3 = stones[i] + step - 1;
                if (map.containsKey(next1) && step > 0 && map.get(next1) > i)
                    lists.get(map.get(next1)).add(step);
                if (map.containsKey(next2) && step + 1 > 0 && map.get(next2) > i)
                    lists.get(map.get(next2)).add(step + 1);
                if (map.containsKey(next3) && step - 1 > 0 && map.get(next3) > i)
                    lists.get(map.get(next3)).add(step - 1);
            }
        }
        return !lists.get(lists.size() - 2).isEmpty();
    }

    //638大礼包  7维数组显然不成立
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> map = new HashMap();
        return shopping(price, special, needs, map);
    }

    public int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> map) {
        if (map.containsKey(needs))
            return map.get(needs);
        int j = 0, res = dot(needs, price);
        for (List<Integer> s : special) {
            ArrayList<Integer> clone = new ArrayList<>(needs);
            for (j = 0; j < needs.size(); j++) {
                int diff = clone.get(j) - s.get(j);
                if (diff < 0)
                    break;
                clone.set(j, diff);
            }
            if (j == needs.size())
                res = Math.min(res, s.get(j) + shopping(price, special, clone, map));
        }
        map.put(needs, res);
        return res;
    }

    public int dot(List<Integer> a, List<Integer> b) {
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i) * b.get(i);
        }
        return sum;
    }

    //单词拼接
    public boolean wordBreak(String s, List<String> wordDict) {
        int[] h = new int[s.length() + 1];
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i < h.length; i++) {
            for (int j = 0; j < i; j++) {
                String s1 = s.substring(j, i);
                if (set.contains(s1)) {
                    if (h[j] + s1.length() == i) {
                        h[i] = i;
                        break;
                    }
                }
            }
        }
        return h[h.length - 1] == h.length - 1;
    }

    //309买卖股票含冷冻期
    public int maxProfitCold(int[] prices) {
        // 0 买，1卖，2冷冻期，3空
        int[][] dp = new int[prices.length + 1][4];
        for (int i = 1; i < dp.length; i++) {
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4; j++) {

                }
            }
        }
        return dp[prices.length][0];
    }

    //最长上升子序列
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < i; j++) {
                if (nums[i - 1] > nums[j - 1])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    public int findSumCount = 0;

    //目标和
    public int findTargetSumWays(int[] nums, int S) {
        int max = 0;
        int min = 0;
        for (int num : nums) {
            max += num;
            min -= num;
        }
        if (max < S || min > S)
            return 0;
        findTargetSumWaysDiGui(nums, S, 0, 0);
        return findSumCount;
    }

    public void findTargetSumWaysDiGui(int[] nums, int S, int i, int sum) {
        if (sum == S && i == nums.length) {
            findSumCount++;
        }
        if (i == nums.length)
            return;
        int temp = nums[i];
        findTargetSumWaysDiGui(nums, S, i + 1, sum + temp);
        findTargetSumWaysDiGui(nums, S, i + 1, sum - temp);
    }

    //目标和(动态规划)
    public int findTargetSumWaysDP(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }

    //523. 连续的子数组和
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
            if (k == 0) {
                if (dp[i] == 0 && i >= 2)
                    return true;
            } else if (i >= 2 && dp[i] % k == 0) {
                return true;
            }
            for (int j = 1; j < i - 1; j++) {
                if (k == 0) {
                    if (dp[i] - dp[j] == 0)
                        return true;
                } else if ((dp[i] - dp[j]) % k == 0)
                    return true;
            }
        }
        return false;
    }

    //650. 只有两个键的键盘（这递归牛逼）
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        int h = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= h; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    public int fib(int N) {
        int a = 0;
        int b = 1;
        if (N == 0)
            return a;
        if (N == 1)
            return b;
        int c = 0;
        for (int i = 2; i <= N; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            int x = Math.abs(points[i][0] - points[i - 1][0]);
            int y = Math.abs(points[i][1] - points[i - 1][1]);
            ans += Math.min(x, y);
            ans += Math.abs(x - y);
        }
        return ans;
    }

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                row[i] += 1;
                col[j] += 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                if (row[i] == 1 && col[j] == 1) continue;
                ans++;
            }
        }
        return ans;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        Arrays.sort(products);
        out:
        for (int i = 1; i <= searchWord.length(); i++) {
            String temp = searchWord.substring(0, i);
            List<String> list = new ArrayList<>();
            for (int j = 0; j < products.length; j++) {
                if (products[j].length() < temp.length())
                    continue;
                if (products[j].substring(0, i).equals(temp))
                    list.add(products[j]);
                if (list.size() == 3) {
                    ans.add(list);
                    continue out;
                }
            }
            ans.add(list);
        }
        return ans;
    }

    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3)
            return 0;
        int ans = 0;
        int curlen = 2;
        int curnum = A[1] - A[0];
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == curnum)
                curlen++;
            else {
                if (curlen > 2) {
                    ans += (curlen - 2) * (curlen - 1) / 2;
                }
                curnum = A[i] - A[i - 1];
                curlen = 2;
            }
        }
        return ans;
    }

    //695. 岛屿的最大面积
    int maxAreaOfIslandCount = 0;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0)
            return 0;
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    maxAreaOfIslandCount = 0;
                    maxAreaOfIslandBFS(grid, i, j);
                    ans = Math.max(ans, maxAreaOfIslandCount);
                }
            }
        }
        return ans;
    }

    public void maxAreaOfIslandBFS(int[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        grid[i][j] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            maxAreaOfIslandCount++;
            for (int d = 0; d < dict.length; d++) {
                int x = cur[0] + dict[d][0];
                int y = cur[1] + dict[d][1];
                if (isInArea(grid, x, y)) {
                    if (grid[x][y] == 1) {
                        grid[x][y] = 0;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
    }

    public void maxAreaOfIslandDFS(int[][] grid, int i, int j) {
        if (i == -1 || j == -1 || i == grid.length || j == grid[0].length)
            return;
        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            maxAreaOfIslandCount++;
            maxAreaOfIslandDFS(grid, i + 1, j);
            maxAreaOfIslandDFS(grid, i - 1, j);
            maxAreaOfIslandDFS(grid, i, j + 1);
            maxAreaOfIslandDFS(grid, i, j - 1);
        }
    }


    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i) {
            i++;
        }
        return i;
    }

    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap();
        for (int i = 0; i < N; ++i)
            index.put(A[i], i);
        Map<Integer, Integer> longest = new HashMap();
        int ans = 0;
        for (int k = 0; k < N; ++k)
            for (int j = 0; j < k; ++j) {
                int i;
                if (index.containsKey(A[k] - A[j])) {
                    i = index.get(A[k] - A[j]);
                } else
                    i = -1;
                if (i >= 0 && i < j) {
                    int cand;
                    if (index.containsKey(i * N + j)) {
                        cand = index.get(i * N + j);
                    } else
                        cand = 2;
                    longest.put(j * N + k, cand);
                    ans = Math.max(ans, cand);
                }
            }

        return ans >= 3 ? ans : 0;
    }

    public String reverseStr(String s, int k) {
        if (k == 1 || s.equals(""))
            return s;
        Stack<Character> stackRE = new Stack<>();
        List<Character> list = new ArrayList<>();
        char[] c = s.toCharArray();
        int start = 0;
        StringBuilder sb = new StringBuilder();
        while (start < c.length) {
            if (start % (2 * k) < k) {
                while (!list.isEmpty()) {
                    sb.append(list.remove(0));
                }
                stackRE.push(c[start]);
            } else {
                while (!stackRE.isEmpty()) {
                    sb.append(stackRE.pop());
                }
                list.add(c[start]);
            }
            start++;
        }
        while (!stackRE.isEmpty()) {
            sb.append(stackRE.pop());
        }
        while (!list.isEmpty()) {
            sb.append(list.remove(0));
        }
        return sb.toString();
    }

    public int findJudge(int N, int[][] trust) {
        if (N == 1)
            return 1;
        int[] h = new int[N + 1];
        for (int[] ints : trust) {
            h[ints[1]]++;
            h[ints[0]]--;
        }
        int ans = -1;
        for (int i = 0; i < h.length; i++) {
            if (h[i] == N - 1)
                return i;
        }
        return ans;
    }

    public int minFallingPathSum(int[][] A) {
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                int temp = A[i][j];
                if (j == 0) {
                    A[i][j] = Math.min(A[i - 1][j] + temp, A[i - 1][j + 1] + temp);
                } else if (j == A[0].length - 1) {
                    A[i][j] = Math.min(A[i - 1][j - 1] + temp, A[i - 1][j] + temp);
                } else {
                    A[i][j] = Math.min(Math.min(A[i - 1][j - 1] + temp, A[i - 1][j] + temp), A[i - 1][j + 1] + temp);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < A[A.length - 1].length; i++) {
            ans = Math.min(ans, A[A.length - 1][i]);
        }
        return ans;
    }

    //todo 190. 颠倒二进制位（进制不懂）
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }

    public boolean isPowerOfThree(int n) {
        if (n == 0)
            return false;
        if (n == 3)
            return true;
        if (n % 3 == 0)
            return isPowerOfThree(n / 3);
        return false;
    }

    //fizzBuzz
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        String F = "Fizz";
        for (int i = 1; i <= n; i++) {
            String s = "";
            if (i % 3 == 0)
                s += "Fizz";
            if (i % 5 == 0)
                s += "Buzz";
            if (s.isEmpty())
                s = String.valueOf(i);
            ans.add(s);
        }
        return ans;
    }

    //todo [143]重排链表
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        boolean isOne = true;
        int len = 0;
        LinkedList<ListNode> list = new LinkedList<>();
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            if (isOne)
                list.add(head);
            else
                stack.add(head);
            isOne = !isOne;
            head = head.next;
            len++;
        }
        head = list.getFirst();
        isOne = true;
        for (int i = 0; i < len; i++) {

        }
    }

    //todo [673]最长递增子序列的个数
    public int findNumberOfLIS(int[] nums) {
        int ans = 0;
        int max = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        return ans;
    }

    //todo 790. 多米诺和托米诺平铺
    public int numTilings(int N) {
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        if (N < 4)
            return dp[N];
        for (int i = 4; i < dp.length; i++) {
            /*dp[i-1] 是只能用X来拼
                            X
              dp[i-2]是只能用XX来拼
                           YY
              dp[i-3]是只能用XXY和XYY来拼
                           XYY  XXY
              但是我忽略了XXZZ 和 XYYZ 这类情况
                       XYYZ    XXZZ
             */
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] * 4;
        }
        return dp[N];
    }

    public boolean divisorGame(int N) {
        return (N & 1) == 0;
    }

    //984. 不含 AAA 或 BBB 的字符串
    public String strWithout3a3b(int A, int B) {
        int len = A + B;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        if (A >= B) {
            while (index < len) {
                if (A - B > 1) {
                    if (A > 1) {
                        A -= 2;
                        index += 2;
                        sb.append("aa");
                    } else if (A > 0) {
                        A--;
                        index++;
                        sb.append("a");
                    }
                    if (B > 0) {
                        B--;
                        index++;
                        sb.append("b");
                    }
                } else {
                    if (A > 0) {
                        A--;
                        index++;
                        sb.append("a");
                    }
                    if (B > 0) {
                        B--;
                        index++;
                        sb.append("b");
                    }
                }
            }
        } else {
            while (index < len) {
                if (B - A > 1) {
                    if (B > 1) {
                        B -= 2;
                        index += 2;
                        sb.append("bb");
                    } else if (B > 0) {
                        B--;
                        index++;
                        sb.append("b");
                    }
                    if (A > 0) {
                        A--;
                        index++;
                        sb.append("a");
                    }
                } else {
                    if (A > 0) {
                        A--;
                        index++;
                        sb.append("a");
                    }
                    if (B > 0) {
                        B--;
                        index++;
                        sb.append("b");
                    }
                }
            }
        }
        return sb.toString();
    }

    public int[] prevPermOpt1(int[] A) {
        if (A.length == 1)
            return A;
        int i;
        for (i = A.length - 1; i >= 0; i--) {
            if (i == 0)
                return A;
            if (A[i] < A[i - 1]) {
                break;
            }
        }
        i = i - 1;
        int len = A.length - 1;
        while (len > i) {
            if (A[len] >= A[i]) {
                len--;
                continue;
            }
            if (A[len] == A[len - 1]) {
                len--;
                continue;
            }
            break;
        }
        int temp = A[i];
        A[i] = A[len];
        A[len] = temp;
        return A;
    }

    //948. 令牌放置
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int count = 0;
        if (tokens.length == 0 || P < tokens[0]) {
            return count;
        }
        boolean ismax = true;
        int cur = 0;
        int max = tokens.length - 1;
        while (cur <= max) {
            if (P >= tokens[cur]) {
                P -= tokens[cur];
                cur++;
                count++;
                ismax = false;
            } else {
                P += tokens[max];
                count--;
                max--;
                ismax = true;
            }
        }
        return ismax ? count + 1 : count;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        int[] h = new int[1001];
        for (int[] trip : trips) {
            h[trip[1]] += trip[0];
            h[trip[2]] -= trip[0];
        }
        for (int i = 1; i < h.length; i++) {
            h[i] += h[i - 1];
            if (h[i] > capacity)
                return false;
        }
        return true;
    }

    //1007. 行相等的最少多米诺旋转
    public int minDominoRotations(int[] A, int[] B) {
        int i = 0;
        int count = 0;
        int[] Ah = new int[7];
        int[] Bh = new int[7];
        int len = A.length;
        for (int j = 0; j < len; j++) {
            Ah[A[j]]++;
            Bh[B[j]]++;
        }
        int maxA = 0;
        int maxB = 0;
        int ai = 1;
        int bi = 1;
        for (int k = 1; k < 7; k++) {
            if (Ah[k] > maxA) {
                maxA = Ah[k];
                ai = k;
            }
            if (Bh[k] > maxB) {
                maxB = Bh[k];
                bi = k;
            }
        }
        if (maxA > maxB) {
            while (i < len) {
                if (A[i] != ai && B[i] != ai)
                    return -1;
                if (A[i] != ai) {
                    count++;
                }
                i++;
            }
        } else {
            while (i < len) {
                if (A[i] != bi && B[i] != bi)
                    return -1;
                if (B[i] != bi) {
                    count++;
                }
                i++;
            }
        }
        return count;
    }

    //1247. 交换字符使得字符串相同
    public int minimumSwap(String s1, String s2) {
        int ans = 0;
        int c1x = 0;
        int c2x = 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] == c2[i])
                continue;
            if (c1[i] == 'x') {
                c1x++;
                if (c1x == 2) {
                    ans++;
                    c1x = 0;
                }
            }
            if (c2[i] == 'x') {
                c2x++;
                if (c2x == 2) {
                    ans++;
                    c2x = 0;
                }
            }
        }
        if (c1x != c2x)
            return -1;
        if (c1x == 1)
            return ans + 2;
        else
            return ans;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int max = 1000_001;
        int min = 1;
        while (min < max) {
            int mid = (max + min) >>> 1;
            int temp = threshold;
            int i = 0;
            boolean can = true;
            for (int num : nums) {
                temp -= (num + mid - 1) / mid;
                if (temp < 0) {
                    can = false;
                    break;
                }
            }
            if (can)
                max = mid;
            else
                min = mid + 1;
        }
        return min;
    }

    public int removeDuplicatesII(int[] nums) {
        if (nums.length < 3)
            return nums.length;
        int cur = 1;
        int end = 1;
        int len = nums.length - 1;
        while (end < nums.length && cur + 1 < nums.length) {
            if (nums[cur] == nums[cur - 1]) {
                if (nums[end] == nums[cur]) {
                    end++;
                } else {
                    nums[cur + 1] = nums[end];
                    cur++;
                    end++;
                }
            } else if (nums[cur] < nums[cur - 1]) {
                nums[cur] = nums[end];
                end++;
            } else {
                cur++;
                if (end < cur)
                    end = cur;
            }
        }
        return cur + 1;
    }

    int maxBill = 0;

    public int tallestBillboard(int[] rods) {
        Integer[] temp = new Integer[rods.length];
        int sum = 0;
        for (int i = 0; i < rods.length; i++) {
            temp[i] = rods[i];
            sum += rods[i];
        }
        Arrays.sort(temp, Collections.reverseOrder());
        tallestBillDFS(0, temp, rods.length, 0, 0, sum);
        return maxBill;
    }

    public void tallestBillDFS(int i, Integer[] rods, int len, int left, int right, int sum) {
        if (left == right) {
            maxBill = Math.max(maxBill, left);
        }
        if (Math.abs(left - right) > sum || left + right + sum <= maxBill * 2)
            return;
        if (i == len)
            return;
        tallestBillDFS(i + 1, rods, len, left + rods[i], right, sum - rods[i]);
        tallestBillDFS(i + 1, rods, len, left, right + rods[i], sum - rods[i]);
        tallestBillDFS(i + 1, rods, len, left, right, sum - rods[i]);
    }

    //207. 课程表BFS
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            indegrees[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0)
                queue.add(i);
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            numCourses--;
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == cur) {
                    indegrees[prerequisite[0]]--;
                    if (indegrees[prerequisite[0]] == 0)
                        queue.add(prerequisite[0]);
                }
            }
        }
        return numCourses == 0;
    }

    //207. 课程表DFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] h = new int[numCourses][numCourses];
        int[] v = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            h[prerequisite[1]][prerequisite[0]] = 1;
        }
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(i, v, h))
                return false;
        }
        return true;
    }

    public boolean canFinishDFS(int cur, int[] v, int[][] h) {
        if (v[cur] == 1) return false;
        if (v[cur] == -1) return true;
        v[cur] = 1;
        for (int i = 0; i < v.length; i++) {
            if (h[cur][i] == 1 && !canFinishDFS(i, v, h))
                return false;
        }
        v[cur] = -1;
        return true;
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] v = new int[board.length][board[0].length];
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        int[][] dict = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};
        while (!queue.isEmpty()) {
            boolean boom = false;
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if (board[x][y] == 'E')
                board[x][y] = 'B';
            for (int[] ints : dict) {
                int tempx = x + ints[0];
                int tempy = y + ints[1];
                if (tempx < 0 || tempx >= board.length || tempy < 0 || tempy >= board[0].length)
                    continue;
                if (board[tempx][tempy] == 'M' && v[x][y] == 0) {
                    boom = true;
                    if (board[x][y] == 'B') {
                        board[x][y] = '1';
                    } else {
                        board[x][y]++;
                    }
                }
            }
            if (!boom) {
                for (int[] ints : dict) {
                    int tempx = x + ints[0];
                    int tempy = y + ints[1];
                    if (tempx < 0 || tempx >= board.length || tempy < 0 || tempy >= board[0].length)
                        continue;
                    if (board[tempx][tempy] == 'E' && v[x][y] == 0) {
                        queue.add(new int[]{tempx, tempy});
                    }
                }
            }
            v[x][y] = 1;
        }
        return board;
    }

    //424. 替换后的最长重复字符(滑动窗口)
    public int characterReplacement(String s, int k) {
        if (k >= s.length())
            return s.length();
        if (s.length() == 0) return 0;
        int l = 0, r = 0, res = 0;
        int[] dict = new int[256];
        int maxLen = 0;
        while (r < s.length()) {
            dict[s.charAt(r)]++;
            maxLen = Math.max(maxLen, dict[s.charAt(r)]);
            while ((r - l + 1 - maxLen) > k) {
                dict[s.charAt(l++)]--;
            }
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }

    //567. 字符串的排列
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        char[] c = s1.toCharArray();
        char[] c3 = s2.toCharArray();
        int start = 0;
        int end = c.length;
        int[] h = new int[26];
        int[] h2 = new int[26];
        for (char c1 : c) {
            h[c1 - 'a']++;
        }
        char[] c2 = s2.substring(start, end).toCharArray();
        for (char c1 : c2) {
            h2[c1 - 'a']++;
        }
        if (Arrays.equals(h, h2)) {
            return true;
        }
        while (end < s2.length()) {
            h2[c3[start] - 'a']--;
            h2[c3[end] - 'a']++;
            if (Arrays.equals(h, h2)) {
                return true;
            }
            start++;
            end++;
        }
        return false;
    }

    //524. 通过删除字母匹配到字典里最长单词
    public String findLongestWord(String s, List<String> d) {
        String ans = "";
        char[] c = s.toCharArray();
        for (String t : d) {
            char[] temp = t.toCharArray();
            int start1 = 0;
            int start2 = 0;
            while (start1 < c.length && start2 < temp.length) {
                if (c[start1] == temp[start2]) {
                    start2++;
                }
                start1++;
            }
            if (start2 == temp.length) {
                if (t.length() > ans.length()) {
                    ans = t;
                } else if (t.length() == ans.length()) {
                    if (ans.compareTo(t) > 0) {
                        ans = t;
                    }
                }
            }
        }
        return ans;
    }

    //todo 457. 环形数组循环
    public boolean circularArrayLoop(int[] nums) {
        return false;
    }

    //1255. 得分最高的单词集合
    int maxScoreWordsans = 0;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] h = new int[26];
        for (char c : letters) {
            h[c - 'a']++;
        }
        maxScoreWordsDFS(words, h, score, 0, 0);
        return maxScoreWordsans;
    }

    public void maxScoreWordsDFS(String[] words, int[] h, int[] score, int i, int sum) {
        if (i >= words.length)
            return;
        int[] temp = h.clone();
        int tempSum = 0;
        char[] tempc = words[i].toCharArray();
        for (char c : tempc) {
            if (temp[c - 'a'] > 0) {
                tempSum += score[c - 'a'];
                temp[c - 'a']--;
            } else {
                maxScoreWordsDFS(words, h, score, i + 1, sum);
                return;
            }
        }
        maxScoreWordsans = Math.max(maxScoreWordsans, sum + tempSum);
        maxScoreWordsDFS(words, temp, score, i + 1, sum + tempSum);
        maxScoreWordsDFS(words, h, score, i + 1, sum);
    }


    //太长了维护太难了，换新的
    public static void main(String[] args) {
        LeetCode t = new LeetCode();
        int[] nums = new int[]{-2, 1, -1, -2, -2};
        int[] nums2 = new int[]{3, 2, 3, 1, 3, 2, 3, 3, 2};
        String[] numsB = new String[]{"10", "0001", "111001", "1", "0"};
        int[][] ball = new int[][]{{3, 2}, {-2, 2}};
        int[][] grid = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
        };
        char[][] matrix = new char[][]{
                {'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}
        };
        new SpannableStringBuilder().append(null);
//        System.out.println(t.fizzBuzz(15));


//        List<Integer> price = new ArrayList<>();
//        List<List<Integer>> special = new ArrayList<>();
//        List<Integer> needs = new ArrayList<>();
//        price.add(2);
//        price.add(3);
//        price.add(4);
//        needs.add(1);
//        needs.add(2);
//        needs.add(1);
//        List<Integer> temp = new ArrayList<>();
//        temp.add(1);
//        temp.add(1);
//        temp.add(0);
//        temp.add(4);
//        special.add(new ArrayList<>(temp));
//        temp.clear();
//        temp.add(2);
//        temp.add(2);
//        temp.add(1);
//        temp.add(9);
//        special.add(new ArrayList<>(temp));
//        int[][] matrix = new int[][]{
//                {133, -523, -558, 846, -907, -1224, -1346, 787, -411, -1826},
//                {-1478, -853, -1401, 341, -26, 759, -444, 174, -1594, -2000},
//                {861, -584, 670, 696, 676, -1674, -1737, -1407, -484, 248},
//                {458, -1669, -419, -382, -895, 732, -1278, -1802, -527, 862},
//                {-1297, 544, -1943, 563, -380, -1268, 266, -1309, -1946, 85},
//                {-1981, -1631, -168, 741, -211, -1070, -1873, -554, 243, -901},
//                {849, 971, -21, -1111, 463, 944, -124, -1414, -1463, -1287},
//                {70, -1886, -1159, -73, 555, -426, -190, -1750, -1028, -188},
//                {-1220, -1654, -931, -1100, -433, -1643, -1281, -455, 904, -126},
//                {-1494, -632, 243, 90, 993, 322, 32, -388, -225, 952}
//        };
//        List<List<Integer>> triangle = new ArrayList<>();
//        List<Integer> list = new ArrayList<>();
//        list.add(2);
//        triangle.add(new ArrayList<>(list));
//        list.clear();
//        list.add(3);
//        list.add(4);
//        triangle.add(new ArrayList<>(list));
//        list.clear();
//        list.add(6);
//        list.add(5);
//        list.add(7);
//        triangle.add(new ArrayList<>(list));
//        list.clear();
//        list.add(4);
//        list.add(1);
//        list.add(8);
//        list.add(3);
//        triangle.add(new ArrayList<>(list));

//        List<Integer> list = new ArrayList<>();
//        List<List<Integer>> lists = new ArrayList<>();
//        list.add(2);
//        lists.add(new ArrayList<>(list));
//        list.clear();
//        list.add(3);
//        list.add(4);
//        lists.add(new ArrayList<>(list));
//        list.clear();
//        list.add(6);
//        list.add(5);
//        list.add(7);
//        lists.add(new ArrayList<>(list));
//        list.clear();
//        list.add(4);
//        list.add(1);
//        list.add(8);
//        list.add(3);
//        lists.add(new ArrayList<>(list));
//        list.clear();


//        int m[] = new int[]{1,1,1,2,3,4};
//        t.wiggleSort(m);
//        System.out.println(t.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
//        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(2);
//        l1.next.next.next.next = new ListNode(5);
//        l1.next.next.next.next.next = new ListNode(2);
//        ListNode l = t.partition(l1, 3);
//        while (l != null) {
//            System.out.println(l.val);
//            l = l.next;
//        }
    }
}
