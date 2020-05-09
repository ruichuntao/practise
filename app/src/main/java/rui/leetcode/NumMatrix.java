package rui.leetcode;

import java.util.Arrays;

public class NumMatrix {

    int[][] dp;

    public NumMatrix(int[][] matrix) {
        //总是有空数据是为什么？？？fixme
        if (matrix.length == 0)
            dp = new int[1][1];
        else {
            dp = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[i].length; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row2 + 1][col1] - dp[row1][col2 + 1] + dp[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{

        };
        NumMatrix matrix1 = new NumMatrix(matrix);
//        System.out.println(matrix1.sumRegion(0, 0, 2, 4));
    }
}
