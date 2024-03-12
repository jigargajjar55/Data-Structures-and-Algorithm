package DynamicProgramming.Subsequences;
class ZeroOneKnapsack {

    // Time : O(2 ^ N), Space: O(N) {N: Array Length for Recursive Stack Space}
    static int solveByRecursion(int index, int[] wt, int[] val, int capacity) {
        // Base case
        if (index == 0) {
            if (wt[index] <= capacity) {
                return val[index];
            } else {
                return 0;
            }

        }

        int exclude = solveByRecursion(index - 1, wt, val, capacity);
        int include = -(int) (1e9);

        if (wt[index] <= capacity) {
            include = val[index] + solveByRecursion(index - 1, wt, val, capacity - wt[index]);
        }

        int ans = Math.max(exclude, include);

        return ans;

    }

    // Time : O(N * Capacity), Space: O(N) + O(N * Capacity) {N: Array Length for
    // Recursive Stack Space, 2D DP Array}
    static int solveByTopDownDP(int index, int[] wt, int[] val, int capacity, int[][] dp) {
        // Base case
        if (index == 0) {
            if (wt[index] <= capacity) {
                return dp[index][capacity] = val[index];
            } else {
                return dp[index][capacity] = 0;
            }

        }

        // Overlapping sub-problem
        if (dp[index][capacity] != -1) {
            return dp[index][capacity];
        }

        int exclude = solveByTopDownDP(index - 1, wt, val, capacity, dp);
        int include = -(int) (1e9);

        if (wt[index] <= capacity) {
            include = val[index] + solveByTopDownDP(index - 1, wt, val, capacity - wt[index], dp);
        }

        dp[index][capacity] = Math.max(exclude, include);

        return dp[index][capacity];

    }

    // Time : O(N * Capacity), Space: O(N * Capacity) {2D DP Array}
    static int solveByBottomUpDP(int n, int[] wt, int[] val, int capacity) {

        int[][] dp = new int[n][capacity + 1];
        for (int weight = wt[0]; weight <= capacity; weight++) {
            dp[0][weight] = val[0];
        }

        for (int index = 1; index < n; index++) {
            for (int cap = 0; cap <= capacity; cap++) {

                int exclude = dp[index - 1][cap];
                int include = -(int) (1e9);

                if (wt[index] <= cap) {
                    include = val[index] + dp[index - 1][cap - wt[index]];
                }

                dp[index][cap] = Math.max(exclude, include);
            }
        }

        return dp[n - 1][capacity];

    }

    // Time : O(N * Capacity), Space: O(2 * Capacity) {Couple of 1D DP Array}
    static int solveBySpaceOptimise(int n, int[] wt, int[] val, int capacity) {

        int[] prev = new int[capacity + 1];
        for (int weight = wt[0]; weight <= capacity; weight++) {
            prev[weight] = val[0];
        }

        for (int index = 1; index < n; index++) {
            int[] curr = new int[capacity + 1];
            for (int cap = 0; cap <= capacity; cap++) {

                int exclude = prev[cap];
                int include = -(int) (1e9);

                if (wt[index] <= cap) {
                    include = val[index] + prev[cap - wt[index]];
                }

                curr[cap] = Math.max(exclude, include);
            }
            prev = curr;
        }

        return prev[capacity];

    }

    // Time : O(N * Capacity), Space: O(Capacity) {1D DP Array}
    static int solveBySpaceOptimiseMore(int n, int[] wt, int[] val, int capacity) {

        int[] prev = new int[capacity + 1];
        for (int weight = wt[0]; weight <= capacity; weight++) {
            prev[weight] = val[0];
        }

        for (int index = 1; index < n; index++) {

            // From Right to Left, because it depends on previous row left side elements
            for (int cap = capacity; cap >= 0; cap--) {

                int exclude = prev[cap];
                int include = -(int) (1e9);

                if (wt[index] <= cap) {
                    include = val[index] + prev[cap - wt[index]];
                }

                prev[cap] = Math.max(exclude, include);
            }

        }

        return prev[capacity];

    }

    // Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[], int n) {
        // return solveByRecursion(n-1,wt,val,W);

        // int[][] dp = new int[n][W+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(n-1,wt,val,W,dp);

        // return solveByBottomUpDP(n,wt,val,W);

        // return solveBySpaceOptimise(n,wt,val,W);

        return solveBySpaceOptimiseMore(n, wt, val, W);
    }
}