package fire;

import android.app.Activity;
import android.app.Application;
import android.app.IntentService;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.util.ErrorDialogManager;

import java.io.UnsupportedEncodingException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import leakcanary.LeakCanary;
import rui.todd.R;

public class Fire {

    int a = 0;

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        //最长上升子序列
        int[] stack = new int[obstacles.length];//恒定的
        int top = -1;
        int[] res = new int[obstacles.length];
        for (int i = 0; i < obstacles.length; i++) {
            if (top == -1 || obstacles[i] >= stack[top]) {
                stack[++top] = obstacles[i];
                res[i] = top + 1;
            } else {
                //二分
                int l = 0, r = top, m;
                while (l < r) {
                    m = l + (r - l) / 2;
                    if (stack[m] <= obstacles[i]) {
                        l = m + 1;
                    } else {
                        r = m;
                    }
                }
                stack[r] = obstacles[i];
                res[i] = r + 1;
            }
        }
        return res;
    }

    public int minNonZeroProduct(int p) {
        if (p == 1) return 1;
        long mod = (long) 1e9 + 7;
        long x = (1L << p) - 1;
        long ans = x % mod;
        long base = x - 1;
        x >>= 1;
        while (x != 0) {
            if ((x & 1) == 1) ans = (ans % mod * base % mod) % mod;
            base = (base % mod * base % mod) % mod;
            x >>= 1;
        }
        ans %= mod;
        return (int) ans;
    }

    public static void main(String[] args) {
    }

}

class Solution {
    int[] p;

    int find(int x) {
        while (x != p[x]) {
            p[x] = p[p[x]];
            x = p[x];
        }
        return x;
    }

    void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) p[fy] = fx;
    }

    boolean isConnect(int x, int y) {
        return find(x) == find(y);
    }

    public int latestDayToCross(int m, int n, int[][] cells) {
        int[][] h = new int[m + 1][n + 1];
        int tot = m * n + 2;
        p = new int[tot];
        for (int i = 0; i < tot; i++) p[i] = i;
        int l = cells.length;
        for (int i = 1; i <= n; i++) {
            p[i] = 0;
            p[(m - 1) * n + i] = tot - 1;
        }
        int day = 0;
        for (int i = l - 1; i >= 0; i--) {
            int x = cells[i][0];
            int y = cells[i][1];
            h[x][y] = 1;
            if (x - 1 >= 1 && h[x - 1][y] == 1) union((x - 1) * y, x * y);
            if (y - 1 >= 1 && h[x][y - 1] == 1) union(x * (y - 1), x * y);
            if (x + 1 <= m && h[x + 1][y] == 1) union((x + 1) * y, x * y);
            if (y + 1 <= n && h[x][y + 1] == 1) union(x * (y + 1), x * y);
            if (isConnect(0, tot - 1)) {
                System.out.println(Arrays.toString(p));
                day = i;
                break;
            }
        }
        return day;
    }

    public int lengthOfLIS(int[] nums) {
        int m = nums.length;
        int[] tails = new int[m];
        int res = 0;
        for (int num : nums) {
            int i = 0;
            int j = res;
            while (i < j) {
                int mid = (i + j) >>> 1;
                if (tails[mid] < num) i = mid + 1;
                else j = mid;
            }
            tails[j] = num;
            if (j == res) res++;
        }
        return res;
    }

    public static synchronized void a() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaaaaaa");
    }

    synchronized void b() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("bbbbbbbb");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (a.length() < b.length()) return -1;
                if (a.length() > b.length()) return 1;
                return a.compareTo(b);
            }
        });
        for (String s : nums) {
            queue.add(s);
            if (queue.size() > k) queue.poll();
        }
        System.out.println(queue);
        return queue.poll();
    }

    public int mincostTickets(int[] days, int[] c) {
        int n = days.length;
        int[] dp = new int[366];
        int idx = 0;
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for (int i = 1; i < 366; i++) {
            if (idx == n || days[idx] > i) {
                dp[i] = dp[i - 1];
            } else {
                idx++;
                if (i < 7) {
                    dp[i] = Math.min(Math.min(dp[i - 1] + c[0], c[1]), c[2]);
                } else if (i < 30) {
                    dp[i] = Math.min(Math.min(dp[i - 1] + c[0], dp[i - 7] + c[1]), c[2]);
                } else {
                    dp[i] = Math.min(Math.min(dp[i - 1] + c[0], dp[i - 7] + c[1]), dp[i - 30] + c[2]);
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        return dp[365];
    }

    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream()
                .reduce(0, (x, t) -> (t & 1) == 1 ? x + t : x, (x, y) -> x + y);
    }
}

class LRUCache {

    public static void main(String[] args) {
        LRUCache l = new LRUCache(2);
        l.put(1, 0);
        l.put(2, 2);

        System.out.println(l.get(1));
        l.put(3, 3);
        System.out.println(l.get(2));
        l.put(4, 4);
        System.out.println(l.get(1));
        System.out.println(l.get(3));
        System.out.println(l.get(4));
    }

    Map<Integer, Node> map = new HashMap<>();
    int max;
    Node head;
    Node tail;
    int cnt;

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public int key;

        public Node(int key, int val, Node p, Node n) {
            this.key = key;
            this.val = val;
            prev = p;
            next = n;
        }
    }

    void addFirst(Node n) {
        n.next = head.next;
        head.next.prev = n;

        head.next = n;
        n.prev = head;
    }

    Node remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;

        n.next = null;
        n.prev = null;
        return n;
    }

    Node removeLast() {
        return remove(tail.prev);
    }

    public LRUCache(int capacity) {
        max = capacity;
        head = new Node(-1, 0, null, null);
        tail = new Node(-1, 0, null, null);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.get(key) == null) return -1;
        Node cur = map.get(key);
        remove(cur);
        addFirst(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if (map.get(key) != null) {
            Node cur = map.get(key);
            cur.val = value;
            get(key);
        } else {
            cnt++;
            if (cnt > max) {
                map.remove(removeLast().key);
                cnt = max;
            }
            Node nNode = new Node(key, value, null, null);
            map.put(key, nNode);
            addFirst(nNode);
        }
    }
}

