package Strings;
import java.util.*;

public class MinimumDeletionsToMakeCharacterFrequenciesUnique_LC1647 {
    

    //Time: O(N + (26 * Avg. frequency of characters)), Space: O(26)
    public int minDeletions(String s) {

        int n = s.length();
        int[] charFreq = new int[26];

        for(int i=0; i<n; i++){
            int index = (int)(s.charAt(i) - 'a');
            charFreq[index]++;
        }
        Set<Integer> set = new HashSet<>();
        int minDeletion = 0;

        for(int i=0; i<26; i++){
            int freq = charFreq[i];

            while(freq > 0){
                if(set.contains(freq)){
                    minDeletion++;
                }else{
                    set.add(freq);
                    break;
                }                
                freq--;
            }            
        }

        return minDeletion;

        
    }
}