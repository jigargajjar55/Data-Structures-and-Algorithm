public class SubsetSumEqualToTarget {

    // Time : O(2 ^ N), Space: O(N) {N : array length for recursive stack space}
    static boolean solveByRecursion(int index, int target, int[] arr) {
        // Base case
        if (target == 0) {
            return true;
        }
        if (index == 0) {
            return arr[index] == target;
        }

        boolean exclude = solveByRecursion(index - 1, target, arr);
        boolean include = false;

        if (target >= arr[index]) {
            include = solveByRecursion(index - 1, target - arr[index], arr);
        }

        return (include || exclude);
    }

    // Time : O(N * target), Space: O(N) + O(N * target){N : array length for
    // recursive stack space and 2D DP Array}
    static boolean solveByTopDownDP(int index, int target, int[] arr, int[][] dp) {
        // Base case
        if (target == 0) {
            return true;
        }
        if (index == 0) {
            boolean ans = arr[index] == target;
            dp[index][target] = ans ? 1 : 0;
            return ans;
        }

        if (dp[index][target] != -1) {
            return dp[index][target] == 1;
        }

        boolean exclude = solveByTopDownDP(index - 1, target, arr, dp);
        boolean include = false;

        if (target >= arr[index]) {
            include = solveByTopDownDP(index - 1, target - arr[index], arr, dp);
        }

        boolean ans = (include || exclude);

        dp[index][target] = ans ? 1 : 0;

        return ans;
    }

    // Time : O(N * target), Space: O(N * target){2D DP Array}
    static boolean solveByBottomUpDP(int n, int target, int[] arr) {
        // Initialize DP Array
        boolean[][] dp = new boolean[n][target + 1];

        // Analyse base cases
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (target >= arr[0]) {
            dp[0][arr[0]] = true;
        }

        for (int index = 1; index < n; index++) {
            for (int k = 1; k <= target; k++) {

                boolean include = false;
                if (k >= arr[index]) {
                    include = dp[index - 1][k - arr[index]];
                }

                boolean exclude = dp[index - 1][k];

                dp[index][k] = (include || exclude);

            }
        }

        return dp[n - 1][target];

    }

    // Time : O(N * target), Space: O(target){1D DP Array}
    static boolean solveBySpaceOptimize(int n, int target, int[] arr) {
        // Initialize DP Array
        boolean[] prev = new boolean[target + 1];

        // For Base case
        prev[0] = true;
        if (target >= arr[0]) {
            prev[arr[0]] = true;
        }

        for (int index = 1; index < n; index++) {
            boolean[] curr = new boolean[target + 1];
            curr[0] = true;
            for (int k = 1; k <= target; k++) {

                boolean include = false;
                if (k >= arr[index]) {
                    include = prev[k - arr[index]];
                }

                boolean exclude = prev[k];

                curr[k] = (include || exclude);

            }
            prev = curr;
        }

        return prev[target];

    }

    static Boolean isSubsetSum(int N, int arr[], int sum) {

        // return solveByRecursion(N-1,sum,arr);

        // int[][] dp = new int[N][sum+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }

        // return solveByTopDownDP(N-1,sum,arr,dp);

        // return solveByBottomUpDP(N, sum, arr);

        return solveBySpaceOptimize(N, sum, arr);
    }
}