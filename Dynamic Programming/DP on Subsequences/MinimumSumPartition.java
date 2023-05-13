public class MinimumSumPartition {

    // Time : O(N * sum of elements in Array), Space: O(N * sum) {1D DP Array}
    private void solveByBottomUpDP(int[] arr, int sum, int n, boolean[][] dp) {
        // Analyze Base Case
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (sum >= arr[0]) {
            dp[0][arr[0]] = true;
        }

        // Now Do recurrence relation in form of loops

        for (int index = 1; index < n; index++) {

            for (int k = 1; k <= sum; k++) {

                boolean exclude = dp[index - 1][k];
                boolean include = false;
                if (k >= arr[index]) {
                    include = dp[index - 1][k - arr[index]];
                }

                dp[index][k] = (exclude || include);

            }
        }
    }

    public int minDifference(int arr[], int n) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        // boolean[][] dp = new boolean[n][sum + 1];
        // solveByBottomUpDP(arr,sum,n,dp);

        // int mini = (int)(1e9);

        // //dp[n-1][0,1,2,....sum]
        // for(int s1=0; s1<= sum/2; s1++){

        // if(dp[n-1][s1]){
        // mini = Math.min(mini, Math.abs( (sum - s1) - s1) );
        // }
        // }

        // Space Optimize approach
        // Time : O(N * sum of elements in Array), Space: O(sum) {1D DP Array}
        boolean[] prev = new boolean[sum + 1];
        // Analyze Base Case
        prev[0] = true;

        if (sum >= arr[0]) {
            prev[arr[0]] = true;
        }

        // Now Do recurrence relation in form of loops

        for (int index = 1; index < n; index++) {

            boolean[] curr = new boolean[sum + 1];
            curr[0] = true;

            for (int k = 1; k <= sum; k++) {

                boolean exclude = prev[k];
                boolean include = false;
                if (k >= arr[index]) {
                    include = prev[k - arr[index]];
                }

                curr[k] = (exclude || include);

            }
            prev = curr;
        }

        int mini = (int) (1e9);

        // dp[n-1][0,1,2,....sum]
        for (int s1 = 0; s1 <= sum / 2; s1++) {

            if (prev[s1]) {
                mini = Math.min(mini, Math.abs((sum - s1) - s1));
            }
        }

        return mini;

    }
}
