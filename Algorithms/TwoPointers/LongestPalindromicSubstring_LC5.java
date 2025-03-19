public class LongestPalindromicSubstring_LC5 {

    private void getPalindromeStr(String s, int left, int right, int len, int[] indexLen) {

        while ((left >= 0 && right < len) && (s.charAt(left) == s.charAt(right))) {

            int currLen = right - left + 1;

            if (currLen > indexLen[1]) {
                indexLen[0] = left;
                indexLen[1] = currLen;
            }

            left -= 1;
            right += 1;
        }

    }

    // Time: O(N ^ 2)
    // Space: O(1)
    public String longestPalindrome(String s) {

        int n = s.length();
        int[] indexLen = new int[2];

        for (int i = 0; i < n; i++) {

            // For Odd Length;
            getPalindromeStr(s, i, i, n, indexLen);

            // For Even Length;
            getPalindromeStr(s, i, i + 1, n, indexLen);

        }

        return s.substring(indexLen[0], indexLen[0] + indexLen[1]);

    }
}
