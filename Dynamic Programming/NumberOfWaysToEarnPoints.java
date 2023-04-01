public class NumberOfWaysToEarnPoints {

    private int addwithMod(int a, int b) {
        return (((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007);
    }

    // Time : O(Exponential in nature), Space: O(Target) {Aux. Stack Space}
    private int solveByRecursion(int index, int target, int n, int[][] types) {

        // Base case
        if (index == n) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        int count = types[index][0];
        int marks = types[index][1];

        int noW = 0;

        for (int i = 0; i <= count; i++) {

            if ((i * marks) <= target) {
                noW = addwithMod(noW, solveByRecursion(index + 1, target - (i * marks), n, types));
            } else {
                break;
            }

        }

        return noW;

    }

    // Time : O(N * Target * N) ~ O( (N ^ 2) * Target), Space: O(Target) + O(N *
    // Target) {Aux. Stack Space
    // and
    // 2D DP Array}
    private int solveByTopDownDP(int index, int target, int n, int[][] types, int[][] dp) {

        // Base case
        if (index == n) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        // Overlapping sub-problem
        if (dp[index][target] != -1) {
            return dp[index][target];
        }

        int count = types[index][0];
        int marks = types[index][1];

        int noW = 0;

        for (int i = 0; i <= count; i++) {

            if ((i * marks) <= target) {
                noW = addwithMod(noW, solveByTopDownDP(index + 1, target - (i * marks), n, types, dp));
            } else {
                break;
            }

        }

        return dp[index][target] = noW;

    }

    // Time : O(N * Target * N) ~ O( (N ^ 2) * Target), Space: O(N * Target) {2D DP
    // Array}
    private int solveByBottomUpDP(int n, int target, int[][] types) {

        int[][] dp = new int[n + 1][target + 1];
        // analyse the base case
        dp[n][0] = 1;

        for (int index = n - 1; index >= 0; index--) {

            for (int k = 0; k <= target; k++) {

                int count = types[index][0];
                int marks = types[index][1];

                int noW = 0;

                for (int i = 0; i <= count; i++) {

                    if ((k - (i * marks)) >= 0) {
                        noW = addwithMod(noW, dp[index + 1][k - (i * marks)]);
                    } else {
                        break;
                    }

                }

                dp[index][k] = noW;

            }
        }

        return dp[0][target];
    }

    // Time : O(N * Target) ~ O( (N ^ 2) * Target), Space: O(2 * Target) {1D DP
    // Array}
    private int solveBySpaceOptimise(int n, int target, int[][] types) {

        int[] ahead = new int[target + 1];
        // analyse the base case
        ahead[0] = 1;

        for (int index = n - 1; index >= 0; index--) {
            int[] curr = new int[target + 1];

            for (int k = 0; k <= target; k++) {

                int count = types[index][0];
                int marks = types[index][1];

                int noW = 0;

                for (int i = 0; i <= count; i++) {

                    if ((k - (i * marks)) >= 0) {
                        noW = addwithMod(noW, ahead[k - (i * marks)]);
                    } else {
                        break;
                    }

                }

                curr[k] = noW;

            }
            ahead = curr;
        }

        return ahead[target];
    }

    public int waysToReachTarget(int target, int[][] types) {

        int n = types.length;

        // return solveByRecursion(0,target,n,types);

        // int[][] dp = new int[n][target+1];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }
        // return solveByTopDownDP(0,target,n,types,dp);

        // return solveByBottomUpDP(n,target,types);

        return solveBySpaceOptimise(n, target, types);

    }
}
