package Patterns.PrefixSuffix;

import java.util.*;

public class SubarraySumEqualsK_LC560 {

    //Time: O(N){In worst case, hashmap will take O(N), so Time: O(N ^ 2)}
    //Space: O(N)
    public int subarraySum(int[] nums, int k) {

        int n = nums.length;
        int prefixSum = 0;
        int result = 0;
        Map<Integer, Integer> dp = new HashMap<>();

        // If any remaining sum is equal to zero
        dp.put(0, 1);

        for (int i = 0; i < n; i++) {

            prefixSum += nums[i];

            int remainingSum = prefixSum - k;

            if (dp.containsKey(remainingSum)) {
                result += dp.get(remainingSum);
            }

            dp.put(prefixSum, dp.getOrDefault(prefixSum, 0) + 1);

        }

        return result;

    }

}
