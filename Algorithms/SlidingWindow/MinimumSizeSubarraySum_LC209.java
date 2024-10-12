package Algorithms.SlidingWindow;
public class MinimumSizeSubarraySum_LC209 {
    
    //Time: O(2 * N), Space: O(1)
    public int minSubArrayLen(int target, int[] nums) {        
        
        int n = nums.length;

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;

        for(int right=0; right<n; right++){

            sum += nums[right];

            while(sum >= target){
                sum -= nums[left];
                minLen = Math.min(minLen, right - left + 1);
                left++;
            }

        }

        if(minLen == Integer.MAX_VALUE){
            return 0;
        }else{
            return minLen;
        }
        
    }
}