package DynamicProgramming.LIS;
import java.util.*;

public class MakeArrayStrictlyIncreasingLC1187 {

    // https://leetcode.com/problems/make-array-strictly-increasing/solutions/3646774/complete-explanation-recursion-top-down-easy-to-understand/

    // Time: O(log N)
    private int getUpperBound(int key, int[] arr, int m) {

        int start = 0;
        int end = m - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (key >= arr[mid]) {
                start = mid + 1;
            } else {
                ans = arr[mid];
                end = mid - 1;
            }
        }

        return ans;
    }

    // Exponential Time Complexity, Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int index, int prev, int n, int[] arr1, int m, int[] arr2) {

        // Base Case
        if (index >= n) {
            return 0;
        }
        int x = (int) (1e9), y = (int) (1e9), z = (int) (1e9);
        int newCurr = arr1[index], curr = arr1[index];

        if (prev >= newCurr) {
            newCurr = getUpperBound(prev, arr2, m);

            if (newCurr > prev) {
                x = solveByRecursion(index + 1, newCurr, n, arr1, m, arr2);

                if (x != (int) (1e9)) {
                    x++; // Considering current operation
                }
            }

        } else {
            // Exclude
            y = solveByRecursion(index + 1, curr, n, arr1, m, arr2);

            newCurr = getUpperBound(prev, arr2, m);

            if (newCurr > prev) {
                z = solveByRecursion(index + 1, newCurr, n, arr1, m, arr2);

                if (z != (int) (1e9)) {
                    z++;
                }
            }
        }

        int ans = Math.min(x, Math.min(y, z));

        return ans;
    }

    // Time: O((N ^ 2) * log N), Space: O(N + (N ^ 2)){Aux. Stack Space and DP Hash
    // Table}
    private int solveByTopDownDP(int index, int prev, int n, int[] arr1, int m, int[] arr2,
            Map<List<Integer>, Integer> dp) {

        // Base Case
        if (index >= n) {
            return 0;
        }
        List<Integer> currPair = new ArrayList<>();
        currPair.add(index);
        currPair.add(prev);

        if (dp.containsKey(currPair)) {
            return dp.get(currPair);
        }

        int x = (int) (1e9), y = (int) (1e9), z = (int) (1e9);
        int newCurr = arr1[index], curr = arr1[index];

        if (prev >= newCurr) {
            newCurr = getUpperBound(prev, arr2, m);

            if (newCurr > prev) {
                x = solveByTopDownDP(index + 1, newCurr, n, arr1, m, arr2, dp);

                if (x != (int) (1e9)) {
                    x++;
                }
            }

        } else {
            // Exclude
            y = solveByTopDownDP(index + 1, curr, n, arr1, m, arr2, dp);

            newCurr = getUpperBound(prev, arr2, m);

            if (newCurr > prev) {
                z = solveByTopDownDP(index + 1, newCurr, n, arr1, m, arr2, dp);

                if (z != (int) (1e9)) {
                    z++;
                }
            }
        }

        int ans = Math.min(x, Math.min(y, z));

        dp.put(currPair, ans);

        return ans;
    }

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {

        // First Step: Sort the array2
        Arrays.sort(arr2);
        int n = arr1.length;
        int m = arr2.length;

        // int ans = solveByRecursion(0,-1,n,arr1,m,arr2);

        Map<List<Integer>, Integer> dp = new HashMap<>();
        int ans = solveByTopDownDP(0, -1, n, arr1, m, arr2, dp);

        if (ans == (int) (1e9)) {
            return -1;
        } else {
            return ans;
        }

    }
}