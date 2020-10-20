package rui.leetcode;

public class SegNode {

    int[] tree;
    int[] nums;

    public SegNode(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        tree = new int[n * 4];
    }

    public void build(int node, int start, int end) {
        if (start > end) return;
        if (start == end) {
            tree[node] = nums[start];
            return;
        }
        int left = node * 2 + 1;
        int right = node * 2 + 2;
        int mid = (start + end) >>> 1;
        build(left, start, mid);
        build(right, mid + 1, end);
        tree[node] = tree[left] + tree[right];
    }

    public void update(int node, int idx, int val, int start, int end) {
        if (start > end) return;
        if (start == end) {
            tree[node] = val;
            nums[idx] = val;
        } else {
            int mid = (start + end) >>> 1;
            int left = node * 2 + 1;
            int right = node * 2 + 2;
            if (idx >= start && idx <= mid)
                update(left, idx, val, start, mid);
            else
                update(right, idx, val, mid + 1, end);
//            tree[node] = Math.max(tree[left], tree[right]);
            tree[node] = tree[left] + tree[right];
        }
    }

    public void updates(int L, int R, int node, int val, int start, int end) {
        if (L > end || R < start) return;
        if (start == end) {
            if (L <= start && start <= end) {
                tree[node] = val;
                nums[start] = val;
            }
        } else {
            int mid = (start + end) >>> 1;
            int left = node * 2 + 1;
            int right = node * 2 + 2;
            updates(L, R, left, val, start, mid);
            updates(L, R, right, val, mid + 1, end);
            tree[node] = tree[left] + tree[right];
        }
    }

    public int query(int L, int R, int node, int start, int end) {
        if (L > end || R < start) return 0;
        if (L <= start && end <= R) return tree[node];
        if (start == end) return tree[node];
        else {
            int mid = (start + end) >>> 1;
            int left = node * 2 + 1;
            int right = node * 2 + 2;
            return query(L, R, left, start, mid) + query(L, R, right, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = num.length;
        SegNode node = new SegNode(num);
        node.build(0, 0, n - 1);
        System.out.println(node.query(3, 5, 0, 0, n - 1));
        System.out.println(node.query(0, 5, 0, 0, n - 1));
//        node.update(0, 5, 0, 0, n - 1);
        node.updates(0, 5, 0, 10, 0, n - 1);
        System.out.println(node.query(3, 5, 0, 0, n - 1));
        System.out.println(node.query(0, 5, 0, 0, n - 1));
    }
}
