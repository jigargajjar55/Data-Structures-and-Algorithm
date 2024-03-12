package DynamicProgramming.Stocks;


public class BestTimeToBuyAndSellStock {

    // Time: O(N), Space: O(1)
    public int maxProfit(int[] prices) {

        int maxProfit = 0;
        int mini = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int cost = prices[i] - mini;

            maxProfit = Math.max(maxProfit, cost);

            mini = Math.min(mini, prices[i]);
        }

        return maxProfit;

    }
}