package DynamicProgramming.PartitionAndMCM;
import java.util.*;

public class BurstBalloonsLC312 {

    // Time: O(Exponential), Space: O(N){Aux. Stack Space}
    private int solveByRecursion(int i, int j, ArrayList<Integer> list) {

        // Base case
        if (i > j) {
            return 0;
        }
        int maxi = Integer.MIN_VALUE;
        for (int index = i; index <= j; index++) {
            int cost = (list.get(i - 1) * list.get(index) * list.get(j + 1))
                    + solveByRecursion(i, index - 1, list)
                    + solveByRecursion(index + 1, j, list);
            maxi = Math.max(maxi, cost);
        }

        return maxi;

    }

    // Time: O(N * N * N), Space: O(N) + O(N * N){Aux. Stack Space and 2D DP array}
    private int solveByTopDownDP(int i, int j, ArrayList<Integer> list, int[][] dp) {

        // Base case
        if (i > j) {
            return 0;
        }
        // Overlapping sub-problem
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int maxi = Integer.MIN_VALUE;
        for (int index = i; index <= j; index++) {
            int cost = (list.get(i - 1) * list.get(index) * list.get(j + 1))
                    + solveByTopDownDP(i, index - 1, list, dp)
                    + solveByTopDownDP(index + 1, j, list, dp);
            maxi = Math.max(maxi, cost);
        }

        return dp[i][j] = maxi;

    }

    // Time: O(N * N * N), Space: O(N * N){2D DP array}
    private int solveByBottomUpDP(int n, ArrayList<Integer> list) {

        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i > 0; i--) {
            for (int j = 1; j <= n; j++) {

                if (i > j) {
                    continue;
                }

                int maxi = Integer.MIN_VALUE;
                for (int index = i; index <= j; index++) {
                    int cost = (list.get(i - 1) * list.get(index) * list.get(j + 1))
                            + dp[i][index - 1]
                            + dp[index + 1][j];
                    maxi = Math.max(maxi, cost);
                }

                dp[i][j] = maxi;

            }
        }

        return dp[1][n];

    }

    public int maxCoins(int[] nums) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        for (int num : nums) {
            list.add(num);
        }
        list.add(1);

        int n = nums.length;

        // return solveByRecursion(1,n,list);

        // int[][] dp = new int[n+1][n+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(1,n,list,dp);

        return solveByBottomUpDP(n, list);

    }
}
