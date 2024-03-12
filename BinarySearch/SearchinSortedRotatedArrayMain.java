package BinarySearch;

public class SearchinSortedRotatedArrayMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int nums[] = { 1, 23, 0, 2 };
        int target = 2;

        int numsSize = nums.length;

        // int pivot = getPivot(nums,numsSize);
        int pivot = getPivotRecursion(nums, 0, numsSize - 1);

        // System.out.println(pivot);

        int resultIndex = -1;

        if ((nums[pivot] <= target) && (nums[numsSize - 1] >= target)) {

            // result = binarySearchforTarget(nums,pivot,numsSize-1,target);
            resultIndex = binarySearchRecursion(nums, pivot, numsSize - 1, target);

        } else {

            // result = binarySearchforTarget(nums,0,pivot-1,target);
            resultIndex = binarySearchRecursion(nums, 0, pivot - 1, target);

        }

        System.out.println(resultIndex);
    }

    static int getPivot(int[] arr, int size) {

        int start = 0;
        int end = size - 1;

        int mid = start + ((end - start) / 2);

        while (start < end) {

            if (arr[mid] >= arr[0]) {

                start = mid + 1;
            } else {
                end = mid;
            }

            mid = start + ((end - start) / 2);

        }

        return start;

    }

    static int binarySearchforTarget(int[] arr, int start, int end, int key) {

        int mid = start + ((end - start) / 2);

        while (start <= end) {

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            mid = start + ((end - start) / 2);
        }

        return -1;

    }

    static int getPivotRecursion(int[] arr, int start, int end) {

        if (start >= end) {
            return start;
        }
        int mid = start + ((end - start) / 2);
        if (arr[mid] >= arr[0]) {
            return getPivotRecursion(arr, mid + 1, end);
        } else {
            return getPivotRecursion(arr, start, mid);
        }

    }

    static int binarySearchRecursion(int[] arr, int start, int end, int key) {

        if (start > end) {
            return -1;
        }

        int mid = start + ((end - start) / 2);

        if (arr[mid] == key) {
            return mid;
        } else if (arr[mid] > key) {
            return binarySearchRecursion(arr, start, mid - 1, key);
        } else {
            return binarySearchRecursion(arr, mid + 1, end, key);
        }

    }

}
