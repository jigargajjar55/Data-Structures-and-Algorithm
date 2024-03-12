package DynamicProgramming.TwoDimensional;

import java.util.*;

public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps_LC1269 {
    

    

    private int addMod(int num1, int num2){
        return (((num1 % 1_000_000_007) + (num2 % 1_000_000_007)) % 1_000_000_007);
    }


    //Time: O(3 ^ (Steps))
    //Space: O(Steps){Aux. Stack space}
    private int solveByRecursion(int index, int n,int steps){

        //Base Case
        if(index < 0 || index >= n){
            return 0;
        }

        if(steps == 0){
            if(index == 0){
                return 1;
            }else{
                return 0;
            }
        }


        int stayMove = solveByRecursion(index, n, steps-1);
        int leftMove = solveByRecursion(index-1,n,steps-1);
        int rightMove = solveByRecursion(index+1,n,steps-1);

        int ans = addMod(stayMove, addMod(leftMove, rightMove));
        return ans;
    }

    //Time: O(Min(arrLen,Steps) * Steps)
    //Space: O(Steps + (Min(arrLen,Steps) * Steps)){Aux. Stack space & 2D DP Array}
    private int solveByTopDownDP(int index, int n,int steps,int[][] dp){

        //Base Case
        if(steps == 0){
            if(index == 0){
                return 1;
            }else{
                return 0;
            }
        }

        //Overlapping sub-problem
        if(dp[index][steps] != -1){
            return dp[index][steps];
        }

        int ans = 0;

        ans = addMod(ans, solveByTopDownDP(index, n, steps-1,dp));

        if(index > 0){
            ans = addMod(ans, solveByTopDownDP(index-1,n,steps-1,dp));
        }
        if(index < n - 1){
            ans = addMod(ans, solveByTopDownDP(index+1,n,steps-1,dp));
        }  
        return dp[index][steps] = ans;
    }

    //Time: O(Min(arrLen, Steps + 1) * Steps)
    //Space: O(Steps + (Min(arrLen, Steps) * Steps)){Aux. Stack space & DP Hashing}
    private int solveByTopDownDPusingHash(int index, int n,int steps,Map<String,Integer> dp){

        //Base Case
        if(index < 0 || index >= n){
            return 0;
        }

        if(steps == 0){
            if(index == 0){
                return 1;
            }else{
                return 0;
            }
        }

        String curr = index +": "+ steps;

        //Overlapping sub-problem
        if(dp.containsKey(curr)){
            return dp.get(curr);
        }


        int stayMove = solveByTopDownDPusingHash(index, n, steps-1,dp);
        int leftMove = solveByTopDownDPusingHash(index-1,n,steps-1,dp);
        int rightMove = solveByTopDownDPusingHash(index+1,n,steps-1,dp);

        int ans = addMod(stayMove, addMod(leftMove, rightMove));
        dp.put(curr,ans);

        return ans;
    }


    public int numWays(int steps, int arrLen) {

        //return solveByRecursion(0,arrLen, steps);

        //We are taking minimum value of array Length and Step.
        //Because we can't traverse all positions if length is greater than steps
        int minLen = Math.min(arrLen, steps);

        int[][] dp = new int[minLen][steps+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return solveByTopDownDP(0,minLen, steps,dp);

        // Map<String,Integer> dp = new HashMap<>();
        // return solveByTopDownDPusingHash(0,arrLen, steps,dp); 
        
    }
}