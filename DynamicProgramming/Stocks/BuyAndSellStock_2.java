package DynamicProgramming.Stocks;

public class BuyAndSellStock_2 {

    // Time: O(2^N), Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int index, int canBuy, int[] prices) {
        // Base case
        if (index == prices.length) {
            return 0;
        }

        int profit = 0;

        if (canBuy == 1) {
            profit = Math.max(-prices[index] + solveByRecursion(index + 1, 0, prices),
                    solveByRecursion(index + 1, 1, prices));
        } else {
            profit = Math.max(prices[index] + solveByRecursion(index + 1, 1, prices),
                    solveByRecursion(index + 1, 0, prices));
        }

        return profit;

    }

    // Time: O(N * 2), Space: O(N) + O(N * 2){Aux. Stack Space and 2D DP Array}
    private int solveBytopDownDP(int index, int canBuy, int[] prices, int[][] dp) {
        // Base case
        if (index == prices.length) {
            return 0;
        }

        if (dp[index][canBuy] != -1) {
            return dp[index][canBuy];
        }

        int profit = 0;

        if (canBuy == 1) {
            profit = Math.max(-prices[index] + solveBytopDownDP(index + 1, 0, prices, dp),
                    solveBytopDownDP(index + 1, 1, prices, dp));
        } else {
            profit = Math.max(prices[index] + solveBytopDownDP(index + 1, 1, prices, dp),
                    solveBytopDownDP(index + 1, 0, prices, dp));
        }

        return dp[index][canBuy] = profit;

    }

    // Time: O(N * 2), Space: O(N * 2){2D DP Array}
    private int solveByBottomUpDP(int n, int[] prices) {
        int[][] dp = new int[n + 1][2];

        dp[n][0] = 0;
        dp[n][1] = 0;

        for (int index = n - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy < 2; canBuy++) {

                int profit = 0;

                if (canBuy == 1) {
                    profit = Math.max(-prices[index] + dp[index + 1][0], dp[index + 1][1]);
                } else {
                    profit = Math.max(prices[index] + dp[index + 1][1], dp[index + 1][0]);
                }

                dp[index][canBuy] = profit;

            }
        }

        return dp[0][1];
    }

    // Time: O(N * 2), Space: O(2 * 2){1D DP Array}
    private int solveBySpaceOptimise(int n, int[] prices) {
        int[] forward = new int[2];

        forward[0] = 0;
        forward[1] = 0;

        for (int index = n - 1; index >= 0; index--) {
            int[] curr = new int[2];
            for (int canBuy = 0; canBuy < 2; canBuy++) {

                int profit = 0;

                if (canBuy == 1) {
                    profit = Math.max(-prices[index] + forward[0], forward[1]);
                } else {
                    profit = Math.max(prices[index] + forward[1], forward[0]);
                }

                curr[canBuy] = profit;

            }
            forward = curr;
        }

        return forward[1];
    }

    // Time: O(N), Space: O(4){Constant variables}
    private int solveBySpaceOptimiseMore(int n, int[] prices) {

        int forwardNotBuy = 0;
        int forwardBuy = 0;
        int currNotBuy = 0;
        int currBuy = 0;

        for (int index = n - 1; index >= 0; index--) {

            currBuy = Math.max(-prices[index] + forwardNotBuy, forwardBuy);
            currNotBuy = Math.max(prices[index] + forwardBuy, forwardNotBuy);

            forwardNotBuy = currNotBuy;
            forwardBuy = currBuy;

        }

        return forwardBuy;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        // return solveByRecursion(0,1,prices);

        // int[][] dp = new int[n][2];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveBytopDownDP(0,1,prices,dp);

        // return solveByBottomUpDP(n,prices);

        // return solveBySpaceOptimise(n,prices);

        return solveBySpaceOptimiseMore(n, prices);

    }
}
