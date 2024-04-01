package Patterns.SlidingWindow;
import java.util.*;

public class SubarrayswithKDifferentIntegers_LC992 {
    
    // https://www.youtube.com/watch?v=7wYGbV_LsX4
    //Optimised Approach
    //Time: O(2 * N)
    //Space: O(N)
    public int subarraysWithKDistinct(int[] nums, int k) {

        int n = nums.length;

        //exactly(K) = atMost(K) - atMost(K-1) 
        //Instead of finding no. of subarrays having exactly K distinct integers
        //We will get no. Of subarrays with [<=k] distinct integers
        //And no. of subarrays with [<=(k-1)] distinct integers
        
        int atMostKDiff = 0;
        int atMostK_OneDiff = 0;

        Map<Integer, Integer> freq1 = new HashMap<>();
        Map<Integer, Integer> freq2 = new HashMap<>();

        int left = 0;        
        for(int right=0; right<n; right++){
            int curr = nums[right];

            freq1.put(curr, freq1.getOrDefault(curr, 0) + 1);

            while(freq1.size() > k){

                int leftNum = nums[left];
                int leftNumCount = freq1.get(leftNum);

                if(leftNumCount == 1){
                    freq1.remove(leftNum);
                }else{
                    freq1.put(leftNum, leftNumCount-1);
                }
                left++;
            }

            atMostKDiff += (right - left + 1);
                     
        }

       
        left = 0;        
        for(int right=0; right<n; right++){
            int curr = nums[right];

            freq2.put(curr, freq2.getOrDefault(curr, 0) + 1);

            while(freq2.size() > k-1){

                int leftNum = nums[left];
                int leftNumCount = freq2.get(leftNum);

                if(leftNumCount == 1){
                    freq2.remove(leftNum);
                }else{
                    freq2.put(leftNum, leftNumCount - 1);
                }
                left++;
            }

            
            atMostK_OneDiff += (right - left + 1);
                      
        }

        //System.out.println(lessThanKCount +" : "+moreThanKCount);

        int result = atMostKDiff - atMostK_OneDiff;
        return result;
        
    }



    //Bruteforce Approach
    //Time: O((N ^ 2) * log N)
    //Space: O(N)
    public int subarraysWithKDistinct1(int[] nums, int k) {

        int n = nums.length;
        int count = 0;

        for(int i=0; i<n; i++){
            Set<Integer> uniqueHash = new HashSet<>();

            for(int j=i; j<n; j++){

                int curr = nums[j];

                if(!uniqueHash.contains(curr)){
                    uniqueHash.add(curr);
                }

                if(uniqueHash.size() == k){
                    count++;
                }
                else if(uniqueHash.size() > k){
                    break;
                }

            }
        }
        return count;
        
    }
}