class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().minWindow("ADOBECODEBANC", "ABC"));
    }

    Map<Character, Integer> tm = new HashMap<>();
    Map<Character, Integer> sm = new HashMap<>();
    int m, n;
    char[] sc, tc;
    String ans = "";

    boolean check() {
        for (int i = 0; i < 128; i++) {
            char tmp = (char) i;
            if (tm.get(tmp) == null) continue;
            if (sm.get(tmp) == null || sm.get(tmp) < tm.get(tmp)) return false;
        }
        return true;
    }

    public String minWindow(String s, String t) {
        sc = s.toCharArray();
        tc = t.toCharArray();
        m = sc.length;
        n = tc.length;
        int l = 0, r = 0;

        for (int i = 0; i < n; i++) {
            tm.put(tc[i], tm.getOrDefault(tc[i], 0) + 1);
        }

        while (r < m && l < m) {
            if (check()) {
                if (ans.equals("") || ans.length() > s.substring(l, r).length()) {
                    ans = s.substring(l, r);
                }
                int tmp = sm.get(sc[l]);
                if (tmp == 0) {
                    sm.remove(sc[l]);
                } else {
                    sm.put(sc[l], tmp - 1);
                }
                l++;
            } else {
                sm.put(sc[r], sm.getOrDefault(sc[r], 0) + 1);
                r++;
            }
        }
        while (l < m) {
            if (check()) {
                if (ans.equals("") || ans.length() > s.substring(l, r).length()) {
                    ans = s.substring(l, r);
                }
                int tmp = sm.get(sc[l]);
                if (tmp == 0) {
                    sm.remove(sc[l]);
                } else {
                    sm.put(sc[l], tmp - 1);
                }
                l++;
            } else break;
        }
        return ans;
    }
}


class MyLinkedList {

    class Node {
        public Node prev;
        public Node next;
        public int val;

        public Node(int val) {
            this.val = val;
        }

    }

    Node head;
    Node tail;

    int len = 0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }


    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int idx) {
        if (idx >= len) return -1;
        int i = 0;
        Node tmp = head;
        while (i < idx) {
            tmp = tmp.next;
            i++;
        }
        return tmp.next.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {

        Node node = new Node(val);
        head.next.prev = node;
        node.next = head.next;

        node.prev = head;
        head.next = node;
        len++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {

        Node node = new Node(val);
        tail.prev.next = node;
        node.prev = tail.prev;

        node.next = tail;
        tail.prev = node;
        len++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int idx, int val) {
        if (idx >= len) return;
        if (idx < 0) {
            addAtHead(val);
            return;
        }
        int i = 0;
        Node node = new Node(val);
        Node tmp = head;
        while (i < idx) {
            tmp = tmp.next;
            i++;
        }
        tmp.next.prev = node;
        node.next = tmp.next;

        node.prev = tmp;
        tmp.next = node;
        len++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int idx) {
        if (idx >= len) return;
        int i = 0;
        Node tmp = head;
        while (i < idx) {
            tmp = tmp.next;
            i++;
        }

        Node mid = tmp.next;
        tmp.next = mid.next;
        mid.next.prev = tmp;

        mid.next = null;
        mid.prev = null;
        len--;
    }


}

class MyHashSet {

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // 返回 True
        myHashSet.contains(3); // 返回 False ，（未找到）
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // 返回 True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2); // 返回 False ，（已移除）
    }

    int mod = 769;
    LinkedList[] data = new LinkedList[mod];

    int hash(int key) {
        return key % mod;
    }

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        for (int i = 0; i < mod; i++) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int idx = hash(key);
        LinkedList<Integer> list = data[idx];
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int x = it.next();
            if (x == key) return;
        }
        data[idx].add(key);
    }

    public void remove(int key) {
        int idx = hash(key);
        LinkedList<Integer> list = data[idx];
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int x = it.next();
            if (x == key) {
                data[idx].remove(key);
                return;
            }
        }

    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int idx = hash(key);
        LinkedList<Integer> list = data[idx];
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int x = it.next();
            if (x == key) return true;
        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */

