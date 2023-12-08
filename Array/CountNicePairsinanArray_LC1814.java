package Array;
import java.util.*;

public class CountNicePairsinanArray_LC1814 {
    
    //Similar like 2 Sum problem
    // https://leetcode.com/problems/count-nice-pairs-in-an-array/solutions/1140639/java-c-python-straight-forward/?envType=daily-question&envId=2023-11-21

    //Time: O(N * log a)
    //Space: O(N)
    private int getReverse(int num){

        int revNum = 0;
        int factor = 10;

        while(num > 0){
            int digit = num % 10;

            revNum = (revNum * factor) + digit;
            num /= 10;            
        }

        return revNum;

    }
    public int countNicePairs(int[] nums) {

        int n = nums.length;
        
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;

        for(int i=0; i<n; i++){

            int num = nums[i];
            int revNum = getReverse(nums[i]);
            int diff = num - revNum;
            int val = freq.getOrDefault(diff, 0);

            count = (count + val) % 1_000_000_007;
           
            freq.put(diff, val + 1);
            
        }       
        

        return count;
    }
}
