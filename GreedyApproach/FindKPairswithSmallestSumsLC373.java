package GreedyApproach;
import java.util.*;

class Tuple {
    int a;
    int b;
    long sum;

    Tuple(int a, int b, long sum) {
        this.a = a;
        this.b = b;
        this.sum = sum;
    }
}

class Tuple1 {
    int a;
    int b;
    int index;

    Tuple1(int a, int b, int index) {
        this.a = a;
        this.b = b;
        this.index = index;
    }
}

public class FindKPairswithSmallestSumsLC373 {

    // Bruteforce solution
    // Time: O((M * N) * log K), Space: O(K)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums1.length;
        int m = nums2.length;

        PriorityQueue<Tuple> pq = new PriorityQueue<>((p1, p2) -> Long.compare(p2.sum, p1.sum));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                Tuple newTuple = new Tuple(nums1[i], nums2[j], nums1[i] + nums2[j]);

                if (pq.size() < k) {
                    pq.offer(newTuple);
                } else {

                    Tuple top = pq.peek();

                    if (top.sum > newTuple.sum) {
                        pq.poll();
                        pq.offer(newTuple);
                    } else {
                        break;
                    }

                }

            }
        }

        while (!pq.isEmpty()) {
            Tuple top = pq.peek();
            List<Integer> temp = Arrays.asList(top.a, top.b);
            pq.poll();

            result.add(temp);
        }
        return result;
    }

    // Optimized Approach
    // Time: O(K * log K), Space: O(K)
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums1.length;
        int m = nums2.length;

        PriorityQueue<Tuple1> pq = new PriorityQueue<>((p1, p2) -> Long.compare(p1.a + p1.b, p2.a + p2.b));
        for (int i = 0; i < k && i < n; i++) {
            pq.offer(new Tuple1(nums1[i], nums2[0], 0));

        }

        while (k > 0 && !pq.isEmpty()) {
            Tuple1 top = pq.peek();
            List<Integer> temp = Arrays.asList(top.a, top.b);
            pq.poll();

            result.add(temp);

            if (top.index + 1 < m) {
                pq.offer(new Tuple1(top.a, nums2[top.index + 1], top.index + 1));
            }
            k--;
        }
        return result;
    }
}
