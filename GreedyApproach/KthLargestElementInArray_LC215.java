package GreedyApproach;
import java.util.*;

public class KthLargestElementInArray_LC215 {

    // Time: O(N * log K), Space: O(K){For PriorityQueue}
    public int findKthLargestWithPQ(int[] nums, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {

            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else {

                if (minHeap.peek() < num) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
        }

        int ans = minHeap.peek();

        return ans;

    }


    // https://leetcode.com/problems/kth-largest-element-in-an-array/solutions/2180600/a-guide-to-quick-select-java/
    //Time: O(N), Space: O(log N)
    private int partition(int[] nums, int start, int end) {
        int pivot = start, temp;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot])
                start++;
            while (start <= end && nums[end] > nums[pivot])
                end--;
            if (start > end)
                break;
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        temp = nums[end];
        nums[end] = nums[pivot];
        nums[pivot] = temp;
        return end;
    }

    public int findKthLargestWithQuickSelect(int[] nums, int k) {
        int start = 0, end = nums.length - 1, index = nums.length - k;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (pivot < index)
                start = pivot + 1;
            else if (pivot > index)
                end = pivot - 1;
            else
                return nums[pivot];
        }
        return nums[start];
    }

}