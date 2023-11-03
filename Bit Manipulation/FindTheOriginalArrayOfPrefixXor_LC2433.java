class FindTheOriginalArrayOfPrefixXor_LC2433 {

    // Time: O(N)
    // Space: O(1)
    public int[] findArray(int[] pref) {

        int n = pref.length;
        int[] originalArray = new int[n];

        originalArray[0] = pref[0];

        for (int i = 1; i < n; i++) {

            originalArray[i] = pref[i - 1] ^ pref[i];
        }

        return originalArray;

    }
}