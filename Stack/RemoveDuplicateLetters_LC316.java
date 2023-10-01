package Stack;

import java.util.*;



public class RemoveDuplicateLetters_LC316 {
    
    // https://leetcode.com/problems/remove-duplicate-letters/solutions/76762/java-o-n-solution-using-stack-with-detail-explanation/?envType=daily-question&envId=2023-09-26

    //Time: O(2 * N)
    //Space: O(N + (2 * 26))
    public String removeDuplicateLetters(String s) {

        int n = s.length();
        int[] charCountS = new int[26];

        for(int i=0; i<n; i++){
            int index = (int)(s.charAt(i) - 'a');
            charCountS[index]++;
        }
        Stack<Character> st = new Stack<>();
        boolean[] visited = new boolean[26];

        for(int i=0; i<n; i++){
            char currCh = s.charAt(i);
            charCountS[(int)(currCh - 'a')]--;

            if(visited[(int)(currCh - 'a')]){
                continue;
            }

            while(!st.isEmpty() && st.peek() > currCh && charCountS[(int)(st.peek() - 'a')] > 0){
                char ch = st.peek();
                visited[(int)(ch - 'a')] = false;
                st.pop();
            }

            visited[(int)(currCh - 'a')] = true;
            st.push(currCh);
        }

        StringBuilder result = new StringBuilder();
        for(char curr : st){
            result.append(curr);
        }

        return result.toString();

        
    }
}