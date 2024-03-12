package BitManipulation;

public class NeighboringBitwiseXORLC2683 {

    private boolean solveBruteForce(int[] derived) {

        // Time: O(2 * N), Space: O(2 * N)

        // We try to initialize first value 0 and explore all values in original array,
        // If we last element is satisfied with condition will return true;
        // We try for value 1 and do the same steps

        int n = derived.length;

        int[] original1 = new int[n];
        original1[0] = 0;

        for (int i = 0; i < n; i++) {
            if (i == n - 1) {

                if ((derived[i] ^ original1[i]) == original1[0]) {
                    return true;
                }

            } else {
                original1[i + 1] = derived[i] ^ original1[i];
            }
        }

        int[] original2 = new int[n];
        original2[0] = 1;

        for (int i = 0; i < n; i++) {
            if (i == n - 1) {

                if ((derived[i] ^ original2[i]) == original2[0]) {
                    return true;
                }

            } else {
                original2[i + 1] = derived[i] ^ original2[i];
            }
        }

        return false;

    }

    public boolean doesValidArrayExist(int[] derived) {

        // Time: O(N), Space:O(1)
        // https://leetcode.com/problems/neighboring-bitwise-xor/solutions/3522054/xor-of-all-java-python-c-simple-approach/
        int total = 0;

        for (int bit : derived) {
            total = total ^ bit;
        }

        return total == 0;

    }
}
