package Strings;

public class PalindromicSubstrings_LC647 {

    private int getPalindromicSubStringCounts(String s, int i, int j) {
        int n = s.length();
        int count = 0;

        while ((i >= 0 && j < n) && (s.charAt(i) == s.charAt(j))) {
            count++;
            i--;
            j++;
        }

        return count;
    }

    // Time: O(N ^ 2)
    // Space: O(1)
    public int countSubstrings(String s) {

        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {

            // Consider ith index as middle char of palindrome string
            // We will have 2 ways, Either string is odd or even length;

            // For odd length
            count += getPalindromicSubStringCounts(s, i, i);

            // For even length
            count += getPalindromicSubStringCounts(s, i, i + 1);

        }

        return count;
    }
}
