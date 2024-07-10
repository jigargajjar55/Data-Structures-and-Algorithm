package DynamicProgramming.MatrixGrids;

public class MaximalSquare_LC221 {

    // Time: O(3 ^ (M * N))
    // Space: O(M + N){Aux. Stack Space}
    private int solveByRecursion(int i, int j, int m, int n, char[][] matrix, int[] maxi) {

        // Base Case
        if (i >= m || j >= n) {
            return 0;
        }

        int right = solveByRecursion(i, j + 1, m, n, matrix, maxi);
        int diagonal = solveByRecursion(i + 1, j + 1, m, n, matrix, maxi);
        int down = solveByRecursion(i + 1, j, m, n, matrix, maxi);

        int ans = 0;

        if (matrix[i][j] == '1') {
            ans = 1 + Math.min(right, Math.min(diagonal, down));
            maxi[0] = Math.max(maxi[0], ans);
        }

        return ans;

    }

    // Time: O(M * N)
    // Space: O(M + N + (M * N)){Aux. Stack Space and 2D DP array}
    private int solveByTopDownDP(int i, int j, int m, int n, char[][] matrix, int[] maxi, int[][] dp) {

        // Base Case
        if (i >= m || j >= n) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int right = solveByTopDownDP(i, j + 1, m, n, matrix, maxi, dp);
        int diagonal = solveByTopDownDP(i + 1, j + 1, m, n, matrix, maxi, dp);
        int down = solveByTopDownDP(i + 1, j, m, n, matrix, maxi, dp);

        int ans = 0;

        if (matrix[i][j] == '1') {
            ans = 1 + Math.min(right, Math.min(diagonal, down));
            maxi[0] = Math.max(maxi[0], ans);
        }

        return dp[i][j] = ans;

    }

    // Time: O(M * N)
    // Space: O(M * N){2D DP array}
    private int solveByBottomUpDP(int m, int n, char[][] matrix) {

        int[][] dp = new int[m + 1][n + 1];
        int maxi = 0;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                int right = dp[i][j + 1];
                int diagonal = dp[i + 1][j + 1];
                int down = dp[i + 1][j];

                int ans = 0;

                if (matrix[i][j] == '1') {
                    ans = 1 + Math.min(right, Math.min(diagonal, down));
                    maxi = Math.max(maxi, ans);
                }

                dp[i][j] = ans;

            }
        }
        return (maxi * maxi);
    }

    // Time: O(M * N)
    // Space: O(2 * N){1D DP array}
    private int solveBySpaceOptimiseDP(int m, int n, char[][] matrix) {

        int[] forward = new int[n + 1];
        int maxi = 0;

        for (int i = m - 1; i >= 0; i--) {
            int[] curr = new int[n + 1];
            for (int j = n - 1; j >= 0; j--) {

                int right = curr[j + 1];
                int diagonal = forward[j + 1];
                int down = forward[j];

                int ans = 0;

                if (matrix[i][j] == '1') {
                    ans = 1 + Math.min(right, Math.min(diagonal, down));
                    maxi = Math.max(maxi, ans);
                }

                curr[j] = ans;

            }
            forward = curr;
        }
        return (maxi * maxi);
    }

    public int maximalSquare(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        // int[] maxi = new int[1];

        // solveByRecursion(0,0,m,n,matrix,maxi);

        // int[][] dp = new int[m][n];
        // for(int[] row : dp){
        // Arrays.fill(row, -1);
        // }
        // solveByTopDownDP(0,0,m,n,matrix,maxi,dp);
        // return (maxi[0] * maxi[0]);

        // return solveByBottomUpDP(m,n,matrix);

        return solveBySpaceOptimiseDP(m, n, matrix);

    }
}
