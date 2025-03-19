import java.util.ArrayDeque;
import java.util.ArrayList;

public class LongestBoundedDifferenceSubarray {

    // Time: O(N)
    // Space: O(N)
    public ArrayList<Integer> longestSubarray(int[] arr, int x) {

        int n = arr.length;
        int resultIndex = -1;
        int resultLen = 0;
        Deque<Integer> miniQ = new ArrayDeque<>();
        Deque<Integer> maxiQ = new ArrayDeque<>();
        int left = 0;

        for (int right = 0; right < n; right++) {

            while (!miniQ.isEmpty() && arr[miniQ.peekLast()] > arr[right]) {
                miniQ.pollLast();
            }

            while (!maxiQ.isEmpty() && arr[maxiQ.peekLast()] < arr[right]) {
                maxiQ.pollLast();
            }

            miniQ.offerLast(right);
            maxiQ.offerLast(right);

            while (arr[maxiQ.peekFirst()] - arr[miniQ.peekFirst()] > x) {

                if (arr[left] == arr[maxiQ.peekFirst()]) {
                    maxiQ.pollFirst();
                } else if (arr[left] == arr[miniQ.peekFirst()]) {
                    miniQ.pollFirst();
                }

                left++;
            }

            int currLen = right - left + 1;

            if (resultLen < currLen) {
                resultLen = currLen;
                resultIndex = left;
            }

        }

        ArrayList<Integer> result = new ArrayList<>();

        if (resultIndex == -1) {
            return result;
        }

        for (int i = resultIndex; i < (resultIndex + resultLen); i++) {
            result.add(arr[i]);
        }

        return result;

    }
}
