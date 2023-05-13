import java.util.*;

public class TrianglePathSum {
    // Time: O(2 ^ (elements in Grid)), Space: O(N) (Recursive Stack Space) N :-
    // Size of grid
    private static int solveByRecursion(int row, int col, int n, ArrayList<ArrayList<Integer>> grid) {
        // Base case
        if (row == n - 1) {
            return grid.get(row).get(col);
        }

        int down = grid.get(row).get(col) + solveByRecursion(row + 1, col, n, grid);
        int digonal = grid.get(row).get(col) + solveByRecursion(row + 1, col + 1, n, grid);

        int ans = Math.min(down, digonal);

        return ans;

    }

    // Time: O(elements in Grid) ~ O(N * N) , Space : O(N) + O(N * N)
    // {Recursive Stack Space and DP Array}
    private static int solveByTopDownDP(int row, int col, int n, ArrayList<ArrayList<Integer>> grid, int[][] dp) {
        // Base case
        if (row == n - 1) {
            return dp[row][col] = grid.get(row).get(col);
        }

        // Overlapping Sub-problem
        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        int down = grid.get(row).get(col) + solveByTopDownDP(row + 1, col, n, grid, dp);
        int digonal = grid.get(row).get(col) + solveByTopDownDP(row + 1, col + 1, n, grid, dp);

        dp[row][col] = Math.min(down, digonal);

        return dp[row][col];

    }

    // Time: O(elements in Grid) ~ O(N * N) , Space : O(N * N) {DP Array}
    private static int solveByBottomUpDP(int n, ArrayList<ArrayList<Integer>> grid) {

        int[][] dp = new int[n][n];

        // Base case from n-1th row
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = grid.get(n - 1).get(i);
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int col = row; col >= 0; col--) {

                int down = grid.get(row).get(col) + dp[row + 1][col];
                int digonal = grid.get(row).get(col) + dp[row + 1][col + 1];

                dp[row][col] = Math.min(down, digonal);

            }
        }

        return dp[0][0];

    }

    // Time: O(elements in Grid) ~ O(N * N) , Space : O(N) {1D DP Array}
    private static int solveBySpaceOptimize(int n, ArrayList<ArrayList<Integer>> grid) {

        int[] next = new int[n];

        // Base case from n-1th row
        for (int i = 0; i < n; i++) {
            next[i] = grid.get(n - 1).get(i);
        }

        for (int row = n - 2; row >= 0; row--) {

            int[] curr = new int[row + 1];

            for (int col = row; col >= 0; col--) {

                int down = grid.get(row).get(col) + next[col];
                int digonal = grid.get(row).get(col) + next[col + 1];

                curr[col] = Math.min(down, digonal);

            }

            next = curr;
        }

        return next[0];

    }

    public static int minimizeSum(int n, ArrayList<ArrayList<Integer>> grid) {

        // return solveByRecursion(0,0,n,grid);

        // int[][] dp = new int[n][n];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(0,0,n,grid,dp);

        // return solveByBottomUpDP(n,grid);

        return solveBySpaceOptimize(n, grid);

    }
}
