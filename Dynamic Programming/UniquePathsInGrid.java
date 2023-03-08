import java.util.*;
import java.io.*;

public class UniquePathsInGrid {

    // Time : O(2 ^ (m * n)), Space: O(path length) = O((m-1) + (n-1))
    private static int solveByRecursion(int row, int col) {
        // Base Case
        if (row == 0 && col == 0) {
            return 1;
        }
        if (row < 0 || col < 0) {
            return 0;
        }

        int up = solveByRecursion(row - 1, col);
        int left = solveByRecursion(row, col - 1);

        int ans = up + left;

        return ans;
    }

    // Time : O(m * n), Space: O(m * n){for DP Array} + O(path length) = O((m-1) +
    // (n-1)){Recursive Call Stack Space}
    private static int solveByTopDownDP(int row, int col, int[][] dp) {
        // Base Case
        if (row == 0 && col == 0) {
            return dp[row][col] = 1;
        }
        if (row < 0 || col < 0) {
            return 0;
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        int up = solveByTopDownDP(row - 1, col, dp);
        int left = solveByTopDownDP(row, col - 1, dp);

        dp[row][col] = up + left;

        return dp[row][col];
    }

    // Time : O(m * n), Space: O(m * n){for DP Array}
    private static int solveByBottomUpDP(int rows, int cols) {
        // Base Case
        int[][] dp = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (row == 0 && col == 0) {
                    dp[row][col] = 1;
                } else {
                    int up = 0;
                    int down = 0;

                    if (row > 0) {
                        up = dp[row - 1][col];
                    }
                    if (col > 0) {
                        down = dp[row][col - 1];
                    }
                    dp[row][col] = up + down;
                }
            }

        }

        return dp[rows - 1][cols - 1];
    }

    // Time : O(m * n), Space: O(n){for 1D DP Array}
    private static int solveBySpaceOptimise(int rows, int cols) {
        // Base Case
        int[] prev = new int[cols];

        for (int row = 0; row < rows; row++) {
            int[] curr = new int[cols];
            for (int col = 0; col < cols; col++) {

                if (row == 0 && col == 0) {
                    curr[col] = 1;
                } else {
                    int up = 0;
                    int down = 0;

                    if (row > 0) {
                        up = prev[col];
                    }
                    if (col > 0) {
                        down = curr[col - 1];
                    }
                    curr[col] = up + down;
                }
            }

            prev = curr;

        }

        return prev[cols - 1];
    }

    public static int uniquePaths(int m, int n) {

        // return solveByRecursion(m-1,n-1);

        // int[][] dp = new int[m][n];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(m-1,n-1,dp);

        // return solveByBottomUpDP(m,n);

        return solveBySpaceOptimise(m, n);
    }
}
