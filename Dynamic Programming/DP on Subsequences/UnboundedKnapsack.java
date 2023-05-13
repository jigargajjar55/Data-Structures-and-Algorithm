class UnboundedKnapsack {

    // Time : Exponential Time, Space: O(Cap)
    static int solveByRecursion(int index, int cap, int[] wt, int[] val) {

        // Base case
        if (index == 0) {
            if (cap >= wt[index]) {
                int value = val[index];

                int mul = cap / wt[index];

                return (mul * value);
            } else {
                return 0;
            }

        }

        int exclude = solveByRecursion(index - 1, cap, wt, val);
        int include = -(int) (1e9);

        if (wt[index] <= cap) {
            include = val[index] + solveByRecursion(index, cap - wt[index], wt, val);
        }

        int ans = Math.max(exclude, include);

        return ans;

    }

    // Time : O(N * cap), Space : O(cap) + O(N * cap){recursive stack space and DP
    // Array}
    static int solveByTopDownDP(int index, int cap, int[] wt, int[] val, int[][] dp) {

        // Base case
        if (index == 0) {
            if (wt[index] <= cap) {
                int value = val[index];

                int mul = cap / wt[index];

                return dp[index][cap] = (mul * value);
            } else {
                return dp[index][cap] = 0;
            }

        }

        // Overlapping sub-problem
        if (dp[index][cap] != -1) {
            return dp[index][cap];
        }

        int exclude = solveByTopDownDP(index - 1, cap, wt, val, dp);
        int include = -(int) (1e9);

        if (wt[index] <= cap) {
            include = val[index] + solveByTopDownDP(index, cap - wt[index], wt, val, dp);
        }

        dp[index][cap] = Math.max(exclude, include);

        return dp[index][cap];

    }

    // Time : O(N * cap), Space : O(N * cap){2D DP Array}
    static int solveByBottomUpDP(int n, int cap, int[] wt, int[] val) {

        int[][] dp = new int[n][cap + 1];

        for (int c = 0; c <= cap; c++) {
            if (wt[0] <= c) {

                int value = val[0];

                int mul = c / wt[0];

                dp[0][c] = (mul * value);

            } else {
                dp[0][c] = 0;
            }
        }

        for (int index = 1; index < n; index++) {
            for (int weight = 0; weight <= cap; weight++) {

                int exclude = dp[index - 1][weight];
                int include = -(int) (1e9);

                if (wt[index] <= weight) {
                    include = val[index] + dp[index][weight - wt[index]];
                }

                dp[index][weight] = Math.max(exclude, include);

            }
        }

        return dp[n - 1][cap];

    }

    // Time : O(N * cap), Space : O(cap){2 * 1D DP Array}
    static int solveBySpaceOptimise(int n, int cap, int[] wt, int[] val) {

        int[] prev = new int[cap + 1];

        for (int c = 0; c <= cap; c++) {
            if (wt[0] <= c) {

                int value = val[0];

                int mul = c / wt[0];

                prev[c] = (mul * value);

            } else {
                prev[c] = 0;
            }
        }

        for (int index = 1; index < n; index++) {
            int[] curr = new int[cap + 1];
            for (int weight = 0; weight <= cap; weight++) {

                int exclude = prev[weight];
                int include = -(int) (1e9);

                if (wt[index] <= weight) {
                    include = val[index] + curr[weight - wt[index]];
                }

                curr[weight] = Math.max(exclude, include);

            }
            prev = curr;
        }

        return prev[cap];

    }

    // Time : O(N * cap), Space : O(cap){1D DP Array}
    static int solveBySpaceOptimiseMore(int n, int cap, int[] wt, int[] val) {

        int[] prev = new int[cap + 1];

        for (int c = 0; c <= cap; c++) {
            if (wt[0] <= c) {

                int value = val[0];

                int mul = c / wt[0];

                prev[c] = (mul * value);

            } else {
                prev[c] = 0;
            }
        }

        for (int index = 1; index < n; index++) {

            for (int weight = 0; weight <= cap; weight++) {

                int exclude = prev[weight];
                int include = -(int) (1e9);

                if (wt[index] <= weight) {
                    include = val[index] + prev[weight - wt[index]];
                }

                prev[weight] = Math.max(exclude, include);

            }
        }

        return prev[cap];

    }

    static int knapSack(int N, int W, int val[], int wt[]) {
        // return solveByRecursion(N-1,W,wt,val);
        // int[][] dp = new int[N][W+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }

        // return solveByTopDownDP(N-1,W,wt,val,dp);

        // return solveByBottomUpDP(N,W,wt,val);

        // return solveBySpaceOptimise(N, W, wt, val);

        return solveBySpaceOptimiseMore(N, W, wt, val);
    }
}