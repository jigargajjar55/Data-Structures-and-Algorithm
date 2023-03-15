public class PartitionEqualSubsetSum {

    // Time : O(N){For getting sum} + O(2 ^ N), Space: O(N) {N : array length for
    // recursive stack space}
    static boolean solveByRecursion(int index, int target, int[] arr) {
        // Base case
        if (target == 0) {
            return true;
        }
        if (index == 0) {
            return target == arr[index];
        }

        boolean exclude = solveByRecursion(index - 1, target, arr);
        boolean include = false;

        if (target >= arr[index]) {
            include = solveByRecursion(index - 1, target - arr[index], arr);
        }

        return (exclude || include);

    }

    // Time : O(N){For getting sum} + O(N * target), Space: O(N) + O(N * target){N :
    // array length for
    // recursive stack space and 2D DP Array}
    static boolean solveByTopDownDP(int index, int target, int[] arr, int[][] dp) {
        // Base case
        if (target == 0) {
            return true;
        }
        if (index == 0) {
            boolean ans = target == arr[index];
            dp[index][target] = ans ? 1 : 0;
            return ans;
        }

        // Overlapping sub-problem
        if (dp[index][target] != -1) {
            return dp[index][target] == 1;
        }

        boolean exclude = solveByTopDownDP(index - 1, target, arr, dp);
        boolean include = false;
        if (target >= arr[index]) {
            include = solveByTopDownDP(index - 1, target - arr[index], arr, dp);
        }

        boolean ans = (exclude || include);

        dp[index][target] = ans ? 1 : 0;

        return ans;

    }

    // Time : O(N){For getting sum} + O(N * target), Space: O(N * target){2D DP
    // Array}
    static boolean solveByBottomUpDP(int N, int target, int[] arr) {

        boolean[][] dp = new boolean[N][target + 1];

        // Analyze Base Case
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }

        if (target >= arr[0]) {
            dp[0][arr[0]] = true;
        }

        // Now Do recurrence relation in form of loops

        for (int index = 1; index < N; index++) {

            for (int k = 1; k <= target; k++) {

                boolean exclude = dp[index - 1][k];
                boolean include = false;
                if (k >= arr[index]) {
                    include = dp[index - 1][k - arr[index]];
                }

                dp[index][k] = (exclude || include);

            }
        }

        return dp[N - 1][target];

    }

    // Time : O(N){For getting sum} + O(N * target), Space: O(target){1D DP Array}
    static boolean solveBySpaceOptimize(int N, int target, int[] arr) {

        boolean[] prev = new boolean[target + 1];

        // Analyze Base Case
        prev[0] = true;

        if (target >= arr[0]) {
            prev[arr[0]] = true;
        }

        // Now Do recurrence relation in form of loops

        for (int index = 1; index < N; index++) {

            boolean[] curr = new boolean[target + 1];
            curr[0] = true;

            for (int k = 1; k <= target; k++) {

                boolean exclude = prev[k];
                boolean include = false;
                if (k >= arr[index]) {
                    include = prev[k - arr[index]];
                }

                curr[k] = (exclude || include);

            }
            prev = curr;
        }

        return prev[target];

    }

    static int equalPartition(int N, int arr[]) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        // If sum is odd, it's not possible to find partition equal subset
        if ((sum & 1) == 1) {
            return 0;
        }

        int target = sum / 2;

        // if(solveByRecursion(N-1,target,arr)){
        // return 1;
        // }else{
        // return 0;
        // }

        // int[][] dp = new int[N][target+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // if(solveByTopDownDP(N-1,target,arr,dp)){
        // return 1;
        // }else{
        // return 0;
        // }

        // if(solveByBottomUpDP(N,target,arr)){
        // return 1;
        // }else{
        // return 0;
        // }

        if (solveBySpaceOptimize(N, target, arr)) {
            return 1;
        } else {
            return 0;
        }
    }
}
