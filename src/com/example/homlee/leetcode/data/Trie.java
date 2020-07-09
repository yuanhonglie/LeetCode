package com.example.homlee.leetcode.data;

public class Trie {
    public Trie[] next = new Trie[26];
    public boolean isEnd;


    public void insert(String s) {
        Trie node = this;
        for (int i = s.length() - 1; i >= 0; i--) {
            int index = s.charAt(i) - 'a';
            if (node.next[index] == null) {
                node.next[index] = new Trie();
            }
            node = node.next[index];
        }
        node.isEnd = true;
    }
}
