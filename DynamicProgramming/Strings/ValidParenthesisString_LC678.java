package DynamicProgramming.Strings;

public class ValidParenthesisString_LC678 {

    // Time: O(3 ^ N)
    // Space: O(N){Aux. Stack Space}
    private boolean solveByRecursion(int index, int openCount, int len, String s) {

        // Base Case
        if (openCount < 0) {
            return false;
        }
        if (index >= len) {
            return openCount == 0;
        }

        char currCh = s.charAt(index);
        boolean ans = false;

        if (currCh == '(') {
            ans = solveByRecursion(index + 1, openCount + 1, len, s);
        } else if (currCh == ')') {
            if (openCount > 0) {
                ans = solveByRecursion(index + 1, openCount - 1, len, s);
            } else {
                ans = false;
            }
        } else {

            boolean openBrace = solveByRecursion(index + 1, openCount + 1, len, s);
            boolean closeBrace = solveByRecursion(index + 1, openCount - 1, len, s);
            boolean emptyString = solveByRecursion(index + 1, openCount, len, s);

            ans = (openBrace || closeBrace || emptyString);
        }

        return ans;

    }

    // Time: O(N ^ 2)
    // Space: O(N + (N ^ 2)){Aux. Stack Space and 2D DP Array}
    private boolean solveByTopDownDP(int index, int openCount, int len, String s, int[][] dp) {

        // Base Case
        if (openCount < 0) {
            return false;
        }
        if (index >= len) {
            return openCount == 0;
        }

        // Overlapping-subproblem
        if (dp[index][openCount] != -1) {
            return dp[index][openCount] == 1;
        }

        char currCh = s.charAt(index);
        boolean ans = false;

        if (currCh == '(') {
            ans = solveByTopDownDP(index + 1, openCount + 1, len, s, dp);
        } else if (currCh == ')') {
            if (openCount > 0) {
                ans = solveByTopDownDP(index + 1, openCount - 1, len, s, dp);
            } else {
                ans = false;
            }
        } else {

            boolean openBrace = solveByTopDownDP(index + 1, openCount + 1, len, s, dp);
            boolean closeBrace = solveByTopDownDP(index + 1, openCount - 1, len, s, dp);
            boolean emptyString = solveByTopDownDP(index + 1, openCount, len, s, dp);

            ans = (openBrace || closeBrace || emptyString);
        }

        dp[index][openCount] = ans ? 1 : 0;

        return ans;

    }

    public boolean checkValidString(String s) {

        int n = s.length();

        // return solveByRecursion(0,0,n,s);

        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveByTopDownDP(0, 0, n, s, dp);

    }
}
