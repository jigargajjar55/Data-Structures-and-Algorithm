package DynamicProgramming.Greedy;


import java.util.*;

public class MinimumCostToMakeArrayEqualLC2448 {

    // Time: O((N * log(N) + (3 * N)), Space: (3 * N)

    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] numsArray = new int[n][2];
        for (int i = 0; i < n; i++) {
            numsArray[i][0] = nums[i];
            numsArray[i][1] = cost[i];
        }

        // First sort in ascending order based on num
        Arrays.sort(numsArray, (n1, n2) -> Integer.compare(n1[0], n2[0]));

        // We will calculate prefix cost
        long[] leftToRight = new long[n + 1];
        long currSum = 0;
        for (int i = 0; i < n - 1; i++) {

            currSum += numsArray[i][1];

            leftToRight[i + 1] = leftToRight[i] + ((numsArray[i + 1][0] - numsArray[i][0]) * currSum);

        }

        // We will calculate suffix cost
        currSum = 0;
        long[] rightToLeft = new long[n + 1];
        for (int i = n - 1; i > 0; i--) {
            currSum += numsArray[i][1];

            rightToLeft[i - 1] = rightToLeft[i] + ((numsArray[i][0] - numsArray[i - 1][0]) * currSum);
        }
        long minCost = Long.MAX_VALUE;

        // Now we will loop trough it and get the minimum cost
        for (int i = 0; i < n; i++) {
            minCost = Math.min(minCost, leftToRight[i] + rightToLeft[i]);
        }

        return minCost;
    }
}