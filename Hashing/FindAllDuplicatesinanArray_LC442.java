package Hashing;

import java.util.*;

public class FindAllDuplicatesinanArray_LC442 {

    // Optimise approach
    // Time: O(N)
    // Space: O(1)
    public List<Integer> findDuplicates(int[] nums) {

        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            int idx = Math.abs(nums[i]);
            idx--;

            if (nums[idx] < 0) {
                result.add(idx + 1);
            } else {
                nums[idx] = -nums[idx];
            }
        }

        return result;

    }

    // Using Sorting
    // Time: O(N * log N)
    // Space: O(1)
    public List<Integer> findDuplicates1(int[] nums) {

        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        if (n == 1) {
            return result;
        }

        Arrays.sort(nums);

        int prev = nums[0];

        for (int i = 1; i < n; i++) {

            if (prev == nums[i]) {
                result.add(nums[i]);
            }

            prev = nums[i];
        }

        return result;

    }

    // Using HashSet approach
    // Time: O(N)
    // Space: O(N)
    public List<Integer> findDuplicates2(int[] nums) {

        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < n; i++) {

            if (visited.contains(nums[i])) {
                result.add(nums[i]);
            } else {
                visited.add(nums[i]);
            }
        }

        return result;

    }

    // Using HashMap approach
    // Time: O(N)
    // Space: O(N)
    public List<Integer> findDuplicates3(int[] nums) {

        List<Integer> result = new ArrayList<Integer>();

        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

        for (int num : nums) {
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }

        for (int i : hmap.keySet()) {

            if (hmap.get(i) == 2) {
                result.add(i);
            }
        }

        return result;
    }
}