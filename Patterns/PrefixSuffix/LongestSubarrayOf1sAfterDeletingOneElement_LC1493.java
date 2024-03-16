package Patterns.PrefixSuffix;

public class LongestSubarrayOf1sAfterDeletingOneElement_LC1493 {
    
    //Time: O(3 * N), Space: O(2 * N)
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];

        for(int i=1; i<n; i++){
            prefix[i] = nums[i-1] == 1 ? prefix[i-1] + 1 : 0;
        }

        for(int i=n-2; i>=0; i--){
            suffix[i] = nums[i+1] == 1 ? suffix[i+1] + 1 : 0;
        }

        int ans = 0;
        for(int i=0; i<n; i++){
            ans = Math.max(ans, prefix[i] + suffix[i]);
        }

        return ans;
        
    }
}