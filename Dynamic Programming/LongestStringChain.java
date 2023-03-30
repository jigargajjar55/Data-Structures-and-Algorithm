import java.util.*;

public class LongestStringChain {

    private boolean isPossible(String s1, String s2) {

        if (s1.length() != s2.length() + 1) {
            return false;
        }

        int first = 0;
        int second = 0;

        while (first < s1.length()) {
            if (second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                first++;
                second++;

            } else {
                first++;
            }
        }

        if (first == s1.length() && second == s2.length()) {
            return true;
        } else {
            return false;
        }

    }

    // Time : O( ((N ^ 2) * Length of Word) + (N * logN)), Space: O(N)
    public int longestStrChain(String[] words) {

        int N = words.length;

        Arrays.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int maxi = 1;

        for (int i = 0; i < N; i++) {

            for (int prev = 0; prev < i; prev++) {

                if (isPossible(words[i], words[prev]) && dp[i] < 1 + dp[prev]) {
                    dp[i] = 1 + dp[prev];
                }

            }

            if (maxi < dp[i]) {
                maxi = dp[i];
            }
        }

        return maxi;

    }
}
