import java.util.*;

public class MaximumSubsequenceScoreLC2542 {

    // Approach: Applied Greedy algorithm.
    // 1) We Pair number from nums1 and nums2 array
    // 2) Sort pair based on nums2 elements in descending order
    // 3) Now we process first k size subsequence and get first result
    // 4) We will also use minHeap to maintain minimum value remove in each window
    // processing action
    // 5) After processing first k size window, we will calculate result
    // 6) Now we will process remaining elements by using sliding window
    // logic(slight modification).
    // 7) We will remove minimum value from totalSum, and add current nums1 value.
    // Will follow the same process as we done in first.

    // Time: O(N * logN), Space: O(N + K)
    public long maxScore(int[] nums1, int[] nums2, int k) {

        int n = nums1.length;

        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }

        Arrays.sort(pairs, (p1, p2) -> Integer.compare(p2[1], p1[1]));

        long totalSum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            totalSum += (long) pairs[i][0];
            pq.offer(pairs[i][0]);
        }

        long result = (totalSum * pairs[k - 1][1]);

        for (int i = k; i < n; i++) {
            int topMin = pq.peek();
            pq.poll();
            totalSum = totalSum - topMin;

            totalSum += (long) pairs[i][0];
            pq.offer(pairs[i][0]);
            // Each and every window minimum value from nums2 will be last element in that
            // window.
            result = Math.max(result, (totalSum * pairs[i][1]));
        }

        return result;
    }
}
