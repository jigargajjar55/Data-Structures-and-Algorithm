public class BuyAndSellStockWithCooldown {

    // Time: O(Exponential), Space: O(N){Aux. Stack space}
    private int solveByRecursion(int index, int canBuy, int[] prices, int n) {
        // Base case
        if (index >= n) {
            return 0;
        }

        int profit = 0;

        if (canBuy == 1) {
            profit = Math.max(-prices[index] + solveByRecursion(index + 1, 0, prices, n),
                    solveByRecursion(index + 1, 1, prices, n));
        } else {
            profit = Math.max(prices[index] + solveByRecursion(index + 2, 1, prices, n),
                    solveByRecursion(index + 1, 0, prices, n));
        }

        return profit;
    }

    // Time: O(N * 2), Space: O(N) + O(N * 2){Aux. Stack space and 2D DP Array}
    private int solveByTopDownDP(int index, int canBuy, int[] prices, int n, int[][] dp) {
        // Base case
        if (index >= n) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index][canBuy] != -1) {
            return dp[index][canBuy];
        }

        int profit = 0;

        if (canBuy == 1) {
            profit = Math.max(-prices[index] + solveByTopDownDP(index + 1, 0, prices, n, dp),
                    solveByTopDownDP(index + 1, 1, prices, n, dp));
        } else {
            profit = Math.max(prices[index] + solveByTopDownDP(index + 2, 1, prices, n, dp),
                    solveByTopDownDP(index + 1, 0, prices, n, dp));
        }

        return dp[index][canBuy] = profit;
    }

    // Time: O(N), Space: O(N * 2){ 2D DP Array}
    private int solveByBottomUpDP(int n, int[] prices) {

        int[][] dp = new int[n + 2][2];

        for (int index = n - 1; index >= 0; index--) {

            dp[index][1] = Math.max(-prices[index] + dp[index + 1][0], dp[index + 1][1]);

            dp[index][0] = Math.max(prices[index] + dp[index + 2][1], dp[index + 1][0]);

        }
        return dp[0][1];

    }

    // Time: O(N), Space: O(3 * 2){ 1D DP Array}
    private int solveBySpaceOptimise(int n, int[] prices) {

        int[] forward1 = new int[2];
        int[] forward2 = new int[2];

        for (int index = n - 1; index >= 0; index--) {
            int[] curr = new int[2];

            curr[1] = Math.max(-prices[index] + forward1[0], forward1[1]);

            curr[0] = Math.max(prices[index] + forward2[1], forward1[0]);

            forward2 = forward1;
            forward1 = curr;

        }
        return forward1[1];

    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        // return solveByRecursion(0,1,prices,n);

        // int[][] dp = new int[n][2];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(0,1,prices,n,dp);

        // return solveByBottomUpDP(n,prices);

        return solveBySpaceOptimise(n, prices);
    }
}
