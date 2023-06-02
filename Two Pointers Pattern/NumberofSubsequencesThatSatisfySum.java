import java.util.*;

public class NumberofSubsequencesThatSatisfySum {

    private int getAddMod(int a, int b) {
        return (((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007);
    }

    // https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/solutions/3491455/day-401-brute-better-optimal-100-0ms-python-java-c-explained-approach/

    // Time : O(N * log N) + O(N) + O(log N), Space: O(N)

    public int numSubseq(int[] nums, int target) {

        Arrays.sort(nums);
        int n = nums.length;

        int[] pow = new int[n];
        pow[0] = 1;

        for (int i = 1; i < n; i++) {
            pow[i] = ((pow[i - 1] * 2) % 1_000_000_007);
        }
        int start = 0;
        int end = n - 1;
        int ans = 0;

        while (start <= end) {
            if (nums[start] + nums[end] > target) {
                end--;
            } else {

                ans = getAddMod(ans, pow[end - start]);
                start++;

            }
        }

        return ans;

    }
}
