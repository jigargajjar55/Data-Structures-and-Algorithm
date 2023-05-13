import java.util.*;

public class LongestBitonicSubsequence {

    // Time : O( 2 * (N ^ 2) + N), Space: O(2 * N)
    public int LongestBitonicSequence(int[] nums) {
        int N = nums.length;

        int[] dp1 = new int[N];
        Arrays.fill(dp1, 1);

        for (int i = 0; i < N; i++) {

            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i] && 1 + dp1[prev] > dp1[i]) {
                    dp1[i] = 1 + dp1[prev];
                }
            }

        }

        int[] dp2 = new int[N];
        Arrays.fill(dp2, 1);

        for (int i = N - 1; i >= 0; i--) {

            for (int prev = N - 1; prev > i; prev--) {
                if (nums[prev] < nums[i] && 1 + dp2[prev] > dp2[i]) {
                    dp2[i] = 1 + dp2[prev];
                }
            }

        }

        int maxi = 0;

        for (int i = 0; i < N; i++) {
            maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
        }

        return maxi;
    }
}
