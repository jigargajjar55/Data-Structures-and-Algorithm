package Array;

public class NumberofAdjacentElementsWithSameColorLC2672 {

    // Time: O(Q), Space: O(N)
    public int[] colorTheArray(int n, int[][] queries) {
        int[] nums = new int[n];
        int q = queries.length;
        int[] result = new int[q];
        int count = 0;

        for (int i = 0; i < q; i++) {
            int index = queries[i][0];
            int color = queries[i][1];

            // First Step: It will decrese the count as we are going to color current
            // position,
            // We will check for 2 adjacent positions.
            if (nums[index] != 0 && (index - 1 >= 0) && nums[index - 1] == nums[index]) {
                count--;
            }
            if (nums[index] != 0 && (index + 1 < n) && nums[index + 1] == nums[index]) {
                count--;
            }

            // Second Step: Now we will color current position in array
            nums[index] = color;

            // Third Step: It will increase the count if adjacent position have same color
            // We will check for 2 adjacent positions
            if ((index - 1 >= 0) && nums[index - 1] == nums[index]) {
                count++;
            }
            if ((index + 1 < n) && nums[index + 1] == nums[index]) {
                count++;
            }

            // We will store count in result array for query
            result[i] = count;

        }

        return result;

    }
}