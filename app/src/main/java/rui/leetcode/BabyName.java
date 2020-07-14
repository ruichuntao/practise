package rui.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BabyName {

    class Node {
        String name;
        int fre;

        public Node(String name, int fre) {
            this.name = name;
            this.fre = fre;
        }

    }

    class UnionFind {

        private Node[] nodes;
        private int[] p;

        public UnionFind(Node[] nodes, int n) {
            this.nodes = nodes;
            p = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
        }

        public void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                if (nodes[rx].name.compareTo(nodes[ry].name) < 0) {
                    p[ry] = rx;
                } else {
                    p[rx] = ry;
                }
            }
        }

        public int find(int x) {
            while (x != p[x]) {
                p[x] = p[p[x]];
                x = p[x];
            }
            return x;
        }

    }

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        int n = names.length;
        String[] name = new String[n];
        int[] rates = new int[n];
        Node[] nodes = new Node[n];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int l = names[i].indexOf('(');
            int r = names[i].indexOf(')');
            name[i] = names[i].substring(0, l);
            map.put(name[i], i);
            rates[i] = Integer.parseInt(names[i].substring(l + 1, r));
            nodes[i] = new Node(name[i], rates[i]);
        }
        UnionFind uf = new UnionFind(nodes, n);
        for (String s : synonyms) {
            String[] ss = s.split(",");
            String n1 = ss[0].substring(1);
            String n2 = ss[1].substring(0, ss[1].length() - 1);
            if (map.containsKey(n1) && map.containsKey(n2))
                uf.union(map.get(n1), map.get(n2));
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.find(i) != i) {
                nodes[uf.find(i)].fre += nodes[i].fre;
            }
        }
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i) {
                list.add(nodes[i].name + "(" + nodes[i].fre + ")");
            }
        }
        int nn = list.size();
        String[] ans = new String[nn];
        for (int i = 0; i < nn; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] name = new String[]{"John(15)", "Jon(12)", "Chris(13)", "Kris(4)", "Christopher(19)"};
        String[] synonyms = new String[]{"(Jon,John)", "(John,Johnny)", "(Chris,Kris)", "(Chris,Christopher)"};
        BabyName b = new BabyName();
        System.out.println(Arrays.toString(b.trulyMostPopular(name, synonyms)));
    }

}
