package DynamicProgramming.LIS;
import java.util.*;

public class LongestArithmeticSubsequenceOfGivenDifference_LC1218 {
 
    //Time: O(Exponential), Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int index,int prevIndex,int n,int[] arr,int diff){

        //Base Case
        if(index == n){
            return 0;
        }

        int len = solveByRecursion(index+1,prevIndex,n,arr,diff);

        if(prevIndex == -1 || ((arr[index] - arr[prevIndex]) == diff)){
            int ans = 1 + solveByRecursion(index+1,index,n,arr,diff);
            len = Math.max(len, ans);
        }

        return len;
    }

    //Time: O(N ^ 2), Space: O(N + (N ^ 2)){Aux. Stack Space and 2D DP Array}
    private int solveByTopDownDP(int index,int prevIndex,int n,int[] arr,int diff,int[][] dp){

        //Base Case
        if(index == n){
            return 0;
        }
        //Overlapping sub-problem
        if(dp[index][prevIndex+1] != -1){
            return dp[index][prevIndex+1];
        }

        int len = solveByTopDownDP(index+1,prevIndex,n,arr,diff,dp);

        if(prevIndex == -1 || ((arr[index] - arr[prevIndex]) == diff)){
            int ans = 1 + solveByTopDownDP(index+1,index,n,arr,diff,dp);
            len = Math.max(len, ans);
        }

        return dp[index][prevIndex+1] = len;
    }

    //Time: O(N ^ 2), Space: O(N ^ 2){2D DP Array}
     private int solveByBottomUpDP(int n,int[] arr,int diff){

        int[][] dp = new int[n+1][n+1];

        for(int index=n-1; index>=0; index--){
            for(int prevIndex=index-1; prevIndex>=-1; prevIndex--){

                int len = dp[index+1][prevIndex+1];

                if(prevIndex == -1 || ((arr[index] - arr[prevIndex]) == diff)){
                    int ans = 1 + dp[index+1][index+1];
                    len = Math.max(len, ans);
                }

                dp[index][prevIndex+1] = len;

            }
        }

        return dp[0][0];
    }

    //Time: O(N ^ 2), Space: O(2 * N){2 1D DP Array}
    private int solveBySpaceOptimise(int n,int[] arr,int diff){

        int[] ahead = new int[n+1];

        for(int index=n-1; index>=0; index--){
            int[] curr = new int[n+1];
            for(int prevIndex=index-1; prevIndex>=-1; prevIndex--){

                int len = ahead[prevIndex+1];

                if(prevIndex == -1 || ((arr[index] - arr[prevIndex]) == diff)){
                    int ans = 1 + ahead[index+1];
                    len = Math.max(len, ans);
                }

                curr[prevIndex+1] = len;

            }
            ahead = curr;
        }

        return ahead[0];
    }

    //Time: O(N ^ 2), Space: O(N + (N ^ 2)){Aux. Stack Space and DP Hashing}
    private int solveByTopDownDPHash(int index,int prevIndex,int n,int[] arr,int diff,Map<String, Integer> dp){

        //Base Case
        if(index == n){
            return 0;
        }

        String curr = index + ", " + prevIndex;

        //Overlapping sub-problem
        if(dp.containsKey(curr)){
            return dp.get(curr);
        }


        int len = solveByTopDownDPHash(index+1, prevIndex, n, arr,diff,dp);

        if(prevIndex == -1 || ((arr[index] - arr[prevIndex]) == diff)){
            int ans = 1 + solveByTopDownDPHash(index+1, index,n,arr,diff,dp);

            len = Math.max(len, ans);
        }

        dp.put(curr, len);

        return len;

    }



    public int longestSubsequence(int[] arr, int difference) {

        int n = arr.length;

        //return solveByRecursion(0,-1,n,arr,difference);
        
        // int[][] dp = new int[n][n+1];
        // for(int[] row : dp){
        //     Arrays.fill(row, -1);
        // }
        // return solveByTopDownDP(0,-1,n,arr,difference, dp);

        Map<String, Integer> dp = new HashMap<>();

        return solveByTopDownDPHash(0,-1,n,arr,difference, dp);

       // return solveByBottomUpDP(n,arr,difference);
        
       // return solveBySpaceOptimise(n,arr,difference);
    }
}