
import java.util.*;

public class SumofDistancesLC2615 {

    // Time : O(N + (1 * (N + N))), Space: O(N)
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];

        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!adj.containsKey(nums[i])) {
                adj.put(nums[i], new ArrayList<>());
            }
            adj.get(nums[i]).add(i);
        }

        for (int key : adj.keySet()) {
            List<Integer> indexes = adj.get(key);

            long totalSum = 0;
            for (int index : indexes) {
                totalSum += index;
            }

            long prevSum = 0;

            for (int i = 0; i < indexes.size(); i++) {
                int index = indexes.get(i);
                long postSum = totalSum - prevSum - index;

                result[index] += (index * (long) i);
                result[index] -= prevSum;
                result[index] += postSum;
                result[index] -= (index * (long) (indexes.size() - i - 1));

                prevSum += index;
            }

        }

        return result;

    }
}