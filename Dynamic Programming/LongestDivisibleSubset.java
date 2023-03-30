import java.util.*;

public class LongestDivisibleSubset {

    // Time: O((N ^ 2) + N + (N * log (N)), Space:O(2 * N)
    public List<Integer> largestDivisibleSubset(int[] arr) {

        int N = arr.length;

        List<Integer> result = new ArrayList<Integer>();
        Arrays.sort(arr);

        int[] dp = new int[N];
        int[] hash = new int[N];
        Arrays.fill(dp, 1);

        int maxi = 1;
        int lastIndex = 0;

        for (int i = 0; i < N; i++) {
            hash[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if ((arr[i] % arr[prev] == 0) && 1 + dp[prev] > dp[i]) {
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
