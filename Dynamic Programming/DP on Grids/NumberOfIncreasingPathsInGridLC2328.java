import java.util.*;

public class NumberOfIncreasingPathsInGridLC2328 {

    private int addMod(int a, int b) {
        return (((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007);
    }

    private int dfs(int row, int col, int m, int n, int[][] grid, int prev, int[][] dp, int[] delRow, int[] delCol) {

        // Base Case
        if ((row >= m || row < 0) || (col >= n || col < 0) || (grid[row][col] <= prev)) {
            return 0;
        }

        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        int currValue = grid[row][col];

        int count = 1;

        for (int i = 0; i < 4; i++) {
            int nRow = row + delRow[i];
            int nCol = col + delCol[i];

            int recursiveCount = dfs(nRow, nCol, m, n, grid, currValue, dp, delRow, delCol);
            count = addMod(count, recursiveCount);

        }

        return dp[row][col] = count;

    }

    public int countPaths(int[][] grid) {

        // Time: O(m * n * 4), Space: O(2 * m * n) {Aux. Stack Space and DP
        // Array}

        // https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/solutions/3650111/java-c-python-solution-easy-to-understand/

        int m = grid.length;
        int n = grid[0].length;
        int[] delRow = { -1, 0, 1, 0 };
        int[] delCol = { 0, 1, 0, -1 };

        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int count = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int pathAns = dfs(row, col, m, n, grid, -1, dp, delRow, delCol);

                count = addMod(count, pathAns);

            }
        }

        return count;

    }
}