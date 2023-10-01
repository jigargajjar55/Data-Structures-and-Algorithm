package Strings;

public class MakeStringaSubsequenceUsingCyclicIncrements_LC2825 {

    /*
     * Tip :
     * 1.Consider the indices and increment separately.
     * 2.create 2 pointers.
     * 3.If both str1[i] and str2[j] match or if incrementing str1[i] matches
         str2[j] we increase both pointers.
     * 
     */

    // Time: O(M){Length of String1}
    // Space: O(1)
    public boolean canMakeSubsequence(String str1, String str2) {

        int m = str1.length();
        int n = str2.length();
        // Edge Case
        if (m < n) {
            return false;
        }

        int ptr1 = 0;
        int ptr2 = 0;

        while (ptr1 < m && ptr2 < n) {
            char currChar = str1.charAt(ptr1);

            int nextCharIndex = ((int) (currChar - 'a') + 1) % 26;
            char nextChar = (char) (nextCharIndex + 'a');

            if (currChar == str2.charAt(ptr2) || nextChar == str2.charAt(ptr2)) {
                ptr2++;
            }
            ptr1++;
        }

        if (ptr2 == n) {
            return true;
        } else {
            return false;
        }

    }
}