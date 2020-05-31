package rui.leetcode;

import java.util.*;
import java.math.*;

public class Main {

    public static int MOD = (int) 1e9 + 7;

    public static int[][] dict = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static boolean check(char[][] c, int i, int j, int key) {
        int m = c.length;
        int n = c[0].length;
        return i >= 0 && i < m && j >= 0 && j < n && (c[i][j] == '1' || c[i][j] == '3' || (c[i][j] >= 'A' && c[i][j] <= 'Z') || (c[i][j] >= 'a' && c[i][j] <= 'z' && (key & getPos(c[i][j])) != 0));
    }

    public static int getPos(char x) {
        if (x >= 'A' && x <= 'Z') {
            return 1 << (x - 'A');
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main t = new Main();
        int m = 0;
//        int m = sc.nextInt();
        int n = 0;
//        int n = sc.nextInt();
        char[][] c = new char[][]{
                {'0', '2', '1', '1', '1'},
                {'0', '1', 'a', '0', 'A'},
                {'0', '1', '0', '0', '3'},
                {'0', '1', '0', '0', '1'},
                {'0', '1', '1', '1', '1'}
        };
//        for (int i = 0; i < m; i++) {
//            c[i] = sc.next().toCharArray();
//        }
        m = c.length;
        n = c[0].length;
//        for (char[] chars : c) {
//            System.out.println(Arrays.toString(chars));
//        }
        // 26 表示当前是否携带了钥匙
        int[][] v = new int[m][n];
        Set<String> set = new HashSet<>();
        List<Character> list = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        int ex = 0, ey = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == '2') {
                    queue.add(new int[]{i, j, 0, 0});
                    set.add(i + "~" + j + "~" + 0);
//                    v[i][j] = 1;
                } else if (c[i][j] >= 'a' && c[i][j] <= 'z') {
                    list.add(c[i][j]);
                } else if (c[i][j] == '3') {
                    ex = i;
                    ey = j;
                }
            }
        }
        int size = list.size();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int step = cur[2];
            int key = cur[3];
            System.out.println(x + " ->" + y + " ->" + step + "->" + Integer.bitCount(key));
            if (x == ex && y == ey) {
                int i = 0;
                char tc = ' ';
                for (; i < size; i++) {
                    tc = list.get(i);
                    if ((key & getPos(tc)) == 0) break;
                }
                if (i == size) {
                    System.out.print(step);
                    return;
                }
            }
            for (int[] d : dict) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (check(c, nx, ny, key)) {
                    int pos = getPos(c[nx][ny]);
                    if (!set.contains(nx + "~" + ny + "~" + key)) {
                        set.add(nx + "~" + ny + "~" + key);
                        queue.add(new int[]{nx, ny, step + 1, key | pos});
                    }
                }
            }
        }
        System.out.print(-1);
    }
}
