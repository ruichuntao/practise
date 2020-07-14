package rui.leetcode;

import androidx.annotation.NonNull;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
