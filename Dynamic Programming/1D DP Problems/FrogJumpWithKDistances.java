
public class FrogJumpWithKDistances {
    private int minimizeCost(int arr[], int N, int K) {
        // Solve recursive way
        // Time : O(K^N) , Space: O(N)
        // return solveByRecursion(arr,N-1,K);

        // Solve using Top-Down Approach(Recursion + Memoization)
        // Time : O(N * K) , Space: O(N)(1D DP Array) + O(N)(Call stack space)
        // int[] dp = new int[N];
        // Arrays.fill(dp,-1);
        // return solveByMemo(arr,N-1,K,dp);

        // Solve using Bottom-Up Approach(Tabulation)
        return solveByTab(arr, N, K);

        // Solve it in more space Optimization is not possible because even if we take
        // list of K size, worst case will be K=N. So Space:O(N)

    }

    private int solveByTab(int[] arr, int N, int K) {

        int[] dp = new int[N];

        dp[0] = 0;

        for (int index = 1; index < N; index++) {

            int ans = Integer.MAX_VALUE;

            for (int j = 1; j <= K; j++) {

                if (index - j >= 0) {
                    int jump = Math.abs(arr[index] - arr[index - j]) + dp[index - j];
                    ans = Math.min(ans, jump);
                }
            }

            dp[index] = ans;
        }

        return dp[N - 1];

    }

    private int solveByMemo(int[] arr, int index, int K, int[] dp) {

        // Base condition
        if (index == 0) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index] != -1) {
            return dp[index];
        }

        int first = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= K; i++) {

            if (index - i >= 0) {
                first = Math.abs(arr[index] - arr[index - i]) + solveByMemo(arr, index - i, K, dp);
            }

            ans = Math.min(ans, first);
        }

        dp[index] = ans;

        return dp[index];

    }

    private int solveByRecursion(int[] arr, int index, int K) {

        // Base condition
        if (index == 0) {
            return 0;
        }

        int first = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= K; i++) {

            if (index - i >= 0) {
                first = Math.abs(arr[index] - arr[index - i]) + solveByRecursion(arr, index - i, K);
            }

            ans = Math.min(ans, first);
        }

        return ans;

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
