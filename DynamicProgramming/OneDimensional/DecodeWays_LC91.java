package DynamicProgramming.OneDimensional;

public class DecodeWays_LC91 {

    // Time: O(2 ^ N){Exponential}
    // Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int index, int n, String s) {

        // Base Case
        if (index >= n) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }

        int oneDigit = solveByRecursion(index + 1, n, s);
        int twoDigit = 0;

        if ((index + 1) < n && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
            twoDigit = solveByRecursion(index + 2, n, s);
        }

        int ans = oneDigit + twoDigit;

        return ans;

    }

    // Time: O(N)
    // Space: O(N + N){Aux. Stack Space and 1D DP Array}
    private int solveByTopDownDP(int index, int n, String s, Integer[] dp) {

        // Base Case
        if (index >= n) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index] != null) {
            return dp[index];
        }

        int oneDigit = solveByTopDownDP(index + 1, n, s, dp);
        int twoDigit = 0;

        if ((index + 1) < n && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
            twoDigit = solveByTopDownDP(index + 2, n, s, dp);
        }

        int ans = oneDigit + twoDigit;

        return dp[index] = ans;

    }

    // Time: O(N)
    // Space: O(N){1D DP Array}
    private int solveByBottomUpDP(String s, int n) {

        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int index = n - 1; index >= 0; index--) {

            int oneDigit = dp[index + 1];
            int twoDigit = 0;

            if ((index + 1) < n && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
                twoDigit = dp[index + 2];
            }

            int ans = oneDigit + twoDigit;

            // Edge Case
            if (s.charAt(index) == '0') {
                ans = 0;
            }

            dp[index] = ans;

        }

        return dp[0];

    }

    public int numDecodings(String s) {

        int n = s.length();
        // int ans = solveByRecursion(0,n,s);

        // Integer[] dp = new Integer[n];

        // int ans = solveByTopDownDP(0,n,s,dp);

        int ans = solveByBottomUpDP(s, n);

        return ans;

    }
}
