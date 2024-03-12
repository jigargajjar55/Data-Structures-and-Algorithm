package DynamicProgramming.Stocks;

public class BuyAndSellStock_4 {

    // Time: O(Exponential), Space: O(N){Aux. stack space}
    private int solveByRecursion(int index, int trans, int[] prices, int n, int maxTrans) {
        // Base case
        if (index == n || trans == maxTrans) {
            return 0;
        }

        int profit = 0;
        if ((trans & 1) != 1) {
            profit = Math.max(-prices[index] + solveByRecursion(index + 1, trans + 1, prices, n, maxTrans),
                    solveByRecursion(index + 1, trans, prices, n, maxTrans));
        } else {

            profit = Math.max(prices[index] + solveByRecursion(index + 1, trans + 1, prices, n, maxTrans),
                    solveByRecursion(index + 1, trans, prices, n, maxTrans));
        }
        return profit;

    }

    // Time: O(N * 2K), Space: O(N) + O(N * 2K){Aux. stack space and 2D DP Array}
    private int solveByTopDownDP(int index, int trans, int[] prices, int n, int maxTrans, int[][] dp) {
        // Base case
        if (index == n || trans == maxTrans) {
            return 0;
        }
        // Overlapping sub-problem
        if (dp[index][trans] != -1) {
            return dp[index][trans];
        }

        int profit = 0;
        if ((trans & 1) != 1) {
            profit = Math.max(-prices[index] + solveByTopDownDP(index + 1, trans + 1, prices, n, maxTrans, dp),
                    solveByTopDownDP(index + 1, trans, prices, n, maxTrans, dp));
        } else {

            profit = Math.max(prices[index] + solveByTopDownDP(index + 1, trans + 1, prices, n, maxTrans, dp),
                    solveByTopDownDP(index + 1, trans, prices, n, maxTrans, dp));
        }
        return dp[index][trans] = profit;

    }

    // Time: O(N * 2K), Space: O(N * 2K){2D DP Array}
    private int solveByBottomUpDP(int n, int maxTrans, int[] prices) {
        int[][] dp = new int[n + 1][maxTrans + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int trans = maxTrans - 1; trans >= 0; trans--) {

                int profit = 0;
                if ((trans & 1) != 1) {
                    profit = Math.max(-prices[index] + dp[index + 1][trans + 1],
                            dp[index + 1][trans]);
                } else {

                    profit = Math.max(prices[index] + dp[index + 1][trans + 1],
                            dp[index + 1][trans]);
                }
                dp[index][trans] = profit;

            }
        }

        return dp[0][0];
    }

    // Time: O(N * 2K), Space: O(2 * 2K){1D DP Array}
    private int solveBySpaceOptimise(int n, int maxTrans, int[] prices) {
        int[] ahead = new int[maxTrans + 1];

        for (int index = n - 1; index >= 0; index--) {
            int[] curr = new int[maxTrans + 1];
            for (int trans = maxTrans - 1; trans >= 0; trans--) {

                int profit = 0;
                if ((trans & 1) != 1) {
                    profit = Math.max(-prices[index] + ahead[trans + 1],
                            ahead[trans]);
                } else {

                    profit = Math.max(prices[index] + ahead[trans + 1],
                            ahead[trans]);
                }
                curr[trans] = profit;

            }
            ahead = curr;
        }

        return ahead[0];
    }

    public int maxProfit(int k, int[] prices) {

        int n = prices.length;

        // return solveByRecursion(0,0,prices,n,2 * k);

        // int[][] dp = new int[n][2 * k];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(0,0,prices,n,2 * k,dp);

        // return solveByBottomUpDP(n,2 * k,prices);

        return solveBySpaceOptimise(n, 2 * k, prices);

    }
}