package Patterns.SlidingWindow;

import java.util.*;

/* Intuition
Using the PriorityQueue and pushing the k elements for evey subarray every time makes it complex as it gives TLE,as the constraints(array elements) of the problems are very less i.e (-50<=nums[i]<=50>) we can keep track of the freq of the array elements and get the Xth smallest number.

Approach
running two loops for finding the subarray can be done for the frequency but the Time complextity will be O(N*N) for that we use the sliding window technique for getting subarray frequency in sliding window techinique if the length of the window hits K value everytime we remove the left element frequency and add the right element frequnecy and get the xth Smallest element in the subarray and if the xth smallest number is negative we simply store it else we store it as 0 in ans array.

Complexity
Time complexity:F
O(N) for the while loop (sliding window) and we travese the freq array of size 101;

TC -> O(N*101)

Space complexity:
we store the freq of the elements so SC -> O(101) and we store the ans array i.e O(number of subarrays) i.e O(N-K+1)
 */

public class SlidingSubarrayBeautyLC2653 {

    private int getXthNegativeInt(int[] freq, int x) {
        int count = 0;
        for (int i = 0; i < 101; i++) {

            count += freq[i];

            if (count >= x) {
                if ((i - 50) < 0) {
                    return (i - 50);
                } else {
                    break;
                }
            }
        }
        return 0;
    }

    // Time : O(N * 101), Space : O(101)
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;
        int[] freq = new int[101];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            freq[nums[i] + 50]++;
            q.offer(i);
        }

        result[index++] = getXthNegativeInt(freq, x);

        for (int i = k; i < n; i++) {

            while (!q.isEmpty() && i - q.peek() >= k) {
                int ind = q.peek();
                q.poll();
                freq[nums[ind] + 50]--;
            }

            freq[nums[i] + 50]++;
            q.offer(i);

            result[index++] = getXthNegativeInt(freq, x);

        }

        return result;
    }
}
