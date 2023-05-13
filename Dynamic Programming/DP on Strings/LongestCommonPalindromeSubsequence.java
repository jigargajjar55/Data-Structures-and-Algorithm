class LongestPalindromeSubsequence
{
     // Time : O(M * N), Space: O(M * N){2D DP Array}
    private int solveByBottomUpDP(int n, int m, String text1, String text2) {

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

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

        for (int j = 0; j <= m; j++) {
            prev[j] = 0;
        }

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

    public int longestPalinSubseq(String S)
    {
        StringBuilder sr = new StringBuilder(S);
        String revS = sr.reverse().toString();
        int n = S.length();
        int m = revS.length();
        
       // return solveByBottomUpDP(n,m,S,revS);
        
        return solveBySpaceOptimise(n,m,S,revS);
    }
}