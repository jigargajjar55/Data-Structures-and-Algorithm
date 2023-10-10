import java.util.*;

public class MaxDotProductOfTwoSubsequences_LC1458 {

    // Time: O(Exponential)
    // Space: O(Max(N, M)){Aux. Stack Space}
    private int solveByRescursion(int i, int j, int n, int m, int[] nums1, int[] nums2) {
        // Base Case
        if (i == n || j == m) {
            return -(int) (1e9);
        }

        int exclude = Math.max(solveByRescursion(i, j + 1, n, m, nums1, nums2),
                solveByRescursion(i + 1, j, n, m, nums1, nums2));

        int include = (nums1[i] * nums2[j]) + Math.max(solveByRescursion(i + 1, j + 1, n, m, nums1, nums2), 0);

        int ans = Math.max(exclude, include);
        return ans;

    }

    // Time: O(N * M)
    // Space: O(Max(N,M), (N * M)){Aux. Stack Space & 2D DP Array}
    private int solveByTopDownDP(int i, int j, int n, int m, int[] nums1, int[] nums2, int[][] dp) {
        // Base Case
        if (i == n || j == m) {
            return -(int) (1e9);
        }

        // Overlapping sub-problem
        if (dp[i][j] != -(int) (1e9)) {
            return dp[i][j];
        }

        int exclude = Math.max(solveByTopDownDP(i, j + 1, n, m, nums1, nums2, dp),
                solveByTopDownDP(i + 1, j, n, m, nums1, nums2, dp));

        int include = (nums1[i] * nums2[j]) + Math.max(solveByTopDownDP(i + 1, j + 1, n, m, nums1, nums2, dp), 0);

        int ans = Math.max(exclude, include);
        return dp[i][j] = ans;

    }

    // Time: O(N * M)
    // Space: O(N * M){2D DP Array}
    private int solveByBottomUpDP(int[] nums1, int n, int[] nums2, int m) {

        int[][] dp = new int[n + 1][m + 1];
        // Analyse the base case
        for (int i = 0; i <= n; i++) {
            dp[i][m] = -(int) (1e9);
        }
        for (int i = 0; i <= m; i++) {
            dp[n][i] = -(int) (1e9);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                int exclude = Math.max(dp[i][j + 1], dp[i + 1][j]);

                int include = (nums1[i] * nums2[j]) + Math.max(dp[i + 1][j + 1], 0);

                int ans = Math.max(exclude, include);
                dp[i][j] = ans;

            }
        }

        return dp[0][0];

    }

    // Time: O(N * M)
    // Space: O(2 * M){Two 1D DP Array}
    private int solveBySpaceOptimise(int[] nums1, int n, int[] nums2, int m) {

        int[] ahead = new int[m + 1];
        // Analyse the base case
        Arrays.fill(ahead, -(int) (1e9));

        for (int i = n - 1; i >= 0; i--) {

            int[] curr = new int[m + 1];
            curr[m] = -(int) (1e9);

            for (int j = m - 1; j >= 0; j--) {

                int exclude = Math.max(curr[j + 1], ahead[j]);

                int include = (nums1[i] * nums2[j]) + Math.max(ahead[j + 1], 0);

                int ans = Math.max(exclude, include);
                curr[j] = ans;

            }

            ahead = curr;
        }

        return ahead[0];

    }

    public int maxDotProduct(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        // return solveByRescursion(0,0,n,m,nums1,nums2);

        // int[][] dp = new int[n][m];
        // for(int[] row : dp){
        // Arrays.fill(row , -(int)(1e9));
        // }
        // return solveByTopDownDP(0,0,n,m,nums1,nums2,dp);

        // return solveByBottomUpDP(nums1,n,nums2,m);

        return solveBySpaceOptimise(nums1, n, nums2, m);
    }
}
