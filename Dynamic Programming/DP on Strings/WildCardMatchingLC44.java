public class WildCardMatchingLC44 {

    // Time: Exponential, Space: O(N + M){aux. Stack Space, N: length of p string,
    // M: length of s string}
    private boolean solveByRecursion(int i, String p, int j, String s) {
        // Base case
        if (i < 0 && j < 0) {
            return true;
        }
        if (i < 0 && j >= 0) {
            return false;
        }
        if (i >= 0 && j < 0) {
            boolean flag = true;
            for (int it = 0; it <= i; it++) {
                if (p.charAt(it) != '*') {
                    flag = false;
                    break;
                }
            }
            return flag;
        }

        if (p.charAt(i) == s.charAt(j) || p.charAt(i) == '?') {
            return solveByRecursion(i - 1, p, j - 1, s);
        } else if (p.charAt(i) == '*') {
            return (solveByRecursion(i - 1, p, j, s) || solveByRecursion(i, p, j - 1, s));
        } else {
            return false;
        }
    }

    // Time: O(N * M), Space: O(N + M) + O(N * M){aux. Stack Space and 2D DP Array}
    private boolean solveBytopDownDP(int i, String p, int j, String s, int[][] dp) {
        // Base case
        if (i == 0 && j == 0) {
            dp[i][j] = 1;
            return true;
        }
        if (i == 0 && j > 0) {
            dp[i][j] = 0;
            return false;
        }
        if (i > 0 && j == 0) {
            boolean flag = true;
            for (int it = 1; it <= i; it++) {
                if (p.charAt(it - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[i][j] = flag ? 1 : 0;
            return flag;
        }

        // Overlapping sub-problem
        if (dp[i][j] != -1) {
            return dp[i][j] == 1;
        }

        if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
            boolean ans1 = solveBytopDownDP(i - 1, p, j - 1, s, dp);
            dp[i][j] = ans1 ? 1 : 0;
            return ans1;
        } else if (p.charAt(i - 1) == '*') {
            boolean ans2 = (solveBytopDownDP(i - 1, p, j, s, dp) || solveBytopDownDP(i, p, j - 1, s, dp));
            dp[i][j] = ans2 ? 1 : 0;
            return ans2;
        } else {
            dp[i][j] = 0;
            return false;
        }
    }

    // Time: O(N * M), Space: O(N * M){ 2D DP Array}
    private boolean solveByBottomUpDP(int n, String p, int m, String s) {

        boolean[][] dp = new boolean[n + 1][m + 1];
        // Analyse base case
        dp[0][0] = true;

        for (int j = 1; j <= m; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int it = 1; it <= i; it++) {
                if (p.charAt(it - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[i][0] = flag;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {

                    dp[i][j] = dp[i - 1][j - 1];

                } else if (p.charAt(i - 1) == '*') {

                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];

                } else {
                    dp[i][j] = false;
                }

            }
        }

        return dp[n][m];
    }

    // Time: O(N * (N + M)), Space: O(2 * M){ 1D DP Array}
    private boolean solveBySpaceOptimise(int n, String p, int m, String s) {

        boolean[] prev = new boolean[m + 1];
        // Analyse base case
        prev[0] = true;

        for (int j = 1; j <= m; j++) {
            prev[j] = false;
        }

        for (int i = 1; i <= n; i++) {
            boolean[] curr = new boolean[m + 1];

            boolean flag = true;
            for (int it = 1; it <= i; it++) {
                if (p.charAt(it - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            curr[0] = flag;

            for (int j = 1; j <= m; j++) {

                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {

                    curr[j] = prev[j - 1];

                } else if (p.charAt(i - 1) == '*') {

                    curr[j] = prev[j] || curr[j - 1];

                } else {
                    curr[j] = false;
                }

            }
            prev = curr;
        }

        return prev[m];

    }

    public boolean isMatch(String s, String p) {

        int n = p.length();
        int m = s.length();

        // return solveByRecursion(n-1,p,m-1,s);

        // int[][] dp = new int[n+1][m+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveBytopDownDP(n,p,m,s,dp);

        // return solveByBottomUpDP(n,p,m,s);

        return solveBySpaceOptimise(n, p, m, s);

    }
}