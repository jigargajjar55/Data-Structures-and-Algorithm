class SolvingQuestionsWithBrainpowerLC2140 {

    // Time: Exponentail(2 ^ N), Space:O(N){Aux. Stack Space} N: array length
    private long solveByRecursion(int index, int n, int[][] questions) {
        // Base conditiion
        if (index >= n) {
            return 0;
        }

        long exclude = solveByRecursion(index + 1, n, questions);
        int points = questions[index][0];
        int brainPower = questions[index][1];
        int nextIndex = Math.min(index + brainPower + 1, n);

        long include = (long) points + solveByRecursion(nextIndex, n, questions);

        // System.out.println(index+1 + " " + nextIndex);

        long ans = Math.max(exclude, include);

        return ans;

    }

    // Time: O(N), Space:O(N) + O(N){1D DP Array & Aux. Stack Space} N: array length
    private long solveByTopDownDP(int index, int n, int[][] questions, long[] dp) {
        // Base conditiion
        if (index >= n) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index] != -1) {
            return dp[index];
        }

        long exclude = solveByTopDownDP(index + 1, n, questions, dp);
        int points = questions[index][0];
        int brainPower = questions[index][1];
        int nextIndex = Math.min(index + brainPower + 1, n);

        long include = (long) points + solveByTopDownDP(nextIndex, n, questions, dp);

        // System.out.println(index+1 + " " + nextIndex);

        dp[index] = Math.max(exclude, include);

        return dp[index];

    }

    // Time: O(N), Space:O(N){1D DP Array} N: array length
    private long solveByBottomUpDP(int n, int[][] questions) {

        long[] dp = new long[n + 1];

        for (int index = n - 1; index >= 0; index--) {

            long exclude = dp[index + 1];
            int points = questions[index][0];
            int brainPower = questions[index][1];
            int nextIndex = Math.min(index + brainPower + 1, n);

            long include = 0;

            if (nextIndex < n + 1) {
                include = (long) points + dp[nextIndex];
            }

            dp[index] = Math.max(exclude, include);

        }
        return dp[0];

    }

    public long mostPoints(int[][] questions) {

        int n = questions.length;

        // return solveByRecursion(0,n,questions);

        // long[] dp = new long[n];
        // Arrays.fill(dp, -1);
        // return solveByTopDownDP(0,n,questions,dp);

        return solveByBottomUpDP(n, questions);

    }
}