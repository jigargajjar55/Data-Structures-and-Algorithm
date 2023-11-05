package Trie;

import java.util.*;

public class WordBreak_LC139 {

    private class TrieNode {
        char data;
        TrieNode[] children;
        boolean isTerminal;

        TrieNode(char data) {
            this.data = data;
            this.children = new TrieNode[26];
            this.isTerminal = false;
        }
    }

    // Time: O((N * I) + (L ^ 2)), Space: O(L * 26)
    // {N: no. Of Strings in list, I: Average length of String, L: Length of string
    // that need to check for word break}
    class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode('#');
        }

        private void insertWord(String word) {
            int n = word.length();
            TrieNode node = root;
            for (int i = 0; i < n; i++) {
                int index = (int) (word.charAt(i) - 'a');
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode(word.charAt(i));
                }
                node = node.children[index];
            }

            node.isTerminal = true;
        }

        private boolean isWordBreak(String word) {
            int size = word.length();
            boolean[] wordBreak = new boolean[size + 1];
            // For Initially, We will mark first character is true
            wordBreak[0] = true;

            for (int i = 0; i < size; i++) {
                // If it is not word break, we will continue to search character which can be
                // break at point.
                if (!wordBreak[i]) {
                    continue;
                }

                int j = i;
                TrieNode node = root;
                while (j < size && node.children[(int) (word.charAt(j) - 'a')] != null) {
                    node = node.children[(int) (word.charAt(j) - 'a')];
                    j++;

                    if (node.isTerminal) {
                        wordBreak[j] = true;
                    }
                }

            }

            return wordBreak[size];
        }
    }

    public boolean wordBreak(String s, List<String> wordDict) {

        Trie t = new Trie();
        for (String word : wordDict) {
            t.insertWord(word);
        }

        boolean ans = t.isWordBreak(s);

        return ans;
    }
}