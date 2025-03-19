package Algorithms.PrefixSuffix;

import java.util.*;

public class LongestSubarraywithgivenSumK {

    /*
     * First I will apply bruteforce approach - Time: O(N ^ 2)
     * Try out all possible subarrays and check for longest subarray with given sum K
     * 
     * 
     *  We will approach problem by using prefixSum.
     * 
     * 
     * 
     * 
     */
    
    //Time: O(N * 1){In worst case, HashMap will take O(N), so it will Time : O(N ^ 2)}
    //Space: O(N)
    
    public static int longestSubarrayWithSumK1(int []a, long k) {
       
       int n = a.length;
       long sum = 0;
       int maxLen = 0;
       Map<Long, Integer> prefixSum = new HashMap<>();
       prefixSum.put(0, -1);

       for(int index=0; index<n; index++){
           sum += a[index];
           long remainingSum = sum - k;

           if(prefixSum.containsKey(remainingSum)){
               int len = index - prefixSum.get(remainingSum);

               maxLen = Math.max(maxLen, len);
           }

           if(!prefixSum.containsKey(sum)){
               prefixSum.put(sum, index);
           }
       }

       return maxLen;
    }

    //Sliding Window pattern
    //Time: O(2 * N)
    //Space: O(1)
    public static int longestSubarrayWithSumK(int []a, long k) {

        int n = a.length;
        long sum = 0;
        int maxLen = 0;
       
        int left = 0;
        
        for(int right = 0; right<n; right++){

            sum += a[right];

            while(sum > k){
                sum -= a[left];
                left++;
            }

            if(sum == k){
                maxLen = Math.max(maxLen, right - left + 1);
            }            
        }

        return maxLen;
    }

}
