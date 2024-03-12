package DynamicProgramming.Subsequences;
public class PartitionswithGivenDifference {

    // Time: O(N) + O(2 ^ N), Space: O(N) {Recursive Stack space} N: Array length
    private int solveByRecursion(int[] arr, int index, int sum, int mod) {

        // Base case
        if (index == 0) {
            // For test case : [0,0,1]
            if (sum == 0 && arr[index] == 0) {
                return 2;
            }
            if (sum == 0 || arr[index] == sum) {
                return 1;
            }
            return 0;
        }

        int exclude = solveByRecursion(arr, index - 1, sum, mod);
        int include = 0;
        if (sum >= arr[index]) {
            include = solveByRecursion(arr, index - 1, sum - arr[index], mod);
        }
        int ans = (exclude + include) % mod;

        return ans;
    }

    // Time: O(N) + O(N * sum), Space: O(N) + O(N * sum){Recursive stack space and
    // 2D DP
    // Array}
    private int solveByTopDownDP(int[] arr, int index, int sum, int mod, int[][] dp) {

        // Base case
        if (index == 0) {
            if (sum == 0 && arr[index] == 0) {
                return dp[index][sum] = 2;
            }
            if (sum == 0 || arr[index] == sum) {
                return dp[index][sum] = 1;
            }
            return dp[index][sum] = 0;
        }

        // Overlapping sub-problem
        if (dp[index][sum] != -1) {
            return dp[index][sum];
        }

        int exclude = solveByTopDownDP(arr, index - 1, sum, mod, dp);
        int include = 0;
        if (sum >= arr[index]) {
            include = solveByTopDownDP(arr, index - 1, sum - arr[index], mod, dp);
        }
        dp[index][sum] = (exclude + include) % mod;

        return dp[index][sum];
    }

    // Time: O(N) + O(N * sum), Space: O(N * sum){2D DP Array}
    private int solveByBottomUpDP(int[] arr, int n, int sum, int mod) {

        int[][] dp = new int[n][sum + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        if (sum >= arr[0]) {
            dp[0][arr[0]] += 1;
        }

        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= sum; target++) {

                int exclude = dp[index - 1][target];
                int include = 0;
                if (target >= arr[index]) {
                    include = dp[index - 1][target - arr[index]];
                }

                dp[index][target] = (exclude + include) % mod;

            }
        }

        return dp[n - 1][sum];
    }

    // Time: O(N) + O(N * sum), Space: O(sum){1D DP Array}
    private int solveBySpaceOptimize(int[] arr, int n, int sum, int mod) {

        int[] prev = new int[sum + 1];
        prev[0] = 1;
        if (sum >= arr[0]) {
            prev[arr[0]] += 1;
        }

        for (int index = 1; index < n; index++) {

            int[] curr = new int[sum + 1];

            for (int target = 0; target <= sum; target++) {

                int exclude = prev[target];
                int include = 0;
                if (target >= arr[index]) {
                    include = prev[target - arr[index]];
                }

                curr[target] = (exclude + include) % mod;

            }
            prev = curr;
        }

        return prev[sum];
    }

    public int countPartitions(int n, int d, int arr[]) {
        int mod = (int) (1e9 + 7);
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }
        if (totalSum - d < 0 || ((totalSum - d) & 1) == 1) {
            return 0;
        }

        int target = (totalSum - d) / 2;

        // return solveByRecursion(arr,n-1,target,mod);

        // int[][] dp = new int[n][target+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(arr,n-1,target,mod,dp);

        // return solveByBottomUpDP(arr,n,target,mod);

        return solveBySpaceOptimize(arr, n, target, mod);
    }
}