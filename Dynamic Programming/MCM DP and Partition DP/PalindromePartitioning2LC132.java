public class PalindromePartitioning2LC132 {

    private boolean isPalindrome(int i, int j, String s) {

        while (i < j) {

            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;

    }

    // Time : O(Exponetial), Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int i, int n, String s) {

        // Base case
        if (i == n) {
            return 0;
        }

        int mini = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j, s)) {
                int cost = 1 + solveByRecursion(j + 1, n, s);
                mini = Math.min(mini, cost);
            }
        }

        return mini;

    }

    // Time : O(N ^ 3), Space: O(N) + O(N){Aux. Stack Space and DP Array}
    private int solveByTopDownDP(int i, int n, String s, int[] dp) {

        // Base case
        if (i == n) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[i] != -1) {
            return dp[i];
        }

        int mini = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j, s)) {
                int cost = 1 + solveByTopDownDP(j + 1, n, s, dp);
                mini = Math.min(mini, cost);
            }
        }

        return dp[i] = mini;
    }

    // Time : O(N ^ 3), Space: O(N){DP Array}
    private int solveByBottomUpDP(int n, String s) {

        int[] dp = new int[n + 1];
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {

            int mini = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, s)) {
                    int cost = 1 + dp[j + 1];
                    mini = Math.min(mini, cost);
                }
            }

            dp[i] = mini;

        }

        return dp[0];

    }

    public int minCut(String s) {
        int n = s.length();

        // return (solveByRecursion(0,n,s) - 1);

        // int[] dp = new int[n];
        // Arrays.fill(dp,-1);
        // return (solveByTopDownDP(0,n,s,dp) - 1);

        return (solveByBottomUpDP(n, s) - 1);

    }
}
