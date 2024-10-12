package Algorithms.TwoPointers;
import java.util.*;

public class MinimizeMaximumPairSuminArray_LC1877 {

    // Time: O(N * Log N)
    // Space: O(1)
    /*
     * 
     * Intuition:
     * 
     * When faced with the challenge to minimize the maximum pair sum, the
     * instinctual approach might be to evenly distribute the weight of the numbers
     * across all pairs. The most direct threat to having a small maximum sum would
     * be the largest numbers in the array. If they are paired with other large
     * numbers, the resulting pair sum could be excessively large. By contrast,
     * pairing a large number with a small one could 'neutralize' its impact. Think
     * of it as a balance where you're trying to pair heavy items with light ones to
     * keep both sides as even as possible.
     * 
     * From this line of reasoning, one might deduce that pairing the largest number
     * with the smallest, the second-largest with the second-smallest, and so on,
     * would produce the most balanced pairs. This ensures that the maximum pair sum
     * is kept as small as possible.
     * 
     * 
     * 
     * 
     */
    public int minPairSum(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);

        int start = 0;
        int end = n - 1;
        int result = -1;

        while (start < end) {

            int pair = nums[start] + nums[end];

            if (result < pair) {
                result = pair;
            }

            start++;
            end--;
        }

        return result;

    }
}