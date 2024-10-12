package Algorithms.TwoPointers;
public class MinimumLengthofStringAfterDeletingSimilarEnds_LC1750 {

    // Time: O(N)
    // Space: O(1)
    public int minimumLength(String s) {

        int n = s.length();

        int left = 0;
        int right = n - 1;

        while (left < right && s.charAt(left) == s.charAt(right)) {

            char ch = s.charAt(left);

            // Find the non-empty prefix
            while (left <= right && s.charAt(left) == ch) {
                left++;
            }

            // Find the non-empty suffix
            while (left <= right && s.charAt(right) == ch) {
                right--;
            }
        }

        int minLen = right - left + 1;

        return minLen;

    }
}
