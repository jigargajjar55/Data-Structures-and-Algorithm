package BinarySearch;

public class FindMinimuminRotatedSortedArrayII_LC154 {
    // Time: Avg. case: O(log N), Worst case: O(N)
    // Space: O(1)
    public int findMin(int[] nums) {

        int len = nums.length;
        int start = 0;
        int end = len - 1;

        // Edge Case
        if (nums[start] < nums[end]) {
            return nums[start];
        }

        while (start < end) {
            int mid = start + ((end - start) / 2);

            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else if(nums[mid] < nums[end]) {
                end = mid;
            }else{
                end -= 1;
            }
        }

        return nums[start];

    }
}
