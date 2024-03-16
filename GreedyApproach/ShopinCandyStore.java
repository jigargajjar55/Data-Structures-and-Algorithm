package GreedyApproach;
import java.util.*;

public class ShopinCandyStore {

    // Time : O((N * log(N)) + N + N), Space: O(2)
    static ArrayList<Integer> candyStore(int candies[], int N, int K) {

        ArrayList<Integer> result = new ArrayList<>();

        int miniCost = 0;
        int maxCost = 0;

        Arrays.sort(candies);

        int i = 0;
        int j = N - 1;

        while (i <= j) {

            miniCost += candies[i];
            i++;
            j = j - K;
        }

        i = 0;
        j = N - 1;

        while (i <= j) {
            maxCost += candies[j];
            j--;
            i = i + K;
        }

        result.add(miniCost);
        result.add(maxCost);

        return result;

    }
}
