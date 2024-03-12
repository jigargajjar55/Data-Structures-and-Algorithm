package DynamicProgramming.LIS;
import java.util.*;

public class LongestStringChain {

    //Time: O(Min(n,m))
    private boolean isPossible(String s1, String s2){

        int n = s1.length();
        int m = s2.length();

        //If second string is not long exactly one letter
        if(n + 1 != m){
            return false;
        }

        int ptr1 = 0;
        int ptr2 = 0;

        while(ptr1 < n && ptr2 < m){
            if(s1.charAt(ptr1) == s2.charAt(ptr2)){
                ptr1++;
            }
            ptr2++;
        }

        if(ptr1 == n){
            return true;
        }else{
            return false;
        }

    }
    
    // Time : O((N * logN) +  ((N ^ 2) * (Min(Length of selected 2 strings))))
    // Space: O(N)
    public int longestStrChain(String[] words) {

        int n = words.length;
        //Sort words array based on word length
        Arrays.sort(words, (w1,w2) -> Integer.compare(w1.length(), w2.length()));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxChain = 0;

        for(int curr = 0; curr < n; curr++){

            for(int prev = 0; prev < curr; prev++){

                if(isPossible(words[prev], words[curr]) && (dp[curr] < 1 + dp[prev])){
                    dp[curr] = 1 + dp[prev];
                }
            }

            if(maxChain < dp[curr]){
                maxChain = dp[curr];
            }
        }

        return maxChain;
        
    }




}
