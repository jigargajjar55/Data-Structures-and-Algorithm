public class LongestSubarrayOf1sAfterDeletingOneElement_LC1493 {
    
    //Time: O(2 * N), Space: O(1)
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int zeros = 0;
        int ans = 0;

        for(int right=0; right<n; right++){

            if(nums[right] == 0){
                zeros++;
            }

            while(zeros > 1){
                if(nums[left] == 0){
                    zeros--;
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1 - zeros);
        }

        if(ans == n){
            return ans - 1;
        }else{
            return ans;
        }
        
    }
}

