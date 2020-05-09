package rui.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec {
    public static String s = "";

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) return "[]";
        List<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                list.add("null");
            } else
                list.add(String.valueOf(cur.val));
            if (cur != null) {
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        sb.append("[");
        for (String s : list) {
            sb.append(s);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        StringBuilder sb = new StringBuilder(data);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);
        if(sb.length() == 0) return null;
        data = sb.toString();
        String[] s = data.split(",");
        TreeNode ans;
        ans = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(ans);
        int idx = 0;
        while (idx < s.length - 1) {
            TreeNode cur = queue.poll();
            if (!s[++idx].equals("null")) {
                cur.left = new TreeNode(Integer.parseInt(s[idx]));
                queue.add(cur.left);
            }
            if (!s[++idx].equals("null")) {
                cur.right = new TreeNode(Integer.parseInt(s[idx]));
                queue.add(cur.right);
            }
        }
        return ans;
    }

    char[] c;

    public int[] c2i(char ch) {
        if (ch == 'R') return new int[]{0, 1};
        if (ch == 'L') return new int[]{0, -1};
        if (ch == 'U') return new int[]{1, 0};
        if (ch == 'D') return new int[]{-1, 0};
        return new int[0];
    }

    public int[] dfs(int x, int y, int idx) {
        if (idx == c.length) {
            return new int[]{x, y};
        }
        int[] dict = c2i(c[idx]);
        dfs(x + dict[0], y + dict[1], idx++);
        return new int[0];
    }

    public boolean judgeCircle(String moves) {
        c = moves.toCharArray();
        int[] ans = dfs(0, 0, 0);
        return (ans[0] == 0 && ans[1] == 0);
    }

    public static void main(String[] args) {
        TreeNode n = Codec.deserialize("[1]");
        System.out.println(Codec.serialize(n));
    }
}
