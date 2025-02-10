package Algorithms.PrefixSuffix;

import java.util.*;

public class CountSubarraysWithGivenXOR {

    // https://www.geeksforgeeks.org/problems/count-subarray-with-given-xor/1
    // Time: O(N)
    // Space: O(N)
    public long subarrayXor(int arr[], int k) {

        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        long result = 0;
        int prefix = 0;

        for (int i = 0; i < n; i++) {

            prefix ^= arr[i];

            result += map.getOrDefault(prefix ^ k, 0);

            map.put(prefix, map.getOrDefault(prefix, 0) + 1);

        }

        return result;

    }

}
