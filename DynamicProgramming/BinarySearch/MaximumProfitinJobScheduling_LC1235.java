package DynamicProgramming.BinarySearch;

import java.util.*;

class MaximumProfitinJobScheduling_LC1235 {
    private class Tuple {
        int start;
        int end;
        int profit;

        Tuple(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    private int binarySearch(int index, List<Tuple> jobs, int endTime) {
        int start = index;
        int end = jobs.size();

        while (start < end) {
            int mid = start + ((end - start) / 2);

            Tuple midTuple = jobs.get(mid);

            if (midTuple.start >= endTime) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    // Time: O((N * log N) + (Exponential))
    // Space: O(N){Aux. Stack space}
    private int solveByRecursion(int index, int n, List<Tuple> jobs) {

        // Base Case
        if (index >= n) {
            return 0;
        }

        int exclude = solveByRecursion(index + 1, n, jobs);
        Tuple job = jobs.get(index);

        // int nextJobIndex = n;
        // for(int j=index+1; j<n; j++){

        // //Non-overlapping job
        // if(job.end <= jobs.get(j).start){

        // nextJobIndex = j;
        // break;
        // }
        // }
        int nextJobIndex = binarySearch(index + 1, jobs, job.end);
        int include = job.profit + solveByRecursion(nextJobIndex, n, jobs);

        int result = Math.max(exclude, include);
        return result;

    }

    // Time: O((N * log N) + (N * log N))
    // Space: O(N + N){Aux. Stack space and 1D DP Array}
    private int solveByTopDownDP(int index, int n, List<Tuple> jobs, int[] dp) {

        // Base Case
        if (index >= n) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[index] != -1) {
            return dp[index];
        }

        int exclude = solveByTopDownDP(index + 1, n, jobs, dp);
        Tuple job = jobs.get(index);

        int nextJobIndex = binarySearch(index + 1, jobs, job.end);
        int include = job.profit + solveByTopDownDP(nextJobIndex, n, jobs, dp);

        int result = Math.max(exclude, include);
        return dp[index] = result;

    }

    // Time: O((N * log N) + (N * log N))
    // Space: O(N){1D DP Array}
    private int solveByBottomUpDP(int n, List<Tuple> jobs) {

        int[] dp = new int[n + 1];

        for (int index = n - 1; index >= 0; index--) {

            int exclude = dp[index + 1];
            Tuple job = jobs.get(index);

            int nextJobIndex = binarySearch(index + 1, jobs, job.end);
            int include = job.profit + dp[nextJobIndex];

            int result = Math.max(exclude, include);
            dp[index] = result;

        }

        return dp[0];

    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;
        List<Tuple> jobs = new ArrayList<>();
        for (int it = 0; it < n; it++) {
            jobs.add(new Tuple(startTime[it], endTime[it], profit[it]));
        }

        Collections.sort(jobs, (j1, j2) -> Integer.compare(j1.start, j2.start));

        // return solveByRecursion(0,n,jobs);

        // int[] dp = new int[n];
        // Arrays.fill(dp, -1);

        // return solveByTopDownDP(0,n,jobs,dp);

        return solveByBottomUpDP(n, jobs);

    }
}