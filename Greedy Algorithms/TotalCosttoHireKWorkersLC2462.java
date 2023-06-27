import java.util.*;

class Solution {

    // Time: O((candidates + K) * log (candidates)) , Space: O(candidates)
    public long totalCost(int[] costs, int k, int candidates) {

        int n = costs.length;
        PriorityQueue<Integer> firstHeap = new PriorityQueue<>(
                (f1, f2) -> (costs[f1] == costs[f2]) ? Integer.compare(f1, f2) : Integer.compare(costs[f1], costs[f2]));
        PriorityQueue<Integer> lastHeap = new PriorityQueue<>(
                (l1, l2) -> (costs[l1] == costs[l2]) ? Integer.compare(l1, l2) : Integer.compare(costs[l1], costs[l2]));

        for (int i = 0; i < candidates; i++) {
            firstHeap.offer(i);
            if (((n - candidates) + i) >= candidates) {
                lastHeap.offer((n - candidates) + i);
            }
        }

        long cost = 0;
        int ptr1 = candidates;
        int ptr2 = n - candidates - 1;

        while (k > 0) {
            if (lastHeap.isEmpty() || (!firstHeap.isEmpty() && costs[firstHeap.peek()] <= costs[lastHeap.peek()])) {

                cost += costs[firstHeap.peek()];
                System.out.println("Index: " + firstHeap.peek() + " Cost: " + cost);
                firstHeap.poll();

                if (ptr1 < n && ptr1 <= ptr2) {

                    firstHeap.offer(ptr1);
                    ptr1++;
                }
                k--;
            } else {
                cost += costs[lastHeap.peek()];
                System.out.println("Index: " + lastHeap.peek() + " Cost: " + cost);
                lastHeap.poll();

                if (ptr2 >= 0 && ptr1 <= ptr2) {

                    lastHeap.offer(ptr2);
                    ptr2--;

                }
                k--;
            }
        }

        return cost;

    }
}