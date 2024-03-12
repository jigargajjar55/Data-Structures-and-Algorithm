package DynamicProgramming.LIS;
import java.util.*;

public class NumberOfLongestIncreasingSubsequence {

    // Time : O(N^2), Space: O(2 * N)
    public int NumberofLIS(int N, int arr[]) {
        int[] dp = new int[N];
        int[] count = new int[N];
        Arrays.fill(count, 1);
        Arrays.fill(dp, 1);

        int maxi = 1;

        for (int i = 0; i < N; i++) {

            for (int prev = 0; prev < i; prev++) {
                if (arr[prev] < arr[i] && 1 + dp[prev] > dp[i]) {
                    dp[i] = 1 + dp[prev];
                    // Inherit
                    count[i] = count[prev];
                } else if (arr[prev] < arr[i] && 1 + dp[prev] == dp[i]) {
                    // adding\
                    count[i] += count[prev];
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
            }
        }

        int numbers = 0;

        for (int i = 0; i < N; i++) {
            if (dp[i] == maxi) {

                numbers += count[i];

            }
        }

        return numbers;
    }
}