package BinarySearch;
public class FindInMountainArray_LC1095 {

    /*
     * // This is MountainArray's API interface.
     * // You should not implement it, or speculate about its implementation
     */
    interface MountainArray {
        public int get(int index);
        public int length();
    }

    private int getPeakIndex(MountainArray mountainArr, int n) {
        int start = 0;
        int end = n - 1;

        while (start < end) {

            int mid = start + ((end - start) / 2);

            if ((mid + 1 < n) && (mountainArr.get(mid) < mountainArr.get(mid + 1))) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    private int getTargetFromLeft(MountainArray mountainArr, int start, int end, int target) {

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            int currElement = mountainArr.get(mid);

            if (currElement == target) {
                return mid;
            } else if (currElement < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;

    }

    private int getTargetFromRight(MountainArray mountainArr, int start, int end, int target) {

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            int currElement = mountainArr.get(mid);

            if (currElement == target) {
                return mid;
            } else if (currElement < target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;

    }

    //Time: O(3 * log N),
    //Space: O(1)
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int n = mountainArr.length();

        int peakIndex = getPeakIndex(mountainArr, n);

        if (mountainArr.get(peakIndex) == target) {
            return peakIndex;
        }
        // System.out.println(peakIndex);

        int resultIndex = -1;

        if (mountainArr.get(0) <= target && mountainArr.get(peakIndex) >= target) {
            resultIndex = getTargetFromLeft(mountainArr, 0, peakIndex, target);
        }

        if (resultIndex > -1) {
            return resultIndex;
        }

        if ((peakIndex + 1 < n) && (mountainArr.get(n - 1) <= target && mountainArr.get(peakIndex + 1) >= target)) {

            resultIndex = getTargetFromRight(mountainArr, peakIndex + 1, n - 1, target);
            // System.out.println(resultIndex);
        }

        return resultIndex;

    }
}