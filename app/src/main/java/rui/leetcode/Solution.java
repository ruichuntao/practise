package rui.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int[] nums;
    int n;
    int[] ans;

    public void merge(int l, int r) {
        if (l >= r) return;
        int m = (l + r) >>> 1;
        merge(l, m);
        merge(m + 1, r);
        int k = l;
        int len = r - l + 1;
        int s = 0;
        int mid = m + 1;
        int[] tmp = new int[len];
        while (l <= m && mid <= r) {
            if (nums[l] <= nums[mid]) {
                tmp[s++] = nums[l];
                l++;
            } else {
                tmp[s++] = nums[mid];
                mid++;
            }
        }
        while (l <= m) {
            tmp[s++] = nums[l];
            l++;
        }
        while (mid <= r) {
            tmp[s++] = nums[mid];
            mid++;
        }
        System.arraycopy(tmp, 0, nums, k, len);
    }

    public int reversePairs(int[] nums) {
        this.nums = nums;
        n = nums.length;
        ans = new int[n];
        merge(0, n - 1);
        System.out.println(Arrays.toString(ans));
        return 0;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.reversePairs(new int[]{5, 2, 6, 1}));
    }
}