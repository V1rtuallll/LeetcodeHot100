package 板1;

public class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        char[] w = word.toCharArray();
        for (int i = 0; i < w.length; i++) {
            TrieNode node = cur.children[w[i] - 'a'];
            if (node == null) {
                node = new TrieNode();
                cur.children[w[i] - 'a'] = node;
            }
            cur = node;
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        char[] w = word.toCharArray();
        for (char c : w) {
            TrieNode node = cur.children[c - 'a'];
            if (node == null) {
                return false;
            } else {
                cur = node;
            }
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        char[] w = prefix.toCharArray();
        for (char c : w) {
            TrieNode node = cur.children[c - 'a'];
            if (node == null) {
                return false;
            } else {
                cur = node;
            }
        }
        return true;
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.isEnd = false;
    }
}