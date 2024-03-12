package DynamicProgramming.TwoDimensional;
import java.util.*;

public class PaintingTheWalls_LC2742 {
    

    
    //Time: O(Exponential)
    //Space: O(N){Aux. Stack space}
    private int solveByRecursion(int index,int remaining,int n,int[] cost,int[] time){

        //Base Case
        if(remaining <= 0){
            return 0;
        }
        if(index == n){
            return (int)(1e8);
        }


        int exclude = solveByRecursion(index+1,remaining,n,cost,time);
        int include = cost[index] + solveByRecursion(index+1,remaining - time[index] - 1,n,cost,time);

        int ans = Math.min(exclude, include);
        return ans;

    }

    //Time: O(N * N)
    //Space: O(N + (N ^ 2)){Aux. Stack space & 2D DP Array}
    private int solveByTopDownDP(int index,int remaining,int n,int[] cost,int[] time,int[][] dp){

        //Base Case
        if(remaining <= 0){
            return 0;
        }
        if(index == n){
            return (int)(1e9);
        }

        //Overlapping sub-problem
        if(dp[index][remaining] != -1){
            return dp[index][remaining];
        }


        int exclude = solveByTopDownDP(index+1,remaining,n,cost,time,dp);
        int include = cost[index] + solveByTopDownDP(index+1,remaining - time[index] - 1,n,cost,time,dp);

        int ans = Math.min(exclude, include);
        return dp[index][remaining] = ans;

    }
    public int paintWalls(int[] cost, int[] time) {

        int n = cost.length;

        //return solveByRecursion(0,n,n,cost,time);


        int[][] dp = new int[n][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solveByTopDownDP(0,n,n,cost,time,dp);

        
    }
}