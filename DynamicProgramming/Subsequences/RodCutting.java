package DynamicProgramming.Subsequences;
class RodCutting {

    // Time : Exponential Time, Space: O(Cap)
    private int solveByRecursion(int index, int cap, int[] val) {

        // Base case
        if (index == 0) {

            return ((cap / (index + 1)) * val[index]);

        }

        int exclude = solveByRecursion(index - 1, cap, val);
        int include = -(int) (1e9);

        if ((index + 1) <= cap) {
            include = val[index] + solveByRecursion(index, cap - (index + 1), val);
        }

        int ans = Math.max(exclude, include);

        return ans;

    }

    // Time : O(N * cap), Space : O(cap) + O(N * cap){recursive stack space and DP
    // Array}
    private int solveByTopDownDP(int index, int cap, int[] val, int[][] dp) {

        // Base case
        if (index == 0) {

            return dp[index][cap] = ((cap / (index + 1)) * val[index]);
        }

        // Overlapping sub-problem
        if (dp[index][cap] != -1) {
            return dp[index][cap];
        }

        int exclude = solveByTopDownDP(index - 1, cap, val, dp);
        int include = -(int) (1e9);

        if ((index + 1) <= cap) {
            include = val[index] + solveByTopDownDP(index, cap - (index + 1), val, dp);
        }

        dp[index][cap] = Math.max(exclude, include);

        return dp[index][cap];

    }

    // Time : O(N * cap), Space : O(N * cap){2D DP Array}
    private int solveByBottomUpDP(int n, int cap, int[] val) {

        int[][] dp = new int[n][cap + 1];

        for (int c = 0; c <= cap; c++) {
            dp[0][c] = ((c / (0 + 1)) * val[0]);
        }

        for (int index = 1; index < n; index++) {
            for (int weight = 0; weight <= cap; weight++) {

                int exclude = dp[index - 1][weight];
                int include = -(int) (1e9);

                if ((index + 1) <= weight) {
                    include = val[index] + dp[index][weight - (index + 1)];
                }

                dp[index][weight] = Math.max(exclude, include);

            }
        }

        return dp[n - 1][cap];

    }

    // Time : O(N * cap), Space : O(cap){2 * 1D DP Array}
    private int solveBySpaceOptimise(int n, int cap, int[] val) {

        int[] prev = new int[cap + 1];

        for (int c = 0; c <= cap; c++) {
            prev[c] = ((c / (0 + 1)) * val[0]);
        }

        for (int index = 1; index < n; index++) {
            int[] curr = new int[cap + 1];
            for (int weight = 0; weight <= cap; weight++) {

                int exclude = prev[weight];
                int include = -(int) (1e9);

                if ((index + 1) <= weight) {
                    include = val[index] + curr[weight - (index + 1)];
                }

                curr[weight] = Math.max(exclude, include);

            }
            prev = curr;
        }

        return prev[cap];

    }

    // Time : O(N * cap), Space : O(cap){1D DP Array}
    private int solveBySpaceOptimiseMore(int n, int cap, int[] val) {

        int[] prev = new int[cap + 1];

        for (int c = 0; c <= cap; c++) {
            prev[c] = ((c / (0 + 1)) * val[0]);
        }

        for (int index = 1; index < n; index++) {

            for (int weight = 0; weight <= cap; weight++) {

                int exclude = prev[weight];
                int include = -(int) (1e9);

                if ((index + 1) <= weight) {
                    include = val[index] + prev[weight - (index + 1)];
                }

                prev[weight] = Math.max(exclude, include);

            }
        }

        return prev[cap];

    }

    public int cutRod(int price[], int n) {

        int len = price.length;

        // return solveByRecursion(len-1,n,price);

        // int[][] dp = new int[len][n+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }

        // return solveByTopDownDP(len-1,n,price,dp);

        // return solveByBottomUpDP(len,n,price);

        // return solveBySpaceOptimise(len,n,price);

        return solveBySpaceOptimiseMore(len, n, price);
    }
}