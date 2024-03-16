package GreedyApproach;
import java.util.*;

class Job {
    int id, profit, deadline;

    Job(int x, int y, int z) {
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }
}

public class JobSequencingProblem {

    // Time : O((N * log(N)) + N + (N * k)), Space: O(maxDeadline)
    int[] JobScheduling(Job arr[], int n) {
        Arrays.sort(arr, (j1, j2) -> Integer.compare(j2.profit, j1.profit));
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, arr[i].deadline);
        }

        int[] schedule = new int[maxDeadline + 1];
        Arrays.fill(schedule, -1);

        int count = 0;
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            int currJobID = arr[i].id;
            int currDeadline = arr[i].deadline;
            int currProfit = arr[i].profit;

            for (int k = currDeadline; k > 0; k--) {
                if (schedule[k] == -1) {
                    count++;
                    maxProfit += currProfit;
                    schedule[k] = currJobID;
                    break;
                }
            }
        }

        int[] result = new int[2];
        result[0] = count;
        result[1] = maxProfit;

        return result;

    }
}
