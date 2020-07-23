package rui.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegmentTreeNode {
    // 线段范围
    int start;
    int end;
    // 线段中元素数
    int count;
    // 线段左孩子
    SegmentTreeNode left;
    // 线段右孩子
    SegmentTreeNode right;

    public SegmentTreeNode() {

    }

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        count = 0;
        left = null;
        right = null;
    }

    public SegmentTreeNode build(int start, int end) {
        if (start > end) return null;
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) {
            root.count = 0;
        } else {
            int mid = (start + end) >> 1;
            root.left = build(start, mid);
            root.right = build(mid + 1, end);
        }
        return root;
    }

    public int count(SegmentTreeNode root, int start, int end) {
        if (root == null || start > end) return 0;
        if (start == root.start && end == root.end) return root.count;
        int mid = (root.start + root.end) >> 1;
        int left = 0, right = 0;
        if (start <= mid) {
            if (mid < end) {
                left = count(root.left, start, mid);
            } else {
                left = count(root.left, start, end);
            }
        }
        if (mid < end) {
            if (start <= mid) {
                right = count(root.right, mid + 1, end);
            } else {
                right = count(root.right, start, end);
            }
        }
        return left + right;
    }

    public void insert(SegmentTreeNode root, int index, int val) {
        if (root.start == index && root.end == index) {
            root.count += val;
            return;
        }
        int mid = (root.start + root.end) >> 1;
        if (index >= root.start && index <= mid) {
            insert(root.left, index, val);
        }
        if (index > mid && index <= root.end) {
            insert(root.right, index, val);
        }
        root.count = root.left.count + root.right.count;
    }

    public List<Integer> countSmaller(int[] nums) {
        int m = nums.length;
        Integer[] ans = new Integer[m];
        if (m == 0) return new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        SegmentTreeNode root = build(min, max);
        for (int i = m - 1; i >= 0; i--) {
            ans[i] = count(root, min, nums[i] - 1);
            insert(root, nums[i], 1);
        }
        return Arrays.asList(ans);
    }

    public static void main(String[] args) {
        SegmentTreeNode n = new SegmentTreeNode();
        System.out.println(n.countSmaller(new int[]{-1, -2}));
    }

}
