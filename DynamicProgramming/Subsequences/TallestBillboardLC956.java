package DynamicProgramming.Subsequences;
import java.util.*;

public class TallestBillboardLC956 {

    /*
     * 
     * The problem can stated in other words as: Partition the given array of rod
     * lengths into 2 disjoint subsets such that the sum of both the subsets is same
     * and is the maximum.
     * Now the tricky part is that the 2 subsets may / may not cover all the rod
     * lengths, that is we might ignore some rod lengths.
     * 
     * Now such problems of partitioning the array into subsets actually are types
     * of 0-1 Knapsack problems. However unlike the classical 0-1 Knapsack, where at
     * each step we have 2 choices: either take element i or leave it, here we
     * actually have 3 choices:
     * 
     * Select rod for Positive Diff
     * Don't select rod(Negative Diff)
     * Ignore rod
     * 
     * Instead of using 3 parameters, we will use 2 only because of constraint. We
     * use difference between two subsets sum
     */

    // Time: O(3 ^ N), Space: O(N){Aux. Stack space}
    private int solveByRecursion(int index, int diff, int n, int[] rods) {

        // Base Case
        if (index == n) {
            if (diff == 0) {
                return 0;
            } else {
                return -(int) (1e9);
            }
        }

        int addRodToPositiveDiff = rods[index] + solveByRecursion(index + 1, diff + rods[index], n, rods);
        int addRodToNegativeDiff = solveByRecursion(index + 1, diff - rods[index], n, rods);
        int ignoreRod = solveByRecursion(index + 1, diff, n, rods);

        int ans = Math.max(ignoreRod, Math.max(addRodToPositiveDiff, addRodToNegativeDiff));
        return ans;
    }

    // Time: O(N * Sum), Space: O(N + (N * Sum)) {Aux. Stack Space and DP hashing}
    private int solveByTopDownDP(int index, int diff, int n, int[] rods, Map<String, Integer> dp) {

        // Base Case
        if (index == n) {
            if (diff == 0) {
                return 0;
            } else {
                return -(int) (1e9);
            }
        }
        String curr = index + "->" + diff;

        if (dp.containsKey(curr)) {
            return dp.get(curr);
        }

        int addRodToPositiveDiff = rods[index] + solveByTopDownDP(index + 1, diff + rods[index], n, rods, dp);
        int addRodToNegativeDiff = solveByTopDownDP(index + 1, diff - rods[index], n, rods, dp);
        int ignoreRod = solveByTopDownDP(index + 1, diff, n, rods, dp);

        int ans = Math.max(ignoreRod, Math.max(addRodToPositiveDiff, addRodToNegativeDiff));
        dp.put(curr, ans);

        return ans;

    }

    public int tallestBillboard(int[] rods) {
        int n = rods.length;

        // return solveByRecursion(0,0,n,rods);

        Map<String, Integer> dp = new HashMap<>();

        return solveByTopDownDP(0, 0, n, rods, dp);

    }
}
