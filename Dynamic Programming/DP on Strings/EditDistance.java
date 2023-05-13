public class EditDistance {

    // Time: Exponential O(3^N * 3^M), Space: O(N + M){Aux. stack space}
    private int solveByRecursion(int i, String word1, int j, String word2) {
        // Base case
        if (i < 0) {
            return (j + 1);
        }
        if (j < 0) {
            return (i + 1);
        }

        if (word1.charAt(i) == word2.charAt(j)) {

            return solveByRecursion(i - 1, word1, j - 1, word2);

        } else {
            int insert = solveByRecursion(i, word1, j - 1, word2);
            int delete = solveByRecursion(i - 1, word1, j, word2);
            int replace = solveByRecursion(i - 1, word1, j - 1, word2);

            int minOperations = 1 + Math.min(insert, Math.min(delete, replace));

            return minOperations;

        }

    }

    // Time: O(N * M), Space: O(N + M) + O(N * M){Aux. stack space and 2D DP Array}
    private int solveByTopDownDP(int i, String word1, int j, String word2, int[][] dp) {
        // Base case
        if (i == 0) {
            return dp[i][j] = j;
        }
        if (j == 0) {
            return dp[i][j] = i;
        }

        // Overlapping sub-problem
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {

            return dp[i][j] = solveByTopDownDP(i - 1, word1, j - 1, word2, dp);

        } else {
            int insert = solveByTopDownDP(i, word1, j - 1, word2, dp);
            int delete = solveByTopDownDP(i - 1, word1, j, word2, dp);
            int replace = solveByTopDownDP(i - 1, word1, j - 1, word2, dp);

            dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));

            return dp[i][j];
        }

    }

    // Time: O(N * M), Space: O(N * M){2D DP Array}
    private int solveByBottomUpDP(int n, String word1, int m, String word2) {

        int[][] dp = new int[n + 1][m + 1];

        // Analyse base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[n][m];
    }

    // Time: O(N * M), Space: O(2 * M){1D DP Array}
    private int solveBySpaceOptimise(int n, String word1, int m, String word2) {

        int[] prev = new int[m + 1];
        prev[0] = 0;

        for (int j = 1; j <= m; j++) {
            prev[j] = j;
        }

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            curr[0] = i;
            for (int j = 1; j <= m; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {

                    curr[j] = prev[j - 1];

                } else {
                    int insert = curr[j - 1];
                    int delete = prev[j];
                    int replace = prev[j - 1];

                    curr[j] = 1 + Math.min(insert, Math.min(delete, replace));

                }

            }
            prev = curr;
        }

        return prev[m];

    }

    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // return solveByRecursion(n-1,word1,m-1,word2);

        // int[][] dp = new int[n+1][m+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(n,word1,m,word2,dp);

        // return solveByBottomUpDP(n,word1,m,word2);

        return solveBySpaceOptimise(n, word1, m, word2);

    }
}
