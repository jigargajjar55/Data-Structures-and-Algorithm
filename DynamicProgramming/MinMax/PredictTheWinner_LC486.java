package DynamicProgramming.MinMax;
import java.util.*;

public class PredictTheWinner_LC486 {
    

    //Time: O(Exponential), Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int start,int end,int[] nums){

        //Base Case
        if(start > end){
            return 0;
        }


        int takeFromStart = nums[start] + 
                                    Math.min(solveByRecursion(start+1,end-1, nums),
                                             solveByRecursion(start+2,end, nums));
        int takeFromEnd = nums[end] + Math.min(solveByRecursion(start+1,end-1, nums),
                                                    solveByRecursion(start,end-2, nums));

        int ans = Math.max(takeFromStart, takeFromEnd);
        return ans;       

    }

   
    //Time: O(N ^ 2), Space: O(N + (N ^ 2)){Aux. Stack Space and 2D DP Array}
    private int solveByTopDownDP(int start,int end,int[] nums,int[][] dp){

        //Base Case
        if(start > end){
            return 0;
        }

        //Overlapping sub-problem
        if(dp[start][end] != -1){
            return dp[start][end];
        }


        int takeFromStart = nums[start] + 
                                    Math.min(solveByTopDownDP(start+1,end-1, nums,dp),
                                             solveByTopDownDP(start+2,end, nums,dp));
        int takeFromEnd = nums[end] + Math.min(solveByTopDownDP(start+1,end-1, nums,dp),
                                                    solveByTopDownDP(start,end-2, nums,dp));

        int ans = Math.max(takeFromStart, takeFromEnd);
        return dp[start][end] = ans;      

    }

    //Time: O(N ^ 2), Space: O(N ^ 2){2D DP Array}
    private int solveByBottomUpDP(int n,int[] nums){
        //Initialize DP
        int[][] dp = new int[n+1][n+1];
        

        for(int start=n-1; start>=0; start--){
            for(int end=start; end < n; end++){

                int takeFromEndByOpsFirstStep = end > 0 ? dp[start+1][end-1] : 0;
                int takeFromStartByOpsFirstStep = ((start+2) <= n) ? dp[start+2][end] : 0;

                int takeFromStartByOpsSecondStep = end > 0 ? dp[start+1][end-1] : 0;
                int takeFromEndByOpsSecondStep = end > 1 ? dp[start][end-2] : 0;

                int takeFromStart = nums[start] + Math.min(takeFromEndByOpsFirstStep,takeFromStartByOpsFirstStep);
                int takeFromEnd = nums[end] + Math.min(takeFromStartByOpsSecondStep,takeFromEndByOpsSecondStep);
                                   

                int ans = Math.max(takeFromStart, takeFromEnd);
                dp[start][end] = ans;      


            }
        }

        return dp[0][n-1];

    }


    public boolean PredictTheWinner(int[] nums) {

        int n = nums.length;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        // int myBestTake = solveByRecursion(0,n-1,nums);
        // System.out.println(resultForPlayer1);

        // int[][] dp = new int[n][n];
        // for(int[] row : dp){
        //     Arrays.fill(row, -1);
        // }
        // int myBestTake = solveByTopDownDP(0,n-1,nums,dp);

        int myBestTake = solveByBottomUpDP(n,nums);

        boolean ans = ((2 * myBestTake) >= sum);

        return ans;
        
    }
}