import java.util.*;

class MinimumCoinChange {

    // Time: Exponential in nature,
    private int solveByRecursion(int index, int amount, int[] coins) {
        // Base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return (int) (1e9);
        }

        int mini = (int) (1e9);
        for (int i = 0; i < coins.length; i++) {
            int ans = solveByRecursion(index - 1, amount - coins[i], coins);

            if (ans != (int) (1e9)) {
                mini = Math.min(mini, ans + 1);
            }
        }
        return mini;
    }

    // Time: O(N * amount), Space: O(amount) + O(amount){Recursive Stack space and
    // DP Array}
    private int solveByTopDownDP(int index, int amount, int[] coins, int[] dp) {
        // Base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return (int) (1e9);
        }

        // Overlapping SubProblem
        if (dp[amount] != -1) {
            return dp[amount];
        }

        int mini = (int) (1e9);
        for (int i = 0; i < coins.length; i++) {
            int ans = solveByTopDownDP(index - 1, amount - coins[i], coins, dp);

            if (ans != (int) (1e9)) {
                mini = Math.min(mini, ans + 1);
            }
        }
        return dp[amount] = mini;
    }

    // Time: O(N * amount), Space: O(amount){DP Array}
    private int solveByBottomUpDP(int n, int amount, int[] coins) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, (int) (1e9));
        dp[0] = 0;

        for (int target = 0; target <= amount; target++) {

            for (int i = 0; i < coins.length; i++) {
                if (((target - coins[i]) >= 0) && (dp[target - coins[i]] != (int) (1e9))) {

                    dp[target] = Math.min(dp[target], 1 + dp[target - coins[i]]);

                }
            }

        }
        if (dp[amount] == (int) (1e9)) {
            return -1;
        } else {
            return dp[amount];
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

        // int[] dp = new int[amount+1];
        // Arrays.fill(dp,-1);

        // int ans = solveByTopDownDP(n-1,amount,coins,dp);
        // if(ans >= (int)(1e9)){
        // return -1;
        // }else{
        // return ans;
        // }

        return solveByBottomUpDP(n, amount, coins);

    }
}