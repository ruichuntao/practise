package rui.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class FindKeys {
    // @ . a . #
    // # # # . #
    // b . A . B
    char[][] c;
    int[][][] v;
    int m;
    int n;

    private int[][] dict = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean isIn(int i, int j) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }

    public int c2i(char c) {
        if (c >= 'a' && c <= 'f') return 1 << (c - 'a');
        return -1;
    }

    public int c2I(char c) {
        if (c >= 'A' && c <= 'F') return 1 << (c - 'A');
        return -1;
    }


    public int shortestPathAllKeys(String[] grid) {
        m = grid.length;
        n = grid[0].length();
        c = new char[m][n];
        for (int i = 0; i < m; i++) {
            c[i] = grid[i].toCharArray();
        }
        Queue<int[]> queue = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == '@') {
                    // x, y坐标, 3步数 4 钥匙数
                    queue.add(new int[]{i, j, 0, 0});
                } else if (c[i][j] >= 'a' && c[i][j] <= 'f') {
                    cnt++;
                }
            }
        }
        int keys = (1 << cnt) - 1;
        v = new int[m][n][1 << cnt];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int step = cur[2];
            int key = cur[3];
//            System.out.println(x + "->" + y);
            if (key == keys) return step;
            for (int[] d : dict) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (isIn(nx, ny) && c[nx][ny] != '#') {
                    if (c[nx][ny] >= 'A' && c[nx][ny] <= 'F') {
                        if ((key & c2I(c[nx][ny])) != 0 && v[nx][ny][key] == 0) {
                            v[nx][ny][key] = 1;
                            queue.add(new int[]{nx, ny, step + 1, key});
                        }
                    } else if (c[nx][ny] >= 'a' && c[nx][ny] <= 'f') {
                        int nk = key | c2i(c[nx][ny]);
                        if (v[nx][ny][nk] == 0) {
                            v[nx][ny][nk] = 1;
                            queue.add(new int[]{nx, ny, step + 1, nk});
                        }
                    } else if (v[nx][ny][key] == 0) {
                        v[nx][ny][key] = 1;
                        queue.add(new int[]{nx, ny, step + 1, key});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // "@ . a . #",
        // "# # # . #",
        // "b . A . B"

        // "@ . . a A",
        // ". . B # .",
        // ". . . . b"

        // "@ . . . a",
        // ". # # # A",
        // "b . B C c"

        // "@ f e d c b B C D E F a A"

        String[] s = new String[]{"@.a.#", "###.#", "b.A.B"};
        String[] ss = new String[]{"@..aA", "..B#.", "....b"};
        String[] sss = new String[]{"@...a", ".###A", "b.BCc"};
        String[] ssss = new String[]{"@fedcbBCDEFaA"};
        FindKeys f = new FindKeys();
        System.out.println(f.shortestPathAllKeys(ssss));
        System.out.println(f.shortestPathAllKeys(sss));
        System.out.println(f.shortestPathAllKeys(ss));
        System.out.println(f.shortestPathAllKeys(s));
    }

}
