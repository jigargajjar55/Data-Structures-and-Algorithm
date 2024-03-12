package DynamicProgramming.Stocks;

class BuyAndSellStock_3 {

    // Time: O(Exponential), Space: O(N){Aux. stack space}
    private int solveByRecursion(int index, int canBuy, int cap, int[] prices) {
        // Base case
        if (cap == 0) {
            return 0;
        }
        if (index == prices.length) {
            return 0;
        }

        int profit = 0;
        if (canBuy == 1) {
            profit = Math.max(-prices[index] + solveByRecursion(index + 1, 0, cap, prices),
                    solveByRecursion(index + 1, 1, cap, prices));
        } else {
            profit = Math.max(prices[index] + solveByRecursion(index + 1, 1, cap - 1, prices),
                    solveByRecursion(index + 1, 0, cap, prices));
        }

        return profit;

    }

    // Time: O(N * 2 * 3), Space: O(N) + O(N * 2 * 3){Aux. stack space and 3D DP
    // Array}
    private int solveByTopDownDP(int index, int canBuy, int cap, int[] prices, int[][][] dp) {
        // Base case
        if (cap == 0) {
            return 0;
        }
        if (index == prices.length) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index][canBuy][cap] != -1) {
            return dp[index][canBuy][cap];
        }

        int profit = 0;
        if (canBuy == 1) {
            profit = Math.max(-prices[index] + solveByTopDownDP(index + 1, 0, cap, prices, dp),
                    solveByTopDownDP(index + 1, 1, cap, prices, dp));
        } else {
            profit = Math.max(prices[index] + solveByTopDownDP(index + 1, 1, cap - 1, prices, dp),
                    solveByTopDownDP(index + 1, 0, cap, prices, dp));
        }

        return dp[index][canBuy][cap] = profit;

    }

    // Time: O(N * 2 * 3), Space: O(N * 2 * 3){ 3D DP Array}
    private int solveByBottomUpDP(int n, int[] prices) {
        int[][][] dp = new int[n + 1][2][3];

        for (int index = n - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy < 2; canBuy++) {
                for (int cap = 1; cap < 3; cap++) {

                    int profit = 0;
                    if (canBuy == 1) {
                        profit = Math.max(-prices[index] + dp[index + 1][0][cap],
                                dp[index + 1][1][cap]);
                    } else {
                        profit = Math.max(prices[index] + dp[index + 1][1][cap - 1],
                                dp[index + 1][0][cap]);
                    }

                    dp[index][canBuy][cap] = profit;

                }
            }
        }

        return dp[0][1][2];

    }

    // Time: O(N * 2 * 3), Space: O(2 * 2 * 3){ 2D DP Array}
    private int solveBySpaceOptimise(int n, int[] prices) {
        int[][] forward = new int[2][3];

        for (int index = n - 1; index >= 0; index--) {
            int[][] curr = new int[2][3];
            for (int canBuy = 0; canBuy < 2; canBuy++) {
                for (int cap = 1; cap < 3; cap++) {

                    int profit = 0;
                    if (canBuy == 1) {
                        profit = Math.max(-prices[index] + forward[0][cap],
                                forward[1][cap]);
                    } else {
                        profit = Math.max(prices[index] + forward[1][cap - 1],
                                forward[0][cap]);
                    }

                    curr[canBuy][cap] = profit;
                }
            }
            forward = curr;
        }

        return forward[1][2];

    }

    // Time: O(Exponential), Space: O(N){Aux. stack space}
    private int solveByRecursionN4(int index, int trans, int[] prices) {
        // Base case
        if (index == prices.length || trans == 4) {
            return 0;
        }

        int profit = 0;
        if ((trans & 1) != 1) {
            profit = Math.max(-prices[index] + solveByRecursionN4(index + 1, trans + 1, prices),
                    solveByRecursionN4(index + 1, trans, prices));
        } else {

            profit = Math.max(prices[index] + solveByRecursionN4(index + 1, trans + 1, prices),
                    solveByRecursionN4(index + 1, trans, prices));
        }
        return profit;

    }

    // Time: O(N * 4), Space: O(N) + O(N * 4){Aux. stack space and 2D DP Array}
    private int solveByTopDownDPnN4(int index, int trans, int[] prices, int[][] dp) {
        // Base case
        if (index == prices.length || trans == 4) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index][trans] != -1) {
            return dp[index][trans];
        }

        int profit = 0;
        if ((trans & 1) != 1) {
            profit = Math.max(-prices[index] + solveByTopDownDPnN4(index + 1, trans + 1, prices, dp),
                    solveByTopDownDPnN4(index + 1, trans, prices, dp));
        } else {

            profit = Math.max(prices[index] + solveByTopDownDPnN4(index + 1, trans + 1, prices, dp),
                    solveByTopDownDPnN4(index + 1, trans, prices, dp));
        }
        return dp[index][trans] = profit;

    }

    // Time: O(N * 4), Space: O(N * 4){ 2D DP Array}
    private int solveByBottomUpDPN4(int n, int[] prices) {
        int[][] dp = new int[n + 1][5];

        for (int index = n - 1; index >= 0; index--) {
            for (int trans = 3; trans >= 0; trans--) {

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

    // Time: O(N * 4), Space: O(2 * 4){ 1D DP Array}
    private int solveBySpaceOptimiseN4(int n, int[] prices) {
        int[] ahead = new int[5];

        for (int index = n - 1; index >= 0; index--) {
            int[] curr = new int[5];
            for (int trans = 3; trans >= 0; trans--) {

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

    public int maxProfit(int[] prices) {

        // return solveByRecursion(0,1,2,prices);

        int n = prices.length;

        // int[][][] dp = new int[n][2][3];
        // for(int i=0; i<n; i++){
        // for(int[] row : dp[i]){
        // Arrays.fill(row,-1);
        // }
        // }
        // return solveByTopDownDP(0,1,2,prices,dp);

        // return solveByBottomUpDP(n,prices);

        // return solveBySpaceOptimise(n,prices);

        // return solveByRecursionN4(0,0,prices);

        // int[][] dp = new int[n][4];
        // for(int row[] : dp){
        // Arrays.fill(row,-1);
        // }

        // return solveByTopDownDPnN4(0,0,prices,dp);

        // return solveByBottomUpDPN4(n,prices);

        return solveBySpaceOptimiseN4(n, prices);

    }
}