package Trie;
import java.util.*;

public class ExtraCharactersInAString_LC2707 {
    

    private class TrieNode{
        char data;
        TrieNode[] children;
        boolean isTerminal;
        TrieNode(char data){
            this.data = data;
            children = new TrieNode[26];
            this.isTerminal = false;
        }
    }

    private class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode('#');
        }
        private void insertWord(String word){
            TrieNode node = root;
            int n = word.length();
            for(int i=0; i<n; i++){
                char currCh = word.charAt(i);
                int index = (int)(currCh - 'a');

                if(node.children[index] == null){
                    node.children[index] = new TrieNode(currCh);
                }

                node = node.children[index];
            }
            node.isTerminal = true;
        }

        private int solveByTopDownDPOnTrie(int i,int n,String word,int[] dp){

            //Base Case
            if(i == n){
                return 0;
            }

            //Overlapping sub-problem
            if(dp[i] != -1){
                return dp[i];
            }

            //Exclude character
            //Consider current character as extra in string
            int minAns = 1 + solveByTopDownDPOnTrie(i+1,n,word,dp);
            TrieNode node = root;

            int j = i;
            while(j < n){
                int index = (int)(word.charAt(j) - 'a');
                node = node.children[index];

                //If character is not present in trie
                if(node == null){
                    break;
                } 

                //If current char is last char in word or substring
                if(node.isTerminal){
                    minAns = Math.min(minAns, solveByTopDownDPOnTrie(j+1,n,word,dp));
                }

                j++;
            }

            return dp[i] = minAns;      
        }
    }

    //Time: O((M * L) + (N ^ 2)), Space: O((M * L) + N)
    //{M: no. Of string in dict,L: Avg length of string, N: Length of string s}
    public int minExtraChar(String s, String[] dictionary) {
        Trie t = new Trie();
        int m = dictionary.length;

        for(int i=0; i<m; i++){
            String word = dictionary[i];
            t.insertWord(word);
        }
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        int ans = t.solveByTopDownDPOnTrie(0,n,s,dp);

        return ans;       
    }
}