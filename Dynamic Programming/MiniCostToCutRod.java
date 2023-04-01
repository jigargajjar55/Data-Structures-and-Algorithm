import java.util.*;

class MiniCostToCutRod {

    // Time : O(Exponential), Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int i, int j, int[] cutsArray) {
        // Base case
        if (i > j) {
            return 0;
        }

        int mini = (int) (1e9);
        for (int index = i; index <= j; index++) {
            int cost = (cutsArray[j + 1] - cutsArray[i - 1])
                    + solveByRecursion(i, index - 1, cutsArray)
                    + solveByRecursion(index + 1, j, cutsArray);
            mini = Math.min(mini, cost);
        }

        return mini;
    }

    // Time : O(N ^ 3), Space: O(N + (N ^ 2)){Aux. Stack Space and 2D DP Array}
    private int solveByTopDownDP(int i, int j, int[] cutsArray, int[][] dp) {
        // Base case
        if (i > j) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int mini = (int) (1e9);
        for (int index = i; index <= j; index++) {
            int cost = (cutsArray[j + 1] - cutsArray[i - 1])
                    + solveByTopDownDP(i, index - 1, cutsArray, dp)
                    + solveByTopDownDP(index + 1, j, cutsArray, dp);
            mini = Math.min(mini, cost);
        }

        return dp[i][j] = mini;
    }

    // Time : O(N ^ 3), Space: O(N ^ 2){2D DP Array}
    private int solveByBottomUpDP(int n, int c, int[] cutsArray) {
        int[][] dp = new int[c + 2][c + 2];
        // analyse the base case

        for (int i = c; i > 0; i--) {

            for (int j = 1; j <= c; j++) {

                if (i > j) {
                    continue;
                }

                int mini = (int) (1e9);
                for (int index = i; index <= j; index++) {
                    int cost = (cutsArray[j + 1] - cutsArray[i - 1])
                            + dp[i][index - 1]
                            + dp[index + 1][j];
                    mini = Math.min(mini, cost);
                }

                dp[i][j] = mini;

            }

        }

        return dp[1][c];

    }

    public int minCost(int n, int[] cuts) {

        int c = cuts.length;

        int[] cutsArray = new int[c + 2];
        cutsArray[0] = 0;
        int index = 1;
        for (int cut : cuts) {
            cutsArray[index++] = cut;
        }
        cutsArray[index] = n;
        Arrays.sort(cutsArray);

        // return solveByRecursion(1,c,cutsArray);

        // int[][] dp = new int[c+1][c+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(1,c,cutsArray,dp);

        return solveByBottomUpDP(n, c, cutsArray);

    }
}