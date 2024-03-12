package DynamicProgramming.MatrixGrids;
import java.util.*;

public class MaximumNumberofMovesinGridLC2684 {

    private boolean isSafe(int row, int col, int m, int n) {
        if ((row >= 0 && row < m) && (col >= 0 && col < n)) {
            return true;
        } else {
            return false;
        }
    }

    // Time: O(3 ^ (M * N)), Space: O(N){No. of Columns in Grid}
    private int solveByResursion(int row, int col, int[][] grid, int m, int n, int[] delRow) {

        int maxi = 0;

        for (int i = 0; i < 3; i++) {

            int nRow = row + delRow[i];
            int nCol = col + 1;

            if (isSafe(nRow, nCol, m, n) && (grid[row][col] < grid[nRow][nCol])) {
                maxi = Math.max(maxi, 1 + solveByResursion(nRow, nCol, grid, m, n, delRow));
            }

        }

        return maxi;
    }

    // Time: O(M * N)), Space: O(N) + O(M * N){Aux. Stack Space + DP Array, N : No.
    // of Columns in Grid}
    private int solveByTopDownDP(int row, int col, int[][] grid, int m, int n, int[] delRow, int[][] dp) {

        // Overlapping subproblem
        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        int maxi = 0;

        for (int i = 0; i < 3; i++) {

            int nRow = row + delRow[i];
            int nCol = col + 1;

            if (isSafe(nRow, nCol, m, n) && (grid[row][col] < grid[nRow][nCol])) {
                maxi = Math.max(maxi, 1 + solveByTopDownDP(nRow, nCol, grid, m, n, delRow, dp));
            }

        }

        return dp[row][col] = maxi;
    }

    public int maxMoves(int[][] grid) {

        int[] delRow = { -1, 0, 1 };

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);

        }

        int result = 0;

        for (int i = 0; i < m; i++) {
            result = Math.max(result, solveByTopDownDP(i, 0, grid, m, n, delRow, dp));
        }

        return result;

    }
}
