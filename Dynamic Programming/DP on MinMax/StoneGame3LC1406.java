import java.util.*;

public class StoneGame3LC1406 {

    // https://leetcode.com/problems/stone-game-iii/solutions/564896/java-2-solutions-minimax-bottom-up-dp-clean-code-o-n/
    // https://leetcode.com/problems/stone-game-iii/solutions/564266/c-python-easy-and-concise-solution-with-minimax-algorithm/

    private int solveByTopDownDP(int index, int isAlex, int n, int[] piles, int[][] dp) {

        // Base Case
        if (index >= n) {
            return 0;
        }

        // Overlapping subproblem
        if (dp[index][isAlex] != -1) {
            return dp[index][isAlex];
        }

        int score = isAlex == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int limit = Math.min(n, index + 3);

        int currSum = 0;
        for (int i = index; i < limit; i++) {

            currSum += piles[i];

            int nextRecursion = solveByTopDownDP(i + 1, (isAlex ^ 1), n, piles, dp);

            if (isAlex == 1) {
                score = Math.max(score, (currSum + nextRecursion));
            } else {
                score = Math.min(score, (nextRecursion));
            }

        }
        return dp[index][isAlex] = score;
    }

    public String stoneGameIII(int[] stoneValue) {

        int n = stoneValue.length;
        int totalValue = 0;
        for (int stone : stoneValue) {
            totalValue += stone;
        }
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int aliceValue = solveByTopDownDP(0, 1, n, stoneValue, dp);

        if (totalValue - aliceValue < aliceValue) {
            return "Alice";
        } else if (totalValue - aliceValue > aliceValue) {
            return "Bob";
        } else {
            return "Tie";
        }

    }
}