package fire;


import android.os.Message;

import androidx.collection.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Micro {


    public Micro() {
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode dfs(TreeNode root, int t1, int t2) {
        if (root == null || t1 == root.val || t2 == root.val) return root;
        TreeNode left = dfs(root.left, t1, t2);
        TreeNode right = dfs(root.right, t1, t2);
        if (left != null && right != null) return root;
        if (left != null) return left;
        return right;
    }

//    static class Apple extends Fruit {
//
//    }
//
//    static class Jonathan extends Apple {
//
//    }
//
//    static class Fruit {
//
//    }
//
//    static void writeTo() {
//        List<? super Apple> apples = new ArrayList<>();
//        apples.add(new Apple());
//        apples.add(new Jonathan());
//
//        List<? extends Apple> a = (List<? extends Apple>) apples;
//        for (Apple apple : a) {
//            System.out.println(apple);
//        }
//    }

    public static void main(String[] args) {
//        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 5; i++) {
//            final int index = i;
//
//            cachedThreadPool.execute(new Runnable() {
//
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+", index="+index);
//                }
//            });
//
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//        }
        String s = "2";
        String ss = new String("2");
        System.out.println(s == ss);
        String intern = ss.intern();
        System.out.println(s == intern);
    }
}
