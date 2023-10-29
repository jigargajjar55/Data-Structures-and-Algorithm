public class MaximumScoreOfAGoodSubarray_LC1793 {
    
    // https://leetcode.com/problems/maximum-score-of-a-good-subarray/solutions/4194071/92-13-two-pointers/?envType=daily-question&envId=2023-10-22

    //Time: O(N)
    //Space: O(1)
    public int maximumScore(int[] nums, int k) {

        int n = nums.length;
        int mini = nums[k];
        int i = k-1;
        int j = k+1;
        int ans = mini;

        while(i >= 0 || j < n){
            int v1 = 0;
            int v2 = 0;
            int mini1 = mini;
            int mini2 = mini;


            if(i >= 0){
                mini1 = Math.min(mini1, nums[i]);
                v1 = mini1 * (j - i);
            }
            if(j < n){
                mini2 = Math.min(mini2, nums[j]);
                v2 = mini2 * (j - i);
            }

            if(v1 > v2){
                i--;
                mini = Math.min(mini, mini1);
                ans = Math.max(ans, v1);
            }else{
                j++;
                mini = Math.min(mini, mini2);
                ans = Math.max(ans, v2);
            }
        }
        return ans;
        
    }
}