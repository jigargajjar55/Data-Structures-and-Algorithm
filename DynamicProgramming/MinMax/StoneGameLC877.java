package DynamicProgramming.MinMax;
import java.util.*;

public class StoneGameLC877 {

    // Time: O(Exponential), Space: O(N) {Aux. stack space, N : Array length}
    private int solveByRecursion(int left, int right, boolean turn, int[] piles) {

        // Base Case
        if (left > right) {
            return 0;
        }

        int firstElement = turn ? piles[left] : 0;
        int lastElement = turn ? piles[right] : 0;

        boolean altTurn = !turn;

        int takeFirst = firstElement + solveByRecursion(left + 1, right, altTurn, piles);

        int takeLast = lastElement + solveByRecursion(left, right - 1, altTurn, piles);

        int ans = Math.max(takeFirst, takeLast);

        return ans;
    }


    private int solveByRecursion1(int left, int right, int[] piles){

        //Base Case
        if(left > right){
            return 0;
        }        

        int takeFirst = piles[left] + Math.min(solveByRecursion1(left+1,right-1, piles),
                                            solveByRecursion1(left+2,right, piles));

        int takeLast = piles[right] + Math.min(solveByRecursion1(left+1,right-1, piles),
                                            solveByRecursion1(left,right-2, piles));

        int ans = Math.max(takeFirst, takeLast);

        return ans;
    }


    

    // Time: O(N * N), Space: O(N + (N * N)) {Aux. stack space and 2D DP, N : Array
    // length}
    private int solveByTopDownDP(int left, int right, boolean turn, int[] piles, int[][] dp) {

        // Base Case
        if (left > right) {
            return 0;
        }

        // Overlapping subproblem
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int firstElement = turn ? piles[left] : 0;
        int lastElement = turn ? piles[right] : 0;

        boolean altTurn = !turn;

        int takeFirst = firstElement + solveByTopDownDP(left + 1, right, altTurn, piles, dp);

        int takeLast = lastElement + solveByTopDownDP(left, right - 1, altTurn, piles, dp);

        dp[left][right] = Math.max(takeFirst, takeLast);

        return dp[left][right];
    }

    public boolean stoneGame(int[] piles) {

        int n = piles.length;
        int sum = 0;
        for (int pile : piles) {
            sum += pile;
        }
        // return solveByRecursion(0,n-1,true,piles) > (sum / 2);

        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solveByTopDownDP(0, n - 1, true, piles, dp) > (sum / 2);

    }
}
