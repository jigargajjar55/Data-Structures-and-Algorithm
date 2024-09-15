package BitManipulation;

public class LongestSubarrayWithMaximumBitwiseAND_LC2419 {

    // Time: O(N)
    // Space: O(1)
    public int longestSubarray(int[] nums) {

        int n = nums.length;
        int maxi = -1;

        for (int num : nums) {

            if (maxi < num) {
                maxi = num;
            }
        }

        int size = 0;
        int count = 0;

        for (int right = 0; right < n; right++) {

            if (nums[right] == maxi) {

                count += 1;

                if (count > size) {
                    size = count;
                }
            } else {
                count = 0;
            }
        }

        return size;

    }
}
