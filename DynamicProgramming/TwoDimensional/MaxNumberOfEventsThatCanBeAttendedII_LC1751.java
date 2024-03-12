package DynamicProgramming.TwoDimensional;

import java.util.*;

public class MaxNumberOfEventsThatCanBeAttendedII_LC1751 {

    
    //Time: O(2 ^ N) , Space: O(N)
    private int solveByRecursion(int prevEndDay,int index,int k,int[][] events,int n){

        //Base Case
        if(k == 0 || index == n){
            return 0;
        }

        if(prevEndDay >= events[index][0]){
            return solveByRecursion(prevEndDay, index+1, k,events,n);
        }

        int exclude = solveByRecursion(prevEndDay, index+1, k, events, n);
        int include = events[index][2] + solveByRecursion(events[index][1], index+1, k-1,events,n);


        int ans = Math.max(exclude,include);        
        return ans;
    }

    //Time: O(N * K), Space: O(N)
     private int solveByTopDownDP(int prevEndDay,int index,int k,int[][] events,int n,int[][] dp){

        //Base Case
        if(k == 0 || index == n){
            return 0;
        }

        if(prevEndDay >= events[index][0]){
            return solveByTopDownDP(prevEndDay, index+1, k,events,n,dp);
        }

        //Overlapping sub-problem
        if(dp[index][k] != -1){
            return dp[index][k];
        }

        int exlcude = solveByTopDownDP(prevEndDay, index+1, k, events, n,dp);
        int include = events[index][2] + solveByTopDownDP(events[index][1], index+1, k-1,events,n,dp);

        int ans = Math.max(exlcude,include);
        
        return dp[index][k] = ans;

    }
    public int maxValue(int[][] events, int k) {

        int n = events.length;

        //Time: O(N log N) + O(N * K), Space: O(N + (N * K)){Aux. Stack Space and 2D DP Array}
        //First sort the events based on starting Day
        Arrays.sort(events, (e1,e2) -> Integer.compare(e1[0],e2[0]));

        //return solveByRecursion(0,0,k,events,n);

        int[][] dp = new int[n][k+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }        
        return solveByTopDownDP(0,0,k,events,n,dp);
    }
}
