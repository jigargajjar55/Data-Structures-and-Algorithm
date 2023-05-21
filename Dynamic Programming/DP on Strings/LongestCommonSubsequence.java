public class LongestCommonSubsequence {

    // Time : O((2 ^ M) * (2 ^ N)), Space: O(M + N){Aux. Stack space}
    private int solveByRecursion(int index1, int index2, String text1, String text2) {

        // Base case
        if (index1 < 0 || index2 < 0) {
            return 0;
        }

        if (text1.charAt(index1) == text2.charAt(index2)) {
            return 1 + solveByRecursion(index1 - 1, index2 - 1, text1, text2);
        }

        return Math.max(solveByRecursion(index1 - 1, index2, text1, text2),
                solveByRecursion(index1, index2 - 1, text1, text2));

    }

    // Time : O(M * N), Space: O(M + N) + O(M * N){Aux. Stack space and 2D DP Array}
    private int solveByTopDownDP(int index1, int index2, String text1, String text2, int[][] dp) {

        // Base case
        if (index1 == 0 || index2 == 0) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index1][index2] != -1) {
            return dp[index1][index2];
        }

        if (text1.charAt(index1 - 1) == text2.charAt(index2 - 1)) {
            return dp[index1][index2] = 1 + solveByTopDownDP(index1 - 1, index2 - 1, text1, text2, dp);
        }

        return dp[index1][index2] = Math.max(solveByTopDownDP(index1 - 1, index2, text1, text2, dp),
                solveByTopDownDP(index1, index2 - 1, text1, text2, dp));

    }

    // Time : O(M * N), Space: O(M * N){2D DP Array}
    private int solveByBottomUpDP(int n, int m, String text1, String text2) {

        int[][] dp = new int[n + 1][m + 1];
        // Analyze the base case
        // for (int i = 0; i <= n; i++) {
        // dp[i][0] = 0;
        // }
        // for (int j = 0; j <= m; j++) {
        // dp[0][j] = 0;
        // }

        for (int index1 = 1; index1 <= n; index1++) {
            for (int index2 = 1; index2 <= m; index2++) {

                if (text1.charAt(index1 - 1) == text2.charAt(index2 - 1)) {
                    dp[index1][index2] = 1 + dp[index1 - 1][index2 - 1];

                } else {
                    dp[index1][index2] = Math.max(dp[index1 - 1][index2], dp[index1][index2 - 1]);
                }

            }
        }

        return dp[n][m];

    }

    // Time : O(M * N), Space: O(2 * M){1D DP Array}
    private int solveBySpaceOptimise(int n, int m, String text1, String text2) {

        int[] prev = new int[m + 1];

        // for (int j = 0; j <= m; j++) {
        // prev[j] = 0;
        // }

        for (int index1 = 1; index1 <= n; index1++) {
            int[] curr = new int[m + 1];
            for (int index2 = 1; index2 <= m; index2++) {

                if (text1.charAt(index1 - 1) == text2.charAt(index2 - 1)) {
                    curr[index2] = 1 + prev[index2 - 1];

                } else {
                    curr[index2] = Math.max(prev[index2], curr[index2 - 1]);
                }

            }
            prev = curr;
        }

        return prev[m];

    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        // return solveByRecursion(n-1,m-1,text1,text2);

        // int[][] dp = new int[n+1][m+1];
        // for(int[] row : dp){
        // Arrays.fill(row, -1);
        // }

        // return solveByTopDownDP(n,m,text1,text2,dp);

        // return solveByBottomUpDP(n,m,text1,text2);

        return solveBySpaceOptimise(n, m, text1, text2);

    }
}