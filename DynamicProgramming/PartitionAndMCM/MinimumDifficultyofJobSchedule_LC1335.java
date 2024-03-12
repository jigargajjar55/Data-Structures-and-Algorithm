package DynamicProgramming.PartitionAndMCM;

public class MinimumDifficultyofJobSchedule_LC1335 {

    // Time: O(Expenential)
    // Space: O(N){Aux. Stack space}
    private int solveByRecursion(int index, int days, int n, int[] jobDifficulty) {

        // Base Case
        if (days == 0 && index >= n) {
            return 0;
        }
        if ((days == 0) || (index >= n)) {
            return (int) (1e9);
        }

        int mini = (int) (1e9);
        int maxDifficulty = jobDifficulty[index];

        for (int i = index; i < n; i++) {

            maxDifficulty = Math.max(maxDifficulty, jobDifficulty[i]);

            int cost = solveByRecursion(i + 1, days - 1, n, jobDifficulty);
            if (cost != (int) (1e9)) {
                mini = Math.min(mini, maxDifficulty + cost);
            }

        }

        return mini;

    }

    // Time: O((N ^ 2) * Days)
    // Space: O(N + (N * Days)){Aux. Stack space and 2D DP Array}
    private int solveByTopDownDP(int index, int days, int n, int[] jobDifficulty, int[][] dp) {

        // Base Case
        if (days == 0 && index >= n) {
            return 0;
        }
        if ((days == 0) || (index >= n)) {
            return (int) (1e9);
        }

        // Overlapping sub-problem
        if (dp[index][days] != -1) {
            return dp[index][days];
        }

        int mini = (int) (1e9);
        int maxDifficulty = jobDifficulty[index];

        for (int i = index; i < n; i++) {

            maxDifficulty = Math.max(maxDifficulty, jobDifficulty[i]);

            int cost = solveByTopDownDP(i + 1, days - 1, n, jobDifficulty, dp);
            if (cost != (int) (1e9)) {
                mini = Math.min(mini, maxDifficulty + cost);
            }

        }

        return dp[index][days] = mini;

    }

    // Time: O((N ^ 2) * Days)
    // Space: O(N * Days){2D DP Array}
    private int solveByBottomUpDP(int days, int n, int[] jobDifficulty) {

        int[][] dp = new int[n + 1][days + 1];

        // Analyse the base case
        dp[n][0] = 0;

        for (int index = 0; index < n; index++) {
            dp[index][0] = (int) (1e9);
        }
        for (int day = 1; day <= days; day++) {
            dp[n][day] = (int) (1e9);
        }

        for (int index = n - 1; index >= 0; index--) {
            for (int day = 1; day <= days; day++) {

                int mini = (int) (1e9);
                int maxDifficulty = jobDifficulty[index];

                for (int i = index; i < n; i++) {

                    maxDifficulty = Math.max(maxDifficulty, jobDifficulty[i]);

                    int cost = dp[i + 1][day - 1];
                    if (cost != (int) (1e9)) {
                        mini = Math.min(mini, maxDifficulty + cost);
                    }
                }

                dp[index][day] = mini;
            }
        }

        return dp[0][days];

    }

    public int minDifficulty(int[] jobDifficulty, int d) {

        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        // return solveByRecursion(0,d,n,jobDifficulty);

        // int[][] dp = new int[n][d+1];
        // for(int[] row : dp){
        // Arrays.fill(row , -1);
        // }
        // return solveByTopDownDP(0,d,n,jobDifficulty,dp);

        return solveByBottomUpDP(d, n, jobDifficulty);

    }
}