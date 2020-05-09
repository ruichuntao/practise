package rui.leetcode;

public class Trie {

    boolean isEnd = false;
    Trie[] next = new Trie[26];

    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie root = this;
        char[] c = word.toCharArray();
        for (char ch : c) {
            if (root.next[ch - 'a'] == null) root.next[ch - 'a'] = new Trie();
            root = root.next[ch - 'a'];
        }
        root.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie root = this;
        char[] c = word.toCharArray();
        for (char ch : c) {
            if (root.next[ch - 'a'] == null) return false;
            root = root.next[ch - 'a'];
        }
        return root.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie root = this;
        char[] c = prefix.toCharArray();
        for (char ch : c) {
            if (root.next[ch - 'a'] == null) return false;
            root = root.next[ch- 'a'];
        }
        return true;
    }
}
