package DynamicProgramming.OneDimensional;

import java.util.*;

public class MaximizeScoreAfterNOperationsLC1799 {

    private int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getGCD(b, a % b);
    }

    // Time: O(Exponential), Space:(Operation)
    private int solveByRecursion(int operation, int n, int m, int[] nums, boolean[] visited) {

        int maxi = 0;

        for (int i = 0; i < m - 1; i++) {

            if (visited[i]) {
                continue;
            }

            for (int j = i + 1; j < m; j++) {

                if (visited[j]) {
                    continue;
                }
                visited[i] = true;
                visited[j] = true;
                int currGCD = getGCD(nums[i], nums[j]);

                int ans = (operation * currGCD) + solveByRecursion(operation + 1, n, m, nums, visited);

                visited[i] = false;
                visited[j] = false;

                maxi = Math.max(maxi, ans);
            }
        }
        return maxi;
    }

    // Time: O(Operation * (m ^ 2)), Space:(Operation)
    private int solveByTopDownDP(int operation, int n, int m, int[] nums, List<Boolean> visited,
            Map<List<Boolean>, Integer> dp) {

        // Overlapping subproblem
        if (dp.containsKey(visited)) {
            return dp.get(visited);
        }

        int maxi = 0;

        for (int i = 0; i < m - 1; i++) {

            if (visited.get(i)) {
                continue;
            }

            for (int j = i + 1; j < m; j++) {

                if (visited.get(j)) {
                    continue;
                }
                visited.set(i, true);
                visited.set(j, true);
                int currGCD = getGCD(nums[i], nums[j]);

                int ans = (operation * currGCD) + solveByTopDownDP(operation + 1, n, m, nums, visited, dp);

                visited.set(i, false);
                visited.set(j, false);

                maxi = Math.max(maxi, ans);
            }
        }
        dp.put(visited, maxi);
        return maxi;
    }

    public int maxScore(int[] nums) {

        int m = nums.length;
        int n = (m / 2);
        List<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            visited.add(false);
        }

        // int ans = solveByRecursion(1,n,m,nums,visited);

        Map<List<Boolean>, Integer> dp = new HashMap<>();

        int ans = solveByTopDownDP(1, n, m, nums, visited, dp);

        return ans;

    }
}
