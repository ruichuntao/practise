package rui.google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionAC {

    static List<PackageNode> sonList = new ArrayList<>();// 求当前点的所有依赖它的点的list
    static Set<PackageNode> set = new HashSet<>(); // 去重

    static class PackageNode {
        String name = "";
        // 依赖的集合
        List<PackageNode> dependList = new ArrayList<>();
        // 被依赖的集合
        List<PackageNode> beDependList = new ArrayList<>();

        public PackageNode(String name) {
            this.name = name;
        }
    }

    // 求被依赖集合
    static void dfs(PackageNode n) {
        if (set.contains(n)) return;
        set.add(n);
        for (PackageNode packageNode : n.dependList) {
            packageNode.beDependList.add(n);
            dfs(packageNode);
        }
    }

    // 找被依赖的所有点
    static void fun(PackageNode n){
        if (set.contains(n)) return;
        set.add(n);
        sonList.add(n);
        for (PackageNode packageNode : n.beDependList) {
            fun(packageNode);
        }
    }

    public static void main(String[] args) {
        PackageNode a = new PackageNode("a");
        PackageNode b = new PackageNode("b");
        PackageNode c = new PackageNode("c");
        PackageNode d = new PackageNode("d");
        PackageNode e = new PackageNode("e");
        PackageNode f = new PackageNode("f");
        a.dependList.add(b);
        a.dependList.add(c);
        a.dependList.add(d);
        b.dependList.add(c);
        c.dependList.add(e);
        d.dependList.add(c);
        e.dependList.add(f);
        // 初始化
        set.clear();
        sonList.clear();
        dfs(a);
        // 去重复用
        set.clear();
        fun(f);
        for (PackageNode packageNode : sonList) {
            System.out.println(packageNode.name);
        }
    }
}