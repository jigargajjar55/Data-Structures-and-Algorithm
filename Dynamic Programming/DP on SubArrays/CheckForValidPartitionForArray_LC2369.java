import java.util.*;

public class CheckForValidPartitionForArray_LC2369 {
    

    //Time: O(Exponential), Space: O(N){Aux. Stack space, N: length of nums array}
    private boolean solveByRecursion(int index,int n,int[] nums){

        //Base Case
        if(index == n){
            return true;
        }
        
        boolean result = false;
        if(index+1 < n && nums[index] == nums[index+1]){
            boolean ans1 = solveByRecursion(index+2,n,nums);
            result |= ans1;

            if(index+2 < n && nums[index] == nums[index+2]){
                boolean ans2 = solveByRecursion(index+3,n,nums);
                result |= ans2;
            }
        }

        if((index+2 < n) && (nums[index] + 1 == nums[index+1]) && (nums[index+1] + 1 == nums[index+2])){
            boolean ans3 = solveByRecursion(index+3,n,nums);
            result |= ans3;
        }

        return result;

    }

    //Time: O(N), Space: O(N + N){Aux. Stack space and 1D DP Array, N: length of nums array}
    private boolean solveByTopDownDP(int index,int n,int[] nums,int[] dp){

        //Base Case
        if(index == n){
            return true;
        }

        //Overlapping sub-problem
        if(dp[index] != -1){
            return dp[index] == 1;
        }
        
        boolean result = false;
        if(index+1 < n && nums[index] == nums[index+1]){
            boolean ans1 = solveByTopDownDP(index+2,n,nums,dp);
            result |= ans1;

            if(index+2 < n && nums[index] == nums[index+2]){
                boolean ans2 = solveByTopDownDP(index+3,n,nums,dp);
                result |= ans2;
            }
        }

        if((index+2 < n) && (nums[index] + 1 == nums[index+1]) && (nums[index+1] + 1 == nums[index+2])){
            boolean ans3 = solveByTopDownDP(index+3,n,nums,dp);
            result |= ans3;
        }

        dp[index] = result ? 1 : 0;

        return result;

    }

    //Time: O(N), Space: O(N){1D DP Array, N: length of nums array}
    private boolean solveByBottomUpDP(int n,int[] nums){

        boolean[] dp = new boolean[n+1];
        dp[n] = true; 

        for(int index=n-1; index>=0; index--){

            boolean result = false;
            if(index+1 < n && nums[index] == nums[index+1]){
                boolean ans1 = index+2 <= n ? dp[index+2] : false;
                result |= ans1;

                if(index+2 < n && nums[index] == nums[index+2]){
                    boolean ans2 = index+3 <= n ? dp[index+3] : false;
                    result |= ans2;
                }
            }

            if((index+2 < n) && (nums[index] + 1 == nums[index+1]) && (nums[index+1] + 1 == nums[index+2])){
                boolean ans3 = index+3 <= n ? dp[index+3] : false;
                result |= ans3;
            }

            dp[index] = result;
        }  
        return dp[0];
    }


    public boolean validPartition(int[] nums) {

        int n = nums.length;
        //return solveByRecursion(0,n,nums);

        // int[] dp = new int[n];
        // Arrays.fill(dp,-1);
        // return solveByTopDownDP(0,n,nums,dp);

        return solveByBottomUpDP(n,nums);
        
    }
}
