package Patterns.SlidingWindow;
import java.util.*;

public class LengthofLongestSubarrayWithatMostKFrequency_LC2958 {

    
    public int maxSubarrayLength(int[] nums, int k) {

        //Optimize approach
        //Time: O(2 * N)
        //Space: O(N)

        int n = nums.length;
        int len = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;

        for(int right=0; right<n; right++){

            int key = nums[right];
            int value = freq.getOrDefault(key, 0) + 1;

            if(value > k){

                while(nums[left] != key){

                    int currKey = nums[left];
                    int currValue = freq.get(currKey) - 1; 

                    if(currValue == 0){
                        freq.remove(currKey);
                    }else{
                        freq.put(currKey, currValue);
                    }                   

                    left++;
                }
                left++;
            }else{
                freq.put(key, value);
            }

            len = Math.max(len, (right - left + 1));

        }

        return len;








        /*


        //Bruteforce Approach
        //Time: O(N ^ 2)
        //Space: O(N ^ 2)
        //Try out all possible subarrays
        int len = 0;
        int n = nums.length;

        for(int i=0; i<n; i++){

            Map<Integer, Integer> freq = new HashMap<>();

            for(int j=i; j<n; j++){

                int key = nums[j];
                int value = freq.getOrDefault(key, 0) + 1;

                if(value > k){
                    break;
                }

                len = Math.max(len, (j - i + 1));
                freq.put(key, value);
            }
        }

        return len;

        */
        
    }
}