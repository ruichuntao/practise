package rui.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class PushBox {

    // 方向移动
    int[][] dicts = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    // 判断当前位置是否可用
    public boolean check(char[][] grid, int x, int y) {
        if (x < 0 || x == grid.length || y < 0 || y == grid[0].length || grid[x][y] == '#')
            return false;
        return true;

    }
    // 用来找从上一次的位置lx,ly能否到达准备推的位置px，py
    // 就是准备推这个箱子需要从上次的位置，到准备移动的位置推箱子移动到nx，ny
    // 其中tx和ty是当前箱子的位置，这个是不可以越过的
    // v数组是标记数组，防止多次查找
    public boolean dfs(char[][] grid, int nx, int ny, int x, int y, int tx, int ty, int[][] v) {
        if (x == nx && y == ny) return true;
        if (check(grid, x, y) && v[x][y] == 0 && !(x == tx && y == ty)) {
            v[x][y] = 1;
            return dfs(grid, nx, ny, x + 1, y, tx, ty, v) ||
                    dfs(grid, nx, ny, x - 1, y, tx, ty, v) ||
                    dfs(grid, nx, ny, x, y + 1, tx, ty, v) ||
                    dfs(grid, nx, ny, x, y - 1, tx, ty, v);
        }
        return false;
    }


    public int minPushBox(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 12维 对应当前坐标 34维代表上一次的位置，5中0表示没带箱子，1带箱子;
        // 防止循环查找
        int[][][][][] v = new int[m][n][m][n][2];
        // 12是人和箱子的位置，3是步数，4标志是否带箱子，56上一次移动的方向
        // 4 0 - > 12是人的位置，1 - > 12是箱子的位置
        // new int[]{i, j, 0, 0, i, j});
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    queue.add(new int[]{i, j, 0, 0, i, j});
                    v[i][j][i][j][0] = 1;
                    break;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 上一次箱子的位置
            int x = cur[0];
            int y = cur[1];
            int step = cur[2];
            int b = cur[3];
            // 上一次人的位置
            int lx = cur[4];
            int ly = cur[5];
            if (grid[x][y] == 'T' && b == 1)
                return step;
            for (int[] d : dicts) {
                // 箱子新的位置
                int nx = x + d[0];
                int ny = y + d[1];
                // 这一次人准备推箱子的位置
                int px = x - d[0];
                int py = y - d[1];
                if (b == 1) {
                    int[][] tv = new int[m][n];
                    if (check(grid, nx, ny) && check(grid, px, py) && dfs(grid, px, py, lx, ly, x, y, tv) && v[nx][ny][x][y][1] == 0) {
                        v[nx][ny][x][y][1] = 1;
                        queue.add(new int[]{nx, ny, step + 1, b, x, y});
                    }
                } else {
                    if (check(grid, nx, ny) && v[nx][ny][x][y][1] == 0) {
                        if (grid[nx][ny] == 'B') {
                            v[nx][ny][x][y][1] = 1;
                            queue.add(new int[]{nx, ny, step, 1, x, y});
                        } else {
                            v[nx][ny][x][y][0] = 1;
                            queue.add(new int[]{nx, ny, step, 0, x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[][] c = new char[][]{
                {'#', '.', '.', '#', '#', '#', '#', '#'},
                {'#', '.', '.', 'T', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '#', 'B', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '#', '.', 'S', '#'},
                {'#', '.', '.', '#', '#', '#', '#', '#'}
        };
        char[][] cc = new char[][]{
                {'#', '.', '.', '#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', '#', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '#', '.', '#', 'B', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#', '.', 'S', '#'},
                {'#', '.', '.', '#', '.', '#', '#', '#', '#'}};
        char[][] ccc = new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '#', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}
        };
        PushBox p = new PushBox();
        System.out.println(p.minPushBox(ccc));
    }

}
