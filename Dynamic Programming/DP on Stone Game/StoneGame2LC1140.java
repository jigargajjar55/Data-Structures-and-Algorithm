import java.util.*;

public class StoneGame2LC1140 {

    // Time: O(Exponential), Space:(N){Aux. stack space}
    private int solveByRecursion(int index, int m, int isAlex, int n, int[] piles) {

        // Base Case
        if (index >= n) {
            return 0;
        }

        int score = isAlex == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int limit = Math.min(n, index + (2 * m));

        int currSum = 0;
        for (int i = index; i < limit; i++) {

            currSum += piles[i];

            int nextRecursion = solveByRecursion(i + 1, Math.max(m, i - index + 1), (isAlex ^ 1), n, piles);

            if (isAlex == 1) {
                score = Math.max(score, (currSum + nextRecursion));
            } else {
                score = Math.min(score, (nextRecursion));
            }
        }

        return score;
    }

    // Time: O(N * N * 4), Space:(N + N * N * 4){Aux. stack space and DP array}
    private int solveByTopDownDP(int index, int m, int isAlex, int n, int[] piles, int[][][] dp) {

        // Base Case
        if (index >= n) {
            return 0;
        }

        // Overlapping subproblem
        if (dp[index][m][isAlex] != -1) {
            return dp[index][m][isAlex];
        }

        int score = isAlex == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int limit = Math.min(n, index + (2 * m));

        int currSum = 0;
        for (int i = index; i < limit; i++) {

            currSum += piles[i];

            int nextRecursion = solveByTopDownDP(i + 1, Math.max(m, i - index + 1), (isAlex ^ 1), n, piles, dp);

            if (isAlex == 1) {
                score = Math.max(score, (currSum + nextRecursion));
            } else {
                score = Math.min(score, (nextRecursion));
            }
        }

        return dp[index][m][isAlex] = score;
    }

    public int stoneGameII(int[] piles) {

        int n = piles.length;

        // return solveByRecursion(0,1,1,n,piles);

        int[][][] dp = new int[n][2 * n][2];
        for (int i = 0; i < n; i++) {
            for (int[] row : dp[i]) {
                Arrays.fill(row, -1);
            }
        }

        return solveByTopDownDP(0, 1, 1, n, piles, dp);

    }
}