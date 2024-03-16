package GreedyApproach;
import java.util.*;

public class LeastNumberofUniqueIntegersafterKRemovals_LC1481 {

    class Pair {
        int num;
        int freq;

        Pair(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }

    // Time: O(N * log N)
    // Space: O(2 * N){Map and Priority Queue for Greedy Approach}
    public int findLeastNumOfUniqueInts(int[] arr, int k) {

        int n = arr.length;
        Map<Integer, Integer> freq = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.freq, p2.freq));

        for (int i = 0; i < n; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }

        for (int key : freq.keySet()) {
            int value = freq.get(key);
            pq.offer(new Pair(key, value));
        }

        int count = k;

        while (count > 0) {
            Pair top = pq.peek();
            pq.poll();

            if (top.freq <= count) {
                count -= top.freq;
            } else {
                top.freq -= count;
                count = 0;
                pq.offer(top);
            }
        }

        return pq.size();

    }
}