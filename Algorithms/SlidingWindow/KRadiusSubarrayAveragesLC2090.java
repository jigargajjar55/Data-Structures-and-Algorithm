package Algorithms.SlidingWindow;
import java.util.*;

class Solution {
    public int[] getAverages(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[n];

        // Time: O(N), Space:O(2*k + 1){For sliding window}

        if (k == 0) {
            for (int index = 0; index < n; index++) {
                result[index] = nums[index];
            }
        } else {

            Queue<Integer> q = new LinkedList<>();
            long sum = 0;
            Arrays.fill(result, -1);
            long windowSize = (2 * k) + 1;
            int sourcePoint = n;

            for (int index = 0; index < (int) windowSize; index++) {

                if (index < n) {
                    sum += nums[index];
                    q.offer(index);

                    if (index == k) {
                        sourcePoint = index;
                    }
                }
            }

            if (sourcePoint < n && windowSize <= n) {
                long avg = sum / windowSize;

                result[sourcePoint] = (int) avg;
            }

            for (int index = (int) windowSize; index < n; index++) {

                if (!q.isEmpty() && (index - q.peek()) >= windowSize) {
                    int removeIndex = q.peek();
                    q.poll();
                    sum -= nums[removeIndex];
                }

                sum += nums[index];
                q.offer(index);

                sourcePoint++;

                long avg = sum / windowSize;

                result[sourcePoint] = (int) avg;

            }

        }

        return result;

    }
}