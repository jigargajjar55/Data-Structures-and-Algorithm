package GreedyApproach;
import java.util.*;

public class ReorganizeString_LC767 {
  
    // https://leetcode.com/problems/reorganize-string/solutions/262019/simple-java-solution-using-priorityqueue-with-detailed-explanation/
    
    //Time: O(N + (UniqueChars * log(UniqueChars) + (N * log N)))
    //Space: O(N  + 26)

    private class Pair{
        char ch;
        int freq;
        Pair(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }

    }
    public String reorganizeString(String s) {

        int n = s.length();
        StringBuilder result = new StringBuilder();

        //Step1 : Count frequency of char in string 
        int[] charCount = new int[26];
        for(int i=0; i<n; i++){
            int index = (int)(s.charAt(i) - 'a');
            charCount[index]++;
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1,p2) -> Integer.compare(p2.freq, p1.freq));
        //Step2: Add all string's character in PriorityQueue for Greedy Approach
        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        for(int i=0; i<26; i++){
            if(charCount[i] > 0){
                pq.offer(new Pair((char)(i + 'a'), charCount[i]));
            }
        }

        Pair prev = new Pair('#',-1);
        //Step3: We will pick char which has max frequency, then we store it as previous. So it can't be have a same adjacent characters
        
        while(!pq.isEmpty()){
            Pair curr = pq.peek();
            pq.poll();

            result.append(curr.ch);

            if(prev.freq > 0){
                pq.offer(prev);
            }

            (curr.freq)--;
            prev = curr;
        }

        if(result.length() != n){
            return "";
        }else{
            return result.toString();
        }
        
    }
}