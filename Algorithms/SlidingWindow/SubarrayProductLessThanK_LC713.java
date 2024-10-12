package Algorithms.SlidingWindow;

public class SubarrayProductLessThanK_LC713 {
    

    //Time: O(N)
    //Space: O(1)
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        int n = nums.length;

        int result = 0;
        int left = 0;
        long product = 1;

        for(int right = 0; right<n; right++){

            product *= nums[right];

            while(left <= right && product >= k){
                product /= nums[left];
                left++;
            }

            //Window size will be the subarrays which product less than K
            result += (right - left + 1);
        }

        return result;
        
    }
}