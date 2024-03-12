package BinarySearch;
public class MaximumValueAtGivenIndenInaBoundedArrayLC1802 {

    // Time: O(log(maxSum)), Space: O(1)
    private boolean isPossible(int n, int index, int maxSum, long mid) {
        int leftSpace = index;
        int rightSpace = n - index - 1;

        long leftSum = 0;
        long rightSum = 0;
        long m = mid - 1;
        long sum = mid;

        if (rightSpace <= m) {

            rightSum = ((m * (m + 1)) / 2) - (((m - rightSpace) * (m - rightSpace + 1)) / 2);

        } else {
            rightSum = ((m * (m + 1)) / 2) + (1 * (rightSpace - m));
        }

        if (leftSpace <= m) {

            leftSum = ((m * (m + 1)) / 2) - (((m - leftSpace) * (m - leftSpace + 1)) / 2);

        } else {
            leftSum = ((m * (m + 1)) / 2) + (1 * (leftSpace - m));
        }

        sum += leftSum + rightSum;

        if (sum <= maxSum) {
            return true;
        } else {
            return false;
        }

    }

    public int maxValue(int n, int index, int maxSum) {

        long start = 1;
        long end = maxSum;

        long ans = 0;

        while (start <= end) {
            long mid = start + ((end - start) / 2);

            if (isPossible(n, index, maxSum, mid)) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return (int) ans;

    }
}