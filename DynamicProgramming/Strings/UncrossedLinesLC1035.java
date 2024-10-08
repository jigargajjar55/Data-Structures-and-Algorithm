package DynamicProgramming.Strings;
public class UncrossedLinesLC1035 {

    // Time: Exponential, Space: O(Min(M, N)),{Aux. Stack space, M: nums1 length, N:
    // nums2 length}
    private int solveByRecursion(int i, int j, int n, int m, int[] nums1, int[] nums2) {
        // Base condition
        if (i >= n || j >= m) {
            return 0;
        }

        int ans = 0;
        if (nums1[i] == nums2[j]) {
            ans = 1 + solveByRecursion(i + 1, j + 1, n, m, nums1, nums2);
        } else {
            ans = Math.max(solveByRecursion(i + 1, j, n, m, nums1, nums2),
                    solveByRecursion(i, j + 1, n, m, nums1, nums2));
        }

        return ans;
    }

    // Time: O(M * N), Space: O(M * N) + O(Min(M,N)){2D DP Array and Aux. Stack
    // space}
    private int solveByTopDownDP(int i, int j, int n, int m, int[] nums1, int[] nums2, int[][] dp) {
        // Base condition
        if (i >= n || j >= m) {
            return 0;
        }

        // Overlapping subproblem
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (nums1[i] == nums2[j]) {

            dp[i][j] = 1 + solveByTopDownDP(i + 1, j + 1, n, m, nums1, nums2, dp);

        } else {
            dp[i][j] = Math.max(solveByTopDownDP(i + 1, j, n, m, nums1, nums2, dp),
                    solveByTopDownDP(i, j + 1, n, m, nums1, nums2, dp));
        }

        return dp[i][j];
    }

    // Time: O(M * N), Space: O(M * N){2D DP Array}
    private int solveByBottomUp(int n, int m, int[] nums1, int[] nums2) {

        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                if (nums1[i] == nums2[j]) {

                    dp[i][j] = 1 + dp[i + 1][j + 1];

                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }

            }
        }

        return dp[0][0];
    }

    // Time: O(M * N), Space: O(2 * N){1D DP Array}
    private int solveBySpaceOptimise(int n, int m, int[] nums1, int[] nums2) {

        int[] ahead = new int[m + 1];

        for (int i = n - 1; i >= 0; i--) {

            int[] curr = new int[m + 1];

            for (int j = m - 1; j >= 0; j--) {

                if (nums1[i] == nums2[j]) {
                    curr[j] = 1 + ahead[j + 1];

                } else {
                    curr[j] = Math.max(ahead[j], curr[j + 1]);
                }

            }
            ahead = curr;
        }

        return ahead[0];
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        // return solveByRecursion(0,0,n,m,nums1,nums2);

        // int[][] dp = new int[n][m];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(0,0,n,m,nums1,nums2,dp);

        // return solveByBottomUp(n,m,nums1,nums2);

        return solveBySpaceOptimise(n, m, nums1, nums2);

    }
}
