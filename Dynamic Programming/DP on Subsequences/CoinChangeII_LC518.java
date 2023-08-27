public class CoinChangeII_LC518 {

    // Time: Exponential in nature, Space: ~ O(amount)
    private int solveByRecursion(int index, int amount, int[] coins) {
        // Base case
        if (index == 0) {
            if ((amount % coins[index]) == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        int exclude = solveByRecursion(index - 1, amount, coins);
        int include = 0;

        if (coins[index] <= amount) {
            include = solveByRecursion(index, amount - coins[index], coins);
        }
        int ans = exclude + include;

        return ans;
    }

    // Time: O(N * amount), Space: O(amount) + O(N * amount)
    private int solveByTopDownDP(int index, int amount, int[] coins, int[][] dp) {
        // Base case
        if (index == 0) {
            if ((amount % coins[index]) == 0) {
                return dp[index][amount] = 1;
            } else {
                return dp[index][amount] = 0;
            }
        }

        // Overlapping sub-problem
        if (dp[index][amount] != -1) {
            return dp[index][amount];
        }

        int exclude = solveByTopDownDP(index - 1, amount, coins, dp);
        int include = 0;

        if (coins[index] <= amount) {
            include = solveByTopDownDP(index, amount - coins[index], coins, dp);
        }
        dp[index][amount] = exclude + include;

        return dp[index][amount];
    }

    // Time: O(N * amount), Space: O(N * amount)
    private int solveByBottomUpDP(int n, int amount, int[] coins) {

        int[][] dp = new int[n][amount + 1];

        // Analyse Base case
        for (int i = 0; i <= amount; i++) {
            if ((i % coins[0]) == 0) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
            }
        }

        for (int index = 1; index < n; index++) {

            for (int target = 0; target <= amount; target++) {

                int exclude = dp[index - 1][target];
                int include = 0;

                if (coins[index] <= target) {
                    include = dp[index][target - coins[index]];
                }
                dp[index][target] = exclude + include;

            }
        }

        return dp[n - 1][amount];

    }

    // Time: O(N * amount), Space: O(2 * amount)
    private int solveBySpaceOptimise(int n, int amount, int[] coins) {

        int[] prev = new int[amount + 1];

        // Analyse Base case
        for (int i = 0; i <= amount; i++) {
            if ((i % coins[0]) == 0) {
                prev[i] = 1;
            } else {
                prev[i] = 0;
            }
        }

        for (int index = 1; index < n; index++) {
            int[] curr = new int[amount + 1];

            for (int target = 0; target <= amount; target++) {

                int exclude = prev[target];
                int include = 0;

                if (coins[index] <= target) {
                    include = curr[target - coins[index]];
                }
                curr[target] = exclude + include;

            }
            prev = curr;
        }

        return prev[amount];

    }

    public int change(int amount, int[] coins) {

        int n = coins.length;
        // return solveByRecursion(n-1,amount,coins);

        // int[][] dp = new int[n][amount+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(n-1,amount,coins,dp);

        // return solveByBottomUpDP(n,amount,coins);

        return solveBySpaceOptimise(n, amount, coins);

    }

}
