package Algorithms.SlidingWindow;
public class MinimumOperationsToReduceXToZero_LC1658 {
    

    //Time: O(2 * N)
    //Space: O(1)
    public int minOperations(int[] nums, int x) {

        int n = nums.length;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        int maxSubArray = Integer.MIN_VALUE;

        int currSum = 0;

        for(int left=0, right=0; right<n; right++){
            currSum += nums[right];

            while(left <= right && currSum > sum - x){
                currSum -= nums[left];
                left++;
            }

            if(currSum == sum - x){
                maxSubArray = Math.max(maxSubArray, right - left + 1);
            }
        }

        if(maxSubArray == Integer.MIN_VALUE){
            return -1;
        }else{
            return (n - maxSubArray);
        } 
        
    }
}