package DynamicProgramming.LIS;
import java.util.*;

public class LongestIncreasingSubsequence {

    // Time: O(2 ^ N), Space : O(N){Aux. Stack Space}
    private int solveByRecursion(int index, int prevIndex, int n, int[] nums) {

        // Base case
        if (index == n) {
            return 0;
        }

        int len = solveByRecursion(index + 1, prevIndex, n, nums);

        if (prevIndex == -1 || nums[prevIndex] < nums[index]) {

            len = Math.max(len, 1 + solveByRecursion(index + 1, index, n, nums));

        }

        return len;

    }

    // Time: O(N * N), Space : O(N) + O(N * N){Aux. Stack Space and 2D DP Array}
    private int solveByTopDownDP(int index, int prevIndex, int n, int[] nums, int[][] dp) {

        // Base case
        if (index == n) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index][prevIndex + 1] != -1) {
            return dp[index][prevIndex + 1];
        }

        int len = solveByTopDownDP(index + 1, prevIndex, n, nums, dp);

        if (prevIndex == -1 || nums[prevIndex] < nums[index]) {

            len = Math.max(len, 1 + solveByTopDownDP(index + 1, index, n, nums, dp));

        }

        return dp[index][prevIndex + 1] = len;

    }

    // Time: O(N * N), Space : O(N * N){2D DP Array}
    private int solveByBottomUpDP(int n, int[] nums) {
        int[][] dp = new int[n + 1][n + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int prevIndex = index - 1; prevIndex >= -1; prevIndex--) {

                int len = dp[index + 1][prevIndex + 1];

                if (prevIndex == -1 || nums[prevIndex] < nums[index]) {

                    len = Math.max(len, 1 + dp[index + 1][index + 1]);

                }

                dp[index][prevIndex + 1] = len;

            }
        }

        return dp[0][0];
    }

    // Time: O(N * N), Space : O(2 * N){1D DP Array}
    private int solveBySpaceOptimise(int n, int[] nums) {
        int[] forward = new int[n + 1];

        for (int index = n - 1; index >= 0; index--) {
            int[] curr = new int[n + 1];
            for (int prevIndex = index - 1; prevIndex >= -1; prevIndex--) {

                int len = forward[prevIndex + 1];

                if (prevIndex == -1 || nums[prevIndex] < nums[index]) {

                    len = Math.max(len, 1 + forward[index + 1]);

                }

                curr[prevIndex + 1] = len;

            }
            forward = curr;
        }

        return forward[0];
    }

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;

        // return solveByRecursion(0,-1,n,nums);

        // int[][] dp = new int[n][n+1];
        // for(int[] row : dp){
        // Arrays.fill(row, -1);
        // }
        // return solveByTopDownDP(0,-1,n,nums,dp);

        // return solveByBottomUpDP(n, nums);

        return solveBySpaceOptimise(n, nums);

    }

    // LIS with BinarySearch, Time: O(N * log(N)), Space: O(N)
    static int getLowerBound(ArrayList<Integer> temp, int start, int end, int target) {

        while (start < end) {
            int mid = start + ((end - start) / 2);

            if (temp.get(mid) == target) {
                return mid;
            } else if (temp.get(mid) > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;

    }

    // Function to find length of longest increasing subsequence.
    static int longestSubsequence(int size, int a[]) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(a[0]);
        int len = 1;

        for (int i = 1; i < size; i++) {
            if (a[i] > temp.get(len - 1)) {
                temp.add(a[i]);
                len++;
            } else {
                int index = getLowerBound(temp, 0, len - 1, a[i]);

                temp.set(index, a[i]);

            }
        }

        return len;
    }
}
