package DynamicProgramming.MatrixGrids;
public class MaximumFallingPathSum {

    // Time: O(3 ^ N){Exponential in nature}, Space: O(N){Recursion Tree Depth}
    static int solveByRescursion(int row, int col, int N, int[][] matrix) {
        // Base case
        if (col < 0 || col >= N) {
            return -(int) (1e9);
        }
        if (row == 0) {
            return matrix[row][col];
        }

        int up = matrix[row][col] + solveByRescursion(row - 1, col, N, matrix);
        int digonalLeft = matrix[row][col] + solveByRescursion(row - 1, col - 1, N, matrix);
        int digonalRight = matrix[row][col] + solveByRescursion(row - 1, col + 1, N, matrix);

        int ans = Math.max(up, Math.max(digonalLeft, digonalRight));

        return ans;

    }

    // Time: O(N * N), Space: O(N * N) + O(N){DP Array and Recursion call stack
    // space}{Recursion + Memoization}
    static int solveByTopDownDP(int row, int col, int N, int[][] matrix, int[][] dp) {
        // Base case
        if (col < 0 || col >= N) {
            return -(int) (1e9);
        }
        if (row == 0) {
            return matrix[row][col];
        }

        // Overlapping sub-problem
        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        int up = matrix[row][col] + solveByTopDownDP(row - 1, col, N, matrix, dp);
        int digonalLeft = matrix[row][col] + solveByTopDownDP(row - 1, col - 1, N, matrix, dp);
        int digonalRight = matrix[row][col] + solveByTopDownDP(row - 1, col + 1, N, matrix, dp);

        dp[row][col] = Math.max(up, Math.max(digonalLeft, digonalRight));

        return dp[row][col];

    }

    // Time: O(N * N), Spcae: O(N * N) {DP Array}{Tabulation}
    static int solveByBottomUpDP(int N, int[][] matrix) {

        int[][] dp = new int[N][N];

        // BAse case
        for (int i = 0; i < N; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int row = 1; row < N; row++) {

            for (int col = 0; col < N; col++) {

                int up = matrix[row][col] + dp[row - 1][col];

                int digonalLeft = matrix[row][col];

                if (col > 0) {
                    digonalLeft += dp[row - 1][col - 1];
                } else {
                    digonalLeft = -(int) (1e9);
                }
                int digonalRight = matrix[row][col];

                if (col < N - 1) {

                    digonalRight += dp[row - 1][col + 1];
                } else {
                    digonalRight = -(int) (1e9);
                }

                dp[row][col] = Math.max(up, Math.max(digonalLeft, digonalRight));
            }
        }

        int maxi = -(int) (1e9);
        for (int i = 0; i < N; i++) {
            maxi = Math.max(maxi, dp[N - 1][i]);
        }

        return maxi;
    }

    // Time: O(N * N), Space: O(N) {1d DP Array to store previous row}
    static int solveBySpaceOptimize(int N, int[][] matrix) {

        int[] prev = new int[N];

        // BAse case
        for (int i = 0; i < N; i++) {
            prev[i] = matrix[0][i];
        }

        for (int row = 1; row < N; row++) {

            int[] curr = new int[N];

            for (int col = 0; col < N; col++) {

                int up = matrix[row][col] + prev[col];

                int digonalLeft = matrix[row][col];

                if (col > 0) {
                    digonalLeft += prev[col - 1];
                } else {
                    digonalLeft = -(int) (1e9);
                }
                int digonalRight = matrix[row][col];

                if (col + 1 < N) {

                    digonalRight += prev[col + 1];
                } else {
                    digonalRight = -(int) (1e9);
                }

                curr[col] = Math.max(up, Math.max(digonalLeft, digonalRight));
            }
            prev = curr;
        }

        int maxi = -(int) (1e9);
        for (int i = 0; i < N; i++) {
            maxi = Math.max(maxi, prev[i]);
        }

        return maxi;
    }

    static int maximumPath(int N, int Matrix[][]) {

        // int maxi = -(int)(1e9);
        // for(int i=0; i<N; i++){
        // maxi = Math.max(maxi, solveByRescursion(N-1,i,N,Matrix));
        // }
        // return maxi;

        // int[][] dp = new int[N][N];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }

        // int maxi = -(int)(1e9);

        // for(int i=0; i<N; i++){
        // maxi = Math.max(maxi, solveByTopDownDP(N-1,i,N,Matrix,dp) );

        // }

        // return maxi;

        // return solveByBottomUpDP(N,Matrix);

        return solveBySpaceOptimize(N, Matrix);

    }
}
