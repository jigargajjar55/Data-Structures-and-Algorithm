import java.util.*;

/*
 * 
 * 
 * Intuition
    We have to count the number of ways in which we start from the 'start' city and want to finish at the 'finish' city, with the given condition that we can visit any city any number of times.
    The first thing that comes to mind whenever we have to get all possible ways to reach at some destination point, We have to think about Recursive Approach as it explore all possible ways.
    Once we achieve recursive solution, We will apply Memoization to optimize it.
    Complexity
    Time complexity: O((N * Fuel) * N) { There are N * Fuel states that top-down DP will explored and N for inner loop}
    Space complexity: O(Fuel + (N * Fuel)){Aux. Stack Space}
 */

class Solution {
    private int addMod(int a, int b) {
        return (((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007);
    }

    // Bruteforce DFS to get Recursive soltuion

    private int solveByRecursion(int start, int finish, int fuel, int n, int[] locations) {

        // Base Case
        if (fuel < 0) {
            return 0;
        }

        int count = 0;

        // If It reach to destination, We won't return 1. Instead we keep 1 as count and
        // continue to explore as city can be visited multiple time with fuel capacity.
        // we have to count all possible ways.
        if (start == finish) {
            count = 1;
        }

        // We loop through all location to explore all possibilities
        for (int index = 0; index < n; index++) {

            if (index != start) {
                int usedFuel = Math.abs(locations[start] - locations[index]);
                int ans = solveByRecursion(index, finish, fuel - usedFuel, n, locations);
                count = addMod(count, ans);
            }
        }

        return count;
    }

    // Apply Memoization using Top-Down DP approach
    private int solveByTopDownDP(int start, int finish, int fuel, int n, int[] locations, int[][] dp) {

        // Base Case
        if (fuel < 0) {
            return 0;
        }

        // Overlapping sub-problem
        if (dp[start][fuel] != -1) {
            return dp[start][fuel];
        }

        int count = 0;

        if (start == finish) {
            count = 1;
        }

        for (int index = 0; index < n; index++) {

            if (index != start) {
                int usedFuel = Math.abs(locations[start] - locations[index]);
                int ans = solveByTopDownDP(index, finish, fuel - usedFuel, n, locations, dp);
                count = addMod(count, ans);
            }
        }

        return dp[start][fuel] = count;
    }

    public int countRoutes(int[] locations, int start, int finish, int fuel) {

        int n = locations.length;
        // return solveByRecursion(start,finish,fuel,n,locations);

        int[][] dp = new int[n][fuel + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveByTopDownDP(start, finish, fuel, n, locations, dp);

    }
}