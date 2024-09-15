package DynamicProgramming.LIS;
import java.util.*;

public class PrintLongestIncreasingSubsequence {

    // Time: O(N * N), Space: O(2 * N)
    public ArrayList<Integer> longestIncreasingSubsequence(int N, int arr[]) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        int[] dp = new int[N];
        int[] hash = new int[N];
        Arrays.fill(dp, 1);

        int maxi = 1;
        int lastIndex = 0;

        for (int i = 0; i < N; i++) {
            hash[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if (arr[prev] < arr[i] && 1 + dp[prev] > dp[i]) {
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIndex = i;
            }
        }

        result.add(arr[lastIndex]);

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            result.add(arr[lastIndex]);
        }

        // System.out.println(maxi);

        Collections.reverse(result);

        return result;
    }
}
