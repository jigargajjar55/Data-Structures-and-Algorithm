import java.util.*;

public class IntegerBreak_LC343 {

    //Time: O(Exponential),
    //Space: O(Target) {Aux. Stack Space}
    private int solveByRecursion1(int target,int k){

        //Base Case
        if(target == 0){
            if(k >= 2){
                return 1;
            }else{
                return 0;
            }
        }

        int result = 0;
        for(int numBreak=1; numBreak<=target; numBreak++){

            int ans = solveByRecursion1(target-numBreak, k+1);
            int output = numBreak * ans;

            if(result < output){
                result = output;
            }
        }
        
        return result;       

    }

    //Time: O(Target * Target * Target),
    //Space: O(Target + (Target * Target)) {Aux. Stack Space and 2D DP Array}
    private int solveByTopDownDP1(int target,int k,int[][] dp){

        //Base Case
        if(target == 0){
            if(k >= 2){
                return 1;
            }else{
                return 0;
            }
        }
        //Overlapping sub-problem
        if(dp[target][k] != -1){
            return dp[target][k];
        }

        int result = 0;
        for(int numBreak=1; numBreak<=target; numBreak++){

            int ans = solveByTopDownDP1(target-numBreak, k+1,dp);
            int output = numBreak * ans;

            if(result < output){
                result = output;
            }
        }
        
        return dp[target][k] = result;       

    }
    
    public int integerBreak1(int n) {

        //return solveByRecursion1(n,0);

        int[][] dp = new int[n+1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solveByTopDownDP1(n,0,dp);
        
    }



    //Time: O(Exponential),
    //Space: O(Target) {Aux. Stack Space}
    private int solveByRecursion2(int target){

        //Base Case
        if(target <= 2){
            return 1;
        }
        if(target < 0){
            return 0;
        }
         

        int result = 0;
        for(int numBreak=2; numBreak<=target; numBreak++){

            int ans = solveByRecursion2(target-numBreak);
            int output = numBreak * ans;

            result = Math.max(result, output);
        }
        
        return result;       

    }


    //Time: O(Target * Target),
    //Space: O(Target + Target) {Aux. Stack Space & 1D DP Array}
    private long solveByTopDownDP2(int target,long[] dp){

        //Base Case
        if(target <= 2){
            return 1;
        }
        if(target < 0){
            return 0;
        }
        //Overlapping sub-problem
        if(dp[target] != -1){
            return dp[target];
        }

        long result = 0;
        for(int numBreak=2; numBreak<=target; numBreak++){

            long ans = solveByTopDownDP2(target-numBreak,dp);
            long output = numBreak * ans;

            result = Math.max(result, output);
        }
        
        return dp[target] = result;       

    }
    
    
    public int integerBreak(int n) {

        if(n <= 2){
            return 1;
        }

        if(n == 3){
            return 2;
        }

       //return solveByRecursion2(n);

        long[] dp = new long[n+1];        
        Arrays.fill(dp, -1);
        
        return (int)solveByTopDownDP2(n,dp);
        
    }





}