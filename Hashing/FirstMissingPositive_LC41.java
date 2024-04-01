package Hashing;

import java.util.*;

public class FirstMissingPositive_LC41 {

    // https://leetcode.com/problems/first-missing-positive/solutions/17214/java-simple-solution-with-documentation
    // Optimise approach
    // In-place algorithm
    // Time: O(N)
    // Space: O(1)
    public int firstMissingPositive(int[] nums) {

        int n = nums.length;
        // If each cell in the array were to contain positive integers only,
        // we can use the negative of the stored number as a flag to mark something (in
        // this case the flag indicates this index was found in some cell of the array)

        // 1. mark numbers (num < 0) and (num > n) with a special marker number (n+1)
        // (we can ignore those because if all number are > n then we'll simply return
        for (int i = 0; i < n; i++) {
            // Numbers greater then n can be ignored because the missing integer must be in
            // the range 1..n+1

            // First we will replace all negative,zero and >n values to n+1
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }

        // note: all number in the array are now positive, and on the range 1..n+1

        // 2. mark each cell appearing in the array, by converting the index for that
        // number to negative
        for (int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]);

            // If Index is >n, It bound to be <=0 or >n values, which is unnecessary
            if (idx > n) {
                continue;
            }
            // -1 for zero index based array (so the number 1 will be at pos 0)
            idx--;

            // prevents double negative operations
            if (nums[idx] > 0) {
                nums[idx] *= -1;
            }
        }

        // 3. find the first cell which isn't negative (doesn't appear in the array)
        for (int i = 0; i < n; i++) {

            // It means this index/number is not visited, So It is fist missing positive
            // number
            if (nums[i] > 0) {
                // Because 0-based indexing
                return i + 1;
            }
        }

        // 4. no positive numbers were found, which means the array contains all numbers
        // 1..n
        // And fist missing positive number will be n+1
        return n + 1;

    }

    // Brute-force Approach
    // Time: O(Max * N)
    // Space: O(1)
    public int firstMissingPositive1(int[] nums) {

        int result = -1;

        for (int num = 1; num <= Integer.MAX_VALUE; num++) {
            boolean isNotFound = true;
            for (int currNum : nums) {

                if (currNum == num) {
                    isNotFound = false;
                    break;
                }
            }

            if (isNotFound) {
                result = num;
                break;
            }
        }

        return result;

    }

    // Using HashSet approach
    // Time: O(N + Integer.MAX_VALUE)
    // Space: O(N)
    public int firstMissingPositive2(int[] nums) {

        int n = nums.length;
        Set<Integer> visited = new HashSet<>();

        for (int num : nums) {

            if (num > 0) {
                visited.add(num);
            }

        }

        int result = -1;
        for (int num = 1; num <= Integer.MAX_VALUE; num++) {

            if (!visited.contains(num)) {
                result = num;
                break;
            }
        }

        return result;

    }
}
