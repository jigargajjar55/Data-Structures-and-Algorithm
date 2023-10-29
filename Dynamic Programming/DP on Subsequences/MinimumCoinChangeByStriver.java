class MinimumCoinChangeByStriver {
    // Time: Exponential in nature, Space: ~O(amount)
    private int solveByRecursion(int index, int amount, int[] coins) {
        // Base case
        if (index == 0) {
            if ((amount % coins[index]) == 0) {
                return (amount / coins[index]);
            } else {
                return (int) (1e9);
            }
        }

        int exclude = solveByRecursion(index - 1, amount, coins);
        int include = (int) (1e9);

        if (coins[index] <= amount) {
            include = 1 + solveByRecursion(index, amount - coins[index], coins);
        }
        int ans = Math.min(exclude, include);

        return ans;
    }

    // Time: O(N * amount), Space: O(amount) + O(N * amount)
    private int solveByTopDownDP(int index, int amount, int[] coins, int[][] dp) {
        // Base case
        if (index == 0) {
            if ((amount % coins[index]) == 0) {
                return (amount / coins[index]);
            } else {
                return (int) (1e9);
            }
        }

        // Overlapping sub-problem
        if (dp[index][amount] != -1) {
            return dp[index][amount];
        }

        int exclude = solveByTopDownDP(index - 1, amount, coins, dp);
        int include = (int) (1e9);

        if (coins[index] <= amount) {
            include = 1 + solveByTopDownDP(index, amount - coins[index], coins, dp);
        }
        dp[index][amount] = Math.min(exclude, include);

        return dp[index][amount];
    }

    // Time: O(N * amount), Space: O(N * amount)
    private int solveByBottomUpDP(int n, int amount, int[] coins) {

        int[][] dp = new int[n][amount + 1];

        // Analyse Base case
        for (int i = 0; i <= amount; i++) {
            if ((i % coins[0]) == 0) {
                dp[0][i] = (i / coins[0]);
            } else {
                dp[0][i] = (int) (1e9);
            }
        }

        for (int index = 1; index < n; index++) {

            for (int target = 0; target <= amount; target++) {

                int exclude = dp[index - 1][target];
                int include = (int) (1e9);

                if (coins[index] <= target) {
                    include = 1 + dp[index][target - coins[index]];
                }
                dp[index][target] = Math.min(exclude, include);

            }
        }

        if (dp[n - 1][amount] == (int) (1e9)) {
            return -1;
        } else {
            return dp[n - 1][amount];
        }
    }

    // Time: O(N * amount), Space: O(2 * amount)
    private int solveBySpaceOptimise(int n, int amount, int[] coins) {

        int[] prev = new int[amount + 1];

        // Analyse Base case
        for (int i = 0; i <= amount; i++) {
            if ((i % coins[0]) == 0) {
                prev[i] = (i / coins[0]);
            } else {
                prev[i] = (int) (1e9);
            }
        }

        for (int index = 1; index < n; index++) {
            int[] curr = new int[amount + 1];

            for (int target = 0; target <= amount; target++) {

                int exclude = prev[target];
                int include = (int) (1e9);

                if (coins[index] <= target) {
                    include = 1 + curr[target - coins[index]];
                }
                curr[target] = Math.min(exclude, include);

            }
            prev = curr;
        }

        if (prev[amount] == (int) (1e9)) {
            return -1;
        } else {
            return prev[amount];
        }
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;

        // int ans = solveByRecursion(n-1,amount,coins);
        // if(ans >= (int)(1e9)){
        // return -1;
        // }else{
        // return ans;
        // }

        // int[][] dp = new int[n][amount+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // int ans = solveByTopDownDP(n-1,amount,coins,dp);
        // if(ans >= (int)(1e9)){
        // return -1;
        // }else{
        // return ans;
        // }

        // return solveByBottomUpDP(n,amount,coins);

        return solveBySpaceOptimise(n, amount, coins);

    }
}