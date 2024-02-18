import java.util.*;

public class FindPolygonWiththeLargestPerimeter_LC2971 {

    // Time: O(N * log N)
    // Space: O(1)
    public long largestPerimeter(int[] nums) {

        int n = nums.length;

        if (n < 3) {
            return -1;
        }

        // Sort an array
        Arrays.sort(nums);
        long result = 0;

        long sum = nums[0];
        sum += nums[1];

        for (int i = 2; i < n; i++) {

            if (sum > nums[i]) {
                result = Math.max(result, sum + nums[i]);
            }
            sum += nums[i];
        }

        if (result == 0) {
            return -1;
        }

        return result;

    }
}
