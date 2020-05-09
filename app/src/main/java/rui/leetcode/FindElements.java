package rui.leetcode;

import java.util.HashSet;
import java.util.Set;

//1261. 在受污染的二叉树中查找元素
public class FindElements {
    Set<Integer> set = new HashSet<>();

    public FindElements(TreeNode root) {
        root.val = 0;
        set = new HashSet<>();
        set.add(root.val);
        dfs(root);
    }

    public void dfs(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null) {
            root.left.val = 2 * root.val + 1;
            set.add(root.left.val);
            dfs(root.left);
        }
        if (root.right != null) {
            root.right.val = 2 * root.val + 2;
            set.add(root.right.val);
            dfs(root.right);
        }
    }

    public boolean find(int target) {
        return set.contains(target);
    }
}
