class UniquePathsII_LC63 {

    // Time : O(2 ^ (m * n)), Space: O(path length) ~ O((m-1) + (n-1))
    private int solveByRecursion(int row, int col, int[][] grid, int mod) {

        // Base case
        if (row < 0 || col < 0) {
            return 0;
        }
        if (row >= 0 && col >= 0 && grid[row][col] == 1) {
            return 0;
        }
        if (row == 0 && col == 0) {
            return 1;
        }        

        int up = solveByRecursion(row - 1, col, grid, mod);
        int left = solveByRecursion(row, col - 1, grid, mod);

        int ans = (up + left) % mod;
        return ans;

    }

    // Time : O(m * n), Space: O(DP Array and path length) ~ O(m * n) + O((m-1) +
    // (n-1))
    private int solveByTopDownDP(int row, int col, int[][] grid, int[][] dp) {

        // Base case
        if (row < 0 || col < 0) {
            return 0;
        }
        if (row >= 0 && col >= 0 && grid[row][col] == 1) {
            return dp[row][col] = 0;
        }
        if (row == 0 && col == 0) {
            return dp[row][col] = 1;
        }        

        // Overlapping Sub-Problem
        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        int up = solveByTopDownDP(row - 1, col, grid, dp);
        int left = solveByTopDownDP(row, col - 1, grid, dp);

        dp[row][col] = (up + left);
        return dp[row][col];

    }

    // Time: O(m * n) , Space: O(m * n) (Space for DP Array)
    private int solveByBottomUpDP(int m, int n, int[][] grid) {
        // Step1 : Initilise DP Array
        int[][] dp = new int[m][n];
        // Step 2: Analyse Base case
        dp[0][0] = 1;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 1) {
                    dp[row][col] = 0;

                } else if (row == 0 && col == 0) {
                    continue;
                } else {
                    int up = 0;
                    int left = 0;

                    if (row > 0) {
                        up = dp[row - 1][col];
                    }
                    if (col > 0) {
                        left = dp[row][col - 1];
                    }

                    dp[row][col] = up + left;
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // Time: O(m * n) , Space: O(n) (Space for 1D DP Array)
    private int solveBySpaceOptimise(int m, int n, int[][] grid) {
        // Step1 : Initilise DP Array
        int[] prev = new int[n];

        for (int row = 0; row < m; row++) {
            int[] curr = new int[n];
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 1) {
                    curr[col] = 0;

                } else if (row == 0 && col == 0) {
                    curr[col] = 1;
                } else {
                    int up = 0;
                    int left = 0;

                    if (row > 0) {
                        up = prev[col];
                    }
                    if (col > 0) {
                        left = curr[col - 1];
                    }

                    curr[col] = up + left;
                }
            }

            prev = curr;
        }

        return prev[n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // return solveByRecursion(m-1,n-1,obstacleGrid,mod);

        // int[][] dp = new int[m][n];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(m-1,n-1,obstacleGrid,dp);

        // return solveByBottomUpDP(m,n,obstacleGrid);

        return solveBySpaceOptimise(m, n, obstacleGrid);

    }
}