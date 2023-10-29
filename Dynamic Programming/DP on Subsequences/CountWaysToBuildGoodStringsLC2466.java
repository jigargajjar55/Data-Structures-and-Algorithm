class CountWaysToBuildGoodStringsLC2466 {
    private int getAddMod(int a, int b) {
        return (((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007);
    }

    // Time: O(Exponential), Space: O(high){Aux. Stack Space}
    private int solveByRecursion(int target, int zero, int one) {
        // Base condition
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }

        int pickZero = solveByRecursion(target - zero, zero, one);
        int pickOne = solveByRecursion(target - one, zero, one);

        int ans = getAddMod(pickZero, pickOne);

        return ans;

    }

    // Time: O(high), Space: O(high) + O(high){Aux. Stack Space and DP Array}
    private int solveByTopDownDP(int target, int zero, int one, int[] dp) {
        // Base condition
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }

        // Overlapping subproblem
        if (dp[target] != -1) {
            return dp[target];
        }

        int pickZero = solveByTopDownDP(target - zero, zero, one, dp);
        int pickOne = solveByTopDownDP(target - one, zero, one, dp);

        dp[target] = getAddMod(pickZero, pickOne);

        return dp[target];

    }

    // Time: O(high), Space: O(high){DP Array}
    private int solveByBottomUpDP(int low, int high, int zero, int one) {

        int[] dp = new int[high + 1];
        dp[0] = 1;

        int result = 0;

        for (int i = 1; i <= high; i++) {
            int pickZero = 0;

            if (i - zero >= 0) {
                pickZero = dp[i - zero];
            }

            int pickOne = 0;

            if (i - one >= 0) {
                pickOne = dp[i - one];
            }
            dp[i] = getAddMod(pickZero, pickOne);

            if (i - low >= 0) {

                result = getAddMod(result, dp[i]);

            }

        }

        return result;

    }

    public int countGoodStrings(int low, int high, int zero, int one) {

        // int count = 0;
        // for(int target=low; target<=high; target++){
        // int ans = solveByRecursion(target, zero,one);
        // count = getAddMod(count, ans);
        // }
        // return count;

        // int[] dp = new int[high+1];
        // Arrays.fill(dp, -1);

        // for(int target=low; target<=high; target++){
        // int ans = solveByTopDownDP(target, zero,one,dp);
        // count = getAddMod(count, ans);
        // }
        // return count;

        return solveByBottomUpDP(low, high, zero, one);

    }
}