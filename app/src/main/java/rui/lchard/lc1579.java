package rui.lchard;

public class lc1579 {

    int[] pa;
    int[] pb;

    int find(int x, int[] p) {
        while (p[x] != x) {
            p[x] = p[p[x]];
            x = p[x];
        }
        return p[x];
    }

    boolean union(int x, int y, int[] p) {
        int rx = find(x, p);
        int ry = find(y, p);
        if (rx != ry) {
            p[rx] = ry;
            return true;
        }
        return false;
    }

    boolean connect(int x, int y, int[] p) {
        return find(x, p) == find(y, p);
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int ans = 0;
        pa = new int[n];
        pb = new int[n];
        for (int i = 0; i < n; i++) {
            pa[i] = i;
            pb[i] = i;
        }
        for (int[] edge : edges) {
            edge[1]--;
            edge[2]--;
        }
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!union(edge[1], edge[2], pa)) {
                    ++ans;
                } else {
                    union(edge[1], edge[2], pb);
                }
            }
        }
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (!union(edge[1], edge[2], pa)) {
                    ++ans;
                }
            } else if (edge[0] == 2) {
                if (connect(edge[1], edge[2], pb)) {
                    ++ans;
                    union(edge[1], edge[2], pb);
                }
            }
        }
//        int diffA = 0;
//        int diffB = 0;
//        for (int i = 0; i < n; i++) {
//            if (find(i, pa) == i) diffA++;
//            if (find(i, pb) == i) diffB++;
//            if (diffA > 1 || diffB > 1) return -1;
//        }
        return ans;
    }

}
