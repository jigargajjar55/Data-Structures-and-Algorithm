package DynamicProgramming.TwoDimensional;
import java.util.*;

public class CountAllValidPickupandDeliveryOptions_LC1359 {

    private long addMod(long a, long b) {
        return (((a % 1_000_000_007) + (b % 1_000_000_007)) % 1_000_000_007);
    }

    // Time: O(Exponential), Space: O(N){Aux. Stack space}
    private long solveByRecursion(int pick, int delivery) {

        // Base Case
        // It can't be negative
        if (pick < 0 || delivery < 0) {
            return 0;
        }
        // condition to insure we are first picking then delivering
        if (delivery < pick) {
            return 0;
        }
        // everything finishes return 1
        if (pick == 0 && delivery == 0) {
            return 1;
        }

        long count = 0;

        // For Packing: if there are pick number of orders we have [pick]C1 = pick number of choices
        // for selecting and we have picked one so we do pick-1
        count = addMod(count, (pick) * solveByRecursion(pick - 1, delivery));

        // For Delivering : the number of items we have picked but not delivered is
        // (del-pick) so we can select one item from these to deliver 
        // [del-pick]C1 = del-pick and we have delivered so del-1
        count = addMod(count, (delivery - pick) * solveByRecursion(pick, delivery - 1));

        return count;
    }

    // Time: O(N * N), Space: O(N + (N * N)){Aux. Stack space and 2D DP Array}
    private long solveByTopDownDP(int pick, int delivery, long[][] dp) {

        // Base Case
        if (pick < 0 || delivery < 0) {
            return 0;
        }
        if (delivery < pick) {
            return 0;
        }
        if (pick == 0 && delivery == 0) {
            return 1;
        }

        // Overlapping sub-problem
        if (dp[pick][delivery] != -1) {
            return dp[pick][delivery];
        }

        long count = 0;

        // For pick
        count = addMod(count, (pick) * solveByTopDownDP(pick - 1, delivery, dp));

        // For Delivery
        count = addMod(count, (delivery - pick) * solveByTopDownDP(pick, delivery - 1, dp));

        return dp[pick][delivery] = count;
    }

    public int countOrders(int n) {

        // return (int)solveByRecursion(n,n);

        long[][] dp = new long[n + 1][n + 1];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }

        return (int) solveByTopDownDP(n, n, dp);

    }
}