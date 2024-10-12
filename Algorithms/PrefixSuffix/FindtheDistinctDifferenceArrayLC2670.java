package Algorithms.PrefixSuffix;




import java.util.*;

public class FindtheDistinctDifferenceArrayLC2670 {

    public int[] distinctDifferenceArray(int[] nums) {

        // Time: O(2 * N), Space: O(2 * N)
        int n = nums.length;
        Map<Integer, Integer> prefix = new HashMap<>();
        Map<Integer, Integer> suffix = new HashMap<>();
        int[] result = new int[n];

        for (int num : nums) {
            suffix.put(num, suffix.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            prefix.put(nums[i], prefix.getOrDefault(nums[i], 0) + 1);

            int suffixCount = suffix.get(nums[i]);
            if (suffixCount == 1) {
                suffix.remove(nums[i]);
            } else {
                suffix.put(nums[i], suffix.get(nums[i]) - 1);
            }

            result[i] = prefix.size() - suffix.size();
        }

        return result;

        // Time: O(N ^ 2), Space: O(N * N)
        // int n = nums.length;
        // int[] result = new int[n];

        // for(int i=0; i<n; i++){
        // Set<Integer> prefix = new HashSet<>();
        // Set<Integer> suffix = new HashSet<>();

        // for(int j=0; j<=i; j++){
        // prefix.add(nums[j]);
        // }

        // for(int k=i+1; k<n; k++){
        // suffix.add(nums[k]);
        // }

        // result[i] = prefix.size() - suffix.size();
        // }

        // return result;

    }
}
