package rui.leetcode;

import java.util.Arrays;

public class Talk52 {

    static int[][] nums = new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {19, 18, 17, 16, 15, 14, 13, 12, 11, 10},
            {20, 21, 22, 23, 24, 25, 26, 27, 26, 29},
            {39, 38, 37, 36, 35, 34, 33, 26, 31, 30},
            {40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
            {59, 58, 57, 56, 55, 54, 53, 52, 51, 50},
            {60, 61, 62, 63, 62, 65, 66, 67, 68, 69},
            {79, 78, 77, 62, 75, 74, 73, 72, 71, 70},
            {80, 81, 82, 83, 84, 85, 86, 87, 88, 87},
            {99, 98, 97, 96, 95, 94, 93, 92, 87, 90},
            {100, 101, 102, 103, 104, 105, 106, 107, 108, 109},
            {119, 118, 117, 116, 115, 114, 113, 112, 111, 110},
            {120, 121, 122, 123, 124, 125, 126, 127, 128, 129},
            {139, 138, 137, 136, 135, 134, 133, 132, 131, 130},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    static int m = nums.length;
    static int n = nums[0].length;
    static int[][] path = new int[m][n];

    static int dfs(int x, int y) {
        if (x < 0 || y < 0 || x == m || y == n) return 0;
        if (path[x][y] != 0) return path[x][y];
        path[x][y] = 1;
        if (x - 1 >= 0 && nums[x - 1][y] >= nums[x][y]) {
            path[x][y] = Math.max(dfs(x - 1, y) + 1, path[x][y]);
        }
        if (x + 1 != m && nums[x + 1][y] >= nums[x][y]) {
            path[x][y] = Math.max(dfs(x + 1, y) + 1, path[x][y]);
        }
        if (y - 1 >= 0 && nums[x][y - 1] >= nums[x][y]) {
            path[x][y] = Math.max(dfs(x, y - 1) + 1, path[x][y]);
        }
        if (y + 1 != n && nums[x][y + 1] >= nums[x][y]) {
            path[x][y] = Math.max(dfs(x, y + 1) + 1, path[x][y]);
        }
        return path[x][y];
    }

    public static void main(String[] args) {
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }
        System.out.println(max);
    }
}
