public class MinimumASCIIDeleteSumforTwoStrings_LC712 {

    /*

    https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/solutions/1892268/lcs-variation-c-approach-explained/
     * 
     * Approach
     * 
     * This question is just a variation of lcs as there we need to find out the
     * length,here we need to find out sum of their ascii values, just a slight
     * change is there i.e we need to add the ascii value of the character to
     * dp[i][j] when s1[i-1]==s2[j-1]
     * At last we have our ans which is basically the sum of ascii value of the
     * longest common subsequence.
     * Hence the result will be sum1(of chars of string s1) + sum2(of chars of
     * string s2) - 2*dp[n][m] which stores the sum of the LCS between 2 strings
     */

    // Time: O((2 ^ N) * (2 ^ M)), Space: O(Min(N,M)) {Aux. Stack Space}
    private int solveByRecursion(int i, int n, int j, int m, String s1, String s2) {

        // Base Case
        if (i == n || j == m) {
            return 0;
        }

        int ans = -(int) (1e9);
        if (s1.charAt(i) == s2.charAt(j)) {
            int sameChar = (int) (s1.charAt(i)) + solveByRecursion(i + 1, n, j + 1, m, s1, s2);
            ans = Math.max(sameChar, ans);
        }

        int notSameChar = Math.max(solveByRecursion(i + 1, n, j, m, s1, s2),
                solveByRecursion(i, n, j + 1, m, s1, s2));

        ans = Math.max(ans, notSameChar);
        return ans;
    }

    // Time: O(N * M), Space: O(Min(N,M) + (N * M)) {Aux. Stack Space and 2D DP
    // Array}
    private int solveByTopDownDP(int i, int n, int j, int m, String s1, String s2, int[][] dp) {

        // Base Case
        if (i == n || j == m) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = -(int) (1e9);
        if (s1.charAt(i) == s2.charAt(j)) {
            int sameChar = (int) (s1.charAt(i)) + solveByTopDownDP(i + 1, n, j + 1, m, s1, s2, dp);
            ans = Math.max(sameChar, ans);
        }

        int notSameChar = Math.max(solveByTopDownDP(i + 1, n, j, m, s1, s2, dp),
                solveByTopDownDP(i, n, j + 1, m, s1, s2, dp));

        ans = Math.max(ans, notSameChar);
        return dp[i][j] = ans;
    }

    // Time: O(N * M), Space: O(N * M) { 2D DP Array}
    private int solveByBottomUpDP(int n, int m, String s1, String s2) {

        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                int ans = -(int) (1e9);
                if (s1.charAt(i) == s2.charAt(j)) {
                    int sameChar = (int) (s1.charAt(i)) + dp[i + 1][j + 1];
                    ans = Math.max(sameChar, ans);
                }

                int notSameChar = Math.max(dp[i + 1][j], dp[i][j + 1]);

                ans = Math.max(ans, notSameChar);
                dp[i][j] = ans;

            }
        }

        return dp[0][0];
    }

    // Time: O(N * M), Space: O(2 * M) { 2 1D DP Array}
    private int solveBySpaceOptimise(int n, int m, String s1, String s2) {

        int[] ahead = new int[m + 1];

        for (int i = n - 1; i >= 0; i--) {
            int[] curr = new int[m + 1];
            for (int j = m - 1; j >= 0; j--) {

                int ans = -(int) (1e9);
                if (s1.charAt(i) == s2.charAt(j)) {
                    int sameChar = (int) (s1.charAt(i)) + ahead[j + 1];
                    ans = Math.max(sameChar, ans);
                }

                int notSameChar = Math.max(ahead[j], curr[j + 1]);

                ans = Math.max(ans, notSameChar);
                curr[j] = ans;

            }
            ahead = curr;
        }

        return ahead[0];
    }

    public int minimumDeleteSum(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += (int) (s1.charAt(i));
        }
        for (int i = 0; i < m; i++) {
            sum += (int) (s2.charAt(i));
        }

        // int result = (sum - (2 * solveByRecursion(0,n,0,m,s1,s2)));

        // int[][] dp = new int[n][m];
        // for(int[] row : dp){
        // Arrays.fill(row, -1);
        // }
        // int result = (sum - (2 * solveByTopDownDP(0,n,0,m,s1,s2,dp)));

        // int result = (sum - (2 * solveByBottomUpDP(n,m,s1,s2)));

        int result = (sum - (2 * solveBySpaceOptimise(n, m, s1, s2)));
        return result;

    }
}