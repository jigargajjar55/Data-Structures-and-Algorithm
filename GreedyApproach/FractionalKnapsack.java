package GreedyApproach;
import java.util.*;

public class FractionalKnapsack {

    private class Item {
        int value, weight;

        Item(int x, int y) {
            this.value = x;
            this.weight = y;
        }
    }

    private class Tuple {
        int value;
        int weight;
        double perUnit;

        Tuple(int value, int weight, double perUnit) {
            this.value = value;
            this.weight = weight;
            this.perUnit = perUnit;
        }
    }

    // Time : O( N + (N * log(N) + N)), Space : O(N)
    // Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n) {
        ArrayList<Tuple> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double perUnitValue = (1.0 * arr[i].value) / arr[i].weight;
            list.add(new Tuple(arr[i].value, arr[i].weight, perUnitValue));
        }

        Collections.sort(list, (t1, t2) -> Double.compare(t2.perUnit, t1.perUnit));

        double totalValue = 0;

        for (int i = 0; i < n; i++) {
            if (list.get(i).weight > W) {

                totalValue += W * (list.get(i).perUnit);
                W = 0;

            } else {
                totalValue += list.get(i).value;
                W = W - list.get(i).weight;
            }
        }

        return totalValue;
    }
}
