package Patterns.PrefixSuffix;

import java.util.*;

public class ContiguousArray_LC525 {

    // Time: O(N)
    // Space: O(N)
    public int findMaxLength1(int[] nums) {

        int n = nums.length;
        int result = 0;
        int zeros = 0;
        int ones = 0;
        // (ones - zeros) -> index
        Map<Integer, Integer> map = new HashMap<>(); // Diff -> index

        for (int index = 0; index < n; index++) {

            if (nums[index] == 0) {
                zeros++;
            } else {
                ones++;
            }

            int diff = ones - zeros;

            if (!map.containsKey(diff)) {
                map.put(diff, index);
            }

            if (zeros == ones) {
                result = Math.max(result, (zeros + ones));
            } else {
                int idx = map.get(diff);
                result = Math.max(result, index - idx);
            }

        }

        return result;
    }

    // Using prefix-suffix pattern
    // Time: O(N)
    // Space: O(N)
    public int findMaxLength(int[] nums) {

        int n = nums.length;
        int maxi = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        // We will add 0
        prefixMap.put(0, -1);

        int[] arr = new int[n];
        // We will convert 0 to -1, to cancel-out element from the subarray
        for (int i = 0; i < n; i++) {
            int val = 1;
            if (nums[i] == 0) {
                val = -1;
            }
            arr[i] = val;
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (prefixMap.containsKey(sum)) {
                maxi = Math.max(maxi, i - prefixMap.get(sum));
            } else {

                // We have putting this into else block
                // Because we want length as max as possible

                prefixMap.put(sum, i);
            }
        }

        return maxi;

    }

}
