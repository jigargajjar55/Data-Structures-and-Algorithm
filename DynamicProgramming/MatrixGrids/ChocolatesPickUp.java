package DynamicProgramming.MatrixGrids;

public class ChocolatesPickUp {

    // Time : O((3 ^ N) * (3 ^ N)){Exponential}, Space: O(N){Aux. stack space} N :
    // Number of rows
    private int solveByRecursion(int row, int col1, int col2, int rows, int cols, int[][] grid) {
        // Base Case
        if (col1 < 0 || col1 >= cols || col2 < 0 || col2 >= cols) {
            return -(int) (1e9);
        }
        if (row == rows - 1) {
            if (col1 == col2) {
                return grid[row][col1];
            } else {
                return (grid[row][col1] + grid[row][col2]);
            }
        }

        // Explore all paths
        int maxi = -(int) (1e9);
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int value = 0;
                if (col1 == col2) {
                    value = grid[row][col1];
                } else {
                    value = grid[row][col1] + grid[row][col2];
                }
                value += solveByRecursion(row + 1, col1 + dj1, col2 + dj2, rows, cols, grid);

                maxi = Math.max(maxi, value);
            }
        }

        return maxi;
    }

    // Time : O(N * M * M * 9), Space: O(N) + O(N * M * M){Aux. stack space and DP
    // Array} N : Number of rows, M : Number of cols
    private int solveByTopDownDP(int row, int col1, int col2, int rows, int cols, int[][] grid, int[][][] dp) {
        // Base Case
        if (col1 < 0 || col1 >= cols || col2 < 0 || col2 >= cols) {
            return -(int) (1e9);
        }
        if (row == rows - 1) {
            if (col1 == col2) {
                return dp[row][col1][col2] = grid[row][col1];
            } else {
                return dp[row][col1][col2] = (grid[row][col1] + grid[row][col2]);
            }
        }

        if (dp[row][col1][col2] != -1) {
            return dp[row][col1][col2];
        }

        // Explore all paths
        int maxi = -(int) (1e9);
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int value = 0;
                if (col1 == col2) {
                    value = grid[row][col1];
                } else {
                    value = grid[row][col1] + grid[row][col2];
                }
                value += solveByTopDownDP(row + 1, col1 + dj1, col2 + dj2, rows, cols, grid, dp);

                maxi = Math.max(maxi, value);
            }
        }

        return dp[row][col1][col2] = maxi;

    }

    // Time : O(N * M * M * 9), Space: O(N * M * M){3D DP Array} N : Number of rows,
    // M : Number of cols
    private int solveByBottomUpDP(int n, int m, int[][] grid) {

        int[][][] dp = new int[n][m][m];
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) {
                    dp[n - 1][j1][j2] = grid[n - 1][j1];
                } else {
                    dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
                }
            }
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int col1 = 0; col1 < m; col1++) {
                for (int col2 = 0; col2 < m; col2++) {

                    // Explore all paths
                    int maxi = -(int) (1e9);
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int value = 0;
                            if (col1 == col2) {
                                value = grid[row][col1];
                            } else {
                                value = grid[row][col1] + grid[row][col2];
                            }
                            if (col1 + dj1 >= 0 && col1 + dj1 < m && col2 + dj2 >= 0 && col2 + dj2 < m) {
                                value += dp[row + 1][col1 + dj1][col2 + dj2];
                            } else {
                                value = -(int) (1e9);
                            }

                            maxi = Math.max(maxi, value);
                        }
                    }

                    dp[row][col1][col2] = maxi;

                }
            }
        }

        return dp[0][0][m - 1];

    }

    // Because dp[i][j1][j2] depends on next section, So we will take only 2D Array
    // of next section
    // Time : O(N * M * M * 9), Space: O(M * M){2D DP Array} N : Number of rows, M :
    // Number of cols
    private int solveBySpaceOptimize(int n, int m, int[][] grid) {

        int[][] next = new int[m][m];
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) {
                    next[j1][j2] = grid[n - 1][j1];
                } else {
                    next[j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
                }
            }
        }

        for (int row = n - 2; row >= 0; row--) {
            int[][] curr = new int[m][m];
            for (int col1 = 0; col1 < m; col1++) {
                for (int col2 = 0; col2 < m; col2++) {

                    // Explore all paths
                    int maxi = -(int) (1e9);
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int value = 0;
                            if (col1 == col2) {
                                value = grid[row][col1];
                            } else {
                                value = grid[row][col1] + grid[row][col2];
                            }
                            if (col1 + dj1 >= 0 && col1 + dj1 < m && col2 + dj2 >= 0 && col2 + dj2 < m) {
                                value += next[col1 + dj1][col2 + dj2];
                            } else {
                                value = -(int) (1e9);
                            }

                            maxi = Math.max(maxi, value);
                        }
                    }

                    curr[col1][col2] = maxi;

                }
            }

            next = curr;
        }

        return next[0][m - 1];

    }

    public int solve(int n, int m, int grid[][]) {

        // return solveByRecursion(0,0,m-1,n,m,grid);

        // int[][][] dp = new int[n][m][m];
        // for(int i=0; i<n; i++){
        // for(int[] j : dp[i]){
        // Arrays.fill(j,-1);
        // }
        // }
        // return solveByTopDownDP(0,0,m-1,n,m,grid,dp);

        // return solveByBottomUpDP(n,m,grid);

        return solveBySpaceOptimize(n, m, grid);
    }
}
