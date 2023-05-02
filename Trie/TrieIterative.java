package Trie;

//Iterative Approach for Trie Data Structure
class TrieNode {
    char data;
    TrieNode[] children;
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

    public void insertWord(String word) {

        TrieNode curr = root;
        int n = word.length();

        for (int i = 0; i < n; i++) {
            char currCh = word.charAt(i);
            int index = (int) (currCh - 'A');

            if (curr.children[index] == null) {
                TrieNode newNode = new TrieNode(currCh);
                curr.children[index] = newNode;
            }
            curr = curr.children[index];

        }
        curr.isTerminal = true;
    }

    public boolean searchWord(String word) {

        TrieNode curr = root;
        int n = word.length();

        for (int i = 0; i < n; i++) {
            char currCh = word.charAt(i);
            int index = (int) (currCh - 'A');

            if (curr.children[index] == null) {
                return false;
            }

            curr = curr.children[index];

        }

        return curr.isTerminal;

    }

    public void removeWord(String word) {

        TrieNode curr = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char currCh = word.charAt(i);
            int index = (int) (currCh - 'A');

            if (curr.children[index] == null) {
                System.out.println("Word is not present in Trie!!");
                break;
            }
            curr = curr.children[index];
        }

        curr.isTerminal = false;

    }

}

public class TrieIterative {
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
