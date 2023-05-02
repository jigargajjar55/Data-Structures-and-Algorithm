package Trie;

class TrieNode {
    char data;
    TrieNode children[];
    boolean isTerminal;

    TrieNode(char data) {
        this.data = data;
        children = new TrieNode[26];
        isTerminal = false;
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

        // assumption, word will be in CAPS
        char ch = word.charAt(index[0]++);
        int i = (int) (ch - 'A');
        // System.out.println(ch);
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

    boolean searchUtil(TrieNode root, String word, int index[]) {

        // base condition
        if (index[0] >= word.length()) {

            return root.isTerminal;
        }

        char ch = word.charAt(index[0]++);

        int i = (int) (ch - 'A');
        TrieNode child;
        // System.out.println(ch);
        // present
        if (root.children[i] != null) {
            child = root.children[i];
        } else {
            // present
            return false;
        }

        return searchUtil(child, word, index);

    }

    // T.C. : O(L), S.C: O(L) , L:- Length of words
    boolean searchWord(String word) {
        int index[] = new int[1];
        return searchUtil(root, word, index);
    }

    void removeUtil(TrieNode root, String word, int index[]) {

        // Base case
        if (index[0] >= word.length()) {
            root.isTerminal = false;
            return;
        }

        char ch = word.charAt(index[0]++);
        int i = (int) (ch - 'A');

        TrieNode child;

        if (root.children[i] != null) {
            child = root.children[i];

        } else {
            System.out.println("No word exist");
            return;
        }

        // recursion
        removeUtil(child, word, index);

    }

    // T.C. : O(L), S.C: O(L) , L:- Length of words
    void removeWord(String word) {
        int index[] = new int[1];
        removeUtil(root, word, index);
    }

}

public class LearnTrie {

    public static void main(String[] args) {
        Trie t = new Trie();

        t.insertWord("ARM");
        t.insertWord("DO");
        t.insertWord("TIME");
        t.insertWord("JIGAR");

        System.out.println("Word is present or not: " + t.searchWord("TIME"));

        t.removeWord("ARM");

        System.out.println("Word is present or not: " + t.searchWord("ARM"));

    }

}
