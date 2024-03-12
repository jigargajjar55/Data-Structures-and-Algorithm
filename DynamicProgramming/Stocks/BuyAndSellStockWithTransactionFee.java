package DynamicProgramming.Stocks;

public class BuyAndSellStockWithTransactionFee {

    // Time: O(Exponential), Space:O(N){Aux. Stack Space}
    private int solveByRecursion(int index, int canBuy, int[] prices, int n, int fee) {
        // Base case
        if (index == n) {
            return 0;
        }

        int profit = 0;

        if (canBuy == 1) {
            profit = Math.max(-prices[index] - fee + solveByRecursion(index + 1, 0, prices, n, fee),
                    solveByRecursion(index + 1, 1, prices, n, fee));
        } else {
            profit = Math.max(prices[index] + solveByRecursion(index + 1, 1, prices, n, fee),
                    solveByRecursion(index + 1, 0, prices, n, fee));
        }

        return profit;
    }

    // Time: O(N * 2), Space: O(N) + O(N * 2){Aux. Stack Space and 2D DP Array}
    private int solveByTopDownDP(int index, int canBuy, int[] prices, int n, int fee, int[][] dp) {
        // Base case
        if (index == n) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index][canBuy] != -1) {
            return dp[index][canBuy];
        }

        int profit = 0;

        if (canBuy == 1) {
            profit = Math.max(-prices[index] - fee + solveByTopDownDP(index + 1, 0, prices, n, fee, dp),
                    solveByTopDownDP(index + 1, 1, prices, n, fee, dp));
        } else {
            profit = Math.max(prices[index] + solveByTopDownDP(index + 1, 1, prices, n, fee, dp),
                    solveByTopDownDP(index + 1, 0, prices, n, fee, dp));
        }

        return dp[index][canBuy] = profit;
    }

    // Time: O(N), Space: O(N * 2){Aux. Stack Space and 2D DP Array}
    private int solveByBottomDP(int n, int fee, int[] prices) {

        int[][] dp = new int[n + 1][2];

        for (int index = n - 1; index >= 0; index--) {

            dp[index][1] = Math.max(-prices[index] - fee + dp[index + 1][0], dp[index + 1][1]);

            dp[index][0] = Math.max(prices[index] + dp[index + 1][1], dp[index + 1][0]);

        }

        return dp[0][1];
    }

    // Time: O(N), Space: O(4){Constant}
    private int solveBySpaceOptimise(int n, int fee, int[] prices) {

        // int[] ahead = new int[2];
        int aheadBuy = 0;
        int aheadNotBuy = 0;
        int currBuy = 0;
        int currNotBuy = 0;

        for (int index = n - 1; index >= 0; index--) {

            // int[] curr = new int[2];

            currBuy = Math.max(-prices[index] - fee + aheadNotBuy, aheadBuy);

            currNotBuy = Math.max(prices[index] + aheadBuy, aheadNotBuy);

            aheadBuy = currBuy;
            aheadNotBuy = currNotBuy;

        }

        return aheadBuy;
    }

    public int maxProfit(int[] prices, int fee) {

        int n = prices.length;

        // return solveByRecursion(0,1,prices,n,fee);

        // int[][] dp = new int[n][2];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(0,1,prices,n,fee,dp);

        // return solveByBottomDP(n,fee,prices);

        return solveBySpaceOptimise(n, fee, prices);

    }
}