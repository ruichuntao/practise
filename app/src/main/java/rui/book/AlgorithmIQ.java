package rui.book;

import java.util.HashSet;
import java.util.Set;

public class AlgorithmIQ {

    int four(int n, int m) {
        int count = 0, current = 1;
        while (n > current) {
            current += Math.min(current, m);
            count++;
        }
        return count;
    }

    static int eightHelper(Set<String> set, int x, int y, int m) {
        if (set.contains(x + "-" + y)) return 0;
        if (m == 0) return 1;
        int sum = 0;
        set.add(x + "-" + y);
        sum += eightHelper(set, x + 1, y, m - 1);
        sum += eightHelper(set, x - 1, y, m - 1);
        sum += eightHelper(set, x, y + 1, m - 1);
        sum += eightHelper(set, x, y - 1, m - 1);
        set.remove(x + "-" + y);
        return sum;
    }

    static int eight() {
        Set<String> set = new HashSet<>();
        return eightHelper(set, 0, 0, 12);
    }

    public static void main(String[] args) {
        System.out.println(eight());
    }
}
