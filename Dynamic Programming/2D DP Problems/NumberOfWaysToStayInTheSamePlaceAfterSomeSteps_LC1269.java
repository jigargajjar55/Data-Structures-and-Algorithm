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

    //Time: O(arrLen * Steps)
    //Space: O(Steps + (arrLen * steps)){Aux. Stack space & 2D DP Array}
    private int solveByTopDownDP(int index, int n,int steps,int[][] dp){

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

        //Overlapping sub-problem
        if(dp[index][steps] != -1){
            return dp[index][steps];
        }


        int stayMove = solveByTopDownDP(index, n, steps-1,dp);
        int leftMove = solveByTopDownDP(index-1,n,steps-1,dp);
        int rightMove = solveByTopDownDP(index+1,n,steps-1,dp);

        int ans = addMod(stayMove, addMod(leftMove, rightMove));
        return dp[index][steps] = ans;
    }

    //Time: O(arrLen * Steps)
    //Space: O(Steps + (Min(arrLen/2, steps) * steps)){Aux. Stack space & DP Hashing}
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

        // int[][] dp = new int[arrLen][steps+1];
        // for(int[] row : dp){
        //     Arrays.fill(row, -1);
        // }
        // return solveByTopDownDP(0,arrLen, steps,dp);

        Map<String,Integer> dp = new HashMap<>();
        return solveByTopDownDPusingHash(0,arrLen, steps,dp); 
        
    }
}