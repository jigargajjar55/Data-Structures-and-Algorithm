package Trie;

import java.util.*;
import java.io.*;

class TrieNode1 {
    char data;
    TrieNode1 children[];
    boolean isTerminal;

    TrieNode1(char data) {
        this.data = data;
        children = new TrieNode1[26];
        isTerminal = false;
    }
}

class Trie1 {
    TrieNode1 root;

    public Trie1() {
        root = new TrieNode1('#');
    }

    private void insertUtil(TrieNode1 node, String word, int[] index) {
        // Base condition
        if (index[0] >= word.length()) {
            node.isTerminal = true;
            return;
        }

        char ch = word.charAt(index[0]++);
        int i = (int) (ch - 'a');
        TrieNode1 child;
        if (node.children[i] != null) {
            child = node.children[i];
        } else {
            child = new TrieNode1(ch);
            node.children[i] = child;
        }

        insertUtil(child, word, index);
    }

    public void insert(String word) {
        int[] index = new int[1];
        insertUtil(root, word, index);
    }

    private boolean startsWithUtil(TrieNode1 node, String word, int index) {
        // Base condition
        if (index >= word.length()) {
            return true;
        }

        char ch = word.charAt(index);
        int i = (int) (ch - 'a');
        TrieNode1 child;
        if (node.children[i] != null) {
            child = node.children[i];
            if (!child.isTerminal) {
                return false;
            }
        } else {
            return false;
        }

        return startsWithUtil(child, word, index + 1);
    }

    public String findCompleteString(int n, String[] a) {
        String longest = "";
        for (int i = 0; i < a.length; i++) {
            if (startsWithUtil(root, a[i], 0)) {
                if (a[i].length() > longest.length()) {
                    longest = a[i];
                } else if (a[i].length() == longest.length() && a[i].compareTo(longest) < 0) {
                    longest = a[i];

                }
            }
        }

        return longest.equals("") ? "None" : longest;
    }
}

public class LongestCompleteString {

    /*
     * Problem Statement
     * 
     * Ninja developed a love for arrays and strings so this time his teacher gave
     * him an array of strings,
     * ‘A’ of size ‘N’. Each element of this array is a string. The teacher taught
     * Ninja about prefixes in the past,
     * so he wants to test his knowledge.
     * A string is called a complete string if every prefix of this string is also
     * present in the array ‘A’.
     * Ninja is challenged to find the longest complete string in the array ‘A’.If
     * there are multiple strings with the same length,
     * return the lexicographically smallest one and if no string exists, return
     * "None".
     * Note :
     * String ‘P’ is lexicographically smaller than string ‘Q’, if :
     * 
     * 1. There exists some index ‘i’ such that for all ‘j’ < ‘i’ , ‘P[j] = Q[j]’
     * and ‘P[i] < Q[i]’. E.g. “ninja” < “noder”.
     * 
     * 2. If ‘P’ is a prefix of string ‘Q’, e.g. “code” < “coder”.
     * Example :
     * N = 4
     * A = [ “ab” , “abc” , “a” , “bp” ]
     * 
     * Explanation :
     * 
     * Only prefix of the string “a” is “a” which is present in array ‘A’. So, it is
     * one of the possible strings.
     * Prefixes of the string “ab” are “a” and “ab” both of which are present in
     * array ‘A’. So, it is one of the possible strings.
     * Prefixes of the string “bp” are “b” and “bp”. “b” is not present in array
     * ‘A’. So, it cannot be a valid string.
     * Prefixes of the string “abc” are “a”,“ab” and “abc” all of which are present
     * in array ‘A’. So, it is one of the possible strings.
     * We need to find the maximum length string, so “abc” is the required string.
     * 
     * Constraints :
     * 1 <= T <= 10
     * 1 <= N <= 10^5
     * 1 <= A[i].length <= 10^5
     * A[i] only consists of lower case English letters.
     * Sum of A[i].length <= 10^5 over all test cases
     * 
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int n = 6;
        String[] a = { "n", "ni", "nin", "ninj", "ninja", "ninga", "ninjas" };

        Trie1 t = new Trie1();

        for (String str : a) {
            t.insert(str);
        }

        String result = t.findCompleteString(n, a);
        System.out.println(result);

    }

}
