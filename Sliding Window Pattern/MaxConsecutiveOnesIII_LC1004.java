public class MaxConsecutiveOnesIII_LC1004 {
    
    //Time: O(2 * N), Space: O(1)
    public int longestOnes(int[] nums, int k) {

        int zeros = 0;
        int len = 0;
        int n = nums.length;

        int left = 0;

        for(int right=0; right<n; right++){

            if(nums[right] == 0){
                zeros++;
            }

            while(left <= right && zeros > k){
                if(nums[left] == 0){
                    zeros--;
                }
                left++;
            }

            len = Math.max(len, right - left + 1);
        }

        return len;
        
    }
}