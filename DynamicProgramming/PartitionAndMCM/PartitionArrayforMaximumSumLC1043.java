package DynamicProgramming.PartitionAndMCM;

public class PartitionArrayforMaximumSumLC1043 {

    // Time : O(Exponential), Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int index, int n, int k, int[] arr) {
        // Base Case
        if (index == n) {
            return 0;
        }

        int maxAns = Integer.MIN_VALUE;
        int maxi = Integer.MIN_VALUE;
        int len = 0;

        for (int j = index; j < Math.min(n, index + k); j++) {
            len++;
            maxi = Math.max(maxi, arr[j]);
            int sum = (maxi * len) + solveByRecursion(j + 1, n, k, arr);

            maxAns = Math.max(maxAns, sum);
        }

        return maxAns;
    }

    // Time : O(N * k), Space: O(N) + O(N){Aux. Stack Space and DP array}
    private int solveBytopDownDP(int index, int n, int k, int[] arr, int[] dp) {
        // Base Case
        if (index == n) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index] != -1) {
            return dp[index];
        }

        int maxAns = Integer.MIN_VALUE;
        int maxi = Integer.MIN_VALUE;
        int len = 0;

        for (int j = index; j < Math.min(n, index + k); j++) {
            len++;
            maxi = Math.max(maxi, arr[j]);
            int sum = (maxi * len) + solveBytopDownDP(j + 1, n, k, arr, dp);

            maxAns = Math.max(maxAns, sum);
        }

        return dp[index] = maxAns;
    }

    // Time : O(N * k), Space: O(N){DP array}
    private int solveByBottomUpDP(int n, int k, int[] arr) {

        int[] dp = new int[n + 1];

        // Analyse the base case
        dp[n] = 0;

        for (int index = n - 1; index >= 0; index--) {

            int maxAns = Integer.MIN_VALUE;
            int maxi = Integer.MIN_VALUE;
            int len = 0;

            for (int j = index; j < Math.min(n, index + k); j++) {
                len++;
                maxi = Math.max(maxi, arr[j]);
                int sum = (maxi * len) + dp[j + 1];

                maxAns = Math.max(maxAns, sum);
            }

            dp[index] = maxAns;

        }

        return dp[0];
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {

        int n = arr.length;

        // return solveByRecursion(0,n,k,arr);

        // int[] dp = new int[n];
        // Arrays.fill(dp,-1);
        // return solveBytopDownDP(0,n,k,arr,dp);

        return solveByBottomUpDP(n, k, arr);

    }
}