import java.util.*;

public class ChampagneTower_LC799 {

    // https://leetcode.com/problems/champagne-tower/solutions/1818599/full-visual-explanation-dp-beginner-friendly-easy-and-simple-c/?envType=daily-question&envId=2023-09-24

    // Time: O(query_row * query_glass)
    // Space: O(query_row * query_glass){2D DP Array}
    public double champagneTower(int poured, int query_row, int query_glass) {

        double[][] dp = new double[query_row + 2][query_row + 2];

        dp[0][0] = (double) (poured);

        for (int i = 0; i <= query_row; i++) {
            for (int j = 0; j <= i; j++) {

                double quantity = ((double) dp[i][j] - 1) / 2;

                if (quantity > 0) {
                    dp[i + 1][j] += quantity;
                    dp[i + 1][j + 1] += quantity;
                }
            }
        }

        double ans = Math.min(1, dp[query_row][query_glass]);

        return ans;

    }
}
