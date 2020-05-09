package rui.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {

    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        neighbors = _children;
    }


    public static void main(String[] args) {

    }
};