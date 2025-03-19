package DynamicProgramming.Trie;

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

    class Trie {

        TrieNode root;

        Trie() {
            root = new TrieNode('#');
        }

        void insertUtil(TrieNode root, String word, int index[]) {

            // Base condition
            if (index[0] >= word.length()) {
                // System.out.println(root.data);
                root.isTerminal = true;
                return;
            }

            char ch = word.charAt(index[0]++);
            int i = (int) (ch - 'a');

            TrieNode child;
            // present
            if (root.children[i] != null) {
                child = root.children[i];

            } else {
                // absent
                child = new TrieNode(ch);
                root.children[i] = child;
            }

            // recursion
            insertUtil(child, word, index);

        }

        // T.C. : O(L), S.C: O(L) , L:- Length of words
        void insertWord(String word) {
            int index[] = new int[1];
            insertUtil(root, word, index);

        }

        boolean isPrefixExist(TrieNode curr, String str, int start, int end) {

            for (int i = start; i <= end; i++) {
                int index = str.charAt(i) - 'a';

                if (curr.children[index] == null) {
                    return false;
                }
                curr = curr.children[index];
            }

            return curr.isTerminal;

        }

        boolean isWordBreak(int start, int end, String str, Integer[][] dp) {

            int size = end - start + 1;

            if (size == 0) {
                return true;
            }

            // Overlapping sub-problem
            if (dp[start][end] != null) {
                return (dp[start][end] == 1);
            }

            boolean isFound = false;

            for (int i = start; i <= end; i++) {

                boolean searchPrefix = isPrefixExist(root, str, start, i);

                if (searchPrefix && isWordBreak(i + 1, end, str, dp)) {
                    isFound = true;
                    break;
                }

            }

            dp[start][end] = isFound ? 1 : 0;

            return isFound;

        }

    }

    // Time: O((N * I) + (L ^ 2)),
    // Space: O(L ^ 2)
    // {N: no. Of Strings in list, I: Average length of String, L: Length of string
    // that need to check for word break}
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie t = new Trie();
        for (String str : wordDict) {
            t.insertWord(str);
        }

        int l = s.length();
        Integer[][] dp = new Integer[l][l];

        return t.isWordBreak(0, l - 1, s, dp);
    }
}