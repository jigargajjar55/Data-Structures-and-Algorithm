package DynamicProgramming.Strings;
class DistinctSubsequences {

    // Time: O(2^N * 2^M), Space:O(N + M){Aux. stack space} N : Length of s string,
    // M: Length of t string
    private int solveByRecursion(int i, String s, int j, String t) {
        // Base case
        if (j < 0) {
            return 1;
        }
        if (i < 0) {
            return 0;
        }

        if (s.charAt(i) == t.charAt(j)) {
            return (solveByRecursion(i - 1, s, j - 1, t) + solveByRecursion(i - 1, s, j, t));
        } else {
            return solveByRecursion(i - 1, s, j, t);
        }

    }

    // Time: O(N * M), Space:O(N + M) + O(N * M) {Aux. stack space and 2D DP Array}
    private int solveByTopDownDP(int i, String s, int j, String t, int[][] dp) {
        // Base case
        if (j == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }

        // Overlapping Sub-problem
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s.charAt(i - 1) == t.charAt(j - 1)) {
            return dp[i][j] = (solveByTopDownDP(i - 1, s, j - 1, t, dp) + solveByTopDownDP(i - 1, s, j, t, dp));
        } else {
            return dp[i][j] = solveByTopDownDP(i - 1, s, j, t, dp);
        }

    }

    // Time: O(N * M), Space: O(N * M) {2D DP Array}
    private int solveByBottomUpDP(int sLength, String s, int tLength, String t) {

        int[][] dp = new int[sLength + 1][tLength + 1];
        // Analyse base case
        for (int i = 0; i <= sLength; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= tLength; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= sLength; i++) {
            for (int j = 1; j <= tLength; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }

        return dp[sLength][tLength];
    }

    // Time: O(N * M), Space: O(2 * M) {1D DP Array}
    private int solveBySpaceOptimise(int sLength, String s, int tLength, String t) {

        int[] prev = new int[tLength + 1];
        // Analyse base case
        prev[0] = 1;

        for (int i = 1; i <= sLength; i++) {
            int[] curr = new int[tLength + 1];
            curr[0] = 1;
            for (int j = 1; j <= tLength; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + prev[j];
                } else {
                    curr[j] = prev[j];
                }

            }
            prev = curr;
        }

        return prev[tLength];
    }

    // Time: O(N * M), Space: O(M) {1D DP Array}
    private int solveBySpaceOptimiseMore(int sLength, String s, int tLength, String t) {

        int[] prev = new int[tLength + 1];
        // Analyse base case
        prev[0] = 1;

        for (int i = 1; i <= sLength; i++) {
            for (int j = tLength; j > 0; j--) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    prev[j] = prev[j - 1] + prev[j];
                } else {
                    prev[j] = prev[j];
                }

            }
        }

        return prev[tLength];
    }

    public int numDistinct(String s, String t) {

        int n = s.length();
        int m = t.length();

        // return solveByRecursion(n-1,s,m-1,t);

        // int[][] dp = new int[n+1][m+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(n,s,m,t,dp);

        // return solveByBottomUpDP(n,s,m,t);

        // return solveBySpaceOptimise(n,s,m,t);

        return solveBySpaceOptimiseMore(n, s, m, t);

    }
}