
public class MatrixChainMultiplication {

    static int solveByRecursion(int i, int j, int[] arr) {
        // Base Case
        if (i == j) {
            return 0;
        }

        int mini = (int) (1e9);

        for (int k = i; k < j; k++) {

            int steps = (arr[i - 1] * arr[k] * arr[j]) + solveByRecursion(i, k, arr) + solveByRecursion(k + 1, j, arr);

            mini = Math.min(mini, steps);
        }

        return mini;

    }

    static int solveByTopDownDP(int i, int j, int[] arr, int[][] dp) {
        // Base Case
        if (i == j) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int mini = (int) (1e9);

        for (int k = i; k < j; k++) {

            int steps = (arr[i - 1] * arr[k] * arr[j]) + solveByTopDownDP(i, k, arr, dp)
                    + solveByTopDownDP(k + 1, j, arr, dp);

            mini = Math.min(mini, steps);
        }

        return dp[i][j] = mini;

    }

    static int solveByBottomUpDP(int n, int[] arr) {

        int[][] dp = new int[n][n];

        for (int i = n - 1; i > 0; i--) {
            for (int j = i + 1; j < n; j++) {

                int mini = (int) (1e9);

                for (int k = i; k < j; k++) {

                    int steps = (arr[i - 1] * arr[k] * arr[j]) + dp[i][k] + dp[k + 1][j];

                    mini = Math.min(mini, steps);
                }

                dp[i][j] = mini;

            }
        }

        return dp[1][n - 1];

    }

    static int matrixMultiplication(int N, int arr[]) {

        // return solveByRecursion(1,N-1,arr);

        // int[][] dp = new int[N][N];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(1,N-1,arr,dp);

        return solveByBottomUpDP(N, arr);
    }
}
