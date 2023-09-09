import java.util.*;

public class CombinationSumIV_LC377 {
    

    
    //Time: O(target ^ N){Exponential}, Space: O(target){Aux. Stack space}
    private int solveByRecursion(int index,int n,int[] nums,int target){

        //Base Case
        if(target == 0){
            return 1;
        }        

        int result = 0;

        for(int i=index; i<n; i++){

            int ans = 0;
            if(target >= nums[i]){
                ans = solveByRecursion(index,n,nums,target-nums[i]);
            }
            result += ans;
        }

        return result;       
    }
     
    //Time: O(target * N), Space: O(target + (target * N)){Aux. Stack space and 2D DP Array}
    private int solveByTopDownDP(int index,int n,int[] nums,int target,int[][] dp){

        //Base Case
        if(target == 0){
            return 1;
        }     

        //Overlapping sub-problem
        if(dp[index][target] != -1){
            return dp[index][target];
        }   

        int result = 0;

        for(int i=index; i<n; i++){

            int ans = 0;
            if(target >= nums[i]){
                ans = solveByTopDownDP(index,n,nums,target-nums[i],dp);
            }
            result += ans;
        }

        return dp[index][target] = result;       
    }
    //Time: O(target * N), Space: O(target){1D DP Array}
    private int solveByBottomUpDP(int[] nums,int target){
        int n = nums.length;

        int[] dp = new int[target+1];

        dp[0] = 1;

        for(int k=1; k<=target; k++){ 
  
            for(int index = n-1; index >=0; index--){                       
           
                int ans = 0;
                if(k >= nums[index]){
                    dp[k] += dp[k-nums[index]];
                }  
            }
        }

        return dp[target];

    }
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        //return solveByRecursion(0,n,nums,target);

        int[][] dp = new int[n][target+1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        return solveByTopDownDP(0,n,nums,target,dp); 

       // return solveByBottomUpDP(nums,target);      
    }
}